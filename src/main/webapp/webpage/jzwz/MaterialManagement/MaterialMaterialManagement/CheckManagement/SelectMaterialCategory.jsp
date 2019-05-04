<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>自治区自然灾害救助信息管理系统</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" />
    <link href="/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/css/animate.css" rel="stylesheet" />
    <link href="/css/style.css" rel="stylesheet" />
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
</head>
<body class="gray-bg">
    <form id="form1">
        <div class="wrapper wrapper-content animated fadeInRight">          
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
                                        <span class="fl labeltext">物资类别编号：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">物资类别名称：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control">
                                        </div>

                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="Button1" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
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
                                            <tr>
                                                <th data-field="state" data-checkbox="true" id="CheckBoxAll"></th>
                                                <th data-field="id" data-sort-stable="true">物资类别编号</th>
                                                <th data-field="name">物资类别名称</th>
                                                <th data-field="price">备注</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="choose" /></td>
                                                <td>1</td>
                                                <td>被服类</td>
                                                <td>库存较多，无需购买</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="choose" /></td>
                                                <td>2</td>
                                                <td>安置类</td>
                                                <td>库存较少，需及时订购</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="choose" /></td>
                                                <td>3</td>
                                                <td>装具类</td>
                                                <td>号码不全，需补充库存</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="choose" /></td>
                                                <td>4</td>
                                                <td>装备类</td>
                                                <td>及时维护，保证正常使用</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="choose" /></td>
                                                <td>5</td>
                                                <td>食品其它类</td>
                                                <td>十分紧俏，囤积发放</td>
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
    </form>

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
    <!-- 个人js -->
    <script src="/js/my.js"></script>
    <script type="text/javascript">
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
        function checked()
        {
            var selectedCount = 0;
            var result="";
            $("#exampleTableEvents").find('tr').each(function () {
                if ($(this).hasClass('selected')) {
                    selectedCount++;
                    if (selectedCount == 1) {
                        result = $(this).children("td").eq(2).html();
                    }}
            })
            if (selectedCount > 1) {
                alert("只能选择一条记录！");
                return false;
            }
            else if (selectedCount < 1) {
                alert("请选择一条记录！");
                return false;
            } else
            {
                return result;
            }      
        }
    </script>
</body>
</html>
