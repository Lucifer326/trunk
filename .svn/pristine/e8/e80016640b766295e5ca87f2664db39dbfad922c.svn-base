<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
    <form>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="alert alert-success whj_location"><span class="c6">当前位置：自然灾害救助信息管理系统 &nbsp > &nbsp 物资管理 &nbsp > &nbsp</span>物资信息管理 </div>
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
                                        <span class="fl labeltext">物资编号：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" />
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">物资品名：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" />
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">物资类别：</span>
                                        <div class="fl">
                                            <input type="text" id="txt1" name="" class="form-control fl" style="width: 70%">
                                            <button class="btn btn-primary btnh" id="btn1" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>
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
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                        <c:forEach items="${categoryList}" var="category" varStatus="s">
                                            <tr>
                                                <th data-field="state" data-checkbox="true"></th>
                                                <th data-field="number">序号</th>
                                                <th data-field="num">物资编号</th>
                                                <th data-field="name">物资品名</th>
                                                <th data-field="type">物资类别</th>
                                                <th data-field="size">规格型号</th>
                                                <th data-field="danwei">单位</th>
                                            </tr>
                                        </c:forEach>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="checkbox" name="checkss"></td>
                                                <td>${s.index}</td>
                                                <td>${category.categoryNumber}</td>
                                                <td>${category.categoryName}</td>
                                                <td>${category.categoryNumber}</td>
                                                <td>200G*24</td>
                                                <td>箱</td>

                                            </tr>

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
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="${pageContext.request.contextPath}/js/content.js"></script>
        <!-- Bootstrap table -->
        <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo2.js"></script>

        <!-- 个人js -->
        <script src="${pageContext.request.contextPath}/js/my.js"></script>
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
            $('#Add').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    maxmin: true,
                    zIndex: 100,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['770px', '450px'],
                    content: 'MaterialInfoManagement.do?',
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
                    title: '物资类别选择',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['700px', '420px'],
                    content: '../MaterialManagement/MaterialInfoManagement/SelectMaterialCategory.jsp',
                    btn: ['确定', '取消']
                });
            });

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
        </script>
    </form>
</body>
</html>
