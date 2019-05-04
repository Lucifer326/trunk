<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<!DOCTYPE html>
<html>
 <head>
  <title><t:mutiLang langKey="common.process.params"/></title>
  <t:base type="jquery,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="div" action="processController.do?savePro">
   <input name="id" type="hidden" value="${processpro.id }">
   <input name="TPProcess.id" type="hidden" value="${processid}">
   <fieldset class="step">
    <legend>
     <t:mutiLang langKey="common.process.params"/>
    </legend>
    <div class="form">
      <label class="Validform_label">
      <t:mutiLang langKey="common.variable.code"/>:
     </label>
     <input name="processprokey" value="${processpro.processprokey }" datatype="s3-20" class="inputxt">
     <span class="Validform_checktip"><t:mutiLang langKey="variablecode.range.3to20"/></span>
    </div>
     <div class="form">
      <label class="Validform_label">
      <t:mutiLang langKey="common.variable.name"/>:
     </label>
     <input name="processproname" class="inputxt" value="${processpro.processproname}" >
    </div>
    <div class="form">
     <label class="Validform_label">
      <t:mutiLang langKey="common.variable.type"/>:
     </label>
     <select name="processprotype">
      <option value="default" <c:if test="${processpro.processprotype=='default'}">selected="selected"</c:if>>
        <t:mutiLang langKey="common.variable.type.normal"/>
      </option>
      <option value="opt" <c:if test="${processpro.processprotype=='opt'}">selected="selected"</c:if>>
      <t:mutiLang langKey="common.variable.type.control"/>
      </option>
     </select>
    </div>
    <div class="form">
      <label class="Validform_label">
      <t:mutiLang langKey="common.variable.node"/>:
     </label>
     <select name="TPProcessnode.id">
      <c:forEach items="${nodeList}" var="node">
       <option value="${node.id}" <c:if test="${node.id==processpro.TPProcessnode.id}">selected="selected"</c:if>>
        ${node.processnodename}
       </option>
      </c:forEach>
     </select>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
