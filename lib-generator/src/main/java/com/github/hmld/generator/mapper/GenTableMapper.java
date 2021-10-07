package com.github.hmld.generator.mapper;

import java.util.List;

import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.domain.GenTableColumn;

public interface GenTableMapper {
  /**
   * 查询数据库中的模式（命名空间）
   * @return 命名空间结果集
   */
  public List<GenTable> queryTableNameSpaceList();

  /**
   * 查询数据库中的表信息
   * 
   * @param genTable
   * @return 表信息结果集
   */
  public List<GenTable> queryGenTableList(GenTable genTable);

  /**
   * 通过表名称查询
   * 
   * @param genTable
   * @return 对应表数据
   */
  public GenTable queryGenTableByTableName(GenTable genTable);

  /**
   * 查询数据库中对应的表的字段信息
   * 
   * @param genTable
   * @return 字段信息结果集
   */
  public List<GenTableColumn> qyeryGenTableColumnList(GenTable genTable);
  /**
   * 通过主键查询表信息
   * @param pkTable
   * @return
   */
  public GenTable queryGenTableByPk(Long pkTable);
  /**
   * 通过表主键查询表字段信息
   * @param pkTable
   * @return
   */
  public List<GenTableColumn> qyeryGenTableColumnListByPk(Long pkTable);
  /**
   * 获取表字段需要的包
   * @param pkTable
   * @return
   */
  public List<String> qyeryGenTableColumnPackagesPk(Long pkTable);
  /**
   * 保存表内容
   * 
   * @param genTable
   * @return
   */
  public int insertGenTable(GenTable genTable);
  /**
   * 修改表内容
   * @param genTable
   * @return
   */
  public int updateGenTable(GenTable genTable);
  /**
   * 删除表信息
   * @param pkTable
   * @return
   */
  public int deleteGenTable(Long pkTable);
  
  /**
   * 保存字段信息
   * @param genTableColumn
   * @return
   */
  public int insertGenTableColumn(GenTableColumn genTableColumn);
  /**
   * 删除字段信息
   * @param pkTable
   * @return
   */
  public int deleteGenTableColumn(Long pkTable);
}
