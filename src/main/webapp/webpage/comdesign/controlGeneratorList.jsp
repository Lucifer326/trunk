<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控件生成器</title>
<h:base type="jquery-hp,easyui,hplus,hplisttools"></h:base>
<link href="plug-in/tag/main.css" rel="stylesheet" type="text/css" />
<script src="webpage/jeecg/image/ajaxfileupload.js"></script>
<script src="plug-in/My97DatePicker/WdatePicker.js"></script>
</head>
<body style="overflow-y: auto">
	<div>
		<!--左侧标题  -->
		<div class="header">
			<p class="title" style="font-size: 16px;font-weight:bold;">控件生成器</p>
		</div>
		<!--控件选择  -->
		<div class="container">
		
			<!--1弹窗树  -->
			<a class="tag t1" href="controlGeneratorController.do?controlPropertyTarget&typeId=popTreeTag" target="controlProperty" onclick="historyDate('popTreeTag',this);">
			</a>
			<!--2图片上传  -->
			<a class="tag t2" href="controlGeneratorController.do?controlPropertyTarget&typeId=imgUploadTag" target="controlProperty" onclick="historyDate('imgUploadTag',this);">
			</a>
			<!--3下拉框  -->
			<a class="tag t3" href="controlGeneratorController.do?controlPropertyTarget&typeId=dictSelectTag" target="controlProperty" onclick="historyDate('dictSelectTag',this);">
			</a>
			<!--4附件上传  -->
			<a class="tag t4" href="controlGeneratorController.do?controlPropertyTarget&typeId=webUploaderTag" target="controlProperty" onclick="historyDate('webUploaderTag',this);">
			</a>
			<!--5下拉树  -->
			<a class="tag t5" href="controlGeneratorController.do?controlPropertyTarget&typeId=comboTreeTag" target="controlProperty" onclick="historyDate('comboTreeTag',this);">
			</a>
			<!--6数据表格  -->
			<a class="tag t6" href="controlGeneratorController.do?controlPropertyTarget&typeId=datalistgridTag" target="controlProperty" onclick="historyDate('datalistgridTag',this);">
			</a>
			<!--7单选按钮 -->
			<a class="tag t7" href="controlGeneratorController.do?controlPropertyTarget&typeId=dictRadioTag" target="controlProperty" onclick="historyDate('dictRadioTag',this);">
			</a>
			<!--8多选按钮  -->
			<a class="tag t8" href="controlGeneratorController.do?controlPropertyTarget&typeId=dictCheckboxTag" target="controlProperty" onclick="historyDate('dictCheckboxTag',this);">
			</a>
			<!--9时间选择  -->
			<a class="tag t9" href="controlGeneratorController.do?controlPropertyTarget&typeId=datePickerTag" target="controlProperty" onclick="historyDate('datePickerTag',this);">
			</a>
			<!--10多功能按钮  -->
			<a class="tag t10" href="controlGeneratorController.do?controlPropertyTarget&typeId=multiButtonTarget" target="controlProperty" onclick="historyDate('multiButtonTarget',this);">
			</a>
		</div>
		<div class="header">
			<span style="font-size: 16px;font-weight:bold;">控件设置</span>
		</div>
	<!--内容显示区  -->
	<div>
			<div id ="d" title="控件设置" style="padding: 10px">
				
				<!--左侧  -->
				<div class="contentleft">
						
						<!--弹窗树控件设置页面  -->
						<h5 >效果显示</h5>
						
						<div class="innerleft" id="show">
						
						<div id="afterPreview" class="kongjian" style=" display: none" ></div>
						</div>
						
						<!--源码显示  -->
						<div class="source">
							<h5 class="title">源码显示</h5><h5 style="float:right;margin-right:40px"><button id="preview_parent" name= "preview_parent" onclick="preview()" >预览</button></h5>
							<textarea  id = "sourceCode"  readonly="readonly" style="float:left;">
							</textarea>
						</div>
				</div>
				<!--属性设置  -->
				<div  class="contentright" style=" background:none">
				<div style="height:60px; margin:0 auto;">
				 <h5 style="float:left; width:500px;height:22px;">属性设置 </h5>
				</div>
					<iframe  name="controlProperty" id="childframe"  frameborder="no" style=" background:#fff;border:1px solid #ddd"></iframe>
				</div>
			<!--历史数据显示  -->
				<div class="history" style="background:#eee; height:580px;">
						<h5 style="height:10px;">历史数据显示</h5>
						<div id="historyList" style="background:#eee; height:580px;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	//点击预览
	function preview(){
		document.getElementById("childframe").contentWindow.document.getElementById('preview').click();
	}
	//点击保存
	function save(){		
		document.getElementById("childframe").contentWindow.save(); 
	}
	//点击另存
	function saveAs(){		
		document.getElementById("childframe").contentWindow.saveAs();
	}
	//加载历史数据
	function historyDate(typeId,o){
		$("#show").css("display","block");
		$("#afterPreview").css("display","none");
		if(o != null){
		//调用点击控件图片标签切换方法
		labelSwitching(o);
		}
		//加载历史数据
		$('#historyList').datagrid({
					idField : 'id',
					title : '',
					url : 'controlGeneratorController.do?datagrid&typeId='+typeId,//&field=id,name,updateDate
					loadMsg : '数据加载中...',
					pageSize : 10,
					pagination : true,
					pageList : [ 10, 20, 30 ],
					sortOrder : 'asc',
					rownumbers : true,
					singleSelect : true,

					columns : [ [
							{
								field : 'id',
								title : '编号',
								hidden : true,
							},
							{
								field : 'name',
								title : '名称',
								width : '180',
							},
							{
								field : 'updateDate',
								title : '修改时间',
								width : '180',
								formatter:function(value,row,index){  
			                         var unixTimestamp = new Date(value);  
			                         return unixTimestamp.toLocaleString();  
			                         } 
							},
							{
								field : 'opt',
								title : '操作',
								width : '',
								formatter : function(value, rec, index) {
									
									if (!rec.id) {
										return '';
									}
									var href = '';
									href += "[<a href='#' onclick=delObj('controlGeneratorController.do?del&id="
											+ rec.id + "','historyList')>";
									href += "删除</a>]";
									return href;
								}
							} ] ],
					onLoadSuccess : function(data) {
						$("#historyList").datagrid("clearSelections");
					},
					onClickRow : function(rowIndex, rowData) {
						rowid = rowData.id;
						gridname = 'historyList';
						
						//选中历史数据后回显源码和属性值
						historyPreview(rowid);
					}
				});
		
	}
	//点击控件图片标签切换
	function labelSwitching(o){
		$(".tag").each(function(index,element){
			   index++;
			   $(this).css("background","url(plug-in/tag/image/anniu-"+index+".png)"); 
			   
			  });
		var cturl=$(o).css("background-image")
		var reg=/.png/g;
		var reg1=/anniu-/g;
		cturl2=cturl.replace(reg1,"xiaoguozhanshi");
		cturl=cturl.replace(reg,"-2.png");
		$(o).css("background",cturl);
		$(".innerleft").css("background",cturl2);

	}
	//删除
	function delObj(url){
		$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		url : url,// 请求的action路径
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			var d = $.parseJSON(data);
    			   			
    			if (d.success) {
    			   tip(d.msg); 			
    			   $("#historyList").datagrid('reload');
    			} else {
    				//
    				tip(d.msg);
    			}
    		}
    	});
	}

	//选中历史数据后回显源码和属性值
	function historyPreview(id){
		$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		url : 'controlGeneratorController.do?historyData',// 请求的action路径
    		dataType: "json",
    		data: {"id":id},	
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			var obj = data.obj;
    			var pro = JSON.parse(obj.property);
    			
    			document.getElementById("childframe").contentWindow.showHistory(pro,id);
    		}
    	});
	}
</script>
</html>


