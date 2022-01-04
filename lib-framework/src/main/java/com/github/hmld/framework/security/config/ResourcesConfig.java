package com.github.hmld.framework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @author hmld
 *
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

  /**
   * 跨域配置
   */
  @Bean
  public CorsFilter corsFilter()
  {
      CorsConfiguration config = new CorsConfiguration();
      // 是否允许请求带有验证信息
      config.setAllowCredentials(true);
      // 设置访问源地址
      config.addAllowedOriginPattern("*");
      // 设置访问源请求头
      config.addAllowedHeader("*");
      // 设置访问源请求方法
      config.addAllowedMethod("*");
      // 对接口配置跨域设置
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
  }
}
