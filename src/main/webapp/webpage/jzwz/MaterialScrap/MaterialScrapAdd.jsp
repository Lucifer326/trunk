<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
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
    <link href="${pageContext.request.contextPath}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .chosen-container {
            width: 100% !important;
        }

        .layer-date {
            max-width: none !important;
        }

        .table {
            border: solid 1px #ddd;
        }

        .table th {
            border: solid 1px #ddd;
        }

        .table td {
            border: solid 1px #ddd !important;
        }
    </style>
    <script>

    </script>
</head>
<body class="add-dis">

<form id="myForm">
    <div class="wrapper wrapper-content animated">
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
                            <label class="col-sm-2 control-label"><span class="start">*</span>单据号：</label>
                            <div class="col-sm-4">
                                <input type="text" id="scrapNumber" name="scrapNumber" class="form-control" readonly/>
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库单：</label>
                            <div class="col-sm-4">
                                <div id="Div1">
                                    <input type="text" id="StoreNum" name="StoreNum" class="form-control fl"  style="width: 70%" readonly/>
                                    <button class="btn btn-primary btnh" id="btnStore" style="width: 30%" type="button"
                                            onclick="SelectStore(this)"><i class="fa fa-search"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                           <%-- <label class="col-sm-2 control-label"><span class="start">*</span>关联单据：</label>
                            <div class="col-sm-4">
                                <select class="chosen-select form-control" tabindex="2" style="width: 100%"
                                        id="associatedDocuments" name="associatedDocuments">
                                    <option>盘点单据</option>
                                    <option>物资回收</option>
                                </select>
                            </div>--%>
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库仓库：</label>
                            <div class="col-sm-4">
                                <div id="Div3">
                                    <input type="text" id="wareHouse" name="wareHouse" class="form-control fl"
                                           style="width: 70%" readonly="readonly" value=""/>
                                   <%-- <button class="btn btn-primary btnh" id="btn" style="width: 30%" type="button"
                                            onclick="SelectStorehouse(this)"><i class="fa fa-search"></i>&nbsp;选择
                                    </button>--%>
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">备注：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="3" name="remark"></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="isDeleted" value="0">
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
                    <button type="button" class="btn btn-outline btn-primary" id="Add" onclick="addRow()">新建</button>
                    <button type="button" class="btn btn-outline btn-danger" id="del" onclick="fnDeleteRow()">删除 </button>
                </div>
                <div style="width: 100%; float: left; margin-top: 20px;">
                    <table width="100%" cellpadding="0" cellspacing="0" id="mytab" class="crease table table-border">
                        <thead>
                        <tr>
                            <th align="center">序列</th>
                            <th align="center"width="30%">物资品名</th>
                            <th align="center">计量单位</th>
                            <th align="center">库存量</th>
                            <th align="center">报废数量</th>
                            <th align="center">报废原因</th>
                        </tr>
                        </thead>
                        <tbody id="tab">
                        <%--<c:forEach items="${scrapLookTwoInformation}" var="information" varStatus="status">--%>
                        <%--<tr>--%>

                        <%--<td>--%>
                        <%--<input type="hidden" value="${information.uuid}" name="id">--%>
                        <%--<input type="checkbox" value='${status.count}' name="chkArr"/>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" readonly id="name" name="subName" class="form-control fl" style="width: 55%; margin-right: 10px" value="${information.subName}" />--%>
                        <%--&lt;%&ndash; <button class="btn btn-primary btnh fl" id="btn" onclick="a(this)" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>&ndash;%&gt;--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" readonly id="size" name="unit" class="form-control"  value="${information.unit}"/>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" readonly id="type" name="scrapTotal" class="form-control"  value="${information.scrapTotal}"/>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" readonly name="scrapCause" id="unit" class="form-control" value="${information.scrapCause}" />--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>
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
    <!-- Peity -->
    <script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
    <!--固定格式-->
    <script src="${pageContext.request.contextPath}/js/plugins/jasny/jasny-bootstrap.min.js"></script>

    <script>
        //外部js调用
       /* laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });*/
        $(document).ready(function () {

            $('.summernote').summernote({
                lang: 'zh-CN'
            });

        });

        /**
         * 选择入库单
         * @param data
         * @constructor
         */
        function SelectStore(data) {
            var id = data.id;
            parent.layer.open({
                type: 2,
                title: '选择入库单',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: '${pageContext.request.contextPath}/ScrapController.do?showStoreScarp',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow;
                    var result = data.selectStoreNum();
                    var together = new Array();
                    together = result.split(",");
                    if (result != false) {
                        var text = together[0];
                        $("#" + id).prev().val(text);
                        $.ajax({
                            type: "post",
                            dataType: "JSON",
                            url: "${pageContext.request.contextPath}/ScrapController.do?listRepository&storenum="+$("#StoreNum").val(),
                            success: function (data) {
                                $("#wareHouse").val(data.repository_name);
                            }
                        });
                        parent.layer.close(index);
                    }
                }
            });
        }
/*        function SelectStorehouse(data){
            var id = data.id;
            var storenum = $("#StoreNum").val();
            alert(storenum);
            parent.layer.open({
                type: 2,
                title: '选择仓库',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: '${pageContext.request.contextPath}/ScrapController.do?listRepository&storenum'+storenum,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow;
                    var result = data.selectHouse();
                    var together = new Array();
                    together = result.split(",");
                    if (result != false) {
                        var text = together[0];
                        $("#" + id).prev().val(text);
                        parent.layer.close(index);
                    }
                }
            });
        }*/

        /**
         * 信息的回显
         * @param data
         * @constructor
         */

        function SelectInformation(data) {
            var obj=data;
            //var obj=$(this);
            var id = data.id;
            parent.layer.open({
                type: 2,
                title: '选择物资品名',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: '${pageContext.request.contextPath}/ScrapController.do?MaterialInfoManagement&storenum='+$("#StoreNum").val(),
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow;
                    var result = data.checkeds();
                    var together = new Array();
                    together = result.split(",");
                    if (result != false) {
                        var text = together[0];//品名
                        var unit = together[1];//计量单位
                        var count = together[2];
                       // var count = together[3];
                        $("#" + id).prev().val(text)
                        $("#" + id).parent().next().children("input").val(unit);
                        $("#" + id).parent().next().next().children("input").val(count);
                        parent.layer.close(index);
                    }
                    $(obj).parent().parent().find("input[name=messageId]").val(together[3]);
                }
            });
        }


        //增加
        var i = 0;
        function addRow() {
            i++;
            var tabObj = document.getElementById("mytab");//获取添加数据的表格
            var bodyObj = document.getElementById("tab");//获取添加数据的表格
            var rowsNum = tabObj.rows.length;
            var rowsNum2 = bodyObj.rows.length;
            var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
            var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
            var newTdObj1 = myNewRow.insertCell(0);
            newTdObj1.innerHTML = "<input type='checkbox' name='check_list' id='check_list'" + rowsNum + " style='border:0px' />" +
                "<input type='hidden' name='messageId' id='messageId'/> ";

            var newTdObj2 = myNewRow.insertCell(1);
            newTdObj2.innerHTML = "<input type='text' id='information' name='subName' readonly='readonly' class='form-control fl text' style='width: 65%'/>"
                + "<button class='btn btn-primary btnh bt Button2' id='Button1" + i + "' style='width: 35%' type='button' onclick='SelectInformation(this)'><i class='fa fa-search'></i>&nbsp;选择</button>";
            var newTdObj3 = myNewRow.insertCell(2);
            newTdObj3.innerHTML = "<input type='text' id='' name='unit' readonly='readonly' class='form-control materials_unit'/>";
            var newTdObj4 = myNewRow.insertCell(3);
            newTdObj4.innerHTML = "<input type='text' name='count' class='form-control' readonly />";
            //库存量
            var newTdObj4 = myNewRow.insertCell(4);
            newTdObj4.innerHTML = "<input type='text' name='scrapTotal' value='1' class='form-control' onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"/>";
            var newTdObj5 = myNewRow.insertCell(5);
            newTdObj5.innerHTML = "<input type='text' name='scrapCause' value='暂无' class='form-control'/>";
        };

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
        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();


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
        $("#scrapNumber").val(getDateString("yyyyMMdd-HHmmss","BF-"));


    </script>
</form>
</body>
</html>

