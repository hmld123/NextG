package com.github.hmld.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.github.hmld.common.utils.spring.SpringUtils;
/**
 * 获取i18n资源文件
 * @author hmld
 *
 */
public class MsageUtils {
  /**
   * 据消息键和参数 获取消息 委托给spring messageSource
   * @param key 消息键
   * @param args 参数
   * @return 获取国际化翻译值
   */
  public static String message(String key, Object... args) {
    MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
    return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
  }
  
  /**
   * 据消息键和参数 获取消息 委托给spring messageSource
   * @param key 消息键
   * @return 获取国际化翻译值
   */
  public static String message(String key) {
    MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
    return messageSource.getMessage(key, null,  LocaleContextHolder.getLocale());
  }
}
