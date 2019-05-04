<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收件箱</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12 animated fadeInRight">
                <div class="mail-box-header">
                    <div class="pull-right tooltip-demo">
                    </div>
                    <h2>
                    查看邮件
                </h2>
                    <a href="javascript:void(0);" onclick="window.opener=null; window.open('','_self');window.close();">返回收件箱</a>
                    <div class="mail-tools tooltip-demo m-t-md">


                        <h3>
                        <span class="font-noraml">主题： </span>${mail.title}
                    </h3>
                        <h5>
                        <span class="pull-right font-noraml">${mail.date}</span>
                        <span class="font-noraml">发件人： </span>${mail.sender}
                    </h5>
                    </div>
                </div>
                <div class="mail-box">


                    <div class="mail-body">
                        ${mail.content}
                    </div>

                    <div class="clearfix"></div>


                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>    
</body>
</html>
