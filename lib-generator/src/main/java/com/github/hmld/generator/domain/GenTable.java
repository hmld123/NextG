package com.github.hmld.generator.domain;
import java.util.List;
/**
 * 业务表
 * @author hmld
 *
 */
public class GenTable {
  /** 表对应的主键*/
  private Long pkTable;
  /** 父表主键*/
  private Long parentPkTable;
  /** 表对应的主键*/
  private Long tableCode;
  /** 表对应的数据库名称*/
  private String tableCatalog;
  /** 表对应的模式名称*/
  private String tableSchema;
  /** 表名称*/
  private String tableName;
  /** 表注释*/
  private String tableComment;
  /** 表类型*/
  private String tableType;
  /** 类名称*/
  private String javaClass;
  /** 主键字段的所在列*/
  private Short[] pknums;
  /** 包路径*/
  private String packagePatch;
  /** 模块编码*/
  private String moduleCode;
  /** 功能编码*/
  private String functionCode;
  /** 功能名*/
  private String functionName;
  /** 功能作者*/
  private String functionAuthor;
  /** 生成路径*/
  private String genPatch;
  /** 表字段*/
  private List<GenTableColumn> columnList;
  /** 子表*/
  private List<GenTable> childTables;
  /** 表字段包*/
  private List<String> columPackages;
  public Long getPkTable() {
    return pkTable;
  }
  public void setPkTable(Long pkTable) {
    this.pkTable = pkTable;
  }
  public Long getParentPkTable() {
    return parentPkTable;
  }
  public void setParentPkTable(Long parentPkTable) {
    this.parentPkTable = parentPkTable;
  }
  public Long getTableCode() {
    return tableCode;
  }
  public void setTableCode(Long tableCode) {
    this.tableCode = tableCode;
  }
  public String getTableCatalog() {
    return tableCatalog;
  }
  public void setTableCatalog(String tableCatalog) {
    this.tableCatalog = tableCatalog;
  }
  public String getTableSchema() {
    return tableSchema;
  }
  public void setTableSchema(String tableSchema) {
    this.tableSchema = tableSchema;
  }
  public String getTableName() {
    return tableName;
  }
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  public String getTableComment() {
    return tableComment;
  }
  public void setTableComment(String tableComment) {
    this.tableComment = tableComment;
  }
  public String getTableType() {
    return tableType;
  }
  public void setTableType(String tableType) {
    this.tableType = tableType;
  }
  public String getJavaClass() {
    return javaClass;
  }
  public void setJavaClass(String javaClass) {
    this.javaClass = javaClass;
  }
  public Short[] getPknums() {
    return pknums;
  }
  public void setPknums(Short[] pknums) {
    this.pknums = pknums;
  }
  public String getPackagePatch() {
    return packagePatch;
  }
  public void setPackagePatch(String packagePatch) {
    this.packagePatch = packagePatch;
  }
  public String getModuleCode() {
    return moduleCode;
  }
  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }
  public String getFunctionCode() {
    return functionCode;
  }
  public void setFunctionCode(String functionCode) {
    this.functionCode = functionCode;
  }
  public String getFunctionName() {
    return functionName;
  }
  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }
  public String getFunctionAuthor() {
    return functionAuthor;
  }
  public void setFunctionAuthor(String functionAuthor) {
    this.functionAuthor = functionAuthor;
  }
  public String getGenPatch() {
    return genPatch;
  }
  public void setGenPatch(String genPatch) {
    this.genPatch = genPatch;
  }
  public List<GenTableColumn> getColumnList() {
    return columnList;
  }
  public void setColumnList(List<GenTableColumn> columnList) {
    this.columnList = columnList;
  }
  public List<GenTable> getChildTables() {
    return childTables;
  }
  public void setChildTables(List<GenTable> childTables) {
    this.childTables = childTables;
  }
  public List<String> getColumPackages() {
    return columPackages;
  }
  public void setColumPackages(List<String> columPackages) {
    this.columPackages = columPackages;
  }

}
