<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>公式区划范围选择</title>
<%
	String tablename = request.getParameter("tablename");	
	String formulaname = request.getParameter("formulaname");  
%>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
$(function(){
	var init=false;
	$('#regiontree').tree(
			{
				animate : true,
				url : 'defController.do?tree&tablename=<%=tablename%>&formulaname=<%=formulaname%>',
				checkbox : true,
				onCheck: function(node){
					if (init){
						setRegion();
					}
				},
				onLoadSuccess:function(){
					setRegion();
					init=true;
				}
			});	
});
function setRegion() {
	var nodes = $('#regiontree').tree('getChecked');
	var nodeids = '';
	var nodetexts = '';
	for (var i = 0; i < nodes.length; i++) {
		nodeids += nodes[i].id + ",";
		nodetexts += nodes[i].text + ",";
	}
	$("#nodes").val(nodeids);
	$("#nodestext").val(nodetexts);
	/*$.ajax({
		url : "defController.do?saveRegion&tablename=" + tablenameold
				+ "&formulaname=<%=formulaname%>'&nodes=" + nodeids, //后台webservice里的方法名称 %2b 
		type : "get",
		dataType : "json",
		contentType : "application/json",
		traditional : true,
		success : function(data) {
			if (data[0].success != "true")
				alert(data[0].erro);
		},
		error : function(msg) {
			alert("出错了！");
		}
	});*/
}
</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<!-- <div class="easyui-layout" fit="true">
	<div region="west" style="width: 100%;" title="公式选择" split="true" collapsed="false"> -->
	<div class="easyui-panel" style="padding: 0px; border: 0px" fit="true"
		border="true">
		<ul id="regiontree">
		</ul>
	</div>
	<!--</div>
			</div> -->
	<t:formvalid formid="formobj" layout="div" dialog="true"
		action="defController.do?saveRegion" callback="regionTextV">
		<fieldset class="step">
		<input type="hidden" name="tablename" id="tablename"
			value="${tablename}">
		<input type="hidden" name="formulaname" id="formulaname"
			value="${formulaname}">
		<input type="hidden" name="nodes" id="nodes" value="">
		<input type="hidden" name="nodestext" id="nodestext" value="">

</fieldset>
	</t:formvalid>
</body>
</html>
