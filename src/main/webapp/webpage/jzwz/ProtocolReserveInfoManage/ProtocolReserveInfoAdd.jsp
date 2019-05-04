﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <form id="ProtocolReserveInfoAddForm" role="form" class="form-horizontal m-t">
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议编号(合同号)：</label>
                    <div class="col-sm-4">
                        <input type="text" name="protocolNumber" id="protocolNumber" readonly class="form-control" placeholder="" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议签订日期：</label>
                    <div class="col-sm-4">
                        <input id="hello1" readonly name="protocolDate" class="laydate-icon form-control layer-date">
                    </div>

                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议储备物资：</label>
                    <div class="col-sm-4">
                        <div id="Div5">
                            <input type="text" id="txtin" name="protocolStorage" class="form-control fl" style="width: 70%" readonly>
                            <input type="hidden" id="txtin1" name="protocolStorageId" class="form-control fl" style="width: 70%">
                            <!--<input type="button" class="form-control" value="选择" style=" width:30%" id="btn" >-->
                            <button class="btn btn-primary btnh" id="btnin" style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                        </div>
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资规格：</label>
                    <div class="col-sm-4">
                        <input type="text" readonly name="storageSize" id="txtTypee" class="form-control" placeholder="">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议储备数量：</label>
                    <div class="col-sm-4">
                        <input type="text" name="protocolAmount" id="txtWork" class="form-control" placeholder="" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议年限：</label>
                    <div class="col-sm-4">
                        <input type="text" min="1" name="protocolYear" id="protocolYear" class="form-control" placeholder="" >
                        <%--onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"--%>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议单价：</label>
                    <div class="col-sm-4">
                        <input type="text" name="protocolPrice" id="Text1" class="form-control" placeholder="" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>协议公司：</label>
                    <div class="col-sm-10">
                        <div id="Div1">
                            <input type="text" id="txt1" name="protocolCompany" class="form-control fl" style="width: 89%" readonly>
                            <input type="hidden" id="txt2" name="protocolCompanyId" class="form-control fl" style="width: 89%">
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
                        var str = data.split(',');
                        $("#txtin").val(str[0]);
                        $("#txtin1").val(str[1]);
                        $("#txtTypee").val(str[2]);
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
                content: '/DisasterCapitalManage/SuperiorFinancing/SelectOrg',
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
                        var str = data.split(',')
                        $("#txt1").val(str[0]);
                        $("#txt2").val(str[1]);
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
                content: 'MaterialAllocationApply',
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

        <%--
           刘雨泽
           获取当前系统时间 prefix+patt+suffix格式的字符串,在咱这里可以用来获取单号啥的
       --%>

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

        $("#protocolNumber").val(getDateString("yyyyMMdd-HHmmss","CB-"));

    </script>

</body>
</html>
