<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>上传单位</title>
<t:base type="jquery"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<link rel="stylesheet" href="plug-in/jstree/css/style.min.css" type="text/css">
 <script type="text/javascript" src="plug-in/jstree/js/jstree.js"></script>
 <script type="text/javascript" src="plug-in/jstree/js/jstree.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<!-- update-end--Author:jg_renjie  Date:20160317 for：组织机构改为ztree -->
<script type="text/javascript">

$(document).ready(function(){
	var name="";
	$.ajax({
		  url:"systemRegionController.do?tree", 
			success:function(data){
				 $('#jstree1').jstree({
					 'plugins':['checkbox'], 
					 	"checkbox": {
		                    "keep_selected_style": false,//是否默认选中
		                    "three_state": false//父子级别级联选择
		                },
					    'core' : {
					    	'data':JSON.parse(data)
					  }   
				}); 
			 }
		 });	
});




function treeChecked() {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    
    var ids = $('#jstree1').jstree().get_selected(); //获取所有选中的节点ID
    var nodes = $('#jstree1').jstree().get_selected(true); //获取所有选中的节点对象
    var names="";    
    $.each( nodes, function(i, n){
    	if (i==0){
    		names+= n.text;
    	}
    	else{
    		names+= ","+n.text;
    	}
	});
    return ids+"|" +names;
}
		 
</script>
</head>
<body style="overflow-y: auto" scroll="no">
<ul id="jstree1" class="ztree" style="margin-top: 30px;"></ul>
</body>
</html>