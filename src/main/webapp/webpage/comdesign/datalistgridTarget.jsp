<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>数据表格</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link href="plug-in/tag/propertyStyle.css" rel="stylesheet" type="text/css"/>

</head>
<body style="overflow:hidden;">
	<!-- 属性设置  -->
	<div>
		<input type="hidden" id="historyId">
		<div>
			<t:formvalid layout="div" dialog="false" formid="formobj"
				beforeSubmit="onBeforeSub_data" tiptype="1">
				<input class="hbtnbox" type="submit" id="saveAs" name="saveAs"
					value="另存为" onclick="defineType(this);">
				<input class="hbtnbox" type="submit" id="save" name="save"
					value="保存" onclick="defineType(this);">
				<input class="hbtnbox" type="submit" id="preview" name="preview"
					value="预览" onclick="defineType(this);" style="display: none;">
				<div style="margin: 20px 0 10px 0;"></div>
				<div class="easyui-accordion" style="width: 660px; height: 490px;">
					<div title="表单设置" style="overflow: auto; padding: 10px;">
						<p>表单设置</p>
						<table cellpadding="5">
							<tr>
								<td><span style="color: red">*</span>表名称:</td>
								<td><input class="easyui-textbox" id="tableName"
									type="text" name="tableName" onblur="showColums(this);"></input></td>
								<td><span class="required">*</span>表格唯一标示:</td>
								<td><input class="easyui-textbox" id="name" type="text"
									name="name" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头,至少2位" onblur="setProperty(this,1);"></input></td>
							</tr>
							<tr>
								<td>表格标题:</td>
								<td><input class="easyui-textbox" id="title" type="text"
									name="title" onblur="setProperty(this,1);"></input></td>
							</tr>
							<tr>
								<td>复选框类型:</td>
								<td><input type="radio" id="checkbox_t" name="checkbox"
									value="true" checked="checked" onchange="setProperty(this,1);">多选
									<input type="radio" id="checkbox_f" name="checkbox"
									value="false" onchange="setProperty(this,1);">单选</td>
								<td>是否显示分页条:</td>
								<td><input type="radio" id="pagination_t" name="pagination"
									value="true" checked="checked" onchange="setProperty(this,1);">是
									<input type="radio" id="pagination_f" name="pagination"
									value="false" onchange="setProperty(this,1);">否</td>
							</tr>
							<tr>
								<td>表格宽度:</td>
								<td><input class="easyui-textbox" id="width" type="text"
									name="width" value="400" onblur="setProperty(this,1);"></input></td>
								<td>表格高度:</td>
								<td><input class="easyui-textbox" id="height" type="text"
									name="height" value="500" onblur="setProperty(this,1);"></input></td>
							</tr>
							<tr>
							</tr>
						</table>
					</div>
					<div id="showColumns" title="列设置" style="padding: 10px;">
						<iframe name="colum_iframe" id="colum_iframe"
							style="background: #fff; border: 1px solid #ddd; height:100%; width:100%; overflow: auto;"></iframe>
					</div>
				</div>
			</t:formvalid>
		</div>
	</div>

	<a id="tag" target="colum_iframe" style="display: hidden;"></a>
</body>
<script type="text/javascript">
	
	//控件类型
	var typeId = '${typeId}';
	//声明控件标签参数
	var paramTagType = "datalistgrid";
	//拼接源码
	var datalistgridsource = "";
	//声明一个数组，用来存放各个属性对象，对象不可重复
	var controlArray = new Array();
	var columcontrolArray = new Array();
	
	var b1 = new Array();
	var b2 = new Array();
	
	var c1 = new Array();
	var c2 = new Array();
	
	//实例化一个控件对象 属性为 id value
	function Control(id,value){
		this.id=id;
		this.value=value;
	}
	//实例化一个列对象 属性为 check,field,title,width
	function columControl(rec,id,value){
		
		this.rec=rec;
		this.id=id;
		this.value=value;
	}
	/**
	 * 表单验证通过后 单表单条件下的多提交按钮 选择 
	 * 按钮类型 ：保存 另存，预览
	 */
	var checkButton = "";

		function defineType(o){
			checkButton = o.id;
		}

	
	function showColums(o) {

		var tablename = o.value;
		$("#tag").removeAttr("href").attr("href",
				"datalistgridController.do?setColumns&tablename=" + tablename);
		$("#tag")[0].click();

		setTimeout(function() {
			setProperty(o,1)
		}, 1000);
	}
	//拼接数据表格和列标签源码
	function commongenerateSourceCode() {
		
		datalistgridsource = "";
		var datagridcode = generateSourceCode("datalistgrid");
		var columcode = document.getElementById("colum_iframe").contentWindow
				.columgenerateSourceCode("colum");

		datalistgridsource = datagridcode + columcode + "</h\:datalistgrid>";
		parent.$("#sourceCode").html(datalistgridsource);
	}
	//给colum列 属性赋值
	function setColumValue(id,val,r) {
		var i = "#"+id;
		var j = "#check_"+r;
		$("#colum_iframe").contents().find(i).val(val); 
		$("#colum_iframe").contents().find(j).attr("checked","checked").click();
	}
	//回显时给列各属性赋值
	function showHistoryCallback(){
		var r="";
		for(var i = 0;i<columcontrolArray.length;i++){
			r = columcontrolArray[i].rec;
			
			var columobj = columcontrolArray[i];

			for (c in columobj){  

				if(c =="field"||c =="rec"){
					continue;
				}
				setColumValue(c+"_"+r,columobj[c],r);
			  }  
		}
	}

//生成源码的方法
function generateSourceCode(paramTagType){
		source = "<h\:" + paramTagType + " ";
		property = "";
		for(var i=0;i<controlArray.length;i++){
			var tempV = controlArray[i].value + "";
			if(tempV != ""){
				source = source + controlArray[i].id +"= '"+ controlArray[i].value+"' ";
				property = property + controlArray[i].id+"=" + controlArray[i].value+"&";
			}
		}
			source = source + ">";
			return source;
}

//向数组中添加对象
function addToArray(b){
	var l = controlArray.length;
	if(l==0) {
		controlArray.push(b);
		return;
	}
	//循环判断数组中是否存在对象，对已存在的对象不进行添加
	for(var i=0;i<controlArray.length;i++){
		if(controlArray[i].id == b.id){//已存在
			controlArray.splice(i,1);
			break;
		}
	}
	controlArray.push(b);
}
//向数组中添加对象
function addToColumArray(b){
	var l = columcontrolArray.length;
	if(l==0) {
		columcontrolArray.push(b);
		return;
	}
	//循环判断数组中是否存在对象，对已存在的对象不进行添加
	for(var i=0;i<columcontrolArray.length;i++){
		if(columcontrolArray[i].id == b.id){//已存在
			columcontrolArray.splice(i,1);
			break;
		}
	}
	columcontrolArray.push(b);
}
	
//属性页面各属性触发事件后   给控件对象属性赋值 
function setProperty(o){
		
	var b = new Control(o.name,o.value);
	addToArray(b);
	
	//调用生成源码的方法
	//initgenerateSourceCode(paramTagType);
	commongenerateSourceCode();
}

	//回显历史记录
	function showHistory(obj,id){
		
		//清空数组
		controlArray.splice(0,controlArray.length);
		//获取历史数据id
		$("#historyId").val(id);
		//重置表单
		document.getElementById("formobj").reset();
		
		//获取历史数据中的表名
		var tableName = obj.tableName;
		//给tableName 元素 赋值 触发 聚焦 失焦 方法 把表对应的列加载出来
		$("#tableName").val(tableName).focus().blur();

		//调用 拼接源码方法
		//调用setValue方法 回显数据到 对应的元素上
		
		setTimeout(function() {
			// 遍历对象所有属性 
			for ( var p in obj ){
				// 方法 
				if ( typeof ( obj [p]) == " function " ){ 
				} else {
					
					if(p == "columnList"){//给列数组赋值
						
						columcontrolArray = document.getElementById("colum_iframe").contentWindow.controlArray;
						columcontrolArray.splice(0,columcontrolArray.length);
						
						for(var i=0; i<obj[p].length;i++){
							for(var c in obj[p][i]){
								b1.push(c);
								b2.push(obj[p][i][c]);
							}
							
							c1 = b1.slice(0);
							c2 = b2.slice(0);
							var b = new columControl(obj[p][i].rec,c1,c2);
							
							columcontrolArray.push(b);
							
							console.log(columcontrolArray);
							b1.splice(0,b1.length);
							b2.splice(0,b2.length);
								
						} 
					}else{						
						// p 为属性名称，obj[p]为对应属性的值 
						setValue(p,obj[p]);
						var b = new Control(p,obj[p]);
						addToArray(b);
					}
				}
			}
			//属性页面回调属性函数
			showHistoryCallback();
			//调用生成源码的方法
			commongenerateSourceCode();
		}, 1000);
	}
	
	//给datalistgrid 属性赋值
	function setValue(name,value) {
		var v = document.getElementsByName(name);
		if(v.length == 0 ){
			 return;
		}
		
		//若只找到一个元素，表示是input type=text，直接赋值
		if(v.length == 1) {
			v[0].value = value;
			return;
		}
		//若找到多个元素，表示是input type=radio checkbox ，直接赋值
		for(var i=0;i<v.length;i++) {
			var v1 = v[i].value + "";
			var v2 = value + "";
			
			if(v1 == v2) {
				v[i].checked = true;
			}
		}
	}

//点击预览后调用的方法
function preview_datalistgrid(){
	
	console.log("columcontrolArray："+columcontrolArray);
	
	
	parent.$("#afterPreview").css("display","block");//隐藏预览前区域
	parent.$("#beforePreview").css("display","none");//显示预览后区域
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		data:{"columdatalist":JSON.stringify(document.getElementById("colum_iframe").contentWindow.controlArray)},
		url : paramTagType + "Controller.do?"+paramTagType+"SourceCode&"+property,// 请求的action路径
		dataType: "json",   		 
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			
			if (data.success) {
				parent.$("#afterPreview").html(data.msg);
			} else {
				tip(data.msg);
			}
		}
	}); 
	
}
//点击保存按钮触发函数
function datalistgridsave(){
	var hId = $("#historyId").val();
	if(hId == ""){
			
		p = $.dialog({content: 'url:controlGeneratorController.do?addControlDesign', zIndex: 9999, title: '控件新增页面', 
				lock: true, width: '300px', height: '100px', opacity: 0.4,
				button: [
		   {name: '确定', callback: saveControlDesign, focus: true},
		   {name: '取消', callback: function (){}}
	   		]}).zindex();
		
	}else{
		$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		url : paramTagType+'Controller.do?save&source='+datalistgridsource+"&typeId="+typeId+"&historyId=" + hId
    		+"&columdatalist="+JSON.stringify(document.getElementById("colum_iframe").contentWindow.controlArray),// 请求的action路径
    		
    		dataType: "json",
    		data: property,	
    		 
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			
    			if (data.success) {
    				$("#historyId").val(data.msg);
    				tip("保存成功");
    				//调用父窗口的historyDate()方法，刷新历史数据
    				parent.historyDate(typeId,null);
    			} else {
    				tip(data.msg);
    			}
    		}
    	});	
	}
}

//点击另存为按钮触发函数
function datalistgridsaveAs(){
	$("#historyId").val("");
	p = $.dialog({content: 'url:controlGeneratorController.do?addControlDesign', zIndex: 9999, title: '控件新增页面', 
		lock: true, width: '300px', height: '100px', opacity: 0.4,
		button: [
   {name: '确定', callback: saveControlDesign, focus: true},
   {name: '取消', callback: function (){}}
]}).zindex();
		
	
}
//在弹框中点击确定触发函数
function saveControlDesign(){
	//获取控件名称
	var controlName = p.content.document.getElementById("controlName").value;
	
	var hId = $("#historyId").val();
	
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : paramTagType+'Controller.do?save&source='+datalistgridsource+"&typeId="+typeId+"&controlName="+controlName+"&historyId=" + hId
				+"&columdatalist="+JSON.stringify(document.getElementById("colum_iframe").contentWindow.controlArray),// 请求的action路径   		
		dataType: "json",    		
		data:property,
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			
			if (data.success) {
				$("#historyId").val(data.msg);
				tip("保存成功");
				//调用父窗口的historyDate()方法，刷新历史数据
				parent.historyDate(typeId,null);
			} else {
				tip(data.msg);
			}
		}
	});
}

//表单提交前处理的函数
function onBeforeSub_data() {
	if (checkButton == "save") {
		datalistgridsave();
	} else if (checkButton == "saveAs") {
		datalistgridsaveAs();
	} else if (checkButton == "preview") {
		preview_datalistgrid();
	}
	return false;

}
//页面加载时设置默认属性
function initProperties() {
	parent.$("#sourceCode").html("");
	var b1 = new Control($("#checkbox_t").attr("name"), $("#checkbox_t")
			.attr("value"));
	var b2 = new Control($("#pagination_t").attr("name"),
			$("#pagination_t").attr("value"));
	var b3 = new Control($("#width").attr("name"), $("#width")
			.attr("value"));
	var b4 = new Control($("#height").attr("name"), $("#height").attr(
			"value"));

	addToArray(b1);
	addToArray(b2);
	addToArray(b3);
	addToArray(b4);
	
	commongenerateSourceCode();
}
initProperties();

</script>
</html>

