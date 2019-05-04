<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
<script>
	//选择部门
	function openDepartmentSelect() {
		var ids = $('#orgIds').val();
		$.dialog({content: 'url:departController.do?departSelect&ids=' + ids, zIndex: 99999, title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
		   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
		   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
	   ]}).zindex();
	}
	
	//选择部门回调方法
	function callbackDepartmentSelect() {
		var iframe = this.iframe.contentWindow;
		var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length>0){
			var ids='',names='';
			for(i=0;i<nodes.length;i++){
				var node = nodes[i];
				ids += node.id+',';
				names += node.name+',';
			}
			$('#departname').val(names);
			$('#departname').blur();		
			$('#orgIds').val(ids);
			cascadeChangeRoleIds(ids);
		}
	}
	
	//由于用户角色依赖于部门，因此用户的组织机构变更时，其用户角色需同步改动
	function cascadeChangeRoleIds(orgIds){
		orgIds = "," + orgIds + ",";
		var curRoleIds = $('#roleId').val();
		var curRoleNames = $('#roleName').val();
		if(curRoleIds==""){//获取当前的roleId，若roleId为空，则直接返回
			return ;
		}
		
		var newRoleIds = "";
		var newRoleNames = "";
		var roleIdArray = curRoleIds.split(",");
		var roleNameArray = curRoleNames.split(",");
		for(var i=0;i<roleIdArray.length;i++) {
			//roleId格式：orgId_roleId
			var orgId = roleIdArray[i];
			if(orgId == "") {
				continue;
			}
			orgId = "," + orgId.substr(0,orgId.indexOf("_")) + ",";//截取出orgId
			if(orgIds.indexOf(orgId) >= 0){//用户角色涉及的部门依然存在于用户的归属部门
				newRoleIds = newRoleIds + roleIdArray[i] + ",";
				newRoleNames = newRoleNames + roleNameArray[i] + ",";
			}
		}
		$('#roleName').val(newRoleNames);
	 	$('#roleName').blur();		
	 	$('#roleId').val(newRoleIds);	
	}
	
	//清空组织机构
	function callbackClean(){
		$('#departname').val('');
		$('#orgIds').val('');	
	}
	
	//选择角色
	function openRoleSelect() {
		var orgIds = $('#orgIds').val();
		if(orgIds=="") {
			alert("请先选择组织机构");
			return;
		}
		var ids = $('#roleId').val();
		$.dialog({content: 'url:userController.do?roleSelect&orgIds=' + orgIds + '&ids=' + ids, zIndex: 99999, title: '角色列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
		   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackRoleSelect, focus: true},
		   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
	   ]}).zindex();
	}
		
	//选择角色回调方法
	function callbackRoleSelect() {
		var iframe = this.iframe.contentWindow;
		var treeObj = iframe.$.fn.zTree.getZTreeObj("roleSelect");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length>0){
			var ids='',names='';
			for(i=0;i<nodes.length;i++){
		    	var node = nodes[i];
		    	ids = ids + node.id+',';
		    	names = names + node.getParentNode().name + "_" + node.name+',';
		 	}
		 	$('#roleName').val(names);
		 	$('#roleName').blur();		
		 	$('#roleId').val(ids);		
		}
	}
	
	//清空角色
	function cleanRole(){
		$('#roleName').val('');
		$('#roleId').val('');	
	}
	
</script>
</head>
<body style="overflow-y: auto">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="userController.do?saveUser">
	<c:if test="${user.id!=null }">
		<input id="id" name="id" type="hidden" value="${user.id }">
	</c:if>
	<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.username"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="s2-30" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to30"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.cardId"/>: </label></td>
			<td class="value">
                <input id="cardId" class="inputxt" name="cardId" value="${user.cardId}"  datatype="/^(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)$/" errormsg="身份证号码不正确">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.cardid"/></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.real.name"/>: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="s2-10">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		<c:if test="${user.id==null }">
			<tr>
				<td align="right"><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.password"/>: </label></td>
				<td class="value">
                    <input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
                    <span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
                </td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.repeat.password"/>: </label></td>
				<td class="value">
                    <input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！">
                    <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
                </td>
			</tr>
		</c:if>
		<tr>
			<td align="right"><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.department"/>: </label></td>
			<td class="value">
                
                <input id="departname" name="departname" type="text" readonly="readonly" class="inputxt" datatype="*" value="${departname}">
                <input id="orgIds" name="orgIds" type="hidden" value="${orgIds}">
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
                <c:if test="${orgUserRelation==1 }">
	                <span class="Validform_checktip">
	                <t:mutiLang langKey="please.muti.department"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"><span style="color:red;">*</span><t:mutiLang langKey="common.role"/>: </label></td>
			<td class="value" nowrap>
                <input name="roleId" type="hidden" value="${roleId}" id="roleId">
                <input name="roleName" height="50px;" class="inputxt" value="${roleName }" id="roleName" readonly="readonly" datatype="*" />
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="roleSearch" onclick="openRoleSelect()">选择</a>
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="roleRedo" onclick="cleanRole()">清空</a>          
                <c:if test="${roleUserRelation==1 }">
	                <span class="Validform_checktip">
	                <t:mutiLang langKey="role.muti.select"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="m" errormsg="手机号码不正确" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.tel"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="officePhone" value="${user.officePhone}" datatype="n" errormsg="办公室电话不正确" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.common.mail"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="email" value="${user.email}" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
		     <td align="right">
		      <label class="Validform_label">
		       	<span style="color:red;">*</span>工作流引擎:
		      </label>
		     </td>
		     <td class="value">
		      <input name="activitiSync" type="radio" datatype="min" min="1" <c:if test="${user.activitiSync eq 1}">checked="true"</c:if> value="1">
		     	 同步
		      <input name="activitiSync" type="radio" <c:if test="${user.activitiSync eq 0}">checked="true"</c:if> value="0">
		     	 不同步
		      <span class="Validform_checktip">是否同步工作流引擎</span>
		     </td>
    	</tr>
	</table>
</t:formvalid>
</body>