﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

		function seachAllocation() {
			parent.layer.open({
				type: 2,
				title: '选择调拨单',
				maxmin: true,
				shadeClose: true, //点击遮罩关闭层
				area: ['800px', '500px'],
				content: 'materialsInputController.do?seachAllocation',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					var data = $(layero).find('iframe')[0].contentWindow.CheckSelect();
					if (data != false) {
						var str = data.split(',');
						$('#txt').val(str[0]);
						var tabObj = document.getElementById("mytab");//获取添加数据的表格
						var bodyObj = document.getElementById("tab");//获取添加数据的表格
						var tabtr = document.getElementsByTagName("tr");
						for (var i = tabtr.length; i > 1; i--) {
							tabObj.deleteRow(1);
						}

						//发送ajax请求获取调拨通知详细和调拨物资信息
						queryAllocation(str[4]);
					}
					parent.layer.close(index);
				}
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
						parent.layer.close(index);
					}
				}
			});
		}

		//=============================入库仓库选择
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

		//=================================生成入库单号(页面加载完执行)
		window.onload = function () {
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = today.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var hour = today.getHours();
			if (hour < 10) {
				hour = "0" + hour;
			}
			var minute = today.getMinutes();
			if (minute < 10) {
				minute = "0" + minute;
			}
			var second = today.getSeconds();
			if (second < 10) {
				second = "0" + second;
			}

			$("#in_num input").val("RK-" + year + month + day + "-" + hour + minute + second);
			$("#in_num label").html("RK-" + year + month + day + "-" + hour + minute + second);
		};
    </script>
</head>
<body class="add-dis">

<div class="wrapper wrapper-content animated">
</div>
<%--action="materialsInputController.do?saveOrupdate&ctrl=save" method="post" --%>
<form id="add_form" enctype="multipart/form-data" target="_parent">
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
                        <div class="col-sm-4" id="in_num">
                            <input type="hidden" value="" name="storageNumber">
                            <label class="labelform"></label>
                        </div>
                        <label class="col-sm-2 control-label">是否定向：</label>
                        <div class="col-sm-4">
                            <div class="radio radio-inline radio-danger">
                                <input type="radio" id="radio15" value="是" name="isorienteering" checked="">
                                <label for="radio15">是 </label>
                            </div>
                            <div class="radio radio-inline radio-danger">
                                <input type="radio" id="radio16" value="否" name="isorienteering">
                                <label for="radio16">否</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group draggable">
                        <label class="col-sm-2 control-label">采购批次：</label>
                        <div class="col-sm-4">
                            <input type="text" name="purchaseBatch" maxlength="13" class="form-control"
                                   onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）+——|{}【】‘；：”“'。，、？ \\]/g,'');">
                        </div>
                        <label class="col-sm-2 control-label"><span class="start">*</span>使用用途：</label>
                        <div class="col-sm-4">
                            <div class="input-group" style="width: 100%;">
                                <select data-placeholder="" name="donationPurposes" id="donationPurposes"
                                        class="chosen-select form-control" tabindex="2"
                                        style="width: 100%">
                                    <%--<option value="">-请选择-</option>--%>
                                    <c:forEach items="${purpose}" var="purpose">
                                        <option value="${purpose.typecode}"
                                                hassubinfo="true">${purpose.typename}</option>
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
                                       readonly="readonly"/>
                                <input type="hidden" id="storageWarehouse" name="storageWarehouse">
                                <button class="btn btn-primary btnh" id="Button2" onclick="SelectWarehouse();"
                                        style="width: 30%" type="button"><i class="fa fa-search"></i>&nbsp;选择
                                </button>
                            </div>
                        </div>

					</div>
					<div class="form-group draggable">
						<label class="col-sm-2 control-label"><span class="start">*</span>物资来源：</label>
						<div class="col-sm-4">
							<div class="input-group" style="width: 100%;">
								<select data-placeholder="" name="substanceSource"
								        class="chosen-select form-control" id="wuzi_resource"
								        tabindex="2" style="width: 100%">
									<%--<option value="">-请选择-</option>--%>
									<c:forEach items="${source}" var="source">
										<option value="${source.typecode}" hassubinfo="true">${source.typename}</option>
										<c:if test="${source.typename eq '调拨'}">
											<input type="hidden" id="diaobo" value="${source.typecode}"/>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<label class="col-sm-2 control-label"><span class="start">*</span>来源对象：</label>
						<div class="col-sm-4">
							<input type="text" name="sourceObject" value="" class="form-control" id="txt1">

                            <input type="text" id="txt" readonly="readonly" name="sourceObject" class="form-control fl"
                                   style="width: 70%;display: none;">
                            <button class="btn btn-primary btnh" id="btn" onclick="seachAllocation();"
                                    style="width: 30%;display: none;height: 34px;" type="button">&nbsp;调拨单
                            </button>
                        </div>
                    </div>
                    <div class="form-group draggable">
                        <label class="col-sm-2 control-label"><span class="start">*</span>入库人：</label>
                        <div class="col-sm-4">
                            <input type="text" name="storagePerson" maxlength="13" class="form-control"
                                   id="storagePerson"
                                   onkeyup="this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）+——|{}【】‘；：”“'。，、？ \\]/g,'');">
                        </div>
                        <label class="col-sm-2 control-label"><span class="start">*</span>入库日期：</label>
                        <div class="col-sm-4">
                            <input id="hello" name="storageDate" class="laydate-icon form-control layer-date">
                        </div>
                    </div>
                    <div class="form-group draggable">
                        <label class="col-sm-2 control-label">检查报告上传：</label>
                        <div class="col-sm-4">
                            <div id="file-pretty">
                                <%--accept=".xls,.doc,.txt,.pdf,.jpg,.png,.bmp,.gif,.psd"--%>
                                <input type="file" name="file" id="uploadFile" class="form-control" accept=".xls,.doc,.txt,.pdf,.jpg,.png,.bmp,.gif,.psd">
                            </div>
                        </div>
                        <label class="col-sm-2 control-label"><span class="start">*</span>使用区域：</label>
                        <div class="col-sm-4">
                            <div id="Div1">
                                <input type="text" id="area" name="donateArea" readonly="readonly"
                                       class="form-control fl"
                                       style="width: 70%">
                                <button class="btn btn-primary btnh" id="btn1" onclick="SelectDiv();"
                                        style="width: 30%"
                                        type="button"><i class="fa fa-search"></i>&nbsp;选择
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="form-group draggable">
                        <label class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="remark" maxlength="250" name="remark"
                                      rows="3"></textarea>
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
                    </tbody>
                </table>
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

		$("#wuzi_resource").change(function () {
			var a = $("#wuzi_resource").val();
			if (a == $('#diaobo').val()) {
				$("#txt1").hide();
				$("#txt").show();
				$("#btn").show();
				$("#Add").hide();
				$("#del").hide();
				$("#txt1").val("");
				var tabObj = document.getElementById("mytab");//获取添加数据的表格
				var bodyObj = document.getElementById("tab");//获取添加数据的表格
				var tabtr = document.getElementsByTagName("tr");
				for (var i = tabtr.length; i > 1; i--) {
					tabObj.deleteRow(1);
				}
			} else {
				$("#txt1").show();
				$("#txt").hide();
				$("#btn").hide();
				$("#Add").show();
				$("#del").show();
				$("#txt").val("");
				var tabObj = document.getElementById("mytab");//获取添加数据的表格
				var bodyObj = document.getElementById("tab");//获取添加数据的表格
				var tabtr = document.getElementsByTagName("tr");
				for (var i = tabtr.length; i > 1; i--) {
					tabObj.deleteRow(1);
				}
			}

		})

	});

	//这个没有用
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

	//=========================选择物资
	var i = 0;
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
					newTdObj5.innerHTML = "<input type='number' min='1' name='outputAmount' class='form-control' maxlength='5' required minlength='1'   placeholder='' onkeyup=\"this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/g,'');\" />";
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
					newTdObj12.innerHTML = "<input readonly type='hidden' name='messageid' value='" + str[4] + "' class='form-control' placeholder='' />";

					// $("input[name=outputAmount]").change(function () {
					// 	var num = parseFloat($(this).val()) * parseFloat($("input[name=price]").val());
					// 	$(this).parent().parent().find("input[name=totalPrices]").val(num);
					// });

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
