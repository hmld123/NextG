package com.github.hmld.generator.contraller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.constant.GenConstants;
import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.common.logger.OperationType;
import com.github.hmld.common.logger.OperationUnit;
import com.github.hmld.generator.domain.GenTable;
import com.github.hmld.generator.service.IGenTableService;
import com.github.hmld.properties.Nex_G_Propertie;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/postgres/gen")
public class GenTableController {
  @Autowired
  private IGenTableService genTableService;
  @Autowired
  private Nex_G_Propertie sysprofile;
  /**
   * 获取数据库中的模式（命名空间）
   * @return 查询结果
   */
  @Log(level = 0,detail = "querySchemas()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
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
  @Log(level = 0,detail = "getTables()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/tables")
  @ResponseBody
  public AjaxResult getTables(@RequestBody GenTable genTable) {
  	return AjaxResult.success(genTableService.queryGenTableList(genTable));
  }
  
  /**
   * 获取要生成的表数据
   * @param genTable
   * @return 查询结果
   */
  @Log(level = 0,detail = "genTable()",operationType = OperationType.UNKNOWN,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/table")
  @ResponseBody
  @CrossOrigin
  public AjaxResult genTable(GenTable genTable) {
  	return AjaxResult.success(genTableService.queryGenTableByTableName(genTable));
  }
  /**
   * 保存表信息
   * @param genTable
   * @return
   */
  @Log(level = 0,detail = "saveTable()",operationType = OperationType.INSERT,operationUnit = OperationUnit.DATABASE)
  @PostMapping("/table")
  @ResponseBody
  @CrossOrigin
  public AjaxResult saveTable(@RequestBody GenTable genTable) {
    return AjaxResult.success(genTableService.insertGenTable(genTable));
  }
  /**
   * 修改表信息
   * @param genTable
   * @return
   */
  @Log(level = 0,detail = "updateTable()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
  @PutMapping("/table")
  @ResponseBody
  @CrossOrigin
  public AjaxResult updateTable(@RequestBody GenTable genTable) {
    return AjaxResult.success(genTableService.updateGenTable(genTable));
  }
  /**
   * 删除表信息
   * @param pkTables
   * @return
   */
  @Log(level = 0,detail = "deleteTable()",operationType = OperationType.DELETE,operationUnit = OperationUnit.DATABASE)
  @DeleteMapping("/table/{pkTables}")
  @ResponseBody
  @CrossOrigin
  public AjaxResult deleteTable(@PathVariable Long[] pkTables) {
    return AjaxResult.success(genTableService.deleteGenTable(pkTables));
  }
  /**
   * 生产源码
   * @return
   * @throws IOException 
   * @throws TemplateException 
   */
  @Log(level = 0,detail = "genSource()",operationType = OperationType.UNKNOWN,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/{pkTables}")
  @ResponseBody
  @CrossOrigin
  public AjaxResult genSource(@PathVariable Long[] pkTables) throws IOException, TemplateException {
    String uploadFloder = sysprofile.getUpload();
    if (!sysprofile.getUpload().substring(sysprofile.getUpload().length()-1, sysprofile.getUpload().length()).equals("/")) {
      uploadFloder = uploadFloder + "/";
    }
    GenTable table = genTableService.queryGenTableByPk(pkTables[0]);
    
    uploadFloder = uploadFloder + table.getGenPatch()+ "/";
    File floder = new File(uploadFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    Configuration cof = new Configuration(Configuration.VERSION_2_3_31);
    cof.setDefaultEncoding("utf-8");
    cof.setClassForTemplateLoading(getClass(), "/");
    Template temp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/mapper.ftl");
    File outFile = new File(uploadFloder + table.getJavaClass() +".java");
    FileOutputStream outos = new FileOutputStream(outFile);
    OutputStreamWriter outw = new OutputStreamWriter(outos);
    Writer out = new BufferedWriter(outw);
    temp.process(table, out);
    out.flush();
    outw.flush();
    outos.flush();
    out.close();
    outw.close();
    outos.close();
    return AjaxResult.success("isfile:"+outFile.isFile()+",path:"+outFile.getPath());
  }
  
}
