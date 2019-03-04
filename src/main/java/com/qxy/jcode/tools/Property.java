/**   
* @Title ：Property.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月3日 下午5:01:16 
* @version ： 1.0   
*/
package com.qxy.jcode.tools;

/** 
* @ClassName ：Property 
* @Description ：实体对应的属性类
* @author ：PeterQi  
* @date ：2018年8月3日 下午5:01:16 
*  
*/
public class Property {
	
	//TABLE_CATALOG
	private String tableCatalog;
	//TABLE_SCHEMA
	private String tableSchema;
	//TABLE_NAME
	private String tableName;
	//TABLE_COMMENT
	private  String tableComment;
	// 属性数据类型
	private String javaType;
	// 属性名称
	private String propertyName;
	// 属性描述
	private String propertyDescription;
	// 属性类型
	private PropertyType propertyType;
	// 属性对应数据库字段名
	private String columnName;
	// 属性对应数据库字段类型
	private String columnType;
	//DATA_TYPE
	private String dataType;
	// 是否主键
	private boolean primary;
	// 是否为空
	private boolean isNullable;
	/**
	 * @return the tableCatalog
	 */
	public String getTableCatalog() {
		return tableCatalog;
	}
	/**
	 * @param tableCatalog the tableCatalog to set
	 */
	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}
	/**
	 * @return the tableSchema
	 */
	public String getTableSchema() {
		return tableSchema;
	}
	/**
	 * @param tableSchema the tableSchema to set
	 */
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the tableComment
	 */
	public String getTableComment() {
		return tableComment;
	}
	/**
	 * @param tableComment the tableComment to set
	 */
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	/**
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}
	/**
	 * @param javaType the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * @return the propertyDescription
	 */
	public String getPropertyDescription() {
		return propertyDescription;
	}
	/**
	 * @param propertyDescription the propertyDescription to set
	 */
	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}
	/**
	 * @return the propertyType
	 */
	public PropertyType getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the primary
	 */
	public boolean isPrimary() {
		return primary;
	}
	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	/**
	 * @return the isNullable
	 */
	public boolean isNullable() {
		return isNullable;
	}
	/**
	 * @param isNullable the isNullable to set
	 */
	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}
	
}