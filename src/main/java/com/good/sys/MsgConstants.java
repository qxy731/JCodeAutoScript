package com.good.sys;

import java.io.Serializable;

/**
 * 消息码类型定义
 */
public class MsgConstants implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作成功
     */
    public final static String I0000 = "I0000";
    /**
     * 新增成功
     */
    public final static String I0001 = "I0001";
    /**
     * 删除成功
     */
    public final static String I0002 = "I0002";
    /**
     * 修改成功
     */
    public final static String I0003 = "I0003";
    /**
     * 保存成功
     */
    public final static String I0004 = "I0004";
    /**
     * 查询成功
     */
    public final static String I0005 = "I0005";
    /**
     * 警告信息
     */
    public final static String W0000 = "W0000";
    /**
     * 系统出错
     */
    public final static String E0000 = "E0000";
    /**
     * 参数为空
     */
    public final static String E0001 = "E0001";
    /**
     * 数据库操作错
     */
    public final static String E0002 = "E0002";
    /**
     * 参数非法
     */
    public final static String E0003 = "E0003";
    /**
     * 未实现
     */
    public final static String E0004 = "E0004";
    /**
     * 用户未登录
     */
    public static final String E0005 = "E0005";
    /**
     * 获取流水号失败
     */
    public static final String E0006 = "E0006";
    /**
     * 记录不存在
     */
    public static final String E0007 = "E0007";
    /**
     * Session已经无效或过期<br/>
     * 请重新登录!
     */
    public static final String E0008 = "E0008";
    /**
     * 文件不存在{0}
     */
    public static final String E0009 = "E0009";
    /**
     * 复制文件失败
     */
    public static final String E0010 = "E0010";
    /**
     * 新增失败
     */
    public static final String E0011 = "E0011";
    /**
     * 修改失败
     */
    public static final String E0012 = "E0012";
    /**
     * 删除失败
     */
    public static final String E0013 = "E0013";
    /**
     * 查询失败
     */
    public static final String E0014 = "E0014";
    /**
     * 保存失败
     */
    public static final String E0015 = "E0015";
    /**
     * 许可证文件无效
     */
    public static final String E0016 = "E0016";
    /**
     * 无操作权限
     */
    public static final String E0017 = "E0017";
    /**
     * 工作流错误
     */
    public static final String E6000 = "E6000";
}
