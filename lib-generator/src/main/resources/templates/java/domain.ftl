package ${packagePatch}.${moduleCode}.${functionCode}.domain;
<#list columPackages as columPackage>
import ${columPackage};
</#list>
/**
 * ${functionName}
 * @author ${functionAuthor}
 *
 */
public class ${javaClass} {
	<#list columnList as colum>
	<#if colum.columnComment??>/** ${colum.columnComment}*/</#if>
	private ${colum.javaType} ${colum.javaField};
	</#list>
	<#list columnList as colum>
	<#if colum.columnComment??>
	/** 
	 * 设置 ${colum.columnComment}
	 * @param ${colum.javaField}
	 */
	</#if>
	public void set${colum.javaField ? cap_first} (${colum.javaType} ${colum.javaField}) {
	  this.${colum.javaField} = ${colum.javaField};
	}
	<#if colum.columnComment??>
	/** 
	 * 获取 ${colum.columnComment}
	 * @param ${colum.javaField}
	 */
	</#if>
	public ${colum.javaType} get${ colum.javaField ? cap_first} () {
	  return ${colum.javaField};
	}
	</#list>
}