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
        <title>交作业demo</title>
		<link rel="stylesheet" type="text/css" href="plug-in/planeui/dist/css/planeui.css" />
		<style type="text/css">
            #formobj{padding:15px;}
            input[type=text], input[type=password] , select{ width: 180px;} 
            input[type=submit]{border:1px solid #ddd;background:none;padding:6px 10px;cursor: pointer;font-size:14px;}
        </style>
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
                        <a href="mobileTaskController.do?goTaskApply" pui-side-slide="left"><i class="fa fa-chevron-left fa-2x pui-text-white"></i></a>
                    </div>
                    <div class="pui-app-header-middle" pui-side-slide="top">
                        <strong class="pui-h4 pui-text-white">测试表单申请</strong>
                    </div>
                    <div class="pui-app-header-offside">      
                        <a href="javascript:;"><i class="fa fa-list fa-2x pui-text-white"></i></a>
                    </div>
                </header> 
                <div class="pui-app-main-container" id="wrapper1">
                	<div class="pui-layout" style="width:96%;">
                   	<form id="formobj" name="formobj" method="post" class="pui-form-label-left">
							  <input type="hidden" id="btn_sub" class="btn_sub"/>
							  <input type="hidden" name="tableName" value="student_zuoye" >
							  <input type="hidden" name="id" value="" >
							  <input type="hidden" id="create_name" name="create_name" value="" >
							  <input type="hidden" id="create_by" name="create_by" value="" >
							  <input type="hidden" id="create_date" name="create_date" value="" >
							  <input type="hidden" id="update_by" name="update_by" value="" >
							  <input type="hidden" id="update_date" name="update_date" value="" >
							  <input type="hidden" id="bpm_status" name="bpm_status" value="1" >
							  
							   <label>学生名字：</label>
                			   <input type="text" id="name"  name="name" />
                			   <span fv-role="name"></span>
                			   <br/>
							   <label>作业数目：</label>
                			   <input type="text" id="num"  name="num"/>
                			   <span fv-role="num"></span>
                			   <br/>
							   <label>作业内容：</label>
                			   <input type="text" id="content"  name="content" />
                			   <span fv-role="content"></span>
                			   <br/>
                			   <input type="button" name="submit" id="demo-form-submit" class="pui-btn pui-btn-primary" value="提交表单" />
					</form>
                   	
                   </div>
                   
                </div>
                
                <div class="pui-mask pui-mask-bg" style="display: none;opacity:0;"></div>
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
		<script type="text/javascript">
		$(function() {
			$("#demo-form-submit").formValidator({
		    	name : true,
		    	num : true,
		    	content : true
		      }, {
		        url : "cgFormBuildController.do?saveOrUpdate",
		        beforeSend:function (XMLHttpRequest) {
		        	loadDialog();
		        },
		        success : function(data){
		            console.log("ajaxSubmit.data", data);
		            var d = $.parseJSON(data);
		            if(d.success){
		            	if(d.obj.id!=""){
		            		submitProcess('activitiController.do?startOnlineProcess&configId=student_zuoye&id='+d.obj.id);
		            	}
		            }else{
		            	alert("表单保存失败："+d.msg);
		            	loading.hide();
		            }
		        },
		        error : function(status){
		            //console.log("ajaxSubmit.error", status);
		        }
		    }, {
		        normal : "* 必填项",
		        empty  :" 不能为空。"
		    }); 
		   
		});
		
		var loading;
		function loadDialog(){
			loading = $.dialog({
		        type: "loading",
		        maskClickClosed : false,
		        toolbar: false,
		        content : '<div class="pui-loading pui-loading-ring"></div><p style="margin-top:10px;">正在提交中，请稍后....</p>'
		    });
		}
		
		function submitProcess(url){
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
						alert(msg);
						var url = "mobileTaskController.do?goTaskApply";
						location.href=url;
					}else{
						alert("提交流程失败："+d.msg);
					}
					loading.hide();
				}
			});
			/* $.dialog({
	            type : "confirm",
	            content : "确认提交流程？",
	            buttons : {
	                values : {
	                    yes : "嗯，确定",
	                    cancel : "我再想想"
	                }
	            },
	            yes : function(){ 
	                //console.log("confirm yes: "+this.id);
	            },
	            cancel : function(){ 
	                //console.log("confirm cancel: "+this.id);
	            }
	        }); */
		}
		</script>
		
		
    </body>
</html>