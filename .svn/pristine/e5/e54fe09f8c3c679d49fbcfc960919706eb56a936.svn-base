<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><t:mutiLang langKey="common.release.info" /></title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true"
		action="releaseController.do?saveRelease">
		<c:if test="${release.id != null}">
			<input name="id" type="hidden" value="${release.id}">
		</c:if>
		<input name="isUse" type="hidden" value="${release.isUse}">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.release.groupname" />:
				</label> <input name="groupname" id="groupname"
					class="inputxt" value="${release.groupname }" datatype="*2-50">
				<span class="Validform_checktip"><t:mutiLang
						langKey="releasegroupnamescope.rang2to50.notnull" /></span>
			</div>
			<div class="form">
				<t:dictSelect field="cache" typeGroupCode="ffffff" type="radio"></t:dictSelect>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.release.name" />:
				</label> <input name="name" id="name"
					class="inputxt" value="${release.name }" datatype="*2-50">
				<span class="Validform_checktip"><t:mutiLang
						langKey="releasenamescope.rang2to50.notnull" /></span>
			</div>
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.release.version" />:
				</label> <input name="version" id="version"
					class="inputxt" value="${release.version }" datatype="*2-50">
				<span class="Validform_checktip"><t:mutiLang
						langKey="releaseversionscope.rang2to50.notnull" /></span>
			</div>
			<c:if test="${release.id!=null }">
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.status" />:
				</label> 
				<c:if test="${release.isUse=='0' }">
					<span class="Validform_checktip"><t:mutiLang
							langKey="common.status.role.disable" /></span>
				</c:if>
				<c:if test="${release.isUse=='1' }">
					<span class="Validform_checktip"><t:mutiLang
							langKey="common.status.role.enable" /></span>
				</c:if>
			</div>
			</c:if>

		</fieldset>
	</t:formvalid>
	
</body>
</html>
