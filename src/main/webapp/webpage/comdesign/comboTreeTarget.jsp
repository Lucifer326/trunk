<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>弹窗树</title>
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
									<td><span style="color:red">*</span>控件id:</td>
									<td><input class="easyui-textbox" id="id" type="text" datatype="*" name="id" onblur="setProperty(this);" /></td>
									<td><span style="color:red">*</span>控件名称:</td>
									<td><input class="easyui-textbox" id="name" type="text" datatype="*" name="name" onblur="setProperty(this);"/></td>
								</tr>
								<tr>
									<td><span style="color:red">*</span>数据源:</td>
									<td>
										<input class="easyui-textbox" id="url" type="text" datatype="*" name="url" value="controlGeneratorService.getDepartInfo" onblur="setProperty(this);"/>
									</td>
								</tr>
								<tr>
									<td>是否显示lable:</td>
									<td class="hasLabel" colspan="3">
										<input  type="radio" id="hasLabel_y" name="hasLabel" value = "true" onchange="setProperty(this),isShowLabelValue(this.name);" >是
										<input  type="radio" id="hasLabel_n" name="hasLabel" value = "false" checked="checked" onchange="setProperty(this),isShowLabelValue(this.name);" >否
									</td>
									
									<td class="label_value" style="display: none;">Lable值</td>
									<td class="label_value" style="display: none;"><input class="easyui-textbox" id="lable" type="text" name="lable" onblur="setProperty(this);"/></td>
									
								</tr>
								
								<tr>
									<td>宽度:</td>
									<td>
										<input class="easyui-textbox" id="width" type="text" datatype="n2-4" name="width" value="140" onblur="setProperty(this);"/>
									</td>
									<td>是否多选:</td>
									<td>
										<input  type="radio" id="multiple_t" name="multiple" value = "true"  onchange="setProperty(this);" >是
										<input  type="radio" id="multiple_f" name="multiple" value = "false" checked="checked" onchange="setProperty(this);" >否
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
	//声明控件标签
	var paramTagType = "comboTree";
	
  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		
  		//对是否显示label值的JS级联方法
  		isShowLabelValue("hasLabel");
  		
  	}
  	
  	//页面加载时设置默认属性
  	 function initProperties(){
  		var b1 = new Control($("#url").attr("name"),$("#url").attr("value"));
  		var b2 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
  		var b3 = new Control($("#multiple_f").attr("name"),$("#multiple_f").attr("value"));
  		var b4 = new Control($("#width").attr("name"),$("#width").attr("value"));
  		
		addToArray(b1);
		addToArray(b2);
		addToArray(b3);
		addToArray(b4);
		
		generateSourceCode(paramTagType);
  	}
  		initProperties();
	</script>

</html>

