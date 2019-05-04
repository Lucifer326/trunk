<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%--   update-start--Author:duanql  Date:20130619 for：操作按钮窗口显示控制--%>
<div id="system_function_functionList" class="easyui-layout" fit="true"><%--   update-end--Author:duanql  Date:20130619 for：操作按钮窗口显示控制--%>
<div region="center" style="padding:0px;border:0px">
    <t:datagrid  name="functionList" title="视图管理"
                actionUrl="ModelViewController.do?mvGrid" idField="id" treegrid="true" pagination="false" checkbox="true">
        <t:dgCol title="common.id" field="id" treefield="id" hidden="true"></t:dgCol>
        <t:dgCol title="序号" field="functionOrder" treefield="order"></t:dgCol>
        <t:dgCol title="视图名" field="functionName" treefield="text"></t:dgCol>
        <t:dgCol title="原视图路径" field="functionUrl" treefield="src"></t:dgCol>
         <t:dgCol title="新视图路径" field="functionUrlt" treefield="code"></t:dgCol>
        
        <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
        <t:dgDelOpt url="ModelViewController.do?del&id={id}" title="common.delete"></t:dgDelOpt>
        <t:dgToolBar title="新建视图" langArg="common.menu" icon="icon-add" url="ModelViewController.do?addorupdate" funname="addFun"></t:dgToolBar>
        <t:dgToolBar title="编辑视图" langArg="common.menu" icon="icon-edit" url="ModelViewController.do?goUpdate" funname="update"></t:dgToolBar>
    </t:datagrid>
</div>
</div>
<%--   update-start--Author:duanql  Date:20130619 for：操作按钮窗口显示控制--%>
<div data-options="region:'east',
	title:'<t:mutiLang langKey="operate.button.list"/>',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 400px; overflow: hidden;">
<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="operationDetailpanel"></div>
</div>
</div>

<script type="text/javascript">
<%--   update-start--Author:anchao  Date:20130415 for：按钮权限控制--%>
$(function() {
	var li_east = 0;
	
	$('#system_function_functionList').click(function(){ 
	
		}); 
});


function reloadTable(){
	$(functionList).treegrid('reload');
	
}
function addFun(title,url, id) {
	var rowData = $('#'+id).datagrid('getSelected');
	if (rowData) {
		url += '&tsModelview.id='+rowData.id;
		
	}
	add(title,url,'functionList');
}
<%--   update-end--Author:jueyue  Date:20130622 for：菜单录入代入父菜单--%>
</script>

