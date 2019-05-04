﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="url" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="${url}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${url}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${url}/css/animate.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
    <link href="${url}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${url}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${url}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${url}/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="${url}/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${url}/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal m-t">
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">公司名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" value="${productById.name}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">公司简介：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="3" readonly="readonly">${productById.companyInfo}</textarea>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">是否参加过政府招标：</label>
                    <div class="col-sm-10">
                        <c:forEach items="${proIsNot}" var="pn">
                            <c:if test="${productById.tender eq pn.typecode}">
                                <input type="text" name="" class="form-control" value="${pn.typename}" readonly="readonly">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
				 <div class="form-group draggable">
                    <label class="col-sm-2 control-label">是否录入民政部《自然灾害应急救助物资生产商参考名录》：</label>
                    <div class="col-sm-10">
                          <c:forEach items="${proIsNot}" var="pn">
                              <c:if test="${productById.inPut eq pn.typecode}">
                                  <input type="text" name="" class="form-control" value="${pn.typename}" readonly="readonly">
                              </c:if>
                          </c:forEach>
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">主营产品：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" value="${productById.product}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">联系人：</label>
                    <div class="col-sm-4">
                        <input type="text" name="" class="form-control" value="${productById.contact}" readonly="readonly">
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>联系电话：</label>
                    <div class="col-sm-4">
                        <input type="text" name="" class="form-control" value="${productById.phone}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">邮箱：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" value="${productById.email}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">网址：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" value="${productById.url}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">地址：</label>
                    <div class="col-sm-10">
                        <input type="text" name="" class="form-control" value="${productById.address}" readonly="readonly">
                    </div>
                </div>
				
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label">备注：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="3" readonly="readonly">${productById.remark}</textarea>
                    </div>
                </div>
            </form>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${url}/js/jquery.min.js"></script>
    <script src="${url}/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${url}/js/content.js"></script>
    <!--时间-->
    <script src="${url}/js/plugins/layer/laydate/laydate.js"></script>
    <!--编辑器-->
    <script src="${url}/js/plugins/summernote/summernote.min.js"></script>
    <script src="${url}/js/plugins/summernote/summernote-zh-CN.js"></script>
    <!-- 上传 -->
    <script src="${url}/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
    <!-- 下拉列表 -->
    <script src="${url}/js/plugins/chosen/chosen.jquery.js"></script>
    <!--固定格式-->
    <script src="${url}/js/plugins/jasny/jasny-bootstrap.min.js"></script>

    <script>
        //外部js调用
        laydate({
            elem: '#Text1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        $(document).ready(function () {

            $('.summernote').summernote({
                lang: 'zh-CN'
            });

        });
        $('input[type="file"]').prettyFile();
        $(".chosen-select").chosen();

    </script>

</body>
</html>
