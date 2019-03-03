package com.good.comm.web;


/**
 * ajax请求返回统一结果
 * 存放头信息
 * 
 * @author wuwei
 *
 */
public class WebPageResult {
    private String msg = "操作成功!";
    private String retcode = "I0001";
    private int pagesize = 15;
    private int pageno = 1;
    private Object data;
    private int recordsTotal;
    private int recordsFiltered;

    public WebPageResult() {
        super();
    }
    
    public WebPageResult(Object data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    public int getPagesize() {
        return pagesize;
    }
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
    public int getPageno() {
        return pageno;
    }
    public void setPageno(int pageno) {
        this.pageno = pageno;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public int getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    
}
