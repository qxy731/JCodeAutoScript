package com.good.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.good.comm.enu.BizType;
import com.good.comm.enu.ExecuteResult;
import com.good.comm.enu.FunctionType;
import com.good.db.IPage;
import com.good.sys.MsgConstants;
import com.good.sys.ServiceException;
import com.good.sys.bean.AuditLogPo;
import com.good.sys.bean.Operator;
import com.good.sys.mapper.AuditLogDao;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogDao auditLogDao;

    public List<AuditLogPo> listAuditLog(Map<String, Object> param, IPage page) throws ServiceException {
        return auditLogDao.listAuditLog(param, page);
    }

    /**
     * 记录审计日志
     * 
     * @param bizType 业务模块
     * @param operCode 操作代码
     * @param operName 操作名称
     * @param logDetail 详细日志
     * @param funcType 操作类型
     * @param result 执行结果
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    public void addAuditLog(Operator oper,BizType bizType,String operCode,String operName,String logDetail,FunctionType funcType,
            ExecuteResult result) throws ServiceException {
        if (oper == null) {
            throw new ServiceException(MsgConstants.E0001,"操作员");
        }
        if (bizType == null) {
            throw new ServiceException(MsgConstants.E0001,"业务模块");
        }
        if (funcType == null) {
            throw new ServiceException(MsgConstants.E0001,"操作类型");
        }
        AuditLogPo po = new AuditLogPo();
        po.setBizType(bizType.name());
        po.setExecTime(new Date());
        po.setFuncType(funcType.getValue());
        po.setIpAddr(oper.getIpAddress());
        po.setLogDetail(logDetail);
        po.setOperCode(operCode);
        po.setOperName(operName);
        po.setOperStaffid(oper.getUserID());
        po.setExecResult(result == null ? ExecuteResult.UNKNOWN.getValue():result.getValue());
        auditLogDao.insert(po);
    }
}
