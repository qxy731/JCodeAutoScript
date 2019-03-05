/**   
* @Title ：DataUtil.java 
* @Package ：com.qxy.jcode.utils 
* @Description ： TODO
* @author ：PeterQi
* @date ： 2018年8月10日 上午12:31:01 
* @version ： 1.0   
*/
package com.qxy.jcode.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.util.StringUtils;
import com.qxy.jcode.tools.ColumnPrototype;
import com.qxy.jcode.tools.DbConnectionTool;
import com.qxy.jcode.tools.Property;
import com.qxy.jcode.tools.PropertyType;
import com.qxy.jcode.tools.TablePrototype;

/** 
* @ClassName ：DataUtil 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2018年8月10日 上午12:31:01 
*  
*/

public class DataUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DataUtil.class);
	
	public static HashMap<String,List <Property>> TABLES = new HashMap<String,List <Property> >();
	
	public static HashMap<String,Object> TABLE = new HashMap<String,Object>();
	
	@Value("${pay.apiKey}")
	private String databaseName;
	
	private final String  tableSQL = "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+databaseName+"'" ; 
	
	private final String  columnsSQL = "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT,DATA_TYPE,IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+databaseName+"'" ; 
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * 按照数据库名称查询所有表结构
	 * @param tableName
	 * @param connection
	 * @return
	 */
	@SuppressWarnings("finally")
	public static List<TablePrototype>  setTableComment(String tableName,Connection connection){
		List<TablePrototype> tableRows = new ArrayList<TablePrototype>();
    	//String tableComSql = "select TABLE_COMMENT from INFORMATION_SCHEMA.Tables where table_name='"+ tableName+"' ;";
		String tableComSql = "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+tableName+"'";
    	PreparedStatement statement = null;
    	ResultSet rs = null;
		try {
			statement = connection.prepareStatement(tableComSql);
			rs = statement.executeQuery();
	    	while(rs.next()){
	    		TablePrototype tablePrototype = new TablePrototype();
	    		tablePrototype.setTableCatalog(rs.getString("TABLE_CATALOG"));
	    		tablePrototype.setTableSchema(rs.getString("TABLE_SCHEMA"));
	    		tablePrototype.setTableName(rs.getString("TABLE_NAME"));
	    		tablePrototype.setTableComment(rs.getString("TABLE_COMMENT"));
	    		tableRows.add(tablePrototype);
	    		//TABLE.put("tableComment", rs.getString("TABLE_COMMENT"));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tableRows;
		}
    }
	
	 private static List<ColumnPrototype> setColumnComment(String tableName,Connection connection){
	    	HashMap<String,String> comments = new HashMap<String,String>();
	    	List<ColumnPrototype> columnsRows = new ArrayList<ColumnPrototype>();
	    	//String commentSql = "show full columns from " + tableName;
	    	String commentSql =  "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT,DATA_TYPE,IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+tableName+"'" ;
	    	PreparedStatement statement = null;
	        ResultSet rs = null;
			try {
				statement = connection.prepareStatement(commentSql);
				rs = statement.executeQuery();
				while(rs.next()){
					ColumnPrototype column = new ColumnPrototype();
					column.setTableCatalog(rs.getString("TABLE_CATALOG"));
					column.setTableSchema(rs.getString("TABLE_SCHEMA"));
					column.setTableName(rs.getString("TABLE_NAME"));
					column.setColumnName(rs.getString("COLUMN_NAME"));
					column.setColumnType(rs.getString("COLUMN_TYPE"));
					column.setColumnKey(rs.getString("COLUMN_KEY"));
					column.setColumnComment(rs.getString("COLUMN_COMMENT"));
					column.setDataType(rs.getString("DATA_TYPE"));
					column.setIsNullable(rs.getString("IS_NULLABLE"));
					columnsRows.add(column);
		        	//comments.put(rs.getString("Field"), rs.getString("Comment"));
		        }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	        return columnsRows;
	    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String,List <Property>> convertTableToProperty(String tableName){
    	Connection connection = null;
		try {
			StringBuffer table = new StringBuffer();
	    	StringBuffer columns = new StringBuffer();
	    	connection = DbConnectionTool.geConnection();	
	    	table.append(tableSQL);
	    	columns.append(columnsSQL);
	    	if(!StringUtils.isEmpty(tableName)) {
	    		table.append(" AND TABLE_NAME='"+ tableName+"'");
	    		columns.append(" AND TABLE_NAME='"+ tableName+"'");
	    	}
	    	List<TablePrototype> tableRows = setTableComment(tableName,connection);
	    	List<ColumnPrototype> columnsRows = setColumnComment(tableName,connection);
	    	for(TablePrototype tb : tableRows){
	    		//Property property = new Property();
	    		//property.setTableCatalog(tb.getTableCatalog());
	    		//property.setTableSchema(tb.getTableSchema());
	    		//property.setTableName(tb.getTableName());
	    		//property.setTableComment(tb.getTableComment());
	    		//TABLES.put(tb.getTableName(), property);
	    		
	    		List <Property> propertyList = new ArrayList<Property>();
	    		for(ColumnPrototype col : columnsRows) {
		    		String tablename = col.getTableName();
		    		Property property = new Property();//TABLES.get(tablename);
		    		if(property != null && tablename.equals(tb.getTableName())) {
		    			property.setColumnName(col.getColumnName());
		        		property.setColumnType(col.getColumnType());
		        		property.setDataType(col.getDataType());
		        		PropertyType propertyType = sqlType2JavaType(col.getDataType());
		        		property.setJavaType(propertyType.name());
		        		property.setNullable(col.getIsNullable()=="YES"?true:false);
		        		property.setPrimary(col.getColumnKey()=="pri"?true:false);
		        		property.setPropertyDescription(col.getColumnComment());
		        		property.setPropertyName(CommonUtil.lowerFirst(col.getColumnName()));
		        		property.setPropertyType(propertyType);
		        		propertyList.add(property);
		    		}
		    		
		    	}
	    		TABLES.put(tb.getTableName(), propertyList);
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=connection) {
				try {
					connection.close();
				}catch(Exception e) {
					
				}
			}
		}
		return TABLES;
    }
    

    /**
     * 
    * @Title：sqlType2JavaType 
    * @Description ：mysql的字段类型转化为java的类型
    * @date ：2018年8月11日 下午8:46:08 
    * @param ：@param sqlType
    * @param ：@return 
    * @return ：String 
    * @throws 
     */
    private static PropertyType sqlType2JavaType(String sqlType) {  
          
        if(sqlType.equalsIgnoreCase("bit")){    
            return PropertyType.Boolean;    
        }else if(sqlType.equalsIgnoreCase("tinyint")|| sqlType.equalsIgnoreCase("blob") ){    
            return PropertyType.Byte;    
        }else if(sqlType.equalsIgnoreCase("smallint")){    
            return PropertyType.Short;    
        }else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer") ){    
            return PropertyType.Integer;    
        }else if(sqlType.equalsIgnoreCase("bigint")){    
            return PropertyType.Long;    
        }else if(sqlType.equalsIgnoreCase("float")){    
            return PropertyType.Float;    
        }else if(sqlType.equalsIgnoreCase("decimal") ){ 
        	return PropertyType.BigDecimal;
        }else if(sqlType.equalsIgnoreCase("double")){    
            return PropertyType.Double;    
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")     
                 || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("tinytext")){    
            return PropertyType.String;    
        }else if(sqlType.equalsIgnoreCase("datetime") ||sqlType.equalsIgnoreCase("date") 
        		 ||sqlType.equalsIgnoreCase("timestamp")||sqlType.equalsIgnoreCase("time")){    
            return PropertyType.Date;    
        }   
            
        return PropertyType.String;   
    } 

 
    /**
     * 
    * @Title：parseGenerateXml 
    * @Description ：解析geennerate.xml读取表名和实体名前缀，生成过代码的要注释掉，否则重新执行会覆盖掉原来的文件
    * @date ：2018年8月14日 上午11:29:20 
    * @param ：@return 
    * @return ：HashMap<String,String> 
    * @throws 
     */
    @SuppressWarnings("rawtypes")
	public static HashMap<String,String> parseGenerateXml(){
    	//key为实体名 value为表名
    	HashMap<String,String> beanList = new HashMap<String,String>();
    	SAXReader reader = new SAXReader();
    	try {
            Document document = reader.read(new File("src/main/resources/generate.xml"));
            Element generates = document.getRootElement();
            Iterator it = generates.elementIterator();
           
            while (it.hasNext()) {
                Element generate = (Element) it.next();
                // 获取table , beanname
                String tableName = generate.attributeValue("tableName");
                String beanNameNoSuf = generate.attributeValue("beanNameNoSuf");
                beanList.put(beanNameNoSuf, tableName);
            }   
            }catch (DocumentException e) {
                logger.error("generate.xml解析异常,",e);
            }
    	return beanList;
    }

    public static void main(String[] args) {
    	DataUtil.parseGenerateXml();
    	//System.out.println(CommonUtil.lowerFirstCase("RptBranchDeposAnalService"));
		//System.out.println(CommonUtil.upperFirstCase("r"));
		//System.out.println(i);
	}
}
