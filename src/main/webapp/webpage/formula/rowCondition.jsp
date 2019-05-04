<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>行范围条件设置</title>
<%
	String tablename = request.getParameter("tablename");
	String formulaname = request.getParameter("formulaname");
%>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
var taclick = 0;
var expressstart = 0;
$(function(){
	$('#dicttree').tree(
			{
				animate : true,
				url : 'defController.do?getDictColAndItem&tablename=<%=tablename%>&formulaname=<%=formulaname%>',
				onDblClick : function(node) {
					var tree2selectLevel = 1;
					var tar2 = node;
					while(tar2.id!="#"){
						tree2selectLevel++;
						tar2 = $('#dicttree').tree('getParent',
								tar2.target);
					}
					if (tree2selectLevel==1)
						return;
					if(tree2selectLevel==2){
						setexpress("[" + node.text + "]");
					}else if(tree2selectLevel==3){
						setexpress("［" + $('#dicttree').tree('getParent',node.target).text+"_"+node.text + "］");
					}
				}
			});
	});
function setexpress(a) {
	var extendsblank = " " + a + " ";
	var ta = document.getElementById("dictexpress");
	if (ta.selectionStart != 0)
		expressstart = ta.selectionStart;
	var old = $("#dictexpress").val();
	if (expressstart == 0)
		ta.innerText = extendsblank;
	else
		ta.innerText = old.substring(0, expressstart) + extendsblank
				+ old.substring(expressstart, old.length);
	ta.value = old.substring(0, expressstart) + extendsblank
			+ old.substring(expressstart, old.length);
	if (taclick == 0)
		expressstart = expressstart + (extendsblank).length;
	else {
		taclick = 0;
	}
	moveCursor(expressstart);
}

function moveCursor(s1) {
	var oTextarea = document.getElementById("dictexpress");
	var start = s1;
	var end = s1;
	if (isNaN(start) || isNaN(end)) {
		alert("位置输入错误");
	}
	if (1 == 2) { //IE模式未调试
		var oTextRange = oTextarea.createTextRange();
		var LStart = start;
		var LEnd = end;
		var start = 0;
		var end = 0;
		var value = oTextarea.value;
		for (var i = 0; i < value.length && i < LStart; i++) {
			var c = value.charAt(i);
			if (c != '\n') {
				start++;
			}
		}
		for (var i = value.length - 1; i >= LEnd && i >= 0; i--) {
			var c = value.charAt(i);
			if (c != '\n') {
				end++;
			}
		}
		oTextRange.moveStart('character', start);
		oTextRange.moveEnd('character', -end);
		//oTextRange.collapse(true);  
		oTextRange.select();
		oTextarea.focus();
	} else {
		oTextarea.select();
		oTextarea.selectionStart = start;
		oTextarea.selectionEnd = end;
	}
}

</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<table style="width: 100%;height:390px" cellpadding="1" cellspacing="0" border="1"
		bordercolor="#CCEEFF">
			<tr>
				<td style="width: 50%;height:100%;vertical-align: top;">
						<ul id="dicttree"></ul>
				</td>
				<td>
						<t:formvalid formid="formobj" layout="div" dialog="true"
		action="defController.do?saveRowCondition" callback="rowCoditionTextV">
		<fieldset class="step"> 
			<input type="hidden" name="tablename" id="tablename"
				value="${tablename}"> <input type="hidden"
				name="formulaname" id="formulaname" value="${formulaname}">
			<input type="hidden" name="nodes" id="nodes" value="">
<textarea name="dictexpress" cols=70 rows=20
						id="dictexpress" style="height:100%" ></textarea>
		</fieldset> 
	</t:formvalid></td>
			</tr>
	</table>
	
</body>
</html>
