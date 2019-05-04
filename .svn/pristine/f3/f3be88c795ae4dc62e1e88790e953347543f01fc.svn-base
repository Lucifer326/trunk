<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,autocomplete"></t:base>
<script type="text/javascript">
	var formulaname = "";
	var formulacontent = "";
	function addf(){
		//add('新增公式', 'defController.do?define&type=add&tablename=TMP&formulaname=工资合计虚列的公式1', 'addDemoList', 1000, 630);
		$.dialog({content: 'url:defController.do?define&type=add&tablename=bspr_fam', 
				zIndex: 50, 
				title: '新增公式-当前表名【TMP】', 
				lock: true, 
				width: '1000px', 
				height: '660px', 
				opacity: 0.4 ,
				button : [ {
				name : '返回',
				callback : function() {
					iframe = this.iframe.contentWindow;
					iframe.saveFormula();
					formulacontent=iframe.$("#calcexpress").val();
					//alert(formulacontent);
					formulaname =  iframe.$('#formtree').tree("getSelected").text;
					//alert(formulaname);
				},
				focus : true
			}, {
				name : '取消',
				callback : function() {

				}
			} ]
			}).zindex();
	}
	function update(){
		//add('修改公式', 'defController.do?define&type=update&tablename=TMP&formulaname=工资合计虚列的公式1', 'addDemoList', 1000, 630);
		$.dialog({content: 'url:defController.do?define&type=update&tablename=bspr_fam&formulaname=4e0a25819b47437ca60a89b87f1d37e1', zIndex: 50, title: '修改公式-当前表名【TMP】', 
			lock: true, width: '1000px', height: '630px', opacity: 0.4 ,button : [ {
				name : '返回',
				callback : function() {
					iframe = this.iframe.contentWindow;
					iframe.saveFormula();
					formulacontent=iframe.$("#calcexpress").val();
					//alert(formulacontent);
					formulaname =  iframe.$('#formtree').tree("getSelected").text;
					//alert(formulaname);
				},
				focus : true
			}, {
				name : '取消',
				callback : function() {

				}
			} ]
			}).zindex();
		//工资合计的公式2
	}
	function ehcache (){
		$.ajax({
			url : "calcInterface.do?calc",
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	//url : "calcInterface.do?SFormulaComputeAllArg2&tablename=TMP&formulaname="+a+"&g=10&d=200&f=300&@MEMBERID@=1&b22="+$("#a").val(),
	function calc(a){
		$.ajax({
			url : "calcInterface.do?SFormulaComputeAllArg2&tablename=bspr_fam&formulaname="+a+"&@FUNDSTANDARDID@=8a8a80235e7fc13c015e7ff1ec1f0013",
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function getarg(a){
		$.ajax({
			url : "calcInterface.do?GetFromulaComputeArg&tablename=TMP&formulaname="+a,
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function getformulaOriginal(a){
		$.ajax({
			url : "calcInterface.do?getformulaOriginal&tablename=TMP&formulaname="+a,
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function getTPProcessnodeByProcessKey(a){
		$.ajax({
			url : "processController.do?getTPProcessnodeByProcessKey&processkey="+a,
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	
	function workflowsetup(){
		//add('新增公式', 'defController.do?define&type=add&tablename=TMP&formulaname=工资合计虚列的公式1', 'addDemoList', 1000, 630);
		$.dialog({content: 'url:processController.do?processSetup', zIndex: 50, title: '流程设置', 
			lock: true, width: '400px', height: '550px', opacity: 0.4 ,button : [ {
				name : '确定',
				callback : function() {
					iframe = this.iframe.contentWindow;
					var processcheck =  iframe.$('#processsetuptree').tree("getChecked");
					for(var i = 0;i<processcheck.length;i++){
						if(iframe.$('#processsetuptree').tree("isLeaf",processcheck[i].target)){
							var p = iframe.$('#processsetuptree').tree("getParent",processcheck[i].target);
							alert(p.id);
							alert("name=["+processcheck[i].text +"],id=["+ processcheck[i].id+"]");
						}
						
					}
				},
				focus : true
			}, {
				name : '取消',
				callback : function() {

				}
			} ]
			}).zindex();
	}
	function getBpmLogsByTaskId(){
		$.ajax({
			url : "taskController.do?getBpmLogsByTaskId&taskid="+$("#c").val(),
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function findBpmLogsByBpmIDAndTaskID(){
		
		$.ajax({
			url : "taskController.do?findBpmLogsByBpmIDAndTaskID&taskname="+$("#taskname").val()+"&bpmid="+$("#bpmid").val(),
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
		
	}
	function droolsController(){
		$.ajax({
			url : "droolsController.do?test",
			type : "get",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	
	function startprocess(){
		$.ajax({
			url : "activitiController.do?startOnlineProcess&configId=bspr_fam_temp&processkey=cityDIBaoShenQing&getinstid=1&id="+$("#b").val(),
			//url : "activitiController.do?startOnlineProcess&configId=bspr_fam_temp&processkey=cityDiBaoShenPi&getinstid=1&id=49aaaecb743546718ca5322845a70623",
			type : "post",
			dataType : "json",
			contentType : "application/json",
			traditional : true,
			success : function(data) {
				alert(data.msg);
			},
			error : function(msg) {
				alert("出错了！");
			}
		});
	}
	function ImportXls(a,b){
		alert(a+b);
	}
</script>
<table style="border-collapse:separate; border-spacing:0px 10px;">
	<tr>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="addf();" style="overflow:hidden;">公式新增</a></td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="update();">公式修改-222</a></td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="getarg('222');">获取公式参数列</a></td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="calc('ebfd35e7d9a740a7b9f0953e32d2b6cb');">公式计算-工资合计虚列的公式1</a></td>
			<!-- 工资合计虚列的公式1 -->
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="calc('f098c14d0bea44ad8c14358150365d65');">公式计算-222</a></td>
		<td>录入1或2进行计算<input type="text" value="1" id="a"></td>
		
	</tr>
	<tr><td></td></tr>
	<tr>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="getformulaOriginal('222');">得到公式原文</a></td>
			<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="droolsController()">规则引擎测试</a></td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="ehcache();">缓存测试</a></td>
	</tr>
	<tr><td>----------------------------</td>
	<td>------------------------------</td>
	<td>------------------------------</td>
	<td>------------------------------</td>
	<td>-----------------------------</td></tr>
	<tr>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="workflowsetup();">工作流设置</a></td>
		<td>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="getTPProcessnodeByProcessKey('processhorse2')">获取工作流任务节点</a></td>
		
		</td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="getBpmLogsByTaskId()">通过任务ID获取流程审批意见</a></td>
		<td><input type="text" value="请输入任务ID" id="c"></td>
		
	</tr>
	<tr>
	<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="startprocess();">启动流程城市低保</a></td>
			<td><input type="text" value="请录入业务数据ID" id="b"></td>
		<td><a href="#" class="easyui-linkbutton" iconCls="icon-add"
			onclick="findBpmLogsByBpmIDAndTaskID()">by任务名称和流程ID获取审批意见</a></td>
		<td><input type="text" value="请输入流程实例ID" id="bpmid"></td>	
		<td><input type="text" value="请输入任务名称" id="taskname"></td>	
	</tr>
	<tr><td>
	<a href="taskController.do?goMyTaskAlreadyDoList" class="easyui-linkbutton" iconCls="icon-add"
			>查看我的已办任务列表</a></td>
	<td><a href="taskController.do?goMyTaskStartList" class="easyui-linkbutton" iconCls="icon-add"
			>查看我发起的任务列表</a></td>
	<td><a href="taskController.do?claimAndgoTaskTab&taskId=37447" class="easyui-linkbutton" iconCls="icon-add"
			>任务签收和业务办理合二为一</a></td>
	<td>
	<a href="taskController.do?goMyTaskList" class="easyui-linkbutton" iconCls="icon-add"
			>查看我的代办任务列表</a></td>
	<td></td>
	</tr>
</table>

<h:datagrid name="systemRegionList" checkbox="true" fitColumns="false" title="系统区划表" actionUrl="systemRegionController.do?datagrid" idField="id" fit="true" queryMode="group">
	   <h:dgToolBar title="录入" icon="icon-add" url="systemRegionController.do?goAdd" funname="add" styleClass="btn-primary" width="800px" height="520px"></h:dgToolBar>
	   <h:dgToolBar title="导入" icon="icon-put" funname="ImportXls('123456','systemRegionController.do?goAdd')"  styleClass="btn-submit" width="800px" height="520px"></h:dgToolBar>
	 </h:datagrid>