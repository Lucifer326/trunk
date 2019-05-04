<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
    <title><h:mutiLang langKey="platform.title"/></title>
    <link href="plug-in/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="plug-in/hplus/css/font-awesome.min.css" rel="stylesheet">
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in/hplus/css/style_darkred.css" rel="stylesheet">
    <link href="plug-in/hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <!--  <link href="plug-in/easyui/themes/icon.css" rel="stylesheet">-->
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side navbar_lf_bg" role="navigation">
            <div class="nav-close">
                <i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse ">
                <ul class="nav nav_lf" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span>
                                <img alt="image" class="img-circle" src="plug-in/hplus/img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs super"><strong class="font-bold">${userName }</strong></span>
                                    <span class="text-muted text-xs block super">${roleName }<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li> 
                                	<a  href="javascript:add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword','',550,200)">
                                    	<t:mutiLang langKey="common.change.password"/>
                                	</a>
                                </li>
                             <li><a  href="javascript:openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')"><t:mutiLang langKey="common.profile"/></a></li>
                            <!-- 用户存在多个归属组织机构时，提供切换组织机构功能 -->
                            <c:if test="${fn:length(sessionScope.LOCAL_CLINET_USER_DEPART_LIST) > 1}">
                            	<li><a href="javascript:add('<t:mutiLang langKey="common.change.depart"/>','userController.do?changeDepart','',550,300)"><t:mutiLang langKey="common.change.depart"/></a></li>
                            </c:if>
                            <li><a  href="javascript:add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle','',550,300)"><t:mutiLang langKey="common.my.style"/></a></li>
                            <li><a href="javascript:clearLocalstorage()"><t:mutiLang langKey="common.clear.localstorage"/></a></li>
                            <li><a href="javascript:updateVersion()"><t:mutiLang langKey="common.update.version"/></a></li>
                            <li class="divider"></li>
                            <li><a href="javascript:logout()">注销</a></li>
                            </ul> 
                        </div>
                        <div class="logo-element">
                            H+
                        </div>
                    </li>

					<h:menu style="hplus" menuFun="${menuMap}"></h:menu>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top  top_bj" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header top_bt">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary hamb " href="#"><i class="fa fa-bars"></i></a>
                        <div class="navbar-form-custom">
                            <%-- <a><h:mutiLang langKey="jeect.platform"/></a> --%>
                            <a><h:mutiLang langKey="platform.common.title"/></a>
                        </div>
                    </div>
                    <ul class="nav navbar-top-links navbar-right them">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i>主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs cont-bot">
                <button class="roll-nav roll-left J_tabLeft">
                    <i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs pot">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="loginController.do?hplusbluehome">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight">
                    <i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">
                        关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:logout()" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i>退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
            	<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="loginController.do?hplusbluehome" frameborder="0" data-id="loginController.do?hplusbluehome" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">
                    	<h:mutiLang langKey="platform.right.bottom"/>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i>主题
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3><i class="fa fa-comments-o"></i>主题设置</h3>
                            <small><i class="fa fa-tim"></i>你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定宽度
                                </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                                    <a href="#" class="s-skin-0">默认皮肤
                                    </a>
                                </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                                    <a href="#" class="s-skin-1">蓝色主题
                                    </a>
                                </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                                    <a href="#" class="s-skin-3">黄色/紫色主题
                                    </a>
                                </span>
                            </div>-->
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->



    </div>
</body>
<script src="plug-in/hplus/js/jquery.min.js"></script>
<script src="plug-in/jquery/jquery-migrate-1.4.1.min.js"></script>
<script src="plug-in/hplus/js/bootstrap.min.js"></script>
<script src="plug-in/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="plug-in/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="plug-in/hplus/js/plugins/layer/layer.min.js"></script>

<h:base type="hplisttools"></h:base>
<!-- 自定义js -->
<script src="plug-in/hplus/js/hplus.js"></script>
<script type="text/javascript" src="plug-in/hplus/js/contabs.js"></script>
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>

<script src="plug-in/hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="plug-in/jquery-plugs/storage/jquery.storageapi.min.js"></script>

<!-- 第三方插件 -->
<script src="plug-in/hplus/js/plugins/pace/pace.min.js"></script>
<!-- 弹出TAB -->
<script type="text/javascript" src="plug-in/hplus/hplus-tab.js"></script>
<script type="text/javascript">
    $(function () {
        $(".nav-second-level li a").click(function () {
            $(".nav li a").removeClass("nav-second-color");
            $(this).addClass("nav-second-color")
        })
    })
    
   	function logout(){
        layer.confirm('您确定要注销吗？', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            location.href="loginController.do?logout";
        }, function(){
            return;
        });
    }
    function updateVersion(){
        $.ajax({
        	url:'versionUpdateController.do?checkVersion',
        	type:'post',
        	dataType:'json',
        	success:function(data){
        		if(data=='已是最新版本，无需更新！'){
        			layer.msg(data);
        		}else if(data=='有新版本，需要更新！'){
        			layer.confirm('您确定要更新吗？', {
        	            btn: ['确定','取消'], //按钮
        	            shade: false //不显示遮罩
        	        }, function(){
        	            $.ajax({
        	            	url:'versionUpdateController.do?updateVersion',
        	            	type:'post',
        	            	dataType:'json',
        	            	success:function(data1){
        	            		if(data1=='更新成功，请重启服务!'){
        	            			layer.msg(data1);
        	            		}else if(data1=='更新失败，请重新操作！'){
        	            			layer.msg(data1);
        	            		}else{
        	            			layer.msg('服务器报错！');
        	            		}
        	            	},
        	            	error:function(e){
        	            		layer.msg("出错了哦！");
        	    			}
        	            });
        	        }, function(){
        	            return;
        	        });
        		}else {
        			layer.msg(data);
        		}
        	},
        	error:function(e){
        		layer.msg("出错了哦！");
			}
        });
    }
    function clearLocalstorage(){
        var storage=$.localStorage;
        if(!storage)
            storage=$.cookieStorage;
        storage.removeAll();
        //bootbox.alert( "浏览器缓存清除成功!");
        layer.msg("浏览器缓存清除成功!");
    }
    function goAllNotice(){
        var addurl = "noticeController.do?noticeList";
        createdetailwindow("公告", addurl, 800, 400);
    }
    function goAllMessage(){
        var addurl = "tSSmsController.do?getSysInfos";
        createdetailwindow("消息", addurl, 800, 400);
    }
</script>
</html>
