<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>自治区自然灾害救助信息管理系统</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
<form id="form1">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="alert alert-success whj_location"><span class="c6">当前位置：自治区自然灾害救助信息管理系统 &nbsp > &nbsp 调拨通知 </span>
        </div>

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
                                        <span class="fl labeltext">调拨单据号：</span>
                                        <div class="fl">
                                            <input type="text" class="form-control" id="diaobo1" name="diaobodanjuhao"
                                                   value="${diaobodanjuhao}"
                                                   onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">救灾项目：</span>
                                        <div class="fl">
                                            <!--<input type="text" class="form-control">-->
                                            <input type="text" id="txt1" name="" class="form-control fl"
                                                   style="width: 70%" value="${project}"
                                                   onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');">
                                            <!--<input type="button" class="form-control" value="选择" style=" width:30%" id="btn" >-->
                                        </div>
                                        <div class="clear"></div>
                                    </li>

                                    <li>
                                        <span class="fl labeltext">调拨日期：</span>
                                        <input id="hello1" class="laydate-icon form-control layer-date fl w-148"
                                               value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"></fmt:formatDate>"
                                               onkeyup="this.value=this.value.replace(/[`~!@ ·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');"
                                        >

                                        <div class="clear"></div>
                                    </li>

                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i
                                                class="fa fa-search"></i>&nbsp;搜索
                                        </button>
                                        <input class="btn btn-warning btnh" id="reset" type="button"
                                               style="width: 70px; margin-left: 20px;" value="重置">
                                    </li>

                                </ul>
                                <div class="col-sm-1 iconfather">
                                    <i class="fa fa-angle-down "></i>
                                    <i class="fa fa-angle-up none"></i>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </form>

                    </div>
                    <!--ibox-content-->
                </div>
            </div>
        </div><!--row-->
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
                                <form id="form2">
                                    <table id="exampleTableEvents" data-height="" data-mobile-responsive="true">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th data-field="id" data-sort-stable="true">序号</th>
                                            <th data-field="name0">调拨单据号</th>
                                            <th data-field="name1"> 救灾项目</th>
                                            <th data-field="name2">调出部门</th>
                                            <th data-field="name3">调入部门</th>
                                            <th data-field="name4">调拨日期</th>
                                            <th data-field="name5">物资用途</th>

                                        </tr>
                                        </thead>
                                        <tbody id="noticelist">
                                        <c:forEach items="${list}" var="notice" varStatus="s">
                                            <tr>
                                                <td><input type="checkbox" values="${notice.uuid}"
                                                           isUse="${notice.isuse}"
                                                           allot_status="${notice.allotStatus}"/></td>
                                                <td>${s.count}</td>
                                                <td>${notice.allotNumber}</td>
                                                <td>${notice.reloefProject}</td>
                                                <td>${notice.calloutDepartment}</td>
                                                <td>${notice.callinDepartment}</td>
                                                <td><fmt:formatDate value="${notice.allotDate}"
                                                                    pattern="yyyy-MM-dd"></fmt:formatDate></td>
                                                <td>${notice.storageUse}</td>


                                            </tr>
                                        </c:forEach>


                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
    <%--1.这是校验用的js文件--%>
    <script src="${pageContext.request.contextPath}/js/jzwz/AllocationNotice/checkNoticeForm.js"></script>
    <script>

        $("#reset").click(function () {
            $("#diaobo1").val("");
            $("#txt1").val("");
            $("#hello1").val("");
        });

        //外部js调用
        laydate({
            elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        //模糊查询条件
        $('#btnh').on('click', function () {
            window.location.href = "NoticeController.do?showlikeBydanjuhao&diaobodanjuhao=" +
                $("#diaobo1").val() + "&project=" + $("#txt1").val()
                + "&date=" + $("#hello1").val();


        });

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

        $('#btn').on('click', function () {

            parent.layer.open({
                type: 2,
                title: '物资信息',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['840px', '520px'],
                zIndex: 101,
                content: 'DRMManagement/MaterialAllocation/MaterialAllocationNum.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("list");
                    if (data != false) {
                        $("#txt").val(data);
                        parent.layer.close(index);
                    }
                }

            });
        });


        $('#Add').on('click', function () {

            layer.open({
                type: 2,
                title: '新建',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                zIndex: 100,
                // allocationNoticeController.do?datalist
                content: 'NoticeController.do?datalist',
                btn: ['确定', '取消'],

                yes: function (index, layero) {
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("edit_from");
                    //再打开添加的子窗口时，若其中的某些字段不符合js文件校验的规则就返回
                    var check = checkForm($(layero).find('iframe')[0].contentWindow, form);//传入打开的子窗口给js文件校验

                    if (!check) {
                        return;//取消yes操作，弹出js中相应的消息
                    }


                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/NoticeController.do?add",
                        //cache缓存，true:缓存；false:不缓存
                        cache: false,
                        data: $(layero).find('iframe')[0].contentWindow.$('#edit_from').serialize(),
                        async: false,
                        success: function (data) {
                            //由于json信息中包含中文，需要解析json
                            var da = JSON.parse(data);
                            if (da.success) {
                                swal({
                                    title: da.msg,
                                    zIndex: 19891014,
                                    type: "success",
                                    confirmButtonText: "确定"
                                }, function () {
                                    layer.close(index);
                                    history.go(0);
                                });
                            } else {
                                swal({
                                    title: da.msg,
                                    zIndex: 19891014,
                                    type: "error",
                                    confirmButtonText: "确定"
                                });
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            swal({
                                title: "请检查数据是否填写完整",
                                zIndex: 19891014,
                                type: "warning",
                                confirmButtonText: "确定"
                            });
                        }
                    });

                }


            });
        });


        $('#copy').on('click', function () {
            swal({
                title: "打印成功",
                type: "success",
                confirmButtonText: "确定",
                zIndex: 101

            });
        });


        $('#Edit').on('click', function () {
            //定义一个数组，用于存放所选中的id值
            var ids = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#noticelist input[type=checkbox]:checked').each(function () {
                ids.push($(this).attr('values'));
            });
            //根据ids数组的长度判断所选择的条数
            if (ids.length > 1) {
                layer.alert("只能选择一条记录！");
                return false;
            } else if (ids.length == 0) {
                layer.alert("请选择一条记录！");
                return false;
            }

            // 校验是否已经被使用
            var approval_status = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#noticelist input[type=checkbox]:checked').each(function () {
                ids.push($(this).attr('values'));
                approval_status.push($(this).attr('isUse'));
            });
            var approval_statu = approval_status[0];
            if (approval_statu == '0') {
                swal({
                    title: "该调拨单已被审批,无法修改",
                    zIndex: 19891014,
                    confirmButtonText: "确定",
                    type: "warning",
                }, function () {
                });
                return false;
            }

            // 校验是否已经入库
            var allot_status = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#noticelist input[type=checkbox]:checked').each(function () {
                ids.push($(this).attr('values'));
                allot_status.push($(this).attr('allot_status'));
            });
            var allot_statu = allot_status[0];
            if (allot_statu == '2') {
                swal({
                    title: "该调拨单已入库完成,无法修改",
                    zIndex: 19891014,
                    confirmButtonText: "确定",
                    type: "warning",
                }, function () {
                });
                return false;
            }


            //获取所选择的的id
            var id = ids[0];
            layer.open({
                type: 2,
                title: '修改',
                zIndex: 100,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: 'NoticeController.do?dataParUpdate&uuid=' + id,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //获取子窗口form数据
                    var form = $(layero).find("iframe")[0].contentWindow.document.getElementById("edit_from");

                    //调用js文件的校验，把打开的窗口传给js文件，js据该窗口中的节点元素进行校验
                    var check = checkForm($(layero).find('iframe')[0].contentWindow,form);
                    if (!check) {
                        return;
                    }

                    //发送请求
                    $.ajax({
                        url: "${pageContext.request.contextPath}/NoticeController.do?updateNotice",
                        data: $(form).serialize(),
                        type: "POST",
                        dataType: "JSON",
                        error: function () {
                            alert("发生错误了");
                        },
                        success: function (data) {
                            if (data.success) {
                                swal({
                                    title: "修改成功",
                                    zIndex: 19891014,
                                    confirmButtonText: "确定",
                                    type: "success",
                                }, function () {
                                    layer.close(index);
                                    history.go(0);
                                });
                            } else {
                                swal({
                                    title: "修改失败",
                                    text: data.msg,
                                    type: "error",
                                    zIndex: 101,
                                    confirmButtonText: "确定"
                                });
                            }
                        }
                    });
                }
            });
        });


        $('#Look').on('click', function () {
            //定义一个数组，用于存放所选中的id值
            var ids = [];
            //遍历所有选中的复选框，并将id值添加到数组中
            $('#noticelist input[type=checkbox]:checked').each(function () {
                ids.push($(this).attr('values'));
            });
            //根据ids数组的长度判断所选择的条数
            if (ids.length > 1) {
                layer.alert("只能选择一条记录！");
                return false;
            } else if (ids.length == 0) {
                layer.alert("请选择一条记录！");
                return false;
            }


            //获取所选择的的id
            var id = ids[0];
            layer.open({
                type: 2,
                title: '查看',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['800px', '520px'],
                content: 'NoticeController.do?look&uuid=' + id,
                btn: ['关闭']
            });
        });


        $('#del').click(function () {
            var ids = [];
            $('#noticelist input[type=checkbox]:checked').each(function () {
                ids.push($(this).attr('values'));
            });
            //根据ids数组的长度判断所选择的条数
            if (ids.length == 0) {
                layer.alert("请选择一条记录！");
                return false;
            }

            Del();
        });

        function Del() {
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

                //定义一个数组，用于存放所选中的id值
                var ids = [];
                //遍历所有选中的复选框，并将id值添加到数组中
                $('#noticelist input[type=checkbox]:checked').each(function () {
                    ids.push($(this).attr('values'));
                });

                //获取所选择的的id
                var uuid = "";
                for (var i = 0; i < ids.length; i++) {
                    uuid = ids[i] + ",";
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/NoticeController.do?delNotice&uuid=" + uuid,
                    type: "POST",
                    success: function (data) {
                        swal("删除成功！", "您已经永久删除选中的信息。", "success");
                        swal({
                            title: "删除成功！",
                            text: "您已经永久删除了选中的信息。",
                            type: "success",
                            confirmButtonText: "确定"

                        });
                        history.go(0);
                    }
                })

            });

        }
    </script>
</form>
</body>
</html>
