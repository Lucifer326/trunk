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
        <title>业务申请</title>
		<link rel="stylesheet" type="text/css" href="plug-in/planeui/dist/css/planeui.css" />
         <style> 
            #test > .pui-button-sheet-row > a {
                border: 5px solid #fff;
            }
            
            .pui-button-sheet {
                height: 360px;
            }
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
                        <a href="javascript:;"><i class="fa fa-chevron-left fa-2x pui-text-white"></i></a>
                    </div>
                    <div class="pui-app-header-middle">
                        <strong class="pui-h4 pui-text-white">业务申请</strong>
                    </div>
                    <div class="pui-app-header-offside">      
                        <a href="javascript:;" pui-side-slide="right" ><i class="fa fa-list fa-2x pui-text-white"></i></a>
                    </div>
                </header> 
                <div class="pui-app-main-container" id="wrapper1">
                   <div class="pui-layout">
                	<div class="pui-button-sheet pui-button-sheet-table pui-button-sheet-bordered" style="height:360px;" pui-button-sheet-show-more-height="270px">
	                    <div class="pui-button-sheet-row">
	                        <a href="javascript:;"><i class="fa fa-clock-o pui-text-yellow-600 fa-3x"></i><br/>请假</a>
	                        <a href="javascript:;"><i class="fa fa-dollar pui-text-orange-400 fa-3x"></i><br/>报销</a>
	                        <a href="javascript:;"><i class="fa fa-plane pui-text-light-blue-200 fa-3x"></i><br/>出差</a>
	                    </div> 
	                    <div class="pui-button-sheet-row">
	                        <a href="javascript:;"><i class="fa fa-car pui-text-blue-400 fa-3x"></i><br/>外出</a>
	                        <a href="javascript:;"><i class="fa fa-suitcase pui-text-lime-700 fa-3x"></i><br/>物品领用</a>
	                        <a href="javascript:;"><i class="fa fa-flag pui-text-light-blue-400 fa-3x"></i><br/>通用审批</a>
	                    </div> 
	                    <div class="pui-button-sheet-row">
	                        <a href="javascript:goApply('mobileDemoController.do?goDemoForm');;"><i class="fa fa-apple pui-text-yellow-500 fa-3x"></i><br/>测试</a>
	                        <a href="javascript:;" class="pui-button-sheet-more"><i class="fa fa-ellipsis-horizontal fa-3x"></i>更多</a>
	                        <a href="javascript:;"></a>
	                    </div>
	                </div>
		           </div>
                </div>
                <footer class="pui-app-footer">
                    <div class="pui-button-sheet pui-button-sheet-table pui-button-sheet-hover-pui-bg-none pui-unbordered" style="height:100%;">
                        <div class="pui-button-sheet-row" style="height:48px;">
                            <a href="mobileTaskController.do?goRunningTask" ><i class="fa fa-briefcase fa-2x"></i>待我审批</a>
                            <a href="javascript:;" style="color:#39f;"><i class="fa fa-tasks fa-2x"></i>业务申请</a>
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
				$(function() {  
		        	$().sideSlide();
		            $().sidePosition();
		        	getMoreData();
		        	
		        });  
				function goApply(url){
					location.href=url;
				}
		</script>
		
    </body>
</html>