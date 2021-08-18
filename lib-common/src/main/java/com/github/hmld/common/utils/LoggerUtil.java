package com.github.hmld.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger工具
 * 
 * @author hmld
 *
 */
public class LoggerUtil {
  public static Logger initLogger(Class<?> clas) {
    return LoggerFactory.getLogger(clas);
  }
}
