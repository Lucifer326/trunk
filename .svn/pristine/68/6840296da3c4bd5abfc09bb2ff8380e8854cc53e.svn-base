<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看页面</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body class="add-dis">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <form role="form" class="form-horizontal m-t">
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资编号：</label>
                    <div class="col-sm-4">
                        <input type="text" name="" value="${repSubstanceMessage.subNumber}" readonly="readonly" class="form-control">
                    </div>
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资品名：</label>
                    <div class="col-sm-4">
                        <input type="text" name="" value="${repSubstanceMessage.subName}" readonly="readonly" class="form-control">
                    </div>
                </div>
                <div class="form-group draggable">
                    <label class="col-sm-2 control-label"><span class="start">*</span>物资类别：</label>
                    <div class="col-sm-4">
                        <input type="text" id="txt1" name="" readonly="readonly" value="${repSubstanceMessage.subCategory}" class="form-control fl" style="width: 60%">
                    </div>
               
			   <label class="col-sm-2 control-label">规格型号：</label>
                <div class="col-sm-4">
                    <input type="text" name="" value="${repSubstanceMessage.specificationType}" readonly="readonly" class="form-control">
                </div>  
        </div>
        <div class="form-group draggable">
            <label class="col-sm-2 control-label">是否可回收：</label>
            <div class="col-sm-4">
                <input type="text" name="" value="${repSubstanceMessage.isrecycle==1?'是':'否'}" readonly="readonly" class="form-control">
            </div>
             <label class="col-sm-2 control-label">供应商：</label>
                <div class="col-sm-4">
                    <input type="text" name="" readonly="readonly" value="${repSubstanceMessage.supplier}" class="form-control">
                </div>
			
        </div>
		<div class="form-group draggable">
		<label class="col-sm-2 control-label">单位：</label>
            <div class="col-sm-4">
                <input type="text" name="" readonly="readonly" value="${repSubstanceMessage.unit}" class="form-control">
            </div>
            <label class="col-sm-2 control-label">单价(元)：</label>
            <div class="col-sm-4">
                <input type="text" name="" value="${repSubstanceMessage.price}"  readonly="readonly"class="form-control">
            </div>
		</div>
        <div class="form-group draggable">
            <label class="col-sm-2 control-label">备注：</label>
            <div class="col-sm-10">
                <textarea class="form-control" readonly="readonly" rows="3">${repSubstanceMessage.remark}</textarea>
            </div>
        </div>
        </form>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
