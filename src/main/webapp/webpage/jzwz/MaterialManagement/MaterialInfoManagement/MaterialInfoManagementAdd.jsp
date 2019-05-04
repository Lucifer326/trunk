<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
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
        <form role="form" class="form-horizontal m-t" action="MaterialManagement.do?saveRepSubstanceMessage"method="post" id="createRepSubstanceMessage">
            <div class="form-group draggable">
                <label class="col-sm-2 control-label"><span class="start">*</span>物资编号：</label>
                <div class="col-sm-4">
                    <input type="text" name="subNumber" class="form-control" id="subNumber" readonly>
                </div>
                <label class="col-sm-2 control-label"><span class="start">*</span>物资品名：</label>
                <div class="col-sm-4">
                    <input type="text" name="subName" class="form-control" id="subName"
                    onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？《》 ]/g,'');">
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label"><span class="start">*</span>物资类别：</label>
                <div class="col-sm-4">
                    <input type="text" id="txt1" name="subCategory" readonly="readonly" class="form-control fl" style="width: 60%">
                    <button class="btn btn-primary btnh" id="btn1" type="button"><i class="fa fa-search"></i>&nbsp;选择
                    </button>
                </div>

                <label class="col-sm-2 control-label">规格型号：</label>
                <div class="col-sm-4">
                    <input type="text" name="specificationType" class="form-control" id="specificationType">
                </div>
            </div>
            <div class="form-group draggable">

                <label class="col-sm-2 control-label">是否可回收：</label>
                <div class="col-sm-4">
                    <select class="form-control chosen-select" name="isrecycle" id="isrecycle">
                        <option value="">-请选择-</option>
                        <option  value="1">是</option>
                        <option  value="0">否</option>
                    </select>
                </div>
                <label class="col-sm-2 control-label">供应商：</label>
                <div class="col-sm-4">
                    <input type="text" name="supplier" class="form-control" id="supplier">
                </div>

            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">单位：</label>
                <div class="col-sm-4">
                    <select class="form-control chosen-select" name="unit" id="unit">
                        <option value="">-请选择-</option>
                        <option>个</option>
                        <option>双</option>
                        <option>辆</option>
                        <option>箱</option>
                    </select>
                </div>
                <label class="col-sm-2 control-label">单价(元)：</label>
                <div class="col-sm-4">
                    <input type="text" name="price" class="form-control" id="price">
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">备注：</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="3" name="remark" id="remark"></textarea>
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
    // laydate({
    //     elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    //     event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    // });
    // laydate({
    //     elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    //     event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    // });
    // laydate({
    //     elem: '#hello2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    //     event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    // });
    function getDateString(patt,prefix,suffix) {
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
        var str = new Date().Format(patt);
        if (prefix!=undefined && prefix!=''){
            str = prefix + str;
        }
        if (suffix!=undefined && suffix!=''){
            str += suffix;
        }
        return str;
    }
    $("#subNumber").val(getDateString("yyyyMMdd-HHmmss","WZ-"));
    $(document).ready(function () {

        $('.summernote').summernote({
            lang: 'zh-CN'
        });
    });
    $('input[type="file"]').prettyFile();
    $(".chosen-select").chosen();

    $(document).on('click','#btn1', function () {
        parent.layer.open({
            type: 2,
            title: '选择',
            maxmin: true,
            zIndex: 100,
            shadeClose: true, //点击遮罩关闭层
            area: ['790px', '520px'],
            content: 'MaterialManagement.do?MaterialCategoryList',
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

