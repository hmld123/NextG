package com.github.hmld.common.logger;

import java.util.Date;

public class SysOperationLog {
  private String id;

  private Date createTime;
  /**
   * 日志等级
   */
  private Integer level;
  /**
   * 被操作的对象
   */
  private String operationUnit;
  /**
   * 方法名
   */
  private String method;
  /**
   * 参数
   */
  private String args;
  /**
   * 操作人id
   */
  private String userId;
  /**
   * 操作人
   */
  private String userName;
  /**
   * 操作类型
   */
  private String operationType;
  /**
   * 方法运行时间
   */
  private Long runTime;
  /**
   * 方法返回值
   */
  private String returnValue;
  /**
   * 日志描述参数
   */
  private String describestr;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id == null ? null : id.trim();
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  /**
   * 获取 日志等级
   * @return
   */
  public Integer getLevel() {
    return level;
  }
  
  /**
   * 设置 日志等级
   * @return
   */
  public void setLevel(Integer level) {
    this.level = level;
  }
  /**
   * 获取 被操作的对象
   * @return
   */
  public String getOperationUnit() {
    return operationUnit;
  }
  
  /**
   * 设置 被操作的对象
   * @param operationUnit
   */
  public void setOperationUnit(String operationUnit) {
    this.operationUnit = operationUnit == null ? null : operationUnit.trim();
  }
  /**
   * 获取 方法名
   * @return
   */
  public String getMethod() {
    return method;
  }
  /**
   * 设置 方法名
   * @param method
   */
  public void setMethod(String method) {
    this.method = method == null ? null : method.trim();
  }
  /**
   * 获取 参数
   * @return
   */
  public String getArgs() {
    return args;
  }
  /**
   * 设置 参数
   * @param args
   */
  public void setArgs(String args) {
    this.args = args == null ? null : args.trim();
  }
  /**
   * 获取 操作人id
   * @return
   */
  public String getUserId() {
    return userId;
  }
  /** 
   * 设置 操作人id
   * @param userId
   */
  public void setUserId(String userId) {
    this.userId = userId == null ? null : userId.trim();
  }
  /**
   * 获取 操作人
   * @return
   */
  public String getUserName() {
    return userName;
  }
  /**
   * 设置 操作人
   * @param userName
   */
  public void setUserName(String userName) {
    this.userName = userName == null ? null : userName.trim();
  }
  /**
   * 获取 操作类型
   * @return
   */
  public String getOperationType() {
    return operationType;
  }
  /**
   * 设置 操作类型
   * @param operationType
   */
  public void setOperationType(String operationType) {
    this.operationType = operationType == null ? null : operationType.trim();
  }
  /**
   * 获取 方法运行时间
   * @return
   */
  public Long getRunTime() {
    return runTime;
  }
  /**
   * 设置 方法运行时间
   * @param runTime
   */
  public void setRunTime(Long runTime) {
    this.runTime = runTime;
  }
  /**
   * 获取 方法返回值
   * @return
   */
  public String getReturnValue() {
    return returnValue;
  }
  /**
   * 设置 方法返回值
   * @param returnValue
   */
  public void setReturnValue(String returnValue) {
    this.returnValue = returnValue == null ? null : returnValue.trim();
  }
  /**
   * 获取 日志描述参数
   * @return
   */
  public String getDescribestr() {
    return describestr;
  }
  /**
   * 设置 日志描述参数
   * @param describestr
   */
  public void setDescribestr(String describestr) {
    this.describestr = describestr;
  }
  
  @Override
  public String toString() {
    return "SysOperationLog [id=" + id + ", createTime=" + createTime + ", level=" + level + ", operationUnit="
        + operationUnit + ", method=" + method + ", args=" + args + ", userId=" + userId + ", userName=" + userName
        + ", operationType=" + operationType + ", runTime=" + runTime + ", returnValue=" + returnValue
        + ", describestr=" + describestr + "]";
  }

}