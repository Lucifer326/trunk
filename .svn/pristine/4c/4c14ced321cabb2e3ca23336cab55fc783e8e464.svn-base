<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<link href="${pageContext.request.contextPath}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
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
						$("#txtModel").val(str[1]);
						$("#txtType").val(str[2]);
						$("#txtUnit").val(str[3]);
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
						$("#txtSupplier").val(str);
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
							<label class="labelform">ZYHD201612161541</label>
						</div>
						<label class="col-sm-2 control-label">是否定向：</label>
						<div class="col-sm-4">
							<div class="radio radio-inline radio-danger">
								<input type="radio" id="radio15" value="option1" name="radio4" checked="">
								<label for="radio1">是 </label>
							</div>
							<div class="radio radio-inline radio-danger">
								<input type="radio" id="radio16" value="option2" name="radio4">
								<label for="radio2">否</label>
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">采购批次：</label>
						<div class="col-sm-4">
							<input type="text" name="" class="form-control">
						</div>
						<label class="col-sm-2 control-label">捐赠用途：</label>
						<div class="col-sm-4">
							<div class="input-group" style="width: 100%;">
								<select data-placeholder="" class="chosen-select form-control" tabindex="2"
								        style="width: 100%">
									<option value="">-请选择-</option>
									<option value="110000" hassubinfo="true">灾难应急食物救助</option>
									<option value="120000" hassubinfo="true">倒塌、损坏住房恢复重建</option>
									<option value="130000" hassubinfo="true">冬春临时生活困难救助</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label"><span class="start">*</span>入库仓库：</label>
						<div class="col-sm-4">
							<div id="Div3">
								<input type="text" id="txtWareHouse" name="" class="form-control fl" style="width: 70%"
								       readonly="readonly"/>
								<button class="btn btn-primary btnh" id="Button2" onclick="SelectWarehouse();"
								        style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择
								</button>
							</div>
						</div>
						<label class="col-sm-2 control-label"><span class="start">*</span>物资来源：</label>
						<div class="col-sm-4">
							<div class="input-group" style="width: 100%;">
								<select data-placeholder="" class="chosen-select form-control" tabindex="2"
								        style="width: 100%">
									<option value="">-请选择-</option>
									<option value="110000" hassubinfo="true">采购</option>
									<option value="120000" hassubinfo="true">捐赠</option>
									<option value="130000" hassubinfo="true">调拨</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label"><span class="start">*</span>来源对象：</label>
						<div class="col-sm-4">
							<input type="text" name="" class="form-control">
						</div>
						<label class="col-sm-2 control-label"><span class="start">*</span>入库人：</label>
						<div class="col-sm-4">
							<input type="text" name="" class="form-control">
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label"><span class="start">*</span>入库日期：</label>
						<div class="col-sm-4">
							<input id="hello3" class="laydate-icon form-control layer-date">
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">检查报告上传：</label>
						<div class="col-sm-10">
							<div id="file-pretty">
								<input type="file" multiple="multiple" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label">备注：</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="3"></textarea>
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
			</div>
			<div style="width: 100%; float: left; margin-top: 20px;">
				<table cellpadding="0" cellspacing="0" id="mytab" class="crease table table-border">
					<thead>
					<tr>
						<th style="text-align: center; width: 7%">序列</th>
						<th style="text-align: center;">物资信息</th>
					</tr>
					</thead>
					<tbody id="tab">
					<tr>
						<td>
							<input type="checkbox" name="check_list" disabled="disabled"/>
						</td>
						<td>
							<div>
								<form role="form" class="form-horizontal m-t">
									<div class="form-group draggable">
										<label class="col-sm-2 control-label"><span class="start">*</span>物资品名：</label>
										<div class="col-sm-4">
											<div id="Div1">
												<input type="text" id="txtName" name="" class="form-control fl"
												       style="width: 65%" readonly="readonly"/>
												<button class="btn btn-primary btnh" id="btn" onclick="SelectGoods();"
												        style="width: 35%" type="button"><i class="fa fa-search"></i>&nbsp;选择
												</button>
											</div>
										</div>
										<label class="col-sm-2 control-label">计量单位：</label>
										<div class="col-sm-4">
											<input type="text" id="txtUnit" name="" class="form-control"
											       readonly="readonly"/>
										</div>
									</div>
									<div class="form-group draggable">
										<label class="col-sm-2 control-label">规格型号：</label>
										<div class="col-sm-4">
											<input type="text" id="txtModel" name="" class="form-control"
											       readonly="readonly"/>
										</div>
										<label class="col-sm-2 control-label"><span class="start">*</span>物资类别：</label>
										<div class="col-sm-4">
											<input type="text" id="txtType" name="" class="form-control"
											       readonly="readonly"/>
										</div>
									</div>
									<div class="form-group draggable">
										<label class="col-sm-2 control-label"><span class="start">*</span>入库数量：</label>
										<div class="col-sm-4">
											<input type="number" name="" class="form-control"/>
										</div>
										<label class="col-sm-2 control-label">捐赠区域：</label>
										<div class="col-sm-4">
											<div id="Div2">
												<input type="text" id="Text1" name="" class="form-control fl"
												       style="width: 65%" readonly="readonly"/>
												<button class="btn btn-primary btnh" id="Button1" onclick="SelectDiv();"
												        style="width: 35%" type="button"><i class="fa fa-search"></i>&nbsp;选择
												</button>
											</div>
										</div>
									</div>
									<div class="form-group draggable">
										<label class="col-sm-2 control-label">单价（元）：</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" placeholder=""/>
										</div>
										<label class="col-sm-2 control-label">供应商：</label>
										<div class="col-sm-4">
											<div id="Div4">
												<input type="text" id="txtSupplier" name="" class="form-control fl"
												       style="width: 65%" readonly="readonly"/>
												<button class="btn btn-primary btnh" id="Button3"
												        onclick="SelectSupplier();" style="width: 35%" type="button"><i
														class="fa fa-search"></i>&nbsp;选择
												</button>
											</div>
										</div>
									</div>
									<div class="form-group draggable">
										<label class="col-sm-2 control-label">购置日期：</label>
										<div class="col-sm-4">
											<input id="hello" class="laydate-icon form-control layer-date">
										</div>
										<label class="col-sm-2 control-label">生产日期：</label>
										<div class="col-sm-4">
											<input id="hello1" class="laydate-icon form-control layer-date">
										</div>
									</div>
									<div class="form-group draggable">
										<label class="col-sm-2 control-label">过保日期：</label>
										<div class="col-sm-4">
											<input id="hello2" class="laydate-icon form-control layer-date">
										</div>
										<label class="col-sm-2 control-label">总金额（元）：</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" placeholder=""/>
										</div>
									</div>
								</form>
							</div>
						</td>
					</tr>
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

<script>
	//外部js调用
	laydate({
		elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	$(document).ready(function () {

		$('.summernote').summernote({
			lang: 'zh-CN'
		});

	});
	//选择出库仓库
	$('#btn').on('click', function () {
		parent.layer.open({
			type: 2,
			title: '出库仓库',
			maxmin: true,
			shadeClose: true, //点击遮罩关闭层
			area: ['800px', '500px'],
			zIndex: 1200,
			content: 'WareHouseSelect.html',
			btn: ['确定', '取消'],
			yes: function (index, layero) {
				$("#txt").val("抗灾救助食品库");
				parent.layer.close(index);
			}
		});
	});

	var i = 0;
	$('#Add').on('click', function () {
		i++;
		var tabObj = document.getElementById("mytab");//获取添加数据的表格
		var bodyObj = document.getElementById("tab");//获取添加数据的表格
		var rowsNum = tabObj.rows.length;
		var rowsNum2 = bodyObj.rows.length;
		var colsNum = tabObj.rows[rowsNum - 1].cells.length;//获取行的列数
		var myNewRow = bodyObj.insertRow(rowsNum2);//插入新行
		var newTdObj1 = myNewRow.insertCell(0);
		newTdObj1.innerHTML = "<input type='checkbox' name='check_list' id='check_list" + rowsNum + "' style='border:0px' /> ";

		var newTdObj2 = myNewRow.insertCell(1);
		newTdObj2.innerHTML = $("#mytab").children('tbody').children('tr').eq(0).children("td").eq(1).html();

		setTimeout(function () {
			laydate({
				elem: "#hi" + rowsNum + "", //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
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