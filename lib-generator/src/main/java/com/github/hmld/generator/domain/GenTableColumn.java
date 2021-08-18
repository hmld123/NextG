package com.github.hmld.generator.domain;
/**
 * 业务表对应字段
 * @author hmld
 *
 */
public class GenTableColumn {
  /** 字段对应的主键*/
  private Long pkColumn;
  /** 表对应的主键*/
  private Long pkTable;
  /** 表对应的编码*/
  private Long tableCode;
  /** 字段对应的名称*/
  private String columnName;
  /** 字段描述*/
  private String columnComment;
  /** 字段对应的类型*/
  private String columnType;
  /** 字段对应的JAVA类型*/
  private String javaType;
  /** 字段对应的JAVA类型的包路径*/
  private String javaTypePackage;
  /** 字段对应的JAVA字段名*/
  private String javaField;
  /** 是否主键（1是） */
  private String isPk;
  /** 是否自增（1是） */
  private String isIncrement;
  /** 是否必填（1是） */
  private String isRequired;
  /** 是否为保存该字段的值（1是） */
  private String isInsert;
  /** 是否可编辑字段（1是） */
  private String isEdit;
  /** 是否列表显示字段（1是） */
  private String isList;
  /** 是否可查询字段（1是） */
  private String isQuery;
  /** 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围） */
  private String queryType;
  /** 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件） */
  private String htmlType;
  /** 字典类型 */
  private String dictType;
  /** 排序 */
  private Integer sort;
  /** 默认值*/
  private String columnDefault;
  public Long getPkColumn() {
    return pkColumn;
  }
  public void setPkColumn(Long pkColumn) {
    this.pkColumn = pkColumn;
  }
  public Long getPkTable() {
    return pkTable;
  }
  public void setPkTable(Long pkTable) {
    this.pkTable = pkTable;
  }
  public Long getTableCode() {
    return tableCode;
  }
  public void setTableCode(Long tableCode) {
    this.tableCode = tableCode;
  }
  public String getColumnName() {
    return columnName;
  }
  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }
  public String getColumnComment() {
    return columnComment;
  }
  public void setColumnComment(String columnComment) {
    this.columnComment = columnComment;
  }
  public String getColumnType() {
    return columnType;
  }
  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }
  public String getJavaType() {
    return javaType;
  }
  public void setJavaType(String javaType) {
    this.javaType = javaType;
  }
  public String getJavaTypePackage() {
    return javaTypePackage;
  }
  public void setJavaTypePackage(String javaTypePackage) {
    this.javaTypePackage = javaTypePackage;
  }
  public String getJavaField() {
    return javaField;
  }
  public void setJavaField(String javaField) {
    this.javaField = javaField;
  }
  public String getIsPk() {
    return isPk;
  }
  public void setIsPk(String isPk) {
    this.isPk = isPk;
  }
  public String getIsIncrement() {
    return isIncrement;
  }
  public void setIsIncrement(String isIncrement) {
    this.isIncrement = isIncrement;
  }
  public String getIsRequired() {
    return isRequired;
  }
  public void setIsRequired(String isRequired) {
    this.isRequired = isRequired;
  }
  public String getIsInsert() {
    return isInsert;
  }
  public void setIsInsert(String isInsert) {
    this.isInsert = isInsert;
  }
  public String getIsEdit() {
    return isEdit;
  }
  public void setIsEdit(String isEdit) {
    this.isEdit = isEdit;
  }
  public String getIsList() {
    return isList;
  }
  public void setIsList(String isList) {
    this.isList = isList;
  }
  public String getIsQuery() {
    return isQuery;
  }
  public void setIsQuery(String isQuery) {
    this.isQuery = isQuery;
  }
  public String getQueryType() {
    return queryType;
  }
  public void setQueryType(String queryType) {
    this.queryType = queryType;
  }
  public String getHtmlType() {
    return htmlType;
  }
  public void setHtmlType(String htmlType) {
    this.htmlType = htmlType;
  }
  public String getDictType() {
    return dictType;
  }
  public void setDictType(String dictType) {
    this.dictType = dictType;
  }
  public Integer getSort() {
    return sort;
  }
  public void setSort(Integer sort) {
    this.sort = sort;
  }
  public String getColumnDefault() {
    return columnDefault;
  }
  public void setColumnDefault(String columnDefault) {
    this.columnDefault = columnDefault;
  }
  
}
