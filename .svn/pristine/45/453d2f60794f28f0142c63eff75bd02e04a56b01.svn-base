<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>弹窗树</title>
<h:base type="jquery-hp,easyui,hplus,hplisttools"></h:base>
<link href="plug-in/tag/propertyStyle.css" rel="stylesheet" type="text/css" />
<script src="webpage/comdesign/controlGenerator.js"></script>
</head>
	<body style="overflow-y: auto">
				<!-- 属性设置  -->
				<div >
					<input type="hidden" id="historyId">
					<div >												
						
							<t:formvalid layout="div" dialog="false" formid="formobj" beforeSubmit="onBeforeSub" tiptype="1">
							
							<input class="hbtnbox" type="submit" id="saveAs" name="saveAs" value="另存为" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="save" name="save" value="保存" onclick="defineType(this);">
							<input class="hbtnbox" type="submit" id="preview" name="preview" value="预览" onclick="defineType(this);" style="display: none;">
							
							<table cellpadding="5">
								<tr>
									<td><span style="color:red">*</span>名称:</td>
									<td><input class="easyui-textbox" id="name" type="text" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头，至少2位" name="name" onblur="setProperty(this);"></input></td>
								</tr>
								<tr>
									<td><span style="color:red">*</span>数据提供者:</td>
									<td>
										<input class="easyui-textbox" id="dataProvider" type="text" datatype="*" value="controlGeneratorService.getDepartInfo" name="dataProvider" onblur="setProperty(this);"></input>
									</td>
									<td>是否只读:</td>
									<td>
										<input  type="radio" id="readonly_y" name="readonly" value = "true" checked="checked" onchange="setProperty(this);">是
										<input  type="radio" id="readonly_n" name="readonly" value = "false" onchange="setProperty(this);">否
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
									<td>弹窗宽度（单位：px）:</td>
									<td><input class="easyui-textbox" id="dialogWidth" type="text" datatype="n3-4" name="dialogWidth" value="400" onblur="setProperty(this);"></input></td>
									
									<td>弹窗高度（单位：px）:</td>
									<td><input class="easyui-textbox" id="dialogHeight" type="text" datatype="n3-4" name="dialogHeight" value="500" onblur="setProperty(this);"></input></td>
								</tr>
								<tr>
									<td>自定义Class:</td>
									<td><input class="easyui-textbox" id="inputClass" type="text" name="inputClass" onblur="setProperty(this);"></input></td>
									
									<td>自定义css:</td>
									<td><input class="easyui-textbox" id="inputStyle" type="text" name="inputStyle" onblur="setProperty(this);"></input></td>
								</tr>
								
								<tr>
									<td>弹窗标题:</td>
									<td><input class="easyui-textbox" id="dialogTitle" type="text" value = "数据选择" name="dialogTitle" onblur="setProperty(this);"></input></td>
									
									<td>弹窗树的类型:</td>
									<td>
										<input  type="radio" id="selectType_radio" name="selectType" value = "radio" checked="checked" onchange="setProperty(this),choosePopTreeType(this.value);">单选
										<input  type="radio" id="selectType_check" name="selectType" value = "checkbox" onchange="setProperty(this),choosePopTreeType(this.value);">多选
									</td>
								</tr>
								<tr class="multiSelect" style="display: none;">
									<td>是否允许选择非叶子节点:</td>
									<td>
										<input class="removeChecked" type="radio" id="checkBranch_y" name="checkBranch" value = "true"  onchange="setProperty(this);">是
										<input class="removeChecked" type="radio" id="checkBranch_n" name="checkBranch" value = "false" onchange="setProperty(this);">否
									</td>
									<td>是否支持级联选择:</td>
									<td>
										<input class="removeChecked" type="radio" id="cascadeCheck_y" name="cascadeCheck" value = "true"  onchange="setProperty(this);">是
										<input class="removeChecked" type="radio" id="cascadeCheck_n" name="cascadeCheck" value = "false" onchange="setProperty(this);">否
									</td>
								</tr>
							</table>
						</t:formvalid>
						
					</div>
				</div>
	</body>
	<script type="text/javascript">
	
			//控件类型
			var typeId = '${typeId}';
			//声明控件标签
			var paramTagType = "popupTree";
		
		  	//选择弹窗树的类型
			function choosePopTreeType(value){
				
				if(value == "radio"){//单选
					
					$(".multiSelect").css("display","none");
						
				$(".removeChecked").each(function(index,data){
					
					$("#"+data.id).val('').change();
				})
				
				}else if(value == "checkbox"){//多选
					
					$(".multiSelect").css("display","");
					$(".removeChecked").each(function(index,data){
						if((index+1) %2 == 0){
							$("#"+data.id).val('false').removeAttr("checked");
						}else{
							$("#"+data.id).val('true').removeAttr("checked");
						}
					})
				}
			}
		  	//点击历史数据回显属性时的回调方法
		  	function showHistoryCallback(){
		  		//对是否显示label值的JS级联方法
		  		isShowLabelValue("hasLabel");
		  	}
		  	
		  	//页面加载时设置默认属性
		  	function initProperties(){
		  		var b1 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
		  		var b2 = new Control($("#selectType_radio").attr("name"),$("#selectType_radio").attr("value"));
		  		var b3 = new Control($("#checkBranch_y").attr("name"),$("#checkBranch_y").attr("value"));
		  		var b4 = new Control($("#cascadeCheck_y").attr("name"),$("#cascadeCheck_y").attr("value"));
		  		var b5 = new Control($("#hasLabel_n").attr("name"),$("#hasLabel_n").attr("value"));
		  		var b6 = new Control($("#readonly_y").attr("name"),$("#readonly_y").attr("value"));
		  		var b7 = new Control($("#dialogTitle").attr("name"),$("#dialogTitle").attr("value"));
		  		var b8 = new Control($("#dataProvider").attr("name"),$("#dataProvider").attr("value"));
		  		
				addToArray(b1);
				addToArray(b2);
				addToArray(b3);
				addToArray(b4);
				addToArray(b5);
				addToArray(b6);
				addToArray(b7);
				addToArray(b8);
				
				generateSourceCode(paramTagType);
		  	}
		  	initProperties();
		  	isShowLabelValue("hasLabel");
	</script>

</html>
