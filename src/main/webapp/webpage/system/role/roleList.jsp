<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="main_role_list" class="easyui-layout" fit="true">
	
	<!--当前位置信息  -->
	<div region="north" class="loaction" style="height:30px!important;  border-top:solid 1px #AFAFAF;">
			<p><span class="pl30">${currentLocation}</span></p>
     </div>
     
	<div data-options="region:'east',title:' ',split:true,collapsed:true" style="width: 600px;" split="true">
		<div tools="#tt" class="easyui-panel" title='<t:mutiLang langKey="permission.set"/>' style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
	</div>
	<div data-options="region:'center'" style="padding:0px;border:0px">
	
	<t:datagrid name="roleList" title="common.role.list" actionUrl="roleController.do?roleGrid" 
	    idField="id" sortName="createDate" sortOrder="desc" queryMode="group">
		<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>	
		<t:dgCol title="common.role.name" field="roleName" query="true"></t:dgCol>
		<t:dgCol title="common.role.code" field="roleCode"></t:dgCol>
		<t:dgCol title="common.status" field="status" replace="common.status.role.enable_1,common.status.role.disable_0"></t:dgCol>
		<t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
		<t:dgCol title="common.createtime" field="createDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
		<t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
		<t:dgCol title="common.updatetime" field="updateDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
		<t:dgCol title="common.operation" field="opt"></t:dgCol>
		<t:dgFunOpt funname="lockObj(id)" exp="status#eq#1" title="common.lock.role"></t:dgFunOpt>
		<t:dgFunOpt funname="unlockObj(id)" exp="status#eq#0" title="common.unlock.role"></t:dgFunOpt>
		<t:dgFunOpt funname="delRole(id)" title="common.delete"></t:dgFunOpt>
		<t:dgFunOpt funname="userListbyrole(id,roleName,status)" title="common.user"></t:dgFunOpt>
		<t:dgFunOpt funname="setfunbyrole(id,roleName)" title="permission.set"></t:dgFunOpt>
		<t:dgToolBar title="common.add.param" langArg="common.role" icon="icon-add" url="roleController.do?addorupdate" funname="add"></t:dgToolBar>
		<t:dgToolBar title="common.edit.param" langArg="common.role" icon="icon-edit" url="roleController.do?addorupdate" funname="update"></t:dgToolBar>
		<t:dgToolBar title="excelImport" icon="icon-put" funname="ImportXls"></t:dgToolBar>
		<t:dgToolBar title="excelOutput" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
		<t:dgToolBar title="templateDownload" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
	</t:datagrid>
	</div>

</div>
<!-- <div id="tt"></div> -->

<script type="text/javascript">

//点击权限设置 触发函数
function setfunbyrole(id,roleName) {
	//弹出右侧隐藏菜单列表
	$("#main_role_list").layout("expand","east");
	
	//加载菜单列表数据
	$("#function-panel").panel(
		{
			title :roleName+ ':' + '<t:mutiLang langKey="current.permission"/>',
			href:"roleController.do?fun&roleId=" + id
		}
	);
	//刷新菜单列表
	$('#function-panel').panel("refresh" );
	
}
//update-start--Author:gaofeng Date:20140822 for：查看角色的所有用户信息

//点击用户 触发函数
function userListbyrole(id,roleName,status) {
	//弹出右侧隐藏用户列表
	$("#main_role_list").layout("expand","east");
	
	//加载用户列表数据
	$("#function-panel").panel(
		{
			title :roleName+ ':' + '<t:mutiLang langKey="common.user"/>',
			href:"roleController.do?userList&roleId=" + id +"&status=" + status
		}
	);
	//刷新用户列表
	$('#function-panel').panel("refresh" );
	
}
//update-end--Author:gaofeng Date:20140822 for：查看角色的所有用户信息
//删除角色
function delRole(id){
	var judgeUrl= 'roleController.do?judgeDelRole&id='+id;
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
				var msg = d.msg;
				if(msg != ""){
					//可以删除，但存在关联用户，需用户确认后才可继续删除
					var tabName= 'roleList';
					var url= 'roleController.do?delRole&id='+id;
					$.dialog.confirm(msg, function(){
						doSubmit(url,tabName);
						rowid = '';
						$("#function-panel").html("");//删除角色后，清空对应的权限
					}, function(){
					});
				}else {
					//可以删除，且不存在关联用户
					var tabName= 'roleList';
					var url= 'roleController.do?delRole&id='+id;
					$.dialog.confirm('<t:mutiLang langKey="confirm.delete.this.record"/>', function(){
						doSubmit(url,tabName);
						rowid = '';
						$("#function-panel").html("");//删除角色后，清空对应的权限
					}, function(){
					});
				}
				
			} else {
				//不可删除
				tip(d.msg);
			}
		}
	});
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'roleController.do?upload', "roleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("roleController.do?exportXls", "roleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("roleController.do?exportXlsByT", "roleList");
}

function lockObj(id) {

	var url = "roleController.do?lock&lockvalue=0&id=" + id;
	$.dialog.confirm('<t:mutiLang langKey="common.lock.role.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}

function unlockObj(id) {
	var url = "roleController.do?lock&lockvalue=1&id=" + id;
	$.dialog.confirm('<t:mutiLang langKey="common.unlock.role.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}
function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
			
		}
	});
}
</script>
