package com.github.hmld.common.logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.LoggerUtil;

@Aspect
@Component
public class LogAspect {
  private static final Logger logger = LoggerUtil.initLogger(LogAspect.class);
  private Long functionStartTime = 0L;
  /**
   * 此处的切点是注解方式，也可以用包名的方式达到相同的效果 '@Pointcut("execution(*
   * com.wwj.springboot.service.impl.*.*(..))")'
   */
  @Pointcut("@annotation(com.github.hmld.common.logger.Log)")
  public void operationLog() {
  }

  @Around("operationLog()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Object res = null;
    long time = System.currentTimeMillis();
    try {
      res = joinPoint.proceed();
      time = System.currentTimeMillis() - time;
      return res;
    } finally {
      try {
        SysOperationLog operationLog = addOperationLog(joinPoint, res, time);
        logger.info(operationLog.toString());
      } catch (Exception e) {
        logger.error("LogAspect 操作失败：" + e.getMessage());
      }
    }
  }

  private SysOperationLog addOperationLog(ProceedingJoinPoint joinPoint, Object res, long time) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      MethodSignature signature = (MethodSignature) joinPoint.getSignature();
      SysOperationLog operationLog = new SysOperationLog();
      operationLog.setRunTime(time);
      operationLog.setReturnValue(mapper.writeValueAsString(res));
      operationLog.setId(UUID.randomUUID().toString());
      operationLog.setArgs(mapper.writeValueAsString(joinPoint.getArgs()));
      operationLog.setCreateTime(new Date());
      operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
      operationLog.setUserId("#{currentUserId}");
      operationLog.setUserName("#{currentUserName}");
      Log log = signature.getMethod().getAnnotation(Log.class);
      if (log != null) {
        operationLog.setLevel(log.level());
        operationLog.setDescribestr(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(),
            joinPoint.getArgs(), log, mapper));
        operationLog.setOperationType(log.operationType().getValue());
        operationLog.setOperationUnit(log.operationUnit().getValue());
      }
      return operationLog;
    } catch (JsonProcessingException e) {
      logger.error("LogAspect 操作失败：" + e.getMessage());
      return null;
    }
  }

  private String getDetail(String[] parameterNames, Object[] args, Log log, ObjectMapper mapper) {
    Map<Object, Object> map = new HashMap<Object, Object>(4);
    for (int i = 0; i < parameterNames.length; i++) {
      map.put(parameterNames[i], args[i]);
    }

    String detail = log.detail();
    try {
      detail = "'" + "#{currentUserName}" + "'=》" + log.detail();
      for (Map.Entry<Object, Object> entry : map.entrySet()) {
        Object k = entry.getKey();
        Object v = entry.getValue();
        detail = detail.replace("{{" + k + "}}", mapper.writeValueAsString(v));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return detail;
  }

  /**
   * 进入方法前执行
   * 
   * @param joinPoint
   */
  @Before("operationLog()")
  public void doBeforeAdvice(JoinPoint joinPoint) {
    this.functionStartTime = DateUtils.getNowDate().getTime();
    logger.info("方法开始执行时间：" + functionStartTime);
  }

  /**
   * 处理完请求，返回内容
   * 
   * @param ret
   */
  @AfterReturning(returning = "ret", pointcut = "operationLog()")
  public void doAfterReturning(Object ret) {
    logger.info("方法的返回值 : " + ret);
  }

  /**
   * 后置异常通知
   */
  @AfterThrowing("operationLog()")
  public void throwss(JoinPoint jp) {
    logger.info("方法异常时执行.....");
  }

  /**
   * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
   */
  @After("operationLog()")
  public void after(JoinPoint jp) {
    long functionEndTime = DateUtils.getNowDate().getTime();
    logger.info("方法最后执行.....");
    logger.info("方法开始结束时间：" + functionEndTime);
    logger.info("用时：" + (functionEndTime - this.functionStartTime) +" ms");
  }
}
