<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看页面</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal">
                <div class="form-group draggable">

                    <label class="col-sm-2 control-label">协议编号(合同号)：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.protocolNumber}</div>
                    </div>
                    <label class="col-sm-2 control-label">协议签订日期：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly"><fmt:formatDate value="${protocol.protocolDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
                    </div>
                </div>

                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">协议储备物资：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.protocolStorage}</div>
                    </div>
                    <label class="col-sm-2 control-label">物资规格：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.storageSize}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">协议储备数量：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.protocolAmount}</div>
                    </div>
                    <label class="col-sm-2 control-label">协议年限：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.protocolYear}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">协议单价：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${protocol.protocolPrice}</div>
                    </div>
                </div>
                <div class="form-group draggable">

                    <label class="col-sm-2 control-label">协议公司：</label>
                    <div class="col-sm-10">
                        <div class="form-control" readonly="readonly">${protocol.protocolCompany}</div>
                    </div>
                </div>
            </form>
        </div>

    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="js/content.js"></script>
    <!--时间-->
    <script src="js/plugins/layer/laydate/laydate.js"></script>
    <!--编辑器-->
    <script src="js/plugins/summernote/summernote.min.js"></script>
    <script src="js/plugins/summernote/summernote-zh-CN.js"></script>
    <!-- 上传 -->
    <script src="js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
    <!-- 下拉列表 -->
    <script src="js/plugins/chosen/chosen.jquery.js"></script>
    <!--固定格式-->
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>

    <script>
        //外部js调用
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        $(document).ready(function () {

            $('.summernote').summernote({
                lang: 'zh-CN'
            });

        });
        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();

    </script>

</body>
</html>
