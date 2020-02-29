package com.zyr.gateway.filtters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("pre为Gateway转发请求之前调用");
            log.info("过滤器的参数为: {} = {}", config.getName(), config.getValue());
            // 通过exchange.getRequest().mutate()方法修改Request
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .build();
            // 通过exchange.mutate()此方法修改Exchange
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
            // 通过chain.filter传递给下一个过滤器处理
            return chain.filter(modifiedExchange);
        };
    }
}
