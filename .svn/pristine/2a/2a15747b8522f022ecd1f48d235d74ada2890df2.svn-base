<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统配置表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="" tiptype="1">
		<input id="id" name="id" type="hidden" value="${bssysDeployPage.id }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${bssysDeployPage.updateBy }">
		<input id="updateName" name="updateName" type="hidden"
			value="${bssysDeployPage.updateName }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${bssysDeployPage.updateDate }">
		<input id="isdeleted" name="isdeleted" type="hidden"
			value="${bssysDeployPage.isdeleted }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${bssysDeployPage.sysOrgCode }">
		<input id="dateExpr" name="dateExpr" type="hidden"
			value="${bssysDeployPage.dateExpr }">
		<%-- <input id="parentCode" name="parentCode" type="hidden"
			value="${bssysDeployPage.parentCode }"> --%>
		<input id="code" name="code" type="hidden"
			value="${bssysDeployPage.code }">
		<input id="valuebak" name="valuebak" type="hidden"
			value="${bssysDeployPage.value }">
		<input id="controlType" name="controlType" type="hidden"
			value='${bssysDeployPage.controlType}'>
		<input id="isBinddisbak" name="isBinddisbak" type="hidden"
			value="${bssysDeployPage.isBinddis }">

		<table class="table-easyui" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td style="width:20%;"><label >配置名称:</label></td>
					<td><label >${bssysDeployPage.name}</label></td>
				</tr>
				<tr>
					<td><label >配置编号:</label></td>
					<td><label >${bssysDeployPage.code}</label></td>
				</tr>
				<tr>
					<td><label >说明:</label></td>
					<td><label>${bssysDeployPage.description }</label></td>
				</tr>
				<c:if test="${not empty bssysDeployPage.controlType }">
					<tr>
						<td><label >配置值:</label></td>
						<td><label>${bssysDeployPage.value }</label></td>
					</tr>
					<tr>
						<td><label >是否绑定区划:</label></td>
						<td>
							<label><c:if test="${bssysDeployPage.isBinddis == 1}">是</c:if>
							<c:if test="${bssysDeployPage.isBinddis == 0}">否</c:if>
							</label>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</t:formvalid>
</body>
	