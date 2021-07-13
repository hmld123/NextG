package com.github.hmld.generator.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.common.constant.GenConstants;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.domain.GenTableColumn;
import com.github.hmld.generator.mapper.GenTableMapper;
import com.github.hmld.generator.service.IGenTableService;
@Service
public class GenTableServiceImpl implements IGenTableService {
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
    return genTableMapper.queryGenTableList(genTable);
  }
  
  /**
   * 查询数据库中对应的表的字段信息
   * @param genTable
   * @return 字段信息结果集
   */
  public List<GenTableColumn> qyeryGenTableColumnList(GenTable genTable){
    GenTable table = genTableMapper.queryGenTableByTableName(genTable);
    Short[] pkIndex = table.getPknums();
    List<GenTableColumn> columnList = genTableMapper.qyeryGenTableColumnList(genTable);
    for (GenTableColumn column : columnList) {
      column.setTableCode(table.getTableCode());
      column.setIsRequired(toValue(column.getIsRequired()));
      column.setJavaField(StringUtils.toCamelCase(column.getColumnName()));
      if (arraysContains(GenConstants.COLUMNTYPE_STR, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_STRING);
      }else if (arraysContains(GenConstants.COLUMNTYPE_BOOLEAN, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_BOOLEAN);
      }else if (arraysContains(GenConstants.COLUMNTYPE_FLOAT, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_FLOAT);
      }else if (arraysContains(GenConstants.COLUMNTYPE_DOUBLE, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_DOUBLE);
      }else if (arraysContains(GenConstants.COLUMNTYPE_INTEGER, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_INTEGER);
      }else if (arraysContains(GenConstants.COLUMNTYPE_LONG, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_LONG);
      }else if (arraysContains(GenConstants.COLUMNTYPE_BIGDECIMAL, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_BIGDECIMAL[0]);
        column.setJavaTypePackage(GenConstants.TYPE_BIGDECIMAL[1]);
      }else if (arraysContains(GenConstants.COLUMNTYPE_DATE, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_DATE[0]);
        column.setJavaTypePackage(GenConstants.TYPE_DATE[1]);
      }else if (arraysContains(GenConstants.COLUMNTYPE_TIME, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_TIME[0]);
        column.setJavaTypePackage(GenConstants.TYPE_TIME[1]);
      }else if (arraysContains(GenConstants.COLUMNTYPE_TIMESTAMP, column.getColumnType())) {
        column.setJavaType(GenConstants.TYPE_TIMESTAMP[0]);
        column.setJavaTypePackage(GenConstants.TYPE_TIMESTAMP[1]);
      }
      setPk(column, pkIndex);
      setIncrement(column);
    }
    return columnList;
  }
  
  /** 值转换*/
  private static String toValue(String targetValue){
    String value = "0";
    if (targetValue.equals("NO")) {
      value = "1";
    }
    else if (targetValue.equals("YES")) {
      value = "0";
    }
    return value;
  }
  
  /**
   * 判断是否主键
   * @param column
   * @param pkIndex
   */
  private static void setPk(GenTableColumn column ,Short[] pkIndex) {
    for (int i = 0; i < pkIndex.length; i++) {
      //判断是否主键
      if (column.getSort().equals(Integer.parseInt(pkIndex[i].toString()))) {
        column.setIsPk("1");
      }
    }
    if (column.getIsPk()==null) {
      column.setIsPk("0");
    }
  }
  
  /**
   * 判断是否自增长
   * @param column
   */
  private static void setIncrement(GenTableColumn column){
    if (column.getColumnDefault()!=null ) {
      String rex = "^nextval(.*$)";
      Pattern r = Pattern.compile(rex );
      Matcher matcher = r.matcher(column.getColumnDefault());
      if (matcher.find()) {
        column.setIsIncrement("1");
      }else {
        column.setIsIncrement("0");
      }
    }else {
      column.setIsIncrement("0");
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
