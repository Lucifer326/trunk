<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .chosen-container
        {
            width: 100%!important;
        }

        .layer-date
        {
            max-width: none!important;
        }

        .table
        {
            border: solid 1px #ddd;
        }

            .table th
            {
                border: solid 1px #ddd;
            }

            .table td
            {
                border: solid 1px #ddd!important;
            }
    </style>
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <form id="add_form" enctype="multipart/form-data" >
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
                                <label class="col-sm-2 control-label"><%--<span class="start">*</span>--%>出库单号：</label>
                                <div class="col-sm-4">
                                    <input type="text" readonly id="outhouseNumber" name="outhouseNumber" class="form-control" />
                                </div>
                                <label class="col-sm-2 control-label"><span class="start">*</span>出库人：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="outhousePerson" id="outhousePerson" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':; '\\,\[\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？]/g,'');" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group draggable">
                                <label class="col-sm-2 control-label"><span class="start">*</span>出库日期：</label>
                                <div class="col-sm-4">
                                    <input id="hello" name="outhouseDate" class="laydate-icon form-control layer-date"  />
                                </div>
                                <label class="col-sm-2 control-label"><span class="start">*</span>领取人：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="receiver"  id="receiver" class="form-control" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':; '\\,\[\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？]/g,'');" />
                                </div>
                            </div>
                            <div class="form-group draggable">
                                <label class="col-sm-2 control-label">附件：</label>
                                <div class="col-sm-4">
                                    <div id="file-pretty">
                                        <input type="file"  name="file" class="form-control" />
                                    </div>
                                </div>
                                <label class="col-sm-2 control-label"><span class="start">*</span>调拨单：</label>
                                <div class="col-sm-4">
                                    <div id="Div2">
                                        <input type="text" id="Text1" name="" readonly="readonly" class="form-control fl" style="width: 70%" />
                                        <input type="hidden" id="requisitionId" name="requisition">
                                        <button class="btn btn-primary btnh" id="btn2" style="width: 30%" type="button">&nbsp;选择</button>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group draggable">
                                <label class="col-sm-2 control-label"><span class="start">*</span>出库仓库：</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="warehouseId" name="warehouseId"/>
                                    <input type="text" id="txt" name="repositoryName" readonly="readonly" class="form-control fl" style="width: 70%" />
                                    <button class="btn btn-primary btna" id="btn" style="width: 30%" type="button">&nbsp;选择</button>
                                </div>
                            </div>
                            <div class="form-group draggable">
                                <label class="col-sm-2 control-label">备注：</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="remark" id="remark" rows="3" onkeyup="this.value=this.value.replace(/[`%#$^&*()=|{}':; '\\,\[\]<>/#￥……&*（）_|{}']/g,'');"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ibox float-e-margins">
                <div class="ibox-title whj-see-ibox">
                    <h5>物资信息</h5>
                </div>
                <div class="ibox-content">
                    <div class="btn-fl">
                        <button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
                        <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
                        <%--<button type="button" class="btn btn-outline btn-success" id="Look">查看</button>--%>
                    </div>
                    <div style="width: 100%; float: left; margin-top: 20px;">
                        <table width="100%" cellpadding="0" cellspacing="0" id="mytab" class="crease table table-border">
                            <thead>
                                <tr>
                                    <th style="text-align: center; width: 7%">序列</th>
                                    <%--<th style="text-align: center;">物资编号</th>--%>
                                    <th style="text-align: center;">物资品名</th>
                                    <th style="text-align: center;">调拨数量</th>
                                    <th style=".">入库单号</th>
                                    <%--<th style="text-align: center;">出库仓库</th>--%>
                                    <th style=".">库存</th>
                                    <th style=".">出库数量</th>
                                    <%--<th style="text-align: center;">物资类别</th>
                                    <th style="text-align: center;">规格型号</th>
                                    <th style="text-align: center;">计量单位</th>
                                    <th style="text-align: center;">出库数量</th>
                                    <th style="text-align: center;">是否可回收</th>--%>
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

        <%--
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
        $("#outhouseNumber").val(getDateString("yyyyMMdd-HHmmss","CK-"));

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
        //选择出库仓库
        $('#btn').on('click', function () {
            parent.layer.open({
                type: 2,
                title: '出库仓库',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                zIndex: 1200,
                content: '${pageContext.request.contextPath}/materialsInputController.do?selectWarehouse',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        var str = data.split(',');
                        $("#txt").val(str[0]);
                        $("#warehouseId").val(str[1]);
                        // $("#storageWarehouse").val(str[1]);
                        layer.close(index);
                    }
                    parent.layer.close(index);
                    // $("#tab").find("input[name=storageNumber]").val('');
                    // $("#tab").find("input[name=storageDetail]").val('');
                    // $("#tab").find("input[name=amount]").val('');
                    $("#tab").empty()
            }
            });
        });


        $('#btn2').on('click', function () {
            parent.layer.open({
                type: 2,
                title: '调拨通知单',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                zIndex: 1200,
                content: '${pageContext.request.contextPath}/recycleController.do?seachAllocation&isUse=1',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    // $("#Text1").val("调拨单据号1000");
                    //获取调拨单的单号,物资信息和物资对应的调拨数量
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        var str = data.split(',');
                        $("#Text1").val(str[0]);
                        $("#requisitionId").val(str[4]);
                        parent.layer.close(index);
                        $("#tab").empty();
                    }
                }
            });
        });

        //增加物资信息
        var i = 0;
        $('#Add').on('click', function () {
            var requisitionId = $("#requisitionId").val();
            if(requisitionId == ''){//判断是否有值
                layer.alert("请选择调拨单");
                return false;
            }
            //判断出库仓库是否已选择,若没有选择则提示选择仓库
            var warehouseId = $("#warehouseId").val();
            if(warehouseId == ''){//判断是否有值
                layer.alert("请选择仓库");
                return false;
            }
            //获取调拨单的id（隐藏域）
            var requisitionId = $("#requisitionId").val();
            parent.layer.open({
                type: 2,
                title: '选择物资',
                maxmin: true,
                zIndex: 1000,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?materialCategory&uuid='+requisitionId,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        var str = data.split(',');
                        parent.layer.close(index);
                        i++;
                        var tabObj = document.getElementById("mytab");//获取添加数据的表格
                        var bodyObj = document.getElementById("tab");//获取添加数据的表格
                        var rowsNum = tabObj.rows.length;
                        var rowsNum2 = bodyObj.rows.length;
                        var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
                        var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
                        var newTdObj1 = myNewRow.insertCell(0);
                        newTdObj1.innerHTML = "<input type='checkbox' name='check_list'  style='border:0px' /> ";
                        var newTdObj3 = myNewRow.insertCell(1);//物资名称
                        newTdObj3.innerHTML = "<input type='text' readonly name='subName' id='subName' class='form-control' value='"+str[0]+"' />"+
                                "<input type='hidden' name='subId' class='form-control' value='"+str[2]+"' />";
                        var newTdObj4 = myNewRow.insertCell(2);//调拨数量,应该显示调拨单中的调拨数量
                        newTdObj4.innerHTML = "<input type='text' readonly name='allotAmount' id='allotAmount' class='form-control' value='"+str[1]+"' />";
                        var newTdObj5 = myNewRow.insertCell(3);//调拨数量
                        newTdObj5.innerHTML = "<div id='Div3'>"
                            + "<input type='text' readonly  name='storageNumber' id='storageNumber' class='form-control fl text' style='width: 55%'/>"
                            + "<input type='hidden' name='storageDetail'/>"
                            + "<button class='btn btn-primary btnh bt SelectStorage' warehouseId='"+warehouseId+"' subId='"+str[2]+"' style='width: 45%' type='button' >&nbsp;选择</button>"
                            + "</div>";
                        var newTdObj7 = myNewRow.insertCell(4);//库存,入库详细中的库存量
                        newTdObj7.innerHTML = "<input type='text' readonly name='amount' id='amount' class='form-control'/>";
                        var newTdObj8 = myNewRow.insertCell(5);//出库量,用户手动输入
                        newTdObj8.innerHTML = '<input type="number" min="1" required name="outhouseAmount" id="outhouseAmount" class="form-control" />';
                    }
                }
            });
            setTimeout(function () {
                laydate({
                    // elem: "#hi" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
                }, 1000);
            });
        });

        $(document).on("click",".SelectStorage",function () {
            var warehouseId = $(this).attr("warehouseId");

            var subId = $(this).attr("subId");

            SelectStorage(subId,warehouseId,this);
        })
        function SelectStorage(subId,warehouseId, ele) {
            var url = '${pageContext.request.contextPath}/MaterialsOutPut.do?SelectStorage';
            //不要删
            if(warehouseId != undefined && warehouseId != ''){
                url += '&warehouseId='+warehouseId;
            }
            if(subId != undefined && subId != ''){
                url += '&subId='+subId;
            }
            parent.layer.open({
                type: 2,
                title: '选择入库单号',
                maxmin: true,
                zIndex: 1000,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: url,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    str = data.split(",");
                    //判断是否重复
                    var flag = false;
                    $.each($(ele).parent().parent().parent().parent().find("input[name=storageNumber]"),function(index1,ele1){
                        if ($(this).val() == str[0] && $(ele).parent().parent().parent().find("input[name=subId]").val() == $(this).parent().parent().parent().find("input[name=subId]").val()){
                            flag = true;
                            return false;
                        }
                    });
                    if (flag){
                        parent.layer.close(index);
                        layer.alert("已存在该物资的入库单号,请修改数量或选择其他入库单");
                        return false;
                    }
                    $(ele).parent().find("input[name=storageNumber]").val(str[0]);
                    $(ele).parent().find("input[name=storageDetail]").val(str[2]);
                    $(ele).parent().parent().parent().find("input[name=amount]").val(str[1]);
                    parent.layer.close(index);
                }
            });
        }

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
        //查看
        $('#Look').on('click', function () {
            //获取被选中的物资出库详细的id
            parent.layer.open({
                type: 2,
                title: '物资信息',
                maxmin: true,
                zIndex: 1400,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsLook',
                btn: ['关闭']
            });
        });
        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();
    </script>
</body>
</html>

