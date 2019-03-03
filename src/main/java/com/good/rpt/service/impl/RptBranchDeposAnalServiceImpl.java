package com.good.rpt.service.impl;

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

import com.good.rpt.bean.RptBranchDeposAnalPo;
import com.good.rpt.mapper.RptBranchDeposAnalDao;
import com.good.rpt.service.RptBranchDeposAnalService;
/** 
*
* @ClassName ：RptBranchDeposAnalServiceImpl 
* @Description ： 业务实现类
* @author ：PeterQi
*
*/
@Service
public class RptBranchDeposAnalServiceImpl implements RptBranchDeposAnalService {

	private static Logger logger = LoggerFactory.getLogger(RptBranchDeposAnalServiceImpl.class);
	
	@Autowired
	private RptBranchDeposAnalDao mapper;
	
	@Autowired
	private AuditLogService logService;
	
	@Override
	public List<RptBranchDeposAnalPo> list(Operator oper,@Param("condition") Map<String, Object> param, IPage page) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		List<RptBranchDeposAnalPo> ret = mapper.selectByConditionSelective(param, page);
		logger.info("list service result:\n{}\n", result);
		result = ExecuteResult.SUCCESS;
		logService.addAuditLog(oper, BizType.PRD, "listRptBranchDeposAnalPo", "查询","",
				FunctionType.QUERY, result);
		return ret;
	}

	@Override
	public void delete(Operator oper , RptBranchDeposAnalPo[] list) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		StringBuffer sb = new StringBuffer();
		for (RptBranchDeposAnalPo one : list) {
			String pkId = one.getPkId();
            mapper.deleteByPrimaryKey(pkId);
            sb.append(one.getPkId()).append(",");
        }	
		result = ExecuteResult.SUCCESS;
		logger.info("delete service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "deleteRptBranchDeposAnalPo", "删除",sb.toString(),
				FunctionType.DELETE, result);
	}

	@Override
	public void update(Operator oper, RptBranchDeposAnalPo one) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		mapper.updateByPrimaryKey(one);
		result = ExecuteResult.SUCCESS;
		logger.info("update service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "updateRptBranchDeposAnalPo", "更新", one.getPkId().toString(),
				FunctionType.UPDATE, result);
	}


	@Override
	public void insert(Operator oper, RptBranchDeposAnalPo one) throws ServiceException {
		ExecuteResult result = ExecuteResult.UNKNOWN;
		String pkId = UUIDGenerator.getUUIDKey();
		one.setPkId(pkId);
		mapper.insert(one);
		result = ExecuteResult.SUCCESS;
		logger.info("insert service result:\n{}\n", result);
		logService.addAuditLog(oper, BizType.PRD, "insertRptBranchDeposAnalPo", "增加", pkId,
				FunctionType.INSERT, result);
		
	}
	
}