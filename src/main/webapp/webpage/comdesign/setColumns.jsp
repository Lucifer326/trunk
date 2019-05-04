<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title></title>
<style type="text/css">

	
	.setColum_d1{
		display: inline-block;
	}
</style>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
	<body style="overflow-y: auto">
				<!-- 属性设置  -->
				<div >
					<div>												
						<t:formvalid layout="div" dialog="false" formid="formobj" beforeSubmit="onBeforeSub" tiptype="1">
							
							<div class="Colum_d3">
							
								<div class="Colum_d3">
									<p class="setColum_d1">选择</p>
									<p class="setColum_d1">序号</p>
									<p class="setColum_d1">列名</p>
									<p class="setColum_d1">标题</p>
									<p class="setColum_d1">列宽度</p>
									<p class="setColum_d1">是否隐藏列</p>
								</div>
									<c:forEach items="${columnsList}" var="list" varStatus="status">
											<div class="setColum"><input id="check_${status.index+1}" type="checkbox"  name="check" onclick="generCode(this);"></div>
										<div class="setColum_d2">
											<div class="setColum_d1">${status.index+1}</div>
											<div class="setColum_d1"><input class="xx_${status.index+1}" id="field_${status.index+1}" type="text" value="${list.fieldName}" name="${list.fieldName}" readonly="readonly"></div>
											<div class="setColum_d1"><input class="xx_${status.index+1}" id="title_${status.index+1}" type="text" value="${list.content}" name="title" ></div>
											<div class="setColum_d1"><input class="xx_${status.index+1}" id="width_${status.index+1}" type="text" value="100" name="width" ></div>
											<div class="setColum_d1">
												<select class="xx_${status.index+1}" id="isHidden_${status.index+1}">
													<option value="true">是</option>
													<option value="false" selected="selected">否</option>
												</select>
											</div>
										</div>
									</c:forEach>
								
							</div>																					
						</t:formvalid>
						
					</div>
				</div>						
				
	</body>
	<script type="text/javascript">
	
	//拼接源码
	var source = "";
	//拼接的列属性集合
	var columproperty = "";
	//控件类型
	var typeId = '${typeId}';
	//声明控件标签参数
	var paramTagType = "colum";
	
	//声明一个数组，用来存放各个列对象，对象不可重复
	var controlArray = new Array();
	
	//实例化一个列对象 属性为 check,field,title,width
	function Control(rec,id,value){
		
		this.rec=rec;
		this.id=id;
		this.value=value;
	}
	
	function generCode(o){
		
			var checkid = o.id;
			
			var _index = checkid.indexOf('_');
			var n = checkid.substring(_index+1);
		if(o.checked){
			
			$(".setColum_d2").children(".setColum_d1").children(".xx_"+n).attr("readonly","readonly");

			var fieldVal = $("#field_"+n).val();
			var titleVal = $("#title_"+n).val();
			var widthVal = $("#width_"+n).val();
			var hidden = $("#isHidden_"+n).val();
			
			
			var c1 = new Array("rec","field","title","width","hidden");
			var c2 = new Array(n,fieldVal,titleVal,widthVal,hidden);
			
			var b = new Control(n,c1,c2);
			
			addToArray(b);
		}else{
		
			$(".setColum_d2").children(".setColum_d1").children(".xx_"+n).removeAttr("readonly");
			
		for(var i=0;i<controlArray.length;i++){
			var r = controlArray[i].rec;
				if(r == n){
					controlArray.splice(i,1);
				}
			}
		}
		//调用生成源码的方法
		parent.commongenerateSourceCode();
	}
	//生成列标签源码的方法
	function columgenerateSourceCode(paramTagType){
		
		source = "";
		for(var i=0;i<controlArray.length;i++){
			
			source += "<h\:" + paramTagType + " ";
			columproperty = "";
			
			var arrayKey = new Array();
			
			arrayKey = controlArray[i].id;
			
			var arrayVal = new Array();
				
			arrayVal = controlArray[i].value;
			for(var j=0;j<arrayKey.length;j++){
				
				source += arrayKey[j]+"='"+arrayVal[j]+"' ";
				columproperty = columproperty + controlArray[i].id+"=" + controlArray[i].value+"&";
			}
				source += "></h\:" + paramTagType + ">";
		}
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
			if(controlArray[i].rec == b.rec){//已存在
				controlArray.splice(i,1);
				break;
			}
		}
		controlArray.push(b);
	}
		
	//属性页面各属性触发事件后   给控件对象属性赋值 
	function setProperty(o){
		var b = new Control(o.name,o.value);
		addToArray(b);
		//调用生成源码的方法
		columgenerateSourceCode(paramTagType);
	}
	
	</script>


</html>

