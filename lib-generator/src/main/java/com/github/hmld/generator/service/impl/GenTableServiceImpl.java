package com.github.hmld.generator.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.hmld.common.constant.GenConstants;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.domain.GenTableColumn;
import com.github.hmld.generator.mapper.GenTableMapper;
import com.github.hmld.generator.service.IGenTableService;
import com.github.hmld.properties.Nex_G_Propertie;
@Service
public class GenTableServiceImpl implements IGenTableService {
	@Autowired
  private Nex_G_Propertie sysprofile;
  @Autowired
  private GenTableMapper genTableMapper;
  /**
   * 查询数据库中的模式（命名空间）
   * @return 命名空间结果集
   */
  public List<GenTable> queryTableNameSpaceList(){
    return genTableMapper.queryTableNameSpaceList();
  }
  /**
   * 查询数据库中的表信息
   * @param genTable
   * @return 表信息结果集
   */
  public List<GenTable> queryGenTableList(GenTable genTable){
    List<GenTable> tabList = genTableMapper.queryGenTableList(genTable);
    for (GenTable table : tabList) {
      table.setJavaClass(StringUtils.toCamelCase(table.getTableName()));
    }
    return tabList;
  }
  
  /**
   * 查询数据库中对应的表的字段信息
   * @param genTable
   * @return 字段信息结果集
   */
  public List<GenTableColumn> qyeryGenTableColumnList(GenTable genTable){
    GenTable table = genTableMapper.queryGenTableByTableName(genTable);
    List<GenTableColumn> columnList = genTableMapper.qyeryGenTableColumnList(genTable);
    return initColumnField(columnList, table);
  }
  
  /**
   * 查询数据库中对应的表的信息
   * @param genTable
   * @return 表信息结果集
   */
  public GenTable queryGenTableByTableName(GenTable genTable){
    GenTable table = genTableMapper.queryGenTableByTableName(genTable);
    initTbale(table);
    StringBuilder tableSB = new StringBuilder();
    String[] tableItems = genTable.getTableName().split("_");
    for (String tableItem : tableItems) {
      tableSB.append(tableItem.substring(0, 1).toUpperCase()+tableItem.substring(1, tableItem.length()));
    }
    table.setJavaClass(tableSB.toString());
    List<GenTableColumn> cols = genTableMapper.qyeryGenTableColumnList(genTable);
    List<GenTableColumn> list = initColumnField(cols, table);
    table.setColumnList(list);
    return table;
  }
  /**
   * 通过主键查询表信息
   * @param pkTable
   * @return
   */
  public GenTable queryGenTableByPk(Long pkTable) {
    GenTable table = genTableMapper.queryGenTableByPk(pkTable);
    List<String> columPackages = genTableMapper.qyeryGenTableColumnPackagesPk(pkTable);
    List<GenTableColumn> tableColums = genTableMapper.qyeryGenTableColumnListByPk(pkTable);
    if (columPackages!=null) {
      table.setColumPackages(columPackages);
    }
    if (tableColums!=null) {
      table.setColumnList(tableColums);
    }
    return table;
  }
  /**
   * 保存表内容
   * 
   * @param genTable
   * @return
   */
  @Transactional
  public int insertGenTable(GenTable genTable) {
    int insertRow = genTableMapper.insertGenTable(genTable);
    List<GenTableColumn> colums = genTable.getColumnList();
    for (GenTableColumn genTableColumn : colums) {
      genTableColumn.setPkTable(genTable.getPkTable());
      genTableMapper.insertGenTableColumn(genTableColumn);
    }
    return insertRow;
  }
  
  /**
   * 修改表内容
   * @param genTable
   * @return
   */
  @Transactional
  public int updateGenTable(GenTable genTable) {
  	initTbale(genTable);
    genTableMapper.deleteGenTableColumn(genTable.getPkTable());
    int updaterow = genTableMapper.updateGenTable(genTable);
    List<GenTableColumn> list = genTable.getColumnList();
    for (GenTableColumn genTableColumn : list) {
      genTableColumn.setPkTable(genTable.getPkTable());
      genTableMapper.insertGenTableColumn(genTableColumn);
    }
    return updaterow;
  }
  
  /**
   * 删除表信息
   * @param pkTable
   * @return
   */
  @Transactional
  public int deleteGenTable(Long[] pkTables) {
    int deleterow = 0;
    for (Long pkTable : pkTables) {
      genTableMapper.deleteGenTableColumn(pkTable);
      deleterow = deleterow + genTableMapper.deleteGenTable(pkTable);
    }
    return deleterow;
  }
  
  /**
   * 初始化table
   * @param table
   */
  private void initTbale(GenTable table) {
  	if (table.getPkTable()==null || table.getPkTable().equals(0L)) {
	  	table.setPackagePatch(sysprofile.getGenbasepack());
	  	table.setModuleCode("system");
	  	table.setFunctionCode("default");
	  	table.setFunctionName("default");
	  	table.setFunctionAuthor("system");
  	}
  	String genpath = sysprofile.getGenbasepack().replace(".", "/");
  	if (StringUtils.isNotEmpty(table.getModuleCode())) {
  		genpath = genpath + table.getModuleCode() + "/";
		}
  	if (StringUtils.isNotEmpty(table.getFunctionCode())) {
  		genpath = genpath + table.getFunctionCode() + "/";
		}
  	table.setGenPatch(genpath);
  }
  
  /**
   * 初始化字段
   * @param columnList
   * @param table
   * @return
   */
  private static List<GenTableColumn> initColumnField(List<GenTableColumn> columnList,GenTable table){
    Short[] pkIndex = table.getPknums();
    for (GenTableColumn column : columnList) {
      column.setTableCode(table.getTableCode());
      column.setIsRequired(toValue(column.getIsRequired()));
      column.setIsInsert(GenConstants.ENABLE);
      column.setJavaField(StringUtils.toCamelCase(column.getColumnName()));
      column.setQueryType("EQ");
      //校验字段类型
      if (arraysContains(GenConstants.COLUMNTYPE_STR, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_STRING);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_BOOLEAN, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_BOOLEAN);
      }else if (arraysContains(GenConstants.COLUMNTYPE_FLOAT, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_FLOAT);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_DOUBLE, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_DOUBLE);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_INTEGER, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_INTEGER);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_LONG, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_LONG);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_BIGDECIMAL, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_BIGDECIMAL[0]);
        column.setJavaTypePackage(GenConstants.TYPE_BIGDECIMAL[1]);
        column.setHtmlType(GenConstants.VIEW_INPUT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_DATE, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_DATE[0]);
        column.setJavaTypePackage(GenConstants.TYPE_DATE[1]);
        column.setHtmlType(GenConstants.VIEW_DATE);
      }else if (arraysContains(GenConstants.COLUMNTYPE_TIME, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_TIME[0]);
        column.setJavaTypePackage(GenConstants.TYPE_TIME[1]);
        column.setHtmlType(GenConstants.VIEW_TIME);
      }else if (arraysContains(GenConstants.COLUMNTYPE_TIMESTAMP, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_TIMESTAMP[0]);
        column.setJavaTypePackage(GenConstants.TYPE_TIMESTAMP[1]);
        column.setHtmlType(GenConstants.VIEW_DATE_TIME);
      }
      setPk(column, pkIndex);
      setIncrement(column);
      /** 判读是否可以修改*/
      if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, column.getColumnName()) && !column.getIsPk().equals(GenConstants.ENABLE)) {
        column.setIsEdit(GenConstants.ENABLE);
      }else {
        column.setIsEdit(GenConstants.DISABLE);
      }
      /** 判读是否需要页面查询*/
      if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, column.getColumnName()) && !column.getIsPk().equals(GenConstants.ENABLE)) {
        column.setIsQuery(GenConstants.ENABLE);
      }else {
        column.setIsQuery(GenConstants.DISABLE);
      }
      /** 判读是否需显示列表字段*/
      if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, column.getColumnName()) && !column.getIsPk().equals(GenConstants.ENABLE)) {
        column.setIsList(GenConstants.ENABLE);
      }else {
        column.setIsList(GenConstants.DISABLE);
      }
      /** 设置查询类型*/
      if (StringUtils.endsWithIgnoreCase(column.getColumnName(), "name"))
      {
          column.setQueryType(GenConstants.QUERY_LIKE);
      }
      /** 状态字段设置单选框*/
      else if (StringUtils.endsWithIgnoreCase(column.getColumnName(), "status")) {
        column.setHtmlType(GenConstants.VIEW_RADIO);
      }
      /** 状态字段设置单选框*/
      else if (StringUtils.endsWithIgnoreCase(column.getColumnName(), "type")
          || StringUtils.endsWithIgnoreCase(column.getColumnName(), "type")) {
        column.setHtmlType(GenConstants.VIEW_SELECT);
      }
      /** 文件字段设置文件上传控件*/
      else if (StringUtils.endsWithIgnoreCase(column.getColumnName(), "file")) {
        column.setHtmlType(GenConstants.VIEW_UPLOAD);
      }
      /** 内容字段设置富文本控件*/
      else if (StringUtils.endsWithIgnoreCase(column.getColumnName(), "content")) {
        column.setHtmlType(GenConstants.VIEW_EDITOR);
      }
    }
    return columnList;
  }
  
  /** 值转换*/
  private static String toValue(String targetValue){
    String value = GenConstants.DISABLE;
    if (targetValue.equals("NO")) {
      value = GenConstants.ENABLE;
    }
    else if (targetValue.equals("YES")) {
      value = GenConstants.DISABLE;
    }
    return value;
  }
  
  /**
   * 判断是否主键
   * @param column
   * @param pkIndex
   */
  private static void setPk(GenTableColumn column ,Short[] pkIndex) {
  	if (pkIndex!=null) {
  		for (int i = 0; i < pkIndex.length; i++) {
        //判断是否主键
        if (column.getSort().equals(Integer.parseInt(pkIndex[i].toString()))) {
          column.setIsPk(GenConstants.ENABLE);
          column.setIsQuery(GenConstants.DISABLE);
          column.setIsList(GenConstants.DISABLE);
        }
      }
      if (column.getIsPk()==null) {
        column.setIsPk(GenConstants.DISABLE);
      }
		}else {
			column.setIsPk(GenConstants.DISABLE);
		}
  }
  
  /**
   * 判断是否自增长
   * @param column
   */
  private static void setIncrement(GenTableColumn column){
    if (column.getColumnDefault()!=null ) {
      String rex = "^nextval(.*$)";
      Pattern r = Pattern.compile(rex);
      Matcher matcher = r.matcher(column.getColumnDefault());
      if (matcher.find()) {
        column.setIsIncrement(GenConstants.ENABLE);
      }else {
        column.setIsIncrement(GenConstants.DISABLE);
      }
    }else {
      column.setIsIncrement(GenConstants.DISABLE);
    }
  }
  
  /**
   * 校验数组十分包含指定值
   * @param arr 数组
   * @param targetValue 值
   * @return 是否包含
   */
  public static boolean arraysContains(String[] arr, String targetValue) {
    return Arrays.asList(arr).contains(targetValue);
  }
}
