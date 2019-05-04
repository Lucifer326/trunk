<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>SQL计算器定义</title>
<%
	String tablename = request.getParameter("tablename");
	String formulaname = request.getParameter("formulaname");
%>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
var url = "";
$(function(){
	$('#sqlcalctree').tree(
			{
				animate : true,
				url : 'defController.do?getSQLCalcName&tablename=<%=tablename%>&formulaname=<%=formulaname%>',
				onClick : function(node) {
					if (node.id=='#'){
						var ta = document.getElementById("sqlcalcexpress");
						ta.innerText = "";
						ta.value = "";
						return;
					}
					$.ajax({
						url : "defController.do?getSQLCalcExpress&sqlcalcname="
								+ node.text, //后台webservice里的方法名称 %2b 
						type : "get",
						dataType : "json",
						contentType : "application/json",
						traditional : true,
						success : function(data) {
							var ta = document.getElementById("sqlcalcexpress");
							ta.innerText = data.msg;
							ta.value = data.msg;
						},
						error : function(msg) {
							alert("出错了！");
						}
					});
				}
			});
	});
	function formCommit(){
		var sqlexpress = encodeURIComponent($("#sqlcalcexpress").val());
		var sqlcalcname = $('#sqlcalctree').tree('getSelected').text;
		$.ajax({
			url : "defController.do?saveCalc&tablename=<%=tablename%>"
					+ "&sqlexpress=" + sqlexpress + "&sqlcalcname="
					+ sqlcalcname, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					return true;
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function setvar(){
		var sqlexpress = encodeURIComponent($("#sqlcalcexpress").val());
		var sqlcalcname = $('#sqlcalctree').tree('getSelected').text;
		var url1 = "";//"defController.do?saveCalc&tablename=<%=tablename%>&sqlexpress=" + sqlexpress + "&sqlcalcname="+ sqlcalcname;
		alert(url1);
	}
</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<table style="width: 100%;height:350px" cellpadding="1" cellspacing="0" border="1"
		bordercolor="#CCEEFF">
			<tr>
				<td style="width: 50%;height:100%;vertical-align: top;">
						<ul id="sqlcalctree"></ul>
				</td>
				<td>
						<t:formvalid formid="formobj" layout="div" dialog="true"
		action="defController.do?saveCalc&tablename=222">
		<fieldset class="step"> 
			<input type="hidden" name="tablename" id="tablename"
				value="${tablename}"> <input type="hidden"
				name="formulaname" id="formulaname" value="${formulaname}">
			<input type="hidden" name="nodes" id="nodes" value="">
<textarea name="sqlcalcexpress" cols=68 rows=21
						id="sqlcalcexpress" style="height:100%" ></textarea>
		</fieldset> 
	</t:formvalid></td>
			</tr>
	</table>
	
</body>
</html>
