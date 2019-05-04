<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
		<meta name="renderer" content="webkit" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta name="description" content="JEECG BPM" />
        <meta name="keywords" content="JEECG BPM" />
		<title>JEECG BPM 登录</title>
        <link rel="stylesheet" type="text/css" href="plug-in/planeui/dist/css/planeui.css" />
        <link rel="stylesheet" type="text/css" href="plug-in/planeui/login/css/login.css" />
        
	</head>
	<body>
        <!--[if lte IE 9]>
        <div class="pui-layout pui-browsehappy">
            <a href="javascript:;" class="pui-close" onclick="document.body.removeChild(this.parentNode);"></a>
            <p>您正在使用 <strong class="pui-text-yellow pui-text-xl">过时</strong> 的非现代浏览器，<strong class="pui-text-success pui-text-xl">91.23%</strong> 的人选择 <a href="http://browsehappy.com/" target="_blank" class="pui-text-green-400 pui-text-xl"><strong>升级浏览器</strong></a>，获得了更好、更安全的浏览体验！</p>
        </div>
        <![endif]-->
        <a name="top"></a>
		<div class="pui-layout pui-flexbox pui-flex-column login-layout">
		    <div class="pui-layout pui-flex login-main">
                <div class="pui-layout pui-layout-fixed pui-layout-fixed-1200 login-main-con">
                    <div class="login-panel">
                        <form name="formLogin" id="formLogin"  class="pui-form login-form" action="mLoginController.do?login" check="mLoginController.do?checkuser" method="post">
                            <div class="pui-form-group">
                                <h1 class="pui-text-white pui-text-normal">
                                    <i class="planeui-logo-2x pui-text-xxxxxl"></i>BPM登录 
                                </h1>
                                <div role="username">
                                    <input type="text" name="userName" id="userName"  class="pui-unbordered" placeholder="用户名" />
                                    <i class="fa fa-user pui-text-blue"></i>
                                </div>
                            </div>
                            <div class="pui-form-group">
                                <div role="password">
                                    <input type="password" name="password" id="password"  class="pui-unbordered" placeholder="密码" />
                                    <i class="fa fa-lock pui-text-blue"></i>
                                </div>
                            </div>
                            <div class="pui-form-group">
                                <div role="vcode">
                                    <input type="text" name="randCode" id="randCode" maxlength="4" class="pui-unbordered" placeholder="验证码" />
                                    <i class="fa fa-shield pui-text-blue"></i>
                                    <img src="randCodeImage" id="vcode-img" />
                                </div>
                            </div>
                            <div class="pui-form-group">
                                <input id="but_login" type="button" name="but_login" onclick="checkUser()"  class="pui-btn pui-btn-default pui-bg-blue-800 pui-unbordered" value="登录" />
                                <input id="forgetpass" type="button" name="forgetpass" class="pui-btn pui-btn-default pui-bg-blue-800 pui-unbordered" value="重置" />
                                <span><a href="#" class="pui-text-white pui-text-xs">忘记密码？</a></span>
                            </div>
                        </form>
                    </div>
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

<script type="text/javascript">

function showErrorMsg(msg){
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

//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		checkUser();
	}
});

//update-begin--Author:zhangguoming  Date:20140226 for：添加验证码
$('#vcode-img').click(function(){
    reloadRandCodeImage();
});
/**
 * 刷新验证码
 */
function reloadRandCodeImage() {
    var date = new Date();
    $("#vcode-img").attr("src",'randCodeImage?a=' + date.getTime());
}

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    Login();
  }
  //表单验证
  function validForm(){
    if($.trim($("#userName").val()).length==0){
      showErrorMsg("请输入用户名");
      return false;
    }

    if($.trim($("#password").val()).length==0){
      showErrorMsg("请输入密码");
      return false;
    }

    if($.trim($("#randCode").val()).length==0){
      showErrorMsg("请输入验证码");
      return false;
    }
    return true;
  }

  //登录处理函数
  function Login(orgId) {
    //setCookie();
    var actionurl=$('form').attr('action');//提交路径
    var checkurl=$('form').attr('check');//验证路径
    var formData = new Object();
    var data=$(":input").each(function() {
      formData[this.name] =$("#"+this.name ).val();
    });
    formData['orgId'] = orgId ? orgId : "";
    // update-begin--Author:ken  Date:20140629 for：添加语言选择
    //formData['langCode']=$("#langCode").val();
    // update-end--Author:ken  Date:20140629 for：添加语言选择
    //formData['langCode'] = $("#langCode option:selected").val();
   var loading =  $.dialog({
        type: "loading",
        maskClickClosed : false,
        toolbar: false,
        content : '<div class="pui-loading pui-loading-ring"></div><p style="margin-top:10px;">正在登录中，请稍后....</p>'
    });
    jQuery.ajax({
      type : 'POST',
      url : checkurl,// 请求的action路径
      data : formData,
      error : function() {// 请求失败处理函数
      },
      success : function(data) {
        var d = $.parseJSON(data);
        if (d.success) {
          //loginsuccess();
          window.location.href=actionurl;
       }else{
    	   loading.hide();
    	   showErrorMsg(d.msg);
    	   
       }
      }
    });
  }

  

</script>
	</body>
</html>