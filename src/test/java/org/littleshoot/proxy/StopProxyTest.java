package org.littleshoot.proxy;

import org.junit.Test;
import com.ly.travel.institute.proxy.server.HttpProxyServer;
import com.ly.travel.institute.proxy.server.impl.DefaultHttpProxyServer;

public class StopProxyTest {
    @Test
    public void testStop() {
        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
                .withPort(0)
                .start();

        proxyServer.stop();
    }

    @Test
    public void testAbort() {
        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
                .withPort(0)
                .start();

        proxyServer.abort();
    }
}
