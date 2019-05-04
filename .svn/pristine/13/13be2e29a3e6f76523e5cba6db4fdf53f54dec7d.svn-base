<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title><t:mutiLang langKey="common.buss.info"/></title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="processController.do?saveBus">
   <input name="id" type="hidden" value="${busbase.id}">
   <input name="TPProcess.id" type="hidden" value="${processid}">
   <fieldset class="step">
    <div class="form">
      <label class="Validform_label" style="width: 100px">
      	<t:mutiLang langKey="common.buss.name"/>:
     </label>
     <input name="busname" value="${busbase.busname }" datatype="s2-50" class="inputxt">
     <span class="Validform_checktip"><t:mutiLang langKey="bussname.range.2to50"/></span>
    </div>
    
     <div class="form">
	      <label class="Validform_label" style="width: 100px">
	      	<t:mutiLang langKey="common.buss.form.type"/>:
	      </label>
	      <t:dictSelect field="formType" typeGroupCode="bpm_form_type" hasLabel="false" defaultVal="${busbase.formType }"></t:dictSelect>
    </div>
    
    
    <div class="form" id="tstable_div" <c:if test="${busbase.formType eq 'online'}">style="display: none"</c:if>  >
     <label class="Validform_label" style="width: 100px">
      	<t:mutiLang langKey="common.buss.entity.define"/>:
     </label>
     <select name="TSTable.id">
     		 <option value=""><t:mutiLang langKey="common.please.select"/></option>
	       <c:forEach items="${tableList }" var="table">
	     	 <option value="${table.id}" <c:if test="${table.id eq busbase.TSTable.id}">selected="selected"</c:if>>
	     	 ${table.tableTitle}
	      	</option>
	      </c:forEach>
     </select>
    </div>
    
    <div class="form" id="onlineId_div" <c:if test="${busbase.formType eq 'entity'}">style="display: none"</c:if> >
	      <label class="Validform_label" style="width: 100px">
	      	<t:mutiLang langKey="common.buss.entity.online"/>:
	      </label><!-- validType="t_s_busconfig,online_id,id"  ignore="ignore"-->
	      <input name="onlineId" value="${busbase.onlineId }"  datatype="s2-50" class="inputxt">
	      <span class="Validform_checktip"><t:mutiLang langKey="onlinetable.range.2to50"/></span>
    </div>
    
    <div class="form">
      <label class="Validform_label" style="width: 100px">
      	业务标题表达式:
     </label>
     <input name="busTitleExp" value="${busbase.busTitleExp }" datatype="*2-150" class="inputxt" ignore="ignore">
     <span class="Validform_checktip">参考：XXXX【<%="${busname}"%>】-XXXX【<%="${name}"%>】</span>
    </div>
    <div class="form">
      <label class="Validform_label" style="width: 100px">
      	流程办理风格:
      </label>
      <select name="processDealStyle">
     		 <option value=""><t:mutiLang langKey="common.please.select"/></option>
     		<c:forEach items="${procDealStyleList }" var="style">
	     	 <option value="${style.code }" <c:if test="${busbase.processDealStyle eq style.code}">selected="selected"</c:if>>${style.name }</option>
	     	</c:forEach>
     </select>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>


<script type="text/javascript">
<!--
$("select[name='formType']").change(function(){
	if("online"==this.value){
		$("#tstable_div").hide();
		$("#onlineId_div").show();
	}else{
		$("#tstable_div").show();
		$("#onlineId_div").hide();
	}
});
//-->
</script>