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
 * 
 * 
 * @ClassName ：Entity 
 * @Description ： 业务接口类
 * @author ：PeterQi
 * @date ：2018年8月3日 下午5:05:48
 */

public class ServiceEntity extends Entity {

	public ServiceEntity(String nameNoSuffix) {
		super.setNameNoSuffix(nameNoSuffix);
		super.setParentPackage(Constant.OUT_DIR_JAVA_BASE);
		super.setSubPackage(Constant.OUT_DIR_JAVA_SERVICE);
		super.setNameSuffix(Constant.OUT_FILE_SUFFIX_SERVICE);
	}

}