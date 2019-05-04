<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script>
        function SelectDiv() {
            layer.open({
                type: 2,
                title: '选择区划',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['400px', '300px'],
                content: '/CommonPage/SelectDiv.html',
                btn: ['确定', '取消']
            });
        }
    </script>
</head>
<body class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <!--<div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救助物资管理系统 &nbsp > &nbsp</span>物资入库 </div>-->
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
                                            <span class="fl labeltext">物资品名：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control" />
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">捐赠区域：</span>
                                            <div class="fl">
                                                <input type="text" class="form-control" name=""/>
                                            </div>
                                            <button class="btn btn-primary btna " id="btna" type="button" onclick="SelectDiv()">&nbsp;选择</button>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">物资类别：</span>
                                            <div class="fl">
                                                <select class="form-control w-196">
                                                    <option>-全部-</option>
                                                    <option>被服类</option>
                                                    <option>安置类</option>
                                                    <option>装具类</option>
                                                    <option>装备类</option>
                                                    <option>食品其他类</option>
                                                </select>
                                            </div>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="fl labeltext">入库日期：</span>
                                            <input id="hello" class="laydate-icon form-control layer-date fl w-148">
                                            <span class="fl communicate">―</span>
                                            <input id="hello1" class="laydate-icon form-control layer-date fl w-148">
                                            <div class="clear"></div>
                                        </li>
                                    
                                        <li>
                                            <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
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
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr>
                                                <th data-field="state" data-checkbox="true"></th>
                                                <th data-field="number">序号</th>
                                                <th data-field="num">物资品名</th>
                                                <th data-field="n">物资类别</th>
                                                <th data-field="type">入库仓库</th>
                                                <th data-field="size">入库数量</th>
                                                <th data-field="nam">计量单位</th>
                                                <th data-field="name">入库日期</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>1</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>2</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>3</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>4</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>5</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>6</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>7</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="checkss"></td>
                                                <td>8</td>
                                                <td>蔬菜</td>
                                                <td>食品其它类</td>
                                                <td>抗灾救助食品库</td>
                                                <td>300</td>
                                                <td>箱</td>
                                                <td>2016-12-16</td>
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
        </script>
</body>
</html>

