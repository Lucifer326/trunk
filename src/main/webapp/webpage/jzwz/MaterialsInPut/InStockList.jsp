﻿<%@ page import="org.jeecgframework.core.util.StringUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <script>
        //=======================================选择仓库
        function SelectWarehouse() {
            layer.open({
                type: 2,
                title: '选择仓库',
                maxmin: true,
                zIndex: 1000,
                shadeClose: true, //点击遮罩关闭层
                area: ['900px', '500px'],
                content: 'materialsInputController.do?selectWarehouse',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        var str = data.split(',');
                        $("#txtWareHouse").val(str[0]);
	                    $("#storageWarehouse").val(str[1]);
                        layer.close(index);
                    }
                }
            });
        }

    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span>物资入库</div>
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
                    <form id="search_form" class="form-horizontal m-t" role="form"
                          action="materialsInputController.do?queryAll" method="post">
                        <div class="form-group">
                            <ul class="ulist">
                                <li>
                                    <span class="fl labeltext">入库单号：</span>
                                    <div class="fl">
                                        <input type="text" name="storageNumber" value="${vo.storageNumber}"
                                               class="form-control" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？ \\]/g,'');"/>
                                    </div>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="fl labeltext">入库仓库：</span>
                                    <div class="fl">
                                        <input type="text" id="txtWareHouse" value="${vo.repositoryName}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）_+\-——|{}【】‘；：”“'。，、？ \\]/g,'');"
                                               class="form-control" name="repositoryName">
                                        <input type="hidden" id="storageWarehouse" value="${vo.storageWarehouse}" class="form-control" name="storageWarehouse">
                                    </div>
                                    <button class="btn btn-primary btna " id="btna" type="button"
                                            onclick="SelectWarehouse()">&nbsp;选择
                                    </button>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="fl labeltext">入库日期：</span>
                                    <input id="hello" name="startDate" value="${vo.startDate}"
                                           class="laydate-icon form-control layer-date fl w-148">
                                    <span class="fl communicate">―</span>
                                    <input id="hello1" name="endDate" value="${vo.endDate}"
                                           class="laydate-icon form-control layer-date fl w-148">
                                    <div class="clear"></div>
                                </li>

                                <li>
                                    <button class="btn btn-primary btnh" id="btnh" type="submit"><i
                                            class="fa fa-search"></i>&nbsp;搜索
                                    </button>
                                    <input class="btn btn-warning btnh" id="reset" type="button"
                                           style="width: 70px; margin-left: 20px;" value="重置">
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
                                <button type="button" class="btn btn-outline  btn-primary" id="Add2">新建</button>
                                <button type="button" class="btn btn-outline  btn-success" id="Look">查看</button>
                                <button type="button" class="btn btn-outline  btn-warning" id="Edit">修改</button>
                                <button type="button" class="btn btn-outline  btn-danger" id="del">删除</button>
                            </div>
                            <table id="exampleTableEvents" data-mobile-responsive="true">
                                <thead>
                                <tr>
                                    <%--<th data-field="state" data-checkbox="true"></th>--%>
                                    <th></th>
                                    <th data-field="number">序号</th>
                                    <th data-field="num">入库单号</th>
                                    <th data-field="n">采购批次</th>
                                    <th data-field="type">入库仓库</th>
                                    <th data-field="size">入库人</th>
                                    <th data-field="name">入库日期</th>
                                </tr>
                                </thead>
                                <tbody id="table_content">
                                <c:forEach items="${maps}" var="row" varStatus="i">
                                    <tr>
                                        <td><input type="checkbox" values="${row.uuid}"></td>
                                        <td>${i.count}</td>
                                        <td>${row.storage_Number}</td>
                                        <td>${row.purchase_Batch}</td>
                                        <td>${row.repository_Name}</td>
                                        <td>${row.storage_Person}</td>
                                        <td><fmt:formatDate value="${row.storage_Date}" pattern="yyyy-MM-dd"/></td>
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
</div>

<%--表单验证--%>
<script src="${pageContext.request.contextPath}/js/jzwz/MaterialsInPut/MaterialsInPut.js"></script>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js"></script>
<!-- Bootstrap table -->
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- Peity -->
<script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- 个人js -->
<script src="${pageContext.request.contextPath}/js/my.js"></script>
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


    //=========================================================重置
    $("#reset").click(function () {
        $("#search_form input[name]").val("");
	    $("#search_form").submit();
    });
    //========================================================================新建
    $('#Add2').on('click', function () {
        layer.open({
            type: 2,
            title: '新建',
            maxmin: true,
            zIndex: 100,
            shadeClose: true, //点击遮罩关闭层
            area: ['800px', '500px'],
            content: 'materialsInputController.do?toAdd2',
            btn: ['确定', '取消'],
            yes: function (index, layero) {

                if (verification(layero.find("iframe")[0].contentWindow,"add_form")) {
                    //======================================提交表单
                    var form = layero.find("iframe")[0].contentWindow.document.getElementById("add_form");

                    //限制文件大小
                    var file = layero.find("iframe")[0].contentWindow.$("#uploadFile");
                    var fileMaxSize = 1024 * 1024 * 5;//限制5M
                    var filePath = file.val();
                    if(filePath){
                        var f = file[0].files;
                        var fileSize = f[0].size;
                        if(fileSize > fileMaxSize){
                            layer.msg("文件大小不能超过5M", {time: 2000, icon: 7, shift: 1}, function () {});
                            return false;
                        }
                    }

                    var fileForm = new FormData(form);
                    //发送ajax请求添加数据
	                materialsInPutAdd(fileForm,index);
                }

            }
        });
    });
    //======================================================================查看
    $('#Look').on('click', function () {
        //定义一个数组，用于存放所选中的id值
        var ids = [];
        //遍历所有选中的复选框，并将id值添加到数组中
        $('#table_content input[type=checkbox]:checked').each(function () {
            ids.push($(this).attr('values'));
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
        var id = ids[0];
        layer.open({
            type: 2,
            title: '查看',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['800px', '500px'],
            content: 'materialsInputController.do?inStockLook&ctrl=query&uuid=' + id,
            btn: ['关闭']
        });
    });

    //===============================================================================修改
    $('#Edit').on('click', function () {
        //定义一个数组，用于存放所选中的id值
        var ids = [];
        //遍历所有选中的复选框，并将id值添加到数组中
        $('#table_content input[type=checkbox]:checked').each(function () {
            ids.push($(this).attr('values'));
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
        var id = ids[0];
        layer.open({
            type: 2,
            title: '修改',
            maxmin: true,
            zIndex: 100,
            shadeClose: true, //点击遮罩关闭层
            area: ['800px', '500px'],
            content: 'materialsInputController.do?inStockLook&ctrl=edit&uuid=' + id,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                if (verification(layero.find("iframe")[0].contentWindow,"edit_form")) {
                    //======================================提交表单
                    var form = layero.find("iframe")[0].contentWindow.document.getElementById("edit_form");
                    //限制文件大小
                    var file = layero.find("iframe")[0].contentWindow.$("#uploadFile");
                    var fileMaxSize = 1024 * 1024 * 5;//限制5M
                    var filePath = file.val();
                    if(filePath){
                        var f = file[0].files;
                        var fileSize = f[0].size;
                        if(fileSize > fileMaxSize){
                            layer.msg("文件大小不能超过5M", {time: 2000, icon: 7, shift: 1}, function () {});
                            return false;
                        }
                    }

                    var fileForm = new FormData(form);
                    //发送ajax请求执行修改
	                materialsInPutEdit(fileForm, index);
                }
            }
        });
    });
    //================================================================删除
    $('#del').click(function () {
        //定义一个数组，用于存放所选中的id值
        var ids = [];
        //遍历所有选中的复选框，并将id值添加到数组中
        $('#table_content input[type=checkbox]:checked').each(function () {
            ids.push($(this).attr('values'));
        });
        //根据ids数组的长度判断所选择的条数
        if (ids.length > 1) {
            layer.alert("只能选择一条记录！");
            return false;
        } else if (ids.length == 0) {
            layer.alert("请选择一条记录！");
            return false;
        }

        // var id = $('#table_content input[type=checkbox]:checked').attr('values');

        //获取所选择的的id
        var id = ids[0];
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
            //发送ajax请求执行删除操作
	        materialsInPutdelete(id);
        });
    });

    $(":button[name='refresh']").click(function () {
	    $("#search_form").submit();
    });
</script>
</body>
</html>