package ${entity.parentPackage}.${entity.subPackage};

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.good.db.IPage;
import ${Constant.OUT_DIR_JAVA_BASE}.${Constant.OUT_DIR_JAVA_ENTITY}.${BeanName};

/** 
*
* @ClassName ：${entity.name} 
* @Description ： 映射接口类
* @author ：PeterQi
*
*/
public interface ${entity.name} {

	public int deleteByPrimaryKey(String pkId);

	public int insert(${BeanName} record);

	public int insertSelective(${BeanName} record);

	public int updateByPrimaryKeySelective(${BeanName} record);

	public int updateByPrimaryKey(${BeanName} record);

	public ${BeanName} selectByPrimaryKey(String pkId);

	public List<${BeanName}> selectByConditionSelective(@Param("condition") Map<String,Object> param, @Param("page") IPage page);

}