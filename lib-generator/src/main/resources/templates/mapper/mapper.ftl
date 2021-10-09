<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packagePatch}.${moduleCode}.${functionCode}.mapper.${javaClass}Mapper">
	<!-- ${functionName} -->
	<resultMap type="${javaClass}" id="${javaClass ? uncap_first}Result">
	<#list columnList as colum>
	<!-- ${colum.columnComment} -->
	<#if colum.isPk == '1'>
	<id property="${colum.javaField}" column="${colum.columnName}" />
	<#else>
	<result property="${colum.javaField}" column="${colum.columnName}" />
	</#if>
	</#list>
	</resultMap>
	
	<sql id="select${javaClass}Vo">
		select <#list columnList as colum> ${colum.columnName} <#if colum_has_next>,</#if></#list> from ${tableName}
	</sql>
	
	<select id="query${javaClass}List" parameterType="${javaClass}" resultMap="${javaClass ? uncap_first}Result">
		<include refid="select${javaClass}Vo"/>
		<where>
		<#list columnList as colum>  
			<if test="${colum.javaField} != null <#if colum.javaType =='String'>and configName != ''</#if>">
				AND ${colum.columnName} <#if colum.queryType =='EQ'> = ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='NE'>!= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='GT'>&gt;= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='LT'>&lt;= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='LIKE'>like concat('%', ${r"#{"}${colum.javaField}}, '%')<#elseif colum.queryType =='BETWEEN'>&gt;= ${r"#{"}${colum.javaField}} and &lt;= ${r"#{"}${colum.javaField}}</#if>
			</if>
		</#list>
		</where>
    </select>
	
	<#list columnList as colum>
	<#if colum.isPk == '1'>
	<select id="query${javaClass}ByPK" parameterType="${colum.javaType}" resultMap="${javaClass ? uncap_first}Result">
		<include refid="select${javaClass}Vo"/>
		where ${colum.columnName} = ${r"#{"}${colum.javaField}}
    </select>
	</#if>
	</#list>
	
	
</mapper>












package ${packagePatch}.${moduleCode}.${functionCode}.mapper;

import ${packagePatch}.${moduleCode}.${functionCode}.domain.${javaClass};

/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
public interface ${javaClass}Mapper {
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
  public int insert${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    /**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  public int update${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 删除${functionName}
   * @param ${colum.javaField} 主键
   * @return 结果package ${packagePatch}.${moduleCode}.${functionCode}.mapper;

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
  public int insert${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    /**
   * 修改${functionName}package ${packagePatch}.${moduleCode}.${functionCode}.mapper;

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
  public int insert${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    /**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  public int update${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    <#list columnList as colum>
	<#if colum.isPk == '1'>
	/**
   * 删除${functionName}
   * @param ${colum.javaField} 主键
   * @return 结果package ${packagePatch}.${moduleCode}.${functionCode}.mapper;

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
  public int insert${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
    /**
   * 修改${functionName}
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  public int update${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
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
   * @param ${javaClass ? uncap_first}
   * @return 结果
   */
  public int update${javaClass}List(${javaClass} ${javaClass ? uncap_first});
  
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