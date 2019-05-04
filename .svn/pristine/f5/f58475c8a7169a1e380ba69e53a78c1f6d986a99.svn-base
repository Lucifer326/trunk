<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn" class="pui-app">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="renderer" content="webkit" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="description" content="JEECG BPM" />
        <meta name="keywords" content="JEECG BPM" />
        <title>业务办理</title>
		<link rel="stylesheet" type="text/css" href="plug-in/planeui/dist/css/planeui.css" />
    </head>
    <body>
        <!--[if lte IE 9]>
        <div class="pui-layout pui-browsehappy">
            <a href="javascript:;" class="pui-close" onclick="document.body.removeChild(this.parentNode);"></a>
            <p>您正在使用 <strong class="pui-text-yellow pui-text-xl">过时</strong> 的非现代浏览器，<strong class="pui-text-success pui-text-xl">91.23%</strong> 的人选择 <a href="http://browsehappy.com/" target="_blank" class="pui-text-green-400 pui-text-xl"><strong>升级浏览器</strong></a>，获得了更好、更安全的浏览体验！</p>
        </div>
        <![endif]-->
        <div class="pui-layout pui-app-layout">
            <div class="pui-app-main pui-app-main-prev">
                <div class="mask mask-bg pui-side-position-mask"></div>
               
                <header class="pui-app-header pui-bg-blue-300">
                    <div class="pui-app-header-aside">      
                        <a href="mobileTaskController.do?goRunningTask"><i class="fa fa-chevron-left fa-2x pui-text-white"></i></a>
                    </div>
                    <div class="pui-app-header-middle" pui-side-slide="top">
                        <strong class="pui-h4 pui-text-white">业务办理</strong>
                    </div>
                    <div class="pui-app-header-offside">      
                        <a href="javascript:;" pui-side-slide="right" ><i class="fa fa-list fa-2x pui-text-white"></i></a>
                    </div>
                </header> 
                
                
                <div class="pui-app-main-container" id="wrapper1" style="width:99.99%;height:100%">
                	<div id="operateTab" class="pui-tab pui-tab-underline pui-tab-justify" style="width:99.99%;height:100%">
		                <ul class="pui-tab-head">
		                    <li tab-ajax-url="mobileTaskController.do?goTaskFormMobile&taskId=${taskId }" class="active">表单</li>
		                    <li tab-ajax-url="mobileTaskController.do?goTaskOperate&taskId=${taskId }">办理</li>
		                    <li tab-ajax-url="mobileTaskController.do?viewProcessInstanceHistory&processInstanceId=${processInstanceId }">流程图</li>
		                </ul>
		                <div class="pui-tab-container" style="width:99.99%;height:100%">
		                    <div class="pui-tab-box" style="display: block;width:99.99%;height:100%;padding:1px 0">
		                    </div>
		                    <div class="pui-tab-box" style="width:99.99%;height:100%;padding:1px 0"></div>
		                    <div class="pui-tab-box" style="width:99.99%;height:100%;padding:1px 0"></div>
		                </div> 
		            </div>
                    
                </div>
               
                <div class="pui-mask pui-mask-bg" style="display: none;opacity:0;"></div>
            </div>
           	<div class="pui-side-slide-right">                
                <div>
	                <ul class="pui-menu pui-menu-accordion pui-menu-bordered pui-bg-blue click-toggle">
		                <li>                    
		                    <a href="mobileTaskController.do?goRunningTask">
		                        <i class="fa fa-home pui-text-xl"></i>
		                        <span>首页</span>
		                    </a>
		                </li>
		                <li>
		                    <a href="mLoginController.do?logout"><i class="fa fa-sign-out pui-text-lg"></i>退出登录</a>
		                </li>
		            </ul>
            	</div>
            </div>
        </div> 

        <!--[if (gte IE 9) | !(IE)]><!-->
		<script type="text/javascript" src="plug-in/jquery/jquery-2.1.1.min.js"></script>
        <!--<![endif]-->

		<!--[if lt IE 9]>
		<script type="text/javascript" src="plug-in/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="plug-in/planeui/dist/js/planeui.patch.ie8.js"></script>
		<![endif]-->

		<!--[if lt IE 10]>
		<script type="text/javascript" src="plug-in/planeui/dist/js/planeui.patch.ie9.js"></script>
		<![endif]-->
		<script type="text/javascript" src="plug-in/planeui/dist/js/planeui.js"></script>
		<script type="text/javascript" src="plug-in/planeui/app/js/iscroll.js"></script>
		<script type="text/javascript">  
		   
            $(function() {
            	$().sideSlide();
                $().sidePosition();
                
            	$("#operateTab").tab({
                    speed : "slow",
                    showMode : "slide",
                    ajaxLoading : '<div class="pui-loading-spinner pui-animation-rotate pui-animation-repeat pui-animation-reverse pui-text-center"><span class="fa fa-spinner fa-2x"></span></div> 正在加载内容，请稍后...',
                    callback : function(tab, tabHead, tabBox, index, settings) {
                        if(tabHead.eq(index).attr("tab-ajax-cached")) {
                        	return ;
                        }else{
                        	tabBox.eq(index).html(settings.ajaxLoading);
                        	var url = tabHead.eq(index).attr("tab-ajax-url");
                        	$.post(url, {}, function(data) {
                                tabBox.eq(index).html(data);
                        		tabHead.eq(index).attr("tab-ajax-cached", true);
                        		if(index==2){
                        			getMoreData();
                        		}
                            });
                        	//tabBox.eq(index).html("<iframe id=\"iframe_"+index+"\" src=\""+url+"\"  width=\"99.99%\" height=\"100%\" scrolling=\"yes\" frameborder=\"0\">"+settings.ajaxLoading+"</iframe>");
                        }
                    } 
                });
				
            	init();
            });
            
            function init(){
            	var tabHead =$("#operateTab").find(".pui-tab-head li:first");
            	var tabBox = $("#operateTab").find(".pui-tab-box:first"); 
            	var url = tabHead.attr("tab-ajax-url");
            	tabBox.html('<div class="pui-loading-spinner pui-animation-rotate pui-animation-repeat pui-animation-reverse pui-text-center"><span class="fa fa-spinner fa-2x"></span></div> 正在加载内容，请稍后...');
            	$.post(url, {}, function(data) {
                    tabBox.html(data);
            		tabHead.attr("tab-ajax-cached", true);
                });
            }
            
       
		    	function disabledButton(){
		    		$('input[buttongroup="buttongroup"]').attr("disabled","true");
		    		$('input[buttongroup="buttongroup"]').attr("class","pui-btn pui-btn-primary disabled");
		    	}
				function procPass(yes,nextnode){
					$("#option").val(yes);
					$("#nextnode").val(nextnode);
					var formData = {};
					$("#formobj").find("input,textarea,select").each(function(){
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
								//if($(".uploadify-queue-item").length>0){
								//	upload();
								//}else{
									var msg = d.msg;
									alert(msg);
									//W.tip(msg);
									//W.reloadTable();
									var url = "mobileTaskController.do?goRunningTask";
									location.href=url;
									//windowapi.close();
								//}	
							}else{
								var msg = d.msg;
								alert(msg);
								//W.tip(msg);
							}
						}
					});
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
				
				/**
				 * 多分支模式 提交
				 */ 
				 function manyModelSubmit(){
					var formData = {};
					$("#formobj").find("input,textarea,select").each(function(){
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
							//alert('d.success'+d.success);
							if (d.success) {
								$("#bpmlogId").val(d.obj.id);
								//if($(".uploadify-queue-item").length>0){
								//	upload();
								//}else{
									var msg = d.msg;
									alert(msg);
									//W.tip(msg);
									//W.reloadTable();
									var url = "mobileTaskController.do?goRunningTask";
									location.href=url;
									//windowapi.close();
								//}	
							}else{
								var msg = d.msg;
								alert(msg);
								//W.tip(msg);
							}
						}
					});
				}
				
				 function getMoreData(){
		        	   $.getJSON("activitiController.do?taskHistoryList&processInstanceId=${processInstanceId}", {page: 1,rows:100}, function(data) { 
		                   if (data.rows&&data.rows.length>0) {  
		                       var str = "";  
		                       $.each(data.rows, function(index, array) {  
		                       	   var str ="<div class=\"pui-timeline-item\" style=\"padding-left: 32px\">"
		                       	   var str = str + "<label class=\"pui-badge pui-badge-info pui-badge-dot\"></label>";
			                       var str = str + "<div class=\"pui-timeline-item-context\">";
			                       var str = str + "<div class=\"pui-timeline-divider\"><summary><h6>流程节点："+array['name']+"</h6></summary></div>";
			                       var str = str + "<div class=\"pui-tooltip pui-tooltip-bordered pui-tooltip-arrow-lt \">";
			                       var str = str + "<summary><h6>负责人："+array['assignee']+"</h6></summary>";
			                       var str = str + "开始时间："+array['startTime']+"<br/>";
			                       var str = str + "结束时间："+array['endTime']+"<br/>";
			                       var str = str + array['deleteReason'];
			                       var str = str + "</div>";
			                       var str = str + "</div>";
			                       var str = str + "</div>";
		                           $("#container").before(str);  
		                       });  
		                       
		                   } 
		               });  
		           }
				</script>
    </body>
</html>