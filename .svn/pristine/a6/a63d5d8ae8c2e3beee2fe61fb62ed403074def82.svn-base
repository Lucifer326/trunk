<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/chosen/chosen.css" rel="stylesheet">
</head>
<body class="add-dis">
<div class="wrapper wrapper-content animated">
    <div class="row">
        <form role="form" class="form-horizontal m-t" id="forms">
            <input type="hidden" name="wareHouse" value="${wareHouse}">
            <div class="form-group draggable">
                <label class="col-sm-2 control-label"><span class="start">*</span>审批状态：</label>
                <div class="col-sm-4">
                    <select name="approvalStatus" data-placeholder="" class="chosen-select form-control" tabindex="2" style="width: 100%">
                        <option value="0">-请选择-</option>
                        <c:forEach items="${approval_status}" var="s">
                            <option value="${s.typecode}">${s.typename}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-2 control-label">审批人：</label>
                <div class="col-sm-4">
                    <div class="form-control">${sessionScope.LOCAL_CLINET_USER.realName}</div>
                </div>
            </div>
            <div class="form-group draggable">
                <label class="col-sm-2 control-label">审批意见：</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="3" name="approvalOpinion"></textarea>
                </div>
            </div>
        </form>
    </div>

</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 下拉列表 -->
<script src="${pageContext.request.contextPath}/js/plugins/chosen/chosen.jquery.js"></script>

<script>
    $(".chosen-select").chosen();
</script>

</body>
</html>
