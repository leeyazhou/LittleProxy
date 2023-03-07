package org.littleshoot.proxy;

import static com.ly.travel.institute.proxy.server.TransportProtocol.TCP;
import javax.net.ssl.SSLEngine;
import com.ly.travel.institute.proxy.server.ChainedProxy;
import com.ly.travel.institute.proxy.server.HttpProxyServerBootstrap;
import com.ly.travel.institute.proxy.server.SslEngineSource;
import com.ly.travel.institute.proxy.server.TransportProtocol;
import com.ly.travel.institute.proxy.server.extras.SelfSignedSslEngineSource;

/**
 * Tests that servers are authenticated and that if they're missing certs, we
 * get an error.
 */
public class MitmWithBadServerAuthenticationTCPChainedProxyTest extends 
        MitmWithChainedProxyTest {
    protected final SslEngineSource serverSslEngineSource = new SelfSignedSslEngineSource(
            "chain_proxy_keystore_1.jks");
    
    protected final SslEngineSource clientSslEngineSource = new SelfSignedSslEngineSource(
            "chain_proxy_keystore_2.jks");

    @Override
    protected boolean expectBadGatewayForEverything() {
        return true;
    }
    
    @Override
    protected HttpProxyServerBootstrap upstreamProxy() {
        return super.upstreamProxy()
                .withTransportProtocol(TCP)
                .withSslEngineSource(serverSslEngineSource);
    }

    @Override
    protected ChainedProxy newChainedProxy() {
        return new BaseChainedProxy() {
            @Override
            public TransportProtocol getTransportProtocol() {
                return TransportProtocol.TCP;
            }

            @Override
            public boolean requiresEncryption() {
                return true;
            }

            @Override
            public SSLEngine newSslEngine() {
                return clientSslEngineSource.newSslEngine();
            }
        };
    }
}
