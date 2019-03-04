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
import java.util.Set;

import com.qxy.jcode.api.GeneratorClient;
import com.qxy.jcode.entity.BeanEntity;
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
public class EntityGeneratorClient  implements GeneratorClient{

	private Template template;
	private Map<String, Object> root;
	private BeanEntity entity;
	
	@Override
	public void generator(String classNameNoSuffix) {
		try {
			// 创建、解析模板并缓存
			template = CommonUtil.getTemplate("entity.ftl");
			// 创建 数据模型
			createDataModel(classNameNoSuffix);
			// 创建文件并控件台输出文件
			createFile(FileType.JAVA);
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
		entity = new BeanEntity(nameNoSuffix);
		List<Property> propertyList = (List<Property>)DataUtil.TABLE.get(nameNoSuffix);
		// 将属性集合添加到实体对象中
		entity.setProperties(propertyList);
		Set<String> importType = CommonUtil.calculateEntityImports(propertyList);
		entity.setImportType(importType);
		root = new HashMap<String,Object>();
		root.put("entity", entity);
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
		FileUtil.out(template, root, Constant.OUT_DIR_JAVA_ENTITY, fileName,fileType);
	}	
	
	public static void main(String[] args) {
		try { 
			EntityGeneratorClient entity = new EntityGeneratorClient();
			HashMap<String,String> map = DataUtil.parseGenerateXml();
			for(String nameNoSuffix : map.keySet()){
				//装载实体属性及表字段到DataUtil.TABLE
				//DataUtil.convertTableToProperty(map.get(nameNoSuffix), nameNoSuffix);
				entity.generator(nameNoSuffix);
				DataUtil.TABLE.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
