<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>选择视图列表</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript" charset="UTF-8">

	
	  $(function(){
		$('#viewnametree').tree({
				url:"ModelViewController.do?GetTsfunctionMenu",
			//	url:"functionController.do?functionGrid",//用这个需要改造MvController.do?getTsfunctionMenudata
			//	url:"functionController.do?setPFunction",//因为传参不一样这个只有id，没有functionid等
					onSelect:function(node){
				if ($('#viewnametree').tree('isLeaf', node.target)){
					
					//表单赋值后再次发起异步填充表单其他字段
					$.get(
						'ModelViewController.do?getTsfunctionMenudata&id='+node.id,
					function(data)
					{
								
								data=JSON.parse(data);
								
								$('#viewname').val(data.viewurl);
							
							
						
					}
					);
				}
			}		
		}); 
	}) 

 
 
</script>
</head>
<body>
<table id="treegrid"></table>
 <ul  id="viewnametree"></ul> 
<input id="viewname" type="hidden"></input> 

</body>
</html>
