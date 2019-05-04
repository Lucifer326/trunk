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
<style>



#regionListtb>div>div{
	padding-left:16px!important;
}

#typegroupcode{
	height:20px!important;
}
#typegroupname{
	height:20px!important;
}
</style>
</head>
<body class="gray-bg">

  
	<div id="main_dict_list" class="easyui-layout" fit="true">
	 <div region="north" class="loaction" style="height:30px!important;  border-top:solid 1px #AFAFAF;">
			<p><span class="pl30">${currentLocation}</span></p>
     </div>
	<div region="center" style="padding:0px;border:0px">
			
			<table width="100%" id="dictionaryList" toolbar="#regionListtb" checked ="true" style="height:700px;"></table>
		<div id="regionListtb" style="padding: 3px; height: 80px;padding-top:8px;">			
			<div style="float: left; height:30px;" border-top:solid 1px #AFAFAF;">
					<span >字典名称:</span> <input type ="text" id = "typegroupname" name ="typegroupname" />
					<span >字典编码:</span> <input type ="text" id = "typegroupcode" name ="typegroupcode" />
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="selectType();">查询</a>
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="clearType();">重置</a>
				<div class="clear"></div>
				
				</div>
			
			<div class="clear"></div>
			<div style="height:30px;padding-top:10px;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addDictionary();">字典录入</a>
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="dictionaryTypeUpdate('字典编辑','systemController.do?goUpdate','dictionaryList');">字典编辑</a>
				<!-- <a href="#" class="easyui-linkbutton" plain="true" icon="icon-put" onclick="ImportXls()">导入</a> 
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="ExportXls()">导出</a> 
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="ExportXlsByT()">模板下载</a>
				
				 <a href="#" class="easyui-linkbutton" plain="true" icon="icon-put" onclick="reloadTable1()">刷新</a> -->
			</div>
		</div>
	</div> 
	
		<!--右侧隐藏框  --> 
				<div  data-options="region:'east',
						title:'字典条目表',
						collapsed:true,
						split:true,
						border:false,
						onExpand : function(){
							li_east = 1;
										},
						onCollapse : function() {
							li_east = 0;
						}"
						style="width: 800px; overflow: hidden;" id="eastPanel">
					<div class="easyui-panel"  fit="true" border="false" id="itemListpanel"></div>	
				</div>
</div>
	  <!-- 引入对应JS文件 -->
	  <script src = "system/type/typeGroupList1.js"></script> 
	<script type="text/javascript">
	
	//刷新
	function reloadTable1() {
		
		//alert("执行");
		try {
			$('#dictionaryList').treegrid('reload');
			$("#main_dict_list").layout("collapse","east");
		} catch (ex) {
		}
	}
	//添加操作
	function addDictionary(){
		var id = '';
		var rowsData = $('#dictionaryList').treegrid('getSelected');
		if(rowsData){
			id = rowsData.id;
			
		}
		
		var url = 'systemController.do?goAdd&id='+id;
		add1('字典录入',url,'dictionaryList');
		
	}
	/**
	 * 添加事件打开窗口
	 * @param title 编辑框标题
	 * @param addurl//目标页面地址
	 */
	function reloadTable(){
		reloadTable1();
	}
	//
	function add1(title,addurl,gname,width,height) {
		gridname=gname;
		createwindow1(title, addurl,width,height);
	}
	/**
	 * 修改
	 * 
	 */
	function dictionaryTypeUpdate(title,url, id,width,height,isRestful) {
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
		createwindow1(title,url,width,height);
		
	}
	/**
	 * 创建添加或编辑窗口
	 * 
	 * @param title
	 * @param addurl
	 * @param saveurl
	 */
	function createwindow1(title, addurl,width,height) {
		width = width?width:700;
		height = height?height:400;
		if(width=="100%" || height=="100%"){
			width = window.top.document.body.offsetWidth;
			height =window.top.document.body.offsetHeight-100;
		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		if(typeof(windowapi) == 'undefined'){
			$.dialog({
				content: 'url:'+addurl,
				lock : true,
				zIndex: getzIndex(),
				width:width,
				height:height,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){				    	
			    	iframe = this.iframe.contentWindow;
			    	saveObj1();	
			    	//setTimeout("reloadTable1()",20);
			    	//reloadTable1()
			    	return false;
			    	
			    },
			    cancelVal: '关闭',				   
			    cancel: true /*为true等价于function(){}*/
			});
		}else{
			W.$.dialog({
				content: 'url:'+addurl,
				lock : true,
				width:width,
				zIndex:getzIndex(),
				height:height,
				parent:windowapi,
				title:title,
				//不透明性
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	saveObj1();
			    	//setTimeout("reloadTable1()",20);
			    	//reloadTable1();
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});
			
		}
	   	    
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
	}
	/**
	 * 执行保存
	 * 
	 * @param url
	 * @param gridname
	 */
	function saveObj1() {
		
		
		$('#btn_sub', iframe.document).click();

		
	}
	// 删除调用函数
	function delObj1(url,name) {
		gridname=name;
		createdialog1('删除确认 ', '确定删除该记录吗 ?', url,name);
	}
	/**
	 * 创建询问窗口
	 * 
	 * @param title
	 * @param content
	 * @param url
	 */
	function createdialog1(title, content, url,name) {
		$.dialog.setting.zIndex = getzIndex(true);
		$.dialog.confirm(content, function(){
			doSubmit1(url,name);
			rowid = '';
		}, function(){
		});
	}
	/**
	 * 执行操作
	 * 
	 * @param url
	 * @param index
	 */
	function doSubmit1(url,name,data) {
		gridname=name;
		//--author：JueYue ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
		var paramsData = data;
		if(!paramsData){
			paramsData = new Object();
			if (url.indexOf("&") != -1) {
				var str = url.substr(url.indexOf("&")+1);
				url = url.substr(0,url.indexOf("&"));
				var strs = str.split("&");
				for(var i = 0; i < strs.length; i ++) {
					paramsData[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
				}
			}      
		}
		//--author：JueYue ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
		$.ajax({
			async : true,
			cache : true,
			type : 'POST',
			data : paramsData,
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数
			},
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					//alert("刷新");
					reloadTable1();
				} else {
					tip(d.msg);
					reloadTable1();
				}
			}
		});
		
		
	}
	//查询功能
	function selectType(){
		var url = 'systemController.do?typegroupGrid1';
		
		var typegroupcode = $('#typegroupcode').val();
		var typegroupname = $('#typegroupname').val();
		
		//如果查询的 值  不为 空 去后台 查询数据 返给前台 
		if(typegroupcode !='' || typegroupname !=''){
						
			$.ajax({
				  
				  type: 'POST',
				  url: url,
				  data: {"typegroupcode" : typegroupcode,
					  	"typegroupname" : typegroupname,
				  },
				 
				  success: function(data){
					  
					  data = eval(JSON.parse(data));
		    			
					  //如果有数据 展示在页面 
					   if(data.length){
			    			$('#dictionaryList').treegrid('loadData',{total:data.length,rows:data});
			    						    			
		    			}else{
		    				$('#dictionaryList').treegrid('loadData',{total:0,rows:[]});
		    			} 
					   $("#main_dict_list").layout("collapse","east");
				  },
				});
			
		}else{
			reloadTable1();
		}
	}
	//清空功能
	function clearType(){
		$('#typegroupcode').val('');
		$('#typegroupname').val('');
	}

		//表单初始化操作
		$(function() {
			
			$('#dictionaryList').treegrid({
				idField : 'id',
				treeField : 'text',
				animate : true,
				/* title : '数据字典表',  */
				url : 'systemController.do?typegroupGrid1',
				
				//自动适应
				//fitColumns : true,
				columns : [ [
						{
							field : 'id',
							title : '编号',
							hidden : true,
						},
						{
							field : 'text',
							title : '字典名称',
							width : 220,
							align:'left'
						},
						 {
							field : 'code',
							title : '字典编码',
							width : 150,
							align:'left'
						},
						
						{
							field : 'remarks',
							title : '备注',
							width : 150,
							
						},
						{
							field : 'typegroupcode',
							title : '排序编号',
							width : 150,
							
							
						},
						{
							field : 'null',
							width:'150',
							
							title : '操作',
							formatter : function(
									value,
									rec,
									index) {
								
								var href = '';
								href += "[<a href='#' onclick=delObj1('systemController.do?doDel&id="
										+ rec.id
										+ "','dictionaryList')>";
								href += "删除</a>]";
								return href;
							}
						} ] ],
						
						
						
				//加载成功事件
				  onLoadSuccess : function(data) {
					$("#dictionaryList")
							.treegrid(
									"clearSelections");
					//成功加载后  树形  不展开
					$("#dictionaryList")
					.treegrid("collapseAll");
				},
				//点击事件
				   onClickRow : function(
						rowData) {
					//判断是否是子节点
				 	if (rowData.children == undefined)
						//是子节点
						detail(1);
					else
						//不是子节点
						detail(0); 
		
				}  
				
				
			});
		
	});
		
		// 显示 或 隐藏  东列表
		function detail(a) {
			if (a==1){
				//显示 列表
				$("#main_dict_list").layout("expand","east");
			//行数据组
			var rowsData = $('#dictionaryList').datagrid('getSelections');
			
			//列表 从后台 获取 数据 
			$('#itemListpanel').panel("refresh", "systemController.do?typeList1&typegroupId=" +rowsData[0].id);
				
			}else{
				//隐藏列表
				$("#main_dict_list").layout("collapse","east");//隐藏列表
			}
				
		
		}
		
		
		
		//导入
		function ImportXls() {
			openuploadwin('Excel导入', 'dictionaryTypeController.do?upload',
					"systemRegionList");
		}

		//导出
		function ExportXls() {
			JeecgExcelExport("dictionaryTypeController.do?exportXls",
					"systemRegionList");
		}

		//模板下载
		function ExportXlsByT() {
			JeecgExcelExport("dictionaryTypeController.do?exportXlsByT",
					"systemRegionList");
		}
	</script>
</body>
</html>