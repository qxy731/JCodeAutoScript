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
import com.qxy.jcode.entity.ServiceEntity;
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
public class ServiceGeneratorClient  implements GeneratorClient{
	
	private Template template;
	private Map<String, Object> root;
	private ServiceEntity entity;
	
	@Override
	public void generator(String nameNoSuffix) {
		try {
			// 创建、解析模板并缓存
			template = CommonUtil.getTemplate("service.ftl");
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
		entity = new ServiceEntity(nameNoSuffix);
		root = new HashMap<String, Object>();
		root.put("entity", entity);
		root.put("Constant", CommonUtil.useStaticConstant());
		root.put("BeanName",new BeanEntity(nameNoSuffix).getName());
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
		FileUtil.out(template, root, Constant.OUT_DIR_JAVA_SERVICE, fileName,fileType);
	}
	
	public static void main(String[] args) {
		try{ 
			HashMap<String,Object> map = DataUtil.TABLE;
			ServiceGeneratorClient entity = new ServiceGeneratorClient();
			for(String nameNoSuffix : map.keySet()) {
				entity.generator(nameNoSuffix);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}