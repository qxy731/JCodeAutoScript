/**   
* @Title ：Entity.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月3日 下午5:05:48 
* @version ： 1.0   
*/
package com.qxy.jcode.entity;

import java.util.List;
import java.util.Set;

import com.qxy.jcode.tools.Property;

/**
 *  
 * 
 * @ClassName ：Entity 
 * @Description ： 实体类
 * @author ：PeterQi  
 * @date ：2018年8月3日 下午5:05:48    
 */

public class Entity {

	// 实体所在的主包名
	private String parentPackage;
	// 实体所在的子包名
	private String subPackage;
	// 实体类名后缀
	private String nameSuffix;
	// 实体类名
	private String name;
	// 实体类名无后缀
	private String nameNoSuffix;
	// 实体类描述
	private String description;
	// 父类
	private String superType;
	// 实体引入类
	private Set<String> importType;
	// 实现类
	private Set<String> implementType;
	// 属性集合
	private List<Property> properties;

	/**
	 * @return the parentPackage
	 */
	public String getParentPackage() {
		return parentPackage;
	}

	/**
	 * @param parentPackage
	 *            the parentPackage to set
	 */
	public void setParentPackage(String parentPackage) {
		this.parentPackage = parentPackage;
	}

	/**
	 * @return the subPackage
	 */
	public String getSubPackage() {
		return subPackage;
	}

	/**
	 * @param subPackage
	 *            the subPackage to set
	 */
	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix
	 *            the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the nameNoSuffix
	 */
	public String getNameNoSuffix() {
		return nameNoSuffix;
	}

	/**
	 * @param nameNoSuffix
	 *            the nameNoSuffix to set
	 */
	public void setNameNoSuffix(String nameNoSuffix) {
		this.nameNoSuffix = nameNoSuffix;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the superType
	 */
	public String getSuperType() {
		return superType;
	}

	/**
	 * @param superType
	 *            the superType to set
	 */
	public void setSuperType(String superType) {
		this.superType = superType;
	}

	/**
	 * @return the importType
	 */
	public Set<String> getImportType() {
		return importType;
	}

	/**
	 * @param importType
	 *            the importType to set
	 */
	public void setImportType(Set<String> importType) {
		this.importType = importType;
	}

	/**
	 * @return the implementType
	 */
	public Set<String> getImplementType() {
		return implementType;
	}

	/**
	 * @param implementType
	 *            the implementType to set
	 */
	public void setImplementType(Set<String> implementType) {
		this.implementType = implementType;
	}

	/**
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name == null ? this.nameNoSuffix + this.nameSuffix : this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}