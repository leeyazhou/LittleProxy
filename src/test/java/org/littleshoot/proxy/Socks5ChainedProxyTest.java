package org.littleshoot.proxy;

import com.ly.travel.institute.proxy.server.ChainedProxyType;

public class Socks5ChainedProxyTest extends BaseChainedSocksProxyTest {
    @Override
    protected ChainedProxyType getSocksProxyType() {
        return ChainedProxyType.SOCKS5;
    }
}
