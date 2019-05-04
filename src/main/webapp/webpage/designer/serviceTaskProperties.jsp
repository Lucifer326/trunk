<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
var tid = '${id}';
var task = workflow.getFigure(tid);
populateTaskProperites();
function saveTaskProperties(){
	task.taskId=$('#id').val();
	task.taskName=$('#name').val();
	task.expression=$('#expression').val();
	task.documentation=$('#documentation').val();
	task.serviceType=$('#serviceType').val();
	task.resultVariable=$('#resultVariable').val();
	tip("保存成功 !");
}
function populateTaskProperites(){
	$('#id').val(task.taskId);
	$('#name').val(task.taskName);
	$('#expression').val(task.expression);
	$('#documentation').val(task.documentation);
	$('#serviceType').val(task.serviceType);
	$('#resultVariable').val(task.resultVariable);
}
</script>
<div id="task-properties-layout" class="easyui-layout" fit="true">
	<div id="task-properties-toolbar-panel" region="north" border="false" style="height:30px;background:#E1F0F2;">
		<a href="##" id="sb2" class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveTaskProperties()"><t:mutiLang langKey="bpm.designer.page.save"/></a>
	</div>
	<div id="task-properties-panel" region="center" border="true">
		<div id="task-properties-accordion" class="easyui-accordion" fit="true" border="false">
			<div id="general" title="<t:mutiLang langKey="bpm.designer.page.servertask.mainproper"/>" selected="true" class="properties-menu">
				<table id="general-properties">
					<tr>
						<td align="right">Id:</td>
						<td><input type="text" id="id" name="id"  value=""/></td>
					</tr>
					<tr>
						<td align="right"><t:mutiLang langKey="bpm.designer.page.servertask.name"/>:</td>
						<td><input type="text" id="name" name="name"  value=""/></td>
					</tr>
					<tr>
						<td align="right"><t:mutiLang langKey="bpm.designer.page.servertask.doc"/>:</td>
						<td><textarea id="documentation" name="documentation" cols="15" rows="4"></textarea></td>
					</tr>
					<tr>
						<td align="right"><t:mutiLang langKey="bpm.designer.page.servertask.type"/>:</td>
						<td><select id="serviceType" >
								<option value="javaClass">javaClass</option>
								<option value="expression" >expression</option>
								<option value="delegateExpression" >delegateExpression</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right"><t:mutiLang langKey="bpm.designer.page.servertask.expression"/>:</td>
						<td>
						<textarea id="expression" name="expression" cols="25" rows="5"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right"><t:mutiLang langKey="bpm.designer.page.servertask.resultVar"/>:</td>
						<td><input type="text" id="resultVariable" name="resultVariable"  value=""/></td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.服务任务，当流程执行到服务任务时，执行相应的服务内容。</div>
						<div>2.类型:<br/>&nbsp;&nbsp;&nbsp;&nbsp;javaClass,执行java类;<br/>&nbsp;&nbsp;&nbsp;&nbsp;expression：表达式，<br/>&nbsp;&nbsp;&nbsp;&nbsp;delegateExpression：Spring容器中bean。</div>
						<div>3.执行内容：具体的类全名，或表达式。</div>
						<div>4.返回变量：任务返回处理结果保存到流程变量中的变量名称。</div>
				</fieldset>
			</div>
		</div>
	</div>
</div>