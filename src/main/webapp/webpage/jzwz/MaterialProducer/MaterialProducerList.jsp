﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>物资生产商列表页改版</title>
    <link href="${url}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${url}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${url}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${url}/css/animate.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
    <link href="${url}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <script>
        function SelectDiv() {
            layer.open({
                type: 2,
                title: '选择区划',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['400px', '300px'],
                content: '/CommonPage/SelectDiv.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    $("#sdiv").val("乌鲁木齐市天山区");
                    parent.layer.close(index);
                }
            });
        }
    </script>
</head>
<body class="gray-bg">
    <form id="productForm">
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span> 物资生产商 &nbsp</div>
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
                                <form >
                                <ul class="ulist">
                                    <li>
                                        <span class="fl labeltext">公司名称：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" name="name" id="name" value="${name}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]/g,'');">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">主营产品：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" name="product" id="product" value="${product}" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]/g,'');">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                     <li>
                                        <span class="fl labeltext">是否录入民政部：</span>
                                        <div class="fl">
                                            <select class="form-control w-196" name="inPut" id="inPut" >
                                                <option value="">-全部-</option>
                                                <c:forEach items="${isOrNot}" var="item">
                                                    <option  value="${item.typecode}" ${item.typecode eq inPut ? "selected='selected'":""}>${item.typename} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
                                    </li>
                                </ul>
                                </form>

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
                                    <div class="btn-fl">
                                        <button type="button" class="btn btn-outline  btn-primary" id="Add">新建</button>
                                        <button type="button" class="btn btn-outline  btn-success" id="Look">查看</button>
                                        <button type="button" class="btn btn-outline  btn-warning" id="Edit">修改</button>
                                        <button type="button" class="btn btn-outline  btn-danger" id="del">删除</button>
                                    </div>


                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th data-field="id" data-sort-stable="true">序号</th>
                                                <th data-field="name">公司名称</th>
                                                <th>公司简介</th>
                                                <th>主营产品</th>
                                                <th>联系人</th>
                                                <th>联系电话</th>
                                                <th>地址</th>
												<th>是否录入民政部《自然灾害应急救助物资生产商参考名录》</th>
                                              <%--  <th data-visible="true">详细购买记录</th>--%>
                                            </tr>
                                        </thead>

                                        <tbody id="table_content">
                                        <c:forEach items="${productList}" var="pro" varStatus="i">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" values="${pro.uuid}" ></td>
                                                <td>${i.count}</td>
                                                <td>${pro.name}</td>
                                                <td>${pro.companyInfo}</td>
                                                <td>${pro.product}</td>
                                                <td>${pro.contact}</td>
                                                <td>${pro.phone}</td>
                                                <td>${pro.address}</td>
												<td>
                                                    <c:forEach items="${isOrNot}" var="item">
                                                        <c:if test="${pro.inPut eq item.typecode}">${item.typename}</c:if>
                                                    </c:forEach>
                                                </td>
                                              <%-- <td><a  style="color: blue;"  id="query" onclick="clickk()">购买记录查询</a></td>--%>
                                                <%--<td>购买记录查询</td>--%>
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
        <script src="${url}/js/jquery.min.js"></script>
        <script src="${url}/js/bootstrap.min.js"></script>
        <!-- 自定义js -->
        <script src="${url}/js/content.js"></script>
        <script src="${url}/js/jzwz/material_producer/formCheck.js"></script>
        <!-- Bootstrap table -->
        <script src="${url}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
        <script src="${url}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
        <script src="${url}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

        <!-- Peity -->
        <script src="${url}/js/demo/bootstrap-table-demo.js"></script>
        <script src="${url}/js/plugins/layer/laydate/laydate.js"></script>
        <script src="${url}/js/plugins/layer/layer.min.js"></script>
        <script src="${url}/js/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- 个人js -->
        <script src="${url}/js/my.js"></script>
        <script>
            $(function(){
                $('#userData input[type=checkbox]').click(
                    function(){
                        var val= $(this).attr('values')
                        console.log('值：',val)
                    }
                )
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
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    zIndex: 100,
                    content: 'materialProducerController.do?productAdd',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var form = layero.find("iframe")[0].contentWindow;
                        if(validateForm(form)){
                            $.ajax({
                                type: "post",
                                dataType:"JSON",
                                url: "materialProducerController.do?productIns",
                                data: layero.find("iframe")[0].contentWindow.$("#myForm").serialize(),//序列化前需要把html元素转成jquery元素，再用jquery元素的序列化方法提交。。$(dom元素)。。给钱就可以
                                success: function (result) {
                                    if(result.success){
                                        swal({
                                            title: "新增成功",
                                            zIndex: 101,
                                            type: "success",
                                            confirmButtonText: "确定",
                                        }, function () {
                                            layer.close(index);
                                            window.location.href="${url}/materialProducerController.do?productList";
                                        });
                                        //layer.close(index);
                                    }else {
                                    }
                                }
                            });
                        }
                    }
                });
            });
            //查看按钮
            $('#Look').on('click', function () {
                //var count = $("input[name='check_list']:checked").length;
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                })
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                var id = ids[0];
                layer.open({
                    type: 2,
                    title: '查看',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'materialProducerController.do?productLook&uuid='+id,
                    btn: ['关闭']
                });
            });
            //修改按钮
            $('#Edit').on('click', function () {
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));

                })
                //根据ids数组的长度判断所选择的条数
                if (ids.length > 1) {
                    layer.alert("只能选择一条记录！");
                    return false;
                } else if (ids.length == 0) {
                    layer.alert("请选择一条记录！");
                    return false;
                }
                var id = ids[0];
                layer.open({
                    type: 2,
                    title: '修改',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '520px'],
                    zIndex: 100,
                    content: 'materialProducerController.do?productEdit&uuid=' + id,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var form = layero.find("iframe")[0].contentWindow;
                        if(validateForm(form)){
                            $.ajax({
                                type: "post",
                                url: "materialProducerController.do?productUpd",
                                data: layero.find("iframe")[0].contentWindow.$("#myForm").serialize(),//序列化前需要把html元素转成jquery元素，再用jquery元素的序列化方法提交。。$(dom元素)。。给钱就可以
                                dataType:"JSON",
                                success: function (result) {
                                    /*console.log(result);
                                    layer.close(index);*/
                                    if(result.success){
                                        swal({
                                            title: "修改成功",
                                            zIndex: 101,
                                            type: "success",
                                            confirmButtonText: "确定",
                                        }, function () {
                                            layer.close(index);
                                            window.location.href="${url}/materialProducerController.do?productList";
                                        })
                                    }else {
                                        layer.msg("信息修改失败，请重新操作", function(){

                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            });
            //删除、批量删除
            $('#del').click(function () {
                //定义一个接收id的数组
                var ids = [];
                $('#table_content input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                });
                if(ids.length == 0){
                    layer.alert("请选择一条数据")
                    return false;
                }else {
                    swal({
                        title: "您确定要删除这条信息吗",
                        text: "删除后将无法恢复，请谨慎操作！",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonText: "取消",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "删除",
                        closeOnConfirm: false
                    }, function () {
                        $.ajax({
                            type:"post",
                            url:"materialProducerController.do?productDels",
                            traditional:true,
                            cache:false,
                            data:{ids : ids},
                            dataType:"JSON",
                            success: function (result) {
                                if(result.success){
                                    swal({
                                        title: "删除成功",
                                        type: "success",
                                        confirmButtonText: "确定"
                                    }, function (index) {
                                        //关闭弹出框的父窗口
                                        layer.close(index);
                                        //重新加载列表页
                                        window.location.href = "${url}/materialProducerController.do?productList";
                                    });
                                }else {
                                }
                            },
                        });
                    });
                }
                });
/*            //购买记录查询
            function clickk(){
                layer.open({
                    type: 2,
                    title: '购买记录',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['800px', '490px'],
                    content: 'materialPurchaseRecordController.do?list',
                    btn: ['关闭']
                });
            }*/
            //条件查询
            $("#btnh").click(function () {
                var name = $("#name").val();
                var product = $("#product").val();
                var inPut = $("#inPut").val();
                window.location.href = "${url}/materialProducerController.do?productQuery&name="+name+"&product="+product+"&inPut="+inPut;
            });
        </script>
    </form>
</body>
</html>
