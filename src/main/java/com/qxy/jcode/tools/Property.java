/**   
* @Title ：Property.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月3日 下午5:01:16 
* @version ： 1.0   
*/
package com.qxy.jcode.tools;

import lombok.Data;

/** 
* @ClassName ：Property 
* @Description ：实体对应的属性类
* @author ：PeterQi  
* @date ：2018年8月3日 下午5:01:16 
*  
*/
@Data
public class Property {
	// 属性数据类型
	private String javaType;
	// 属性名称
	private String propertyName;
	// 属性描述
	private String propertyDescription;
	// 属性类型
	private PropertyType propertyType;

}
