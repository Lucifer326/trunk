<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>附件上传</title>
<%-- <t:base type="jquery,easyui,tools,DatePicker"></t:base> --%>
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
									<td><span style="color:red">*</span>名称:</td>
									<td><input class="easyui-textbox" id="name" type="text" name="name" onblur="setProperty(this);" datatype="*"/></td>
									<td>文件类型:</td>
									<td>
										<input  type="radio" id="type_f" name="type" value = "file" checked="checked" onchange="setProperty(this);" >文件
										<input  type="radio" id="type_i" name="type" value = "image"  onchange="setProperty(this);" >图片
									</td>
								</tr>
								
								<tr>
									<td>存储方式:</td>
									<td>
										<input  type="radio" id="memoryType_s" name="memoryType" value = "server" checked="checked" onchange="setProperty(this);" >服务器
										<input  type="radio" id="memoryType_d" name="memoryType" value = "db"  onchange="setProperty(this);" >数据库
									
									</td>
									<td>单个文件最大值:</td>
									<td><input class="easyui-textbox" id="fileSingleSizeLimit" type="text" name="fileSingleSizeLimit" datatype="n1-2" value="6" onblur="setProperty(this);"/></td>
								</tr>
								<tr>		
								</tr>
								<tr>
									<td>是否自动上传:</td>
									<td>
										<input  type="radio" id="auto_y" name="auto" value = "true" onchange="setProperty(this);" >是
										<input  type="radio" id="auto_n" name="auto" value = "false" checked="checked" onchange="setProperty(this);" >否
									
									</td>
									<td>允许的后缀名:</td>
									<td><input class="easyui-textbox" id="extensions" type="text" name="extensions" onblur="setProperty(this);"/></td>
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
	var paramTagType = "webUploader";

  //点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		
  		
  	}
  	
  	//页面加载时设置默认属性
  	 function initProperties(){
  		
  		b1 = new Control($("#fileSingleSizeLimit").attr("name"),$("#fileSingleSizeLimit").attr("value"));
  	 	b2 = new Control($("#type_f").attr("name"),$("#type_f").attr("value"));
  	 	b3 = new Control($("#auto_n").attr("name"),$("#auto_n").attr("value"));
  	 	b4 = new Control($("#memoryType_s").attr("name"),$("#memoryType_s").attr("value"));
  	 	
  		addToArray(b1);
		addToArray(b2);
		addToArray(b3);
		addToArray(b4);
		
		generateSourceCode(paramTagType);
  	}
  		initProperties();

  		</script>

</html>

