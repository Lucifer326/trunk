<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,autocomplete"></t:base>
<%
	String tablename = (String)request.getAttribute("tablename");
	String formulaname = (String)request.getAttribute("formulaname");
%>
<style type="text/css">
/* 	 .table_content  {
	width: 100%;
	height: 97%;
	border: 0;
	border-collapse: collapse;
	font-size: 12px;
}

.table_content table tr:hover { 
	background-color: #C1EBFF;
}

.table_content table th {
	border-collapse: collapse;
	height: 22px;
	background: url("./tab/images/bg.gif");
	border-right: 1px solid #B5D6E6;
	border-bottom: 1px solid #B5D6E6;
}

.table_content table td {
	height: 22px;
	word-wrap: break-word;
	text-align: left;
	vertical-align: top;
	border-right: 1px solid #B5D6E6;
	border-bottom: 1px solid #B5D6E6;
}
.table_content .panel-body-noborder {
	height:100% !important
}  */
.left_content .panel-body-noborder{
	height:100% !important;
}
</style>
<script type="text/javascript">
	var expressstart = 0;
	var taclick = 0;
	var tablenameold = "";
	var columnname = "";
	var isLeaf = "0";
	var treeselectLevel = 0;
	var isReloadTable = 0;
	var p;
	$(function() {
		$('#formtree').tree(
				{
					animate : true,
					url : 'defController.do?getFormulaFactorTree&tablename=<%=tablename%>',
					onLoadSuccess : function(){
						unvisible();
					},
					onClick : function(node) {
						var ta1 = document.getElementById("calcexpress");
						ta1.innerText = "";
						ta1.value = "";
						treeselectLevel = 1;
						var tar = node;
						while(tar.id!="#"){
							treeselectLevel++;
							tar = $('#formtree').tree('getParent',
									tar.target);
						}
						
						if (treeselectLevel==4) {
							selectTreeFormula(node);
						} else {
							isLeaf = "0"
							tablenameold = "";
							getchoosefactortree("");
						}
						if (treeselectLevel==3) {
							columnname = node.id;
							tablenameold = $('#formtree').tree(
									'getParent',node.target).id;
						}
						 else
							columnname = "";
					}
				});
		getchoosefactortree("");
		
	});
	//显示公式名称选择
	//<div region="west" style="width: 100px;" title="公式选择" split="true"
	

	function unvisible(){
		var node = $('#formtree').tree('find', '<%=formulaname%>');
		$('#formtree').tree('select', node.target);
		selectTreeFormula(node);
	}
	function selectTreeFormula(node){
		getformulaOriginal(node.text);
		if (tablenameold != $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						node.target).target).id||isLeaf=='0') {
			tablenameold = $('#formtree').tree(
					'getParent',
					$('#formtree').tree('getParent',
							node.target).target).id;
			getchoosefactortree(tablenameold);
		}
		isLeaf = "1"
	}
	function getchoosefactortree(b) {
		$('#choosefactortree').tree(
				{
					animate : true,
					
					url : 'defController.do?getExpressFactorTree&tablename='
							+ b,
					onDblClick : function(node) {
						var tree2selectLevel = 1;
						var tar2 = node;
						while(tar2.id!="#"){
							tree2selectLevel++;
							tar2 = $('#choosefactortree').tree('getParent',
									tar2.target);
						}
						if (tree2selectLevel==3){
							if($('#choosefactortree').tree('getParent',node.target).id=='funlist'){
								setexpress("【" + node.text + "】");
							}else if($('#choosefactortree').tree('getParent',node.target).id=='sqlcalclist'){
								setexpress("{" + node.text + "}");
							}else{
								setexpress("[" + node.text + "]");
							}
						}else if (tree2selectLevel==4){
							setexpress("［" + node.text + "］");
						}
					}
				});
	}
	function reloadTable(){
		if (isReloadTable==1){
			isReloadTable = 0;
			$("#formtree").tree("reload");
		}
	}
	function regionTextV(a){
		var t = a.obj;
		if(t.length>20)
			t = t.substr(0,20)+"...";
		$("#regiontext")[0].innerText=t;
		$("#regiontext")[0].innerHTML=t;
	}
	function getRefedCol(b) {
		tablenameold = b;
		$.ajax({
			url : "defController.do?getRefedCol&tablename=" + b, //后台webservice里的方法名称  
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				var optionstring = "";
				for ( var i in data) {
					optionstring += "<option value=\"" + data[i].id + "\" >"
							+ data[i].text + "</option>";
				}
				$("#refedcol").html(optionstring);
				eventLoad(1);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function getSQLCalc() {
		$.ajax({
			url : "defController.do?getSQLCalcName&tablename=" + tablenameold, //后台webservice里的方法名称  
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				var optionstring = "";
				for ( var i in data) {
					optionstring += "<option value=\"" + data[i].id + "\" >"
							+ data[i].text + "</option>";
				}
				$("#refedcalc").html(optionstring);
				eventLoad(2);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function getformulaOriginal(b) {
		//alert(b);
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		$.ajax({
			url : "defController.do?getformulaOriginal&tablename="
					+ formulatable + "&formulaname=" + b, //后台webservice里的方法名称  
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				var ta = document.getElementById("calcexpress");
				var express = data.msg.substring(0, data.msg.indexOf(','));
				ta.innerText = express;
				ta.value = express;
				/*ta = document.getElementById("datarange");
				express = data.msg.substring(data.msg.indexOf(',') + 1);
				ta.innerText = express;
				ta.value = express;
				$('#regiontree').tree(
						{
							animate : true,
							url : 'defController.do?tree&tablename='
									+ tablenameold + "&formulaname=" + b,
							checkbox : true
						});*/
			},
			error : function(msg) {
				alert("出错了！");
			}
		});

	}
	function eventLoad(a) {
		if (a == 1)
			$('#refedcol option').dblclick(function() {
				setexpress("[" + this.text + "]");
			});
		if (a == 2) {
			$('#refedcalc option').dblclick(function() {
				setexpress("[" + this.text + "]");
			});
			$('#refedcalc option').click(function() {
				getsqlexpress(this.text);
			});
		}
	}
	function getsqlexpress(sqlcalcname) {
		$.ajax({
			url : "defController.do?getSQLCalcExpress&sqlcalcname="
					+ sqlcalcname, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				var ta = document.getElementById("sqlexpress");
				ta.innerText = data.msg;
				ta.value = data.msg;
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function refreshFormulaName(a) {
		$('#formulaname').combobox(
				{
					url : "defController.do?getFormulaName&tablename="
							+ tablenameold + "&columnname=" + a,
					valueField : 'id',
					textField : 'text',
					onSelect : function(rec) {
						getformulaOriginal(rec.text);
					},
					onLoadSuccess : function() {
						getformulaOriginal($('#formulaname')
								.combobox('getText'));
					}
				});
	}
	function checkFormula() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		var formulaExp = encodeURIComponent($("#calcexpress").val());
		$.ajax({
			url : "defController.do?checkFormula&tablename=" + tablenameold
					+ "&express=" + formulaExp, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					alert("公式定义合法，验证通过！");
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function checkCalc() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式列！");
			return;
		}
		var sqlexpress = encodeURIComponent($("#sqlexpress").val());
		$.ajax({
			url : "defController.do?checkCalc&tablename=" + tablenameold
					+ "&sqlexpress=" + sqlexpress, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					alert("SQL表达式定义合法，验证通过！");
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function saveFormula() {
		//alert("保存成功!");

		//frameElement.api.close(); 

		//$.dialog({id:'createAlbum'}).close(); 

		//window.parent.location.reload();
		//return;
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		//saveRegion();
		var formulaExp = encodeURIComponent($("#calcexpress").val());
		var datarangeExp = "";//encodeURIComponent($("#datarange").val());
		var forumaName = $('#formtree').tree("getSelected").text;
		$.ajax({
			url : "defController.do?saveFormula&tablename=" + tablenameold
					+ "&express=" + formulaExp + "&formulaname=" + forumaName
					+ "&columnname=" + columnname + "&datarange="
					+ datarangeExp, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			async: false,
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					alert("公式定义保存成功！");
			}
		});
	}
	function saveRegion() {
		var nodes = $('#regiontree').tree('getChecked');
		var nodeids = '';
		for (var i = 0; i < nodes.length; i++) {
			nodeids += nodes[i].id + ",";
		}
		var forumaName = $('#formulaname').combobox('getText');
		$.ajax({
			url : "defController.do?saveRegion&tablename=" + tablenameold
					+ "&formulaname=" + forumaName + "&nodes=" + nodeids, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function saveCalc() {
		/* if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式列！");
			return;
		} */
		//saveRegion();
		alert(($("#sqlcalcexpress").val()));
		 var iframe = this.iframe.contentWindow;
		  var treeObj = iframe.$("#sqlcalctree");
		var sqlexpress = encodeURIComponent(iframe.$("#sqlcalcexpress").val());
		var sqlcalcname = treeObj.tree('getSelected').text;//选中的文本;
		$.ajax({
			url : "defController.do?saveCalc&tablename=TMP" 
					+ "&sqlexpress=" + sqlexpress + "&sqlcalcname="
					+ sqlcalcname, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					alert("SQL表达式定义保存成功！");
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function saveFormulaName() {
		var forumaName = "test公式1";
		$.ajax({
			url : "defController.do?saveFormulaName&tablename=" + tablenameold
					+ "&columnname=" + columnname + "&formulaname="
					+ forumaName, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					alert("公式名称保存成功！");
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function addFormulaName() {
		if (columnname == "") {
			alert("请在公式列选择树中正确选择公式列！");
			return;
		}
		isReloadTable = 1;
		add('新增公式', 'defController.do?addFormulaName&tablename=' + tablenameold
				+ '&columnname=' + columnname, 'addDemoList', 400, 100);
		//saveFormulaName();
	}
	function refresssqlcalctree(){
		var iframe = this.iframe.contentWindow;
		iframe.$("#sqlcalctree").tree("reload");
		 
	}
	function addCalcName() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		add('新增SQL计算器', 'defController.do?addSQLCalcName&tablename='
				+ formulatable , 'addDemoList',
				400, 100);
		/*$.dialog.setting.zIndex = 2; 
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		$.dialog({content: 'url:defController.do?addSQLCalcName&tablename='
			+ formulatable + '&columnname=' + columnname, zIndex: 2100, title: 'SQL计算器定义', 
				lock: true, width: '400px', height: '100px', opacity: 0.4, 
				button: [	
		   {name: '<t:mutiLang langKey="common.confirm"/>', callback: function (){}, focus: true},
		   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
	   ]}).zindex();*/
	}
	function refreshFactorTree(){
		var iframe = this.iframe.contentWindow;
		var sqlexpress = encodeURIComponent(iframe.$("#sqlcalcexpress").val());
		var sqlcalcname = iframe.$("#sqlcalctree").tree('getSelected').text;//选中的文本;
		var nodes =  iframe.$("#selecttabletree").tree('getChecked');
		//alert(nodes);
		var typeitemid = '';
		if (iframe.$("#iftypeitem").get(0).checked){
			typeitemid =  iframe.$("#typeitemtree").tree('getSelected').id;
		}	
		//var typeitemid =  iframe.$("#typeitemtree").tree('getSelected').id;
		//alert(typeitemid);
		var ifcheck=0;
		if (iframe.$("#iftypeitem").get(0).checked)
			ifcheck=1;
		
		var nodeids = '';
		var nodetexts = '';
		for (var i = 0; i < nodes.length; i++) {
			nodeids += nodes[i].id + ",";
			nodetexts += nodes[i].text + ",";
		}
		$.ajax({
			url : "defController.do?saveCalc&tablename="+tablenameold 
					+ "&sqlexpress=" + sqlexpress + "&sqlcalcname="
					+ sqlcalcname+"&ifcheck="+ifcheck+"&typeitemid="+typeitemid, //后台webservice里的方法名称 %2b 
			type : "post",
			dataType : "json",
			data:{"selecttableids":nodeids,"selecttablenames":nodetexts},
			//contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else
					$("#choosefactortree").tree("reload");
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function addCalc() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		//$.dialog.setting.zIndex = -1; 
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		p = $.dialog({content: 'url:defController.do?addSQLCalc&tablename='+ formulatable + '&formulaname=' + $('#formtree').tree("getSelected").text, zIndex: 9999, title: 'SQL计算器定义', 
				lock: true, width: '900px', height: '650px', opacity: 0.4, 
				button: [
			/* {name: '新增', callback: function (){addCalcName();return false;}},
			{name: '删除', callback:delCalcName}, */		
		   {name: '<t:mutiLang langKey="bpm.designer.page.save"/>', callback: refreshFactorTree, focus: true},
		   {name: '<t:mutiLang langKey="common.close"/>', callback: function (){}}
	   ]}).zindex();
		/*
		
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		add('SQL计算器设置', 'defController.do?addSQLCalcName&tablename='
				+ formulatable + '&formulaname=' + $('#formtree').tree("getSelected").text, 'addDemoList',
				800, 600);
		*/
		//showDialog();
	}
	function rowconditionsetup() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		isReloadTable = 2;
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		add('行范围条件设置', 'defController.do?rowCondition&tablename='
				+ formulatable + '&formulaname=' +  $('#formtree').tree("getSelected").text, 'addDemoList',
				600, 400);
	}
	function regionconditionsetup() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		isReloadTable = 3;
		var formulatable = $('#formtree').tree(
				'getParent',
				$('#formtree').tree('getParent',
						$('#formtree').tree("getSelected").target).target).id;
		add('区划范围设置', 'defController.do?regionCondition&tablename='
				+ formulatable + '&formulaname=' +  $('#formtree').tree("getSelected").text, 'addDemoList',
				300, 400);
	}
	function delFormulaName() {
		if (isLeaf == "0") {
			alert("请在公式列选择树中正确选择公式名称！");
			return;
		}
		var forumaName = $('#formtree').tree("getSelected").text;
		$.messager.confirm('确认','确定删除公式【'+forumaName+'】吗？',function(r){
		    if (r){    
		    	$.ajax({
					url : "defController.do?delFormulaName&tablename=" + tablenameold
							+ "&formulaname=" + forumaName, //后台webservice里的方法名称 %2b 
					type : "get",
					dataType : "json",
					contentType : "application/json",
					traditional : true,
					success : function(data) {
						isReloadTable = 1;
						if (data[0].success != "true")
							alert(data[0].erro);
						else {
							reloadTable();					
						}
					},
					error : function(msg) {
						alert("出错了！");
					}
				});
		    }    
		}); 
	}
	function delCalcName() {
		var iframe = this.iframe.contentWindow;
		//var sqlexpress = encodeURIComponent(iframe.$("#sqlcalcexpress").val());
		var calcName = iframe.$("#sqlcalctree").tree('getSelected').text;//选中的文本;
		//var calcName = $('#refedcalc option:selected').text();
		$.ajax({
			url : "defController.do?delCalcName&tablename=" + tablenameold
					+ "&sqlcalcname=" + calcName, //后台webservice里的方法名称 %2b 
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				if (data[0].success != "true")
					alert(data[0].erro);
				else {
					//getSQLCalc();
					//alert("SQL计算器删除成功！");
					$('#choosefactortree').tree('reload');
				}
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}

	function add(title, addurl, gname, width, height) {
		gridname = gname;
		createwindow(title, addurl, width, height);
	}
	function more() {
		if ($('#displayway')[0].innerText == "更少") {
			//$('#displayway')[0].innerText="更多";
			$('#displayway')[0].outerHTML = $('#displayway')[0].outerHTML
					.replace("更少", "更多");
			$('#formulalayout').layout('collapse', 'south');
		} else {
			$('#displayway')[0].outerHTML = $('#displayway')[0].outerHTML
					.replace("更多", "更少");
			$('#formulalayout').layout('expand', 'south');
		}
	}
	$(function() {
		$('#funif').bind('click', function() {
			setexpress("【如果】");
		});
		$('#funthen').bind('click', function() {
			setexpress("【那么】");
		});
		$('#funelse').bind('click', function() {
			setexpress("【否则】");
		});
		$('#funend').bind('click', function() {
			setexpress("【结束】");
		});
		$('#funplus').bind('click', function() {
			setexpress("【+】");
		});
		$('#funredu').bind('click', function() {
			setexpress("【-】");
		});
		$('#funmultiply').bind('click', function() {
			setexpress("【*】");
		});
		$('#fundivision').bind('click', function() {
			setexpress("【/】");
		});
		$('#funisnull').bind('click', function() {
			setexpress("【是空】");
		});
		$('#funnotnull').bind('click', function() {
			setexpress("【非空】");
		});
		$('#fungetint').bind('click', function() {
			setexpress("【取整】( )");
		});
		$('#funround').bind('click', function() {
			setexpress("【四舍五入】( )");
		});
		$('#funbig').bind('click', function() {
			setexpress("【大于】");
		});
		$('#funbige').bind('click', function() {
			setexpress("【大于等于】");
		});
		$('#funsmall').bind('click', function() {
			setexpress("【小于】");
		});
		$('#funsmalle').bind('click', function() {
			setexpress("【小于等于】");
		});
		$('#funand').bind('click', function() {
			setexpress("【并且】");
		});
		$('#funor').bind('click', function() {
			setexpress("【或者】");
		});
		$('#funequals').bind('click', function() {
			setexpress("【等于】");
		});

	});

	function setexpress(a) {
		var extendsblank = " " + a + " ";
		var ta = document.getElementById("calcexpress");
		if (ta.selectionStart != 0)
			expressstart = ta.selectionStart;
		var old = $("#calcexpress").val();
		if (expressstart == 0)
			ta.innerText = extendsblank;
		else
			ta.innerText = old.substring(0, expressstart) + extendsblank
					+ old.substring(expressstart, old.length);
		ta.value = old.substring(0, expressstart) + extendsblank
				+ old.substring(expressstart, old.length);
		if (taclick == 0)
			expressstart = expressstart + (extendsblank).length;
		else {
			taclick = 0;
		}
		moveCursor(expressstart);
	}
	function loadFormByType(jformCategory) {
		var url = 'cgFormHeadController.do?datagrid';
		$("#tablePropertyList").datagrid('reload', {
			jformCategory : jformCategory
		});
	}
	function moveCursor(s1) {
		var oTextarea = document.getElementById("calcexpress");
		var start = s1;
		var end = s1;
		if (isNaN(start) || isNaN(end)) {
			alert("位置输入错误");
		}
		if (1 == 2) { //IE模式未调试
			var oTextRange = oTextarea.createTextRange();
			var LStart = start;
			var LEnd = end;
			var start = 0;
			var end = 0;
			var value = oTextarea.value;
			for (var i = 0; i < value.length && i < LStart; i++) {
				var c = value.charAt(i);
				if (c != '\n') {
					start++;
				}
			}
			for (var i = value.length - 1; i >= LEnd && i >= 0; i--) {
				var c = value.charAt(i);
				if (c != '\n') {
					end++;
				}
			}
			oTextRange.moveStart('character', start);
			oTextRange.moveEnd('character', -end);
			//oTextRange.collapse(true);  
			oTextRange.select();
			oTextarea.focus();
		} else {
			oTextarea.select();
			oTextarea.selectionStart = start;
			oTextarea.selectionEnd = end;
		}
	}
	function taclick() {
		var oTextarea = document.getElementById("calcexpress");
		expressstart = oTextarea.selectionStart;
		taclick = 1;
	}
</script>

<div class="easyui-layout" fit="true" id="mainlayout" style="height:90%;">
	
	<!--左侧隐藏树  -->
	<!-- <div region="west" style="width: 0px;" title="公式选择" split="true"
		collapsed="true">
		<div class="easyui-panel" style="padding: 0px; border: 0px" fit="true"
			border="false">
			<ul id="formtree">
			</ul>
		</div>
	</div> -->
	<ul id="formtree" style="display:none;">
			</ul>
	<div region="center" style="border: 1px solid #E0ECFF;background: #E0ECFF;height:100%">
	
	
		<!-- 上侧 操作按钮 -->
		<div class="table_content" style="width:97.6%;height: 32px; vertical-align: middle; text-align: right; background: #F4F4F4;"><!-- #CCEEFF -->
				<%
					if (tablename==null||tablename.equals("")){
					%>
					<a href="#" class="easyui-linkbutton"
					iconCls="icon-add" onclick="addFormulaName();">新增</a> <a
					href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="delFormulaName();">删除</a> 
					<%} %>
					<a href="#"
					class="easyui-linkbutton" iconCls="icon-ok"
					onclick="checkFormula();">验证</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-save"
					onclick="saveFormula();">保存</a><a href="#" class="easyui-linkbutton"
					iconCls="icon-add" onclick="addCalc();">SQL计算器设置</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
			
				<!-- 左侧 表达式要素选择 -->
				<!-- <div style="border: 1px solid #B5D6E6; width:150px;height:130%; float:left;" overflow-x:auto;> -->
				<div class="left_content" style=" width:180px;height:95%; float:left; "> 
					<div style="width:200px;height:30px;background: linear-gradient(to bottom, #EFF5FF 0, #E0ECFF 100%); text-align:center;float:left;">表达式要素选择</div>
					<div class="easyui-panel" style="padding: 0px; border: 0px;width:150px; height:80%;overflow-x:auto;"
												fit="true" border="false">
						<ul id="choosefactortree" style="padding: 0px; border: 0px;width:180px; height:95%;overflow:auto;"></ul>
					</div>
					
				</div>
				<!--右侧   计算表达式   width:673px-->
				<div style="border-right: 1px solid #B5D6E6;margin-left:10px;width:calc(96% - 172px);height:95%;float:left;">
					 
					 <div style="width:100%;height:30px;background: linear-gradient(to bottom, #EFF5FF 0, #E0ECFF 100%); text-align: center;float:left;">计算表达式</div>
					 
					 		<textarea  name="calcexpress"  id="calcexpress" style="width: 100%; height: 200px;border: 1px solid #E0ECFF;"
								onclick="javascript:taclick();">
							</textarea>
					 
					 <div style="background: linear-gradient(to bottom, #EFF5FF 0, #E0ECFF 100%); text-align: center;height:30px">运算符</div>
				
					<div style=" background: #fafafa; width: 100%; height:200px; border: 0px solid black;text-align: center">
													
								<table  style=" margin:auto;background: #fafafa; text-align: center; width: 60%; height: 100%; border: 1px solid #ccc">
									<tr>
										<td><a href="#" class="easyui-linkbutton" id="funif">如果</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funthen">那么</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funelse">否则</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funend">结束</a></td>
									</tr>
									<tr>
										<td><a href="#" class="easyui-linkbutton" id="funplus">+</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funredu">-</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funmultiply">*</a></td>
										<td><a href="#" class="easyui-linkbutton" id="fundivision">/</a></td>
									</tr>
									<tr>
										<td><a href="#" class="easyui-linkbutton" id="funbig">大于</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funbige">》＝</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funsmall">小于</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funsmalle">《＝</a></td>
									</tr>
									<tr>
										<td><a href="#" class="easyui-linkbutton" id="funand">并且</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funor">或者</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funisnull">是空</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funnotnull">非空</a></td>
									</tr>
									<tr>
										<td><a href="#" class="easyui-linkbutton" id="fungetint">取整</a></td>
										<td colspan="2"><a href="#" class="easyui-linkbutton" id="funround">四舍五入</a></td>
										<td><a href="#" class="easyui-linkbutton" id="funequals">等于</a></td>
		
									</tr>
								</table>
					</div>
				<!-- 	
					<div style="background: linear-gradient(to bottom, #EFF5FF 0, #E0ECFF 100%);height:30px;width:100%; text-align: center">
						公式的适用范围				
					</div>
					
					 <div style="width:100%;height:70px;text-align: center;">
						<div style="width:50%;height:100%;float:left">
							<div >行范围条件</div>
							<div ><a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="rowconditionsetup();">行条件设置</a></div>
						</div>
						<div style="width:45%;height:100%;float:right">
							<div>区划范围</div>
							<div><a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="regionconditionsetup();">区划设置</a></div>
						</div>			
			
					</div> 
				-->
			
		</div>
	</div>
	
</div>