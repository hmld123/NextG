package com.github.hmld;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.LoggerUtil;
/**
 * 项目启动类
 * @author hmld
 */
@SpringBootApplication()
@MapperScan(basePackages = {"com.github.hmld.*.mapper"})
public class Next_G_Application {
  private static final Logger LOGGER = LoggerUtil.initLogger(Next_G_Application.class);
  
  public static void main(String[] args) {
    long startTime = DateUtils.getNowDate().getTime();
    SpringApplication application = new SpringApplication(Next_G_Application.class);
    application.setBannerMode(Mode.CONSOLE);
    application.run(args);
    startSuccesLog(startTime);
  }
  
  /**
   * 启动成功日志
   * @param startTime
   * @param LOGGER
   */
  private static void startSuccesLog(long startTime ) {
    long endTime = DateUtils.getNowDate().getTime();
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("\n====================================================================\n");
    stringBuffer.append("项目启动成功\n");
    stringBuffer.append("总耗时："+(endTime - startTime)+" ms\n");
    stringBuffer.append("====================================================================\n");
    LOGGER.info(stringBuffer.toString());
  }
}