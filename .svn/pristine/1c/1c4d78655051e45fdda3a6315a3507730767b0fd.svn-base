<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>列表页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
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
            <div class="alert alert-success whj_location"><span class="c6">当前位置：系统管理 &nbsp > &nbsp</span>  救灾仓库库存管理</div>
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
                                        <span class="fl labeltext">仓库级别：</span>
                                        <div class="fl">
                                            <select class="form-control w-196" name="organizationClassify" id="organizationClassify">
                                                <option  value="" >-请选择-</option>
                                                <option value="1" >中央级</option>
                                                <option value="2" >自治区级</option>
                                                <option value="3" >地州级</option>
                                                <option value="4" >县级</option>
                                            </select>
                                        </div>
                                        
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="fl labeltext">仓库名称：</span>
                                        <div class="fl">
                                            <select class="form-control w-196" name="repositoryName" id="repositoryName">
                                                <option value="" >-请选择-</option>
                                                <c:forEach items="${repAccessRepositories}" var="repAccessRepositorie">
                                                    <option value="${repAccessRepositorie.repositoryName}">${repAccessRepositorie.repositoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <button class="btn btn-primary btnh" id="btnh" type="button"><i class="fa fa-search"></i>&nbsp;搜索</button>
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
                                    <div class="btn-fl">
                                        <button type="button" class="btn" id="Lead">导出</button>
                                        <!--<button type="button" class="btn btn-outline btn-primary" id="Print">打印</button>-->
                                    </div>
                                    <table id="" class="table table-bordered" data-mobile-responsive="true">
                                        <thead>
                                            <tr class="table-title">
                                                <th>序号</th>
                                                <th>仓库名称</th>
                                                <th>物资品名</th>
                                                <th>规格型号</th>
                                                <th>物资类别</th>
                                                <th>物资单位</th>
                                                <th>单价（元）</th>
                                                <th>供应商</th>
                                                <th>购置日期</th>
                                                <th> 生产日期</th>
                                                <th>过保日期 </th>
                                                <th>总金额（元） </th>
                                                <th>入库数量 </th>
                                            </tr>
                                        </thead>
                                        <!--数据部分-->
                                        <tbody id="tab">
                                        <c:forEach items="${materialReliefFormList}" var="materialReliefForm" varStatus="s">
                                            <tr>
                                                <td>${s.index}</td>
                                                <td>${materialReliefForm.REPOSITORY_NAME}</td>
                                                <td>${materialReliefForm.SUB_NAME}</td>
                                                <td>${materialReliefForm.SPECIFICATION_TYPE}</td>
                                                <td>${materialReliefForm.CATEGORY_NAME}</td>
                                                <td>${materialReliefForm.UNIT}</td>
                                                <td>${materialReliefForm.PRICE}</td>
                                                <td>${materialReliefForm.SUPPLIER}</td>

                                                <td><fmt:formatDate value="${materialReliefForm.PURCHASE_DATE}" pattern="yyyy-MM-dd"/></td>
                                                <td><fmt:formatDate value="${materialReliefForm.MANUFACTURE_DATE}" pattern="yyyy-MM-dd"/></td>
                                                <td><fmt:formatDate value="${materialReliefForm.OVERINSURED_DATE}" pattern="yyyy-MM-dd"/></td>
                                                <td>${materialReliefForm.TOTAL_PRICES}</td>
                                                <td>${materialReliefForm.OUTPUT_AMOUNT}</td>
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
        <script>
            $('#btnh').on('click', function () {
                window.location.href="${pageContext.request.contextPath}/MaterialReliefManagement.do?materialReliefFormListLike&organizationClassify="+$("#organizationClassify option:selected").val()+"&repositoryName="+$("#repositoryName option:selected").val()+"";
            });
            ////导出
            $('#Lead').on('click', function () {
                window.location.href="${pageContext.request.contextPath}/MaterialReliefManagement.do?exportExcel";
            });
//            $('#Print').on('click', function () {
//                alert("打印成功！");
//            });
        </script>
    </form>
</body>
</html>
