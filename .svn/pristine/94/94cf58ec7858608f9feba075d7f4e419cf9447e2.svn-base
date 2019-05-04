<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css"
	href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
	var setting = {

		check : {
			enable : false,
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onExpand : zxTreeOnExpand,
			onClick : onClick
		}
	};
	function onClick(event, treeId, treeNode) {
		var departname = treeNode.name;
		var queryParams = $('#userList').datagrid('options').queryParams;
		queryParams.orgIds = treeNode.id;
		$('#userList').datagrid('options').queryParams = queryParams;
		$("#userList").datagrid("reload");
	}
	//加载展开方法
	function zxTreeOnExpand(event, treeId, treeNode) {
		var children = treeNode.children;
		if (children != undefined) {//已有子节点，直接返回，不在异步加载，避免出现重复节点
			return;
		}

		var treeNodeId = treeNode.id;
		$.post('departController.do?getDepartInfo', {
			parentid : treeNodeId
		}, function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var dbDate = eval(d.msg);
				var tree = $.fn.zTree.getZTreeObj("departSelect");
				tree.addNodes(treeNode, dbDate);
			}
		});
	}
	//默认展开到第二级的方法
	function nextzxtree(treeId, treeNode) {
		var children = treeNode.children;
		if (children != undefined) {//已有子节点，直接返回，不在异步加载，避免出现重复节点
			return;
		}
		var treeNodeId = treeNode.id;
		$.post('departController.do?getDepartInfo', {
			parentid : treeNodeId
		}, function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var dbDate = eval(d.msg);
				var tree = $.fn.zTree.getZTreeObj("departSelect");
				tree.addNodes(treeNode, dbDate);
			}
		});
	}

	//加载数据
	$(function() {
		$.post('departController.do?getDepartInfo', function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var dbDate = eval(d.msg);
				$.fn.zTree.init($("#departSelect"), setting, dbDate);
				var tree = $.fn.zTree.getZTreeObj("departSelect");
				//默认展开到第二级
				alltrees = tree.getNodes();

				for (i = 0; i < alltrees.length; i++) {
					if (tree.getNodes()[i].isParent) {
						nextzxtree("departSelect", alltrees[i]);
					}
				}
			}
		});
	});
</script>
<div class="easyui-layout" style="width: 100%; height: 100%;">
	<div data-options="region:'west',split:true"
		title="<t:mutiLang langKey='common.department'/>"
		style="width: 200px;">
		<ul id="departSelect" class="ztree" style="margin-top: 30px;"></ul>
	</div>
	<div data-options="region:'center'">
		<t:datagrid name="userList" title="common.user.list"
			actionUrl="userController.do?datagrid" fit="true" fitColumns="true"
			idField="id" queryMode="group" sortName="createDate" sortOrder="desc">
			<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="common.username" sortable="false" field="userName"
				query="true"></t:dgCol>
			<t:dgCol title="common.department" sortable="false"
				field="userOrgList.tsDepart.departname" query="false"></t:dgCol>
			<t:dgCol title="common.real.name" sortable="false" field="realName"
				query="true"></t:dgCol>
			<t:dgCol title="common.role" sortable="false" field="userKey"></t:dgCol>
			<t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
			<t:dgCol title="common.createtime" field="createDate"
				formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
			<t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
			<t:dgCol title="common.updatetime" field="updateDate"
				formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
			<t:dgCol title="common.status" sortable="false" field="status"
				replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
			<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
			<t:dgFunOpt funname="lockObj(id)" exp="status#eq#1"
				title="common.lock.user"></t:dgFunOpt>
			<t:dgFunOpt funname="unlockObj(id)" exp="status#eq#0"
				title="common.unlock.user"></t:dgFunOpt>
			<t:dgFunOpt funname="userCardIdInfo(userName)" title="身份证变更记录"></t:dgFunOpt>
			<t:dgDelOpt title="common.delete"
				url="userController.do?del&id={id}&userName={userName}" />
			<t:dgToolBar title="common.add.param" langArg="common.user"
				icon="icon-add" onclick="addUser()"></t:dgToolBar>
			<t:dgToolBar title="common.edit.param" langArg="common.user"
				icon="icon-edit" url="userController.do?addorupdate"
				funname="update"></t:dgToolBar>
			<t:dgToolBar title="common.password.reset" icon="icon-edit"
				url="userController.do?changepasswordforuser" funname="update"></t:dgToolBar>
			<t:dgToolBar title="excelOutput" icon="icon-putout"
				funname="ExportXls"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	function addUser() {
		var treeObj = $.fn.zTree.getZTreeObj("departSelect");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length == 0) {
			alert("请选择要添加用户的组织机构");
			return;
		}

		var _url = "userController.do?addorupdate&departId=" + nodes[0].id;
		add('<t:mutiLang langKey="common.add.param" langArg="common.user"/>',
				_url, "userList", '800px', '450px');
	}
	function lockObj(id) {

		var url = "userController.do?lock&lockvalue=0&id=" + id;
		$.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>',
				function() {
					lockuploadify(url, '&id');
				}, function() {
				});
	}
	function unlockObj(id) {

		var url = "userController.do?lock&lockvalue=1&id=" + id;
		$.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>',
				function() {
					lockuploadify(url, '&id');
				}, function() {
				});
	}
<%--update-begin--Author:zhoujf  Date:20170306 for：TASK #1099 【工作流完善】代理功能(代理的有效时间)必须是同步到工作流的人 --%>
	function userCardIdInfo(userName) {
		var url = "userController.do?userCardInfo&userName=" + userName
		createdetailwindow("身份证变更记录", url, 600, 300);
	}
<%--update-begin--Author:zhoujf  Date:20170306 for：TASK #1099 【工作流完善】代理功能(代理的有效时间)必须是同步到工作流的人 --%>
	function lockuploadify(url, id) {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数

			},
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					reloadTable();
				}
			}
		});
	}

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'userController.do?upload', "userList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("userController.do?exportXls", "userList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("userController.do?exportXlsByT", "userList");
	}
</script>
