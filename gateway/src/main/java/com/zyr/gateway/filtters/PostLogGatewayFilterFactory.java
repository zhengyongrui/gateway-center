package com.zyr.gateway.filtters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("post为Gateway转发请求之后调用");
            log.info("过滤器的参数为: {} = {}", config.getName(), config.getValue());
            // 通过exchange.getResponse方法修改Response
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            log.info("请求后的getStatusCode = {}", serverHttpResponse.getStatusCode().value());
            // 通过chain.filter传递给下一个过滤器处理
            return chain.filter(exchange);
        };
    }
}
