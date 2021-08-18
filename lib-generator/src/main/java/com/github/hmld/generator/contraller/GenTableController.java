package com.github.hmld.generator.contraller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.domain.GenTableColumn;
import com.github.hmld.generator.service.IGenTableService;

@Controller
@RequestMapping("/gen")
public class GenTableController {
  @Autowired
  private IGenTableService genTableService;
  
  @Log(level = 0,detail = "queryGenTableList()")
  @GetMapping("/genTable")
  @ResponseBody
  public AjaxResult queryGenTableList() {
    GenTable genTable = new GenTable();
    genTable.setTableSchema("public");
    genTable.setTableName("sys_user");
    List<GenTableColumn> getTableList = genTableService.qyeryGenTableColumnList(genTable);
    return AjaxResult.success(getTableList);
  }
}
