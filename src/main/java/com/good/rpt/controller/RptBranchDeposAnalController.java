package com.good.rpt.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.comm.FileUtils;
import com.good.comm.web.WebPageResult;
import com.good.comm.web.WebRequest;
import com.good.db.IPage;
import com.good.file.PoiExcelUtil;
import com.good.sys.MsgConstants;
import com.good.sys.WebUtils;
import com.good.sys.bean.LogonInfo;

import com.good.rpt.bean.RptBranchDeposAnalPo;
import com.good.rpt.service.RptBranchDeposAnalService;

/** 
*
* @ClassName ：RptBranchDeposAnalController 
* @Description ：业务 控制类
* @author ：PeterQi
*
*/
@Controller
@RequestMapping("/rpt/rptbranchdeposanal")
public class RptBranchDeposAnalController {

	private static Logger logger = LoggerFactory.getLogger(RptBranchDeposAnalController.class);
	@Autowired
	private RptBranchDeposAnalService service;
	
	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String toPage() throws Exception {
		return "/rptbranchdeposanal/rptbranchdeposanal";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rptbranchdeposanal/list", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public WebPageResult list(WebRequest wr, HttpServletRequest request) {
		WebPageResult ret = new WebPageResult();
		try{
			// 获取排序信息
			HashMap<String,Object> condition = WebUtils.fillOrderParam(wr, null);
			// 设置查询条件
			WebUtils.convertParam(condition, request);
			LogonInfo linfo = (LogonInfo) WebUtils.getLogInfo(request);
			// 获取翻页信息
			IPage page = WebUtils.getPageParam(wr);
			List<RptBranchDeposAnalPo> rows = service.list(linfo.getOperator(),condition, page);
			ret.setData(rows);
			ret.setRecordsTotal(page.getTotalCount());
			ret.setRecordsFiltered(page.getTotalCount());
		} catch (Exception e) {
	        logger.error(MsgConstants.E0000, e);
	        ret.setRetcode(MsgConstants.E0000);
	        ret.setMsg(e.getMessage());
	    }
		return ret;
	}
	
	@RequestMapping(value="/rptbranchdeposanal/download", method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogonInfo linfo = (LogonInfo) WebUtils.getLogInfo(request);
		// 设置查询条件
		HashMap<String,Object> condition = new HashMap<String,Object>();
		//condition.put("dataDate", request.getParameter("cdnDataDate"));
		//condition.put("orgCode", request.getParameter("cdnOrgCode"));
		List<RptBranchDeposAnalPo> rows = service.list(linfo.getOperator(),condition, null);
		String downloadChineseFileName = ".xlsx";
		String sheetName = "";
		String columnNames = "custNo,custName,mktGoal,mktType,lastUpdUserName,lastUpdOrgName,lastUpdTime,mktStatus";
		String columnDisplayNames = "客户号,客户名称,营销目的,营销方式,维护人,维护机构,维护日期,维护状态";
		String fileName = PoiExcelUtil.exportToExcel(request, response, rows, sheetName, columnNames, columnDisplayNames, null);
		File file = new File(fileName);
		HttpHeaders headers = new HttpHeaders();
		String fileNameN = new String(downloadChineseFileName.getBytes("UTF-8"), "ISO-8859-1");
		headers.setContentDispositionFormData("attachment", fileNameN);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.getFileContent(file), headers, HttpStatus.CREATED);
	}
	
}