/**   
* @Title ：EntityGeneratorClient.java 
* @Package ：com.qxy.jcode.entity 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月3日 下午5:12:04 
* @version ： 1.0   
*/
package com.qxy.jcode.generator;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qxy.jcode.api.GeneratorClient;
import com.qxy.jcode.entity.BeanEntity;
import com.qxy.jcode.entity.MapperEntity;
import com.qxy.jcode.entity.XMLEntity;
import com.qxy.jcode.tools.Constant;
import com.qxy.jcode.tools.FileType;
import com.qxy.jcode.tools.Property;
import com.qxy.jcode.utils.CommonUtil;
import com.qxy.jcode.utils.DataUtil;
import com.qxy.jcode.utils.FileUtil;

import freemarker.template.Template;

/** 
* @ClassName ：EntityGeneratorClient 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2018年8月3日 下午5:12:04 
*  
*/

public class XMLGeneratorClient  implements GeneratorClient{

	private Template template;
	private Map<String, Object> root;
	private XMLEntity entity;
	

	
	@Override
	public void generator(String classNameNoSuffix) {
		try {
			// 创建、解析模板并缓存
			template = CommonUtil.getTemplate("xml.ftl");
			// 创建 数据模型
			createDataModel(classNameNoSuffix);
			// 创建文件并控件台输出文件
			createFile(FileType.XML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 创建数据模型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> createDataModel(String nameNoSuffix) {
		entity = new XMLEntity(nameNoSuffix);
		List<Property> propertyList = (List<Property>)DataUtil.TABLE.get(nameNoSuffix);
		// 将属性集合添加到实体对象中
		entity.setProperties(propertyList);
		root = new HashMap<String,Object>();
		root.put("xmlEntity", entity);
		root.put("MapperEntity", new MapperEntity(nameNoSuffix));
		root.put("BeanEntity",new BeanEntity(nameNoSuffix));
		root.put("tableName", DataUtil.TABLE.get("tableName"));
		root.put("pkNames", DataUtil.TABLE.get("pkNames"));
		return root;
	}
	
	/*(non-Javadoc) 
	* <p>Title ：createFile</p> 
	* <p>Description ： </p> 
	* @param fileName 
	* @see com.qxy.jcode.api.GeneratorClient#createFile(java.lang.String) 
	*/
	@Override
	public void createFile(FileType fileType) throws IOException {
		String fileName = entity.getName() + "." + fileType.name().toLowerCase();
		FileUtil.out(template, root, Constant.OUT_DIR_XML_SQLMAP, fileName,fileType);
	}	


}
