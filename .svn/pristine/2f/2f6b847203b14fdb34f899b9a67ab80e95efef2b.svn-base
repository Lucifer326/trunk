<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>下拉框</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
									
									<td><span class="required">*</span>表单id:</td>
									<td><input class="easyui-textbox" id="id" type="text" name="id" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头" onblur="setProperty(this);"></input></td>
			
									<td><span class="required">*</span>表单name:</td>
									<td><input class="easyui-textbox" id="field" type="text" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头" name="field" onblur="setProperty(this);"></input></td>
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
										<h:comboTree url="controlGeneratorService.getTSTypegroups" width="200" value="" onSelect="typegroupcodeSetPor" name="typeGroupCode1" id="typeGroupCode"></h:comboTree>
									</td>
								</tr>
								<tr class="dict" style="display: none;" >
									<td><span class="required">*</span>自定义字典表:</td>
									<td>
										<input class="easyui-textbox" id="dictTable" type="text" name="dictTable" onblur="setProperty(this);">
									</td>
									<td><span class="required">*</span>字典的编码值:</td>
									<td>
										<input class="easyui-textbox" id="dictField" type="text" name="dictField" onblur="setProperty(this);">
									</td>
								</tr>
								<tr class="dict" style="display: none;">
									<td><span class="required">*</span>字典的显示值:</td>
									<td>
										<input class="easyui-textbox" id="dictText" type="text" name="dictText" onblur="setProperty(this);">
									</td>
								</tr>
								
								<tr>
									
								</tr>
								<tr>
									<td>是否显示lable:</td>
									<td class="hasLabel" colspan="3">
										<input  type="radio" id="hasLabel_y" name="hasLabel" value = "true" onchange="setProperty(this),isShowLabelValue(this.name);" >是
										<input  type="radio" id="hasLabel_n" name="hasLabel" value = "false" checked="checked" onchange="setProperty(this),isShowLabelValue(this.name);" >否
									</td>
									
									<td class="label_value" style="display: none;">Lable值:</td>
									<td class="label_value" style="display: none;"><input class="easyui-textbox" id="title" type="text" name="title" onblur="setProperty(this);"/></td>
									
								</tr>
								<tr>
									<td>DIV样式样式:</td>
									<td><input class="easyui-textbox" id="divClass" type="text" name="divClass" onblur="setProperty(this);"></input></td>
									
									<td>Label样式:</td>
									<td><input class="easyui-textbox" id="labelClass" type="text" name="labelClass" onblur="setProperty(this);"></input></td>
								</tr>
								
								
								<tr>
									<td>控件类型:</td>
									<td>
										<input type="radio" id="controlType_s" name="type" value = "select" checked="checked" onchange="setProperty(this);">select
										<input type="radio" id="controlType_c" name="type" value = "checkbox" onchange="setProperty(this);">checkbox
										<input type="radio" id="controlType_r" name="type" value = "radio"  onchange="setProperty(this);">radio
									</td>
									<td>是否自动加载数据:</td>
									<td>
										<input  type="radio" id="autoLoadData_y" name="autoLoadData" value = "true" checked="checked" onchange="setProperty(this);" >是
										<input  type="radio" id="autoLoadData_n" name="autoLoadData" value = "false" onchange="setProperty(this);" >否
										
									</td>
								</tr>
								
								
								<tr>
									<td>onchange事件:</td>
									<td><input class="easyui-textbox" id="onchange" type="text" name="onchange" onblur="setProperty(this);"></input></td>
									
									<td>查询条件:</td>
									<td><input class="easyui-textbox" id="dictCondition" type="text" name="dictCondition" onblur="setProperty(this);"></input></td>
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
	
	//声明控件标签参数
	var paramTagType = "dictSelect";
	
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
		
		if(value == "type_code"){//如果value为type_code，则数据源为字典类型
			
			$(".dict").css("display","none");
			$(".code").css("display","");
			
			//清空自定义表单的值然后出发失焦事件。
			$("#dictTable").val("").blur();
			$("#dictField").val("").blur();
			$("#dictText").val("").blur();
		
			
		}else if(value == "type_table"){//如果value为type_table，则数据源为自定义表单
			
			//清空下拉树的字典值
			$(".combo-text").val("");
			$(".combo-value").val("");
			
			function d(name,value){
	    		this.name=name;
	    		this.value=value;
	    	}
			
			setProperty(new d("typeGroupCode",""));
			
			$(".code").css("display","none");
			$(".dict").css("display","");
			
		}
	}

  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		var tcodeval="";
  		//对是否显示label值的JS级联方法
  		isShowLabelValue("hasLabel");
  		
  		var value = $("#dictTable").val();//获取自定义表 的 值进行判断
  		
  		if(value != ""){//如果 自定义表有值 则数据源为自定义表单类型，
  			
  			$("#dataSource").val("type_table");
  			$(".code").css("display","none");
			$(".dict").css("display","");
			
  		}else{//否则则为数据字典类型
  			
  			$("#dataSource").val("type_code");
  			
  			$(".dict").css("display","none");
			$(".code").css("display","");
			
					
			for(var p in controlArray){
				if(controlArray[p].id=="typeGroupCode"){
					tcodeval = controlArray[p].value;
					$(".combo-text").val(tcodeval);
				}
				
				
				console.log(controlArray[p].value);
			}
			
			
			
			
			$("#dictTable").val("");
			$("#dictField").val("");
			$("#dictText").val("");
  		}
  	}
  	
  	//页面加载时设置默认属性
  	 function initProperties(){
  		
  		var b1 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
  		var b2 = new Control($("#controlType_s").attr("name"),$("#controlType_s").attr("value"));	
  		var b3 = new Control($("#autoLoadData_y").attr("name"),$("#autoLoadData_y").attr("value"));
  		
		addToArray(b1);
		addToArray(b2);
		addToArray(b3);

		generateSourceCode(paramTagType);
		
  	}
  	initProperties();
  	isShowLabelValue("hasLabel");
	</script>

</html>
