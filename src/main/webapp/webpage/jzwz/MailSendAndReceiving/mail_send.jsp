<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/webpage/jzwz/MailSendAndReceiving/ueditor/ueditor.config.js"></script>
		<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/webpage/jzwz/MailSendAndReceiving/ueditor/ueditor.all.min.js">

		</script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/webpage/jzwz/MailSendAndReceiving/ueditor/lang/zh-cn/zh-cn.js"></script>

		<style type="text/css">
			div {
				width: 100%;
			}
		</style>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>收件箱</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	</head>

	<body class="gray-bg">
		<div class="wrapper wrapper-content">
			<div class="row">
				<div class="col-sm-12 animated fadeInRight">
					<div class="mail-box-header">
						<div class="pull-right tooltip-demo">
							<a href="${pageContext.request.contextPath}/webpage/jzwz/MailSendAndReceiving/mailbox.jsp" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="top" title="放弃"><i class="fa fa-times"></i> 放弃</a>
						</div>
						<h2>
                    写信
                </h2>
					</div>
					<div class="mail-box">

						<div class="mail-body">


								<div class="form-group">
									<label class="col-sm-2 control-label">发送到：</label>

									<div class="col-sm-10">
										<input type="text" id="email" name="email" class="form-control" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">主题：</label>

									<div class="col-sm-10">
										<input type="text" id="title" name="title" class="form-control" value="">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-2">

									</div>
									<div class="col-sm-10">
										<script id="editor" type="text/plain" class="col-sm-11" style="height: 300px">邮件内容...</script>
									</div>
								</div>

								<div class="mail-body text-right tooltip-demo">
									<!-- <a href="#" class="btn btn-white btn-sm" onclick="getContent()"><input type="submit" value="发送"></a> -->
									<a href="#" class="btn btn-white btn-sm" onclick="getContent()">发送</a>
									<a href="${pageContext.request.contextPath}/webpage/jzwz/MailSendAndReceiving/mailbox.jsp" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Discard email"><i class="fa fa-times"></i> 放弃</a>
								</div>



						</div>

						<div class="clearfix"></div>

					</div>
				</div>
			</div>
		</div>

		<!-- 全局js -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<!-- 自定义js -->
		<script src="${pageContext.request.contextPath}/js/content.js"></script>
		<!-- iCheck -->
		<script src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
		<!-- SUMMERNOTE -->
		<script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>

		<script type="text/javascript">
			//实例化编辑器
			//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
			var ue = UE.getEditor('editor', {
				//focus时自动清空初始化时的内容
				autoClearinitialContent: true,
				toolbars: [[
					'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
					  'lineheight', '|',
					'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
					'directionalityltr', 'directionalityrtl', 'indent','|', 'touppercase', 'tolowercase', '|',
					'link', 'unlink', 'anchor', '|', 'simpleupload', 'emotion', 'attachment'
				]],
				//默认的编辑区域高度
				initialFrameHeight: 400
			});
			var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			function getContent() {

				if($.trim($("#email").val()).length == 0 ){
					layer.msg("请输入您要发送的邮箱地址！",{icon: 2,time:2000});
					return;
				}
				if(!filter.test($.trim($("#email").val()))){
					layer.msg("请输入正确的邮箱地址！",{icon: 2,time:2000});
					return;
				}
				if($.trim($("#title").val()).length == 0 ){
					layer.msg("请输入邮件主题！",{icon: 2,time:2000});
					return;
				}

				var arr = "";
				arr = (UE.getEditor('editor').getContent());
				email = $("#email").val();
				title = $("#title").val();
				for (var i = 0; i < 10; i++) {
					arr = arr.replace('#','');
				}
				window.location.href = "${pageContext.request.contextPath}/mailController.do?send&email=" + email + "&title=" + title + "&content=" + arr;
			}
			//文件上传标签
			$("#selFile").click(function() {
				$("span").css("color", "red");
				$("span").text("文件未上传");
				$("span").show();
			});

		</script>

		<script>
			$(document).ready(function() {
				$('.i-checks').iCheck({
					checkboxClass: 'icheckbox_square-green',
					radioClass: 'iradio_square-green',
				});

				$('.summernote').summernote({
					lang: 'zh-CN'
				});

			});
			var edit = function() {
				$('.click2edit').summernote({
					focus: true
				});
			};
			var save = function() {
				var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
				$('.click2edit').destroy();
			};
		</script>
	</body>

</html>