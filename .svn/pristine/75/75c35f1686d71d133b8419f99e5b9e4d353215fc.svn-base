<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<t:tabs  id="tt" iframe="false" tabPosition="top">
	<t:tab  href="taskController.do?goTaskOperate&taskId=${taskId }" icon="icon-search" title="common.task.operate" id="taskOperate"></t:tab>
	<t:tab  href="activitiController.do?viewProcessInstanceHistory&processInstanceId=${processInstanceId }" icon="icon-search" title="common.process.chart" id="processPicture"></t:tab>
</t:tabs>
<script>
$("#taskOperate").bind("onload",function(){
	
	iframeresize("taskOperate");
});
function iframeresize(id)
{
	var iframe = document.getElementById(id); 
	var iframeDocument = null;
	//safari和chrome都是webkit内核的浏览器，但是webkit可以,chrome不可以
	if (iframe.contentDocument)
	{ 
		//ie 8,ff,opera,safari
		iframeDocument = iframe.contentDocument;
	} 
	else if (iframe.contentWindow) 
	{ 
		// for IE, 6 and 7:
		iframeDocument = iframe.contentWindow.document;
	} 
	if (!!iframeDocument) {
		var timer=setTimeout(function(){
    		//var wifram=$("#t_table").width();    		
    		//iframe.width=wifram+"px"; 
	    	if (iframe.contentDocument)
	    	{ 
	    		iframe.height=iframeDocument.body.scrollHeight;
	    	}else{
	    		iframe.height=iframeDocument.documentElement.scrollHeight; 
	    	}
    		//iframe.height=this.innerHeight-300+"px";   
		},300);	
	} else {
		alert("this browser doesn't seem to support the iframe document object");
	} 

}
</script>