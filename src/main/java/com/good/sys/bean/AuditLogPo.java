package com.good.sys.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 参数传递查询的类
 */
public class AuditLogPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String operStaffid;

    private String roleId;

    private String devoStaffid;

    private String operCode;

    private String operName;

    private String ipAddr;

    private String bizType;

    private String funcType;

    private String execResult;

    private Date execTime;

    private String logDetail;

    private String staffName;

    private String roleName;

    /**
     * 创建人
     */
    private String crtUser;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date crtTime;
    /**
     * 最后修改人
     */
    private String updUser;
    /**
     * 最后修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updTime;
    /**
     * EXT
     */
    private String ext;

    /**
     * @return 顺序号
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            顺序号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 执行人编码
     */
    public String getOperStaffid() {
        return operStaffid;
    }

    /**
     * @param operStaffid
     *            执行人编码
     */
    public void setOperStaffid(String operStaffid) {
        this.operStaffid = operStaffid;
    }

    /**
     * @return 执行人角色
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            执行人角色
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 代理人编码
     */
    public String getDevoStaffid() {
        return devoStaffid;
    }

    /**
     * @param devoStaffid
     *            代理人编码
     */
    public void setDevoStaffid(String devoStaffid) {
        this.devoStaffid = devoStaffid;
    }

    /**
     * @return 操作编码
     */
    public String getOperCode() {
        return operCode;
    }

    /**
     * @param operCode
     *            操作编码
     */
    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    /**
     * @return 操作名称
     */
    public String getOperName() {
        return operName;
    }

    /**
     * @param operName
     *            操作名称
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * @return 客户端IP
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * @param ipAddr
     *            客户端IP
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * @return 业务类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * @param bizType
     *            业务类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * @return 操作类型
     */
    public String getFuncType() {
        return funcType;
    }

    /**
     * @param funcType
     *            操作类型
     */
    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    /**
     * @return 执行结果
     */
    public String getExecResult() {
        return execResult;
    }

    /**
     * @param execResult
     *            执行结果
     */
    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }

    /**
     * @return 执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public java.util.Date getExecTime() {
        return execTime;
    }

    /**
     * @param execTime
     *            执行时间
     */
    public void setExecTime(java.util.Date execTime) {
        this.execTime = execTime;
    }

    /**
     * @return 详细信息
     */
    public String getLogDetail() {
        return logDetail;
    }

    /**
     * @param logDetail
     *            详细信息
     */
    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail;
    }

    /**
     * @return 执行人姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param operStaffName
     *            执行人姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return 创建人
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param createUser
     *            创建人
     */
    public void setCrtUser(String createUser) {
        this.crtUser = createUser;
    }

    /**
     * @return 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public java.util.Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param createTime
     *            创建时间
     */
    public void setCrtTime(java.util.Date createTime) {
        this.crtTime = createTime;
    }

    /**
     * @return 最后修改人
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * @param updUser
     *            最后修改人
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * @return 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public java.util.Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     *            最后修改时间
     */
    public void seUpdTime(java.util.Date updTime) {
        this.updTime = updTime;
    }

    /**
     * @return EXT
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext
     *            EXT
     */
    public void setExt(String ext) {
        this.ext = ext;
    }
}