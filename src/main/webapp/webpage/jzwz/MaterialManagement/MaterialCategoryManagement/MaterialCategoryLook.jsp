<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看内容</title>
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
            <form role="form" class="form-horizontal">
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资类别编号：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${category.categoryNumber}</div>
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资类别名称：</label>
                    <div class="col-sm-4">
                        <div class="form-control" readonly="readonly">${category.categoryName}</div>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">备注：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="5" readonly="readonly">${category.remark}</textarea>
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
    <!-- 下拉列表 -->
    <script src="${pageContext.request.contextPath}/js/plugins/chosen/chosen.jquery.js"></script>
    <!--固定格式-->
    <script src="${pageContext.request.contextPath}/js/plugins/jasny/jasny-bootstrap.min.js"></script>

    <script>
        //外部js调用       
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
