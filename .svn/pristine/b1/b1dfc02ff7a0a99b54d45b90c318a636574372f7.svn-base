
//通用弹出式文件上传
function commonUpload(callback) {
	$.dialog({
		content : "url:systemController.do?commonUpload",
		lock : true,
		title : "文件上传",
		zIndex : 2100,
		width : 700,
		height : 200,
		parent : windowapi,
		cache : false,
		ok : function() {
			var iframe = this.iframe.contentWindow;
			iframe.uploadCallback(callback);
			return true;
		},
		cancelVal : '关闭',
		cancel : function() {
		}
	});
}
function browseImages(inputId, Img) {// 图片管理器，可多个上传共用
	var finder = new CKFinder();
	finder.selectActionFunction = function(fileUrl, data) {// 设置文件被选中时的函数
		$("#" + Img).attr("src", fileUrl);
		$("#" + inputId).attr("value", fileUrl);
	};
	finder.resourceType = 'Images';// 指定ckfinder只为图片进行管理
	finder.selectActionData = inputId; // 接收地址的input ID
	finder.removePlugins = 'help';// 移除帮助(只有英文)
	finder.defaultLanguage = 'zh-cn';
	finder.popup();
}
function browseFiles(inputId, file) {// 文件管理器，可多个上传共用
	var finder = new CKFinder();
	finder.selectActionFunction = function(fileUrl, data) {// 设置文件被选中时的函数
		$("#" + file).attr("href", fileUrl);
		$("#" + inputId).attr("value", fileUrl);
		decode(fileUrl, file);
	};
	finder.resourceType = 'Files';// 指定ckfinder只为文件进行管理
	finder.selectActionData = inputId; // 接收地址的input ID
	finder.removePlugins = 'help';// 移除帮助(只有英文)
	finder.defaultLanguage = 'zh-cn';
	finder.popup();
}
function decode(value, id) {// value传入值,id接受值
	var last = value.lastIndexOf("/");
	var filename = value.substring(last + 1, value.length);
	$("#" + id).text(decodeURIComponent(filename));
}
// 根据控件类型，显示使用的控件
function ShowControl() {
	// 获取控件类型
	var CONTOL_TYPE = $("#controlType").val();
	//alert(CONTOL_TYPE);
	// 获取配置值
	var value = $("#valuebak").val();
	if (CONTOL_TYPE == 3)// 值是下拉列表显示
	{
		$("#value_select").show();// 显示出下拉列表框
		var str_exp = $("#dateExpr").val();// 参数表达式
		// 绑定下拉列表
		// 根据Ψ分开 否,0,trueΨ是,1,false
		var strData = str_exp.split('Ψ');
		var value_select = $("#value_select");
		$("#value_select").empty();
		for (var i = 0; i < strData.length; i++) {
			// 用,区分text和value
			var strItem = strData[i].split(',');
			$("#value_select").append("<option value='"+strItem[1] +"'>" + strItem[0] + "</option>");
		}
		$("#value_select").val(value);
	} 
//	// 是否绑定行政区划
//	var isBinddisbak = $("#isBinddisbak").val();
//	if (isBinddisbak == "1") {
//		$("#isBinddis1").attr("checked","checked");
//	}
//	else if(isBinddisbak=="0"){
//		$("#isBinddis2").attr("checked","checked");
//	}
}
