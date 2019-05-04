<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/jstree/css/style.min.css"
	type="text/css">
<script type="text/javascript" src="plug-in/jstree/js/jstree.min.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true" class="largebox">
 <div data-options="region:'west',title:'系统配置',
	collapsed:false,
	split:true,
	border:false,"
	style="width: 250px; overflow: hidden;" class="left-hide-box fl">
    <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false"  id="regionListpanel">
    <div id="jstree1">
	<ul id="jstree">
	</ul>
	</div>
    </div>
</div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="bssysDeployList" checkbox="true" fitColumns="false" title="系统配置" actionUrl="bssysDeployController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="配置名称"  field="name"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="配置编号"  field="code"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数据表达式"  field="dateExpr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="说明"  field="description"    queryMode="group"  width="120"></t:dgCol>
 <%--   <t:dgCol title="父节点编码"  field="parentCode"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="控件类型"  field="controlType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="配置值"  field="value"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="排序"  field="sort"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否绑定区划"  field="isBinddis"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人ID"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人姓名"  field="createName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人ID"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人姓名"  field="updateName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="末次修改时间"  field="updateDate"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否已删除"  field="isdeleted"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门ID"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="bssysDeployController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="bssysDeployController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="bssysDeployController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="bssysDeployController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="bssysDeployController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 </body>
 <script src = "webpage/com/jeecg/bssys_deploy/bssysDeployList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 		//绑定系统配置左侧树
	    $.ajax({
			url:'bssysDeployController.do?tree',
			success:function(data){
				$('#jstree').jstree({
					'core':{
						'data':JSON.parse(data)
					}
				})
			}
		});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bssysDeployController.do?upload', "bssysDeployList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bssysDeployController.do?exportXls","bssysDeployList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bssysDeployController.do?exportXlsByT","bssysDeployList");
}
 </script>