<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>DICTIONARY_ITEM</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
//上级字典条目
	$(function() {
		$('#cc').combotree({
			url : 'systemController.do?typeTreegrid1&typegroupId='+ '${typegroupId}',
          width: 155
      });
		
      if($("#id").val()) {
          $('#cc').combotree('disable');
      }
      if(!$('#cc').val()){
      	$('#cc').combotree('setValue','${pid}');
      }
	});
	  
  
  //选择行政区划
 	
	function openRegionSelect() {
		$.dialog.setting.zIndex = 9999; 
		//departController.do?departSelect
		$.dialog({content: 'url:systemRegionController.do?regionSelect', zIndex: 99900, title: '区划列表', 
				lock: true, width: '400px', height: '350px', opacity: 0.4, 
				button: [
		   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackRegionSelect, focus: true},
		   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
	   ]}).zindex();
	}
		
	function callbackRegionSelect() {
		  var iframe = this.iframe.contentWindow;
		  /* var treeObj = iframe.$.fn.zTree.getZTreeObj("regionSelect");
		  var nodes = treeObj.getCheckedNodes(true); */
		  
		  var treeObj = iframe.$("#regionSelect");
		  
		  var nodes = treeObj.tree('getChecked');
		  
		  if(nodes.length>0){
		  var ids='',names='';
		  
		  
		 //循环获得 选中的 节点 
		  for(i=0;i<nodes.length;i++){
			     var name = nodes[i].text;
			     var id = nodes[i].id;
			     ids += id+',';
			    names += name+',';
			    
			 }
		 //   把值放到input 中
		 $('#regiontname').val(names);
		 $('#regiontname').blur();		
		 $('#orgIds').val(ids);
		 
		}else{
			
			$('#regiontname').val('');
			 $('#orgIds').val('');
		}
	}

  
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" layout="div" dialog="true" action="systemController.do?typeDoAdd">
	<input id="id" name="id" type="hidden" value="${typePage.id}">
	<input  name="TSTypegroup.id" type="hidden" class="inputxt" value="${typegroupId}">
	<input  name="isSys" type="hidden" class="inputxt" value="">
	<input  name="isStop" type="hidden" class="inputxt" value="">
	<fieldset class="step">
                
      
        <div class="form">
            <label class="Validform_label">条目编码: </label>
            <input  name="typecode" class="inputxt" value="" datatype="*1-50">
            <span class="Validform_checktip">条目编码为1-50个任意字符</span>
        </div>
         <div class="form">
            <label class="Validform_label">条目名称: </label>
            <input name="typename" class="inputxt" value=""  datatype="*1-50">
            <span class="Validform_checktip">条目名称范围1-50个任意字符</span>
        </div>
  		 <div class="form">
            <label class="Validform_label">上级字典: </label>
            <input id="cc" name="TSType.id" value="${typePage.TSType.id}">
        </div>
         
        <div class="form">
            <label class="Validform_label">排序编号: </label>
            <input name="orderInLevel" class="inputxt" value="${maxNum}"  datatype="n1-3">
            <span class="Validform_checktip">排序编号为1-3位数字</span>
        </div>
       <div class="form" style="height: 60px">
	      	
	      	<td align="right"><label class="Validform_label"> 禁用区划: </label></td>
	      	 <div>
	      	 	<tr>
				<!-- <td align="right"><label class="Validform_label"> 行政区划: </label></td> -->
				<td class="value">
                
                <input id="regiontname" name="regiontname" style="border-radius:3px;border:1px solid #3DADF5; height:40px; width:150px" type="text"  readonly = "readonly" class="inputxt"  value="${regiontname}" onclick="openRegionSelect()">
                <input id="orgIds" name="orgIds" type="hidden" value="">
               	 	 <span class="Validform_checktip">区划可以多选</span>
            	</td>
			</tr>
	      	 </div>
		      
        </div>
	</fieldset>
</t:formvalid>
 </body>
  <!-- <script src = "webpage/com/jeecg/dictionaryitem/dictionaryItem.js"></script>	 -->	
