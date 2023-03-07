package org.littleshoot.proxy;

import static com.ly.travel.institute.proxy.server.TransportProtocol.TCP;
import com.ly.travel.institute.proxy.server.ChainedProxy;
import com.ly.travel.institute.proxy.server.HttpProxyServerBootstrap;
import com.ly.travel.institute.proxy.server.TransportProtocol;

public class MitmWithUnencryptedTCPChainedProxyTest extends MitmWithChainedProxyTest {
    @Override
    protected HttpProxyServerBootstrap upstreamProxy() {
        return super.upstreamProxy()
                .withTransportProtocol(TCP);
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
                return false;
            }
        };
    }
}
