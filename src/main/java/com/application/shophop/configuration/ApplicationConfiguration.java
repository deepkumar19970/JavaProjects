package com.application.shophop.configuration;

import com.application.shophop.filter.LoginFIlter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableSpringConfigured
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Autowired
    private DataSourceInterceptor dataSourceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataSourceInterceptor).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean <LoginFIlter> filterRegistrationBean() {
        FilterRegistrationBean < LoginFIlter > registrationBean = new FilterRegistrationBean();
        LoginFIlter loginFIlter = new LoginFIlter();

        registrationBean.setFilter(loginFIlter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); //set precedence
        return registrationBean;
    }

}
