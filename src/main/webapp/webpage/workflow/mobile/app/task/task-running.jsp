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
        <title>待我审批</title>
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
                        <a href="javascript:;" pui-side-slide="left"><i class="fa fa-chevron-left fa-2x pui-text-white"></i></a>
                    </div>
                    <div class="pui-app-header-middle" pui-side-slide="top">
                        <strong class="pui-h4 pui-text-white">待我审批</strong>
                    </div>
                    <div class="pui-app-header-offside">      
                        <a href="javascript:;" pui-side-slide="right" ><i class="fa fa-list fa-2x pui-text-white"></i></a>
                    </div>
                </header> 
                <div class="pui-app-main-container" id="wrapper1">
                	
                    <div class="pui-app-scroller2">
                        <ul id="container" class="pui-list pui-list-view pui-list-view-angle-right pui-list-view-round">
                            <li>                               
                                <div class="pui-text-center">
                                    <a id="moredata" href="javascript:getMoreData();" class="pui-btn pui-btn-block pui-btn-default pui-btn-xlagre pui-btn-square pui-unbordered pui-text-gray" style="padding: 1.6rem 0;">加载更多 ...</a>
                                </div>                                
                            </li>
                        </ul> 
                    </div>
                </div>
                <footer class="pui-app-footer">
                    <div class="pui-button-sheet pui-button-sheet-table pui-button-sheet-hover-pui-bg-none pui-unbordered" style="height:100%;">
                        <div class="pui-button-sheet-row" style="height:48px;">
                            <a href="javascript:;" style="color:#39f;"><i class="fa fa-briefcase fa-2x"></i>待我审批</a>
                            <a href="mobileTaskController.do?goTaskApply" ><i class="fa fa-tasks fa-2x"></i>业务申请</a>
                            <a href="mobileTaskController.do?goMyApply" ><i class="fa fa-user fa-2x"></i>我发起的</a>
                        </div>
                    </div>
                </footer>
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
            var pageNo = 1;  
            var pageSize = 10;  
            $(function() {  
            	$().sideSlide();
                $().sidePosition();
            	getMoreData();
            	
            });  
            
           function getMoreData(){
        	   $.getJSON("mobileTaskController.do?taskAllList", {page: pageNo,rows:pageSize}, function(data) {  
                   if (data.rows&&data.rows.length>0) {  
                       var str = "";  
                       $.each(data.rows, function(index, array) {  
                       var str ="<li>"
                       var str = str + "<a href=\"javascript:processDeal('"+array['assigneeName']+"','"+array['Process_task_id']+"');\">";
                       var str = str + "<img src=\"plug-in/planeui/app/images/13.jpg\" />";
                       var str = str + "<summary>";
                       var str = str + "<h6>"+array['userRealName']+"<small>"+array['Process_task_createTime']+"</small></h6>";
                       var str = str + "<small>";
                       var str = str + "<input type=\"button\" class=\"pui-btn pui-btn-success pui-btn-xsmall\" value=\""+array['Process_processDefinition_name']+"\" />";
                       var str = str + "&nbsp;"+array['bpmBizTitle'];
                       var str = str + "</small>";  
                       var str = str + "</summary>";
                       if(array['assigneeName']==""){
                       	  var str = str + "<lable class=\"pui-badge pui-badge-error pui-circle\">签</lable>";
                       }
                       var str = str + "</a>";
                       var str = str + "</li>";
                        //   var str = "<div class=\"single_item\"><div class=\"element_head\">";  
                        //   var str = str + "<div class=\"date\">" + array['date'] + "</div>";  
                        //   var str = str + "<div class=\"author\">" + array['author'] + "</div>";  
                        //   var str = str + "</div><div class=\"content\">" + array['content'] + "</div></div>";  
                           $("#container li:last").before(str);  
                       });  
                       pageNo++;  
                   } else {  
                       $("#moredata").hide();  
                       return false;  
                   }  
               });  
           }
           
           function processDeal(assigneeName,taskId){
        	   if(assigneeName==""){
        		   $.dialog({
        	            type : "confirm",
        	            maskClickClosed : false,
        	            content : "确定要签收吗？",
        	            buttons : {
        	                values : {
        	                    yes : "签收",
        	                    cancel : "取消"
        	                }
        	            },
        	            yes : function(){ 
        	                  //去签收
        	            	claim(taskId);
        	                location.reload();
        	            },
        	            cancel : function(){ 
        	            	
        	            }
        	        });
        	   }else{
        		   //去办理
        		   goTaskDeal(taskId);
        	   }
           }
           
           //签收
           function claim(taskId){
        	   var url ="activitiController.do?claim&taskId="+taskId;
        	   doSubmit(url);
           }
           
           //去办理
           function goTaskDeal(taskId){
        	   //var url = 'taskController.do?goTaskTab&taskId=' + taskId;
        	   var url = 'mobileTaskController.do?goTaskTab&taskId=' + taskId;
        	   location.href=url;
           
           }
           
           function doSubmit(url) {
        		$.ajax({
        			async : false,
        			cache : false,
        			type : 'POST',
        			url : url,// 请求的action路径
        			error : function() {// 请求失败处理函数
        			},
        			success : function(data) {
        				var d = $.parseJSON(data);
        				if (d.success) {
        					var msg = d.msg;
        					tip(msg);
        				}
        			}
        		});
        	}
           
           function tip(msg){
        		$.dialog({
        		    type:"alert-app",
        		    content : msg,
        		    buttons : {
        		        style : {
        		            //yes : "color:red;"
        		        },
        		        "class" : {
        		            //yes : "pui-btn-warning"
        		        }
        		    },
        		    yes : function(){ 
        		    }
        		});
        		
        	}
        </script>  
    </body>
</html>