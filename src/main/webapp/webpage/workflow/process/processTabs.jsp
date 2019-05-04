<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<t:tabs id="tt" iframe="false" tabPosition="bottom">
 <t:tab href="processController.do?processnode&processid=${processid}" icon="icon-search" title="common.process.node" id="pnode"></t:tab>
 <t:tab href="processController.do?processpro&processid=${processid }" icon="icon-search" title="common.process.variable" id="ppro"></t:tab>
 <t:tab href="processController.do?busbase&processid=${processid }" icon="icon-search" title="common.process.buss" id="bpro"></t:tab>
</t:tabs>
