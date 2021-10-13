package ${packagePatch}.${moduleCode}.${functionCode}.contraller;

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

import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.common.logger.OperationType;
import com.github.hmld.common.logger.OperationUnit;

import ${packagePatch}.${moduleCode}.${functionCode}.domain.${javaClass};
import ${packagePatch}.${moduleCode}.${functionCode}.service.I${javaClass}Service;
/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
 @Controller
 @RequestMapping("/${moduleCode}/${functionCode}")
 public class ${javaClass}Controller {
 	
 	@Autowired
 	private I${javaClass}Service ${javaClass ? uncap_first}Service;
 	
	/**
   * 查询${functionName}
   * @param ${javaClass ? uncap_first}
   * @return ${functionName}结果集
   */
  @Log(level = 0,detail = "get${javaClass}s()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/${javaClass ? uncap_first}s")
  @ResponseBody
  public AjaxResult get${javaClass}s(@RequestBody ${javaClass} ${javaClass ? uncap_first}) {
  	return AjaxResult.success(${javaClass ? uncap_first}Service.query${javaClass}List(${javaClass ? uncap_first}));
  }
  
  <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 通过主键查询${functionName}
   * @param ${colum.javaField} 主键
   * @return ${functionName} 结果集
   */
  @Log(level = 0,detail = "get${javaClass}()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/${javaClass ? uncap_first}/${r"{"}${colum.javaField}}")
  @ResponseBody
  public AjaxResult get${javaClass}(@PathVariable ${colum.javaType} ${colum.javaField}) {
  	return AjaxResult.success(${javaClass ? uncap_first}Service.query${javaClass}ByPK(${colum.javaField}));
  }
	</#if>
	</#list>
  
	/**
   * 保存${functionName}
   * @param ${javaClass ? uncap_first} 主键
   * @return 结果
   */
  @Log(level = 0,detail = "save${javaClass}()",operationType = OperationType.INSERT,operationUnit = OperationUnit.DATABASE)
  @PostMapping("/${javaClass ? uncap_first}")
  @ResponseBody
  public AjaxResult save${javaClass}(@RequestBody ${javaClass} ${javaClass ? uncap_first}){
  	return AjaxResult.success(${javaClass ? uncap_first}Service.insert${javaClass}(${javaClass ? uncap_first}));
  }
  
	/**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  @Log(level = 0,detail = "update${javaClass}()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
  @PutMapping("/${javaClass ? uncap_first}")
  @ResponseBody
  public AjaxResult update${javaClass}(@RequestBody ${javaClass} ${javaClass ? uncap_first}){
  	return AjaxResult.success(${javaClass ? uncap_first}Service.update${javaClass}(${javaClass ? uncap_first}));
  }
	<#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 删除${functionName}
   * @param ${colum.javaField}s 主键数组
   * @return 结果
   */
  @Log(level = 0,detail = "delete${javaClass}()",operationType = OperationType.DELETE,operationUnit = OperationUnit.DATABASE)
  @DeleteMapping("/${javaClass ? uncap_first}/${r"{"}${colum.javaField}s}")
  @ResponseBody
  public AjaxResult delete${javaClass}(@PathVariable ${colum.javaType}[] ${colum.javaField}s) {
  	return AjaxResult.success(${javaClass ? uncap_first}Service.delete${javaClass}ByPKS(${colum.javaField}s));
  }
	</#if>
	</#list>
 }