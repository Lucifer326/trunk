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
					url : "systemRegionController.do?deptSelectRegionGrid1&parentRegionId=${region.id}",// 请求的action路径
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {

							//var checkRegionId = $("#regionId").val();
							var checkRegionId='8a8a80d961c07c3c0161c0812eb40004';
							$.dialog.setting.zIndex = 9999;
							$
									.dialog(
											{
												content : "url:systemRegionController.do?deptSelectRegion1&checkRegionId="
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
		action="bssysDeployController.do?saveRootRegion">				
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${region.sysOrgCode }">				
		<fieldset class="step">			
			<div class="form">
				<label class="Validform_label">行政区划根节点: </label> 
				<input id="regionName" class="inputxt" value="${region.regionName}" datatype = "s"
					readonly="readonly"> <input id="regionId" name="id"
					type="hidden" class="inputxt" value="${region.id}">
				
					<a href="#" class="easyui-linkbutton" plain="true"
						icon="icon-search" id="departSearch" onclick="openRegionSelect();">选择</a>
				
			</div>
			<div class="form">
				 <label class="Validform_label"><span style="color:red;">*</span>末级显示级别:</label>
				<t:dictSelect field="regionLevel" typeGroupCode="101010" hasLabel="false" defaultVal="${region.regionLevel}" datatype="s" ></t:dictSelect>
			</div>			
		</fieldset>
	</t:formvalid>
</body>
</html>
