<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title><t:mutiLang langKey="common.task.operate"/></title>
<%-- <t:base type="jquery,easyui,tools,DatePicker"></t:base> --%>
   <style type="text/css">
   	#t_table td label {font-size:15px;}
  </style>
  

 </head>
 <body>
   <fieldset style="border: 1px solid #E6E6E6;padding:0;">
 		<legend>表单信息</legend>
   		<iframe id="taskformiframe" name="taskformiframe"  src="taskController.do?goTaskForm&taskId=${taskId }" scrolling="no" frameborder="0" width="100%" onload="iframeresize('taskformiframe');"></iframe>
   </fieldset>
   <t:formvalid formid="formobj" layout="table" dialog="true" usePlugin="password">
	   <input name="taskId" id="taskId" type="hidden" value="${taskId}" />
	   <input name="bormoney" id="bormoney" type="hidden" vartype="B" value="${bormoney}">
	   <input name="keys" id="keys" type="hidden" />
	   <input name="values" id="values" type="hidden" />
	   <input name="types" id="types" type="hidden" />
	   <input name="nextCodeCount" id="nextCodeCount" type="hidden" value="${nextCodeCount}"/>
    	<div  class="ui-widget-content ui-corner-all" style="padding: 10px; margin: 10px;">
	    	<div style="margin: 15px auto; height: 50px; width: 900px;" id="tabs-project">
		    	<c:if test="${bpmLogListCount-3 > 0}">
		    		<div class="progress"></div>
		    		<div class="progress"></div>
		    	</c:if>
		    	 <c:forEach items="${bpmLogList}" var="bpmLg" varStatus="name" >
		    	 	<c:if test="${name.index < bpmLogNewListCount}">
			    	 		<div class="progress progress"></div>
			    	 		<div class="progress progress1">
				    	 		<div class="detial">
							       <b>${bpmLg.task_node }</b><br/>
							       <!--处理时间  -->
							        [<span style="color:red;"><t:mutiLang langKey="common.task.time"/>:
							       	<fmt:formatDate value="${bpmLg.op_time }" pattern="MM-dd HH:mm:ss"/></span>]<br/>
							       	<!--操作人  -->
							       [<span><t:mutiLang langKey="common.task.operator"/>：${bpmLg.op_name }]</span>
							    </div>
						    </div>
		    	 	</c:if>
		    	 </c:forEach>
		    	 <c:if test="${taskName != null }">
		    	 	<div class='progress progress_cancel'></div>
		    	 	<div class="progress progress3">
			    	 	<div class="detial">
			                <span><b>${taskName}</b></span><br>
							<!-- [<span><t:mutiLang langKey="common.task.assignee"/>：</span>]<br> -->
					       [<span><t:mutiLang langKey="common.task.operator"/>：${taskopname }]</span>
			          	</div>
		          	</div>
		    	 </c:if>
		    	 <div class='progress progress_unstart'></div>
				 <div class='progress progress_unstart'></div>
				 <div class='progress progress_unstart'></div>	
	    	 </div>
    	 </div>
    	 <table id="t_table" cellpadding="0" cellspacing="1" class="formtable" >
	    	 <tr height="35">
	    	 	<td class="value" style="padding: 0px 5px;">
	    	 		<label class="Validform_label"><t:mutiLang langKey="common.task.suggestion"/></label>
	    	 	</td>  	 	
	    	 </tr>
		     <c:forEach items="${bpmLogList}" var="bpmLog">
		     	<tr height="35">
		     		<td class="value" style="padding: 0px 5px;border-top:1px dashed #00CCCC; font-size:13px;">
		     			<fmt:formatDate value="${bpmLog.op_time }" pattern="yyyy-MM-dd HH:mm:ss"/>[${bpmLog.op_name }]
		     		</td>
		     	</tr>
		     	<tr height="35">
		     		<td class="value" style="padding: 0px 5px;font-size:13px;">
		     			[<span style="color:blue">${bpmLog.task_node }</span>]${bpmLog.memo }
		     		</td>
		     	</tr>
		     	<c:forEach items="${bpmLog.bpmFiles}" var="bpmFile">
			     	<tr height="35">
			     		<td class="value" style="padding: 0px 5px;">
			     		<!--附件  -->
			     			[<span style="color:blue"><t:mutiLang langKey="common.attachment"/></span>] ${bpmFile.attachmenttitle}
							<a href="commonController.do?viewFile&fileid=${bpmFile.id}&subclassname=org.jeecgframework.workflow.pojo.base.TPBpmFile" title="common.document.download"><t:mutiLang langKey="common.document.download"/></a>
							<a href="javascript:void(0);"
								onclick="openwindow('<t:mutiLang langKey="common.preview"/>','commonController.do?openViewFile&fileid=${bpmFile.id}&subclassname=org.jeecgframework.workflow.pojo.base.TPBpmFile','fList','800','700')"><t:mutiLang langKey="common.preview"/></a>
<!--							<a href="javascript:void(0)" class="jeecgDetail" onclick="del('tFinanceController.do?delFile&id=${bpmFile.id}',this)">删除</a>-->
			     		</td>
			     	</tr>
		    	</c:forEach>	
		    	<br/>
		     </c:forEach>
		     <tr height="35" >
		     <!--处理意见  -->
		     	<td class="value" style="padding: 0px 5px;">
		     		 <label class="Validform_label" style="font-size:14px;">
				      	<t:mutiLang langKey="common.handel.suggestion"/>
				     </label>
				     <textarea name="reason" id = "reason"  datatype="*" vartype="S" style="resize: none;font-size:12px; width:100%"" rows="3"></textarea>
				     <a href="#"  class="easyui-linkbutton" plain="true" icon="icon-search" onclick="selectSuggestion()" style="float:right; margin:10px 0; padding:0px">选择备选意见</a>
		     		<span class="Validform_checktip"></span>
		     	</td>
		     </tr>
		    <tr> 
			  <td class="value" style="padding: 0px 5px;">
			  	<div class="form jeecgDetail" style="padding: 3px;display:none">
			    	<input type="hidden" id="bpmlogId" name="bpmlogId" />
					<br/><t:upload name="fiels" id="file_upload" extend="*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.pdf;*.jpg;" buttonText="common.file.add" formData="bpmlogId" uploader="activitiController.do?saveBpmFiles">
					</t:upload>
				</div>
				<div class="form" id="filediv" style="height: 50px"></div>
				</td>
			</tr> 
			<tr> 
			  <td class="value" name="trhide">
				 <input id="single" type="radio" name="model" value="1" onchange="changeModel(1);" checked/><t:mutiLang langKey="common.model.one"/>
				 <input type="radio" name="model" value="2" onchange="changeModel(2);"/><t:mutiLang langKey="common.model.more"/>
				 <span id="manyModel" style="display:none">
				 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red"><t:mutiLang langKey="common.model.more.all"/>：</span>
					<c:forEach items="${transitionList}" var="trans">
						<input type="checkbox" name="transition" value="${trans.nextnode}" checked disabled>${trans.Transition }
					</c:forEach>
		  		</span>
		  			</td>
		 	</tr>
		 	<c:if test="${histListSize > 0 }">
		 	<tr>
		  				<td>
				  			<a href="#" onclick="unselectradio();">取消选中</a>
					  		<input type="radio" name="model" id="reject" value="3" onchange="changeModel(3);"/><t:mutiLang langKey="common.reject"/>
					  		<input name="taskName" type="hidden" value="" id="taskName"> 
					  		<span id="rejectModel" style="display:none">
					  			<select name="rejectModelNode" >
					  				<c:forEach items="${histListNode}" var="histNode">
					  					<%-- <option value="${histNode.task_def_key_}">${histNode.name_ }</option> --%>
					  					<option value="${histNode.task_def_key_}" id="taskNameSelect" <c:if test="${histNode.task_def_key_==turnbackTaskId }">selected</c:if> >${histNode.name_ }</option>
					  				</c:forEach>
					  			</select>
					  		</span>		
			  			</td>	
		 	</tr>
		 	</c:if>
		 	<tr name="trhide"> 
			  <td class="value">
			  <!-- 指定下一步操作人 -->
				 <t:mutiLang langKey="common.next.operator"/>：
				 <input name="last" id="last" readonly="readonly"> 
				 <input name="id" type="hidden" value="" id="id">  
				 <t:choose hiddenName="id" hiddenid="id" url="activitiController.do?goEntrust" name="entrusterList" icon="icon-search" title="common.user.list" textname="last" isclear="true"></t:choose><t:mutiLang langKey="common.noselect.default"/>
			  </td>
		 	</tr>
		 	<tr name="trhide"> 
			  <td class="value">
				 <input type="checkbox" name="ck_formsubmitflag" id="ck_formsubmitflag" value="1"  onchange="changeModel(1);"/>级联提交表单信息
			  </td>
		 	</tr>
			<tr> 
			  <td class="value"  align="center">
			  		<div id="singleModel" style="display:black">
						<input type="hidden" name="option" id="option" />
			  			<input type="hidden" name="nextnode" id="nextnode" />
						<c:forEach items="${transitionList}" var="trans">
<%-- 							<input type="button" buttongroup="buttongroup" class="Button" onclick="disabledButton();preProcPass('${trans.Transition }','${trans.nextnode}')" value="${trans.Transition }">
 --%>						
 							<input type="button" buttongroup="buttongroup" class="Button" onclick="preProcPass('${trans.Transition }','${trans.nextnode}')" value="${trans.Transition }">
 
 						</c:forEach>
			  		</div>
			  		<div id="manyModelButton" style="display:none">
<%-- 			  			<input type="button" buttongroup="buttongroup" class="Button" onclick="disabledButton();preManyModelSubmit();" value='<t:mutiLang langKey="common.submit"/>'>
 --%>			  		
 						<input type="button" buttongroup="buttongroup" class="Button" onclick="preManyModelSubmit();" value='<t:mutiLang langKey="common.submit"/>'>	
			  			<input type="hidden" name="transStr" id="transStr">
			  		</div>
				</td>
			</tr>
		 </table>
  	</t:formvalid>
    <script type="text/javascript">
  
    window.onresize = function(){
    	iframeresize('taskformiframe');
    }
    /*
	*2017-09-19 隐藏分支选择
	*/
   
	$(function(){ 
    	$("tr[name='trhide']").hide();  
    	$("td[name='trhide']").hide();  
	});
    function isChecked(){
    	var flag = false;
    	var ck = $("#ck_formsubmitflag").attr("checked");
    	if(ck){
    		flag = true;
    	}
    	return flag;
    }
    
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
        		var wifram=$("#t_table").width();    		
        		iframe.width=wifram+"px"; 
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
    
    
    	function disabledButton(){
    		$('input[buttongroup="buttongroup"]').attr("disabled","true");
    		$('input[buttongroup="buttongroup"]').attr("class","disabledButton");
    	}
    	//点击确认提交按钮
    	function preProcPass(yes,nextnode){
    		if(isHasYiJian())//是否有意见
    		{
    			$("#option").val(yes);
    			$("#nextnode").val(nextnode);			
    			if(isChecked()){
    				window.frames["taskformiframe"].submitFrame();
    			}else{
    				procPass();
    			}
    		}
    	}
    	//处理意见是否有值
    	function isHasYiJian()
    	{
    		if($("#reason").val()!="")
			  return true;
    		else
    		{
    			$("#reason").blur();//失去焦点，触发验证提示
    			return false;
    		}
    	}
    	function getReason()
    	{
    		return $("#reason").val();
    	}
		function procPass(){
			var formData = {};
				 $("#taskName").val($("#taskNameSelect").find("option:selected").text());
			$(formobj).find("input,textarea,select").each(function(){
				if($(this).attr("name") == 'model'){
					formData[$(this).attr("name")]= $('input[name="model"]:checked').val();
				}else{
					formData[$(this).attr("name")]= $(this).val();
				}
			});
			//ajax方式提交iframe内的表单
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				data : formData,
				url : 'activitiController.do?processComplete',// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('提交申请失败');
				},
				success : function(data) {
					var d = $.parseJSON(data);
					//alert('d.success'+d.success);
					if (d.success) {
						$("#bpmlogId").val(d.obj.id);
						if($(".uploadify-queue-item").length>0){
							upload();
						}else{							
							var msg = d.msg;							
							W.tip(msg);
							W.reloadTable();
							windowapi.close();
						}	
					}else{
						var msg = d.msg;
						tip(msg);
						}
					}
				}); 
			
		}
		//驳回的取消选中
		function unselectradio(){
			var $browsers = $("input[name=model]"); 
			$browsers.attr("checked",false);
			$("#single").attr("checked",true);
			//清除select选中状态
			$("#taskNameSelect").attr("selected",false);
			$("#rejectModel").hide();
			$("#singleModel").show();
			$("#manyModelButton").hide();		
			//$("#manyModelButton").show();
		}
		/**
		 * 单分支模式/多分支模式切换
		*/
		function changeModel(value){
			if(value == 1){
				//单分支模式
				$("#singleModel").show();
				$("#manyModel").hide();
				$("#manyModelButton").hide();
				$("#rejectModel").hide();
			}else if(value == 2){
				//多分支模式
				$("#singleModel").hide();
				$("#rejectModel").hide();
				$("#manyModel").show();
				$("#manyModelButton").show();
			}else{
				$("#singleModel").hide();
				$("#manyModel").hide();
				$("#rejectModel").show();				
				$("#manyModelButton").show();				
			}
			
		}
		
		//不用验证业务表单数据验证了,验证审批意见
		function preManyModelSubmit(){
			if(isHasYiJian())//是否有意见
    		{
				/* if(isChecked()){
					window.frames["taskformiframe"].submitManyModelFrame();
				}else{
					manyModelSubmit();
				} */
				manyModelSubmit();
    		}
    	}
		
		/**
		 * 多分支模式 提交
		 */ 
		 function manyModelSubmit(){
			// alert('d.success');
			 
		/**	//checkbox 选中
			var transStr = "";
			var trans = $("input[name='transition']");
           	for(i=0;i<trans.length;i++){
                   if(trans[i].checked==true){
                   	transStr += (trans[i].value+',');
                   }
               }
           	$("#transStr").val(transStr);
           	if(transStr == ""){
           		alert("多分支模式必须选择下一步分支");
               	return;
           	}
        */
			var formData = {};
        $("#taskName").val($("#taskNameSelect").find("option:selected").text());
			$(formobj).find("input,textarea,select").each(function(){
				if($(this).attr("name") == 'model'){
					formData[$(this).attr("name")]= $('input[name="model"]:checked').val();
				}else{
					formData[$(this).attr("name")]= $(this).val();
				}
			});
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				data : formData,
				url : 'activitiController.do?processComplete',// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('提交申请失败');
				},
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						$("#bpmlogId").val(d.obj.id);
						if($(".uploadify-queue-item").length>0){
							upload();
						}else{
							var msg = d.msg;
							W.tip(msg);
							W.reloadTable();
							windowapi.close();
						}	
					}else{
						var msg = d.msg;
						tip(msg);
					}
				}
			});
		}
		function selectSuggestion() {
	    	$.dialog({content: 'url:taskController.do?selectSuggestion', 
    			zIndex: '999999', 
    			title: '备选意见列表', 
    			lock: true, 
    			width: '600px', 
    			height: '300px', 
    			opacity: 0.4, 
    			button: [       
    			        {name: '确定', callback: callbackUserSelect, focus: true},       
    			        {name: '取消', callback: function (){}}   ]
    			}).zindex();
    	}
    	function callbackUserSelect() {
    		var iframe = this.iframe.contentWindow;
    		var rowsData = iframe.$('#typeList').datagrid('getSelections');
    		var suggestion=rowsData[0].typename;
    		$('#reason').val(suggestion);
    		$("#reason").blur();
    	} 
</script>
 </body>
</html>
