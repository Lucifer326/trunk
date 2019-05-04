<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<%--update-start--Author:wangkun  Date:20160327 TASK #956 【UI标签】封装选择用户标签--%>
<script type="text/javascript">
var setting = {
		   
        check: {
            enable: false,
            chkboxType: { "Y": "", "N": "" }
        },
        data: {
            simpleData: {
                enable: true
            }
        },callback: {
            onExpand: zxTreeOnExpand,
            onClick:onClick
        }
    };
    function onClick(event, treeId, treeNode){
        var departname = treeNode.name;
        var queryParams = $('#userList1').datagrid('options').queryParams;
        queryParams.orgIds = treeNode.id;
        $('#userList1').datagrid('options').queryParams=queryParams;
        $("#userList1").datagrid("reload");
    }
    //加载展开方法
    function zxTreeOnExpand(event, treeId, treeNode){
   	console.log(treeNode);
    	var children = treeNode.children;
		if(children != undefined) {//已有子节点，直接返回，不在异步加载，避免出现重复节点
			return;
		}
    	
        var treeNodeId = treeNode.id;      
        $.post(
                'departController.do?getDepartInfo',
                {parentid:treeNodeId},
                function(data){
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var dbDate = eval(d.msg);
                        var tree = $.fn.zTree.getZTreeObj("departSelect");
                        tree.addNodes(treeNode, dbDate);
                    }
                }
        );
    }
    //默认展开到第二级的方法
    function nextzxtree(treeId, treeNode){
    	
    	
    	console.log(treeNode); 
    	var children = treeNode.children;
		if(children != undefined) {//已有子节点，直接返回，不在异步加载，避免出现重复节点
			return;
		}
    	
        var treeNodeId = treeNode.id;      
        $.post(
                'departController.do?getDepartInfo',
                {parentid:treeNodeId},
                function(data){
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var dbDate = eval(d.msg);
                        var tree = $.fn.zTree.getZTreeObj("departSelect");
                        tree.addNodes(treeNode, dbDate);
                    }
                }
        );
    }

    //首次进入加载level为2的
    $(function(){
        $.post(
                'departController.do?getDepartInfo',
                function(data){
                    var d = $.parseJSON(data);
                    if (d.success) {
                        var dbDate = eval(d.msg);
                        $.fn.zTree.init($("#departSelect"), setting, dbDate);
                        var tree = $.fn.zTree.getZTreeObj("departSelect");
                      	
                        //默认展开到第二级
                        alltrees=tree.getNodes();
                      	 
               			for(i=0;i<alltrees.length;i++)
               				{
               				console.log(alltrees[i]); 
               				if(tree.getNodes()[i].isParent)
               					{
               					nextzxtree("departSelect", alltrees[i]);
               					
               				
               					}
               				}
                      
                       
                    }
                }
        );
    });
</script>
<div class="easyui-layout" style="width:1000px;height:600px;">
    <div data-options="region:'west',split:true" title="<t:mutiLang langKey='common.department'/>" style="width:200px;">
        <ul id="departSelect" class="ztree" style="margin-top: 30px;"></ul>
    </div>
    <div data-options="region:'center'">
        <t:datagrid name="userList1" title="common.user.select" actionUrl="userController.do?datagrid"
                    fit="true" fitColumns="true" idField="id" queryMode="group" sortName="createDate" sortOrder="desc">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="common.username" sortable="false" field="userName" query="true" width="300"></t:dgCol>
            <t:dgCol title="common.department" sortable="false" field="userOrgList.tsDepart.departname" query="false" width="300"></t:dgCol>
            <t:dgCol title="common.real.name" field="realName" query="false" width="300"></t:dgCol>
            <t:dgCol title="common.role" field="userKey" width="300"></t:dgCol>
            <t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
            <t:dgCol title="common.createtime" field="createDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
            <t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
            <t:dgCol title="common.updatetime" field="updateDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
            <t:dgCol title="common.status" sortable="true" field="status" replace="common.active_1,common.inactive_0,super.admin_-1" width="300"></t:dgCol>
           <%--  <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol> --%>
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
<style>
.Button {
     padding: 0; 
    -webkit-box-shadow: none; 
    -moz-box-shadow: none;
}
</style>