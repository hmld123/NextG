package com.github.hmld.common.logger;

/**
 * 被操作的单元
 * 
 * @author hmldt
 *
 */
public enum OperationUnit {
  UNKNOWN("unknown"), 
  USER("user"), 
  EMPLOYEE("employee"), 
  SERVER("server"), 
  REDIS("redis");

  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  private OperationUnit(String value) {
    this.value = value;
  }
}
