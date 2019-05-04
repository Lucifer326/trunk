<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal m-t">
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">物资名称：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.sub_name}</div>
                    </div>
                    <label class="col-sm-2 control-label">规格型号：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.specification_type}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">物资类别：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.category_name}</div>
                    </div>
                    <label class="col-sm-2 control-label">计量单位：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.unit}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">物资来源：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.substance_source}</div>
                    </div>
                    <label class="col-sm-2 control-label">是否定向：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.isorienteering}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">捐赠用途：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.donation_purposes}</div>
                    </div>
                    <label class="col-sm-2 control-label">捐赠区域：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.donate_area}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">是否可回收：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">
                            <c:choose>
                                <c:when test="${detil.isrecycle eq 1}">是</c:when>
                                <c:when test="${detil.isrecycle eq 0}">否</c:when>
                                <c:otherwise>${detil.isrecycle}</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <label class="col-sm-2 control-label">出库数量：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${detil.outhouse_amount}</div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js"></script>
    <!--时间-->
    <script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
    <!--编辑器-->
    <script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
    <!-- 上传 -->
    <script src="${pageContext.request.contextPath}/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
    <!-- Peity -->
    <script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- 下拉列表 -->
    <script src="${pageContext.request.contextPath}/js/plugins/chosen/chosen.jquery.js"></script>
    <!--固定格式-->
    <script src="${pageContext.request.contextPath}/js/plugins/jasny/jasny-bootstrap.min.js"></script>

    <script>
        //外部js调用
        //laydate({
        //    elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        //});
        //laydate({
        //    elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        //});
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
