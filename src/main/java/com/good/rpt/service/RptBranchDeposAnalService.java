package com.good.rpt.service;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.good.sys.ServiceException;
import com.good.sys.bean.Operator;
import com.good.db.IPage;
import com.good.rpt.bean.RptBranchDeposAnalPo;

/** 
*
* @ClassName ：RptBranchDeposAnalService 
* @Description ： 业务接口类
* @author ：PeterQi
*
*/
public interface RptBranchDeposAnalService {

	public List<RptBranchDeposAnalPo> list(Operator oper,@Param("condition") Map<String,Object> param, @Param("page") IPage page) throws ServiceException;

	public void delete(Operator oper,RptBranchDeposAnalPo[] list) throws ServiceException;

	public void update(Operator oper,RptBranchDeposAnalPo po) throws ServiceException;

	public void insert(Operator oper,RptBranchDeposAnalPo po) throws ServiceException;

}
