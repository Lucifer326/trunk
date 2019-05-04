<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加视图</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="ModelViewController.do?saveTsmv">
		<input name="id" type="hidden" value="${mv.id}">
		<fieldset class="step">
			<%--update-end--Author:zhangguoming  Date:20140509 for：云桌面图标管理--%>
			<div class="form" id="funorder">
				<label class="Validform_label"> <t:mutiLang langKey="序号" />:
				</label> <input name="functionOrder" class="inputxt" value="${oid}"
					datatype="n1-3">
			</div>


			<div class="form">

				<label class="Validform_label"> <t:mutiLang langKey="视图名称" />:
				</label> <input name="functionName" class="inputxt"
					value="${mv.functionName}" datatype="s2-50"> <span
					class="Validform_checktip"> <t:mutiLang
						langKey="menuname.rang4to15" />
				</span>
			</div>

			<div class="form" style="display: none">
				<label class="Validform_label"> <t:mutiLang langKey="是否叶子" />:
				</label> <select name="functionLevel" id="functionLevel" datatype="*"
					style="display: none">
					<option
						<c:if test="${mv.tsModelview eq null}"> value="0" selected="selected"</c:if>>
						<t:mutiLang langKey="根" />
					</option>
					<option
						<c:if test="${mv.tsModelview ne null}"> value="1" selected="selected"</c:if>>
						<t:mutiLang langKey="叶子" />
					</option>
				</select> <span class="Validform_checktip"></span>
			</div>
			<div class="form" id="pfun" style="display: none">

				<input id="functionId" name="tsModelview.id" style="display: none;"
					value="${mv.tsModelview.id}"">
			</div>
			<div class="form" id="funurl">

				<label class="Validform_label"> <t:mutiLang langKey="原视图路径" />:
				</label> <input id="viewnameori" name="functionUrl" class="inputxt"
					value="${mv.functionUrl}">
					 <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-search"
					id="departSearch" onclick="openmvSelect();">选择</a> 
					
					<a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-redo"
					id="departRedo" onclick="callbackClean()">清空</a>

				
			</div>


			<div class="form" id="funurlt">
				<label class="Validform_label"> <t:mutiLang langKey="目标视图路径" />:
				</label> <input name="functionUrlt" class="inputxt"
					value="${mv.functionUrlt}">
			</div>

		</fieldset>
	</t:formvalid>
</body>
</html>

<script type="text/javascript">
function callbackClean(){
	$('#viewnameori').val('');
		
}

function openmvSelect(){
	
		$.dialog.setting.zIndex = 999; 
		$.dialog({content: "url:ModelViewController.do?mvtreepage", zIndex: 9999, title: '选择视图列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
		   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackmvSelect, focus: true},
		   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
	   ]}).zindex(); 
	}


	function callbackmvSelect() {

		var iframe = this.iframe.contentWindow;

		var viewurl = iframe.$("#viewname").val();
			if(viewurl=="fk")
				{
				$('#viewnameori').val('');
				tip("数据无法直接获取，稍后手动输入");
				$('#viewnameori').focus();
				}
			else
				{
				$('#viewnameori').val(viewurl);
				}
			
			

	}
</script>



