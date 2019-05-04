<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>组织机构集合</title>
<t:base type="jquery"></t:base>
<link rel="stylesheet" type="text/css"
	href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
	var selectType = '${selectType}';//单选多选
	var checkBranch = '${checkBranch}';//是否可选父节点
	var cascadeCheck = '${cascadeCheck}';//级联选择
	var async = '${async}';//是否异步

	if (selectType == "radio") {//单选
		var setting = {
			check : {
				enable : true,
				chkStyle : "radio",
				radioType : "all"
			},
			data : {
				simpleData : {

					enable : true
				}
			},
			callback : {
				onExpand : zTreeOnExpand
			}
		};
	} else {//多选

		if (cascadeCheck == 'true') { //多选并且级联
			var setting = {
				check : {

					enable : true,

					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				//级联
				},
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					onExpand : zTreeOnExpand
				}
			};

		} else {//多选并且不级联
			if (checkBranch == 'true') {//非叶子节点可选

				var setting = {

					check : {
						enable : true,
						chkboxType : {
							"Y" : "",
							"N" : ""
						}
					//不级联
					},

					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						onExpand : zTreeOnExpand
					}
				};
			} else {//非叶子节点不可选
				function zTreeBeforeCheck(treeId, treeNode) {

					return !treeNode.isParent;//非叶子节点不可选择
				}
				;
				var setting = {
					check : {
						enable : true,
						chkboxType : {
							"Y" : "",
							"N" : ""
						}
					//不级联
					},
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						beforeCheck : zTreeBeforeCheck,
						onExpand : zTreeOnExpand
					}
				};

			}

		}

	}

	//加载展开方法
	function zTreeOnExpand(event, treeId, treeNode) {
		var children = treeNode.children;
		if (children != undefined) {//已有子节点，直接返回，不在异步加载，避免出现重复节点
			return;
		}
		var treeNodeId = treeNode.id;
		$
				.post(
						'popupTreeController.do?popupTreeGetData&ids=${ids}&dataProvider=${dataProvider}',
						//'departController.do?getDepartInfo&ids=${ids }',
						{
							parentid : treeNodeId
						},
						function(data) {
							var d = $.parseJSON(data);
							if (d.success) {
								var dbDate = eval(d.msg);
								var tree = $.fn.zTree.getZTreeObj("treeSelect");
								tree.addNodes(treeNode, dbDate);
							} else {
								alert(d.msg);
							}
						});
	}

	//首次进入加载level为1的
	$(function() {

		$
				.post(
						'popupTreeController.do?popupTreeGetData&ids=${ids}&dataProvider=${dataProvider}',

						function(data) {
							var d = $.parseJSON(data);
							if (d.success) {
								var dbDate = eval(d.msg);
								$.fn.zTree.init($("#treeSelect"), setting,
										dbDate);
							} else {
								alert(d.msg);
							}
						});
	});
</script>
</head>
<body style="overflow-y: auto">
	<ul id="treeSelect" class="ztree" style="margin-top: 30px;"></ul>
</body>
</html>
