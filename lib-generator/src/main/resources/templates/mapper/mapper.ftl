<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
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
	<!-- 查询${functionName} -->
	<select id="query${javaClass}List" parameterType="${javaClass}" resultMap="${javaClass ? uncap_first}Result">
		<include refid="select${javaClass}Vo"/>
		<where>
		<#list columnList as colum>  
			<if test="${colum.javaField} != null <#if colum.javaType =='String'>and ${colum.javaField} != ''</#if>">
				AND ${colum.columnName} <#if colum.queryType =='EQ'> = ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='NE'>!= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='GT'>&gt;= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='LT'>&lt;= ${r"#{"}${colum.javaField}}<#elseif colum.queryType =='LIKE'>like concat('%', ${r"#{"}${colum.javaField}}, '%')<#elseif colum.queryType =='BETWEEN'>&gt;= ${r"#{"}${colum.javaField}} and &lt;= ${r"#{"}${colum.javaField}}</#if>
			</if>
		</#list>
		</where>
    </select>
	<!-- 通过主键查询${functionName} -->
	<#list columnList as colum>
	<#if colum.isPk == '1'>
	<select id="query${javaClass}ByPK" parameterType="${colum.javaType}" resultMap="${javaClass ? uncap_first}Result">
		<include refid="select${javaClass}Vo"/>
		where ${colum.columnName} = ${r"#{"}${colum.javaField}}
    </select>
    </#if>
    </#list>
	<!-- 保存${functionName} -->
	<insert id="insert${javaClass}" parameterType="${javaClass}"<#list columnList as colum><#if colum.isIncrement = '1' && colum.isPk = '1'> useGeneratedKeys="true" keyProperty="${colum.javaField}"</#if></#list>>
		insert into ${tableName} 
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list columnList as colum>
			<#if colum.isIncrement != '1'>
			<if test="${colum.javaField} != null <#if colum.javaType == 'String'>and ${colum.javaField} != ''</#if> ">${colum.columnName},</if>
			</#if>
			</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
			<#list columnList as colum>
			<#if colum.isIncrement != '1'>
			<if test="${colum.javaField} != null <#if colum.javaType == 'String'>and ${colum.javaField} != ''</#if> ">${r"#{"}${colum.javaField}},</if>
			</#if>
			</#list>
		</trim>
	</insert>
	<!-- 修改${functionName} -->
	<update id="update${javaClass}" parameterType="${javaClass}">
		update ${tableName}
		<set>
			<#list columnList as colum>
			<#if colum.isPk != '1'>
			<if test="${colum.javaField} != null <#if colum.javaType == 'String'>and ${colum.javaField} != ''</#if> ">${colum.columnName} = ${r"#{"}${colum.javaField}},</if>
			</#if>
			</#list>
		</set>
		where <#list columnList as colum><#if colum.isPk == '1'> ${colum.columnName} = ${r"#{"}${colum.javaField}} </#if></#list>
	</update>
	<!-- 删除${functionName} -->
	<#list columnList as colum>
	<#if colum.isPk == '1'>
	<delete id="delete${javaClass}ByPK" parameterType="${colum.javaType}" >
		delete from ${tableName} where ${colum.columnName} = ${r"#{"}${colum.javaField}}
    </delete>
    </#if>
    </#list>
    <!-- 删除${functionName} -->
    <#list columnList as colum>
    <#if colum.isPk == '1'>
	<delete id="delete${javaClass}ByPKS" parameterType="${colum.javaType}" >
		delete from ${tableName} where ${colum.columnName} in
		<foreach item="${colum.javaField}" collection="array" open="(" separator="," close=")">
			${r"#{"}${colum.javaField}}
		</foreach>
    </delete>
    </#if>
    </#list>
</mapper>