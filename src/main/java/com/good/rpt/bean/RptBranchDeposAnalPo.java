package com.good.rpt.bean;

import java.io.Serializable;


import java.math.BigDecimal;
import java.util.Date;

/** 
*
* @ClassName ：RptBranchDeposAnalPo 
* @Description ： 实体类
* @author ：PeterQi
*
*/
public class RptBranchDeposAnalPo implements Serializable{

	private static final long serialVersionUID = 1L;

	//pkId
	private String pkId;

	//orgCode
	private String orgCode;

	//dataDate
	private Date dataDate;

	//deposAmt
	private BigDecimal deposAmt;

	//deposDay
	private BigDecimal deposDay;

	//deposMonth
	private BigDecimal deposMonth;

	//deposYear
	private BigDecimal deposYear;


	/**
	 * @return the PkId
	 */
	public String getPkId() {
		return pkId;
	}
	
	/**
	 * @param PkId the PkId to set
	 */
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	/**
	 * @return the OrgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}
	
	/**
	 * @param OrgCode the OrgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * @return the DataDate
	 */
	public Date getDataDate() {
		return dataDate;
	}
	
	/**
	 * @param DataDate the DataDate to set
	 */
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	/**
	 * @return the DeposAmt
	 */
	public BigDecimal getDeposAmt() {
		return deposAmt;
	}
	
	/**
	 * @param DeposAmt the DeposAmt to set
	 */
	public void setDeposAmt(BigDecimal deposAmt) {
		this.deposAmt = deposAmt;
	}
	/**
	 * @return the DeposDay
	 */
	public BigDecimal getDeposDay() {
		return deposDay;
	}
	
	/**
	 * @param DeposDay the DeposDay to set
	 */
	public void setDeposDay(BigDecimal deposDay) {
		this.deposDay = deposDay;
	}
	/**
	 * @return the DeposMonth
	 */
	public BigDecimal getDeposMonth() {
		return deposMonth;
	}
	
	/**
	 * @param DeposMonth the DeposMonth to set
	 */
	public void setDeposMonth(BigDecimal deposMonth) {
		this.deposMonth = deposMonth;
	}
	/**
	 * @return the DeposYear
	 */
	public BigDecimal getDeposYear() {
		return deposYear;
	}
	
	/**
	 * @param DeposYear the DeposYear to set
	 */
	public void setDeposYear(BigDecimal deposYear) {
		this.deposYear = deposYear;
	}
}