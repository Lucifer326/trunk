<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="main_role_list" class="easyui-layout" fit="true">
	
	<%-- <!--当前位置信息  -->
	<div region="north" class="loaction" style="height:30px!important;  border-top:solid 1px #AFAFAF;">
			<p><span class="pl30">${currentLocation}</span></p>
     </div> --%>
     
	<%-- <div data-options="region:'east',title:' ',split:true,collapsed:true" style="width: 600px;" split="true">
		<div tools="#tt" class="easyui-panel" title='<t:mutiLang langKey="permission.set"/>' style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
	</div> --%>
	<div data-options="region:'center'" style="padding:0px;border:0px">
	
	<t:datagrid name="releaseList" title="版本发布" actionUrl="releaseController.do?releaseGrid" 
	    idField="id" sortName="updateDate" sortOrder="desc" treeField="groupName,name,version,isUse" treegrid="true" queryMode="group">
		<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>
		<t:dgCol title="版本组件" field="groupName"></t:dgCol>	
		<t:dgCol title="版本名称" field="name" query="true"></t:dgCol>
		<t:dgCol title="版本编号" field="version"></t:dgCol>
		<t:dgCol title="版本状态" field="isUse" replace="common.status.role.enable_1,common.status.role.disable_0"></t:dgCol>
		<t:dgCol title="更新时间" field="updateDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
		<t:dgCol title="common.operation" field="opt"></t:dgCol>
		<t:dgDelOpt title="common.delete" url="releaseController.do?del&id={id}" />
		<t:dgToolBar title="发布" langArg="common.release" icon="icon-add" url="releaseController.do?addorupdate" funname="add" operationCode="add"></t:dgToolBar>
		<t:dgToolBar title="编辑" langArg="common.release" icon="icon-edit" url="releaseController.do?addorupdate" funname="update" operationCode="update"></t:dgToolBar>
	</t:datagrid>
	</div>

</div>

<!-- <div id="tt"></div> -->

<script type="text/javascript">



//删除角色
/* function delRelease(id){
	var judgeUrl= 'releaseController.do?judgeDelRelease&id='+id;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : judgeUrl,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var tabName= 'releaseList';
				var url= 'releaseController.do?delRelease&id='+id;
				$.dialog.confirm('<t:mutiLang langKey="confirm.delete.this.record"/>', function(){
					doSubmit(url,tabName);
					rowid = '';
				}, function(){
				});
			} else {
				//不可删除
				tip(d.msg);
			}
		}
	});
} */

</script>
