<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<title>${ftl_description}</title>
		<h:base type="jquery-hp,hplus,hplisttools"></h:base>
	</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="alert alert-success whj_location"><span class="c6">当前位置：<h:mutiLang langKey="jeect.platform"/> &nbsp > &nbsp 自治区双拥信息系统 &nbsp > &nbsp</span>  省双拥模范考评</div>
	 <h:datagrid name="${entityName?uncap_first}List" checkbox="true" fitColumns="false" title="${ftl_description}" actionUrl="${entityName?uncap_first}Controller.do?datagrid" idField="id" fit="true" queryMode="group">
	  <#list columns as po>
	   <h:dgCol title="${po.content}"  field="${po.fieldName}" <#if po.showType?index_of("datetime")!=-1>formatter="yyyy-MM-dd hh:mm:ss"<#else><#if po.showType?index_of("date")!=-1>formatter="yyyy-MM-dd"</#if></#if> <#if po.isShowList?if_exists?html =='N'>hidden="true"<#else></#if> <#if po.isQuery =='Y'>query="true"</#if> <#if po.queryMode =='single'>queryMode="single"<#elseif po.queryMode =='group'>queryMode="group"</#if> <#if po.dictTable?if_exists?html!="">dictionary="${po.dictTable},${po.dictField},${po.dictText}" <#if po.showType=='popup'>popup="true"</#if><#else><#if po.dictTable?if_exists?html=="" && po.dictField?if_exists?html!="">dictionary="${po.dictField}"</#if></#if> width="${po.fieldLength}"></h:dgCol>
	  </#list>
	   <h:dgCol title="操作" field="opt" width="100"></h:dgCol>
	   <%--<h:dgDelOpt title="删除" url="${entityName?uncap_first}Controller.do?doDel&id={id}" />--%>
	   <h:dgToolBar title="录入" icon="icon-add" url="${entityName?uncap_first}Controller.do?goAdd" funname="add" styleClass="btn-primary" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="编辑" icon="icon-edit" url="${entityName?uncap_first}Controller.do?goUpdate" funname="update"  styleClass="btn-success"  width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="批量删除"  icon="icon-remove" url="${entityName?uncap_first}Controller.do?doBatchDel" funname="deleteALLSelect"  styleClass="btn-warning" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="查看" icon="icon-search" url="${entityName?uncap_first}Controller.do?goUpdate" funname="detail"  styleClass="btn-danger" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="导入" icon="icon-put" funname="ImportXls"  styleClass="btn-submit" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"  styleClass="btn-primary" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"  styleClass="btn-success" width="800px" height="520px"></h:dgToolBar>
	   <#list buttons as btn>
	   <#if btn.buttonStyle =='button' && btn.buttonStatus == '1'>
	   	<h:dgToolBar title="${btn.buttonName}" icon="${btn.buttonIcon}" styleClass="btn-primary" <#if btn.optType=='action'> url="${entityName?uncap_first}Controller.do?do${btn.buttonCode?cap_first}" funname="do${btn.buttonCode?cap_first}"<#else> funname="${btn.buttonCode}"</#if> width="800px" height="520px"></h:dgToolBar>
	  	</#if>
	   </#list> 
	 </h:datagrid>
 
 </div>
 
 <!--<script src = "webpage/${bussiPackage?replace('.','/')}/${entityPackage}/${entityName?uncap_first}List.js"></script>	-->	
 <script type="text/javascript">
 $(document).ready(function(){
 		$(".whj_location").html("<span class=\"c6\">当前位置：<h:mutiLang langKey="jeect.platform"/> &nbsp > &nbsp "+$(".active > a >span",window.parent.document)[0].innerText+" &nbsp > &nbsp</span>  "+$(".active .nav-second-color",window.parent.document).text());
 		//给时间控件加上样式
 		<#list columns as po>
 		<#if (po.showType?index_of("datetime")!=-1 || po.showType?index_of("date")!=-1) && po.queryMode=="single">
 			//$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//外部js调用
            //laydate({
            //    elem: '#${po.fieldName}', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
 		<#else>
 		<#if (po.showType?index_of("datetime")!=-1 || po.showType?index_of("date")!=-1) && po.queryMode=="group">
 			//$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			//$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
  			//外部js调用
  			
  			var start = {
                elem: '#${po.fieldName}_begin', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				choose: function(datas){
					end.min = datas; //开始日选好后，重置结束日的最小日期
					end.start = datas //将结束日的初始值设定为开始日
				}
			};
			var end = {
                elem: '#${po.fieldName}_end', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			  	choose: function(datas){
			    	start.max = datas; //结束日选好后，重置开始日的最大日期
			  	}
			};
			laydate(start);
			laydate(end);
  			
  			
	
 		
 		</#if>
 		</#if>
		</#list>
 });
 <#list buttons as btn>
 	<#if btn.buttonStyle =='button' && btn.optType=='action'>
 	//自定义按钮-sql增强-${btn.buttonName}
 	function do${btn.buttonCode?cap_first}(title,url,id){
 		var rowData = $('#'+id).datagrid('getSelected');
		if (!rowData) {
			tip('请选择${btn.buttonName}项目');
			return;
		}
		url = url+"&id="+rowData['id'];
 		createdialog('确认 ', '确定'+title+'吗 ?', url,gridname);
 	}
 	</#if>
 </#list> 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', '${entityName?uncap_first}Controller.do?upload', "${entityName?uncap_first}List");
}

//导出
function ExportXls() {
	JeecgExcelExport("${entityName?uncap_first}Controller.do?exportXls","${entityName?uncap_first}List");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("${entityName?uncap_first}Controller.do?exportXlsByT","${entityName?uncap_first}List");
}
 </script>
 </body>
 </html>