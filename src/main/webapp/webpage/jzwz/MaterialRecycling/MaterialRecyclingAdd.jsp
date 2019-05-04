﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>列表页</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet" />
    <link href="css/animate.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/plugins/chosen/chosen.css" rel="stylesheet" />
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
    <script>



    </script>

</head>
<body class="add-dis">
<form id="recycleAddForm">
    <div class="wrapper wrapper-content animated">
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>基本情况</h5>
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
                            <label class="col-sm-2 control-label"><span class="start">*</span>单据号：</label>
                            <div class="col-sm-4">
                                <input type="text" id="Text1" readonly class="form-control" name="recycleNumber" placeholder="" />
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库仓库：</label>
                            <div class="col-sm-4">
                                <input type="text" id="house" readonly name="storageWarehouse" class="form-control fl" style="width: 70%" />
                                <button class="btn btn-primary btnh" id="btna" style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                                <input type="hidden" id="houseid" name="repositoryId" class="form-control fl" style="width: 70%" />
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库人：</label>
                            <div class="col-sm-4">
                                <input type="text" id="Text2" class="form-control" name="storagePerson" placeholder="" onkeydown="if(event.keyCode==32||event.keyCode==13){return false;}" />
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库日期：</label>
                            <div class="col-sm-4">
                                <input id="hello" readonly class="laydate-icon form-control layer-date" value="" name="storageDate"/>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>出库单：</label>
                            <div class="col-sm-4">
                                <input type="text" id="outHouseNumber" readonly name="outHouseNumber" class="form-control fl" style="width: 70%" />
                                <button class="btn btn-primary btnh" id="selectbtn" style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                                <input type="hidden" id="outHouseId" name="outhouseId" class="form-control fl" style="width: 70%" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>回收物资明细</h5>

            </div>
            <div class="ibox-content">
                <div class="btn-fl">
                    <button type="button" class="btn btn-outline btn-primary" id="Add" onclick="addNewRow();">新建</button>
                    <button type="button" class="btn btn-outline btn-danger" id="del" onclick="removeRow();">删除</button>
                </div>
                <div style="width: 100%; float: left; margin-top: 20px;">
                    <table width="100%" cellpadding="0" cellspacing="0" id="myTab" class="crease table table-border">
                        <thead>
                            <tr>
                                <th align="center">序列</th>
                                <th align="center">物资品名</th>
                                <th align="center">规格型号</th>
                                <th align="center">物资类别</th>
                                <th align="center">计量单位</th>
                                <th align="center">回收数量</th>
                                <th align="center" class="this_td">物资标识</th>
                                <th align="center" class="this_td">物资入库详细标识</th>
                            </tr>
                        </thead>
                        <!--数据部分-->
                        <tbody id="tab">
                            <%--<tr>
                                <td>
                                    <input type="checkbox" name="chkArr" value='14@25@' />
                                </td>
                                <td>
                                    <input type="text" id="name" name="subName" class="form-control fl" style="width: 55%; margin-right: 10px" />
                                    <button class="btn btn-primary btnh fl" id="btn" onclick="a(this)" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                                </td>
                                <td>
                                    <input type="text" id="size" name="" class="form-control" />
                                </td>
                                <td>
                                    <input type="text" id="type" name="specificationType" class="form-control" />
                                </td>
                                <td>
                                    <input type="text" name="unit" id="unit" class="form-control" />
                                </td>
                                <td>
                                    <input type="text" name="" id="num" class="form-control" />
                                </td>
                            </tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- 全局js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js?v=3.4.0"></script>
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
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/my.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="js/demo/bootstrap-table-demo.js"></script>
    <script src="js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>


    <script>
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click

        });

        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();

        //窗口表格增加一行
        var u = 0;
        function addNewRow() {
            outHouseId = $("#outHouseId").val();
            if(outHouseId == ''){//判断是否有值
                layer.alert("请选择出库单");
                return false;
            }


            u++;
            var tabObj = document.getElementById("myTab");//获取添加数据的表格
            var bodyObj = document.getElementById("tab");//获取添加数据的表格
            var rowsNum = tabObj.rows.length;
            var rowsNum2 = bodyObj.rows.length;
            var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
            var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
            var newTdObj1 = myNewRow.insertCell(0);
            newTdObj1.innerHTML = "<input type='checkbox' readonly name='chkArr' id='chkArr'" + rowsNum + " style='border:0px' /> ";
            var newTdObj2 = myNewRow.insertCell(1);
            newTdObj2.innerHTML = "<input type='text' readonly name='subName' id='subName' class='form-control fl bunengweikong' style='width: 55%; margin-right: 10px'>  <button class='btn btn-primary btnh fl' onclick='a(this)' id='btn" + u + "' type='button'><i class='fa fa-search'></i>&nbsp;选择</button>";
            var newTdObj3 = myNewRow.insertCell(2);
            newTdObj3.innerHTML = "<input type='text' id='' readonly name='specificationType' class='form-control'>";
            var newTdObj4 = myNewRow.insertCell(3);
            newTdObj4.innerHTML = "<input type='text' id='' readonly name='subCategory' class='form-control'>";
            var newTdObj5 = myNewRow.insertCell(4);
            newTdObj5.innerHTML = "<input type='text' name='unit' readonly class='form-control'>";
            var newTdObj6 = myNewRow.insertCell(5);
            newTdObj6.innerHTML = "<input type='number' name='backNum' value='1' step='1' min='1' class='form-control' onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" onkeydown=\"if(event.keyCode==32||event.keyCode==13){return false;}\">";
            var newTdObj7 = myNewRow.insertCell(6);
            newTdObj7.innerHTML = "<input type='text' name='uuid' class='this_td' style='display: none'>";
            newTdObj7.style.display="none";
            var newTdObj8 = myNewRow.insertCell(7);
            newTdObj8.innerHTML = "<input type='text' name='storageDetail' class='this_td' style='display: none'>";
            newTdObj8.style.display="none";
            setTimeout(function () {
                laydate({
                    elem: "#hi" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
                }, 1000);
            })
        }
        $(function () {
            $(".this_td").hide();
        })

        //窗口表格删除一行
        function removeRow() {
            var chkObj = document.getElementsByName("chkArr");
            var tabObj = document.getElementById("myTab");
            for (var k = 0; k < chkObj.length; k++) {
                if (chkObj[k].checked) {
                    tabObj.deleteRow(k + 1);
                    k = -1;
                }
            }
        }
        function a(obj) {
            var id = obj.id;
            parent.layer.open({
                type: 2,
                title: '物资信息',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['840px', '520px'],
                zIndex: 101,
                content: '${url}/recycleController.do?materialInfoSelect&outHouseId=' + outHouseId,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var child = $(layero).find('iframe')[0].contentWindow;
                    var result = child.checked();
                    var arr = new Array();
                    arr = result.split(",");
                    if (result != false) {
                        //判断是否重复
                        //alert($(obj).parent().parent().find("input[name=subName]").val());
                        /*var flag = false;
                        $.each($(obj).parent().parent().parent().parent().find("input[name=subName]"),function(index1,obj1){
                            if ($(this).val() == str[0] && $(ele).parent().parent().parent().find("input[name=subName]").val() == $(this).parent().parent().parent().find("input[name=subName]").val()){
                                flag = true;
                                return false;
                            }
                        });
                        if (flag){
                            parent.layer.close(index);
                            layer.alert("已存在该物资,请修改数量或选择其他物资");
                            return false;
                        }*/


                        var name = arr[0];// 品名
                        var prop = arr[1];// 类别
                        var spec = arr[2];// 规格
                        var type = arr[3];// 单位
                        var uuid = arr[4];// uuid
                        var storageDetail = arr[5];// 入库详细id
                        $("#" + id).prev().val(name);
                        $("#" + id).parent().next().children("input").val(spec);
                        $("#" + id).parent().next().next().children("input").val(prop);
                        $("#" + id).parent().next().next().next().children("input").val(type);
                        $("#" + id).parent().next().next().next().next().next().children("input").val(uuid);
                        $("#" + id).parent().next().next().next().next().next().next().children("input").val(storageDetail);
                        parent.layer.close(index);
                    }
                }
            });
        }

        $('#btna').on('click', function () {
            parent.layer.open({
                type: 2,
                title: '入库仓库',
                maxmin: true,
                zIndex: 101,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: '${url}/recycleController.do?selectWarehouse',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var child = $(layero).find('iframe')[0].contentWindow;
                    var result = child.CheckSelect();
                    var arr = new Array();
                    arr = result.split(",");
                    if (result != false) {
                        var name = arr[0];// 名称
                        var houseid = arr[1];// id
                        $("#house").val(name);
                        $("#houseid").val(houseid);
                        parent.layer.close(index);
                    }
                }
            });
        });
        $('#selectbtn').on('click', function () {
            parent.layer.open({
                type: 2,
                title: '出库单',
                maxmin: true,
                zIndex: 101,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: '${url}/recycleController.do?selectOutHouse',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var child = $(layero).find('iframe')[0].contentWindow;
                    var result = child.CheckSelect();
                    var arr = new Array();
                    arr = result.split(",");
                    if (result != false) {
                        var outHouseNumber = arr[0];// 名称
                        var outHouseId = arr[1];// id
                        $("#outHouseNumber").val(outHouseNumber);
                        $("#outHouseId").val(outHouseId);
                        parent.layer.close(index);
                    }
                }
            });
        });
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

        $("#Text1").val(getDateString("yyyyMMdd-HHmmss","HS-"));
    </script>
</form>
</body>
</html>
