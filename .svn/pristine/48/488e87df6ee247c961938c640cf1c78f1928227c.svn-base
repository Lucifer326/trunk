<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
</head>
<body class="add-dis">
<div class="wrapper wrapper-content animated">
    <div class="row">
        <form role="form" class="form-horizontal m-t">
            <div class="form-group draggable">
                <label class="col-sm-2 control-label"><span class="start">*</span>物资品名：</label>
                <div class="col-sm-4">
                    <input type="text" name="" class="form-control">
                </div>
                <label class="col-sm-2 control-label">规格型号：</label>
                <div class="col-sm-4">
                    <input type="text" name="" class="form-control">
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label"><span class="start">*</span>物资类别：</label>
                <div class="col-sm-4">
                    <input type="text" id="txt1" name="" class="form-control fl" style="width: 60%">
                    <button class="btn btn-primary btnh" id="btn1" type="button"><i class="fa fa-search"></i>&nbsp;选择
                    </button>
                </div>
                <label class="col-sm-2 control-label">物资单位：</label>
                <div class="col-sm-4">
                    <select class="form-control chosen-select">
                        <option>-请选择-</option>
                        <option>个</option>
                        <option>双</option>
                        <option>辆</option>
                        <option>箱</option>
                        <option>盒</option>
                    </select>
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">单价(元)：</label>
                <div class="col-sm-4">
                    <input type="text" name="" class="form-control">
                </div>
                <label class="col-sm-2 control-label">供应商：</label>
                <div class="col-sm-4">
                    <input type="text" name="" class="form-control">
                </div>

            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">数量：</label>
                <div class="col-sm-4">
                    <input type="text" name="" class="form-control">
                </div>
                <label class="col-sm-2 control-label">过保日期：</label>
                <div class="col-sm-4">
                    <input id="hello2" class="laydate-icon form-control layer-date">
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">购置日期：</label>
                <div class="col-sm-4">
                    <input id="hello" class="laydate-icon form-control layer-date">
                </div>
                <label class="col-sm-2 control-label">生产日期：</label>
                <div class="col-sm-4">
                    <input id="hello1" class="laydate-icon form-control layer-date">
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">备注：</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="3"></textarea>
                </div>
            </div>
        </form>
    </div>

</div>

<!-- 全局js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- 自定义js -->
<script src="/js/content.js"></script>
<!--时间-->
<script src="/js/plugins/layer/laydate/laydate.js"></script>

<!--编辑器-->
<script src="/js/plugins/summernote/summernote.min.js"></script>
<script src="/js/plugins/summernote/summernote-zh-CN.js"></script>
<!-- 上传 -->
<script src="/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
<!-- 下拉列表 -->
<script src="/js/plugins/chosen/chosen.jquery.js"></script>
<!--固定格式-->
<script src="/js/plugins/jasny/jasny-bootstrap.min.js"></script>

<script>
    laydate({
        elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });
    laydate({
        elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });
    laydate({
        elem: '#hello2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });
    $(document).ready(function () {

        $('.summernote').summernote({
            lang: 'zh-CN'
        });

    });
    $('input[type="file"]').prettyFile();
    $(".chosen-select").chosen();

    $('#btn1').on('click', function () {
        parent.layer.open({
            type: 2,
            title: '选择',
            maxmin: true,
            zIndex: 100,
            shadeClose: true, //点击遮罩关闭层
            area: ['790px', '520px'],
            content: '/page/MaterialManagement/MaterialInfoManagement/SelectMaterialCategory',
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var child = $(layero).find('iframe')[0].contentWindow;
                var result = child.checked();
                if (result != false) {
                    $("#txt1").val(result);
                    parent.layer.close(index);
                }
            }
        });
    });
</script>

</body>
</html>

