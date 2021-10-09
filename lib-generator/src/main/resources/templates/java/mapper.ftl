package ${packagePatch}.${moduleCode}.${functionCode}.mapper;

import java.util.List;

import ${packagePatch}.${moduleCode}.${functionCode}.domain.${javaClass};

/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
public interface ${javaClass}Mapper {
	/**
   * 查询${functionName}
   * @return ${functionName}结果集
   */
  public List<${javaClass}> query${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
  <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 查询${functionName}
   * @return ${functionName}结果集
   */
  public ${javaClass} query${javaClass}ByPK(${colum.javaType} ${colum.javaField});
	</#if>
	</#list>
  
  
  
}