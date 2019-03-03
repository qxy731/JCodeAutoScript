/**   
* @Title ：Entity.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月3日 下午5:05:48 
* @version ： 1.0   
*/
package com.qxy.jcode.entity;

import com.qxy.jcode.tools.Constant;

/** 
* @ClassName ：Entity 
* @Description ： 实体类
* @author ：PeterQi  
* @date ：2018年8月3日 下午5:05:48
*  
*/
public class ViewEntity extends Entity {
	
	public ViewEntity(String nameNoSuffix) {
		super.setNameNoSuffix(nameNoSuffix);
		super.setParentPackage(Constant.OUT_DIR_VIEW_BASE);
		super.setSubPackage(Constant.OUT_DIR_VIEW_REPORT);
		super.setNameSuffix(Constant.OUT_FILE_SUFFIX_VIEW);
	}
	
}