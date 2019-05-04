<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>角色列表</title>
<t:base type="jquery"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
	var setting = {
	  check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	//加载数据
	$(function(){
		$.post(
			'userController.do?getRoleTree&orgIds=${orgIds}&&ids=${ids }',
			function(data){
				var d = $.parseJSON(data);
				if (d.success) {
					var dbDate = eval(d.msg);
					$.fn.zTree.init($("#roleSelect"), setting, dbDate);
				}else {
					alert(d.msg);
				}
			}
		);
	});
</script>
</head>
<body style="overflow: auto">
	<ul id="roleSelect" class="ztree" style="margin-top: 30px;"></ul>
</body>
</html>
