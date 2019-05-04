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
            <div class="alert alert-success whj_location">
                <span class="c6">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span>物资出库
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <form id="form1">
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
                                        <span class="fl labeltext">出库单号：</span>
                                        <div class="fl">
                                            <input type="text" name="outhouseNumber" value="${vo.outhouseNumber}" class="form-control" onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':; '\\,\[\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？]/g,'');"/>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">出库仓库：</span>
                                        <div class="fl">
                                            <div id="Div1">
                                                <input type="text" id="txt" name="repositoryName" value="${vo.repositoryName}" readonly="readonly" class="form-control fl" style="width: 70%" />
                                                <button class="btn btn-primary btna" id="btn" style="width: 30%" type="button">&nbsp;选择</button>
                                            </div>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    
                                    <li>
                                        <span class="fl labeltext">出库日期：</span>
                                        <input id="hello" name="outhouseDate" value='<fmt:formatDate value="${vo.outhouseDate}" pattern="yyyy-MM-dd"/>' class="laydate-icon form-control layer-date fl w-148"/>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
                                    </li>
                                    <li>
                                        <button class="btn btn-warning btnh" id="reset" type="button">重置</button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        </form>
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
                                        <button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
                                        <button type="button" class="btn btn-outline btn-success" id="Look">查看</button>
                                        <button type="button" class="btn btn-outline btn-warning" id="Edit">修改</button>
                                        <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
                                        <button type="button" class="btn btn-outline btn-appro" id="examine">审批</button>
                                    </div>
                                    <table id="exampleTableEvents" data-mobile-responsive="true">
                                        <thead>
                                            <tr class="table-title">
                                                <%--<th data-field="state" data-checkbox="true" id="CheckBoxAll"></th>--%>
                                                <th></th>
                                                <th>序号</th>
                                                <th>出库单号</th>
                                                <th>出库仓库</th>
                                                <th>出库人</th>
                                                <th>出库日期</th>
                                                <th>附件</th>
                                                <th>领取人</th>
                                                <th>审批状态</th>
                                            </tr>
                                        </thead>
                                        <!--数据部分-->
                                        <tbody id="tab">
                                            <%--<tr>
                                                <td>
                                                    <input type="checkbox" name="check_list"/></td>
                                                <td>1</td>
                                                <td>FE5496</td>
                                                <td>抗灾救助食品库</td>
                                                <td>张萌萌</td>
                                                <td>2014-01-07</td>
                                                <td><a style="color: blue;" href="${pageContext.request.contextPath}/webpage/jzwz/MaterialsOutPut/物资出库统计报告.xlsx">物资出库统计报告.xlsx</a></td>
                                                <td>张瑞</td>
                                            </tr>--%>
                                            <c:forEach items="${outPutList}" var="ele" varStatus="status" >
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" values="${ele.UUID}" create_by="${ele.CREATE_BY}" approval_status="${ele.APPROVAL_STATUS}" class="ck_bx" name="chex"/></td>
                                                    <td>${status.index+1}</td>
                                                    <td>${ele.outhouse_Number}</td>
                                                    <td>${ele.REPOSITORY_NAME}</td>
                                                    <td>${ele.outhouse_Person}</td>
                                                    <td><fmt:formatDate value="${ele.outhouse_Date}" pattern="yyyy-MM-dd"/> </td>
                                                    <%--fileUpload/402812816a054588016a05527abe0003.png--%>
                                                    <%--${pageContext.request.contextPath}/webpage/jzwz/MaterialsOutPut/物资出库统计报告.xlsx--%>
                                                    <td><a href="${pageContext.request.contextPath}/MaterialsOutPut.do?fileDown&uuid=${ele.UUID}">${ele.ACCESSORY_NAME}</a></td>
                                                    <td>${ele.receiver}</td>
                                                    <td><c:choose>
                                                        <c:when test="${ele.APPROVAL_STATUS eq approved.typecode}">
                                                            ${approved.typename}
                                                        </c:when>
                                                        <c:when test="${ele.APPROVAL_STATUS eq rejection.typecode}">
                                                            ${rejection.typename}
                                                        </c:when>
                                                        <c:otherwise>
                                                            待审批
                                                        </c:otherwise>
                                                    </c:choose></td>
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
        //点击重置,重置表单并刷新页面
        $("#reset").click(function(){
            $("#form1 input").val("");
            to_page();
        });
       //点击搜索按钮,提交表单查询数据
        $("#btnh").click(function(){
            // $("#form1").submit();
            to_page();
        });
        function to_page(){
            window.location.href="${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsOutPutList&"+$("#form1").serialize();
        }
        $(":button[name=refresh]").click(function(){
            to_page();
        });
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
        //新建
        $('#Add').on('click', function () {
            layer.open({
                type: 2,
                title: '新建',
                zIndex: 1000,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsOutPutAdd',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //获取子窗口表单数据
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("add_form");
                    var check = outHouseCheckForm($(layero).find('iframe')[0].contentWindow,form);
                    if (!check){
                        return false;
                    }
                    //计算同一个物资的出库数量之和与调拨数量进行比较
                    var flag = true;//true表示出库数量与调拨数量一直
                    $.each($(form).find("input[name=subId]"),function(i,ele){
                        var sum = 0;//出库数量之和
                        $.each($(form).find("input[name=subId]"),function(){
                            if($(this).val() == $(ele).val()){
                                sum += parseInt($(this).parent().parent().find("input[name=outhouseAmount]").eq(0).val());
                            }
                        });
                        //获取该物资调拨数量
                        var allotAmount = parseInt($(ele).parent().parent().find("input[name=allotAmount]").eq(0).val());
                        //比较调拨数量与出库数量之和
                        if(sum != allotAmount){
                            flag = false;
                            //获取物资名称,提示用户该物资怎么怎么滴
                            var subName = $(ele).parent().find("input[name=subName]").eq(0).val();
                            var msg = subName +"的出库数量";
                            if(sum > allotAmount){
                                msg += "大于调拨数量";
                            }else{
                                msg += "小于调拨数量";
                            }

                            swal({
                                title: msg,
                                text: "您确认要新建这条信息吗?",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonColor: "#DD6B55",
                                confirmButtonText: "确认",
                                cancelButtonText: "取消",
                                closeOnConfirm: false
                            }, function () {
                                createOutHose(form,index);
                                // swal.close();
                            });
                            return false;
                        }
                    });
                    if(flag){
                        createOutHose(form,index);
                    }
                }
            });
        });
        function createOutHose(form,index){
            var fileForm = new FormData(form);
            //发送ajax请求,新建出库信息
            $.ajax({
                url:"${pageContext.request.contextPath}/materialsOutPutController.do?saveOutPut",
                type:"POST",
                data:fileForm,
                dataType: "json",
                processData:false,
                contentType:false,
                error:function(){
                    swal({
                        title: "服务器无响应",
                        zIndex: 19891014,
                        confirmButtonText: "确定",
                        type: "warning",
                        timer: 2000
                    }, function () {
                    });
                },
                success:function(data){
                    if(data.success){
                        //新建成功,关闭子窗口
                        swal({
                            title: "新增成功",
                            zIndex: 19891014,
                            confirmButtonText: "确定",
                            type: "success",
                            timer: 2000
                        }, function () {
                            layer.close(index);
                            to_page();
                        });
                    }else{
                        //新建失败,提示信息,不关闭子窗口
                        swal({
                            title: data.msg,
                            zIndex: 19891014,
                            confirmButtonText: "确定",
                            type: "warning",
                            timer: 2000
                        }, function () {
                        });
                    }
                }
            });
        }



        //修改
        $('#Edit').on('click', function () {

            //定义一个数组，用于存放所选中的id值
            var ids = [];
            var approval_status = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#tab input[type=checkbox]:checked').each(function() {
                ids.push($(this).attr('values'));
                approval_status.push($(this).attr('approval_status'));
            });
            //根据ids数组的长度判断所选择的条数
            if (ids.length > 1) {
                layer.msg("只能选择一条记录！",{icon: 2,time:2000});
                return false;
            } else if (ids.length == 0) {
                layer.msg("请选择一条记录！",{icon: 2,time:2000});
                return false;
            }
            //获取所选择的的id
            var id = ids[0];
            var approval_statu = approval_status[0];
            if(approval_statu != ''){
                swal({
                    title: "出库单已被审批,无法修改",
                    zIndex: 19891014,
                    confirmButtonText: "确定",
                    type: "warning",
                    timer: 2000
                });
                return false;
            }
            layer.open({
                type: 2,
                title: '修改',
                zIndex: 1000,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsOutPutEdit&uuid='+id,
                btn: ['确定', '取消'],
                yes: function (index, layero) {

                    //获取子窗口form数据
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("edit_from");

                    var check = outHouseDetailCheckForm($(layero).find("iframe")[0].contentWindow,form);
                    if (!check){
                        return false;
                    }
                    //计算同一个物资的出库数量之和与调拨数量进行比较
                    var flag = true;//true表示出库数量与调拨数量一直
                    $.each($(form).find("input[name=subId]"),function(i,ele){
                        var sum = 0;//出库数量之和
                        $.each($(form).find("input[name=subId]"),function(){
                            if($(this).val() == $(ele).val()){
                                sum += parseInt($(this).parent().parent().find("input[name=outhouseAmount]").eq(0).val());
                            }
                        });
                        //获取该物资调拨数量
                        var allotAmount = parseInt($(ele).parent().parent().find("input[name=allotAmount]").eq(0).val());
                        //比较调拨数量与出库数量之和
                        if(sum != allotAmount){
                            flag = false;
                            //获取物资名称,提示用户该物资怎么怎么滴
                            var subName = $(ele).parent().find("input[name=subName]").eq(0).val();
                            var msg = subName +"的出库数量";
                            if(sum > allotAmount){
                                msg += "大于调拨数量";
                            }else{
                                msg += "小于调拨数量";
                            }

                            swal({
                                title: msg,
                                text: "您确认要修改这条信息吗?",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonColor: "#DD6B55",
                                confirmButtonText: "确认",
                                cancelButtonText: "取消",
                                closeOnConfirm: false
                            }, function () {
                                updateOutHose(form,index);
                                // swal.close();
                            });
                            return false;
                        }
                    });
                    if(flag){
                        updateOutHose(form,index);
                    }
                }
            });
        });

        function updateOutHose(form,index){
            var fileForm = new FormData(form);
            //发送请求
            $.ajax({
                url:"${pageContext.request.contextPath}/materialsOutPutController.do?saveOutPut",
                data:fileForm,
                type:"POST",
                dataType:"JSON",
                processData:false,
                contentType:false,
                error:function(){
                    swal({
                        title: "服务器没有响应",
                        zIndex: 19891014,
                        confirmButtonText: "确定",
                        type: "warning",
                        timer: 2000
                    }, function () {
                    });
                },
                success:function(data){
                    if(data.success){
                        swal({
                            title: "修改成功",
                            zIndex: 19891014,
                            confirmButtonText: "确定",
                            type: "success",
                            timer: 2000
                        }, function () {
                            layer.close(index);
                            to_page();
                        });
                    }else{
                        swal({
                            title: data.msg,
                            zIndex: 19891014,
                            confirmButtonText: "确定",
                            type: "warning",
                            timer: 2000
                        }, function () {
                        });
                    }
                }
            });
        }


        //查看
        $('#Look').on('click', function () {
            //定义一个数组，用于存放所选中的id值
            var ids = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#tab input[type=checkbox]:checked').each(function() {
                ids.push($(this).attr('values'));
            });
            //根据ids数组的长度判断所选择的条数
            if (ids.length > 1) {
                layer.msg("只能选择一条记录！",{icon: 2,time:2000});
                return false;
            } else if (ids.length == 0) {
                layer.msg("请选择一条记录！",{icon: 2,time:2000});
                return false;
            }
            //获取所选择的的id
            var id = ids[0];
            layer.open({
                type: 2,
                title: '查看',
                maxmin: true,
                zIndex: 1000,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                content: '${pageContext.request.contextPath}/MaterialsOutPut.do?MaterialsOutPutLook&uuid='+id,
                btn: ['关闭']
            });
        });
        //删除
        $('#del').click(function () {
            //获取被删除数据的主键值们(拼接字符串)
            var ids = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#tab input[type=checkbox]:checked').each(function() {
                ids.push($(this).attr('values'));
            });
            if (ids.length > 1) {
                layer.alert("只能选择一条记录！");
                return false;
            } else if (ids.length == 0) {
                layer.alert("请选择一条记录！");
                return false;
            }
            //根据ids数组的长度判断所选择的条数
            /*if (ids.length < 1) {
                layer.alert("请至少选择一条记录！！");
                return false;
            }
            var idstr = "";
            for (var i=0;i<ids.length;i++){
                idstr += ids[i] + ",";
            }*/
            // idstr = idstr.substring(0,idstr.length-1);
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
                //发送请求删除数据
                $.ajax({
                    url:"${pageContext.request.contextPath}/materialsOutPutController.do?delOutPut&uuid="+ids[0],
                    type:"get",
                    dataType:"JSON",
                    error:function () {
                        swal({
                            title: "服务器没有响应",
                            zIndex: 19891014,
                            confirmButtonText: "确定",
                            type: "warning",
                            timer: 2000
                        }, function () {
                        });
                    },
                    success:function(data){
                        if (data.success){
                            swal("删除成功！", "您已经永久删除了这条信息。", "success");
                            $(".sa-button-container .confirm").text("确定");
                            $(".sa-button-container .confirm").click(function(){
                                to_page();
                            });
                        }else{
                            swal({
                                title: data.msg,
                                zIndex: 19891014,
                                confirmButtonText: "确定",
                                type: "warning",
                                timer: 2000
                            }, function () {
                            });
                            $(".sa-button-container .confirm").text("确定");
                        }
                    }
                });

            });
        });

        //选择仓库
        $('#btn').on('click', function () {
            layer.open({
                type: 2,
                title: '出库仓库',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '500px'],
                zIndex: 1200,
                content: '${pageContext.request.contextPath}/materialsInputController.do?selectWarehouse',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect();
                    if (data != false) {
                        var str = data.split(',');
                        $("#txt").val(str[0]);
                        layer.close(index);
                    }
                    layer.close(index);
                }

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
        $('#examine').on('click', function () {

            //获取选中复选框的id
            var exam = [];
            var approval_status = [];
            var create_by = [];
            //遍历复选框并将主键放入数组中
            $("#tab input[type='checkbox']:checked").each(function (i){
                exam .push($(this).attr('values')) ;
                approval_status.push($(this).attr('approval_status'));
                create_by.push($(this).attr('create_by'));
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
                    timer: 2000
                }, function () {
                });
                return false;
            }
            //获取该出库单创建人登录名称
            var createBy = create_by[0];
            var userName = '${sessionScope.LOCAL_CLINET_USER.userName}';
            //若该出库单为自己创建则不能审批
            if(createBy == userName){
                swal({
                    title: "不能审批自己创建的出库单",
                    zIndex: 19891014,
                    confirmButtonText: "确定",
                    type: "warning",
                    timer: 2000
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
                    //校验表单数据
                    var status = $(form).find("select").eq(0).val();
                    if (status == '' || status == '0'){
                        layer.alert("请选择审批状态！");
                        return false;
                    }

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
                                timer: 2000
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
                                    timer: 2000
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
                                    timer: 2000
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


