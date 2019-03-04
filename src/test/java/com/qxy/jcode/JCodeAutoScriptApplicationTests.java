package com.qxy.jcode;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxy.jcode.utils.DataUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JCodeAutoScriptApplicationTests {

	@Test
	public void test() {
		HashMap<String,String> map = DataUtil.parseGenerateXml();
		for(String nameNoSuffix : map.keySet()){
			//装载实体属性及表字段到DataUtil.TABLE
			//DataUtil.convertTableToProperty(map.get(nameNoSuffix));
		}
	}

}
