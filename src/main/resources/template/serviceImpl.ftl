package ${entity.parentPackage}.${entity.subPackage};

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.comm.enu.BizType;
import com.good.comm.enu.ExecuteResult;
import com.good.comm.enu.FunctionType;
import com.good.db.IPage;
import com.good.sys.ServiceException;
import com.good.sys.UUIDGenerator;
import com.good.sys.bean.Operator;
import com.good.sys.service.AuditLogService;

import ${Constant.OUT_DIR_JAVA_BASE}.${Constant.OUT_DIR_JAVA_ENTITY}.${BeanName};
import ${Constant.OUT_DIR_JAVA_BASE}.${Constant.OUT_DIR_JAVA_MAPPER}.${MapperName};
import ${Constant.OUT_DIR_JAVA_BASE}.${Constant.OUT_DIR_JAVA_SERVICE}.${ServiceName};
/** 
*
* @ClassName ：${entity.name} 
* @Description ： 业务实现类
* @author ：PeterQi
*
*/
@Service
public class ${entity.name} implements ${ServiceName} {

	private static Logger logger = LoggerFactory.getLogger(${entity.name}.class);
	
	@Autowired
	private ${MapperName} mapper;
	
	@Autowired
	private AuditLogService logService;
	
	@Override
	public List<${BeanName}> list(Operator oper,@Param("condition") Map<String, Object> param, IPage page) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		List<${BeanName}> ret = mapper.selectByConditionSelective(param, page);
		logger.info("list service result:\n{}\n", result);
		result = ExecuteResult.SUCCESS;
		logService.addAuditLog(oper, BizType.PRD, "list${BeanName}", "查询${entity.description}","",
				FunctionType.QUERY, result);
		return ret;
	}

	@Override
	public void delete(Operator oper , ${BeanName}[] list) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		StringBuffer sb = new StringBuffer();
		for (${BeanName} one : list) {
			String pkId = one.getPkId();
            mapper.deleteByPrimaryKey(pkId);
            sb.append(one.getPkId()).append(",");
        }	
		result = ExecuteResult.SUCCESS;
		logger.info("delete service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "delete${BeanName}", "删除${entity.description}",sb.toString(),
				FunctionType.DELETE, result);
	}

	@Override
	public void update(Operator oper, ${BeanName} one) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		mapper.updateByPrimaryKey(one);
		result = ExecuteResult.SUCCESS;
		logger.info("update service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "update${BeanName}", "更新${entity.description}", one.getPkId().toString(),
				FunctionType.UPDATE, result);
	}


	@Override
	public void insert(Operator oper, ${BeanName} one) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		String pkId = UUIDGenerator.getUUIDKey();
		one.setPkId(pkId);
		mapper.insert(one);
		result = ExecuteResult.SUCCESS;
		logger.info("insert service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "insert${BeanName}", "增加${entity.description}", pkId,
				FunctionType.INSERT, result);
		
	}
	
}