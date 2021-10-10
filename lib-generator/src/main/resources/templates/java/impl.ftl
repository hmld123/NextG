package ${packagePatch}.${moduleCode}.${functionCode}.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ${packagePatch}.${moduleCode}.${functionCode}.domain.${javaClass};
import ${packagePatch}.${moduleCode}.${functionCode}.service.I${javaClass}Service;
import ${packagePatch}.${moduleCode}.${functionCode}.mapper.${javaClass}Mapper;

/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
@Service
public class ${javaClass}ServiceImpl implements I${javaClass}Service {
	@Autowired
	private ${javaClass}Mapper ${javaClass ? uncap_first}Mapper;
	
	/**
	 * 查询${functionName}
	 * @param ${javaClass ? uncap_first}
	 * @return ${functionName}结果集
	 */
	@Override
  public List<${javaClass}> query${javaClass}List(${javaClass} ${javaClass ? uncap_first}) {
  	return ${javaClass ? uncap_first}Mapper.List<${javaClass}> query${javaClass}List(${javaClass ? uncap_first});
  }
	
  <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 通过主键查询${functionName}
   * @param ${colum.javaField} 主键
   * @return ${functionName} 结果集
   */
	@Override
  public ${javaClass} query${javaClass}ByPK(${colum.javaType} ${colum.javaField}) {
  	return ${javaClass ? uncap_first}Mapper.query${javaClass}ByPK(${colum.javaField});
  }
	</#if>
	</#list>
  
	/**
   * 保存${functionName}
   * @param ${javaClass ? uncap_first} 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int insert${javaClass}List(${javaClass} ${javaClass ? uncap_first}) {
  	return ${javaClass ? uncap_first}Mapper.insert${javaClass}List(${javaClass ? uncap_first});
  }
  
	/**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
	@Override
	@Transactional
  public int update${javaClass}List(${javaClass} ${javaClass ? uncap_first}) {
  	return ${javaClass ? uncap_first}Mapper.update${javaClass}List(${javaClass ? uncap_first});
  }
  
	<#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 删除${functionName}
   * @param ${colum.javaField} 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int delete${javaClass}ByPK(${colum.javaType} ${colum.javaField}) {
  	return ${javaClass ? uncap_first}Mapper.delete${javaClass}ByPK(${colum.javaField});
  }
  
	/**
   * 删除${functionName}
   * @param ${colum.javaField}s 主键数组
   * @return 结果
   */
	@Override
	@Transactional
  public int delete${javaClass}ByPKS(${colum.javaType}[] ${colum.javaField}s) {
  	return ${javaClass ? uncap_first}Mapper.delete${javaClass}ByPKS(${colum.javaField}s);
  }
	</#if>
	</#list>
}