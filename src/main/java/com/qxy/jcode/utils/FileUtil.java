/**   
* @Title ：CreateFile.java 
* @Package ：com.qxy.jcode.utils 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月6日 下午12:59:14 
* @version ： 1.0   
*/
package com.qxy.jcode.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.qxy.jcode.tools.Constant;
import com.qxy.jcode.tools.FileType;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
* @ClassName ：CreateFile 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2018年8月6日 下午12:59:14 
*  
*/
public class FileUtil {
	
	/**
	 * 创建文件 和 返回文件File对象
	 * @param outDirFile 生成文件路径
	 * @param javaPackage java包名
	 * @param javaClassName java类名
	 * @return
	 * @throws IOException 
	 */
/*	public static File createJavaFile(String subPackage, String className) throws IOException {
        File outDirFile = new File(Constant.OUT_DIR_JAVA_ROOT);
		if(!outDirFile.exists()){
			outDirFile.mkdir();
		}
		System.out.println(outDirFile);
        String packagePath = Constant.OUT_DIR_JAVA_BASE+"."+subPackage;
        packagePath = packagePath.replace('.', '/');
        File myPackage = new File(outDirFile,packagePath);
        if(!myPackage.exists()){
        	myPackage.mkdirs();
        }
        File file = new File(myPackage, className);
        return file;
    }
	
	public static File createViewFile(String subFilePath, String fileName) {
		File outDirFile = new File(Constant.OUT_DIR_VIEW_ROOT);
		if(!outDirFile.exists()){
			outDirFile.mkdir();
		}
		String filePath = Constant.OUT_DIR_VIEW_BASE+"/"+subFilePath;
        File myFilePath = new File(outDirFile,filePath);
        if(!myFilePath.exists()){
        	myFilePath.mkdirs();
        }
        File file = new File(myFilePath, fileName);
        return file;
    }
	
	public static File createXmlFile(String subFilePath, String fileName) {
		File outDirFile = new File(Constant.OUT_DIR_XML_ROOT);
		if(!outDirFile.exists()){
			outDirFile.mkdir();
		}
		String filePath = Constant.OUT_DIR_XML_BASE+"/"+subFilePath;
        File myFilePath = new File(outDirFile,filePath);
        if(!myFilePath.exists()){
        	myFilePath.mkdirs();
        }
        File file = new File(myFilePath, fileName);
        return file;
    }*/
	
	/**
	* @Title：createFile 
	* @Description ：创建文件 和 返回文件File对象
	* @date ：2018年8月9日 下午5:07:26 
	* @param ：@param fileType
	* @param ：@param subFilePath
	* @param ：@param fileName
	* @param ：@return 
	* @return ：File 
	* @throws 
	 */
	public static File createFile(FileType fileType,String subFilePath, String fileName) {
		String rootPath = "";
		if(FileType.JAVA.name().equals(fileType.name())) {
			rootPath = Constant.OUT_DIR_JAVA_ROOT;
		}else if(FileType.JSP.name().equals(fileType.name())) {
			rootPath = Constant.OUT_DIR_VIEW_ROOT;
		}else if(FileType.XML.name().equals(fileType.name())) {
			rootPath = Constant.OUT_DIR_XML_ROOT;
		}
		File outDirFile = new File(rootPath);
		if(!outDirFile.exists()){
			outDirFile.mkdir();
		}
		String basePath = "";
		if(FileType.JAVA.name().equals(fileType.name())) {
			basePath = Constant.OUT_DIR_JAVA_BASE+"/"+subFilePath;
			basePath = basePath.replace(".", "/");
		}else if(FileType.JSP.name().equals(fileType.name())) {
			basePath = Constant.OUT_DIR_VIEW_BASE+"/"+subFilePath;
		}else if(FileType.XML.name().equals(fileType.name())) {
			if(subFilePath!=null && !"".equals(subFilePath) ) {
				basePath = Constant.OUT_DIR_XML_BASE+"/"+subFilePath;
			}else {
				basePath = Constant.OUT_DIR_XML_BASE;
			}
		}
        File baseFilePath = new File(outDirFile,basePath);
        if(!baseFilePath.exists()){
        	baseFilePath.mkdirs();
        }
        File file = new File(baseFilePath, fileName);
        return file;
    }
	
	
	
	/**
	 * 
	* @Title：outFile 
	* @Description ：模板数据输出到文件
	* @date ：2018年8月6日 下午1:46:54 
	* @param ：@param template
	* @param ：@param file
	* @param ：@param map 
	* @return ：void 
	* @throws 
	 */
	public static void outFile(Template template,File file,Map<String,Object> root) {
		try {
			Writer writer = new FileWriter(file);
			template.process(root, writer);
			writer.flush();
			System.out.println("文件生成路径：" + file.getCanonicalPath());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title：outFile 
	* @Description ：输出到Console控制台
	* @date ：2018年8月6日 下午1:46:38 
	* @param ：@param template
	* @param ：@param map 
	* @return ：void 
	* @throws 
	 */
	public static void outConsole(Template template,Map<String,Object> root) {
		try {
			Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static void out(Template template,Map<String,Object> root,String subPackage,String fileName,FileType fileType) throws IOException{
		File file = createFile(fileType,subPackage,fileName);
		outFile(template,file,root);
		outConsole(template,root);
	}
	
	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		try {
			FileUtil.createFile(FileType.JAVA,"service","RptBranchDeposAnal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
