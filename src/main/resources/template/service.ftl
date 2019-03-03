package ${entity.parentPackage}.${entity.subPackage};

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.good.sys.ServiceException;
import com.good.sys.bean.Operator;
import com.good.db.IPage;
import ${Constant.OUT_DIR_JAVA_BASE}.${Constant.OUT_DIR_JAVA_ENTITY}.${BeanName};

/** 
*
* @ClassName ：${entity.name} 
* @Description ： 业务接口类
* @author ：PeterQi
*
*/
public interface ${entity.name} {

	public List<${BeanName}> list(Operator oper,@Param("condition") Map<String,Object> param, @Param("page") IPage page) throws ServiceException;

	public void delete(Operator oper,${BeanName}[] list) throws ServiceException;

	public void update(Operator oper,${BeanName} po) throws ServiceException;

	public void insert(Operator oper,${BeanName} po) throws ServiceException;

}
