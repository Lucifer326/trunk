<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title>完整demo</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="ueditor.all.min.js">
		</script>
		<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
		<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
		<script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>

		<style type="text/css">
			div {
				width: 100%;
			}
		</style>
	</head>

	<body>
		<div>
			<h1>完整demo</h1>
			<script id="editor" type="text/plain" style="width:1024px;height:300px;"></script>
		</div>
		<div id="btns">
			<div>
				<button onclick="getContent()">发送</button>
			</div>

		</div>

		<script type="text/javascript">
			//实例化编辑器
			//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
			var ue = UE.getEditor('editor');

			

			function getContent() {
				var arr = "";
				arr=(UE.getEditor('editor').getContent());
				alert(arr);
				location.href="${pageContext.request.contextPath }/mail?content="+arr;
			}

			
		</script>
	</body>

</html>