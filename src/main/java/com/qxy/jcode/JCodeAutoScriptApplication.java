package com.qxy.jcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.qxy.jcode.generator.ControllerGeneratorClient;
import com.qxy.jcode.generator.EntityGeneratorClient;
import com.qxy.jcode.generator.MapperGeneratorClient;
import com.qxy.jcode.generator.ServiceGeneratorClient;
import com.qxy.jcode.generator.ServiceImplGeneratorClient;
import com.qxy.jcode.generator.ViewGeneratorClient;
import com.qxy.jcode.generator.XMLGeneratorClient;
import com.qxy.jcode.tools.Property;
import com.qxy.jcode.utils.DataUtil;

@SpringBootApplication

public class JCodeAutoScriptApplication {
	
	@Autowired
    private Environment env;
	
	@Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

	public static void main(String[] args) {
		//SpringApplication.run(JCodeAutoScriptApplication.class, args);
		try {
			DataUtil.TABLES = new HashMap<String,List <Property>>();
			DataUtil.TABLE = new HashMap<String,Object>();
			EntityGeneratorClient bean = new EntityGeneratorClient();
			MapperGeneratorClient mapper = new MapperGeneratorClient();
			ServiceGeneratorClient service = new ServiceGeneratorClient();
			ServiceImplGeneratorClient serviceImpl = new ServiceImplGeneratorClient();
			ControllerGeneratorClient controller = new ControllerGeneratorClient();
			ViewGeneratorClient view = new ViewGeneratorClient();
			XMLGeneratorClient xml = new XMLGeneratorClient();
			HashMap<String,String> map = DataUtil.parseGenerateXml();
			if(map != null) {
				for(String nameNoSuffix : map.keySet()){
					DataUtil dataUtil = new DataUtil();
					dataUtil.convertTableToProperty(map.get(nameNoSuffix));
				}
			}
			HashMap<String,List <Property>> tables = DataUtil.TABLES;
			Iterator<Map.Entry<String,List <Property>>> entries = tables.entrySet().iterator(); 
			while (entries.hasNext()) {
				Map.Entry<String,List <Property>> entry = (Map.Entry<String,List <Property>>) entries.next(); 
				String tableName = (String)entry.getKey();
				List <Property> property = (List <Property>)entry.getValue(); 
				String nameNoSuffix = tableName;
				DataUtil.TABLE.put("nameNoSuffix",nameNoSuffix);
				DataUtil.TABLE.put("tableName",tableName);
				DataUtil.TABLE.put(tableName,property);
				//DataUtil.TABLE.put("pkNames",);
				bean.generator(nameNoSuffix);
				mapper.generator(nameNoSuffix);
				service.generator(nameNoSuffix);
				serviceImpl.generator(nameNoSuffix);
				controller.generator(nameNoSuffix);
				view.generator(nameNoSuffix);
				xml.generator(nameNoSuffix);
				DataUtil.TABLE.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
