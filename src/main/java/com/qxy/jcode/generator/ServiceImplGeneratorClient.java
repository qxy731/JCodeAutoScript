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
import java.util.Map;

import com.qxy.jcode.api.GeneratorClient;
import com.qxy.jcode.entity.BeanEntity;
import com.qxy.jcode.entity.MapperEntity;
import com.qxy.jcode.entity.ServiceEntity;
import com.qxy.jcode.entity.ServiceImplEntity;
import com.qxy.jcode.tools.Constant;
import com.qxy.jcode.tools.FileType;
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
public class ServiceImplGeneratorClient  implements GeneratorClient{
	
	private Template template;
	private Map<String, Object> root;
	private ServiceImplEntity entity;
	
	@Override
	public void generator(String nameNoSuffix) {
		try {
			// 创建、解析模板并缓存
			template = CommonUtil.getTemplate("serviceImpl.ftl");
			// 创建 数据模型
			createDataModel(nameNoSuffix);
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
	@Override
	public Map<String, Object> createDataModel(String nameNoSuffix) {
		entity = new ServiceImplEntity(nameNoSuffix);
		entity.setDescription("");
		root = new HashMap<String, Object>();
		root.put("entity", entity);
		root.put("Constant", CommonUtil.useStaticConstant());
		root.put("BeanName",new BeanEntity(nameNoSuffix).getName());
		root.put("MapperName",new MapperEntity(nameNoSuffix).getName());
		//root.put("mapperName",CommonUtil.lowerFirstCase(new MapperEntity(nameNoSuffix).getName()));
		root.put("ServiceName",new ServiceEntity(nameNoSuffix).getName());
		return root;
	}
	
	/*(non-Javadoc) 
	* <p>Title ：createFile</p> 
	* <p>Description ： </p> 
	* @param fileName
	* @throws IOException 
	* @see com.qxy.jcode.api.GeneratorClient#createFile(java.lang.String) 
	*/
	@Override
	public void createFile(FileType fileType) throws IOException {
		String fileName = entity.getName() + "." + fileType.name().toLowerCase();
		FileUtil.out(template, root, Constant.OUT_DIR_JAVA_SERVICE_IMPL, fileName,fileType);
	}
	
	public static void main(String[] args) {
		try{ 
			ServiceImplGeneratorClient service = new ServiceImplGeneratorClient();
			HashMap<String,String> map = DataUtil.parseGenerateXml();
			for(String nameNoSuffix : map.keySet()){
				//装载实体属性及表字段到DataUtil.TABLE
				//DataUtil.convertTableToProperty(map.get(nameNoSuffix), nameNoSuffix);
				service.generator(nameNoSuffix);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}