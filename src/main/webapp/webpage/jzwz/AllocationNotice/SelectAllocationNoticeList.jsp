﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>自治区自然灾害救助信息管理系统</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <form id="form1">
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="example-wrap">
                                <div class="example">
                                    <form id="form2">
                                        <table id="exampleTableEvents" data-height="" data-mobile-responsive="true">
                                            <thead>
                                                <tr>
                                                    <th data-field="state" data-checkbox="true" id="CheckBoxAll"></th>
                                                    <th data-field="id" data-sort-stable="true">序号</th>
                                                    <th data-field="name0">调拨单据号</th>
                                                    <%--<th data-field="name1">物资编号</th>--%>
                                                    <%--<th data-field="name2">物资类别</th>--%>
                                                    <%--<th data-field="name3">规格型号</th>--%>
                                                    <%--<th data-field="name4">计量单位</th>--%>
                                                    <%--<th data-field="name5">调拨数量</th>--%>
                                                    <th data-field="name6">救灾项目</th>
                                                    <th data-field="name7">调出部门</th>
                                                    <th data-field="name8">调入部门</th>
                                                    <th data-field="name9">调拨日期</th>
                                                    <th data-field="name10" class="this_td">调拨标识</th>
                                                </tr>
                                            </thead>
                                            <%--调拨通知详细和调拨通知和物资信息三表联合查询在这个页面中显示--%>
                                            <tbody>
                                            <c:forEach items="${allocation}" var="allocation" varStatus="status">
                                                <tr>
                                                    <td><input type="checkbox" name="checks" ></td>
                                                    <td>${status.count}</td>
                                                    <td>${allocation.ALLOT_NUMBER}</td>
                                                    <td>${allocation.RELOEF_PROJECT}</td>
                                                    <td>${allocation.CALLOUT_DEPARTMENT}</td>
                                                    <td>${allocation.CALLIN_DEPARTMENT}</td>
                                                    <td><fmt:formatDate value="${allocation.ALLOT_DATE}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                                                    <td class="this_td">${allocation.UUID}</td>
                                                    <%--<td>2000</td>--%>
                                                    <%--<td>FW141103155006</td>--%>
                                                    <%--<td>新疆民政局</td>--%>
                                                    <%--<td>地方民政局</td>--%>
                                                    <%--<td>2016-12-08 09:30:00</td>--%>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                            <!-- End Example Events -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row-->


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
            function CheckSelect() {

                var selectedCount = 0;
                var result = "";
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++;
                        if (selectedCount == 1) {
                            result = $(this).children("td").eq(2).html()+","+ //调拨单号
                                $(this).children("td").eq(3).html()+","/*救灾项目*/
                                +$(this).children("td").eq(4).html()+","//调出部门
                                +$(this).children("td").eq(5).html()+","/*调入部门*/
                                +$(this).children("td").eq(7).html();//主键uuid

                        }
                    }
                })
                if (selectedCount > 1) {
                    alert("只能选择一条记录！");
                    return false;
                } else if (selectedCount < 1) {
                    alert("请选择一条记录！");
                    return false;
                } else {
                    return result;
                }
            }
            $(function () {
                $(".this_td").hide();
            })

            $("input[name=btSelectAll]").on('click', function () {

                if ($("input[name='btSelectAll']").is(':checked')) {

                    $("#exampleTableEvents").children('tbody').children('tr').each(function () {
                        if (!$(this).hasClass("selected")) {
                            $(this).addClass("selected");
                        }
                    })
                } else {

                    $("#exampleTableEvents").children('tbody').children('tr').each(function () {
                        $(this).removeClass("selected");
                    })
                }
            });


            $('#Look').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++;
                    }
                });
                if (selectedCount > 1) {
                    alert("只能选择一条记录");
                    return false;
                }
                else if (selectedCount < 1) {
                    alert("请选择一条记录");
                    return false;

                }
                Look();

            });
            function Look() {
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'AllocationNoticeLook.html',
                    btn: ['关闭']
                });
            }



        </script>
    </form>
</body>
</html>
