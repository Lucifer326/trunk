<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>上传单位</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<link rel="stylesheet" href="plug-in/jstree/css/style.min.css" type="text/css">
 <script type="text/javascript" src="plug-in/jstree/js/jstree.js"></script>
 <script type="text/javascript" src="plug-in/jstree/js/jstree.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<!-- update-end--Author:jg_renjie  Date:20160317 for：组织机构改为ztree -->
<script type="text/javascript">

$(document).ready(function(){
	
	
	
	//普通tree systemRegionGrid	asynDatagrid 
	$('#tree1').tree({    
	    url:'systemRegionController.do?asynDatagrid',
	    animate : true,
	}); 
	
	
	//JsTree 
	
	/* $.ajax({
		  url:"systemRegionController.do?asynDatagrid", 
			success:function(data){
				 $('#jstree1').jstree({ 
					    'core' : {
					    	'data':JSON.parse(data),
					    	'Multiple':false
					  }   
				}); 
			 }
		 });   */
		 
		 
	
	
	$.ajax({
			url : "systemRegionController.do?asynDatagrid",
			type:'post',
			//data:{sId:$("#standardId").val()},
			success : function(data) {
				
				$('#jstree1').jstree({
					'core':{
						'check_callback': true, // 对树的增删改操作是否被允许。
						'data':JSON.parse(data),
						'state':{'opened':true}
					}
				})
				.on('loaded.jstree', function(e, data){  
					    var inst = data.instance;  
					    var id ='${disid}';
					    var obj = inst.get_node(id);  
					    inst.select_node(obj);
					    $('#jstree').jstree(true).toggle_node(data.selected) // 点击节点名称可以展开
					    
					}).bind("select_node.jstree", function(event, data) {  
						
		                var inst = data.instance;  
		                var selectedNode = inst.get_node(data.selected);  
		                //console.info(selectedNode.aria-level);  
		                var level = $("#"+selectedNode.id).attr("aria-level");  
		                //alert(11);
		                if(selectedNode.children.length==0 ){ 
		                	//alert ("bind");
		                	loadChildNode(inst, selectedNode);  
		                }  
		                else
		                	{
		                	//alert("子节点长度"+selectedNode.children.length);
		                	
		                	}
		            }); 	
			}
		});
	$('#bsprFamList').datagrid('reload',{typeCode:type,familyType:fType});

		 
	 
	
});


function loadChildNode(inst, selectedNode){  
    
	    var domid=selectedNode.id;
	     $.ajax({  
	       // url :  "systemRegionController.do?GetChildRegionTree&domId="+domid+" ", 
	        url :  "systemRegionController.do?asynDatagrid&id="+domid, 
	        dataType : "json",  
	        type : "POST",  
	        success : function(data) {          	
	            if(data) {  
	               selectedNode.children = [];                        
	               $.each(data, function (i, item) {  
	                        var obj = {text:item};  
	                        inst.create_node(selectedNode,item,"last");  
	               });  
	               inst.open_node(selectedNode);  
	               
	            }  
	        }  
	    });   
	}

	
/* function treeChecked() {
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
} */
function treeChecked() {
	var type=$("#type").val();
    var ref=$('#jstree1').jstree(true);
	var sel=ref.get_selected();
	var ids=sel[0];
	var nodes=ref.get_node(sel);	
	var ids=nodes.id;
	var names=nodes.text;
	var tag=true;
	if(ids){
		$.ajax({
			url : "systemRegionController.do?isEnd",
			async:false,
			data:{aId:ids},
			type:'post',
			dataType:"json",
			success : function(data) {
				if(!data.success){
					tip('请选择一个最末节点的行政区划');
					tag=false;
				}
			}
		});
	}
	if(tag){
	    return ids+"|" +names;
	}else{
		return "";
	}
}	
	
	
		 
</script>
</head>
<body style="overflow-y: auto" scroll="no">
<ul id="tree1" class="ztree" style="margin-top: 30px;"></ul>
<ul id="jstree1" class="ztree" style="margin-top: 30px;"></ul>
</body>
</html>