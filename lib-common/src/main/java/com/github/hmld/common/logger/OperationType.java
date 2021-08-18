package com.github.hmld.common.logger;

/**
 * 操作类型
 * 
 * @author hmldt
 *
 */
public enum OperationType {
  UNKNOWN("unknown"), 
  DELETE("delete"), 
  SELECT("select"), 
  UPDATE("update"), 
  INSERT("insert");

  private String value;
  
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  private OperationType(String value) {
    this.value = value;
  }
}
