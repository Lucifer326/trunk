<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<title>系统配置表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body class="gray-bg">
	<table width="100%" id="deployList" toolbar="#deployListtb"></table>
	<div id="deployListtb">
		<div style="padding: 6px 0px; float: left; margin-left: 5px;">
			<span style="float: right"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-undo"
				onclick="searchReset()">重置</a>
			</span> <span style="float: right"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-search"
				onclick="searchRegion()">查询</a>
			</span> <span style="float: right"> &nbsp;&nbsp;<input
				id="searchParam" type="text" name="searchParam"
				style="height: 30px;" />
			</span> <span style="float: right">配置名称：
			</span>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>		
		<div style="height: 30px; padding-top: 10px; border-top: solid 1px #e0e0e0;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add"
				onclick="addDeploy()">系统配置录入</a>
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" operationCode="update"
				onclick="update('系统配置编辑','bssysDeployController.do?goUpdate','deployList','720px','460px')">系统配置编辑</a>
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" operationCode="binddis"
				onclick="bindDis()">绑定行政区划</a>
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" operationCode="update"
				onclick="update('系统配置查看','bssysDeployController.do?goDetail','deployList')">系统配置查看</a>
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="addOrg()">配置当前行政区划</a>
		</div>
	</div>
	<script src = "webpage/com/jeecg/bssys_deploy/bssysDeployList.js"></script>
	<script type="text/javascript">
		$(function() {
			var count = 0;
			$('#deployList')
					.treegrid(
							{
								idField : 'id',
								treeField : 'code_name',
								title : '系统配置列表',
								url : 'bssysDeployController.do?treedatagrid',
								fit : true,
								rownumbers : true,
								animate : true,
								columns : [ [
										{
											field : 'id',
											title : 'ID',
											hidden : true,
											width:'100'
										},
										{
											field : 'code_name',
											title : '配置名称',
											width:'600'
										},
										{
											field : 'description',
											title : '说明',
											width:'600'
										},
										{
											field : 'value',
											title : '配置值',
											width:'100'
										},
										{
											field : 'IsBinddisName',
											title : '是否绑定区划',
											width:'100'
										},
										{
											field : 'null',
											title : '操作',
											width:'100',
											formatter : function(value, rec,
													index) {
												var href = '';
												href += "[<a href='#' onclick=delObj('bssysDeployController.do?doFalseDel&id="
														+ rec.id
														+ "','deployList')>";
												href += "删除</a>]";
												return href;
											}
										} 
										] ],
										//数据加载成功后 默认展开
										onLoadSuccess : function(row,data) {
										if(count<3){
											count++;
											for(var i=0;i<data.length;i++){
												 $("#deployList").treegrid('expand',data[i].id)
											};
										}
									},
									onClickRow : function(
											rowData) {
										rowid = rowData.id;
										gridname = 'deployList';
									},
									//树形节点展开前事件
									onBeforeExpand:function(node){											 
					                    	 var url = "bssysDeployController.do?treedatagrid&id="+node.id;
					                         $("#deployList").treegrid("options").url = url; 
					                         $("#deployList").treegrid("clearSelections");
					                     return true;  
					                 },
							});
		});
		function getSelectRows() {
			return $('#deployList').datagrid('getSelections');
		}
		function reloadTable() {
			try {
				 $("#deployList").treegrid('options').url = 'bssysDeployController.do?treedatagrid';
				$('#deployList').treegrid('reload');
			} catch (ex) {
			}
		}
		 function addDeploy() {
			var id = '';
			var rowsData = $('#deployList').treegrid('getSelected');
			if (rowsData) {
				id = rowsData.id;
			}
			var url = 'bssysDeployController.do?goAdd&id=' + id;
			add('系统配置录入', url, 'deployList','720px','460px');
		} 
		//导入
		function ImportXls() {
			openuploadwin('Excel导入', 'bssysDeployController.do?upload',
					"bssysDeployList");
		}
		//导出
		function ExportXls() {
			JeecgExcelExport("bssysDeployController.do?exportXls",
					"bssysDeployList");
		}
		//模板下载
		function ExportXlsByT() {
			JeecgExcelExport("bssysDeployController.do?exportXlsByT",
					"bssysDeployList");
		}
		
		function addOrg(){
			var url = "bssysDeployController.do?add";
	        var width = "665px";
	        var height = "300px";
	        add('配置行政区划', url, "departList",width,height);
		}
		
		//查询
		function searchRegion() {
			var url = 'bssysDeployController.do?treedatagrid';	
			var searchparam = $('#searchParam').val();
			if (searchparam) {
				url += '&name=' + searchparam;
				$.get(url, function(data) {
					data = eval(JSON.parse(data));					
					if (data.length) {
						$('#deployList').treegrid('loadData', {
							total : data.length,
							rows : data														
						});
					} else {
						$('#deployList').treegrid('loadData', {
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
			$("#deployListtb").find(":input").val("");
		}
	</script>
</body>
</html>