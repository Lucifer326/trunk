<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>测试表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="testController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${testPage.id }">
					<input id="createName" name="createName" type="hidden" value="${testPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${testPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${testPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${testPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${testPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${testPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${testPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${testPage.sysCompanyCode }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  datatype="*" value='${testPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="sex" type="radio"
										typeGroupCode="sex" defaultVal="${testPage.sex}" hasLabel="false"  title="性别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/jeecg/demo/test/test.js"></script>		
