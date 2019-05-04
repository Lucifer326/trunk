<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<div style="display:none">
 	<div class="processListtb">
	 <div style="float: left;">
	  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="editfs('<t:mutiLang langKey="common.process.update"/>','processController.do?processDesigner')"><t:mutiLang langKey="common.process.update"/></a>
	 </div>
	 <div class="clear clearfix"></div>	
	</div> 
</div>
<div class="easyui-layout" fit="true">
 <div region="west" style="width: 200px;" title='<t:mutiLang langKey="common.type.list"/>' split="true">
  <div class="easyui-panel" style="padding: 10px;width: 150px;" fit="true" border="false">
   <ul id="processtree" class="">
   </ul>
  </div>
 </div> 

 <div region="center" style="padding:0px;border:0px" id="process-panelss">
  <t:datagrid name="processList" title="common.process.list" queryMode="single" actionUrl="processController.do?processGrid" idField="id" sortName="createDate" sortOrder="desc">
	 <t:dgCol title="流程id" field="id" width="100" hidden="true"></t:dgCol>
	 <t:dgCol title="common.process.name" field="processname" query="true" width="70"></t:dgCol>
	 <t:dgCol title="common.process.key" field="processkey" width="70"></t:dgCol>
	 <t:dgCol title="common.process.status" field="TSType.typename" width="70"></t:dgCol>
	 <t:dgCol title="common.bpm_status" field="processstate" replace="common.bpmstatus.publish_1,common.bpmstatus.unpublish_0" width="70"></t:dgCol>
	 <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	 <t:dgFunOpt title="common.process.upload"  funname="processUpload(id)"></t:dgFunOpt>
	 <t:dgFunOpt funname="processcofig(id)" title="common.process.config"></t:dgFunOpt>
	 <t:dgConfOpt url="processController.do?deployProcess&processid={id}" message="common.process.publish.sure" title="common.publish"></t:dgConfOpt>
	 <t:dgFunOpt exp="processstate#eq#1" funname="deploymentList(processkey)" title="common.process.version"></t:dgFunOpt>
	 <t:dgDelOpt exp="processstate#eq#0" url="processController.do?delprocess&id={id}" title="common.delete"></t:dgDelOpt>
  </t:datagrid>
</div>
</div>
<script>
$("#processListtb").append($(".processListtb").html());
</script>
<script type="text/javascript">
var processtypeid="";
$(function() {
	$('#processtree').tree({
		animate : true,
		url : 'processController.do?getProcessType',
		onClick : function(node) {
			$('#process-panelss').panel("refresh", "processController.do?processList&typeid=" + node.id);
		}
	});
});
/* function processcofig(id) {
	addOneTab('<t:mutiLang langKey="common.process.config"/>', 'processController.do?processTabs&processid=' + id);
} */
function processcofig(id) {
	addOneTab('<t:mutiLang langKey="common.process.config"/>', 'processController.do?processTabs&processid=' + id,id);		
}
function deploymentList(processkey) {
	addOneTab('<t:mutiLang langKey="common.process.published"/>', 'activitiController.do?deploymentListByProcesskey&processkey=' + processkey);
}
function processUpload(id){
	openuploadwin('<t:mutiLang langKey="common.process.import"/>', 'processController.do?processUpload&id='+id, "processList");
}

</script>
