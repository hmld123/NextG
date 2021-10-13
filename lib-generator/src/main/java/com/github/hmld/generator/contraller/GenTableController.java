package com.github.hmld.generator.contraller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
  public AjaxResult genSource(@PathVariable Long[] pkTables) throws IOException, TemplateException {
    String uploadFloder = sysprofile.getUpload();
    if (!sysprofile.getUpload().substring(sysprofile.getUpload().length()-1, sysprofile.getUpload().length()).equals("/")) {
      uploadFloder = uploadFloder + "/";
    }
    GenTable table = genTableService.queryGenTableByPk(pkTables[0]);
    
    Configuration cof = new Configuration(Configuration.VERSION_2_3_31);
    cof.setDefaultEncoding("utf-8");
    cof.setClassForTemplateLoading(getClass(), "/");
    
    String domainFloder = uploadFloder +"src/main/java/"+ table.getGenPatch() + "/domain/";
    File floder = new File(domainFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    String mapperFloder = uploadFloder +"src/main/java/"+ table.getGenPatch() + "/mapper/";
    floder = new File(mapperFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    String serviceFloder = uploadFloder +"src/main/java/"+ table.getGenPatch() + "/service/";
    floder = new File(serviceFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    String implFloder = uploadFloder +"src/main/java/"+ table.getGenPatch() + "/service/impl/";
    floder = new File(implFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    String contrallerFloder = uploadFloder +"src/main/java/"+ table.getGenPatch() + "/contraller/";
    floder = new File(contrallerFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    String mapperXmlFloder = uploadFloder +"src/main/resources/mybatis/mapper/" + table.getModuleCode() + "/" + table.getFunctionCode() + "/";
    floder = new File(mapperXmlFloder);
    if (!floder.exists()) { floder.mkdirs(); }
    
    Template domainTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/domain.ftl");
    File domainOutFile = new File(domainFloder + table.getJavaClass() +".java");
    FileOutputStream domainoutos = new FileOutputStream(domainOutFile);
    OutputStreamWriter domainoutw = new OutputStreamWriter(domainoutos);
    Writer domainout = new BufferedWriter(domainoutw);
    domainTemp.process(table, domainout);
    domainout.flush();
    domainoutw.flush();
    domainoutos.flush();
    domainout.close();
    domainoutw.close();
    domainoutos.close();
    
    Template mapperTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/mapper.ftl");
    File mapperOutFile = new File(mapperFloder + table.getJavaClass() +"Mapper.java");
    FileOutputStream mapperoutos = new FileOutputStream(mapperOutFile);
    OutputStreamWriter mapperoutw = new OutputStreamWriter(mapperoutos);
    Writer mapperout = new BufferedWriter(mapperoutw);
    mapperTemp.process(table, mapperout);
    mapperout.flush();
    mapperoutw.flush();
    mapperoutos.flush();
    mapperout.close();
    mapperoutw.close();
    mapperoutos.close();
    
    Template serverTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/server.ftl");
    File serverOutFile = new File(serviceFloder + "I" + table.getJavaClass() +"Service.java");
    FileOutputStream serveroutos = new FileOutputStream(serverOutFile);
    OutputStreamWriter serveroutw = new OutputStreamWriter(serveroutos);
    Writer serverout = new BufferedWriter(serveroutw);
    serverTemp.process(table, serverout);
    serverout.flush();
    serveroutw.flush();
    serveroutos.flush();
    serverout.close();
    serveroutw.close();
    serveroutos.close();
    
    Template implTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/impl.ftl");
    File implOutFile = new File(implFloder + table.getJavaClass() +"ServiceImpl.java");
    FileOutputStream imploutos = new FileOutputStream(implOutFile);
    OutputStreamWriter imploutw = new OutputStreamWriter(imploutos);
    Writer implout = new BufferedWriter(imploutw);
    implTemp.process(table, implout);
    implout.flush();
    imploutw.flush();
    imploutos.flush();
    implout.close();
    imploutw.close();
    imploutos.close();
    
    Template controllerTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"java/controller.ftl");
    File controllerOutFile = new File(contrallerFloder + table.getJavaClass() +"Controller.java");
    FileOutputStream controlleroutos = new FileOutputStream(controllerOutFile);
    OutputStreamWriter controlleroutw = new OutputStreamWriter(controlleroutos);
    Writer controllerout = new BufferedWriter(controlleroutw);
    controllerTemp.process(table, controllerout);
    controllerout.flush();
    controlleroutw.flush();
    controlleroutos.flush();
    controllerout.close();
    controlleroutw.close();
    controlleroutos.close();

    Template mapperXmlTemp = cof.getTemplate(GenConstants.TEMP_FLODER+"mapper/mapper.ftl");
    File mapperXmlOutFile = new File(mapperXmlFloder + table.getJavaClass() +"Mapper.xml");
    FileOutputStream mapperXmloutos = new FileOutputStream(mapperXmlOutFile);
    OutputStreamWriter mapperXmloutw = new OutputStreamWriter(mapperXmloutos);
    Writer mapperXmlout = new BufferedWriter(mapperXmloutw);
    mapperXmlTemp.process(table, mapperXmlout);
    mapperXmlout.flush();
    mapperXmloutw.flush();
    mapperXmloutos.flush();
    mapperXmlout.close();
    mapperXmloutw.close();
    mapperXmloutos.close();
    
    return AjaxResult.success();
  }
  
}
