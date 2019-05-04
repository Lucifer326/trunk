<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>SQL计算器定义</title>
<%
	String tablename = request.getParameter("tablename");
	String formulaname = request.getParameter("formulaname");
%>
<t:base type="jquery,easyui,tools"></t:base>
<style type="text/css">
.table_content table {
	width: 100%;
	height: 97%;
	border: 0;
	border-collapse: collapse;
	font-size: 12px;
}

.table_content table tr:hover { 
	background-color: #FFFF;
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

</style>
<script type="text/javascript">
var url = "";
var expressstart = 0;
var taclick = 0;
var typeid = "";
$(function(){
		
	$('#sqlcalctree').tree(
			{
				animate : true,
				url : 'defController.do?getSQLCalcName&tablename=&formulaname=<%=formulaname%>',
				onClick : function(node) {
					if (node.id=='#'){
						var ta = document.getElementById("sqlcalcexpress");
						ta.innerText = "";
						ta.value = "";
						return;
					}
					var calc = node.text;
					$.ajax({
						url : "defController.do?getSQLCalcExpress&sqlcalcname="
								+ node.text, //后台webservice里的方法名称 %2b 
						type : "get",
						dataType : "json",
						contentType : "application/json",
						traditional : true,
						success : function(data) {
							var ta = document.getElementById("sqlcalcexpress");
							var ret = data.msg;
							
							$("#iftypeitem").get(0).checked=false;
							if ((ret.charAt(ret.indexOf('^')+1))=='1')
								$("#iftypeitem").get(0).checked=true;
							ta.innerText = ret.substr(0,ret.indexOf('^'));
							ta.value = ret.substr(0,ret.indexOf('^'));
							typeid = ret.substring(ret.indexOf('#')+1,ret.indexOf(',',ret.indexOf('#')));
							$('#selecttabletree').tree(
									{
										animate : true,
										url : 'defController.do?getTableList&calcname='+calc,//ret.substr(ret.indexOf('@')),
										checkbox:true
													});
							var node = $('#typeitemtree').tree('find', typeid);
							if (node!=null&&node!='null'){
								$('#typeitemtree').tree('select', node.target);
							}
						},
						error : function(msg) {
							alert("出错了！");
						}
					});
				}
			});
	});
$(function(){
$('#typeitemtree').tree(
		{
			animate : true,
			url : 'defController.do?getAllDictType&nodeid=',
			onDblClick : function(node) {
				if (node.id =="#")
					return;
				if ($('#typeitemtree').tree('isLeaf', node.target))
					setexpress("［"+$('#formtree').tree('getParent',
							node.target).text+'_'+ node.text + "］");
				else
					setexpress("【" + node.text + "】");
					//setexpress("{" + node.text + "}");
					//setexpress("[" + node.text + "]");
			}
		});
});

	$(function(){
		$("#wrapper").hide();
	})
function setexpress(a) {
		var extendsblank = " " + a + " ";
		var ta = document.getElementById("sqlcalcexpress");
		if (ta.selectionStart != 0)
			expressstart = ta.selectionStart;
		var old = $("#sqlcalcexpress").val();
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
	function moveCursor(s1) {
		var oTextarea = document.getElementById("sqlcalcexpress");
		var start = s1;
		var end = s1;
		if (isNaN(start) || isNaN(end)) {
			alert("位置输入错误");
		}
		if (1 == 2) { //IE模式未调试
			
		} else {
			oTextarea.select();
			oTextarea.selectionStart = start;
			oTextarea.selectionEnd = end;
		}
	}
	function taclick() {
		var oTextarea = document.getElementById("sqlcalcexpress");
		expressstart = oTextarea.selectionStart;
		taclick = 1;
	}
	
	///////////////////////////////////////////////////
	function addCalcName1() {
							
		var formulatable = '${tablename}'; 
		 
		add1('新增SQL计算器', 'defController.do?addSQLCalcName&tablename='
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
	
	function add1(title, addurl, gname, width, height) {
		gridname = gname;
		createwindow1(title, addurl, width, height);
	}
	/**
	 * 创建添加或编辑窗口
	 * 
	 * @param title
	 * @param addurl
	 * @param saveurl
	 */
	function createwindow1(title, addurl,width,height) {
		width = width?width:700;
		height = height?height:400;
		if(width=="100%" || height=="100%"){
			width = window.top.document.body.offsetWidth;
			height =window.top.document.body.offsetHeight-100;
		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		if(typeof(windowapi) == 'undefined'){
			$.dialog({
							
				content: 'url:'+addurl,
				lock : true,
				zIndex:999999,
				width:width,
				height:height,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	
			    	
			    	iframe = this.iframe.contentWindow;
			    	
			    	saveObj();
			    	setTimeout("reloadTree()",500);
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});
		}else{
			
			W.$.dialog({
				content: 'url:'+addurl,
				lock : true,
				width:width,
				zIndex:999999,
				height:height,
				parent:windowapi,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    			    	
			    	iframe = this.iframe.contentWindow;
					saveObj();	
					setTimeout("reloadTree()",500);
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});
		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		
	}
	function reloadTree(){
		$('#sqlcalctree').tree('reload');
	}
	
	//删除
	function delCalcName() {
		//var iframe = this.iframe.contentWindow;
		//var sqlexpress = encodeURIComponent(iframe.$("#sqlcalcexpress").val());
		 //var calcName = iframe.$("#sqlcalctree").tree('getSelected').text;//选中的文本;
		
		//var calcName = $('#refedcalc option:selected').text();

			 var calcName = $("#sqlcalctree").tree('getSelected').text;//选中的文本;
				$.ajax({
					url : "defController.do?delCalcName&tablename=" + '${tablename}'
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
							//$('#choosefactortree').tree('reload');
							alert('删除成功');
							$('#sqlcalctree').tree('reload');
						}
					},
					error : function(msg) {
						alert("出错了！");
					}
				}); 
			 
	}
</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<div class="table_content">
		<div style="float:right;">
		 <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addCalcName1();">新增</a>
		 <a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="delCalcName();">删除</a> 
	</div>
	<table style="width: 100%; height: 550px"  border="1" >
		
		<tr>
			<td rowspan="2"
				style="width: 30%; height: 100%; vertical-align: top;">
				<ul id="sqlcalctree"></ul>
			</td>
			<t:formvalid formid="formobj" layout="div" dialog="true" action="defController.do?saveCalc&tablename=222">
			<td colspan="2" height="40" style="line-height:40px; vertical-align:middle" class="checkboxlabel">&nbsp;&nbsp;&nbsp;
			<input type="checkbox" id="iftypeitem" name="iftypeitem" style="line-height:40px; vertical-align:middle; display:inline-block"><label>是否来源于字典</label>
			</td>			
			<td rowspan="2"
				style="width: 30%; height: 100%; vertical-align: top;">
				<div id="getpicture"
                style="border: 1px solid #FFFF;overflow:auto; height:615px;">
                <ul id="selecttabletree"></ul>
            </div>
				
			</td>
		</tr>
		<tr>
			<td>
					<!-- <fieldset class="step"> -->
					<input type="hidden" name="tablename" id="tablename"
						value="${tablename}">
					<input type="hidden" name="formulaname" id="formulaname"
						value="${formulaname}">
					<input type="hidden" name="nodes" id="nodes" value="">
					<textarea name="sqlcalcexpress" cols=40 rows=21 id="sqlcalcexpress"
						style="height: 100%" onclick="javascript:taclick();"></textarea>
					<!-- </fieldset> -->
				</td>
				
				<td style="width: 20%; height: 550px; vertical-align: top;">
				<ul id="typeitemtree" style="height:550px; overflow:auto"></ul>
			</td>
			</t:formvalid>
		</tr>
		
	</table>
	 
	
</div>
</body>
</html>
