package com.good.sys.bean;

import com.good.comm.enu.BizType;

/**
 * 
 * @author wuwei
 *
 */
public interface Operator {
    public String getUserName();

    public String getUserID();

    public String getUserLevel();

    public String getOwnerUnitId();

    public String getOwnerUnitName();

    /**
     * 根据应用模块获得操作机构范围
     * 如果该模块未设置，返回缺省设置
     * 
     * @param sysType
     * @return
     */
    public String getOperUnit(BizType sysType);

    public String getIpAddress();
}
