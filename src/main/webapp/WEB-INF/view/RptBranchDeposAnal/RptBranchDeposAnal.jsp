<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/auth"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>报表</title>
<jsp:include page="/WEB-INF/template/comm.jsp"></jsp:include>
</head>
<body class="page-body">
  <div class="page-container">
    <div class="main-content">
      <div class="panel panel-default collapsed">
        <div class="panel-heading">
          <h3 class="panel-title">列表</h3>
          <div class="panel-options">
            <a href="#" data-toggle="panel"> <span class="collapse-icon">&ndash;</span> <span class="expand-icon">+</span>
            </a>
          </div>
        </div>
        <div class="panel-body">
          <h4 class="text-muted">过滤条件设置</h4>
          <div class="row">
            <form id="RptBranchDeposAnal_form" method="post" class="validate">
              <div class="col-md-3">
                <div class="form-group">
                  <label for="s_dataDate" class="control-label">数据日期</label>
                  <input type="date" class="form-control" id="s_dataDate" name="s_dataDate" placeholder="数据日期">
                </div>
              </div>
              <div class="col-md-3">
                <div class="form-group">
                  <label for="s_orgCodeName" class="control-label">机构名称</label>
                  <input type="text" class="form-control" id="s_orgCodeName" name="s_orgCodeName" readonly placeholder="请选择机构">
                  <input type="hidden" class="form-control" id="s_orgCode" name="s_orgCode"></input>
                </div>
              </div>
            </form>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="form-group pull-right">
                <button type="button" class="btn btn-success" id="filter_button">
                  <i class="fa-search"></i> 查询
                </button>
                <button type="reset" class="btn btn-info" id="reset_button">
                  <i class="fa-refresh"></i> 重置
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="panel-body-">
          <div class="row">
            <div class="col-md-9">
              <div class="btn-group" data-toggle="buttons">
                <label class="btn btn-blue glyphicon glyphicon-save" id="downbutton"> <input type="checkbox">导出</label>
              </div>
            </div>
            <div class="col-md-3">
              <li class="hiden-columns-title pull-right">
                <a href="#">
                  <span>隐藏列<i class="fa-angle-down"></i>
                  </span>
                </a>
                <ul class="hiden-columns">
                </ul>
              </li>
            </div>
          </div>
          <table class="table table-bordered table-striped table-condensed nowrap" id="listtable" style="width: 100%;">
            <thead>
              <tr>
                	<th></th>
                	<th></th>
                	<th></th>
                	<th></th>
                	<th></th>
                	<th></th>
                	<th></th>
              </tr>
            </thead>
            <tbody class="middle-align">
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
<jsp:include page="/WEB-INF/template/settings.jsp"></jsp:include>
<jsp:include page="/WEB-INF/template/pub/selectunit.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function() {
        //初始化列表
        var initparams01 = {
            "ajax" : {
                "url" : "${pageContext.request.contextPath}/rpt/rptbranchdeposanal/list",
                "type" : "POST",
                "data" : function(d) {
                    var orders = d.order;
                    console.log(orders);
                    for (var i = 0; i < orders.length; i++) {
                        d["norder[" + i + "][column]"] = d.columns[orders[i]["column"]].data;
                        d["norder[" + i + "][dir]"] = orders[i]["dir"];
                    }
                    console.log(orders);
                    var inputName = WebUtils.convertFormData("RptBranchDeposAnal_form");
                    for (var index in inputName) {
                        d[index] = inputName[index];
                    }
                    console.log(orders);
                }
            },
            "aoColumns" : [
                	{"data" : "pkId", "sClass" : "text-center" }
                	{"data" : "orgCode", "sClass" : "text-center" }
                	{"data" : "dataDate", "sClass" : "text-center" }
                	{"data" : "deposAmt", "sClass" : "text-center" }
                	{"data" : "deposDay", "sClass" : "text-center" }
                	{"data" : "deposMonth", "sClass" : "text-center" }
                	{"data" : "deposYear", "sClass" : "text-center" }
            ],
            "aoColumnDefs" : []
        };
        
        var params = $.extend({}, WebUtils.settings, initparams01);
        //params.ordering = false;
        var table = $("#listtable").DataTable(params);
        WebUtils.initColumnHider(table);

        //过滤条件设置按钮
        $("#filter_button").click(function() {
            table.draw();
            $(".panel-default").addClass("collapsed");
        });
        //过滤条件重置按钮
        $("#reset_button").click(function() {
        	//清空form表单数据
	       	 $("#RptBranchDeposAnal_form input").each(function(){
	            	$(this).val("");
	            });
        });
        
        
      //机构选择框
        $("#s_orgCodeName").on("click", function() {
            var selected = '{"s_orgCode":"unitId","s_orgCodeName":"unitName"}';
            $("#comm_selectUnit").modal("show", {
                backdrop : "static",
                id : selected,
                selectMode : "radio",
                selectedIds : $("#s_orgCode").val(),
                selectedNames : $("#s_orgCodeName").val()
            });
        });
      
        $("#downbutton").click(function() {
            var tableInfo = table.page.info();
            var tableRowsCount = tableInfo.recordsTotal;
            if (tableRowsCount && tableRowsCount != undefined && tableRowsCount > 0) {
                var jsonDataObj = WebUtils.convertFormData("RptBranchDeposAnal_form");
                jsonDataObj.fileName = "日常营销"+new Date().format("yyyyMMdd")+".xls";
                var url = "${pageContext.request.contextPath}/rpt/rptbranchdeposanal/download";
                WebUtils.download(url,jsonDataObj);
            } else {
                WebUtils.alert("请先查询出结果再导出！");
            }
        });
    });
   
</script>
</html>
