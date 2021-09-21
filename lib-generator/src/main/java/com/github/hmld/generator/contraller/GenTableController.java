package com.github.hmld.generator.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.service.IGenTableService;

@Controller
@RequestMapping("/gen")
public class GenTableController {
  @Autowired
  private IGenTableService genTableService;
  
  /**
   * 获取数据库中的模式（命名空间）
   * @return 查询结果
   */
  @Log(level = 0,detail = "querySchemas()")
  @GetMapping("/schemas")
  @ResponseBody
  public AjaxResult querySchemas() {
  	return AjaxResult.success(genTableService.queryTableNameSpaceList());
  }
  
  /**
   * 获取数据库中的信息
   * @param genTable
   * @return 查询结果
   */
  @Log(level = 0,detail = "getTables()")
  @GetMapping("/tables")
  @ResponseBody
  public AjaxResult getTables(GenTable genTable) {
  	return AjaxResult.success(genTableService.queryGenTableList(genTable));
  }
  
  /**
   * 获取要生成的表数据
   * @param genTable
   * @return 查询结果
   */
  @Log(level = 0,detail = "genTable()")
  @PostMapping("/table")
  @ResponseBody
  @CrossOrigin
  public AjaxResult genTable(@RequestBody GenTable genTable) {
  	return AjaxResult.success(genTableService.queryGenTableByTableName(genTable));
  }
  
}
