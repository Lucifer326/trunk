<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>上传单位</title>
<t:base type="jquery"></t:base>
<link rel="stylesheet" type="text/css"
	href="plug-in/ztree/css/zTreeStyle.css">
<link rel="stylesheet" href="plug-in/jstree/css/style.min.css"
	type="text/css">
<link rel="stylesheet" href="plug-in/layer/skin/layer.css"
	type="text/css">
<script type="text/javascript" src="plug-in/jstree/js/jstree.js"></script>
<script type="text/javascript" src="plug-in/jstree/js/jstree.min.js"></script>
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/layer/layer.js"></script>
<!-- update-end--Author:jg_renjie  Date:20160317 for：组织机构改为ztree -->
<script type="text/javascript">

$(document).ready(function(){
	var name="";
	$.ajax({
		  url:"systemRegionController.do?tree", 
			success:function(data){
				 $('#jstree1').jstree({ 
					    'core' : {
					    	'data':JSON.parse(data),
					    	'Multiple':false
					  },
					  'plugins': ['contextmenu'],
					  	"contextmenu":{
	                        "items":{
	                            "新建":{
	                                "label":"新建",
	                                "action":function(data){
	                                    var inst = jQuery.jstree.reference(data.reference),
	                                            obj = inst.get_node(data.reference);
	                                    layer.open({
	                                        type:2,
	                                        title:'新建',
	                                        maxmin:true,
	                                        zIndex:100,
	                                        shadeClose:true,
	                                        area:['900px','500px'],
	                                        content:'tsAdministrationController?goSave&pid='+obj.id,
	                                        btn:['确定','取消'],
	                                        yes:function(index,layero){
	                                            var tem = $(layero).find("iframe")[0].contentWindow.saveFunc();
	                                            $.post(
	                                                'tsAdministrationController/save',tem,function(result){
	                                                    if(result.success==0){
	                                                        swal({
	                                                            title:'添加成功',type:'success',
	                                                            zIndex:101,
	                                                            confitmButtonText:'确定'
	                                                        },function(){
	                                                            layer.close(index);
	                                                        });
	                                                    }}
	                                            );
	                                        }
	                                    });
	                                }
	                            },
	                            "删除":{
	                                "label":"删除",
	                                "action":function(data){
	                                    var inst = jQuery.jstree.reference(data.reference),
	                                            obj = inst.get_node(data.reference);
	                                    if(confirm("确定要删除此菜单？删除后不可恢复。")){
	                                        jQuery.get("tsAdministrationController/deleteAll?id="+obj.id,function(dat){
	                                            if(dat.success == 0){
	                                                alert("删除菜单成功！");
	                                                $('#jstree1').jstree('refresh');

	                                            }else{
	                                                alert("删除菜单失败！");
	                                            }
	                                        });
	                                    }
	                                }
	                            },
	                            "编辑":{
	                                "label":"编辑",
	                                "action":function(data){
	                                    var inst = jQuery.jstree.reference(data.reference),
	                                            obj = inst.get_node(data.reference);
	                                    layer.open({
	                                        type:2,
	                                        title:'修改',
	                                        maxmin:true,
	                                        zIndex:100,
	                                        shadeClose:true,
	                                        area:['900px','500px'],
	                                        content:'tsAdministrationController?goUpdate&id='+obj.id,
	                                        btn:['确定','取消'],
	                                        yes:function(index,layero){
	                                            var tem = $(layero).find("iframe")[0].contentWindow.saveFunc();
	                                            console.log(tem);
	                                            $.post(
	                                                'tsAdministrationController/update',tem,function(data){
	                                                        swal({
	                                                            title:'修改成功',
	                                                            type:'success',
	                                                            zIndex:101,
	                                                            confirmButtonText:'确定'
	                                                        }, function (){
	                                                            layer.close(index);
	                                                        });
	                                                    }
	                                            );

	                                        }
	                                    });
	                                }
	                            }
	                        }
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

function add(data){
    var inst = jQuery.jstree.reference(data.reference),
    	obj = inst.get_node(data.reference);
    layer.open({
        type:2,
        title:'新建',
        maxmin:true,
        zIndex:100,
        shadeClose:true,
        area:['900px','500px'],
        content:'tsAdministrationController?goSave&pid='+obj.id,
        btn:['确定','取消'],
        yes:function(index,layero){
            var tem = $(layero).find("iframe")[0].contentWindow.saveFunc();
            $.post(
                'tsAdministrationController/save',tem,function(result){
                    if(result.success==0){
                        swal({
                            title:'添加成功',type:'success',
                            zIndex:101,
                            confitmButtonText:'确定'
                        },function(){
                            layer.close(index);
                        });
                    }}
            );
        }
    });
}

</script>
</head>
<body style="overflow-y: auto" scroll="no">
	<div>
		<h3>js生成tree</h3>
		<ul id="jstree1" class="ztree" style="margin-top: 30px;"></ul>
	</div>
	<div>
		<h3>标签生成tree</h3>
		<h:tree url="systemRegionController.do?tree" id="jstree2" contextmenu="true" contextmenuFunction="新建:add;修改:add"></h:tree>
	</div>
</body>
</html>