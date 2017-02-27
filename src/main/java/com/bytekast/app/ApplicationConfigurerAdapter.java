package com.bytekast.app;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.bytekast.filter.ForceSSLFilter;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.EnumSet;

@Configuration
public class ApplicationConfigurerAdapter extends WebMvcConfigurerAdapter {

  @Bean
  public FilterRegistrationBean forceSslFilter() {
    EnumSet<DispatcherType> types = EnumSet.noneOf(DispatcherType.class);
    types.addAll(Arrays.asList(DispatcherType.values()));

    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new ForceSSLFilter());
    registrationBean.setName("forceSSLFilter");
    registrationBean.setDispatcherTypes(types);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(0);

    return registrationBean;
  }


}


