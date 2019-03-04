/**   
* @Title ：Column.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2019年3月4日 下午11:23:27 
* @version ： 1.0   
*/
package com.qxy.jcode.tools;

/** 
* @ClassName ：Column 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2019年3月4日 下午11:23:27 
* SQL:
*  
*/
public class ColumnPrototype {
	
	//TABLE_CATALOG
	private String tableCatalog;
	
	//TABLE_SCHEMA
	private String tableSchema;
		
	//TABLE_NAME
	private String tableName;
	
	//COLUMN_NAME
	private String columnName;
	
	//COLUMN_TYPE
	private String columnType;
	
	//COLUMN_KEY
	private String columnKey;
	
	//COLUMN_COMMENT
	private  String columnComment;
	
	//IS_NULLABLE
	private String isNullable;
	
	//DATA_TYPE
	private String dataType;
	
	
	

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
	 * @return the columnKey
	 */
	public String getColumnKey() {
		return columnKey;
	}

	/**
	 * @param columnKey the columnKey to set
	 */
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	/**
	 * @return the columnComment
	 */
	public String getColumnComment() {
		return columnComment;
	}

	/**
	 * @param columnComment the columnComment to set
	 */
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	/**
	 * @return the isNullable
	 */
	public String getIsNullable() {
		return isNullable;
	}

	/**
	 * @param isNullable the isNullable to set
	 */
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
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

}
