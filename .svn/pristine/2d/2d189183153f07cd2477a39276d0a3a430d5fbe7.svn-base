<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
//update-begin--Author:chenxu  Date:20130327 for：流程设计，开始节点，设置发起人表达式存在浏览器兼容问题，在google和360浏览器下，保存不了
//将event修改为eventFigure
var eventFigure = workflow.getFigure(nodeid);
//属性表格定义
 rows = [
    
            { "name": "ID", "group": "<t:mutiLang langKey='bpm.designer.page.event.nodeproperties'/>", "value": eventFigure.eventId,"field": "eventId", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.gateway.name'/>", "group": "<t:mutiLang langKey='bpm.designer.page.event.nodeproperties'/>", "value": eventFigure.eventName, "field": "eventName", "editor": "text" },
            { "name": "<t:mutiLang langKey='bpm.designer.page.servertask.expression'/>", "group": "<t:mutiLang langKey='bpm.designer.page.event.sponsor'/>", "value": eventFigure.expression, "field": "expression", "editor": "text" }
        ];
 //保存属性
function saveEventProperties(){
	eventFigure.eventId=rows[0].value;
	eventFigure.eventName=rows[1].value;
	eventFigure.expression=rows[2].value;
}
 //构建属性表格数据
function populateEventProperites(){
	rows[0].value=eventFigure.eventId;
	rows[1].value=eventFigure.eventName;
	rows[2].value=eventFigure.expression;
	propertygrid();
} 
 //加载属性表格数据
function eventpropertygrid(){
	$('#event-properties').propertygrid('loadData',rows);
	}
$(function(){
//创建属性表格
$('#event-properties').propertygrid({
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
  	saveEventProperties();//自动保存
   }
});
eventpropertygrid();
});
//update-end--Author:chenxu  Date:20130327 for：流程设计，开始节点，设置发起人表达式存在浏览器兼容问题，在google和360浏览器下，保存不了
</script>
<div id="event-properties-layout" class="easyui-layout" fit="true">
 <div id="event-properties-panel"  region="center" border="true">
   <table id="event-properties">
   </table>
 </div>
</div>