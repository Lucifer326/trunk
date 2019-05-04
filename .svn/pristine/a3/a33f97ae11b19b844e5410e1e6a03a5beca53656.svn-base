<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改首页样式</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
<t:formvalid formid="formobj" refresh="false" dialog="true" action="userController.do?savestyle" layout="table">
	<table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tbody><th>首页风格</th>
			<!--
			<tr>
				<td class="value"><input type="radio" value="bootstrap" name="indexStyle" /> <span>BootStrap风格</span></td>
			</tr>
			-->
			<!-- update-start--Author:gaofeng  Date:2014-01-10 for:新增首页风格  -->
			<tr>
				<td class="value"><input type="radio" value="acele" name="indexStyle"  /><span>ACE平面风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="shortcut" name="indexStyle" /> <span>ShortCut风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="salvation" name="indexStyle" /> <span>Salvation风格</span></td>
			</tr>
			
			<tr>
				<td class="value"><input type="radio" value="default" name="indexStyle" /> <span>经典风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="sliding" name="indexStyle"  /><span>Sliding云桌面</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_blue" name="indexStyle"  /><span>H+蓝色风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_darkblue" name="indexStyle"  /><span>H+藏青风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_cyan" name="indexStyle"  /><span>H+青色风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_darkred" name="indexStyle"  /><span>H+暗红风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_darkgrey" name="indexStyle"  /><span>H+深灰风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_orange" name="indexStyle"  /><span>H+橘黄风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_green" name="indexStyle"  /><span>H+绿色风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="hplus_red" name="indexStyle"  /><span>H+红色风格</span></td>
			</tr>	
					
			<!-- 
			<tr>
				<td class="value"><input type="radio" value="ace" name="indexStyle"  /><span>ACE2平面风格</span></td>
			</tr>
			<tr>
				<td class="value"><input type="radio" value="diy" name="indexStyle"  /><span>DIY平面风格</span></td>
			</tr>
			  -->	
		</tbody>
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
		$(function(){
			var val = "${indexStyle}";
			$("input[name='indexStyle']").each(function(){
				if($(this).val()==val){
					$(this).attr("checked",true);
					return false;
				}
			});
		/* 	var val2 = "${cssTheme}";
			$("input[name='cssTheme']").each(function(){
				if($(this).val()==val2){
					$(this).attr("checked",true);
					return false;
				}
			}); */
		});
	</script>
</html>
