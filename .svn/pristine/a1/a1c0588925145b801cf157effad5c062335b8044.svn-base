<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新建</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal m-t" id="ProtocolReserveInfoEditForm">
                <div class="form-group draggable">
                    <div class="col-sm-4">
                        <input type="hidden" name="uuid" class="form-control" value="${protocol.uuid}" placeholder="">
                    </div>

                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议编号(合同号)：</label>
                    <div class="col-sm-4">
                        <input type="text" name="protocolNumber" class="form-control" value="${protocol.protocolNumber}" placeholder="" readonly>
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议签订日期：</label>
                    <div class="col-sm-4">
                        <input id="hello1" readonly name="protocolDate" class="laydate-icon form-control layer-date" value="<fmt:formatDate value="${protocol.protocolDate}" pattern="yyyy-MM-dd"></fmt:formatDate>">
                    </div>

                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议储备物资：</label>
                    <div class="col-sm-4">
                        <div id="Div5">
                            <input type="text" id="txtin" name="protocolStorage" value="${protocol.protocolStorage}" class="form-control fl" style="width: 70%" readonly>
                            <!--<input type="button" class="form-control" value="选择" style=" width:30%" id="btn" >-->
                            <button class="btn btn-primary btnh" id="btnin" style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                        </div>
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资规格：</label>
                    <div class="col-sm-4">
                        <input type="text" name="storageSize" id="txtTypee" class="form-control" value="${protocol.storageSize}" placeholder="" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]/g,'');">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议储备数量：</label>
                    <div class="col-sm-4">
                        <input type="number" name="protocolAmount" id="txtWork" class="form-control" value="${protocol.protocolAmount}" placeholder="">
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议年限：</label>
                    <div class="col-sm-4">
                        <input type="number" name="protocolYear" class="form-control" id="protocolYear" value="${protocol.protocolYear}" placeholder="">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议单价：</label>
                    <div class="col-sm-4">
                        <input type="number" name="protocolPrice" id="Text1" class="form-control" value="${protocol.protocolPrice}" placeholder="">
                    </div>
                </div>
                <div class="form-group draggable">

                    <label class="col-sm-2 control-label"><span class="start">*</span>协议公司：</label>
                    <div class="col-sm-10">
                        <div id="Div1">
                            <input type="text" id="txt1" name="protocolCompany" value="${protocol.protocolCompany}" class="form-control fl" style="width: 89%" readonly>
                            <!--<input type="button" class="form-control" value="选择" style=" width:30%" id="btn" >-->
                            <button class="btn btn-primary btnh" id="btn1" style="width: 11%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                        </div>
                    </div>
                </div>




                <!--   <div class="form-group draggable">
                     <label class="col-sm-2 control-label">物质储备量参考：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" placeholder="">
                    </div>
                </div>-->
                <!--  <div class="form-group draggable">
                    <label class="col-sm-2 control-label">备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 注：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="3"></textarea>
                    </div>
                </div>-->

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
    <script src="js/layer/layer.min.js"></script>
    <script>
        //外部js调用
        laydate({
            elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        $('#btnin').on('click', function () {

            parent.layer.open({
                type: 2,
                title: '协议储备物资',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['840px', '520px'],
                zIndex: 101,
                content: 'protocalController.do?materialCategory',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.checked();
                    if (data != false) {
                        $("#txtin").val(data);
                        parent.layer.close(index);
                    }
                }

            });
        });
        $('#btnout').on('click', function () {

            parent.layer.open({
                type: 2,
                title: '组织机构',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['840px', '520px'],
                zIndex: 101,
                content: '/DisasterCapitalManage/SuperiorFinancing/SelectOrg.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        $("#txtout").val(data);
                        parent.layer.close(index);
                    }
                }

            });
        });
        $('#btn1').on('click', function () {

            parent.layer.open({
                type: 2,
                title: '物资生产商',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['840px', '520px'],
                zIndex: 101,
                content: 'protocalController.do?materialProducer',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        $("#txt1").val(data);
                        parent.layer.close(index);

                    }
                }
            });
        });
        $('#btn2').on('click', function () {

            parent.layer.open({
                type: 2,
                title: '救灾申请',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['860px', '520px'],
                zIndex: 101,
                content: 'MaterialAllocationApply.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        $("#txtShen").val(data);
                        parent.layer.close(index);
                    }
                }

            });
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
