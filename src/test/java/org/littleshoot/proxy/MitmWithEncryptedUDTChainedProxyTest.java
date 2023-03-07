package org.littleshoot.proxy;

import org.junit.BeforeClass;
import com.ly.travel.institute.proxy.server.ChainedProxy;
import com.ly.travel.institute.proxy.server.HttpProxyServerBootstrap;
import com.ly.travel.institute.proxy.server.SslEngineSource;
import com.ly.travel.institute.proxy.server.TransportProtocol;
import com.ly.travel.institute.proxy.server.extras.SelfSignedSslEngineSource;
import javax.net.ssl.SSLEngine;
import static com.ly.travel.institute.proxy.server.TransportProtocol.UDT;
import static org.littleshoot.proxy.TestUtils.disableOnMac;

public class MitmWithEncryptedUDTChainedProxyTest extends MitmWithChainedProxyTest {
    private final SslEngineSource sslEngineSource = new SelfSignedSslEngineSource(
            "chain_proxy_keystore_1.jks");

    @BeforeClass
    public static void beforeClass() {
        disableOnMac();
    }

    @Override
    protected HttpProxyServerBootstrap upstreamProxy() {
        return super.upstreamProxy()
                .withTransportProtocol(UDT)
                .withSslEngineSource(sslEngineSource);
    }

    @Override
    protected ChainedProxy newChainedProxy() {
        return new BaseChainedProxy() {
            @Override
            public TransportProtocol getTransportProtocol() {
                return TransportProtocol.UDT;
            }

            @Override
            public boolean requiresEncryption() {
                return true;
            }

            @Override
            public SSLEngine newSslEngine() {
                return sslEngineSource.newSslEngine();
            }
        };
    }
}
