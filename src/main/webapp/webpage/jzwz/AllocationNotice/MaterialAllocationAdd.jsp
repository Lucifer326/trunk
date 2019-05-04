﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <style type="text/css">
        .chosen-container {
            width: 100%!important;
        }

        .layer-date {
            max-width: none!important;
        }

        .table {
            border: solid 1px #ddd;
        }

        .table th {
            border: solid 1px #ddd;
        }

        .table td {
            border: solid 1px #ddd!important;
        }
    </style>

</head>
<body class="add-dis">
<div class="wrapper wrapper-content animated">
    <form id="edit_from">
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>基本信息</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <div class="ibox-content">
                <div class="row">
                    <div class="form-horizontal">
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>调拨单据号：</label>
                            <div class="col-sm-4">
                                <%--<input type="hidden" name="uuid" class="form-control"  />--%>
                                <input type="text" name="allotNumber" readonly class="form-control" id="diaoboid" />
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>救灾项目：</label>
                            <div class="col-sm-4">
                                <div id="Div1">
                                    <input type="text" id="txt" name="reloefProject"  class="form-control fl" style="width: 70%" onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>调入部门：</label>
                            <div class="col-sm-4">
                                <input type="text" id="callinDepartment" name="callinDepartment"  class="form-control"
                                       onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');"
                                />
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>调拨日期：</label>
                            <div class="col-sm-4">
                                <input id="hello" name="allotDate"  class="laydate-icon form-control layer-date"
                                       onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');"/>
                            </div>



                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>调出部门：</label>
                            <div class="col-sm-4">
                                <input type="text" id="diaochubum" name="calloutDepartment" class="form-control"
                                       onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');"/>
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>物资用途：</label>
                            <div class="col-sm-4">
                                <div id="file-pretty">
                                    <input type="text" id="wuziuse" name="storageUse" class="form-control"
                                           onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>调拨仓库：</label>
                            <div class="col-sm-4">
                                <div id="Div2">
                                    <input type="hidden" name="repositoryid" readonly="readonly" id="test4">
                                    <input type="text" id="Text4"   readonly="readonly" class="form-control fl" style="width: 70%" />
                                    <button class="btn btn-primary btnh" id="btn2" style="width: 30%" type="button">&nbsp;选择</button>
                                </div>
                            </div>

                        </div>
                        <div id="wuzishuliang" ><%--这里用于存放该仓库的所有的物资数量--%>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>物资编号信息</h5>
            </div>
            <div class="ibox-content">
                <div class="btn-fl">
                    <button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
                    <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>

                </div>
                <div style="width: 100%; float: left; margin-top: 20px;">
                    <table width="100%" cellpadding="0" cellspacing="0" id="mytab" class="crease table table-border">
                        <thead>
                        <tr>
                            <th style="text-align: center; width: 7%">序列</th>
                            <%--<th style="text-align: center;">原来的物资编号</th>--%>
                            <th style="text-align: center;">选择的物资</th>
                            <th style="text-align: center;">调拨数量</th>
                            <th style="text-align: center;">物资折价合计</th>

                        </tr>
                        </thead>
                        <tbody id="tab">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </form>
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
<!-- Peity -->
<script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<!--固定格式-->
<script src="${pageContext.request.contextPath}/js/plugins/jasny/jasny-bootstrap.min.js"></script>

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
    //获取某仓库的物资信息
    $("#wuzishuliang").change(function () {

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/NoticeController.do?wuzixinxiCount",
            //cache缓存，true:缓存；false:不缓存
            cache: false,
            data: $("#test4").val(),
            async:false,
            success: function (data){
                //由于json信息中包含中文，需要解析json
                var da = JSON.parse(data);
                //物资信息uuid和该物资的入库总量

            }

        });


    });




    //调拨仓库的选择
    $('#btn2').on('click', function () {

        var form;
        parent.layer.open({
            type: 2,
            title: '调拨仓库',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['800px', '500px'],
            zIndex: 1200,
            content: 'NoticeController.do?repisorityhouse',
            btn: ['确定', '取消'],
            yes: function (index, layero) {

                var child = $(layero).find('iframe')[0].contentWindow;
                var result = child.CheckSelect1();

                if (result != false) {
                    var data = result.split(",");
                    $("#test4").val(data[0]);
                    $("#Text4").val(data[1]);

                    parent.layer.close(index);
                }
            }
        });
    });


    function a(obj){
        var str;
        var str1;
        //var obj=$(this);
        shijian(obj);
        function shijian(){
            parent.layer.open({
                type: 2,
                title: '物资选择',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                zIndex: 1200,
                content: 'protocalController.do?materialCategory',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //获取子窗口表单数据getElementById("replist").value();

                    var form = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (form != null) {
                        var data = form.split(",");

                        str=data[0];
                        str1=data[4];
                        //$(".Button1").parent().find("input").val(str);
                        parent.layer.close(index);

                    }


                    $(obj).parent().parent().find("input").val(str1);
                    $(obj).parent().find("input").val(str);

                }

            });

        }
    }
    //物资信息的选择
    $('.Button1').on('click', function () {

        var str;
        var str1;
        var obj=$(this);
        var id = obj.id;


        shijian(obj);

        function shijian(){
            parent.layer.open({
                type: 2,
                title: '物资选择',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                zIndex: 1200,
                content: 'protocalController.do?materialCategory',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //获取子窗口表单数据getElementById("replist").value();

                    var form = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (form != null) {
                        var data = form.split(",");

                        str=data[0];
                        str1=data[4];

                        //$(".Button1").parent().find("input").val(str);
                        parent.layer.close(index);

                    }



                }

            });

        }

    });

    setTimeout(function () {
        laydate({
            elem: "#hi" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        }, 1000);
    });


    //增加
    var i = 0;
    $('#Add').on('click', function () {

        //物资信息的选择
        parent.layer.open({
            type: 2,
            title: '物资选择',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            zIndex: 101,
            area: ['800px', '500px'],
            content: 'protocalController.do?materialCategory',
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                //获取子窗口表单数据getElementById("replist").value();

                var form = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                if (form != false) {
                    var data = form.split(',');


                    i++;
                    var tabObj = document.getElementById("mytab");//获取添加数据的表格
                    var bodyObj = document.getElementById("tab");//获取添加数据的表格
                    var rowsNum = tabObj.rows.length;
                    var rowsNum2 = bodyObj.rows.length;
                    var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
                    var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
                    var newTdObj1 = myNewRow.insertCell(0);
                    newTdObj1.innerHTML = "<input type='checkbox' name='check_list'  style='border:0px' values='' /> "
                    +"<input type='hidden'  value='${de.uuid}' />";

                    var newTdObj2 = myNewRow.insertCell(1);
                    newTdObj2.innerHTML = "<input type='hidden' name='messageid'  value='"+data[4]+"'/>"
                        + "<div id='Div3'>"<%--这是物资的主键id--%>
                        + "<input type='text' id='mess2' name='mess' readonly='readonly' value='"+data[0]+"' class='form-control fl text' style='width: 100%;text-align:center;' />"
                        + "</div>";

                        // + "<button class='btn btn-primary btnh bt ' id='btn" + i + "' onclick='a(this)' style='width: 45%' type='button'>&nbsp;选择</button>"

                    var newTdObj3 = myNewRow.insertCell(2);
                    newTdObj3.innerHTML = "<input type='text' required name='allotAmount' class='form-control' onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"  \n" +
                        "    onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\\D/g,'')}\" onchange='jisuan1(this)' onkeyup=\"this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');\"/>";
                    var newTdObj4 = myNewRow.insertCell(3);
                    newTdObj4.innerHTML = "<input type='text' readonly name='priceTotal' class='form-control' />"
                            +"<input type='hidden' name='pri'  value='"+data[7]+"'  />";
                    //-----------------------------------------------

                    $("input[name=allotAmount]").change(function () {
                        if($(this).val()!=''){
                            var num = parseFloat($(this).val())*parseFloat($(this).parent().parent().find("input[name=pri]").val());
                            var newnum = Math.ceil(num);
                            $(this).parent().parent().find("input[name=priceTotal]").val(newnum) ;
                        }else{
                            $(this).parent().parent().find("input[name=priceTotal]").val(0);
                        }

                    });


                }

                parent.layer.close(index);

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal({
                    title: "请检查数据是否填写完整",
                    zIndex: 19891014,
                    type: "warning",
                    confirmButtonText: "确定"
                });
            }

        });


    });




    //删除
    $('#del').click(function () {
        var chkObj = document.getElementsByName("check_list");
        var tabObj = document.getElementById("mytab");

        for (var k = 0; k < chkObj.length; k++) {

            if (chkObj[k].checked) {
                tabObj.deleteRow(k + 1);
                k = -1;
            }
        }

    });
    //自动生成调拨单
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

    $("#diaoboid").val(getDateString("yyyyMMdd-HHmmss","DB-"));




    $('input[type="file"]').prettyFile();
    $(".chosen-select").chosen();
</script>
</body>
</html>

