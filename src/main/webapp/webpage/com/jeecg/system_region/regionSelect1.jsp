<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>



<title>行政区划集合</title>
<t:base type="jquery"></t:base>
<!-- update--start--by:jg_renjie--at:20160318 for:#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式 -->
<!-- update-start--Author:jg_renjie  Date:20160317 for：组织机构改为ztree -->
<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<!-- update-end--Author:jg_renjie  Date:20160317 for：组织机构改为ztree -->

</head>
<body style="overflow-y: hidden" scroll="no">
 
<ul id="regionSelect" class="ztree" style="margin-top: 30px;"></ul>


<%-- <t:datagrid name="departList" title="common.department.list" actionUrl="departController.do?departSelectDataGrid" idField="id" checkbox="true" showRefresh="false">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.department.name" field="departname" width="50"></t:dgCol>
</t:datagrid> --%>
</body>
<script type="text/javascript">
	
	//配置数据
	var setting = {
	   check: {
			enable: true,
			autoCheckTrigger: true,   //可触发自动关联勾选操作
			//chkStyle : "checkbox",
			chkboxType: { "Y": "", "N": "" }
		}, 
			
			data: {
					simpleData: {
					enable: true
				},
		
		},
		 callback: {
			onExpand: zTreeOnExpand
		} 
		
	};
	//后台传过来的区划的 信息(id 数组)
	var data = '${orgIds}';
	var datas = data.split(",");
	
	//首次进入加载level为1的
	$(function(){
		$.post(
			//'systemRegionController.do?getRegionInfo',
					'systemRegionController.do?getRegionInfo',
			function(data){
				var d = $.parseJSON(data);
				if (d.success) {
					var dbDate = eval(d.msg);
					
					//初始化树
					
					$.fn.zTree.init($("#regionSelect"), setting, dbDate);
											
					var treeObj = $.fn.zTree.getZTreeObj("regionSelect");
					
					//加载所有节点      treeObj.expandAll(true); 
					
					//后台传过来的区划的 信息(id 数组)
						var data = '${orgIds}';
						var datas = data.split(",");
					for(var i = 0; i <datas.length;i++){
						treeObj.checkNode(treeObj.getNodesByParam("id", datas[i],null)[0],true, true);
					}
				}
			}
		);
	});
	//加载展开方法
	function zTreeOnExpand(event, treeId, treeNode){
		var treeNodeId = treeNode.id;
		 $.post(
			'systemRegionController.do?getRegionInfo',
			{parentid:treeNodeId},
			function(data){
				var d = $.parseJSON(data);
				
				if (d.success) {
					//后台传来的数据
					var dbDate = eval(d.msg);
					
					
					var tree = $.fn.zTree.getZTreeObj("regionSelect");
					//如果没有子节点  就进行添加节点 操作     解决了重复 添加问题
					if(treeNode.children == undefined){
						//添加节点					
						tree.addNodes(treeNode, dbDate);	
							
						
						//后台传过来的区划的 信息(id 数组)
						var data = '${orgIds}';
						var datas = data.split(",");
						  for(var i = 0; i <datas.length;i++){
							tree.checkNode(tree.getNodesByParam("id", datas[i],null)[0],true, true);
						}  
					}					
				}
			}
		);
	}
	
	
</script>

</html>
<!-- update--end--by:jg_renjie--at:20160318 for:#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式 -->