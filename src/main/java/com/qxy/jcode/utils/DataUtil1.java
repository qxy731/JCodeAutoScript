/**   
* @Title ：DataUtil.java 
* @Package ：com.qxy.jcode.utils 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月10日 上午12:31:01 
* @version ： 1.0   
*/
package com.qxy.jcode.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.qxy.jcode.tools.Property;
import com.qxy.jcode.tools.PropertyType;

/** 
* @ClassName ：DataUtil 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2018年8月10日 上午12:31:01 
*  
*/
public class DataUtil {
	
	public static HashMap<String,Object> TABLE = new HashMap<String,Object>();
	
	static {
		List<Property> list = new ArrayList<Property>();
		Property property = new Property();
		property.setJavaType("String");
		property.setPropertyName("pkId");
		property.setPropertyType(PropertyType.String);
		list.add(property);
		property = new Property();
		property.setJavaType("String");
		property.setPropertyName("orgCode");
		property.setPropertyType(PropertyType.String);
		list.add(property);
		property = new Property();
		property.setJavaType("Date");
		property.setPropertyName("dataDate");
		property.setPropertyType(PropertyType.Date);
		list.add(property);
		property = new Property();
		property.setJavaType("BigDecimal");
		property.setPropertyName("deposAmt");
		property.setPropertyType(PropertyType.BigDecimal);
		list.add(property);
		property = new Property();
		property.setJavaType("BigDecimal");
		property.setPropertyName("deposDay");
		property.setPropertyType(PropertyType.BigDecimal);
		list.add(property);
		property = new Property();
		property.setJavaType("BigDecimal");
		property.setPropertyName("deposMonth");
		property.setPropertyType(PropertyType.BigDecimal);
		list.add(property);
		property = new Property();
		property.setJavaType("BigDecimal");
		property.setPropertyName("deposYear");
		property.setPropertyType(PropertyType.BigDecimal);
		list.add(property);
		TABLE.put("RptBranchDeposAnal", list);
	}


	/** 
	* @Title：main 
	* @Description ：TODO
	* @date ：2018年8月10日 上午12:31:01 
	* @param ：@param args 
	* @return ：void 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
