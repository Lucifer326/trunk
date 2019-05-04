<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>区划选择表</title>
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
			
			$('#regionSelect').tree({
				idField : 'id',
				treeField : 'text',
				title : '区划表',
				animate : true,
				url : 'systemRegionController.do?deptSelectRegionTree&parentDepartId=${parentDepartId}&checkRegionId=${checkRegionId}',
				fit : true,
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
						//加载成功后实现回显默认选中的添加高亮
						onLoadSuccess: function (node, data) {
			                
							$.each(data, function(key, val){
								
								//如果数据为选中状态，则默认选中该节点
								if(data[key].checked){
									
									var node = $('#regionSelect').tree('find', data[key].id);
									$('#regionSelect').tree('select', node.target);
								}
										
								});

			            } 
						
				
			});
		
	});
		
		
	</script>
</body>
</html>