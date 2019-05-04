	//拼接源码
	var source = "";
	//拼接的属性集合
	var property = "";
	//弹窗对象
	var p;
	
	//声明一个数组，用来存放各个属性对象，对象不可重复
	var controlArray = new Array();
	
	/**
	 * 表单验证通过后 单表单条件下的多提交按钮 选择 
	 * 按钮类型 ：保存 另存，预览
	 */
	var checkButton = "";
	
		function defineType(o){
			checkButton = o.id;
		}

	//实例化一个控件对象 属性为 id value
	function Control(id,value){
		this.id=id;
		this.value=value;
	}
	
	//生成源码的方法
	function generateSourceCode(paramTagType){
			
			source = "<h\:" + paramTagType + " ";
			property = "";
			for(var i=0;i<controlArray.length;i++){
				var tempV = controlArray[i].value + "";
				if(tempV != "" && tempV != "[]"){
					source = source + controlArray[i].id +"= '"+ controlArray[i].value+"' ";
					property = property + controlArray[i].id+"=" + controlArray[i].value+"&";
				}
			}
				source = source + "></h\:" + paramTagType + ">";
				parent.$("#sourceCode").html(source);
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
		
	//属性页面各属性触发事件后   给控件对象属性赋值 
	function setProperty(o){
		var b = new Control(o.name,o.value);
		addToArray(b);
			//调用生成源码的方法
			generateSourceCode(paramTagType);
	}

  	//回显历史记录
  	function showHistory(obj,id){
  		
  		//重置表单
  		document.getElementById("formobj").reset();
  		
  		//获取历史数据id
  		$("#historyId").val(id);
  		//清空数组
  		controlArray.splice(0,controlArray.length);
  		// 遍历对象所有属性 
		for ( var p in obj ){
			// 方法 
			if ( typeof ( obj [p]) == " function " ){ 
			} else {
				// p 为属性名称，obj[p]为对应属性的值 
				setValue(p,obj[p]);
				var b = new Control(p,obj[p]);
				addToArray(b);
			}
		}
		
		//属性页面回调属性函数
		showHistoryCallback();
		//调用生成源码的方法
		generateSourceCode(paramTagType);
  	}
  	
  	//为各元素赋值
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
  	
	
	//对是否显示label值的JS级联方法
	function isShowLabelValue(name){
		
		var value;
		var radio = document.getElementsByName(name);  
		for (i=0; i<radio.length; i++) {//获取radio中选中的值  
	        if (radio[i].checked) {  
	        	value = radio[i].value; 
	        }  
	    }  
		if(value == "true"){
			
			$(".label_value").css("display","");
			$(".hasLabel").removeAttr("colspan");
		}else{
			
			$(".label_value").css("display","none");
			$(".hasLabel").attr("colspan","3");
			
			//清空label的值
			$("#lable").val("").blur();
		}
	
	}
	
	//下拉框控件级联更新时调用的函数
	function ajaxCascadeUpdateSelect(targetSelectId,dynamicCondition){
		//获取自定义属性dict的值
		var dict = $("#"+targetSelectId+"").attr("dict");
		//获取自定义属性condition的值
		var condition = $("#"+targetSelectId+"").attr("condition");
		
		$.ajax({
	        async : false,
	        cache : false,
	        type : 'POST',
	        url : 'dictSelectController.do?getListByCodeOrTable',// 请求的action路径
	        dataType: "json",
	       
	        data: { "dict":dict,	
	        		"condition":condition,
	        		"dynamicCondition":dynamicCondition
	        	},  
	        error : function() {// 请求失败处理函数
	       
	        },
	        
	        success : function(data) {
	        	
	        	var d = JSON.parse(data);
	        	
	        	//清空需要级联的select内容
	        	$("#"+targetSelectId+"").empty();
	        	
	        	if(d.length >0){//如果数组不为空，则把数据拼接到需要级联的select的option内
	        		
	        		for(i = 0; i < d.length; i++) {//给option循环赋值
	        			$("<option value='"+d[i].id+"'>"+d[i].name+"</option>").appendTo("#"+targetSelectId+"")   
	        		} 
	        	}
	        	
	        }
	      });
	}

		//点击保存按钮触发函数
		function save(){
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
		    		url : paramTagType+'Controller.do?save&source='+source+"&typeId="+typeId+"&historyId=" + hId,// 请求的action路径
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
		function saveAs(){
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
			    		url : paramTagType+'Controller.do?save&source='+source+"&typeId="+typeId+"&controlName="+controlName+"&historyId=" + hId,// 请求的action路径   		
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

	  	//点击预览后调用的方法
	    function preview(){
	    	parent.$("#afterPreview").css("display","block");//隐藏预览前区域
	    	parent.$("#beforePreview").css("display","none");//显示预览后区域
	    	$.ajax({
	    		async : false,
	    		cache : false,
	    		type : 'POST',
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
	    

	//表单提交前处理的函数
	function onBeforeSub() {
	
		if (checkButton == "save") {
			save();
		} else if (checkButton == "saveAs") {
			saveAs();
		} else if (checkButton == "preview") {
			preview();
		}
		return false;
	
	}  
	    
