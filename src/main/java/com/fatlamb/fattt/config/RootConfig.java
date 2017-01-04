package com.fatlamb.fattt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by 58 on 2017/1/3.
 */
@Configuration
@ComponentScan(basePackages = {"com.fatlamb.fattt"} , excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION , value = EnableWebMvc.class)
})
public class RootConfig {
}
