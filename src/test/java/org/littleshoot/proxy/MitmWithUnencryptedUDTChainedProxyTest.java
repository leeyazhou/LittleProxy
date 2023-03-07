package org.littleshoot.proxy;

import org.junit.BeforeClass;
import com.ly.travel.institute.proxy.server.ChainedProxy;
import com.ly.travel.institute.proxy.server.HttpProxyServerBootstrap;
import com.ly.travel.institute.proxy.server.TransportProtocol;
import static com.ly.travel.institute.proxy.server.TransportProtocol.UDT;
import static org.littleshoot.proxy.TestUtils.disableOnMac;

public class MitmWithUnencryptedUDTChainedProxyTest extends MitmWithChainedProxyTest {
    @BeforeClass
    public static void beforeClass() {
        disableOnMac();
    }

    @Override
    protected HttpProxyServerBootstrap upstreamProxy() {
        return super.upstreamProxy()
                .withTransportProtocol(UDT);
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
                return false;
            }
        };
    }
}
