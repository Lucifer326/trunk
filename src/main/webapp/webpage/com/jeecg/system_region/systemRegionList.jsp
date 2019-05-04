<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<title>系统区划表</title>
		<h:base type="jquery-hp,hplus,hplisttools"></h:base>
	</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="alert alert-success whj_location"><span class="c6">当前位置：<h:mutiLang langKey="jeect.platform"/> &nbsp > &nbsp 自治区双拥信息系统 &nbsp > &nbsp</span>  省双拥模范考评</div>
	 <h:datagrid name="systemRegionList" checkbox="true" fitColumns="false" title="系统区划表" actionUrl="systemRegionController.do?datagrid" idField="id" fit="true" queryMode="group">
	   <h:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></h:dgCol>
	   <h:dgCol title="行政区划代码"  field="regionCode"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划名称"  field="regionName"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划简称"  field="regionShortName"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划级别名称"  field="regionLevel"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划长途电话区号"  field="areaCode"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划邮政编码"  field="regionPostcode"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划面积"  field="regionArea"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划设立日期"  field="regionCreateDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划撤销日期"  field="revokeDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划政府驻地名称"  field="regionEncampment"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划人口数量"  field="regionPopulation"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="行政区划城镇人口数量"  field="regionCountyPopulation"  hidden="true"  queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="父级id"  field="parentId"    queryMode="single"  width="120"></h:dgCol>
	   <h:dgCol title="操作" field="opt" width="100"></h:dgCol>
	   <h:dgDelOpt title="删除" url="systemRegionController.do?doDel&id={id}" />
	   <h:dgToolBar title="录入" icon="icon-add" url="systemRegionController.do?goAdd" funname="add" styleClass="btn-primary" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="编辑" icon="icon-edit" url="systemRegionController.do?goUpdate" funname="update"  styleClass="btn-success"  width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="批量删除"  icon="icon-remove" url="systemRegionController.do?doBatchDel" funname="deleteALLSelect"  styleClass="btn-warning" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="查看" icon="icon-search" url="systemRegionController.do?goUpdate" funname="detail"  styleClass="btn-danger" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="导入" icon="icon-put" funname="ImportXls"  styleClass="btn-submit" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"  styleClass="btn-primary" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"  styleClass="btn-success" width="800px" height="520px"></h:dgToolBar>
	 </h:datagrid>
 
 </div>
 
 <!--<script src = "webpage/com/jeecg/system_region/systemRegionList.js"></script>	-->	
 <script type="text/javascript">
 $(document).ready(function(){
 		$(".whj_location").html("<span class=\"c6\">当前位置：<h:mutiLang langKey="jeect.platform"/> &nbsp > &nbsp "+$(".active > a >span",window.parent.document)[0].innerText+" &nbsp > &nbsp</span>  "+$(".active .nav-second-color",window.parent.document).text());
 		//给时间控件加上样式
 			//$("#systemRegionListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//外部js调用
            //laydate({
            //    elem: '#createDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
 			//$("#systemRegionListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//外部js调用
            //laydate({
            //    elem: '#updateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
 			//$("#systemRegionListtb").find("input[name='regionCreateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//外部js调用
            //laydate({
            //    elem: '#regionCreateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
 			//$("#systemRegionListtb").find("input[name='revokeDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//外部js调用
            //laydate({
            //    elem: '#revokeDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'systemRegionController.do?upload', "systemRegionList");
}

//导出
function ExportXls() {
	JeecgExcelExport("systemRegionController.do?exportXls","systemRegionList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("systemRegionController.do?exportXlsByT","systemRegionList");
}
 </script>
 </body>
 </html>