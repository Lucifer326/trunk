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
        <title>待办任务</title>
		<link rel="stylesheet" type="text/css" href="plug-in/planeui/dist/css/planeui.css" />
        <style>            
            .iScrollIndicator {
                width: 5px !important;
                border: none !important;
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
                        <a href="javascript:;" pui-side-slide="left"><i class="fa fa-list fa-2x pui-text-white"></i></a>
                    </div>
                    <div class="pui-app-header-middle" pui-side-slide="top">
                        <strong class="pui-h4 pui-text-white">待办任务</strong>
                    </div>
                    <div class="pui-app-header-offside">      
                        <a href="javascript:;" pui-side-slide="right" class="pui-text-white pui-text-xxxxl" style="line-height:1;line-height:1;padding: 3px 12px 5px;">+</a>
                    </div>
                </header> 
                <div class="pui-app-main-container" id="wrapper1">
                    <div class="pui-app-scroller2">
                        <div>
                            <a href="javascript:;"><img src="images/22.jpg" class="pui-img-responsive" width="100%" /></a>
                        </div>                
                        <ul class="pui-list pui-list-view pui-list-view-angle-right pui-list-view-round">
                            <li>
                                <a href="javascript:;">
                                    <img src="images/17.jpg" />  
                                    <summary>
                                        <h6>似水流年</h6>
                                        <small>我愿这一夜长醉，流年似水的滋味</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-badge-dot pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/23.jpg" />  
                                    <summary>
                                        <h6>一往情深的恋人</h6>
                                        <small>所有的爱都是冒险，那就心甘情愿</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-badge-dot pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/5.jpg" />
                                    <summary>
                                        <h6>匆匆那年</h6>
                                        <small>王菲最新单曲</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;" class="pui-no-angle-right">
                                    <img src="images/11.jpg" />  
                                    <summary>
                                        <h6>可风</h6>
                                        <small>灰白的是过往云烟，痛已不见</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle pui-badge-right">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/17.jpg" />
                                    <summary>
                                        <h6>完美坚持</h6>
                                        <small>我心永恒</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle">99</lable>
                                </a>
                            </li> 
                            <li>
                                <a href="javascript:;">
                                    <img src="images/17.jpg" />  
                                    <summary>
                                        <h6>似水流年</h6>
                                        <small>我愿这一夜长醉，流年似水的滋味</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-badge-dot pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/23.jpg" />  
                                    <summary>
                                        <h6>一往情深的恋人</h6>
                                        <small>所有的爱都是冒险，那就心甘情愿</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-badge-dot pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/5.jpg" />
                                    <summary>
                                        <h6>匆匆那年</h6>
                                        <small>王菲最新单曲</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;" class="pui-no-angle-right">
                                    <img src="images/11.jpg" />  
                                    <summary>
                                        <h6>可风</h6>
                                        <small>灰白的是过往云烟，痛已不见</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle pui-badge-right">99</lable>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <img src="images/17.jpg" />
                                    <summary>
                                        <h6>完美坚持</h6>
                                        <small>我心永恒</small> 
                                    </summary>
                                    <lable class="pui-badge pui-badge-error pui-circle">99</lable>
                                </a>
                            </li> 
                            <li>                               
                                <div class="pui-text-center">
                                    <a href="javascript:;" class="pui-btn pui-btn-block pui-btn-default pui-btn-xlagre pui-btn-square pui-unbordered pui-text-gray" style="padding: 1.6rem 0;">加载更多 ...</a>
                                </div>                                
                            </li>
                        </ul> 
                    </div>
                </div>
                <footer class="pui-app-footer">
                    <div class="pui-button-sheet pui-button-sheet-table pui-button-sheet-hover-pui-bg-none pui-unbordered" style="height:100%;">
                        <div class="pui-button-sheet-row" style="height:48px;">
                            <a href="javascript:;" style="color:#39f;"><i class="fa fa-briefcase fa-2x"></i>待办任务</a>
                            <a href="mobileTaskController.do?goTaskApply" ><i class="fa fa-tasks fa-2x"></i>业务申请</a>
                            <a href="mobileTaskController.do?goMyApply" ><i class="fa fa-user fa-2x"></i>我的申请</a>
                        </div>
                    </div>
                </footer>
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
    </body>
</html>