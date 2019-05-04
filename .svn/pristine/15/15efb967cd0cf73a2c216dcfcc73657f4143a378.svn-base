<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日期控件</title>
<h:base type="jquery-hp,easyui,hplus,hplisttools"></h:base>
<link href="plug-in/tag/propertyStyle.css" rel="stylesheet" type="text/css" /> 
<script src="plug-in/My97DatePicker/WdatePicker.js"></script> 
</head>
	<body style="overflow-y: auto">
				<!-- 属性设置  -->
				<div >
					<input type="hidden" id="historyId">
					<div >
					    <t:formvalid layout="div" dialog="false" formid="formobj" beforeSubmit="onBeforeSub()" tiptype="1">		
							<input class="hbtnbox" type="submit" id="saveAs" name= "saveAs" value="另存为" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="save" name= "save" value="保存" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="preview" name= "preview" value="预览" onclick="defineType(this);" style="display: none;">
							<table cellpadding="5">
								<tr>
									<td><span class="required">*</span>名称:</td>
									<td>
										<input class="easyui-textbox" id="name" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头,至少2位" type="text" name="name" onblur="setProperty(this);" >
									</td>
									<td>显示格式:</td>
									<td>
										<select id="dateFmt" name="dateFmt" onchange="setProperty(this),relateDate(this.value);">
											  <option value ="yyyy年">yyyy年</option>
											  <option value ="yyyy">yyyy</option>
											  <option value ="yyyy年MM月">yyyy年MM月</option>
											  <option value ="yy-MM">yy-MM</option>
											  <option value="yyyyMMdd">yyyyMMdd</option>
											  <option value="yyyy-MM-dd">yyyy-MM-dd</option>
											  <option value="yyyy-MM-dd HH:mm:ss" selected="selected">yyyy-MM-dd HH:mm:ss</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>是否只读:</td>
									<td>
										<input  type="radio" id="readOnly_y" name="readOnly" value = "true" checked="checked" onchange="setProperty(this);">是
										<input  type="radio" id="readOnly_n" name="readOnly" value = "false" onchange="setProperty(this);">否
									</td>
									<td>是否显示清空按钮:</td>
									<td>
										<input  type="radio" id="showClear_y" name="showClear" value = "true" checked="checked" onchange="setProperty(this);">是
										<input  type="radio" id="showClear_n" name="showClear" value = "false" onchange="setProperty(this);">否
									</td>
								</tr>
								<tr>
									<td>是否显示lable:</td>
									<td class="hasLabel" colspan="3">
										<input  type="radio" id="hasLabel_y" name="hasLabel" value = "true" onchange="setProperty(this),isShowLabelValue(this.name);" >是
										<input  type="radio" id="hasLabel_n" name="hasLabel" value = "false" checked="checked" onchange="setProperty(this),isShowLabelValue(this.name);" >否
									</td>
									
									<td class="label_value" style="display: none;">Lable值:</td>
									<td class="label_value" style="display: none;">
										<input class="easyui-textbox" id="lable" type="text" name="lable" onblur="setProperty(this);"/>
									</td>
									
								</tr>
								<tr>
									<td>高度:</td>
									<td>
									<input id="height" type="text" name="height" datatype="n2-3" value="30" onblur="setProperty(this);" />
									</td>
									<td>宽度:</td>
									<td>
									<input id="width" type="text" name="width" datatype="n2-3" value="160" onblur="setProperty(this);" />
									</td>
								</tr>
								<tr>
									<td>设置最小日期:</td>
									<td>
									<input id="minDate" type="text" name="minDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true}),setProperty(this);" />
									</td>
									<td>设置最大日期:</td>
									<td>
									<input id="maxDate" type="text" name="maxDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true}),setProperty(this);" />
									</td>
								</tr>
								<tr>
									<td>是否显示周</td>
									<td>
										<input  type="radio" id="showWeek_y" name="showWeek" value = "true" checked="checked" onchange="setProperty(this);" >是
										<input  type="radio" id="showWeek_n" name="showWeek" value = "false"  onchange="setProperty(this);" >否
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
	//声明控件标签参数
	var paramTagType = "datePicker";
  	//显示格式和  最大最小日期 关联的方法
	function relateDate(value){
	
		$("#minDate").attr("onfocus","WdatePicker({dateFmt:'"+value+"',readOnly:true}),setProperty(this);");
		$("#maxDate").attr("onfocus","WdatePicker({dateFmt:'"+value+"',readOnly:true}),setProperty(this);");
	}
  	
  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		
  		//对是否显示label值的JS级联方法
  		isShowLabelValue("hasLabel");
  		
  		var dateFmtValue = $("#dateFmt").val();
  		
  		relateDate(dateFmtValue);
  	}
  	
  	//页面加载时设置默认属性
  	 function initProperties(){
  		
    	var b1 = new Control($("#dateFmt").attr("name"),$("#dateFmt").attr("value"));
    	var b2 = new Control($("#readOnly_y").attr("name"),$("#readOnly_y").attr("value"));
  		var b3 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
  		var b4 = new Control($("#showClear_y").attr("name"),$("#showClear_y").attr("value"));
  		var b5 = new Control($("#showWeek_y").attr("name"),$("#showWeek_y").attr("value"));
  		var b6 = new Control($("#width").attr("name"),$("#width").attr("value"));
  		var b7 = new Control($("#height").attr("name"),$("#height").attr("value"));
		
  		addToArray(b1);
  		addToArray(b2);
  		addToArray(b3);
  		addToArray(b4);
  		addToArray(b5);
  		addToArray(b6);
  		addToArray(b7);
		
		generateSourceCode(paramTagType);
  	}
  		initProperties();
  		isShowLabelValue("hasLabel");
	</script>

</html>

