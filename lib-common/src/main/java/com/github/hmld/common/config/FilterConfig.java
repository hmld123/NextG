package com.github.hmld.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hmld.common.filter.RepeatableFilter;
import com.github.hmld.common.filter.XssFilter;
import com.github.hmld.common.utils.StringUtils;

/**
 * XSS防护 配置
 * 
 * @author hmld
 *
 */
@Configuration
public class FilterConfig {
  /** XSS防护开关 */
  @Value(value = "${xss.enabled}")
  private String enabled;
  /** 排除连接（用多个逗号分隔） */
  @Value(value = "${xss.excludes}")
  private String excludes;
  /** 匹配链接（用多个逗号分隔） */
  @Value(value = "${xss.urlPatterns}")
  private String urlPatterns;

  @Bean
  public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
    FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<XssFilter>();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new XssFilter());
    registration.addUrlPatterns(StringUtils.split(urlPatterns, ','));
    registration.setName("xssFilter");
    registration.setOrder(Integer.MAX_VALUE);
    Map<String, String> initParameters = new HashMap<String, String>();
    initParameters.put("enabled", enabled);
    initParameters.put("excludes", excludes);
    registration.setInitParameters(initParameters);
    return registration;
  }

  @Bean
  public FilterRegistrationBean<RepeatableFilter> someFilterRegistration() {
    FilterRegistrationBean<RepeatableFilter> registration = new FilterRegistrationBean<RepeatableFilter>();
    registration.setFilter(new RepeatableFilter());
    registration.addUrlPatterns("/*");
    registration.addServletNames("repeatableFilter");
    registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
    return registration;
  }
}
