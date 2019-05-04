<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>多级字典表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body class="gray-bg">
<div id="main_depart_list" class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
			<table width="100%" id="regionSelect" toolbar="#regionListtb" checked ="true"></table>
		
	</div>

		
</div>
	<script type="text/javascript">
		
		//表单初始化操作
		$(function() {
			
			
			storage = $.localStorage;
			if (!storage)storage = $.cookieStorage;
			$('#regionSelect').tree({
				idField : 'id',
				treeField : 'text',
				title : '多级字典列表',
				animate : true,
				url : 'systemRegionController.do?tree1&orgIds='+'${orgIds}',
				fit : true,
				checkbox : true,
				
				//定义是否层叠选中状态
				cascadeCheck :true,

				//自动适应
				fitColumns : true,
				columns : [ [
						{
							field : 'id',
							title : '编号',
							hidden : true,
						},
						{
							field : 'text',
							title : '',
						},
						
						
						] ],
						
						onLoadSuccess : function() {
							
							//expandAll 全部展开   
							//collapseAll 全部合上
							$('#regionSelect').tree('collapseAll');
						},	
						
				
			});
		
	});
		
		
	</script>
</body>
</html>