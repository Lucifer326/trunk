<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"></head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-2">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <%--存放选中的结点名称--%>
                        <input type="text" style="display: none;" id="sel" value="">
                        <div id="jstree1">
                            <ul>
                                <li class="jstree-open">新疆维吾尔自治区
                                    <ul>
                                        <li>乌鲁木齐市
                                            <ul>
                                                <li data-jstree='{"type":"css"}' id="test" value="天山区">天山区</li>
                                                <li data-jstree='{"type":"css"}'>达坂城区</li>
                                                <li data-jstree='{"type":"css"}'>乌鲁木齐县</li>
                                                <li data-jstree='{"type":"css"}'>新市区</li>
                                                <li data-jstree='{"type":"css"}'>天山区</li>
                                                <li data-jstree='{"type":"css"}'>东山区</li>
                                            </ul>
                                        </li>
                                        <li>吐鲁番地区
                                            <ul>
                                                <li data-jstree='{"type":"css"}'>吐鲁番市</li>
                                                <li data-jstree='{"type":"css"}'>鄯善县</li>
                                                <li data-jstree='{"type":"css"}'>托克逊县</li>
                                            </ul>
                                        </li>
                                        <li>哈密地区
                                            <ul>
                                                <li data-jstree='{"type":"css"}'>哈密市</li>
                                                <li data-jstree='{"type":"css"}'>巴里坤哈萨克自治县</li>
                                                <li data-jstree='{"type":"css"}'>伊吾县</li>
                                            </ul>
                                        </li>
                                        <li>阿克苏地区
                                            <ul>
                                                <li data-jstree='{"type":"css"}'>阿克苏市</li>
                                                <li data-jstree='{"type":"css"}'>温宿县</li>
                                                <li data-jstree='{"type":"css"}'>库车县</li>
                                                <li data-jstree='{"type":"css"}'>沙雅县</li>
                                            </ul>
                                        </li>
                                        <li>克拉玛依市
                                            <ul>
                                                <li data-jstree='{"type":"css"}'>独山子区</li>
                                                <li data-jstree='{"type":"css"}'>克拉玛依区</li>
                                                <li data-jstree='{"type":"css"}'>白碱滩区</li>
                                                <li data-jstree='{"type":"css"}'>乌尔禾区</li>
                                            </ul>
                                        </li>
                                        <li>昌吉回族自治州
                                            <ul>
                                                <li data-jstree='{"type":"css"}'>昌吉市</li>
                                                <li data-jstree='{"type":"css"}'>阜康市</li>
                                                <li data-jstree='{"type":"css"}'>呼图壁县</li>
                                                <li data-jstree='{"type":"css"}'>玛纳斯县</li>
                                                <li data-jstree='{"type":"css"}'>奇台县</li>
                                                <li data-jstree='{"type":"css"}'>吉木萨尔县</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--总体-->
    </div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 自定义js -->

<!-- Bootstrap table -->
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<!-- Peity -->
<script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
<!--时间控件-->
<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<!--树-->
<script src="${pageContext.request.contextPath}/js/plugins/jsTree/jstree.min.js"></script>
<!--弹层-->
<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
<!--删除-->
<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<script>
    $(document).ready(function () {

        $('#jstree1').jstree({
            'core': {
                'check_callback': true
            },
            'plugins': ['types', 'dnd'],
            'types': {
                'default': {
                    'icon': 'fa fa-folder'
                },
                'html': {
                    'icon': 'fa fa-file-code-o'
                },
                'svg': {
                    'icon': 'fa fa-file-picture-o'
                },
                'css': {
                    'icon': 'fa fa-file-code-o'
                },
                'img': {
                    'icon': 'fa fa-file-image-o'
                },
                'js': {
                    'icon': 'fa fa-file-text-o'
                }
            }
        });

        $('#jstree1').bind("activate_node.jstree", function (obj, e) {
            // 获取当前节点
            var currentNode = e.node;
            $('#sel').attr('value',currentNode.text);
        });
    });

</script>
</body>
</html>
