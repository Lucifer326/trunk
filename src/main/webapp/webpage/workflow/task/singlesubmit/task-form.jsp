<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,tools,easyui,DatePicker"></t:base>
<script type="text/javascript">
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
<c:if test="${not empty nodeStart }">
<iframe id="taskformiframe" name="taskformiframe" src="${nodeStart}" width="100%" height="100%" scrolling="no" FRAMEBORDER=0 onload="iframeresize('taskformiframe');"></iframe>
</c:if>
<c:if test="${empty  nodeStart }">
 <br> <br> <br> <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<t:mutiLang langKey="common.nodestart.no"/>
</c:if>
<!-- <script src="plug-in/hplus/js/jquery.min.js"></script> -->
<script type="text/javascript">



		$('#passBtn').linkbutton({   
		});  
		$('#returnBtn').linkbutton({   
		}); 
		
		function submitFrame(){
			window.frames["taskformiframe"].neibuClick();
			//parent.procPass();
		}
		//控制提交流程方法，并按钮变灰
		function doProcess(){
			//处理意见有值，按钮变灰，提交表单
			if(parent.isHasYiJian())
			{
				parent.procPass();
				parent.disabledButton();	
			}
			
		}
		function getReason()
    	{
    		return parent.getReason();
    	}
		function submitManyModelFrame(){
			window.frames["taskformiframe"].neibuClick();
			parent.manyModelSubmit();
		}
		
		/* function procPass(yes){
			var iframe  = window.frames["iframeChild"].document;
			var inputvar = $("[vartype]", iframe);
			setvar(yes, inputvar, window.frames["iframeChild"]);
			var formData = {};
			 $("#taskName").val($(iframe).$("#taskNameSelect").find("option:selected").text());
			$(iframe).find("input,textarea,select").each(function(){
				formData[$(this).attr("name")]= $(this).val();
			});
			var formAction = iframe.forms["formobj"].action;
			//ajax方式提交iframe内的表单
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				data : formData,
				url : formAction,// 请求的action路径
				error : function() {// 请求失败处理函数
				},
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						W.tip(msg);
						W.reloadTable();
						windowapi.close();
					}
				}
			});
		} */
		

</script>
