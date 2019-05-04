<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><t:mutiLang langKey="common.process.params" /></title>
<t:base type="jquery,easyui,tools"></t:base>
<script language="javascript">
$(function(){
	$('#nodemenutree').tree({
		url:"processController.do?GetTaskNodeMenu",
		onSelect:function(node){
			if ($('#nodemenutree').tree('isLeaf', node.target)){
				$('#modelandview').val(node.id);
			}
		}		
	});
})

</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" layout="div" action="processController.do?saveNode">
	<input name="id" type="hidden" value="${processnode.id}">
	<input name="TPProcess.id" type="hidden" value="${processid}">
	<fieldset class="step"><legend> <t:mutiLang langKey="common.node.info" /> </legend>
	<div class="form"><label class="Validform_label"> <span style="color:red;">*</span><t:mutiLang langKey="common.node.name" />: </label> <input name="processnodename" value="${processnode.processnodename }" class="inputxt" datatype="s3-50"> <span class="Validform_checktip"><t:mutiLang langKey="nodename.rang3to50" /></span></div>
	<div class="form"><label class="Validform_label"> <t:mutiLang langKey="common.node.code" />: </label> <input name="processnodecode" value="${processnode.processnodecode}" class="inputxt"></div>
	<div class="form"><label class="Validform_label"> <t:mutiLang langKey="common.node.form_pc" />: </label> 
	<input id="modelandview" name="modelandview" value="${processnode.modelandview}" class="inputxt" type="hidden" width: 400px">
	<div style="height: 100px; vertical-align: middle; margin:10px 0 0 10px;  background: #F4F4F4; overflow:auto">
	<ul id="nodemenutree"></ul> </div>	
	</div>
	
	<div class="form"><label class="Validform_label"> <t:mutiLang langKey="common.node.form_mobile" />: </label> <input name="modelandviewMobile" value="${processnode.modelandviewMobile}" class="inputxt" style="width: 400px"></div>
	<div class="form"><label class="Validform_label"><span style="color:red;">*</span> <t:mutiLang langKey="common.node.timeout" />: </label> <input name="nodeTimeout" value="${processnode.nodeTimeout}" class="inputxt" datatype="n1-3"> </div>
	<%--  表单不再使用，全部采用链接的方式配置
    <div class="form">
     <label class="Validform_label">
      内部表单:
     </label>
     <select name="fromid" style="width:140px">
      <option value="0">-请选择内部表单-</option>
      <c:forEach items="${formList }" var="form">
       <option value="${form.id }" <c:if test="${form.id==processnode.TPForm.id}">selected="selected"</c:if>>
        ${form.formname }
       </option>
      </c:forEach>
     </select>
    </div>
     --%></fieldset>
</t:formvalid>
</body>
</html>
