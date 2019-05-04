<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            <!--row-->
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="example-wrap">
                                <div class="example">
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr class="table-title">
                                                <th data-field="state" data-checkbox="true" id="CheckBoxAll"></th>
                                                <th data-field="id" >序号</th>
                                                <th data-field="name0">出库单号</th>
                                                <th data-field="name2">出库人</th>
                                                <th data-field="name3">出库日期</th>
                                                <th data-field="name5">领取人</th>
                                                <th data-field="name6">出库单标识</th>
                                            </tr>
                                        </thead>
                                        <!--数据部分-->
                                        <tbody id="tab">
                                            <c:forEach items="${outPutList}" var="ele" varStatus="status" >
                                                <tr>
                                                    <td><input type="checkbox" values="${ele.UUID}" approval_status="${ele.APPROVAL_STATUS}" class="ck_bx" name="chex"/></td>
                                                    <td>${status.index+1}</td>
                                                    <td>${ele.outhouse_Number}</td>
                                                    <td>${ele.outhouse_Person}</td>
                                                    <td><fmt:formatDate value="${ele.outhouse_Date}" pattern="yyyy-MM-dd"/> </td>
                                                    <td>${ele.receiver}</td>
                                                    <td>${ele.UUID}</td>
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
    <script src="js/jzwz/MaterialsOutPut/checkoutputForm.js"></script>
    <script>
        /*function to_page(pageNum){
            $.ajax({
               url:"materialsOutPutController?getAll&pageNum="+pageNum,
               type:"get",
               dataType:"JSON",
               success:function(data){
                    alert(data);
                    if(data.success){
                        build_tbody(data);
                    }else{
                        alert(data.msg);
                    }
               }
            });

        }*/
        //点击重置,重置表单
        $("#reset").click(function(){
            $("#form1 input").val("");
        });
       //点击搜索按钮,提交表单查询数据
        $("#btnh").click(function(){
            // $("#form1").submit();
            to_page();
        });
        function to_page(){
            window.location.href="${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsOutPutList&"+$("#form1").serialize();
        }

        function CheckSelect() {

            var selectedCount = 0;
            var result = "";

            $("#exampleTableEvents").find('tr').each(function () {
                if ($(this).hasClass('selected')) {
                    selectedCount++;
                    if (selectedCount == 1) {
                        //用来将上边的各个字段值传到别的页面
                        result = $(this).children("td").eq(2).html() + ","//入库单号
                            +$(this).children("td").eq(6).html() + ","//uuid
                    }
                }
            })
            if (selectedCount > 1) {
                layer.alert("只能选择一条记录！");
                return false;
            }
            else if (selectedCount < 1) {
                layer.alert("请选择一条记录！");
                return false;
            } else {
                return result;
            }
        }

        //外部js调用
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
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
        $('#examine').on('click', function () {
            //获取选中复选框的id
            var exam = [];
            var approval_status = [];
            //遍历复选框并将主键放入数组中
            $("#tab input[type='checkbox']:checked").each(function (i){
                exam .push($(this).attr('values')) ;
                approval_status.push($(this).attr('approval_status'));
            });
            if (exam.length <= 0) {
                layer.alert("请选择一条记录！");
                return false;
            }else if (exam.length > 1) {
                layer.alert("只能选择一条记录！");
                return false;
            }
            var approval_statu = approval_status[0];
            if(approval_statu != ''){
                swal({
                    title: "已审批该出库单,无法再次审批",
                    zIndex: 19891014,
                    confirmButtonText: "确定",
                    type: "warning",
                }, function () {
                });
                return false;
            }
            layer.open({
                type: 2,
                title: '审批',
                maxmin: true,
                zIndex: 100,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '320px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?AllocateValid',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //获取子页面的信息
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("forms");
                    //发送ajax请求
                    $.ajax({
                        url:"materialsOutPutController.do?approval&outhoseId="+exam[0],
                        data:$(form).serialize(),
                        type:"get",
                        dataType:"JSON",
                        error:function(){
                            swal({
                                title: "服务器没有响应",
                                zIndex: 19891014,
                                confirmButtonText: "确定",
                                type: "warning",
                            }, function () {
                            });
                        },
                        success:function(data){
                            if (data.success){
                                //审批成功
                                swal({
                                    title: "审批成功",
                                    zIndex: 19891014,
                                    confirmButtonText: "确定",
                                    type: "success",
                                }, function () {
                                    layer.close(index);
                                    to_page();
                                });
                            }else{
                                //审批失败
                                swal({
                                    title: data.msg,
                                    zIndex: 19891014,
                                    confirmButtonText: "确定",
                                    type: "warning",
                                }, function () {
                                });
                            }
                        }
                    });
                }
            });
        });

        function Approval(uuid) {

        }

    </script>

</body>
</html>


