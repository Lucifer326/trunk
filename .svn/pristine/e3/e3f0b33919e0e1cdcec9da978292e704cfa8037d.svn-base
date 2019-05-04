<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>多功能按钮</title>
<h:base type="jquery-hp,easyui,hplus,hplisttools"></h:base>
<link href="plug-in/tag/propertyStyle.css" rel="stylesheet" type="text/css" />  
<link rel="stylesheet" type="text/css" href="plug-in/tag/multiButton.css" > 
   <script src="webpage/comdesign/multiButton.js"></script>
   <script src="webpage/comdesign/controlGenerator.js"></script>
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
									
									<td><span class="required">*</span>唯一标识:</td>
									<td>
										<input class="easyui-textbox" id="field"  type="text" name="field" datatype="/^[A-Za-z][A-Za-z0-9]{1,18}/" errormsg="格式为非汉字不能数字开头" onblur="setProperty(this);" >
									</td>	
									<td>名称:</td>
									<td>
										<input class="easyui-textbox" id="name" type="text" name="name" value="多功能按钮" onblur="setProperty(this);" >
									</td>								
								</tr>
								<tr>
									<td>按钮样式:</td>
									<td>
										<input class="easyui-textbox" id="multibuttonStyle" type="text" name="multibuttonStyle" onblur="setProperty(this);" >
									</td>
									<td><span class="required">*</span>按钮类型:</td>
									<td>
										<h:dictSelect id="multibuttonType" field="multibuttonType" hasLabel="false" typeGroupCode="multibuttonType" onchange="editCondition(this),setProperty(this);"></h:dictSelect>
									</td>
								</tr>
							</table>
								<input id="selectConditionList" type="hidden" name="selectConditionList" onblur="setProperty(this);">
						</t:formvalid>
						<div> 
				<!----------------查询按钮开始 ---------------->
			   <div class="custom_right" style="display: none;">
			     <span>关联表格id：</span>
			     <input id="tableId" name="tableId" type="text" onblur="setProperty(this);"/>
			     <div class="search">
			       <div class="search_div">
			          <span>关联条件id：</span>
					  <input id="controlId" name="controlId" type="text" />      
			       </div>
			       <div class="search_div" >
			          <span>关系：</span>
				      <select class="wenb" id="relation" name="relation">
						<option value="" selected="selected"></option>
						<option value="and">并且</option>
						<option value="or">或者</option>
					  </select>       
			       </div>
			       <div class="search_div">
			          <span>操作符：</span>
				      <select class="wenb" id="operator"  name="operator">
				      <option value="" selected="selected"></option>
						<option value="eq">等于</option>
						<option value="ne">不等于</option>
						<option value="gt">大于</option>
						<option value="lt"> 小于</option>
						<option value="gte">大于等于</option>
						<option value="lte"> 小于等于</option>
						<option value="like">模糊</option>
					  </select>	       
			       </div>
			       <div class=clearfix></div>	       
			     </div><!-- search -->
			      <div class="spec">
			       <div class="spec_left">
			         <table class="spec_list">
			          <thead>
			            <tr>
			              <!-- <th><input type="checkbox"></th> -->
			              <th></th>
			              <th>关联控件id</th>
			              <th>关系</th>
			              <th>操作符</th>
			            </tr>            
			          </thead>
			          <tbody id="d_table">
			          </tbody>
			         </table>
			        
			       </div>
			       <div class="spec_right">
		            <div class="spec_btn">
				         <button class="btn" onclick="addCondition()">添加</button>
				         <button class="btn" onclick="delCondition()">删除</button>
			         </div>
			       </div>
			      </div>
			   </div>	
			   <!----------------查询按钮结束 ---------------->    
	  </div>	  
					</div>
				</div>
	</body>
	<script type="text/javascript">
	
	//控件类型
	var typeId = '${typeId}';
	//声明控件标签参数
	var paramTagType = "multibutton";
  	
  	//点击历史数据回显属性时的回调方法
  	function showHistoryCallback(){
  		clearCondition();
  		var value = $("#multibuttonType").val();
  		var data = "";
		 if(value == "selectButton"){
	  			$(".custom_right").css("display","block");
	  		}else if(value == "addButton"){
	  			$(".custom_right").css("display","none");
	  		}
		 for(var p in controlArray){
			 
			 if(controlArray[p].id =="selectConditionList"){
				 data= controlArray[p].value;
			 }
		 } 
		 var d = JSON.parse(data);
		for(var i=0;i<d.length;i++){
			 $("#d_table").append('<tr">'+
						'+<td><input type="checkbox"></td>'+
						'+<td>'+d[i].controlId+'</td>'+
						'+<td>'+d[i].relation+'</td>'+
						'+<td>'+d[i].operator+'</td>'+
						'+</tr>');	
				saveCondition();
		 }
  	}
  	function editCondition(o){
  		
  		var val = o.value;
  		 if(val == "selectButton"){
  			$(".custom_right").css("display","block");
  		}else if(val == "addButton"){
  			$(".custom_right").css("display","none");
  		}
  	}
  	//页面加载时设置默认属性
  	 function initProperties(){
  		
    	var b1 = new Control($("#name").attr("name"),$("#name").attr("value"));
  		
    	addToArray(b1); 
		
		generateSourceCode(paramTagType);
  	}
  		initProperties();
	</script>
</html>

