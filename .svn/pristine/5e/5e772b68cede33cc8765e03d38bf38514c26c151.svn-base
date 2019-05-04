<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><t:mutiLang langKey="common.role.info" /></title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true"
		action="roleController.do?saveRole">
		<c:if test="${role.id != null}">
			<input name="id" type="hidden" value="${role.id}">
		</c:if>
		<input name="status" type="hidden" value="${role.status}">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.role.name" />:</label> 
				<input name="roleName" ajaxurl="roleController.do?checkRoleName&name=${role.roleName }"
					class="inputxt" value="${role.roleName }" datatype="*2-50" id="roleName" >
				<span class="Validform_checktip"><t:mutiLang
						langKey="rolenamescope.rang2to50.notnull" /></span>
			</div>
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.role.code" />:
				</label> <input name="roleCode" id="roleCode"
					ajaxurl="roleController.do?checkRole&code=${role.roleCode }"
					class="inputxt" value="${role.roleCode }" datatype="*2-50">
				<span class="Validform_checktip"><t:mutiLang
						langKey="rolecodescope.rang2to50.notnull" /></span>
			</div>
			<c:if test="${role.id!=null }">
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.status" />:
				</label> 
				<c:if test="${role.status=='0' }">
					<span class="Validform_checktip"><t:mutiLang
							langKey="common.status.role.disable" /></span>
				</c:if>
				<c:if test="${role.status=='1' }">
					<span class="Validform_checktip"><t:mutiLang
							langKey="common.status.role.enable" /></span>
				</c:if>
			</div>
			</c:if>

		</fieldset>
	</t:formvalid>
</body>
</html>
