<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<%--非当前角色的用户列表--%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_role_list" class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="noCurRoleUserList" title="common.operation"
                    actionUrl="roleController.do?addUserToRoleList&roleId=${param.roleId}" fit="true" fitColumns="true"
                    idField="id" checkbox="true" queryMode="group">
            <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="common.username" sortable="false" field="tsUser.userName" query="true"></t:dgCol>
            <t:dgCol title="common.real.name" sortable="false" field="tsUser.realName" query="true"></t:dgCol>
            <t:dgCol title="common.status" sortable="false" field="tsUser.status" replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
            <t:dgCol title="common.department" sortable="false" field="tsDepart.departname" query="false"></t:dgCol>
        </t:datagrid>
    </div>
</div>

<div style="display: none">
    <t:formvalid formid="formobj" layout="div" dialog="true" action="roleController.do?doAddUserToRole&roleId=${param.roleId}" beforeSubmit="setUserOrgIds">
        <input id=userOrgIds name="userOrgIds">
    </t:formvalid>
</div>
<script>
    function setUserOrgIds() {
        $("#userOrgIds").val(getUserListSelections('id'));
        return true;
    }

    function getUserListSelections(field) {
        var ids = [];
        var rows = $('#noCurRoleUserList').datagrid('getSelections');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i][field]);
        }
        ids.join(',');
        return ids
    }
</script>
