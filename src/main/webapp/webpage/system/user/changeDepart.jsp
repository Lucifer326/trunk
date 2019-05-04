<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>切换登陆组织机构</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
<t:formvalid formid="formobj" refresh="false" dialog="true" action="userController.do?saveLoginDepart" callback="refreshParentWin@Override" layout="table">
	<table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<c:forEach items="${sessionScope.LOCAL_CLINET_USER_DEPART_LIST }" var="depart">
				<tr>
					<td class="value"><input type="radio" value="${depart.id }" name="departId"  /><span>${depart.departname }</span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
$(function(){
	var val = "${currentDepartId}";
	$("input[name='departId']").each(function(){
		if($(this).val()==val){
			$(this).attr("checked",true);
			return false;
		}
	});
});

//刷新整个页面
function refreshParentWin(data){
	if(typeof(windowapi) != 'undefined'){
		var win = frameElement.api.opener;
		if(data.success==true){
			frameElement.api.close();
			win.tip(data.msg);
			win.location.reload();
		}else{
			if(data.responseText==''||data.responseText==undefined){
				$.messager.alert('错误', data.msg);
				$.Hidemsg();
			}else{
				try{
					var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
					$.messager.alert('错误',emsg);
					$.Hidemsg();
				}catch(ex){
					$.messager.alert('错误',data.responseText+"");
					$.Hidemsg();
				}
			}
			return false;
		}
	}else{
		if(data.success==true){
			parent.tip(data.msg);
			parent.location.reload();
			var index = parent.layer.getFrameIndex(window.name); parent.layer.close(index);
		}else{
			if(data.responseText==''||data.responseText==undefined){
				$.messager.alert('错误', data.msg);
				$.Hidemsg();
			}else{
				try{
					var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
					$.messager.alert('错误',emsg);
					$.Hidemsg();
				}catch(ex){
					$.messager.alert('错误',data.responseText+"");
					$.Hidemsg();
				}
			} 
			return false;
		}
	}
}

</script>
<script type="text/javascript">
$(function(){
$("#formobj").Validform({
tiptype:4,
btnSubmit:"#btn_sub",
btnReset:"#btn_reset",
ajaxPost:true,
callback:function(data){
if(typeof(windowapi) != 'undefined'){
 var win = frameElement.api.opener;
if(data.success==true){
 frameElement.api.close();
 win.tip(data.msg);
}
else{
if(data.responseText==''||data.responseText==undefined){
$.messager.alert('错误', data.msg);
$.Hidemsg();
}
else{
try{
var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
 $.messager.alert('错误',emsg);
$.Hidemsg();
}
catch(ex){
$.messager.alert('错误',data.responseText+"");
$.Hidemsg();
}
} 
return false;
}
}
else{
 if(data.success==true){
 parent.tip(data.msg);
 var index = parent.layer.getFrameIndex(window.name); parent.layer.close(index);
 }
else{
if(data.responseText==''||data.responseText==undefined){
$.messager.alert('错误', data.msg);
$.Hidemsg();
}
else{
try{
var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
 $.messager.alert('错误',emsg);
$.Hidemsg();
}
catch(ex){
$.messager.alert('错误',data.responseText+"");
$.Hidemsg();
}
} 
return false;
}
}
win.refreshParentWin(data);
}});});
</html>
