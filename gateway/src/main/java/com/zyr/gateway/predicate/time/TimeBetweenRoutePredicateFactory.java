package com.zyr.gateway.predicate.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {

    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    /**
     * geteway最终调用的是{@link org.springframework.format.datetime.standard.DateTimeFormatterRegistrar}构造时间
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        return serverWebExchange -> {
            LocalTime startDate = config.getStartDate();
            LocalTime endDate = config.getEndDate();
            LocalTime now = LocalTime.now();
            return now.isAfter(startDate) && now.isBefore(endDate);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("startDate", "endDate");
    }

    public static void main(String[] args) {
        // 查看yml配置文件应该配置的字符串格式应该是怎么样的
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(dateFormatter.format(LocalDate.now()));
        System.out.println(timeFormatter.format(LocalTime.now()));
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
    }

}
