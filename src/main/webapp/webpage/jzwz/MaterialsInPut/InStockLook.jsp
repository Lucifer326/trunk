<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
			layer.open({
				type: 2,
				title: '选择区划',
				maxmin: true,
				shadeClose: true, //点击遮罩关闭层
				area: ['400px', '300px'],
				content: '../CommonPage/SelectDiv.html',
				btn: ['确定', '取消']
			});
		}

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
						$("#txtName").val(str[0]);
						//$("#txtModel").val(str[1]);
						//$("#txtType").val(str[2]);
						//$("#txtUnit").val(str[3]);
						parent.layer.close(index);
					}
				}
			});
		}

		function SelectWarehouse() {
			parent.layer.open({
				type: 2,
				title: '选择仓库',
				maxmin: true,
				zIndex: 1000,
				shadeClose: true, //点击遮罩关闭层
				area: ['800px', '500px'],
				content: 'SelectWarehouse.html',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var data = $(layero).find("iframe")[0].contentWindow.CheckSelect("add");
					if (data != false) {
						var str = data.split(',');
						$("#txtWareHouse").val(str);
						parent.layer.close(index);
					}
				}
			});
		}

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
						<label class="col-sm-2 control-label">入库单号：</label>
						<div class="col-sm-4">
							<label class="labelform">${repSubstanceStorageEntity.storage_number}</label>
						</div>
						<label class="col-sm-2 control-label">是否定向：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">${repSubstanceStorageEntity.isorienteering == "是" ? "是" : "否"}</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">采购批次：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">${repSubstanceStorageEntity.purchase_batch}</div>
						</div>
						<label class="col-sm-2 control-label">捐赠用途：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">
								<c:forEach items="${purpose}" var="purpose">
									<c:if test="${repSubstanceStorageEntity.donation_purposes == purpose.typecode}">${purpose.typename}</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">入库仓库：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">${repSubstanceStorageEntity.repository_name}</div>
						</div>
						<label class="col-sm-2 control-label">物资来源：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">
								<c:forEach items="${source}" var="source">
									<c:if test="${repSubstanceStorageEntity.substance_source == source.typecode}">${source.typename}</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">来源对象：</label>
						<div class="col-sm-4">
							<%--<input type="text" name="" class="form-control">--%>
							<div class="form-control"
							     readonly="readonly">${repSubstanceStorageEntity.source_object}</div>
						</div>
						<label class="col-sm-2 control-label">入库人：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly">${repSubstanceStorageEntity.storage_person}</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">入库日期：</label>
						<div class="col-sm-4">
							<div class="form-control"
							     readonly="readonly"><fmt:formatDate value="${repSubstanceStorageEntity.storage_date}" pattern="yyyy-MM-dd"/> </div>
						</div>
						<label class="col-sm-2 control-label">捐赠区域：</label>
						<div class="col-sm-4">
							<div class="form-control" readonly="readonly">${repSubstanceStorageEntity.donate_area}</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">检查报告：</label>
						<div class="col-sm-10">
							<div class="form-control"><a href="${pageContext.request.contextPath}/materialsInputController.do?fileDown&uuid=${repSubstanceStorageEntity.FILEID}">${repSubstanceStorageEntity.ACCESSORY_NAME}</a></div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">备注：</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="3"
							          readonly="readonly">${repSubstanceStorageEntity.remark}</textarea>
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
				<!--<button type="button" class="btn btn-outline btn-primary" id="Add">新建</button>
				<button type="button" class="btn btn-outline btn-danger" id="del">删除</button>
				<button type="button" class="btn btn-outline btn-success" id="look">查看</button>-->
			</div>
			<div style="width: 100%; float: left; margin-top: 20px;overflow:auto;">
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
					<c:forEach items="${list}" var="row" varStatus="i">
						<tr>
							<td>
								<div class="form-control" readonly="readonly">${i.index+1}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.sub_name}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.specification_type}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.category_name}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.unit}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.output_Amount}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.price}</div>
							</td>

							<td>
								<div class="form-control" readonly="readonly">${row.supplier}</div>
							</td>
							<td>
								<div class="form-control" readonly="readonly"><fmt:formatDate value="${row.purchase_Date}" pattern="yyyy-MM-dd"/> </div>
							</td>
							<td>
								<div class="form-control" readonly="readonly"><fmt:formatDate value="${row.manufacture_Date}" pattern="yyyy-MM-dd"/></div>
							</td>
							<td>
								<div class="form-control" readonly="readonly"><fmt:formatDate value="${row.overinsured_Date}" pattern="yyyy-MM-dd"/></div>
							</td>
							<td>
								<div class="form-control" readonly="readonly">${row.total_prices}</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

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
	console.log(${list});
	//外部js调用
	laydate({
		elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	//laydate({
	//    elem: '#helloa', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	//    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	//});
	//laydate({
	//    elem: '#hellob', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	//    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	//});
	//laydate({
	//    elem: '#helloc', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	//    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	//});
	$(document).ready(function () {

		$('.summernote').summernote({
			lang: 'zh-CN'
		});

	});
	$('#look').on('click', function () {
		parent.layer.open({
			type: 2,
			title: '查看物资信息',
			maxmin: true,
			zIndex: 1000,
			shadeClose: true, //点击遮罩关闭层
			area: ['800px', '500px'],
			content: 'materialsInputController.do?inStockAddLook',
			btn: ['关闭']
		});
	});

	var i = 0;
	$('#Add').on('click', function () {
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
					$("#txtName1").val(str[0]);
					//$("#txtModel").val(str[1]);
					//$("#txtType").val(str[2]);
					//$("#txtUnit").val(str[3]);
					parent.layer.close(index);
				}
			}
		});

		i++;
		var tabObj = document.getElementById("mytab");//获取添加数据的表格
		var bodyObj = document.getElementById("tab");//获取添加数据的表格
		var rowsNum = tabObj.rows.length;
		var rowsNum2 = bodyObj.rows.length;
		var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
		var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
		var newTdObj1 = myNewRow.insertCell(0);
		newTdObj1.innerHTML = "<input type='checkbox' name='check_list' id='check_list'" + rowsNum + " style='border:0px' /> ";
		var newTdObj22 = myNewRow.insertCell(1);
		newTdObj22.innerHTML = "<input readonly='readonly' type='text' id='txtName" + rowsNum + "' name='' class='form-control' />";
		var newTdObj2 = myNewRow.insertCell(2);
		newTdObj2.innerHTML = "<input type='text' name='' class='form-control' />";
		var newTdObj3 = myNewRow.insertCell(3);
		newTdObj3.innerHTML = "<input type='text' name='' class='form-control' />";
		var newTdObj4 = myNewRow.insertCell(4);
		newTdObj4.innerHTML = "<input type='text' name='' class='form-control' />";//<input id='hi" + rowsNum + "' class='laydate-icon form-control layer-date fl w-148'>
		var newTdObj5 = myNewRow.insertCell(5);
		newTdObj5.innerHTML = "<input type='text' class='form-control'  placeholder='' />";
		var newTdObj6 = myNewRow.insertCell(6);
		newTdObj6.innerHTML = "<input type='text' id='txtSupplier" + rowsNum + "' name='' class='form-control fl' style='width: 65%' readonly='readonly' />"
			+ "<button class='btn btn-primary btnh' id='Button3' onclick='SelectSupplier();' style='width: 35%' type='button'><i class='fa fa-search'></i>&nbsp;选择</button>";
		var newTdObj7 = myNewRow.insertCell(7);
		newTdObj7.innerHTML = "<input id='helloa" + rowsNum + "' class='laydate-icon form-control layer-date'>";
		var newTdObj8 = myNewRow.insertCell(8);
		newTdObj8.innerHTML = "<input id='hellob" + rowsNum + "' class='laydate-icon form-control layer-date'>";
		var newTdObj9 = myNewRow.insertCell(9);
		newTdObj9.innerHTML = "<input id='helloc" + rowsNum + "' class='laydate-icon form-control layer-date'>";
		var newTdObj10 = myNewRow.insertCell(10);
		newTdObj10.innerHTML = "<input type='text' class='form-control' placeholder='' />";
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
	});


	$('#del').click(function () {
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
</script>
</body>
</html>
