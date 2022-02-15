package io.somesh.bose.ordermanagement.infra.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

  @Override public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HttpReqHeaderInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(new LogRequestInterceptor()).addPathPatterns("/**");
  }
}
