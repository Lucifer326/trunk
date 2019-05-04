<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部门信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
	//区划选择
	function openRegionSelect() {
		$
				.ajax({
					async : false,
					cache : false,
					type : 'POST',
					url : "systemRegionController.do?deptSelectRegionGrid&parentDepartId=${depart.TSPDepart.id}",// 请求的action路径
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {

							var checkRegionId = $("#regionId").val();

							$.dialog.setting.zIndex = 9999;
							$
									.dialog(
											{
												content : "url:systemRegionController.do?deptSelectRegion&parentDepartId=${depart.TSPDepart.id}&checkRegionId="
														+ checkRegionId,
												zIndex : 99900,
												title : '区划列表',
												lock : true,
												width : '400px',
												height : '350px',
												opacity : 0.4,
												button : [
														{
															name : '<t:mutiLang langKey="common.confirm"/>',
															callback : callbackDepartmentSelect,
															focus : true
														},
														{
															name : '<t:mutiLang langKey="common.cancel"/>',
															callback : function() {
															}
														} ]
											}).zindex();

						} else {

							tip(d.msg);
						}
					}
				});

	}

	function callbackDepartmentSelect() {
		var iframe = this.iframe.contentWindow;
		var treeObj = iframe.$("#regionSelect");
		var node = treeObj.tree('getSelected');
		if (node) {
			var id = node.id;
			var name = node.text;
			$('#regionName').val(name);
			$('#regionName').blur();
			$('#regionId').val(id);
		} else {
			$('#regionId').val('');
		}

	}

	function callbackClean() {
		$('#regionName').val('');
		$('#orgIds').val('');
	}
</script>
</head>

<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true"
		action="departController.do?saveDepart">
		<input id="id" name="id" type="hidden" value="${depart.id }">
		<input type="hidden" name="orgCode" value="${depart.orgCode }">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"> <span style="color:red;">*</span><t:mutiLang
						langKey="common.department.name" />:
				</label> <input name="departname" class="inputxt" type="text"
					value="${depart.departname }" datatype="s1-20"> <span
					class="Validform_checktip"><t:mutiLang
						langKey="departmentname.rang1to20" /></span>

			</div>
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="position.desc" />:
				</label> <input name="description" class="inputxt"
					value="${depart.description }" datatype="s0-50" ignore="ignore">
			</div>

			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="parent.depart" />:
				</label> <input id="parentDepartId" name="TSPDepart.id"
					value="${depart.TSPDepart.id}" type="hidden"> <input
					id="parentDepartname" name="parentDepartname" class="inputxt"
					value="${depart.TSPDepart.departname}" type="text"
					disabled="disabled">
			</div>

			<t:dictSelect field="orgType" typeGroupCode="orgtype"
				emptyOption="true" defaultVal="${depart.orgType}"></t:dictSelect>
			<div class="form">
				<label class="Validform_label"><span style="color:red;">*</span> 行政区划: </label> <input
					id="regionName" class="inputxt" value="${region.regionName}" datatype = "s"
					readonly="readonly"> <input id="regionId" name="regionId"
					type="hidden" class="inputxt" value="${depart.regionId}">
				<c:if test="${hiddenSelectRegionBtn != '1'}">
					<a href="#" class="easyui-linkbutton" plain="true"
						icon="icon-search" id="departSearch" onclick="openRegionSelect();">选择</a>
				</c:if>
			</div>
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.mobile" />:
				</label> <input name="mobile" class="inputxt" value="${depart.mobile }"
					datatype="/^$|^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\d{8}$/"
					errormsg="手机号码输入有误" ignore="ignore"> <span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<!--  -->
				<label class="Validform_label"> <t:mutiLang
						langKey="common.fax" />:
				</label> <input name="fax" class="inputxt" value="${depart.fax }"
					datatype="/^$|^(\d{3,4}-)?\d{7,8}$/" errormsg="传真号码输入有误" ignore="ignore"> <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> <t:mutiLang
						langKey="common.address" />:
				</label> <input name="address" class="inputxt" value="${depart.address }"
					datatype="s0-50" errormsg="地址输入有误" ignore="ignore"> <span
					class="Validform_checktip"></span>
			</div>
		</fieldset>
	</t:formvalid>
</body>
</html>
