<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户代理人设置</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
	function openUserSelect() {
		$.dialog({
			content : 'url:userAgentController.do?userSelect',
			zIndex : 9999999,
			title : '用户列表',
			lock : true,
			width : '1000px',
			height : '600px',
			opacity : 0.4,
			button : [ {
				name : '确定',
				callback : callbackUserSelect,
				focus : true
			}, {
				name : '取消',
				callback : function() {
				}
			} ]
		}).zindex();
	}
	function callbackUserSelect() {
		var iframe = this.iframe.contentWindow;
		var rowsData = iframe.$('#userList1').datagrid('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
			return;
		}
		var userName = rowsData[0].userName;
		$('#agentUserName').val(userName);
		$('#agentUserName').blur();
	}
	//清空代理用户
	function callbackClean() {
		$('#agentUserName').val('');
	}
	function openUserSelect1() {
		$.dialog({
			content : 'url:userAgentController.do?userSelect',
			zIndex : 9999999,
			title : '用户列表',
			lock : true,
			width : '1000px',
			height : '600px',
			opacity : 0.4,
			button : [ {
				name : '确定',
				callback : callbackUserSelect1,
				focus : true
			}, {
				name : '取消',
				callback : function() {
				}
			} ]
		}).zindex();
	}
	function callbackUserSelect1() {
		var iframe = this.iframe.contentWindow;
		var rowsData = iframe.$('#userList1').datagrid('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
			return;
		}
		var userName = rowsData[0].userName;
		$('#userName').val(userName);
		$('#userName').blur();
	}
	//清空代理用户
	function callbackClean1() {
		$('#userName').val('');
	}
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="userAgentController.do?doAddOrUpdate">
		<input id="id" name="id" type="hidden" value="${tSUserAgent.id }">
		<input id="createName" name="createName" type="hidden"
			value="${tSUserAgent.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${tSUserAgent.createBy }">
		<input id="createDate" name="createDate" type="hidden"
			value="${tSUserAgent.createDate }">
		<input id="updateName" name="updateName" type="hidden"
			value="${tSUserAgent.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${tSUserAgent.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${tSUserAgent.updateDate }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${tSUserAgent.sysOrgCode }">
		<input id="sysCompanyCode" name="sysCompanyCode" type="hidden"
			value="${tSUserAgent.sysCompanyCode }">
		<table style="width: 700px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="15%"><label class="Validform_label">
						用户名: </label></td>
				<td class="value"><input id="userName" name="userName"
					type="text" style="width: 170px; height: 20px" class="inputxt"
					readonly="readonly" value='${userName}'> <c:if
						test="${realName eq'admin'}">
						<a href="#" class="easyui-linkbutton" plain="true"
							icon="icon-search" id="agentUserSearch"
							onclick="openUserSelect1()">选择</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							icon="icon-redo" id="agentUserRedo" onclick="callbackClean1()">清空</a>
					</c:if> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">用户名</label></td>
			<tr>
				<td align="right"><label class="Validform_label">
						代理人用户名: </label></td>
				<td class="value"><input id="agentUserName"
					name="agentUserName" type="text" style="width: 170px; height: 20px"
					class="inputxt" value='${tSUserAgent.agentUserName}'
					readonly="readonly" datatype="*"> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-search"
					id="agentUserSearch" onclick="openUserSelect()">选择</a> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-redo"
					id="agentUserRedo" onclick="callbackClean()">清空</a> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">代理人用户名</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">
						代理开始时间: </label></td>
				<td class="value"><input id="startTime" name="startTime"
					type="text" style="width: 170px; height: 20px" class="Wdate"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s'})"
					value='<fmt:formatDate value='${tSUserAgent.startTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'
					datatype="*"> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">代理开始时间</label></td>
			<tr>
				<td align="right"><label class="Validform_label">
						代理结束时间: </label></td>
				<td class="value"><input id="endTime" name="endTime"
					type="text" style="width: 170px; height: 20px" class="Wdate"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"
					value='<fmt:formatDate value='${tSUserAgent.endTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'
					datatype="*"> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">代理结束时间</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 状态: </label>
				</td>
				<td class="value">
					<%-- <select id="status" name="status">
							  <option value="0" <c:if test="${tSUserAgentPage.status eq '0'}">selected</c:if>>无效</option>
							  <option value="1" <c:if test="${tSUserAgentPage.status eq '1'}">selected</c:if>>有效</option>
							</select> --%> <input name="status" type="radio" datatype="*"
					<c:if test="${tSUserAgent.status eq 1}">checked="true"</c:if>
					value="1" checked="checked"> 有效 <input name="status"
					type="radio"
					<c:if test="${tSUserAgent.status eq 0}">checked="true"</c:if>
					value="0"> 无效
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>