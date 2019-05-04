﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
</head>
<body class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <!--<div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span>  仓库机构信息管理</div>-->
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
                        <form class="form-horizontal m-t" role="form">
                            <div class="form-group">
                                <ul class="ulist">
                                    <li>
                                        <span class="fl labeltext">库房名称：</span>
                                        <div class="fl">
                                            <input type="text" value="${vo.repositoryName}" class="form-control">
                                        </div>
                                        <div class="clear"></div>
                                    </li>

                                    <li>
                                        <span class="fl labeltext">建成时间：</span>
                                        <input id="hello1"  class="laydate-icon form-control layer-date fl w-148" value='<fmt:formatDate value="${vo.activateTimeStart}" pattern="yyyy-MM-dd"/>' />
                                        <span class="fl communicate">―</span>
                                        <input id="hello2"  class="laydate-icon form-control layer-date fl w-148" value='<fmt:formatDate value="${vo.activateTimeEnd}" pattern="yyyy-MM-dd"/>' />
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">库房类型：</span>
                                        <div class="fl">
                                            <select class="form-control w-196" name="repositoryCategory">
                                                <option value="0">-请选择-</option>
                                                <option value="自有库" ${vo.repositoryCategory eq '自有库' ? "selected='selected'" : ""}>
                                                    自有库
                                                </option>
                                                <option value="租赁库" ${vo.repositoryCategory eq '租赁库' ? "selected='selected'" : ""}>
                                                    租赁库
                                                </option>
                                            </select>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
                                    </li>
                                </ul>
                            </div>
                        </form>
                    </div>

                    <div class="ibox-content whj-padding">
                        <!--ibox-content-->
                    </div>
                </div>
            </div>
            <!--row-->
            <div class="row">
                <div class="col-sm-12">

                    <div class="ibox-content">
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTableEvents" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="check" data-checkbox="true"></th>
                                            <th data-field="id" data-sort-stable="true">序号</th>
                                            <th data-field="name" data-sort-stable="true">库房名称 </th>
                                            <th data-field="cade" data-sort-stable="true">库房编号 </th>
                                            <th data-field="div" data-sort-stable="true">库房区划</th>
                                            <th data-field="type" data-sort-stable="true">库房类型</th>
                                            <th data-field="time" data-sort-stable="true">建成时间</th>
                                            <th data-field="scale" data-sort-stable="true" data-visible="false">建设规模(平方米)</th>
                                            <th data-field="storagevolume" data-sort-stable="true" data-visible="false">储存量(立方米)</th>
                                            <th data-field="area" data-sort-stable="true" data-visible="false">库房面积(平方米)</th>
                                            <th data-field="person" data-sort-stable="true">负责人</th>
                                            <th data-field="phone" data-sort-stable="true">联系电话</th>
                                            <th data-field="money" data-sort-stable="true" data-visible="false">投资情况(万元)</th>
                                            <th data-field="address" data-sort-stable="true">库房地址</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_content">
                                        <%--<tr>--%>
                                            <%--<td>--%>
                                                <%--<input name="checkss" type="checkbox"></td>--%>
                                            <%--<td>1</td>--%>
                                            <%--<td>抗灾救助帐篷库</td>--%>
                                            <%--<td>003</td>--%>
                                            <%--<td>新疆维吾尔自治区克拉玛依市乌尔禾区</td>--%>
                                            <%--<td>自有库</td>--%>
                                            <%--<td>2016-12-01</td>--%>
                                            <%--<td>10000</td>--%>
                                            <%--<td>50000</td>--%>
                                            <%--<td>8000</td>--%>
                                            <%--<td>李丽萍</td>--%>
                                            <%--<td>18843992486</td>--%>
                                            <%--<td>800</td>--%>
                                            <%--<td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>--%>
                                        <%--</tr>--%>
                                        <c:forEach items="${repositories}" var="row" varStatus="i">
                                            <tr>
                                                <td><input  type="checkbox" values="${row.uuid}" ></td>
                                                <td>${i.count}</td>
                                                <td>${row.repositoryName}</td>
                                                <td>${row.repositoryNumber}</td>
                                                <td>${row.repositoryArea}</td>
                                                <td>${row.repositoryCategory}</td>
                                                <td>${row.activateTime}</td>
                                                <td>${row.activateScale}</td>
                                                <td>${row.storageVolume}</td>
                                                <td>${row.repositoryAcreage}</td>
                                                <td>${row.personCharge}</td>
                                                <td>${row.phone}</td>
                                                <td>${row.investCase}</td>
                                                <td>${row.repositoryAddress}</td>
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
            <!--row-->
        </div>

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
        <script>
            //外部js调用
            laydate({
                elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
            laydate({
                elem: '#hello2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
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


            function CheckSelect() {
                var ids = [];
                //遍历所有选中的复选框，并将id值添加到数组中
                $('#table_content input[type=checkbox]:checked').each(function() {
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
                var str = "";
                $("#table_content").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                            str = $(this).children("td").eq(2).text()+","+id;
                    }
                });
                return str;
            }


            var a;
            function CheckAll() {
                if (a == 1) {
                    for (var i = 0; i < window.document.forms[0].elements.length; i++) {
                        var e = form1.elements[i];
                        e.checked = false;
                    }
                    a = 0;
                }
                else {
                    for (var i = 0; i < window.document.forms[0].elements.length; i++) {
                        var e = form1.elements[i];
                        e.checked = true;
                    }
                    a = 1;
                }
            }
        </script>
</body>
</html>

