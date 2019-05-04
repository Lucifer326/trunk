<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
var subProcess= workflow.getFigure(nodeid);
//属性表格定义
 rows = [
    
            { "name": "ID", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.call_activity'/>", "value": subProcess.subProcessId,"field":"subProcessId", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.gateway.name'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.call_activity'/>", "value": subProcess.name, "field": "name", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.gateway.name'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.call_sub_process'/>", "value": subProcess.callSubProcess, "field": "callSubProcess", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.subprocess.source_variable'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.in_variable'/>", "value": subProcess.insource,"field": "insource", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.subprocess.target_variable'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.in_variable'/>", "value": subProcess.intarget, "field": "intarget", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.subprocess.source_variable'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.out_variable'/>", "value": subProcess.outsource, "field": "outsource", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.subprocess.target_variable'/>", "group": "<t:mutiLang langKey='bpm.designer.page.subprocess.out_variable'/>", "value": subProcess.outtarget, "field": "outtarget", "editor": "text" }
        ];
 //保存属性
function saveSubProProperties(){
	subProcess.subProcessId=rows[0].value;
	subProcess.name=rows[1].value;
	subProcess.callSubProcess=rows[2].value;
	subProcess.insource=rows[3].value;
	subProcess.intarget=rows[4].value;
	subProcess.outsource=rows[5].value;
	subProcess.outtarget=rows[6].value;
}
 //构建属性表格数据
function populateSubProProperites(){
	rows[0].value=subProcess.subProcessId;
	rows[1].value=subProcess.name;
	rows[2].value=subProcess.callSubProcess;
	rows[3].value=subProcess.insource;
	rows[4].value=subProcess.intarget;
	rows[5].value=subProcess.outsource;
	rows[6].value=subProcess.outtarget;
	subPropropertygrid();
} 
 //加载属性表格数据
function subPropropertygrid(){
	$('#subpro-properties').propertygrid('loadData',rows);
	}
$(function(){
//创建属性表格
$('#subpro-properties').propertygrid({
  width: 'auto',
  height: 'auto',
  showGroup: true,
  scrollbarSize: 0,
  border:0,
  columns: [[
          { field: 'name', title: '<t:mutiLang langKey='bpm.designer.page.task.property.name'/>', width: 30, resizable: false },
          { field: 'value', title: '<t:mutiLang langKey='bpm.designer.page.task.property.value'/>', width: 100, resizable: false }
  ]],
  onAfterEdit:function(){  
  	saveSubProProperties();//自动保存
   }
});
subPropropertygrid();
});
</script>
<div id="subpro-properties-layout" class="easyui-layout" fit="true">
 <div id="subpro-properties-panel"  region="center" border="true">
   <table id="subpro-properties">
   </table>
 </div>
</div>