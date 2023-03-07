package org.littleshoot.proxy;

import javax.net.ssl.SSLEngine;
import com.ly.travel.institute.proxy.server.ChainedProxy;
import com.ly.travel.institute.proxy.server.HttpProxyServerBootstrap;
import com.ly.travel.institute.proxy.server.SslEngineSource;
import com.ly.travel.institute.proxy.server.extras.SelfSignedSslEngineSource;

public class SelfSignedSslEngineChainedProxyTest extends BaseChainedProxyTest {
    private final SslEngineSource sslEngineSource = new SelfSignedSslEngineSource("/certificate/chain_proxy_keystore.jks",
            false, true, "littleproxy", "Be Your Own Lantern");

    @Override
    protected HttpProxyServerBootstrap upstreamProxy() {
        return super.upstreamProxy()
                .withSslEngineSource(sslEngineSource);
    }

    @Override
    protected ChainedProxy newChainedProxy() {
        return new BaseChainedProxy() {
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
