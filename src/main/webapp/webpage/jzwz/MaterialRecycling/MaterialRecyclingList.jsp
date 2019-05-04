﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <form id="form1">
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp </span>物资回收</div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>搜索条件</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content whj-padding">
                            <form class="form-horizontal m-t" role="form">
                                <div class="form-group">
                                    <ul class="ulist">
                                        <li>
                                            <span class="fl labeltext">单据号：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control" name="recycleNumber" id="recycleNumber" value="${vo.recycleNumber}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');" onkeydown="if(event.keyCode==32||event.keyCode==13){return false;}">
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">入库日期：</span>
                                            <input id="hello" readonly class="laydate-icon form-control layer-date fl w-148" name="inDate" value="<fmt:formatDate value="${vo.inDate}" pattern="yyyy-MM-dd"></fmt:formatDate>" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                                            <span class="fl communicate">―</span>
                                            <input id="hello1" readonly class="laydate-icon form-control layer-date fl w-148" name="outDate" value="<fmt:formatDate value="${vo.outDate}" pattern="yyyy-MM-dd"></fmt:formatDate>" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">入库仓库：</span>
                                            <div class="fl">
                                                <input type="text" id="house" readonly class="form-control" name="house" value="${vo.storageWarehouse}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                                            </div>
                                            &nbsp;
                                            <button class="btn btn-primary btna " id="btnSelectDiv" type="button" onclick="SelectDiv()">&nbsp;选择</button>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
                                        </li>
                                        <li>
                                            <button class="btn btn-warning btnh" id="reset" type="button"></i>&nbsp;重置</button>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                        </div>
                        <!--ibox-content-->
                    </div>
                </div>
            </div>
            <!--row-->
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="example-wrap">
                                <div class="example">
                                    <div class="btn-fl">
                                        <button type="button" class="btn btn-outline btn-primary" id="Add">新增</button>
                                        <button type="button" class="btn btn-outline btn-success" id="Look">查看</button>
                                        <button type="button" class="btn btn-outline btn-warning" id="Edit">修改</button>
                                        <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
                                        <button type="button" class="btn btn-outline btn-appro" id="examine">审批</button>
                                    </div>
                                </div>
                                <table id="exampleTableEvents" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <%--<th data-field="state" data-checkbox="true" id="CheckBoxAll"></th>--%>
                                            <th></th>
                                            <th data-field="id" data-sort-stable="true">序号</th>
                                            <th data-field="societyID">单据号</th>
                                            <th data-field="status">入库仓库</th>
                                            <th data-field="societyName">入库人</th>
                                            <th data-field="person">入库日期</th>
                                            <th>审批状态</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_content">
                                    <c:forEach items="${list}" var="list" varStatus="status">
                                        <tr>
                                            <td>
                                                <input type="checkbox" values="${list.uuid}" approval_status="${list.approvalStatus}" /></td>
                                            <td>${status.count}</td>
                                            <td>${list.recycleNumber}</td>
                                            <td>${list.storageWarehouse}</td>
                                            <td>${list.storagePerson}</td>
                                            <td><fmt:formatDate value="${list.storageDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                                            <td><c:choose>
                                                <c:when test="${list.approvalStatus eq approved.typecode}">
                                                    ${approved.typename}
                                                </c:when>
                                                <c:when test="${list.approvalStatus eq rejection.typecode}">
                                                    ${rejection.typename}
                                                </c:when>
                                                <c:otherwise>
                                                    待审批
                                                </c:otherwise>
                                            </c:choose></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- End Example Events -->
                    </div>
                </div>
            </div>
        </div>
        <!--row-->

        <!-- 全局js -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-2.1.1.min.js"></script>
        <!-- 自定义js -->
        <script src="js/content.js"></script>
        <!-- Bootstrap table -->
        <script src="js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="js/demo/bootstrap-table-demo.js"></script>
        <script src="js/plugins/layer/laydate/laydate.js"></script>
        <script src="js/plugins/layer/layer.min.js"></script>
        <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- 个人js -->
        <script src="js/my.js"></script>
        <script src="js/jzwz/rep_substance_recycle/checkForm.js"></script>
        <script>
            //外部js调用
            laydate({
                elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
            laydate({
                elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
            $("#reset").click(function () {
                $("#recycleNumber").val("");
                $("#hello").val("");
                $("#hello1").val("");
                $("#house").val("");
                window.location.href = "${url}/recycleController.do?conditionList&recycleNumber="+$("#recycleNumber").val()+"&storageWarehouse="+$("#house").val()+"&inDate="+$("#hello").val()+"&outDate="+$("#hello1").val();
            });
            $('#btnh').on('click', function () {
                window.location.href = "${url}/recycleController.do?conditionList&recycleNumber="+$("#recycleNumber").val()+"&storageWarehouse="+$("#house").val()+"&inDate="+$("#hello").val()+"&outDate="+$("#hello1").val();
            });
            $('#btna').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'SelectMaterialCategory.jsp',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {

                        var child = $(layero).find('iframe')[0].contentWindow;
                        var result = child.checked();
                        if (result != false) {
                            $("#type").val(result);
                            layer.close(index);
                        }
                    }
                });
            });

            $('#btnSelectDiv').on('click', function () {
                layer.open({
                    type: 2,
                    title: '入库仓库',
                    maxmin: true,
                    zIndex: 100,
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
                            $("#house").val(arr[0]);
                            layer.close(index);
                        }
                    }
                });
            });

            $('#Add').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    zIndex: 100,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['950px', '500px'],
                    content: '${url}/recycleController.do?showAdd',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var form = layero.find("iframe")[0].contentWindow.document.getElementById("recycleAddForm");
                        var check = checkForm(layero.find('iframe')[0].contentWindow, form);
                        if (!check){
                            return;
                        }
                        $.ajax({
                            type : "POST",
                            url : "${url}/recycleController.do?addRecycle",
                            data : $(layero).find('iframe')[0].contentWindow.$('#recycleAddForm').serialize(),
                            async:false,
                            success : function (data) {
                                var da = JSON.parse(data);
                                if (da.success){
                                    swal({
                                            title: "新建成功",
                                            type: "success",
                                            confirmButtonText: "确定",
                                            zIndex: 101
                                        },
                                        function () {
                                            layer.close(index);
                                            window.location.href = "${url}/recycleController.do?recycleList";
                                        });
                                }else {
                                    layer.msg(da.msg, {time:2000, icon:7, shift:1}, function () {});
                                    return false;
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                swal({
                                    title: "请保证数据的完整性",
                                    zIndex: 19891014,
                                    type: "warning",
                                    confirmButtonText: "确定"
                                });
                            }
                        });
                    }
                });
            });


            $('#Look').on('click', function () {

                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function() {
                    ids.push($(this).attr('values'));
                });
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length < 1) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                var uuid = ids[0];
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '500px'],
                    content: 'recycleController.do?lookRecycle&uuid='+ uuid,
                    btn: ['关闭']
                });
            });
           /* $('#Edit').on('click', function () {
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function() {
                    ids.push($(this).attr('values'));
                });
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length < 1) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                var uuid = ids[0];

                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['950px', '500px'],
                    content: 'recycleController.do?showMaterialRecyclingEdit&uuid=' + uuid,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var check = checkForm($(layero).find('iframe')[0].contentWindow);
                        if (!check){
                            return;
                        }
                        var check = checkUpdateForm($(layero).find('iframe')[0].contentWindow);
                        if (!check){
                            return;
                        }
                        $.ajax({
                            type : "POST",
                            url : "/recycleController.do?updateRecycle",
                            data : $(layero).find('iframe')[0].contentWindow.$('#recycleUpdateForm').serialize(),
                            async:false,
                            cache: false, // 不缓存
                            dataType: "json",
                            success : function (data) {
                                var da = JSON.parse(data);
                                alert(data.success);
                                if (data.success) {
                                    swal({
                                        title: "修改成功",
                                        zIndex: 19891014,
                                        type: "success",
                                        confirmButtonText: "确定"
                                    }, function () {
                                        layer.close(index);
                                        window.location.href = "/recycleController.do?recycleList";
                                    });
                                } else {
                                    swal({
                                        title: "添加失败",
                                        text: data.msg,
                                        type: "warning",
                                        zIndex: 101,
                                        confirmButtonText: "确定"
                                    });
                                    return false;
                                }
                            }
                        });
                        swal({
                            title: "修改成功",
                            zIndex: 101,
                            confirmButtonText: "确定",
                            type: "success",
                        }, function () {
                            layer.close(index);
                        });
                    }
                });
            });*/
            $('#Edit').on('click', function () {
                //定义一个数组，用于存放所选中的id值
                var ids = [];
                var approval_status = [];
                //遍历所有选中的复选框，并将id值添加到数组中
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                    approval_status.push($(this).attr('approval_status'));
                });
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }

                //获取所选择的的id
                var uuid = ids[0];
                var approval_statu = approval_status[0];
                if(approval_statu != ''){
                    layer.msg("回收单已被审批,无法修改", {time:2000, icon:7, shift:1}, function () {});
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['950px', '500px'],
                    content: 'recycleController.do?showMaterialRecyclingEdit&uuid=' + uuid,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var form = layero.find("iframe")[0].contentWindow.document.getElementById("recycleUpdateForm");
                        var check = checkForm(layero.find('iframe')[0].contentWindow, form);
                        if (!check){
                            return;
                        }
                            //======================================提交表单
                            // var form = layero.find("iframe")[0].contentWindow.document.getElementById("edit_form");
                        $.ajax({
                            url: "${url}/recycleController.do?updateRecycle",
                            type: "POST",
                            data: $(layero).find('iframe')[0].contentWindow.$('#recycleUpdateForm').serialize(),
                            dataType: "json",
                            cache: false, // 不缓存
                            // processData: false, // jQuery不要去处理发送的数据
                            // contentType: false, // jQuery不要去设置Content-Type请求头
                            success: function (result) {
                                if (result.success) {
                                    swal({
                                            title: "修改成功",
                                            type: "success",
                                            zIndex: 101,
                                            confirmButtonText: "确定"
                                        },
                                        function () {
                                            layer.close(index);
                                            window.location.href = "${url}/recycleController.do?recycleList";
                                        });
                                } else {
                                    layer.msg(result.msg, {time:2000, icon:7, shift:1}, function () {});
                                    return false;
                                }
                            }
                        });
                    }
                });
            });
            $('#examine').on('click', function () {

                //定义一个数组，用于存放所选中的id值
                var ids = [];
                var approval_status = [];
                //遍历所有选中的复选框，并将id值添加到数组中
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                    approval_status.push($(this).attr('approval_status'));
                });
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }

                //获取所选择的的id
                var uuid = ids[0];
                var approval_statu = approval_status[0];
                if(approval_statu != ''){
                    layer.msg("回收单已被审批,无法审批", {time:2000, icon:7, shift:1}, function () {});
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '审批',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '320px'],
                    content: 'recycleController.do?allocateValid',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        $.ajax({
                            url: "${url}/recycleController.do?approval&uuid=" + uuid,
                            type: "POST",
                            data: $(layero).find('iframe')[0].contentWindow.$('#approvalForm').serialize(),
                            dataType: "json",
                            cache: false, // 不缓存
                            // processData: false, // jQuery不要去处理发送的数据
                            // contentType: false, // jQuery不要去设置Content-Type请求头
                            success: function (result) {
                                if (result.success) {
                                    swal({
                                        title: "审批成功",
                                        zIndex: 101,
                                        confirmButtonText: "确定",
                                        type: "success",
                                    }, function () {
                                        layer.close(index);
                                        window.location.href = "${url}/recycleController.do?recycleList";
                                    });
                                } else {
                                    swal({
                                        title: "审批失败",
                                        text: result.msg,
                                        type: "error",
                                        zIndex: 101,
                                        confirmButtonText: "确定"
                                    });
                                }
                            }
                        });
                    }
                });
            });

            $('#del').click(function () {
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function() {
                    ids.push($(this).attr('values'));
                });
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length < 1) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                var uuid = ids[0];
                swal({
                    title: "您确定要删除这条信息吗",
                    text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "删除",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function () {
                    $.ajax({
                        type : "POST",
                        url : "recycleController.do?deleteRecycles",
                        data : { uuid : uuid },
                        traditional : true,
                        success : function (data) {
                            var da = JSON.parse(data);
                            if (da.success){
                                //    swal("删除成功！", "您已经永久删除了这条信息。", "success");
                                swal({
                                    title: "删除成功！",
                                    text: da.msg,
                                    type: "success",
                                    confirmButtonText: "确定"
                                }, function (index) {
                                    layer.close(index);
                                    window.location.href = "${url}/recycleController.do?recycleList";
                                });
                            }else {

                            }
                        }
                    });

                });
            });
            /*//删除、批量删除
            $('#del').click(function () {
                //定义一个接收id的数组
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                });
                if(ids.length == 0){
                    layer.alert("请选择一条数据")
                    return false;
                }else {
                    var uuid = ids[0];
                    swal({
                        title: "您确定要删除这条信息吗",
                        text: "删除后将无法恢复，请谨慎操作！",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonText: "取消",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "删除",
                        closeOnConfirm: false
                    }, function () {
                        $.ajax({
                            type:"post",
                            url:"recycleController.do?deleteRecycles",
                            traditional:true,
                            cache:false,
                            data:{uuid : uuid},
                            dataType:"JSON",
                            success: function (result) {
                                if(result.success){
                                    swal({
                                        title: "删除成功",
                                        type: "success",
                                        confirmButtonText: "确定"
                                    }, function (index) {
                                        //关闭弹出框的父窗口
                                        layer.close(index);
                                        //重新加载列表页
                                        window.location.href = "/recycleController.do?recycleList";
                                    });
                                }else {
                                }
                            }
                        });
                    });
                }
            });*/


            $("input[name=btSelectAll]").on('click', function () {

                if ($("input[name='btSelectAll']").is(':checked')) {

                    $("#exampleTableEvents").children('tbody').children('tr').each(function () {
                        $(this).addClass("selected");
                    })
                } else {

                    $("#exampleTableEvents").children('tbody').children('tr').each(function () {
                        $(this).removeClass("selected");
                    })
                }
            });
            /*$('#btn1').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'MaterialInfoManagementSelect.jsp',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {

                        var child = $(layero).find('iframe')[0].contentWindow;
                        var result = child.checked();
                        if (result != false) {
                            $("#name").val(result);
                            layer.close(index);
                        }
                    }
                });
            });*/
        </script>
    </form>
</body>
</html>
