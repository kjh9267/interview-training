package me.jun.interviewtraining.common.config;

import lombok.RequiredArgsConstructor;
import me.jun.interviewtraining.common.UserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final UserResolver userResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userResolver);
    }
}
