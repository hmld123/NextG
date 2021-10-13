package ${packagePatch}.${moduleCode}.${functionCode}.service;

import java.util.List;
import ${packagePatch}.${moduleCode}.${functionCode}.domain.${javaClass};

/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
public interface I${javaClass}Service {
  /**
   * 查询${functionName}
   * @param ${javaClass ? uncap_first}
   * @return ${functionName}结果集
   */
  public List<${javaClass}> query${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
  <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 通过主键查询${functionName}
   * @param ${colum.javaField} 主键
   * @return ${functionName} 结果集
   */
  public ${javaClass} query${javaClass}ByPK(${colum.javaType} ${colum.javaField});
	</#if>
	</#list>
  
	/**
   * 保存${functionName}
   * @param ${javaClass ? uncap_first} 主键
   * @return 结果
   */
  public int insert${javaClass}(${javaClass} ${javaClass ? uncap_first});
  
	/**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  public int update${javaClass}(${javaClass} ${javaClass ? uncap_first});
  
    <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 删除${functionName}
   * @param ${colum.javaField} 主键
   * @return 结果
   */
  public int delete${javaClass}ByPK(${colum.javaType} ${colum.javaField});
  
	/**
   * 删除${functionName}
   * @param ${colum.javaField}s 主键数组
   * @return 结果
   */
  public int delete${javaClass}ByPKS(${colum.javaType}[] ${colum.javaField}s);
	</#if>
	</#list>
}