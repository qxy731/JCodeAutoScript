package com.qxy.jcode;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qxy.jcode.generator.ControllerGeneratorClient;
import com.qxy.jcode.generator.EntityGeneratorClient;
import com.qxy.jcode.generator.MapperGeneratorClient;
import com.qxy.jcode.generator.ServiceGeneratorClient;
import com.qxy.jcode.generator.ServiceImplGeneratorClient;
import com.qxy.jcode.generator.ViewGeneratorClient;
import com.qxy.jcode.utils.DataUtil;

@SpringBootApplication
public class JCodeAutoScriptApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JCodeAutoScriptApplication.class, args);
		try {
			EntityGeneratorClient bean = new EntityGeneratorClient();
			MapperGeneratorClient mapper = new MapperGeneratorClient();
			ServiceGeneratorClient service = new ServiceGeneratorClient();
			ServiceImplGeneratorClient serviceImpl = new ServiceImplGeneratorClient();
			ControllerGeneratorClient controller = new ControllerGeneratorClient();
			ViewGeneratorClient view = new ViewGeneratorClient();
			HashMap<String,Object> map = DataUtil.TABLE;
			for(String nameNoSuffix : map.keySet()) {
				bean.generator(nameNoSuffix);
				mapper.generator(nameNoSuffix);
				service.generator(nameNoSuffix);
				serviceImpl.generator(nameNoSuffix);
				controller.generator(nameNoSuffix);
				view.generator(nameNoSuffix);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
