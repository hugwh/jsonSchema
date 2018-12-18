package com.example.json.schema.config;

import com.example.json.schema.filter.JsonSchemaFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


/**
 * 过滤器配置类
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-23 13:54
 */
@Configuration
public class FilterConfig {

    private final int FILTER_ORDER_JSONSCHEMA = 1;

    @Bean
    public Filter JsonSchemaFilter() {
        return new JsonSchemaFilter();
    }

    @Bean
    public FilterRegistrationBean jsonSchemaFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("jsonSchemaFilter");
        filterRegistrationBean.setFilter(JsonSchemaFilter());
        filterRegistrationBean.setOrder(this.FILTER_ORDER_JSONSCHEMA);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
