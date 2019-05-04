﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>物资生产商列表页改版</title>
    <link href="${url}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${url}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${url}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${url}/css/animate.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
    <link href="${url}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <script>
        function SelectDiv() {
            layer.open({
                type: 2,
                title: '选择区划',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['400px', '300px'],
                content: '/CommonPage/SelectDiv',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    $("#sdiv").val("乌鲁木齐市天山区");
                    parent.layer.close(index);
                }
            });
        }
    </script>
</head>
<body class="gray-bg">
    <form>
        <div class="wrapper wrapper-content animated fadeInRight">
          <!--  <div class="alert alert-success whj_location"><span class="c6">当前位置：救灾物资信息管理系统 &nbsp > &nbsp</span> 物资生产商 &nbsp</div>-->
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
                            <div class="form-group">
                                <ul class="ulist">
                                    <li>
                                        <span class="fl labeltext">公司名称：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" name="name" id="name" value="${name}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]/g,'');">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">主营产品：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" name="product" id="product" value="${product}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]/g,'');">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
                                    </li>
                                </ul>


                            </div>

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
                                    </div>
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr>
                                                <!--<th data-field="state" data-switchable="false">
                                                    <input name="check_list" type="checkbox" onclick="CheckAll()"/></th>-->
                                                <th data-field="state" data-checkbox="true"></th>
                                                <th data-field="id" data-sort-stable="true">序号</th>
                                                <th data-field="name">公司名称</th>
                                                <th data-field="name1">公司简介</th>
                                                <th data-field="name2">主营产品</th>
                                                <th data-field="name3">联系人</th>
                                                <th data-field="name4">联系电话</th>
                                                <th data-field="name5">地址</th>
                                                <th data-field="name6">详细购买记录</th>
                                                <th class="this_td">公司标识</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${productList}" var="product" varStatus="status">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="check_list"  value="${product.uuid}"></td>
                                                <td>${status.count}</td>
                                                <td>${product.name}</td>
                                                <td>${product.companyInfo}</td>
                                                <td>${product.product}</td>
                                                <td>${product.contact}</td>
                                                <td>${product.phone}</td>
                                                <td>${product.url}</td>
                                                <td><a href="javasricpt:void(0)" style="color: blue" onclick="buyNode()">详细购买记录</a></td>
                                                <td class="this_td">${product.uuid}</td>
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

        <!-- 全局js -->
        <script src="${url}/js/jquery.min.js"></script>
        <script src="${url}/js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="${url}/js/content.js"></script>
        <!-- Bootstrap table -->
        <script src="${url}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="${url}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="${url}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="${url}/js/demo/bootstrap-table-demo.js"></script>
        <script src="${url}/js/plugins/layer/laydate/laydate.js"></script>
        <script src="${url}/js/plugins/layer/layer.min.js"></script>
        <script src="${url}/js/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- 个人js -->
        <script src="${url}/js/my.js"></script>
        <script>
            //外部js调用
            //laydate({
            //    elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
            //laydate({
            //    elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            //    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            //});
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
            $(function () {
                $(".this_td").hide();
            });

            $("#btnh").on('click', function () {
                window.location.href = "${url}/protocalController.do?productQuery&name="+$("#name").val()+"&product="+$("#product").val();
            })

            function CheckSelect() {

                var selectedCount = 0;
                var str = "";
                var str1="";
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++;
                        if ( selectedCount == 1) {
                            str = $(this).children("td").eq(2).html()+","+
                                $(this).children("td").eq(9).html();
                        }
                    }
                });
                if (selectedCount > 1) {
                    alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount < 1) {
                    alert("请选择一条记录！");
                    return false;
                }
                else {

                    return str;
                }


            }

            $('#Add').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    zIndex: 100,
                    content: 'MaterialProducerAdd',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "新增成功",
                            zIndex: 101,
                            type: "success",
                            confirmButtonText: "确定",
                        }, function () {
                            layer.close(index);
                        });
                    }
                });
            });
            $('#Look').on('click', function () {
                //var count = $("input[name='check_list']:checked").length;
                var count = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        count++
                    }
                })
                if (count == 0) {
                    alert("请至少选择一项");
                    return false;
                }
                else if (count > 1) {
                    alert("请只选择一项");
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'MaterialProducerLook',
                    btn: ['关闭']
                });
            });
            $('#Edit').on('click', function () {
                //var count = $("input[name='check_list']:checked").length;
                var count = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        count++
                    }
                })
                if (count == 0) {
                    alert("请至少选择一项");
                    return false;
                }
                else if (count > 1) {
                    alert("请只选择一项");
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    zIndex: 100,
                    content: 'MaterialProducerEdit',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "修改成功",
                            zIndex: 101,
                            type: "success",
                            confirmButtonText: "确定",
                        }, function () {
                            layer.close(index);
                        });
                    }
                });
            });

            $('#del').click(function () {
                //var count = $("input[name='check_list']:checked").length;
                var count = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        count++
                    }
                })
                if (count == 0) {
                    alert("请至少选择一项");
                    return false;
                }
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
                    swal("删除成功！", "您已经永久删除了这条信息。", "success");
                    $(".sa-button-container .confirm").text("确定");
                });
            });
            function buyNode () {
                layer.open({
                    type: 2,
                    title: '购买记录',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['700px', '400px'],
                    content: 'materialPurchaseRecordController.do?list',
                    btn: ['关闭']
                });
            };
        </script>
    </form>
</body>
</html>
