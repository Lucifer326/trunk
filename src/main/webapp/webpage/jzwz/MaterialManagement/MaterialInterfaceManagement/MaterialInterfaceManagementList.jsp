<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>列表页</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" />
    <link href="/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/css/animate.css" rel="stylesheet" />
    <link href="/css/style.css" rel="stylesheet" />
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
    <style type="text/css">
        .txa
        {
            text-align: center;
        }

        .br
        {
            border-right: solid 1px #ddd;
        }
    </style>
</head>
<body class="gray-bg">
    <form id="form1">
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="alert alert-success whj_location"><span class="c6">当前位置：系统管理 &nbsp > &nbsp</span>  接口设计</div>
            <!--<div class="row">-->
                <!--<div class="col-sm-12">-->
                    <!--<div class="ibox float-e-margins">-->
                        <!--<div class="ibox-title">-->
                            <!--<h5>搜索条件</h5>-->
                            <!--<div class="ibox-tools">-->
                                <!--<a class="collapse-link">-->
                                    <!--<i class="fa fa-chevron-up"></i>-->
                                <!--</a>-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--<div class="ibox-content whj-padding">-->
                            <!--<div class="form-group">-->
                                <!--<ul class="ulist">-->
                                    <!--<li>-->
                                        <!--<span class="fl labeltext">仓库级别：</span>-->
                                        <!--<div class="fl">-->
                                            <!--<select class="form-control w-196">-->
                                                <!--<option>-请选择-</option>-->
                                                <!--<option>中央级</option>-->
                                                <!--<option>自治区级</option>-->
                                                <!--<option>地州级</option>-->
                                                <!--<option>县级</option>-->
                                            <!--</select>-->
                                        <!--</div>-->
                                        <!---->
                                        <!--<div class="clear"></div>-->
                                    <!--</li>-->
                                    <!--<li>-->
                                        <!--<span class="fl labeltext">仓库名称：</span>-->
                                        <!--<div class="fl">-->
                                            <!--<select class="form-control w-196">-->
                                                <!--<option>-请选择-</option>-->
                                                <!--<option>乌鲁木齐库</option>-->
                                                <!--<option>喀什库</option>-->
                                                <!--<option>奎屯库</option>-->
                                            <!--</select>-->
                                        <!--</div>-->

                                        <!--<div class="clear"></div>-->
                                    <!--</li>-->
                                    <!--<li>-->
                                        <!--<button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>-->
                                    <!--</li>-->
                                <!--</ul>-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--&lt;!&ndash;ibox-content&ndash;&gt;-->
                    <!--</div>-->
                <!--</div>-->
            <!--</div>-->
            <!--row-->
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="example-wrap">
                                <div class="example">
                                    <div class="btn-fl">
                                        <button type="button" class="btn btn-outline btn-import" id="Lead">导出</button>
                                        <!--<button type="button" class="btn btn-outline btn-primary" id="Print">打印</button>-->
                                    </div>
                                    <table id="" class="table table-bordered" data-mobile-responsive="true">
                                        <thead>
                                            <tr class="table-title">
                                                <th>名称</th>
                                                <th>接口名</th>
                                                <th>参数</th>
                                                <th>返回值</th>
                                            </tr>
                                        </thead>
                                        <!--数据部分-->
                                        <tbody id="tab">
                                        <tr>
                                            <td>库存接口</td>
                                            <td>getInventory()</td>
                                            <td> CentralInventoryId (中央库存id type:String)</td>
                                            <td>name(名称),address(地址),number(数量),category(类别)</td>
                                        </tr>
                                        <tr>
                                            <td>仓库接口</td>
                                            <td>getWarehouse()</td>
                                            <td>CentralWarehouseId (中央仓库id type:String)</td>
                                            <td>name(名称),address(地址),number(数量),category(类别)</td>
                                        </tr>
                                        <tr>
                                            <td>调拨接口</td>
                                            <td>getAllocation()</td>
                                            <td>CentralAllocation (中央调拨id type:String)</td>
                                            <td>name(名称),address(地址),number(数量),category(类别)</td>
                                        </tr>
                                        <tr>
                                            <td>物资类别接口</td>
                                            <td>getMaterialCategory()</td>
                                            <td>CentralMaterialCategory (中央物资类别id type:String)</td>
                                            <td>name(名称),address(地址),number(数量),category(类别)</td>
                                        </tr>
                                        <tr>
                                            <td>物资出库单接口</td>
                                            <td>getMaterialDelivery()</td>
                                            <td>CentralMaterialDelivery (中央物资出库单id type:String)</td>
                                            <td>name(名称),address(地址),number(数量),category(类别)</td>
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
        <script src="/js/jquery.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="/js/content.js"></script>
        <!-- Bootstrap table -->
        <script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="/js/demo/bootstrap-table-demo.js"></script>
        <script src="/js/plugins/layer/laydate/laydate.js"></script>
        <script src="/js/plugins/layer/layer.min.js"></script>
        <script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- 个人js -->
        <script src="/js/my.js"></script>
        <script>
            ////导出
            $('#Lead').on('click', function () {
                alert("导出成功！");
            });
//            $('#Print').on('click', function () {
//                alert("打印成功！");
//            });
        </script>
    </form>
</body>
</html>
