﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .chosen-container {
            width: 100% !important;
        }

        .layer-date {
            max-width: none !important;
        }

        .table {
            border: solid 1px #ddd;
        }

        .table th {
            border: solid 1px #ddd;
        }

        .table td {
            border: solid 1px #ddd !important;
        }
    </style>
    <script type="text/javascript">
		function SelectDiv() {
			parent.layer.open({
				type: 2,
				title: '选择区划',
				maxmin: true,
				shadeClose: true, //点击遮罩关闭层
				area: ['400px', '300px'],
				content: 'materialsInputController.do?selectDiv',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var str = $(layero).find('iframe')[0].contentWindow.$('#sel').val();
					$('#area').val(str);
					parent.layer.close(index);
				}
			});
		}

		// 这个没有用
		function SelectGoods() {
			parent.layer.open({
				type: 2,
				title: '选择物资',
				maxmin: true,
				zIndex: 1000,
				shadeClose: true, //点击遮罩关闭层
				area: ['800px', '500px'],
				content: '../ProtocolReserveInfoManage/MaterialInfoManagementSelect.html',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("add");
					if (data != false) {
						var str = data.split(',');
						$("#txtName2").val(str[0]);
						//$("#txtModel").val(str[1]);
						//$("#txtType").val(str[2]);
						//$("#txtUnit").val(str[3]);
						parent.layer.close(index);
					}
				}
			});
		}

		//====================================================选择仓库
		function SelectWarehouse() {
			parent.layer.open({
				type: 2,
				title: '选择仓库',
				maxmin: true,
				zIndex: 1000,
				shadeClose: true, //点击遮罩关闭层
				area: ['800px', '500px'],
				content: 'materialsInputController.do?selectWarehouse',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("add");
					if (data != false) {
						var str = data.split(',');
						$("#txtWareHouse").val(str[0]);
						$("#storageWarehouse").val(str[1]);
						parent.layer.close(index);
					}
				}
			});
		}

		// 这个没有用
		function SelectSupplier() {
			parent.layer.open({
				type: 2,
				title: '选择供应商',
				maxmin: true,
				zIndex: 1000,
				shadeClose: true, //点击遮罩关闭层
				area: ['800px', '500px'],
				content: '../ProtocolReserveInfoManage/MaterialProducerSelect.html',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("add");
					if (data != false) {
						var str = data.split(',');
						$("#txtSupplier1").val(str);
						parent.layer.close(index);
					}
				}
			});
		}
    </script>
</head>
<body class="add-dis">
<form id="edit_form" enctype="multipart/form-data" target="_parent">
    <div class="wrapper wrapper-content animated">
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>基本信息</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="form-horizontal">
                        <div class="form-group draggable">
                            <input type="hidden" name="uuid" value="${repSubstanceStorageEntity.uuid}">
                            <label class="col-sm-2 control-label">入库单号：</label>
                            <div class="col-sm-4">
                                <label class="labelform">${repSubstanceStorageEntity.storage_number}</label>
                                <input type="hidden" name="storageNumber"
                                       value="${repSubstanceStorageEntity.storage_number}">
                            </div>
                            <label class="col-sm-2 control-label">是否定向：</label>
                            <div class="col-sm-4">
                                <div class="radio radio-inline radio-danger">
                                    <input type="radio" id="radio15" value="是"
                                           name="isorienteering" ${repSubstanceStorageEntity.isorienteering == '是' ? 'checked' : ''}>
                                    <label for="radio15">是 </label>
                                </div>
                                <div class="radio radio-inline radio-danger">
                                    <input type="radio" id="radio16" value="否"
                                           name="isorienteering" ${repSubstanceStorageEntity.isorienteering == '否' ? 'checked' : ''}>
                                    <label for="radio16">否</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">采购批次：</label>
                            <div class="col-sm-4">
                                <input type="text" name="purchaseBatch" maxlength="13" class="form-control"
                                       value="${repSubstanceStorageEntity.purchase_batch}"
                                       onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）+——|{}【】‘；：”“'。，、？ \\]/g,'');">
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>使用用途：</label>
                            <div class="col-sm-4">
                                <div class="input-group" style="width: 100%;">
                                    <select data-placeholder="" name="donationPurposes" id="donationPurposes"
                                            class="chosen-select form-control" tabindex="2"
                                            style="width: 100%">
                                        <option value="">-请选择-</option>
                                        <c:forEach items="${purpose}" var="purpose">
                                            <option value="${purpose.typecode}"
                                                    hassubinfo="true" ${repSubstanceStorageEntity.donation_purposes == purpose.typecode ? "selected":""}>
                                                    ${purpose.typename}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库仓库：</label>
                            <div class="col-sm-4">
                                <div id="Div3">
                                    <input type="text" id="txtWareHouse" name="" class="form-control fl"
                                           style="width: 70%"
                                           readonly="readonly" value="${repSubstanceStorageEntity.repository_name}"/>
                                    <input type="hidden" id="storageWarehouse" name="storageWarehouse"
                                           value="${repSubstanceStorageEntity.storage_warehouse}">
                                    <button class="btn btn-primary btnh" id="Button2" onclick="SelectWarehouse();"
                                            style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>物资来源：</label>
                            <div class="col-sm-4">
                                <div class="input-group" style="width: 100%;">

                                    <c:forEach items="${source}" var="source">
                                        <c:if test="${repSubstanceStorageEntity.substance_source == source.typecode}">
                                            <input type="text" data-placeholder="" value="${source.typename}"
                                                   class="form-control" readonly="readonly" style="width: 100%">
                                            <input type="hidden" value="${source.typecode}" name="substanceSource"
                                                   id="wuzi_resource">
                                        </c:if>
                                        <c:if test="${repSubstanceStorageEntity.substance_source == source.typecode}">
                                            <input type="hidden" id="str" value="${source.typename}"/>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>来源对象：</label>
                            <div class="col-sm-4">
                                <input type="text" name="sourceObject" class="form-control" id="txt1" maxlength="13"
                                       value="${repSubstanceStorageEntity.source_object}"
                                       onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）+——|{}【】‘；：”“'。，、？ \\]/g,'');">
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库人：</label>
                            <div class="col-sm-4">
                                <input type="text" name="storagePerson" class="form-control" id="storagePerson"
                                       maxlength="13"
                                       value="${repSubstanceStorageEntity.storage_person}"
                                       onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）+——|{}【】‘；：”“'。，、？ \\]/g,'');">
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label"><span class="start">*</span>入库日期：</label>
                            <div class="col-sm-4">
                                <input id="hello" name="storageDate" class="laydate-icon form-control layer-date"
                                       value="<fmt:formatDate value="${repSubstanceStorageEntity.storage_date}" pattern="yyyy-MM-dd"/>">
                            </div>
                            <label class="col-sm-2 control-label"><span class="start">*</span>使用区域：</label>
                            <div class="col-sm-4">
                                <div id="Div1">
                                    <input type="text" id="area" name="donateArea" class="form-control fl"
                                           style="width: 70%"
                                           value="${repSubstanceStorageEntity.donate_area}" readonly="readonly">
                                    <button class="btn btn-primary btnh" id="btn" onclick="SelectDiv();"
                                            style="width: 30%"
                                            type="button"><i class="fa fa-search"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">检查报告上传：</label>
                            <div class="col-sm-10">
                                <div id="file-pretty">
                                    <input type="file" class="form-control" name="file" id="file"
                                           accept=".xls,.doc,.txt,.pdf">
                                </div>
                            </div>

                        </div>
                        <div class="form-group draggable">
                            <label class="col-sm-2 control-label">备注：</label>
                            <div class="col-sm-10">
								<textarea class="form-control" name="remark" id="remark" maxlength="250"
                                          rows="3">${repSubstanceStorageEntity.remark}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ibox float-e-margins">
            <div class="ibox-title whj-see-ibox">
                <h5>物资信息</h5>
            </div>
            <div class="ibox-content">
                <div class="btn-fl">
                    <button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
                    <button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
                    <button type="button" class="btn btn-outline btn-success" id="look">查看</button>
                </div>
                <div style="width: 100%; float: left; margin-top: 20px; overflow: auto;">
                    <table cellpadding="0" cellspacing="0" id="mytab" class="crease table table-border tablemax">
                        <thead>
                        <tr>
                            <th>序列</th>
                            <th>物资品名</th>
                            <th>规格型号</th>
                            <th>物资类别</th>
                            <th>物资单位</th>
                            <th>入库数量</th>
                            <th>单价（元）</th>
                            <th>供应商</th>
                            <th>购置日期</th>
                            <th>生产日期</th>
                            <th>过保日期</th>
                            <th>总金额（元）</th>
                        </tr>
                        </thead>
                        <!--数据部分-->
                        <tbody id="tab">
                        <%--物资入库详细uuid,用于删除,点击删除后,将被选中复选框中的值追加到这个value中,uuid之间用'-'分隔--%>
                        <input type="hidden" id="del_uuid" name="del_uuid" value="">
                        <c:forEach items="${list}" var="row">
                            <tr>
                                <td>
                                        <%--物资入库详细uuid,用于查看--%>
                                    <input type="checkbox" name="check_list" values="${row.uuid}"/>
                                        <%--物资入库详细uuid,用于修改--%>
                                    <input type="hidden" name="minuteid" value="${row.uuid}">
                                        <%--物资信息uuid--%>
                                    <input type="hidden" name="messageid" value="${row.messageid}">
                                </td>
                                <td>
                                        <%--物资品名(不传到后台)--%>
                                    <input type="text" id="txtName" name="sub_name" class="form-control fl"
                                           readonly="readonly" value="${row.sub_name}"/>
                                    <!--<button class="btn btn-primary btnh" id="Button1" onclick="SelectGoods();" style="width: 35%" type="button"><i class="fa fa-search"></i>&nbsp;选择</button>-->
                                </td>
                                <td>
                                        <%--物资型号(不传到后台)--%>
                                    <input type="text" id="txtUnit" name="" class="form-control"
                                           readonly value="${row.specification_type}"/>
                                </td>
                                <td>
                                        <%--物资类别(不传到后台)--%>
                                    <input type="text" id="txtModel" name="" class="form-control"
                                           readonly value="${row.category_name}"/>
                                </td>
                                <td>
                                        <%--单位(不传到后台)--%>
                                    <input type="text" id="txtType" name="" class="form-control" readonly
                                           value="${row.unit}"/>
                                </td>
                                <td>
                                        <%--入库数量--%>
                                    <input type="number" min="1" maxlength="36" name="outputAmount" id="outputAmount"
                                           class="form-control"
                                           onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ \\]/g,'');"
                                           value="${row.output_Amount}"/>
                                        <%--库存量,用来判断是否已出库--%>
                                    <input type="hidden" value="${row.amount}">
                                </td>
                                <td>
                                        <%--单价(需要用于计算)--%>
                                    <input type="text" id="txtSupplier1" name="price" class="form-control fl"
                                           readonly value="${row.price}"/>
                                </td>
                                <td>
                                        <%--供应商(不传到后台)--%>
                                    <input type="text" id="txtSupplier1" name="" class="form-control fl"
                                           readonly value="${row.supplier}"/>
                                </td>
                                <td>
                                        <%--购置日期--%>
                                    <input id="helloa" class="laydate-icon form-control layer-date"
                                           name="purchaseDate"
                                           value="<fmt:formatDate value="${row.purchase_Date}" pattern="yyyy-MM-dd"/>">
                                        <%--<fmt:formatDate value="${row.purchase_Date}" pattern="yyyy-MM-dd"/>--%>
                                </td>
                                <td>
                                        <%--生产日期--%>
                                    <input id="hellob" class="laydate-icon form-control layer-date"
                                           name="manufactureDate"
                                           value="<fmt:formatDate value="${row.manufacture_Date}" pattern="yyyy-MM-dd"/>">
                                </td>
                                <td>
                                        <%--过保日期--%>
                                    <input id="helloc" class="laydate-icon form-control layer-date"
                                           name="overinsuredDate"
                                           value="<fmt:formatDate value="${row.overinsured_Date}" pattern="yyyy-MM-dd"/>">
                                </td>
                                <td>
                                        <%--总价(不传到后台)--%>
                                    <input readonly type="text" class="form-control" name="totalPrices" placeholder=""
                                           value="${row.total_prices}"/>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js"></script>
<!--时间-->
<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<!--编辑器-->
<script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
<!-- 上传 -->
<script src="${pageContext.request.contextPath}/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
<!-- 下拉列表 -->
<script src="${pageContext.request.contextPath}/js/plugins/chosen/chosen.jquery.js"></script>
<!--固定格式-->
<script src="${pageContext.request.contextPath}/js/plugins/jasny/jasny-bootstrap.min.js"></script>


<!-- Peity -->
<script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<script>
	//外部js调用
	laydate({
		elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
		elem: '#helloa', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
		elem: '#hellob', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
		elem: '#helloc', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	$(document).ready(function () {

		$('.summernote').summernote({
			lang: 'zh-CN'
		});

	});

	//============================================================查看
	$('#look').on('click', function () {
		//定义一个数组，用于存放所选中的id值
		var ids = [];
		//遍历所有选中的复选框，并将id值添加到数组中
		$('#tab input[type=checkbox]:checked').each(function () {
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

		// var id = $('#table_content input[type=checkbox]:checked').attr('values');

		//获取所选择的的id
		var id = ids[0];
		parent.layer.open({
			type: 2,
			title: '查看物资信息',
			maxmin: true,
			zIndex: 1000,
			shadeClose: true, //点击遮罩关闭层
			area: ['800px', '500px'],
			content: 'materialsInputController.do?inStockAddLook&uuid=' + id,
			btn: ['关闭']
		});
	});

	var i = 0;
	//==============================================选择物资
	$('#Add').on('click', function () {
		var tabObj = document.getElementById("mytab");//获取添加数据的表格
		var bodyObj = document.getElementById("tab");//获取添加数据的表格
		var rowsNum = tabObj.rows.length;
        
		var selUuids = "";
		$.each($("input[name=messageid]"), function () {
			selUuids += this.value + "','";
		});
		
		parent.layer.open({
			type: 2,
			title: '选择物资',
			maxmin: true,
			zIndex: 1000,
			shadeClose: true, //点击遮罩关闭层
			area: ['800px', '500px'],
			content: 'materialsInputController.do?searchRepSubstanceMessage&selUuids=' + selUuids,
			btn: ['确定', '取消'],
			yes: function (index, layero) {

				var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("add");
				if (data != false) {
					var str = data.split(',');
					parent.layer.close(index);

					i++;

					var rowsNum2 = bodyObj.rows.length;
					var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
					var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
					var newTdObj1 = myNewRow.insertCell(0);       //序列
					newTdObj1.innerHTML = "<input type='checkbox' name='check_list'  id='check_list'" + rowsNum + " style='border:0px' /> ";
					var newTdObj22 = myNewRow.insertCell(1);     // 物资品名
					newTdObj22.innerHTML = "<input readonly type='text' value='" + str[0] + "' name='sub_name' class='form-control' />";
					var newTdObj2 = myNewRow.insertCell(2);      //规格型号
					newTdObj2.innerHTML = "<input readonly type='text' name='' value='" + str[1] + "' class='form-control' />";
					var newTdObj3 = myNewRow.insertCell(3);     //物资类别
					newTdObj3.innerHTML = "<input readonly type='text' name='' value='" + str[2] + "' class='form-control' />";
					var newTdObj4 = myNewRow.insertCell(4);     //物资单位
					newTdObj4.innerHTML = "<input readonly type='text' name='' value='" + str[3] + "' class='form-control' />";//<input id='hi" + rowsNum + "' class='laydate-icon form-control layer-date fl w-148'>
					var newTdObj5 = myNewRow.insertCell(5);     //入库数量
					newTdObj5.innerHTML = "<input type='number' name='outputAmount' maxlength='36' min='1' class='form-control'  placeholder='' onkeyup=\"this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？\\ ]/g,'');\" />";
					var newTdObj6 = myNewRow.insertCell(6);     //单价
					newTdObj6.innerHTML = "<input readonly type='text' value='" + str[7] + "' name='price' class='form-control fl' />";
					// + "<button class='btn btn-primary btnh' id='Button3' onclick='SelectSupplier();' style='width: 35%' type='button'><i class='fa fa-search'></i>&nbsp;选择</button>";


					var newTdObj7 = myNewRow.insertCell(7);     //供应商
					newTdObj7.innerHTML = "<input readonly type='text' name='' value='" + str[6] + "' class='form-control' />";
					var newTdObj8 = myNewRow.insertCell(8);     //购置日期
					newTdObj8.innerHTML = "<input id='helloa" + rowsNum + "' name='purchaseDate' class='laydate-icon form-control layer-date'>";
					var newTdObj9 = myNewRow.insertCell(9);     //生产日期
					newTdObj9.innerHTML = "<input id='hellob" + rowsNum + "' name='manufactureDate' class='laydate-icon form-control layer-date'>";
					var newTdObj10 = myNewRow.insertCell(10);   //过保日期 
					newTdObj10.innerHTML = "<input id='helloc" + rowsNum + "' name='overinsuredDate' class='laydate-icon form-control layer-date'>";
					var newTdObj11 = myNewRow.insertCell(11);   //总金额
					newTdObj11.innerHTML = "<input readonly type='text' name='totalPrices' class='form-control' placeholder='' />";
					var newTdObj12 = myNewRow.insertCell(12);   //物资信息uuid
					newTdObj12.style.display = "none";
					newTdObj12.innerHTML = "<input type='hidden' name='messageid' value='" + str[4] + "' class='form-control' placeholder='' />";
					// var newTdObj13 = myNewRow.insertCell(13);   //物资入库详细uuid
					// newTdObj13.innerHTML = "<input type='hidden' name='minuteid' value='" + str[] + "' class='form-control' placeholder='' />";

					$("input[name=outputAmount]").change(function () {
						var num = parseFloat($(this).val()) * parseFloat($(this).parent().parent().find("input[name=price]").val());
						$(this).parent().parent().find("input[name=totalPrices]").val(num);
					});

					setTimeout(function () {
						laydate({
							elem: "#helloa" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
							event: 'focus' //响应事件。如果没有传入event，则按照默认的click
						}, 1000);
						laydate({
							elem: "#hellob" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
							event: 'focus' //响应事件。如果没有传入event，则按照默认的click
						}, 1000);
						laydate({
							elem: "#helloc" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
							event: 'focus' //响应事件。如果没有传入event，则按照默认的click
						}, 1000);
					});
				}
			}
		});


	});
	$("input[name=outputAmount]").change(function () {
		var num1 = parseFloat($(this).val()) * parseFloat($(this).parent().parent().find("input[name=price]").val());
		$(this).parent().parent().find("input[name=totalPrices]").val(num1);
	});
	//==========================================点击删除
	$('#del').click(function () {


		//定义一个数组，用于存放所选中的id值
		var ids = [];
		//遍历所有选中的复选框，并将id值添加到数组中
		$('#tab input[type=checkbox]:checked').each(function () {
			ids.push($(this).attr('values'));
		});

		var uuids = $("#del_uuid").val();
		for (var i = 0; i < ids.length; i++) {
			if (ids[i] != undefined) {
				uuids += ids[i] + "-";
			}
		}
		$("#del_uuid").val(uuids);

		var chkObj = document.getElementsByName("check_list");
		var tabObj = document.getElementById("mytab");
		for (var k = 0; k < chkObj.length; k++) {
			if (chkObj[k].checked) {
				tabObj.deleteRow(k + 1);
				k = -1;
			}
		}
	});
	$('input[type="file"]').prettyFile();
	$(".chosen-select").chosen();

	$(function () {
		var str = $("#str").val();
		if (str == "调拨") {
			$("#Add").hide();
			$("#del").hide();
			$("input[name=outputAmount]").attr("readonly", "readonly");
			$("#txt1").attr("readonly", "readonly");
		}
	});

	$(function () {
		$.each($("#tab").find('tr'), function () {
			var outputAmount = $(this).find("input[name=outputAmount]");
			var amount = outputAmount.next();
			if (outputAmount.val() != amount.val()) {
				$(this).find("input[name=outputAmount]").attr("readonly", "readonly");
				$(this).find("input[name=purchaseDate]").attr("readonly", "readonly");
				$(this).find("input[name=manufactureDate]").attr("readonly", "readonly");
				$(this).find("input[name=overinsuredDate]").attr("readonly", "readonly");
				$(this).find("input[type=checkbox]").remove();
			}
		})
	});
</script>
</body>
</html>
