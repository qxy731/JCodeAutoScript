/**   
* @Title ：GeneratorClient.java 
* @Package ：com.qxy.jcode 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月9日 下午1:09:38 
* @version ： 1.0   
*/
package com.qxy.jcode.api;

import java.io.IOException;
import java.util.Map;

import com.qxy.jcode.tools.FileType;

/** 
* @ClassName ：GeneratorClient
* @Description ： TODO
* @author ：PeterQi
* @date ：2018年8月9日 下午1:09:38 
*  
*/
public interface GeneratorClient {
	
	public Map<String, Object> createDataModel(String classNameNoSuffix);
	
	public void generator(String classNameNoSuffix);
	
	public void createFile(FileType fileType) throws IOException;

}
