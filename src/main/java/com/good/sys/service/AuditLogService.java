package com.good.sys.service;

import java.util.List;
import java.util.Map;

import com.good.comm.enu.BizType;
import com.good.comm.enu.ExecuteResult;
import com.good.comm.enu.FunctionType;
import com.good.db.IPage;
import com.good.sys.ServiceException;
import com.good.sys.bean.AuditLogPo;
import com.good.sys.bean.Operator;

public interface AuditLogService {

    List<AuditLogPo> listAuditLog(Map<String, Object> condition, IPage p) throws ServiceException;

    /**
     * 增加审计日志
     * 
     * @param log
     */
    public void addAuditLog(Operator oper,BizType bizType,String operCode,String operName,String logDetail,FunctionType funcType,
            ExecuteResult result) throws ServiceException ;
}
