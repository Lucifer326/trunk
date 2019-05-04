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
    <form>
        <div class="wrapper wrapper-content animated fadeInRight">
          <!--  <div class="alert alert-success whj_location"><span class="c6">当前位置：自然灾害救助信息管理系统 &nbsp > &nbsp 物资管理 &nbsp > &nbsp</span>物资信息管理 </div>-->
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
                            <form class="form-horizontal m-t" role="form" id="search_form">
                                <div class="form-group">
                                    <ul class="ulist">
                                        <li>
                                            <span class="fl labeltext">物资编号：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control" id="subNumber" value="${subNumber}" onkeydown="if(event.keyCode==32||event.keyCode==13){return false;}"/>
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">物资品名：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control" id="subName" value="${subName}" onkeydown="if(event.keyCode==32||event.keyCode==13){return false;}"/>
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">物资类别：</span>
                                            <div class="fl">
                                                <input type="text" id="txt1" name="" readonly class="form-control fl" style="width: 70%" value="${subCategory}">
                                                <button class="btn btn-primary btnh" id="btn9" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
                                            </div>
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
                                       <!-- <button type="button" class="btn btn-outline  btn-primary" id="Add">新建</button>
                                        <button type="button" class="btn btn-outline  btn-success" id="Look">查看</button>
                                        <button type="button" class="btn btn-outline  btn-warning" id="Edit">修改</button>
                                        <button type="button" class="btn btn-outline  btn-danger" id="del">删除</button>-->
                                    </div>
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr>
                                                <th data-field="state" data-checkbox="true"></th>
                                                <th data-field="number">序号</th>
                                                <th data-field="num">物资编号</th>
                                                <th data-field="name">物资品名</th>
                                                <th data-field="type">物资类别</th>
                                                <th data-field="size">规格型号</th>
                                                <th data-field="shuliang">是否可回收</th>
                                                <th data-field="danwei">计量单位</th>
                                                <th class="this_td">物资标识</th>
                                                <th class="this_td">入库详细标识</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${substanceMessages}" var="messages" varStatus="status">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" value="${messages.uuid}" name="checkss">
                                                </td>
                                                <td>${status.count}</td>
                                                <td>${messages.SUB_NUMBER}</td>
                                                <td>${messages.SUB_NAME}</td>
                                                <td>${messages.CATEGORY_NAME}</td>
                                                <td>${messages.SPECIFICATION_TYPE}</td>
                                                <td>${messages.ISRECYCLE==1?'是':'否'}</td>
                                                <td>${messages.UNIT}</td>
                                                <td class="this_td">${messages.messageid}</td>
                                                <td class="this_td">${messages.storagedetail}</td>
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
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="js/content.js"></script>
        <!-- Bootstra table -->
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
        <script>
            //外部js调用    
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
            $("#reset").click(function () {
                $("#subNumber").val("");
                $("#subName").val("");
                $("#txt1").val("");
                window.location.href="${pageContext.request.contextPath}/recycleController.do?searchRepSubstanceMessage&subNumber="+$("#subNumber").val()+"&subName="+$("#subName").val()+"&subCategory="+$("#txt1").val()+"&outHouseId=${outHouseId}";
            });
            //向后台传递数据
            $('#btnh').on('click', function () {
                window.location.href="${pageContext.request.contextPath}/recycleController.do?searchRepSubstanceMessage&subNumber="+$("#subNumber").val()+"&subName="+$("#subName").val()+"&subCategory="+$("#txt1").val()+"&outHouseId=${outHouseId}";
            });

            $('#btn9').on('click', function () {
                parent.layer.open({
                    type: 2,
                    title: '选择物资类别',
                    maxmin: true,
                    zIndex: 1000,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'recycleController.do?materialCategoryList',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var child = $(layero).find("iframe")[0].contentWindow.checked()

                        if (child != false) {
                            $("#txt1").val(child);
                            parent.layer.close(index);
                        }
                    }
                });
            });

            function checked() {
                var selectedCount = 0;
                var result = "";
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++;
                        if (selectedCount == 1) {
                            result0 = $(this).children("td").eq(3).html();
                            result1 = $(this).children("td").eq(4).html();
                            result2 = $(this).children("td").eq(5).html();
                            result3 = $(this).children("td").eq(7).html();
                            result4 = $(this).children("td").eq(8).html();
                            result5 = $(this).children("td").eq(9).html();
                            result = result0 + "," + result1 + "," + result2 + "," + result3 + "," + result4 + "," + result5;
                        }
                    }
                })
                if (selectedCount > 1) {
                    alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount < 1) {
                    alert("请选择一条记录！");
                    return false;
                } else {
                    return result;
                }
            }

            $('#Look').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount > 1) {
                    alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount < 1) {
                    alert("请选择一条记录！");
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['770px', '400px'],
                    content: 'MaterialInfoManagementLook.html',
                    btn: ['关闭']
                });
            });
            $('#Edit').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount > 1) {
                    alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount < 1) {
                    alert("请选择一条记录！");
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['770px', '450px'],
                    content: 'MaterialInfoManagementEdit.html',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "修改成功",
                            type: "success",
                            zIndex: 101,
                            confirmButtonText: "确定"
                        },
                        function () {
                            layer.close(index);
                        });
                    }
                });
            });

            $('#del').click(function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount == 0) {
                    alert("请选择一条记录！");
                    return false;
                }
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
                    swal({
                        title: "删除成功",
                        type: "success",
                        text: "您已经永久删除了这条信息。",
                        confirmButtonText: "确定"
                    },
                      function () {

                      });
                });
            });
            /*$('#Add').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['770px', '450px'],
                    content: 'MaterialInfoManagementAdd.html',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "添加成功",
                            type: "success",
                            zIndex: 101,
                            confirmButtonText: "确定"
                        },
                        function () {
                            layer.close(index);
                        });
                    }
                });
            });
            $('#btn1').on('click', function () {
               parent.layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: '/MaterialManagement/MaterialInfoManagement/SelectMaterialCategory.jsp',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var child = $(layero).find("iframe")[0].contentWindow.checked()

                        if (child != false) {
                            $("#txt1").val(child);
                            parent.layer.close(index);
                        }
                    }
                });
            });*/
        </script>
    </form>
</body>
</html>
