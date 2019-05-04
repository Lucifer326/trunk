﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body class="gray-bg">
<form id="form1">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp 系统管理 &nbsp > &nbsp </span>
            仓储机构信息管理
        </div>
        <div class="row"> 
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>搜索条件</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <!--   <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#">选项1</a>
                                </li>
                                <li><a href="#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>-->
                        </div>
                    </div>
                    <div class="ibox-content whj-padding">
                        <form class="form-horizontal m-t" id="queryRepository" method="post">
                            <div class="form-group">
                                <ul class="ulist">
                                    <li>
                                        <span class="fl labeltext">库房名称：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" id="repositoryName"
                                                   name="repositoryName" value="${vo.repositoryName}">
                                        </div>
                                        <div class="clear"></div>
                                    </li>

                                    <li>
                                        <span class="fl labeltext">建成时间：</span>
                                        <%--开始时间--%>
                                        <input id="hello1" class="laydate-icon form-control layer-date fl w-148"
                                               name="activateTimeStart"
                                               value="<fmt:formatDate value="${vo.activateTimeStart}" pattern="yyyy-MM-dd"/>"/>
                                        <span class="fl communicate">―</span>
                                        <%--结束时间--%>
                                        <input id="hello2" class="laydate-icon form-control layer-date fl w-148"
                                               name="activateTimeEnd"
                                               value="<fmt:formatDate value="${vo.activateTimeEnd}" pattern="yyyy-MM-dd"/>"/>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">库房类型：</span>
                                        <div class="fl">
                                            <select class="form-control w-196" id="repositoryCategory"
                                                    name="repositoryCategory">
                                                <option value="">-请选择-</option>
                                                <c:forEach items="${items}" var="item">
                                                    <option value="${item.typecode}"
                                                            <c:if test="${vo.repositoryCategory eq item.typecode}">selected</c:if>>${item.typename}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i
                                                class="fa fa-search"></i>&nbsp;搜索
                                        </button>
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

                <div class="ibox-content">
                    <div class="example-wrap">
                        <div class="example">
                           <%-- <div class="btn-fl">
                                <button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
                                <button type="button" class="btn btn-outline btn-success" id="Look">查看</button>
                                <button type="button" class="btn btn-outline btn-warning" id="Edit"
                                        onclick="CheckEdit()">修改
                                </button>
                                <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
                                <!--<button type="button" class="btn btn-outline btn-danger" id="Export">导出</button>-->
                            </div>--%>
                            <table id="exampleTableEvents" data-mobile-responsive="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="id" data-sort-stable="true">序号</th>
                                    <th data-field="name" data-sort-stable="true">库房名称</th>
                                    <th data-field="type" data-sort-stable="true">库房类型</th>
                                    <th data-field="time" data-sort-stable="true">建成时间</th>
                                    <th data-field="scale" data-sort-stable="true">建设规模(平方米)</th>
                                    <th data-field="area" data-sort-stable="true">库房面积(平方米)</th>
                                    <th data-field="address" data-sort-stable="true">库房地址</th>
                                </tr>
                                </thead>
                                <tbody id="table_content">
                                <c:forEach items="${list}" var="list" varStatus="i">
                                    <tr>
                                        <td><input type="checkbox" values="${list.uuid}"></td>
                                            <%--<td style="display: none">${list.uuid}</td>--%>
                                        <td>${i.count}</td>
                                        <td>${list.repositoryName}</td>
                                        <td>
                                            <c:forEach items="${items}" var="item">
                                                <c:if test="${list.repositoryCategory eq item.typecode}">${item.typename}</c:if>
                                            </c:forEach>
                                        </td>
                                        <td><fmt:formatDate value="${list.activateTime}" type="date"/></td>
                                        <td>${list.activateScale}</td>
                                        <td>${list.repositoryAcreage}</td>
                                        <td>${list.repositoryAddress}</td>
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
    <%--wareHouseList.js--%>
    <script src="${pageContext.request.contextPath}/js/jzwz/system_management/wareHouseList.js"></script>
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

        //添加
        $('#Add').on('click', function (){
            layer.open({
                type: 2,
                title: '新建',
                zIndex: 1000,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: 'warehouse.do?WareHouseInformationManageAdd',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var form = $(layero).find('iframe')[0].contentWindow;
                    //新增
                    save(form, index);
                }
            });
        });

        //查看
        $('#Look').on('click', function () {
            if (getSeletedId() != false) {
                //获取所选择的的id
                var id = getSeletedId();
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'warehouse.do?find&warhouseId=' + id,
                    btn: ['关闭']
                });
            }
        });


        function selectHouse() {
            var selectedCount = 0;
            var result = "";
            $("#exampleTableEvents").find('tr').each(function () {
                if ($(this).hasClass('selected')) {
                    selectedCount++;
                    if (selectedCount == 1) {
                        result0 = $(this).children("td").eq(2).html();

                        result = result0;
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

        //修改
        $('#Edit').on('click', function () {
            if (getSeletedId() != false) {
                //获取所选择的的id
                var id = getSeletedId();
                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    zIndex: 1000,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'warehouse.do?showModify&warhouseId=' + id,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var form = $(layero).find('iframe')[0].contentWindow;
                        //修改
                        update(form, index);
                    }
                });
            }
        });

        //删除
        $('#del').click(function () {
            if (getSeletedId() != false) {
                //获取所选择的的id
                var id = getSeletedId();
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
                    //删除
                    del(id);
                });
            }
        });
        //条件查询
        $('#btnh').click(function () {
            query();
        });

    </script>
</form>
</body>
</html>
