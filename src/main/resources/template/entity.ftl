package ${entity.parentPackage}.${entity.subPackage};

import java.io.Serializable;
<#list entity.importType as importType>
${importType}
</#list>

/** 
*
* @ClassName ：${entity.name} 
* @Description ： 实体类
* @author ：PeterQi
*
*/
public class ${entity.name}<#if entity.superType?has_content> extends ${entity.superType} </#if> implements Serializable{

	private static final long serialVersionUID = 1L;

<#list entity.properties as property>
	//${property.propertyName}
	private ${property.javaType} ${property.propertyName};

</#list>

<#list entity.properties as property>
	/**
	 * @return the ${property.propertyName?cap_first}
	 */
	public ${property.javaType} get${property.propertyName?cap_first}() {
		return ${property.propertyName};
	}
	
	/**
	 * @param ${property.propertyName?cap_first} the ${property.propertyName?cap_first} to set
	 */
	public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
		this.${property.propertyName} = ${property.propertyName};
	}
</#list>
}