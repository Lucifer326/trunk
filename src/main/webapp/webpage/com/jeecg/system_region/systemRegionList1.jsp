<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>行政区划表</title>
<t:base type="jquery,easyui-ss,tools,DatePicker"></t:base>
</head>
<body class="gray-bg">
	<div class="loaction"
		style="height: 30px; padding-top: 10px; border-top: solid 1px #AFAFAF;">
		<p>
			<span class="pl30">${currentLocation}</span>
		</p>
	</div>
	<div id="regionListtb">
		<div style="padding: 6px 0px; float: left; margin-left: 5px;">
			<span style="float: right"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-undo"
				onclick="searchReset()">重置</a>
			</span> <span style="float: right"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-search"
				onclick="searchRegion()">查询</a>
			</span> <span style="float: right"> &nbsp;&nbsp;<input
				id="searchParam" type="text" name="searchParam"
				style="height: 20px;" />
			</span> <span style="float: right">请选择查询条件： <select id="searchParams"
				name="searchParams">
					<!-- <option value="">请选择</option> -->
					<option value="regionName">行政区划名称</option>
					<option value="regionCode">行政区划编码</option>
					<option value="regionShortName">行政区划简称</option>
			</select>
			</span>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
		<div
			style="height: 30px; padding-top: 10px; border-top: solid 1px #e0e0e0;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add"
				onclick="addRegion()">行政区划录入</a> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-edit"
				onclick="update('行政区划编辑','systemRegionController.do?goUpdate','regionList')">行政区划编辑</a>
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-put"
				onclick="ImportXls()">导入</a> <a href="#" class="easyui-linkbutton"
				plain="true" icon="icon-putout" onclick="ExportXls()">导出</a> <a
				href="#" class="easyui-linkbutton" plain="true" icon="icon-putout"
				onclick="ExportXlsByT()">模板下载</a><a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-reload"
				onclick="reloadTable()">刷新</a>
		</div>
	</div>
	<table width="90%" id="regionList" toolbar="#regionListtb"></table>
	<script type="text/javascript">
		//查询
		function searchRegion() {
			var url = 'systemRegionController.do?asynDatagrid';
			var searchparams = $('#searchParams').val();
			var searchparam = $('#searchParam').val();
			if (searchparams && searchparam) {
				url += '&searchparams="' + searchparams + '"&searchparam="'
						+ searchparam + '"';
				$.get(url, function(data) {
					data = eval(JSON.parse(data));
					if (data.length) {
						$('#regionList').treegrid('loadData', {
							total : data.length,
							rows : data
						});
					} else {
						$('#regionList').treegrid('loadData', {
							total : 0,
							rows : []
						});
					}
				});
			} else {
				reloadTable();
			}
		}
		//重置
		function searchReset() {
			$("#regionListtb").find(":input").val("");
		}
		//生成树形表格
		$(function() {
			var count = 0;
			$('#regionList')
					.treegrid(
							{
								idField : 'id',
								treeField : 'text',
								/* title : "行政区划表", */
								url : 'systemRegionController.do?asynDatagrid',
								fit : false,
								animate : true,
								columns : [ [
										{
											field : 'id',
											title : '编号',
											hidden : true
										},
										{
											field : 'text',
											title : '行政区划名称',
											width : 280,

										},
										{
											field : 'code',
											title : '行政区划编码',
											width : 280,
											align : 'center'
										},
										{
											field : 'regionShortName',
											title : '行政区划简称',
											width : 150,
											align : 'center'
										},
										{
											field : 'regionLevel',
											title : '行政区划级别',
											width : 150,
											align : 'center'
										},
										{
											field : 'areaCode',
											title : '行政区划区号',
											width : 280,
											align : 'center'
										},
										{
											field : 'regionPostcode',
											title : '行政区划邮政编码',
											width : 280,
											align : 'center'
										},
										{
											field : 'null',
											width : 100,
											align : 'center',
											title : '操作',
											formatter : function(value, rec,
													index) {
												var href = '';
												href += "[<a href='#' onclick=delObj('systemRegionController.do?doDel&id="
														+ rec.id
														+ "','regionList')>";
												href += "删除</a>]";
												return href;
											}
										} ] ],
								//数据加载成功后 默认展开第1级
								onLoadSuccess : function(row, data) {
									if(count<1){
										count++;
										for(var i=0;i<data.length;i++){
											 $("#regionList").treegrid('expand',data[i].id)
										};
									}
								},
							});
		});
		//刷新
		function reloadTable() {
			try {
				$("#regionList").treegrid('options').url = 'systemRegionController.do?asynDatagrid';
				$('#regionList').treegrid('reload');
			} catch (ex) {
			}
		}
		//新增方法
		function addRegion() {
			var id = '';
			var rowsData = $('#regionList').treegrid('getSelected');
			if (rowsData) {
				id = rowsData.id;
			}
			if (id == '') {//新增前请选择父级节点
				tip("请选择上级区划!");
				return;
			}
			var url = 'systemRegionController.do?goAdd&id=' + id;
			add('行政区划录入', url, 'regionList');
		}

		//导入
		function ImportXls() {
			openuploadwin('Excel导入', 'systemRegionController.do?upload',
					"regionList");
		}

		//导出
		function ExportXls() {
			JeecgExcelExport("systemRegionController.do?exportXls",
					"regionList");
		}

		//模板下载
		function ExportXlsByT() {
			JeecgExcelExport("systemRegionController.do?exportXlsByT",
					"regionList");
		}
		function test(p){
			alert(p);
		}
	</script>
</body>
</html>