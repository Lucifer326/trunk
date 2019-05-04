<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<head>
<t:base type="jquery,easyui-ss,tools,DatePicker"></t:base>
</head>
<body>
	<div class="easyui-layout" fit="true" class="largebox">
		
		<div
			data-options="region:'west',title:'行政区划',
			collapsed:false,
			split:true,
			border:false,"
			style="width: 250px; overflow: hidden;" class="left-hide-box fl">
			<div class="easyui-panel" style="padding: 0px; border: 0px"
				fit="true" border="false" id="regionListpanel">
				<div>
					<ul id="tree">
					</ul>
				</div>
			</div>
		</div>
		<div region="center" style="padding: 0px; border: 0px">
		
			<t:datagrid name="bssysDeploydisList" checkbox="true"
				fitColumns="false" title="系统配置绑定行政区划"
				actionUrl="bssysDeploydisController.do?disdatagrid&deploy_id=${deploy_id}"
				idField="id" fit="true" queryMode="group">
				<t:dgCol title="主键" field="id" hidden="true" queryMode="group"></t:dgCol>
				<t:dgCol title="区划编号" field="disCode" queryMode="group"></t:dgCol>
				<t:dgCol title="区划名称" field="regionName" queryMode="single"></t:dgCol>
				<t:dgCol title="配置名称" field="name" query="false" queryMode="group"></t:dgCol>
				<t:dgCol title="配置值" field="value" queryMode="group"></t:dgCol>
				<t:dgCol title="说明" field="description" queryMode="group"></t:dgCol>
				<t:dgCol title="创建时间" field="createDate" hidden="true"
					queryMode="group"></t:dgCol>
				<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
				<t:dgDelOpt title="删除"
					url="bssysDeploydisController.do?doFalseDel&id={id}" />
				<t:dgToolBar title="录入" icon="icon-add"
					url="bssysDeploydisController.do?goAdd&deploy_id=${deploy_id}"
					funname="add1"></t:dgToolBar>
				<t:dgToolBar title="编辑" icon="icon-edit"
					url="bssysDeploydisController.do?goUpdate" funname="update"></t:dgToolBar>
				<t:dgToolBar title="批量删除" icon="icon-remove"
					url="bssysDeploydisController.do?doBatchFalseDel"
					funname="deleteALLSelect"></t:dgToolBar>
				<t:dgToolBar title="查看" icon="icon-search"
					url="bssysDeploydisController.do?goDetail" funname="detail"></t:dgToolBar>
				<t:dgToolBar title="返回" icon="icon-back" funname="back"></t:dgToolBar>
			</t:datagrid>
		</div>
	</div>
</body>
<script src="webpage/com/jeecg/bssys_deploy/bssysDeploydisList.js"></script>
<script type="text/javascript">
	$(function() {

		$('#tree')
				.tree(
						{

							url : 'systemRegionController.do?asynDatagrid',
							onSelect : function(node) {

								var queryParams = $('#bssysDeploydisList')
										.datagrid('options').queryParams;

								queryParams.dis_id = node.id;
								queryParams.deploy_id = '${deploy_id}';

								$('#bssysDeploydisList').datagrid('options').queryParams = queryParams;

								$("#bssysDeploydisList").datagrid("reload");
							},

							onLoadSuccess : function(data) {

								$("#tree li:eq(0)").find("div").addClass(
										"tree-node-selected"); //设置第一个节点高亮  
								var n = $("#regionListpanel").tree(
										"getSelected");
								if (n != null) {
									$("#tree").tree("select", n.target); //相当于默认点击了一下第一个节点，执行onSelect方法  
								}
							}
						});

	});

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'bssysDeploydisController.do?upload',
				"bssysDeployList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("bssysDeploydisController.do?exportXls",
				"bssysDeployList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("bssysDeploydisController.do?exportXlsByT",
				"bssysDeployList");
	}

	//返回
	function back() {
		this.location.href = "bssysDeployController.do?listtree";
	}
	//新增
	function add1(title, addurl, gname, width, height) {
		
		var node = $('#tree').tree('getSelected');
		var nodeid = node.id;
		
		if (nodeid) {
			//验证是否已存在数据
			$.ajax({
				type : "POST",//用POST方式传输
				dataType : "text",//数据格式:text
				url : 'bssysDeploydisController.do?getCount',//目标地址
				data : "dis_id=" + nodeid + "&deploy_id=${deploy_id}",
				success : function(text) {
					//alert(text);
					if (text != '"0"') {
						tip("该行政区划已存在当前系统配置的数据！")
						return;
					} else {
						gridname = gname;
						addurl += '&dis_id=' + nodeid;
						console.log(addurl);
						createwindow(title, addurl, "600px", "200px");
					}
				}
			});
		} else {
			tip("请选择行政区划！")
			return;
		}
	}
	//编辑
	/*  function update1(title,updateurl,gname,width,height) {
	 createwindow(title, updateurl,"600px","200px");
	 } */
</script>