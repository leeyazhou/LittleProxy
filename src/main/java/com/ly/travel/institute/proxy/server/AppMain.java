/**
 * 
 */
package com.ly.travel.institute.proxy.server;

import com.ly.travel.institute.proxy.server.impl.DefaultHttpProxyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @author leeyazhou
 *
 */
public class AppMain {

  public static void main(String[] args) throws InterruptedException {
    HttpProxyServer server = DefaultHttpProxyServer.bootstrap().withPort(8081).withFiltersSource(new HttpFiltersSourceAdapter() {
      public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        return new HttpFiltersAdapter(originalRequest) {
          @Override
          public HttpResponse clientToProxyRequest(HttpObject httpObject) {
            return null;
          }

          @Override
          public HttpObject serverToProxyResponse(HttpObject httpObject) {
            return httpObject;
          }
        };
      }
    }).start();
  }
}
