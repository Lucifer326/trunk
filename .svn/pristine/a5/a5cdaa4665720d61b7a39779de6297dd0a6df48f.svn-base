<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>多选</title>
<h:base type="jquery-hp,easyui,hplus,hplisttools"></h:base>
<link href="plug-in/tag/propertyStyle.css" rel="stylesheet" type="text/css" />
</head>
	<body style="overflow-y: auto">
				<!-- 属性设置  -->
				<div >
					<input type="hidden" id="historyId">
					<div >
						 <t:formvalid layout="div" dialog="false" formid="formobj" beforeSubmit="onBeforeSub" tiptype="1">
							
							<input class="hbtnbox" type="submit" id="saveAs" name= "saveAs" value="另存为" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="save" name= "save" value="保存" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="preview" name= "preview" value="预览" onclick="defineType(this);" style="display: none;">
							<table cellpadding="5">
								<tr>
									<td><span class="required">*</span>名称:</td>
									<td><input class="easyui-textbox" id="field" type="text" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头,至少2位" name="field" onblur="setProperty(this);" datatype="*"/></td>
									<td>显示格式:</td>
									<td>
										<input  type="radio" id="displayFormat_h" name="displayFormat" value = "displayFormat_h" checked="checked" onchange="setProperty(this);" >横排
										<input  type="radio" id="displayFormat_s" name="displayFormat" value = "displayFormat_s"  onchange="setProperty(this);" >竖排
									</td>
								</tr>
								<tr>
									<td>是否显示lable</td>
									<td class="hasLabel" colspan="3">
										<input  type="radio" id="hasLabel_y" name="hasLabel" value = "true" onchange="setProperty(this),isShowLabelValue(this.name);" >是
										<input  type="radio" id="hasLabel_n" name="hasLabel" value = "false" checked="checked" onchange="setProperty(this),isShowLabelValue(this.name);" >否
									</td>
									<td class="label_value" style="display: none;">Lable值</td>
									<td class="label_value" style="display: none;"><input class="easyui-textbox" id="lable" type="text" name="lable" onblur="setProperty(this);"/></td>
								</tr>
								<tr>
									<td>多选框位置:</td>
									<td>
										<input  type="radio" id="radioPosition_q" name="radioPosition" value = "radioPosition_q" checked="checked" onchange="setProperty(this);" >前
										<input  type="radio" id="radioPosition_h" name="radioPosition" value = "radioPosition_h"  onchange="setProperty(this);" >后
									</td>
								</tr>
								<tr>
									<td>DIV样式</td>
									<td>
										<input class="easyui-textbox" id="divClass" type="text" name="divClass" onblur="setProperty(this);"/>
									</td>
									<td>LABEL样式</td>
									<td>
										<input class="easyui-textbox" id="labelClass" type="text" name="labelClass" onblur="setProperty(this);"/>
									</td>
								</tr>
								<tr>
									<td>请选择数据源:</td>
									<td>
										<select id="dataSource" name="dataSource" onchange="chooseDataSource(this.id);">
											  <option value ="type_code">数据字典类型</option>
											  <option value ="type_table">自定义字典表</option>
										</select>
									</td>
									<td class="code"><span class="required">*</span>数据字典类型:</td>
									<td class="code">
										
										<h:comboTree url="controlGeneratorService.getTSTypegroups" width="200" value="" onSelect="typegroupcodeSetPor" name="typeGroupCode" id="typeGroupCode"></h:comboTree>
									</td>
								</tr>
								<tr class="dict" style="display: none;">
									<td><span class="required">*</span>自定义字典表:</td>
									<td>
										<input class="easyui-textbox" id="dictTable" type="text" datatype="*2-15" ignore="ignore" name="dictTable" onblur="setProperty(this);">
									</td>
									<td><span class="required">*</span>字典的编码值:</td>
									<td>
										<input class="easyui-textbox" id="dictField" type="text" datatype="*2-15" ignore="ignore" name="dictField" onblur="setProperty(this);">
									</td>
								</tr>
								<tr class="dict" style="display: none;">
									<td><span class="required">*</span>字典的显示值:</td>
									<td>
										<input class="easyui-textbox" id="dictText" type="text" datatype="*2-15" ignore="ignore" name="dictText" onblur="setProperty(this);">
									</td>
								</tr>
								
							</table>
						</t:formvalid>
						
					</div>
				</div>
	</body>
	<script src="webpage/comdesign/controlGenerator.js"></script>
	<script type="text/javascript">
	
	//控件类型
	var typeId = '${typeId}';
	
	//数据源 选择字典类型code时 更新源码的方法
	function typegroupcodeSetPor(node) {
		
		function c(name,value){
    		this.name=name;
    		this.value=value;
    	}
    	setProperty(new c("typeGroupCode",node.id));
		
    	$("#typeGroupCode").val(node.id);
	}
	//选择数据源
	function chooseDataSource(id){
		
		var value = $("#"+id+"").val();
		
		if(value == "type_code"){
			
			$(".dict").css("display","none");
			$(".code").css("display","");
			
			$("#dictTable").val("").blur();
			$("#dictField").val("").blur();
			$("#dictText").val("").blur();
		
			$("#dictTable").attr("ignore","ignore");
			$("#dictField").attr("ignore","ignore");
			$("#dictText").attr("ignore","ignore");
			
			
		}else if(value == "type_table"){
			
			function c(name,value){
	    		this.name=name;
	    		this.value=value;
	    	}
	    	setProperty(new c("typeGroupCode",""));
			
			$(".code").css("display","none");
			$(".dict").css("display","");
			
			$("#dictTable").removeAttr("ignore");
			$("#dictField").removeAttr("ignore");
			$("#dictText").removeAttr("ignore");
		}
	}
	//控件标签参数
	var paramTagType = "dictCheckbox";

  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		
  		//取出历史数据中的下拉树的值，回显到下拉树上
  		 var value = "";
  		
  		for(var i=0;i<controlArray.length;i++){
			if(controlArray[i].id == "typeGroupCode"){//从数组中取出id为typeGroupCode的 获取值
				value = controlArray[i].value;
			}
		}
  		 if(value !=""){
  			
  		 $("#typeGroupCode").combotree("setValue",value); 
  		}
  
  		//对是否显示label值的JS级联方法
  		isShowLabelValue("hasLabel");
  		
  		var value = $("#dictTable").val();
  		
  		if(value != ""){
  			
  			$("#dataSource").val("type_table");
  			
  			$(".code").css("display","none");
			$(".dict").css("display","");
			$("#typeGroupCode").val("");
  		}else{
  			
  			$("#dataSource").val("type_code");
  			
  			$(".dict").css("display","none");
			$(".code").css("display","");
			
			$("#dictTable").val("");
			$("#dictField").val("");
			$("#dictText").val("");
  		}
  	}
  	
  	//页面加载时设置默认属性
  	 function initProperties(){
  		var b1 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
  		var b2 = new Control($("#displayFormat_h").attr("name"),$("#displayFormat_h").attr("value"));
  		var b3 = new Control($("#radioPosition_q").attr("name"),$("#radioPosition_q").attr("value"));
  		
		addToArray(b1);
		addToArray(b2);
		addToArray(b3);
		
		generateSourceCode(paramTagType);
  	}
  		initProperties();
  		isShowLabelValue("hasLabel");
	</script>

</html>

