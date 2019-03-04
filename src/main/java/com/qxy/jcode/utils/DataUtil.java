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
	
	public static HashMap<String,Property> TABLES = new HashMap<String,Property>();
	
	public static HashMap<String,Object> TABLE = new HashMap<String,Object>();
	
	@Value("${pay.apiKey}")
	private String databaseName;
	
	private final String  tableSQL = "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+databaseName+"'" ; 
	
	private final String  columnsSQL = "SELECT TABLE_CATALOG,TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT,DATA_TYPE,IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+databaseName+"'" ; 
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String,Property> convertTableToProperty(String tableName){
		try {
			StringBuffer table = new StringBuffer();
	    	StringBuffer columns = new StringBuffer();
	    	table.append(tableSQL);
	    	columns.append(columnsSQL);
	    	if(!StringUtils.isEmpty(tableName)) {
	    		table.append(" AND TABLE_NAME='"+ tableName+"'");
	    		columns.append(" AND TABLE_NAME='"+ tableName+"'");
	    	}
	    	List<TablePrototype> tableRows = jdbcTemplate.query(table.toString(),new BeanPropertyRowMapper(TablePrototype.class));
	    	List<ColumnPrototype> columnsRows = jdbcTemplate.query(columns.toString(),new BeanPropertyRowMapper(ColumnPrototype.class));
	    	for(TablePrototype tb : tableRows){
	    		Property property = new Property();
	    		property.setTableCatalog(tb.getTableCatalog());
	    		property.setTableSchema(tb.getTableSchema());
	    		property.setTableName(tb.getTableName());
	    		property.setTableComment(tb.getTableComment());
	    		TABLES.put(tb.getTableName(), property);
	    	}
	    	for(ColumnPrototype col : columnsRows) {
	    		String tablename = col.getTableName();
	    		Property property = TABLES.get(tablename);
	    		if(property != null) {
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
	    		}
	    		TABLES.put(tablename,property);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
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
