/**   
* @Title ：CommonUtil.java 
* @Package ：com.qxy.jcode.utils 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月9日 下午12:00:28 
* @version ： 1.0   
*/
package com.qxy.jcode.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.qxy.jcode.tools.Constant;
import com.qxy.jcode.tools.Property;
import com.qxy.jcode.tools.PropertyType;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/** 
* @ClassName ：CommonUtil 
* @Description ： TODO
* @author ：PeterQi
* @date ：2018年8月9日 下午12:00:28 
*  
*/
public class CommonUtil {
	
	/** The Constant lineSeparator. */
	private static final String lineSeparator;

	static {
		String ls = System.getProperty("line.separator"); //$NON-NLS-1$
		if (ls == null) {
			ls = "\n"; //$NON-NLS-1$
		}
		lineSeparator = ls;
	}

	/**
	 * returns a unique set of "import xxx;" Strings for the set of types.
	 *
	 * @param importedTypes
	 *    the imported types
	 * @return the sets the
	 */
	public static Set<String> calculateEntityImports(List<Property> propertyList) {
		Set<String> importStrings = new TreeSet<String>();
		for (Property property : propertyList) {
			if(PropertyType.BigDecimal.toString().equals(property.getPropertyType().toString())){
				StringBuffer sb = new StringBuffer("import ");//$NON-NLS-1$
				sb.append("java.math.BigDecimal").append(";");
				importStrings.add(sb.toString());
			}
			if(PropertyType.Date.toString().equals(property.getPropertyType().toString())||PropertyType.Timestamp.toString().equals(property.getPropertyType().toString())){
				StringBuffer sb = new StringBuffer("import ");//$NON-NLS-1$
				sb.append("java.util.Date").append(";");
				importStrings.add(sb.toString());
			}
		}
		importStrings.add(lineSeparator);
		return importStrings;
	}

	public static Template getTemplate(String templateFile) throws IOException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		// 步骤一：指定 模板文件从何处加载的数据源，这里设置一个文件目录
		File file = new File(Constant.IN_DIR_TEMPLATE_ROOT);
		cfg.setDirectoryForTemplateLoading(file);
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
		// 步骤二：创建、解析模板并缓存
		return cfg.getTemplate(templateFile);
	}

	public static TemplateHashModel useStaticType(String className) {
		TemplateHashModel fileStatics = null;
		try {
			BeansWrapperBuilder wrapperBuilder = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			BeansWrapper wrapper = wrapperBuilder.build();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			fileStatics = (TemplateHashModel) staticModels.get(className);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		return fileStatics;
	}
	
	public static TemplateHashModel useStaticConstant() {
		return useStaticType("com.qxy.jcode.tools.Constant");
	}
	
	/**
	* @Title：upperCase 
	* @Description ：String首字母大写方法
	* @date ：2018年8月9日 下午5:50:25 
	* @param ：@param str
	* @param ：@return 
	* @return ：String 
	* @throws 
	 */
	public static String upperFirstCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] -= 32;
		}
		return new String(ch);
	}
	
	/**
	* @Title：upperCase 
	* @Description ：String首字母小写方法
	* @date ：2018年8月9日 下午5:50:25 
	* @param ：@param str
	* @param ：@return 
	* @return ：String 
	* @throws 
	 */
	public static String lowerFirstCase(String str){
		char[] ch = str.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] += 32;
		}
		return new String(ch);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CommonUtil.lowerFirstCase("RptBranchDeposAnalService"));
	}

}
