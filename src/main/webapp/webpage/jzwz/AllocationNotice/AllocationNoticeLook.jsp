﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看页面</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal">
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
                        <div class="form-group draggable">

                            <label class="col-sm-2 control-label">调拨单据号：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${rep.allotNumber}</div>
                            </div>
                            <label class="col-sm-2 control-label">调拨日期：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly"><fmt:formatDate value="${rep.allotDate}" pattern="yyyy-MM-dd"></fmt:formatDate></div>
                            </div>
                        </div>

                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">调入部门：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${rep.callinDepartment}</div>
                            </div>
                            <label class="col-sm-2 control-label">调出部门：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${rep.calloutDepartment}</div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">调拨仓库：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${acc.repositoryName}</div>
                            </div>
                            <label class="col-sm-2 control-label">物资用途：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${rep.storageUse}</div>
                            </div>
                        </div>
                        <div class="form-group draggable">

                            <label class="col-sm-2 control-label">救灾项目：</label>
                            <div class="col-sm-4">
                                <div class="form-control" readonly="readonly">${rep.reloefProject}</div>
                            </div>
                        </div>



                    </div>
                </div>

                <div class="ibox float-e-margins">
                    <div class="ibox-title whj-see-ibox ">
                        <h5>物资信息</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="example-wrap">
                            <div class="example">
                                <form id="form2">
                                    <table id="exampleTableEvents" data-height="" data-mobile-responsive="true">
                                        <thead>
                                            <tr>
                                                <th data-field="name1">物资编号</th>
                                                <th data-field="name2">物资类别</th>
                                                <th data-field="name3">规格型号</th>
                                                <th data-field="name4">计量单位</th>
                                                <th data-field="name5">计量单价</th>
                                                <th data-field="name6">物资折价合计</th>
                                                <th data-field="name7">调拨数量</th>
                                            </tr>
                                        </thead>
                                        <tbody>


                                            <c:forEach items="${map}" var="de" >
                                            <tr>
                                                <td>${de.sub_number}</td>
                                                <td>${de.sub_name}</td>
                                                <td>${de.specification_type}</td>
                                                <td>${de.unit}</td>
                                                <td>${de.price}</td>
                                                <td>${de.price_total}</td>
                                                <td>${de.allot_amount}</td>


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
            </form>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js"></script>
    <!-- **********-->
    <!-- Bootstrap table -->
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- Peity -->
    <script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo1.js"></script>

    <!--    ***********-->

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
        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();

    </script>

</body>
</html>
