
package com.qxy.jcode.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//import org.apache.log4j.Logger;

/**
 * 
* @ClassName ：DbConnectionTool 
* @Description ： TODO
* @author ：PeterQi  
* @date ：2018年8月10日 下午10:10:01 
*  
 */
public class DbConnectionTool {

	//private static Logger logger = Logger.getLogger(DbConnectionTool.class);
    private static Connection conn = null;
  
    private DbConnectionTool(){

    }
    /**
     * 
    * @Title：geConnection 
    * @Description ：获取mysql数据库连接
    * @date ：2018年8月11日 上午10:50:07 
    * @param ：@return 
    * @return ：Connection 
    * @throws 
     */
    public static Connection geConnection(){
        try {
            Properties p=new Properties();
            p.load(DbConnectionTool.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            String dirver=p.getProperty("driver");
            Class.forName(dirver);
            
            String url=p.getProperty("url");
            String name=p.getProperty("username");
            String pwd=p.getProperty("password");
           
            if(conn==null || conn.isClosed()){
        	   conn=DriverManager.getConnection(url, name, pwd);
           }
        } catch (Exception e) {
        	//logger.error("连接数据库异常!",e);
        	System.out.println("连接数据库异常!"+e);
        }
        return conn;
    }
   
    	
    }
