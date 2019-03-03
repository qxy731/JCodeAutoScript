package com.good.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.good.db.IPage;
import com.good.sys.bean.AuditLogPo;

public interface AuditLogDao {

    public List<AuditLogPo> listAuditLog(@Param("condition") Map<String, Object> param, @Param("page") IPage page);

    public void insert(AuditLogPo log);

}
