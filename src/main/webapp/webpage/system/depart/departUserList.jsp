<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="departUserList" 
            actionUrl="departController.do?userDatagrid&departid=${departid}" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="userName" query="true"></t:dgCol>
	<t:dgCol title="common.real.name" field="realName" query="true"></t:dgCol>
	<t:dgCol title="common.status" sortable="true" field="status" replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
	
	<!--是否显示删除操作  1为显示 0为隐藏  默认 为0-->
	<c:if test="${isShowAddOrDelUser == '1'}">
	
		<t:dgCol title="common.operation" field="opt"></t:dgCol>	
		<t:dgFunOpt funname="delUserRole(id)" title="common.delete"></t:dgFunOpt>
	</c:if>

	<!--是否显示新增按钮 1为显示 0为隐藏  默认为0-->
	<c:if test="${isShowAdd == '1'}">
	
		<t:dgToolBar title="common.add.param" langArg="common.user" icon="icon-add" url="userController.do?addorupdate&departid=${departid}" funname="add" height="450"></t:dgToolBar>

	</c:if>
	<!--是否显示新增按钮 1为显示 0为隐藏   默认为0-->
	<c:if test="${isShowUpdate == '1'}">
	
		<t:dgToolBar title="common.edit.param" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate&departid=${departid}" funname="update"></t:dgToolBar>

	</c:if>
	 <%--update-start--Author:zhangguoming  Date:20140826 for：添加有客户--%>
	 <!--组织机构与用户关系       0 为 显示   1 为隐藏，默认 0 显示  -->
	<c:if test="${isShowAddOrDelUser == '1'}">
	
		<t:dgToolBar title="common.add.exist.user" icon="icon-add" url="departController.do?goAddUserToOrg&orgId=${departid}" funname="add" width="500"></t:dgToolBar>
	
	</c:if>
	
    <%--update-end--Author:zhangguoming  Date:20140826 for：添加有客户--%>
</t:datagrid>

<script type="text/javascript">
	function delUserRole(id){
		var did = "${departid}";
		var judgeUrl= "departController.do?judgeDelUserRole&userId=" + id + "&orgId=" + did;
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
    			
    				if(d.msg != ""){
    					//可以删除，但存在关联用户，需用户确认后才可继续删除
    					
    					var tabName= 'departUserList';
    					var url= "departController.do?deleteUserOrg&userId=" + id + "&orgId=" + did;
    					$.dialog.confirm(d.msg, function(){
    						
    						doSubmit(url,tabName);
    						
    						rowid = '';
    						
    					}, function(){
    					});
    				}else {
    					//可以删除，且不存在关联用户
    					var tabName= 'departUserList';
    					var url= "departController.do?deleteUserOrg&userId=" + id + "&orgId=" + did;
    					$.dialog.confirm('<t:mutiLang langKey="confirm.delete.this.record"/>', function(){
    						doSubmit(url,tabName);
    						rowid = '';
    						
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
</script>