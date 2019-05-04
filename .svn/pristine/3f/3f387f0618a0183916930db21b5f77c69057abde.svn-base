<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>


<t:datagrid name="dictionaryItemList" title="操作"  treegrid="true" pagination = "false"
            actionUrl="systemController.do?typeTreegrid&typegroupId=${typegroupId}" fit="true"  fitColumns="true" idField="typepid" queryMode="group">
	
	<t:dgCol title="编号" field="id" treefield="id" hidden="true"></t:dgCol>
	<%-- <t:dgCol title="common.username" sortable="false" field="userName" query="true"></t:dgCol> --%>
	
	<t:dgCol title="条目名称"  field="typename" treefield="text" queryMode="group"  width="80"></t:dgCol>
	
	<t:dgCol title="条目编码" field="typecode" treefield="typecode" queryMode="group"  width="80"></t:dgCol>
	
	
	<t:dgCol title="启停状态" field="isStop" treefield="isStop"  queryMode="group"  width="80" dictionary ="isStop">
	<!--  dictionary ="isStop"  dictionary ="shifou"--></t:dgCol>
	<t:dgCol title="是否内置" field="isSys" treefield="isSys" queryMode="group"  width="80" dictionary ="shifou"></t:dgCol>
	
	<t:dgCol title="排序编号"  field="orderInLevel" treefield="orderInLevel" queryMode="group"  width="80"></t:dgCol>
	<%-- <t:dgCol title="common.operation" field="opt"></t:dgCol>
	<t:dgDelOpt title="common.delete" url="dictionaryItemController.do?doDel&id={id}" /> --%>
	
	
	
	
	 <t:dgToolBar title="条目录入"  icon="icon-add" url="systemController.do?typeGoAdd&typegroupId=${typegroupId}" funname="addType"></t:dgToolBar>
	<t:dgToolBar title="条目编辑"  icon="icon-edit" url="systemController.do?typeGoUpdate&typegroupId=${typegroupId}" funname="update1"></t:dgToolBar>
	<t:dgToolBar title="停用"  icon="icon-disable" url="systemController.do?isAble" funname="isAble_stop"></t:dgToolBar>
	<t:dgToolBar title="启用"  icon="icon-exit" url="systemController.do?isAble" funname="isAble_start"></t:dgToolBar>
	<t:dgToolBar title="删除"  icon="icon-delete" url="systemController.do?typeDoBatchDel" funname="deleteALLSelect1"></t:dgToolBar>
    
</t:datagrid>

<!-- <script src = "webpage/com/jeecg/dictionaryitem/dictionaryItemList.js"></script> -->
<script type="text/javascript">
$(function(){
	$('#dictionaryItemList').treegrid({
		idField : 'id',
		treeField : 'text',
		autoRowHeight: true,
		animate : true,
     
          //加载成功事件
      	  onLoadSuccess : function(data) {
      		$("#dictionaryItemList")
      				.treegrid(
      						"clearSelections");
      		//成功加载后  树形  不展开
      		$("#dictionaryItemList")
      		.treegrid("collapseAll");
      	},
            
            
	});
	
	
});



//添加操作
function addType(title,url){
	var id = '';
	var rowsData = $('#dictionaryItemList').treegrid('getSelected');
	if(rowsData){
		id = rowsData.id;
		
	}
	
	 url += '&id='+id;
	
	add('字典条目录入',url,'dictionaryItemList');
}


/**
 * 多记录刪除請求
 * @param title
 * @param url
 * @param gname
 * @return
 */
function deleteALLSelect1(title,url,gname) {
	gridname=gname;
	var node = $('#dictionaryItemList').treegrid('getSelected');

	if(node.children != undefined){
		tip('存在子节点,请删除子节点');
		return;
	}
	
	
	
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
      
    if(rows[0].isSys == 1){
		tip('系统内置,不可删除');
		return;
	} 
    
    if(rows[0].isSys == 1){
		tip('系统内置,不可删除');
		return;
	} 
    
    
    
    
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定要删除该数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : url,
					type : 'post',
					data : {
						ids : ids.join(',')
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
							ids='';
						}
					}
				});
			}
		});
	} else {
		tip("请选择需要删除的数据");
	}
}

/**
 * 修改
 * 
 */
function update1(title,url, id,width,height,isRestful) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	
	
	
	if (!rowsData || rowsData.length==0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条记录再编辑');
		return;
	}
	
	if(isRestful!='undefined'&&isRestful){
		url += '/'+rowsData[0].id;
	}else{
		url += '&id='+rowsData[0].id;
	}
	
	createwindow(title,url,width,height);
	
}

	  //启用
	  function isAble_start(title,url, id,width,height,isRestful) {
	  			  
		  gridname=id;
	  	
		  //获得选中行的 数据 集合
		  var rowsData = $('#'+id).datagrid('getSelections');
	  		  	
	  	if (!rowsData || rowsData.length==0) {
	  		tip('请选择条目');
	  		return;
	  	}
	  	if (rowsData.length>1) {
	  		tip('请选择一条记录');
	  		return;
	  	}
	  	if (rowsData[0].isStop == 0) {
	  		tip('已启用');
	  		return;
	  	}
	  	if (rowsData[0].isSys == 1) {
	  		tip('系统内置,不可修改');
	  		return;
	  	}
	  	if(isRestful!='undefined'&&isRestful){
	  		url += '/'+rowsData[0].id;
	  	}else{
	  		url += '&id='+rowsData[0].id;
	  	}
	  	//去 后台  进行 判断 是否停用启用 ，然后进行 修改状态
	  	$.ajax({
			url : url,
			type : 'post',		
			cache : false,
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					
				}
				
				reloadTable();
			}
	  			
		});
	  	
	  	
	  }
	  //停用
 function isAble_stop(title,url, id,width,height,isRestful) {
	  	
		  
		  
		  gridname=id;
	  	
		  //获得选中行的 数据 集合
		  var rowsData = $('#'+id).datagrid('getSelections');
	  		  	
	  	if (!rowsData || rowsData.length==0) {
	  		tip('请选择条目');
	  		return;
	  	}
	  	if (rowsData.length>1) {
	  		tip('请选择一条记录');
	  		return;
	  	}
	  	if (rowsData[0].isStop == 1) {
	  		tip('已停用');
	  		return;
	  	}
	  	if (rowsData[0].isSys == 1) {
	  		tip('系统内置,不可修改');
	  		return;
	  	}
	  	if(isRestful!='undefined'&&isRestful){
	  		url += '/'+rowsData[0].id;
	  	}else{
	  		url += '&id='+rowsData[0].id;
	  	}
	  	//去 后台  进行 判断 是否停用启用 ，然后进行 修改状态
	  	$.ajax({
			url : url,
			type : 'post',		
			cache : false,
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					
				}
				
				reloadTable();
			}
	  			
		});
	  	
	  	
	  }
	//刷新
	function reloadTable(){
	 
		$('#dictionaryItemList').treegrid('reload');
	 
	}

</script>