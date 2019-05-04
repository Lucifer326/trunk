<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <form id="form1">
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span>  仓库机构信息管理</div>
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
                                <div class="form-group">
                                    <ul class="ulist">
                                        <li>
                                            <span class="fl labeltext">库房名称：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control">
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                       
                                        <li>
                                            <span class="fl labeltext">建成时间：</span>
                                            <input id="hello1" class="laydate-icon form-control layer-date fl w-148" />
                                            <span class="fl communicate">―</span>
                                            <input id="hello2" class="laydate-icon form-control layer-date fl w-148" />
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">库房类型：</span>
                                            <div class="fl">
                                                <select class="form-control w-196">
                                                    <option>-请选择-</option>
                                                    <option>自有库</option>
                                                    <option>租赁库</option>
                                                </select>
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

                    <div class="ibox-content">
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTableEvents" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="check" data-checkbox="true"></th>
                                            <th data-field="id" data-sort-stable="true">序号</th>
                                            <th data-field="name" data-sort-stable="true">库房名称 </th>
                                            <th data-field="type" data-sort-stable="true">库房类型</th>
                                            <th data-field="time" data-sort-stable="true">建成时间</th>
                                            <th data-field="scale" data-sort-stable="true">建设规模(平方米)</th>
                                            <th data-field="area" data-sort-stable="true">库房面积(平方米)</th>
                                            <th data-field="address" data-sort-stable="true">库房地址</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>1</td>
                                            <td>抗灾救助帐篷库</td>                                         
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>2</td>
                                            <td>抗灾救助帐篷库</td>

                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>                                      
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>3</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>4</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>5</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>6</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>       
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>7</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>8</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>9</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>10</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>11</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>12</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="checkss" type="checkbox"></td>
                                            <td>13</td>
                                            <td>抗灾救助帐篷库</td>
                                            <td>自有库</td>
                                            <td>2016-12-01</td>
                                            <td>10000</td>
                                            <td>50000</td>
                                            <td>新疆维吾尔自治区克拉玛依市乌尔禾区北新街44号</td>
                                        </tr>

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
        <script src="/js/jquery.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="/js/content.js"></script>
        <!-- Bootstrap table -->
        <script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="/js/demo/bootstrap-table-demo2.js"></script>
        <script src="/js/plugins/layer/laydate/laydate.js"></script>
        <script src="/js/plugins/layer/layer.min.js"></script>
        <script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
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

            //添加
            $('#Add').on('click', function () {
                layer.open({
                    type: 2,
                    title: '新建',
                    zIndex: 1000,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'SocialDonationManageAdd',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "新增成功",
                            zIndex: 19891014,
                            type: "success",
                            confirmButtonText: "确定",
                        }, function () {
                            layer.close(index);
                        });
                    }
                });
            });
            //查看
            $('#Look').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'SocialDonationManageLook',
                    btn: ['关闭']
                });
            });
            //修改
            $('#Edit').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                }
                else if (selectedCount == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }

                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    zIndex: 1000,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'SocialDonationManageEdit',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        swal({
                            title: "修改成功",
                            zIndex: 19891014,
                            type: "success",
                            confirmButtonText: "确定",
                        }, function () {
                            layer.close(index);
                        });
                    }
                });
            });

            //导出
            $('#Export').on('click', function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount < 1) {
                    layer.alert("最少选择一条记录！");
                    return false;
                }
                swal({
                    title: "确认要导出吗",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonText: "取消",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "导出",
                    closeOnConfirm: false
                }, function () {
                    swal("导出成功！", " ", "success");
                    $(".sa-button-container .confirm").text("确定");
                });
            });

            //删除
            $('#del').click(function () {
                var selectedCount = 0;
                $("#exampleTableEvents").find('tr').each(function () {
                    if ($(this).hasClass('selected')) {
                        selectedCount++
                    }
                })
                if (selectedCount < 1) {
                    layer.alert("最少选择一条记录！");
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
                    swal("删除成功！", "您已经永久删除了这条信息。", "success");
                    $(".sa-button-container .confirm").text("确定");
                });
            });

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
    </form>
</body>
</html>
