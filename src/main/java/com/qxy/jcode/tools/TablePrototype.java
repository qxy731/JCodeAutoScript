/**   
* @Title ：Table.java 
* @Package ：com.qxy.jcode.tools 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2019年3月4日 下午11:15:31 
* @version ： 1.0   
*/
package com.qxy.jcode.tools;

/** 
* @ClassName ：Table 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2019年3月4日 下午11:15:31 
* sql:
* SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT
* FROM INFORMATION_SCHEMA.COLUMNS 
* WHERE TABLE_NAME='替换表名' AND TABLE_SCHEMA='替换数据库'
*  
*/
public class TablePrototype {
	
	//TABLE_CATALOG
	private String tableCatalog;
	
	//TABLE_SCHEMA
	private String tableSchema;
		
	//TABLE_NAME
	private String tableName;
	
	//TABLE_COMMENT
	private  String tableComment;
	
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

}
