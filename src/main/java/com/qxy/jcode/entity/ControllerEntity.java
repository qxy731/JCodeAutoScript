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
public class ControllerEntity extends Entity {

	public ControllerEntity(String nameNoSuffix) {
		super.setNameNoSuffix(nameNoSuffix);
		super.setParentPackage(Constant.OUT_DIR_JAVA_BASE);
		super.setSubPackage(Constant.OUT_DIR_JAVA_CONTROLLER);
		super.setNameSuffix(Constant.OUT_FILE_SUFFIX_CONTROLLER);
	}

}