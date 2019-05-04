<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>系统配置绑定行政区划表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bssysDeploydisController.do?doAdd" tiptype="1">
		<input id="id" name="id" type="hidden"
			value="${bssysDeploydisPage.id }">
		<input id="sort" name="sort" type="hidden"
			value="${bssysDeploydisPage.sort }">
		<input id="createBy" name="createBy" type="hidden"
			value="${bssysDeploydisPage.createBy }">
		<input id="createName" name="createName" type="hidden"
			value="${bssysDeploydisPage.createName }">
		<input id="createDate" name="createDate" type="hidden"
			value="${bssysDeploydisPage.createDate }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${bssysDeploydisPage.updateBy }">
		<input id="updateName" name="updateName" type="hidden"
			value="${bssysDeploydisPage.updateName }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${bssysDeploydisPage.updateDate }">
		<input id="isdeleted" name="isdeleted" type="hidden"
			value="${bssysDeploydisPage.isdeleted }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${bssysDeploydisPage.sysOrgCode }">
		<input id="disId" name="disId" type="hidden"
			value="${systemRegionPage.id }">
		<input id="disCode" name="disCode" type="hidden"
			value="${systemRegionPage.regionCode }">
		<input id="deployId" name="deployId" type="hidden"
			value="${bssysDeployPage.id }">
		<input id="controlType" name="controlType" type="hidden"
			value="${bssysDeployPage.controlType }">
		<input id="valuebak" name="valuebak" type="hidden"
			value="${bssysDeployPage.value }">
		<input id="dateExpr" name="dateExpr" type="hidden"
			value="${bssysDeployPage.dateExpr }">
		<table class="table-easyui" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td style="width: 20%;"><label class="Validform_label">行政区划:</label>
					</td>
					<td><label>${systemRegionPage.regionName }</label></td>
				</tr>
				<tr>
					<td><label class="Validform_label">配置名称:</label></td>
					<td><label>${bssysDeployPage.name }</label></td>
				</tr>
				<tr>
					<td><label class="Validform_label">说明:</label></td>
					<td><label>${bssysDeployPage.description }</label></td>
				</tr>
				<tr>
					<td><label class="Validform_label">配置值:</label></td>
					<td><c:choose>
							<c:when test="${bssysDeployPage.controlType==2}">
								<input id="value_int" name="value" type="text"
									style="width: 400px" class="inputxt" datatype="n1-50"
									value='${bssysDeployPage.value}'>
							</c:when>
							<c:when test="${bssysDeployPage.controlType==3}">
								<select id="value_select" name="value" style="width: 400px"
									value="${bssysDeployPage.value}"></select>
							</c:when>
							<c:when test="${bssysDeployPage.controlType==4}">
								<div id="div_date">
									<input id="value_date" name="value" type="text"
										style="width: 400px" class="easyui-datebox"
										value='${bssysDeployPage.value}' nullmsg="请填写配置日期！">
								</div>
							</c:when>
							<c:otherwise>
								<!-- 1：字符型 -->
								<textarea id="value_txt" name="value" style="width: 400px"
									class="inputxt" datatype="s1-50" rows="3">${bssysDeployPage.value}</textarea>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>
<script src="webpage/com/jeecg/bssys_deploy/bssysDeploy.js"></script>
<script>
	$(document).ready(function() {
		ShowControl();
	});
</script>