<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="roleUserList" title="common.operation"
            actionUrl="roleController.do?roleUserDatagrid&roleId=${roleId}"  idField="id" queryMode="group" fit="true">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="TSUser.userName" query="true"></t:dgCol>
	<t:dgCol title="common.real.name" sortable="false"  field="TSUser.realName" query="true"></t:dgCol>
	<t:dgCol title="common.status" sortable="true" field="TSUser.status" replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
	<t:dgCol title="common.department" sortable="false"  field="tSDepart.departname" query="false"></t:dgCol>
	<t:dgCol title="common.operation" field="opt"></t:dgCol>
	<t:dgDelOpt title="common.delete" url="roleController.do?delRoleUser&id={id}" />
	<c:if test="${addUserFlag==1 }">
		<t:dgToolBar title="common.add.param" langArg="common.user" icon="icon-add" url="userController.do?addorupdate&" funname="add" width="820" height="460"></t:dgToolBar>
	</c:if>
	<c:if test="${editUserFlag==1 }">
		<t:dgToolBar title="common.edit.param" langArg="common.user" icon="icon-edit" url="userController.do?editUserFromRole" funname="update"></t:dgToolBar>
	</c:if>
	<!--对于已禁用的角色隐藏添加已有用户按钮  -->
	<c:if test="${status ==1 }">
		<t:dgToolBar title="common.add.exist.user"  icon="icon-add" url="roleController.do?goAddUserToRole&roleId=${roleId}" funname="add" width="500"></t:dgToolBar>	
	</c:if>
</t:datagrid>

