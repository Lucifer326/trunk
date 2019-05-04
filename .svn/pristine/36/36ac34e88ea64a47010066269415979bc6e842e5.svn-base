<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>图片上传</title>
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
									<td>
										<input class="easyui-textbox" id="name" type="text" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头，至少2位" name="name" onblur="setProperty(this);" datatype="*">
									</td>
									<td>图片格式:</td>
									<td>
										<input  type="checkbox" id="fileType_1" name="fileType" value = ".jpg" checked="checked" onchange="jqchk(this.name);">.jpg
										<input  type="checkbox" id="fileType_2" name="fileType" value = ".bmp" onchange="jqchk(this.name);">.bmp 
										<input  type="checkbox" id="fileType_3" name="fileType" value = ".png" onchange="jqchk(this.name);">.png
										<input  type="checkbox" id="fileType_4" name="fileType" value = ".jpeg" onchange="jqchk(this.name);">.jpeg
										<input  type="checkbox" id="fileType_5" name="fileType" value = ".gif" onchange="jqchk(this.name);">.gif
										<input  type="checkbox" id="fileType_6" name="fileType" value = ".psd" onchange="jqchk(this.name);">.psd
									</td>
								</tr>
								<tr>
									<td>图片显示高度</td>
									<td>
										<input class="easyui-textbox" id="height" type="text" datatype="n2-4" name="height" value="100" onblur="setProperty(this);">
									</td>
									<td>图片显示宽度</td>
									<td>
										<input class="easyui-textbox" id="width" type="text" datatype="n2-4" name="width" value="100" onblur="setProperty(this);"/>
									</td>
								</tr>
								
								<tr>
									<td>图片的大小(单位：M):</td>
									<td>
										<input class="easyui-textbox" id="fileSize" type="text" datatype="/^([1-9]|10)$/" errormsg="请输入1~10的任意整数" name="fileSize" value="6" onblur="setProperty(this);">
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
	var paramTagType = "imageUpload";
	
	function jqchk(name){ //jquery获取复选框值
		
		//声明一个图片上传空间的实体，属性为 name 和value
		function imgUploadControl(name,value){
			
			this.name=name;
			this.value=value;
		}
		
		//声明一个数组，存放已选中的值
		var chk_value =[];
		
		//遍历已选中的对象	
		$("input[name=\""+name+"\"]:checked").each(function(){
			
			//把值存到数组中
			chk_value.push($(this).val());
		});
		
		//实例化一个图片上传空间的实体
		var imgControl = new imgUploadControl(name,chk_value);
			
			setProperty(imgControl);
		} 

  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		
  		//对是否显示label值的JS级联方法
  		if(controlArray.length >0){
  			
  			var value = new Array();
	  		
  			for(var i =0;i<controlArray.length;i++){
	  			
	  			if(controlArray[i].id == "fileType"){
	  				
	  				value = controlArray[i].value.split(",")
	  				
	  			}
	  		}
  			$("input[name='fileType']").removeAttr("checked");
  		    
  			for(var i=0;i<value.length;i++){
  		        
  		    	var item = value[i];
  		        
  		        $("input:checkbox[value='"+item+"']").attr("checked","checked");
  		    }
  		}
  	}
  	//页面加载时设置默认属性
  	 function initProperties(){
  		 
  		var b1 = new Control($("#fileSize").attr("name"),$("#fileSize").attr("value"))  		 
  		var b2 = new Control($("#width").attr("name"),$("#width").attr("value"));
  		var b3 = new Control($("#height").attr("name"),$("#height").attr("value"));
  		var b4 = new Control($("#fileType_1").attr("name"),$("#fileType_1").attr("value"));
  		
  		addToArray(b1); 
		addToArray(b2);
		addToArray(b3);
		addToArray(b4);
		
		generateSourceCode(paramTagType);
  	}
  	initProperties(); 
  	jqchk("fileType");
	</script>

</html>




