package com.qxy.jcode.generator;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qxy.jcode.api.GeneratorClient;
import com.qxy.jcode.entity.ViewEntity;
import com.qxy.jcode.tools.FileType;
import com.qxy.jcode.tools.Property;
import com.qxy.jcode.utils.CommonUtil;
import com.qxy.jcode.utils.DataUtil;
import com.qxy.jcode.utils.FileUtil;

import freemarker.template.Template;
public class ViewGeneratorClient implements GeneratorClient{
	
	private Template template;
	private Map<String, Object> root;
	private ViewEntity entity;

	@Override
	public void generator(String nameNoSuffix) {
		try {
			// 获取或创建模板
			template = CommonUtil.getTemplate("view.ftl");
			// 创建数据模型
			createDataModel(nameNoSuffix);
			// 创建文件并控件台输出文件
			createFile(FileType.JSP);
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
		entity = new ViewEntity(nameNoSuffix);
		entity.setDescription("");
		List<Property> propertyList = (List<Property>)DataUtil.TABLE.get(nameNoSuffix);
		// 将属性集合添加到实体对象中
		entity.setProperties(propertyList);
		root = new HashMap<String, Object>();
		root.put("entity", entity);
		root.put("Constant", CommonUtil.useStaticConstant());
		return root;
	}
	
	/*(non-Javadoc) 
	* <p>Title ：createFile</p> 
	* <p>Description ： </p> 
	* @param fileType
	* @throws IOException 
	* @see com.qxy.jcode.api.GeneratorClient#createFile(com.qxy.jcode.tools.FileType) 
	*/
	@Override
	public void createFile(FileType fileType) throws IOException {
		String fileName = entity.getName() + "." + fileType.name().toLowerCase();
		FileUtil.out(template,root,entity.getName(),fileName,fileType);
	}
	
	public static void main(String[] args) {
		try {
			HashMap<String,Object> map = DataUtil.TABLE;
			ViewGeneratorClient entity = new ViewGeneratorClient();
			for(String nameNoSuffix : map.keySet()) {
				entity.generator(nameNoSuffix);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	

}
