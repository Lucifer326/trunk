<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>${ftl_description}</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="online/template/ledefault/css/vendor.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap-theme.css">
  <link rel="stylesheet" href="online/template/ledefault/css/bootstrap.css">
  <link rel="stylesheet" href="online/template/ledefault/css/app.css">
  
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/style.css" type="text/css"/>
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/tablefrom.css" type="text/css"/>
  
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
  <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
  <script type="text/javascript" src="plug-in/easyui/locale/zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
  <script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
  <script type="text/javascript" src="plug-in/tools/curdtools_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <link rel="stylesheet" href="plug-in/umeditor/themes/default/css/umeditor.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/umeditor/umeditor.config.js"></script>
  <script type="text/javascript" src="plug-in/umeditor/umeditor.min.js"></script>
  <script type="text/javascript" src="plug-in/umeditor/lang/zh-cn/zh-cn.js"></script>
   <script type="text/javascript">
  //编写自定义JS代码
  </script>
  <#if cgformConfig.cgFormHead.isTree == 'Y'>
  <style type="text/css">
  	.combo_self{height: 22px !important;width: 150px !important;}
  	.layout-header .btn {
	    margin:0;
	   float: none !important;
	}
	.btn-default {
	    height: 35px;
	    line-height: 35px;
	    font-size:14px;
	}
  </style>
  
  <script type="text/javascript">
	$(function(){
		$(".combo").removeClass("combo").addClass("combo combo_self");
		$(".combo").each(function(){
			$(this).parent().css("line-height","0px");
		});   
	});
  		
  		 /**树形列表数据转换**/
  function convertTreeData(rows, textField) {
      for(var i = 0; i < rows.length; i++) {
          var row = rows[i];
          row.text = row[textField];
          if(row.children) {
          	row.state = "open";
              convertTreeData(row.children, textField);
          }
      }
  }
  /**树形列表加入子元素**/
  function joinTreeChildren(arr1, arr2) {
      for(var i = 0; i < arr1.length; i++) {
          var row1 = arr1[i];
          for(var j = 0; j < arr2.length; j++) {
              if(row1.id == arr2[j].id) {
                  var children = arr2[j].children;
                  if(children) {
                      row1.children = children;
                  }
                  
              }
          }
      }
  }
  </script>
  </#if>
</head>

 <body>

  <form id="formobj" action="${entityName?uncap_first}Controller.do?doAdd" name="formobj" method="post">
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			
			<div class="tab-wrapper">
			    <!-- tab -->
			    <ul class="nav nav-tabs">
			      <li role="presentation" class="active"><a href="javascript:void(0);">${ftl_description}</a></li>
			    </ul>
			    <!-- tab内容 -->
			    <div class="con-wrapper" id="con-wrapper1" style="display: block;">
			      <div class="row form-wrapper">
			        <#list pageColumns as po>
				        <#if (pageColumns?size>10)>
							<#if po_index%2==0>
							<div class="row show-grid">
							</#if>
						<#else>
							<div class="row show-grid">
						</#if>
			          <div class="col-xs-3 text-center">
			          	<b>${po.content}：</b>
			          </div>
			          <div class="col-xs-3">
							<#if cgformConfig.cgFormHead.isTree=='Y' && cgformConfig.cgFormHead.treeParentIdFieldName==po.fieldName>
							<input id="${po.fieldName}" name="${po.fieldName}" type="text" style="width: 150px" class="inputxt easyui-combotree" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#elseif po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#elseif po.isNull != 'Y'> datatype="*"</#if>
							<#-- update--begin--author:zhangjiaqiang Date:20170414 for:增加校验必填项 -->
									<#if po.fieldMustInput??><#if po.fieldMustInput == 'Y' || po.isNull != 'Y'>ignore="checked"<#else>ignore="ignore"</#if></#if>
									<#-- update--end--author:zhangjiaqiang Date:20170414 for:增加校验必填项 -->
							data-options="panelHeight:'220',
				                    url: '${entityName?uncap_first}Controller.do?datagrid&field=id,${cgformConfig.cgFormHead.treeFieldname}',  
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, '${cgformConfig.cgFormHead.treeFieldname}');
				                    	return rows; 
				                    },
				                    onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${'$'}{${entityName?uncap_first}Page.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#${po.fieldName}').combotree('setValue', currRow.id);
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#${po.fieldName}').combotree('setValue', currRow.${po.fieldName});
				                    		}
				                    	}
				                    }"/>
						 <#elseif po.showType=='text'>
								<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
						    <#elseif po.showType=='popup'>
								<input id="${po.fieldName}" name="${po.fieldName}" type="text" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if><#if po.dictTable?if_exists?html!=""> onclick="inputClick(this,'${po.dictField}','${po.dictTable}')"</#if> >
						    <#elseif po.showType=='textarea'>
						  	 	<textarea id="${po.fieldName}" class="form-control" rows="6" name="${po.fieldName}"></textarea>
						     <#elseif po.showType=='password'>
						      	<input id="${po.fieldName}" name="${po.fieldName}" type="password" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
							<#elseif po.showType=='radio' || po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>	 
								<t:dictSelect field="${po.fieldName}" type="${po.showType?if_exists?html}" extendJson="{class:'form-control'}"
								<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> hasLabel="false"  title="${po.content}"></t:dictSelect>     
							<#elseif po.showType=='date'>
								<input id="${po.fieldName}" name="${po.fieldName}" type="text" style="width: 150px"  class="form-control" onClick="WdatePicker()"<#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if>  type="date" pattern="yyyy-MM-dd"/>'>
						    <#elseif po.showType=='datetime'>
								<input id="${po.fieldName}" name="${po.fieldName}" type="text" style="width: 150px" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"<#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if>  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
						    <#elseif po.showType=='file'>
									   <input type="hidden" id="${po.fieldName}" name="${po.fieldName}" />
										<a  target="_blank" id="${po.fieldName}_href">暂时未上传文件</a>
										<input class="form-control" type="button" value="上传附件"
														onclick="commonUpload(${po.fieldName}Callback)"/>
										<script type="text/javascript">
										function ${po.fieldName}Callback(url,name){
											$("#${po.fieldName}_href").attr('href',url).html('下载');
											$("#${po.fieldName}").val(url);
										}
										</script>
						    <#else>
						      	<input id="${po.fieldName}" name="${po.fieldName}" type="text" style="width: 150px" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
							</#if>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">${po.content?if_exists?html}</label>
			          </div>
			        <#if (pageColumns?size>10)>
						<#if (po_index%2==0)&&(!po_has_next)>
							<div class="col-xs-2 text-center"><b></b></div>
			         		<div class="col-xs-4"></div>
						</#if>
						<#if (po_index%2!=0)||(!po_has_next)>
							</div>
						</#if>
					<#else>
						</div>
					</#if>
			          
			        
			        </#list>

			       
			          <div class="row" id = "sub_tr" style="display: none;">
				        <div class="col-xs-12 layout-header">
				          <div class="col-xs-6"></div>
				          <div class="col-xs-6"><button type="button" onclick="neibuClick();" class="btn btn-default">提交</button></div>
				        </div>
				      </div>
			     </div>
			   </div>
			   
			   <div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
			 </div>
	</form>
<script type="text/javascript">
$(function() {
    $("#formobj").Validform({
        tiptype: 1,
        btnSubmit: "#btn_sub",
        btnReset: "#btn_reset",
        ajaxPost: true,
		beforeSubmit: function(curform) {
            var tag = true;
			//提交前处理
            return tag;
        },
        usePlugin: {
            passwordstrength: {
                minLen: 6,
                maxLen: 18,
                trigger: function(obj, error) {
                    if (error) {
                        obj.parent().next().find(".Validform_checktip").show();
                        obj.find(".passwordStrength").hide();
                    } else {
                        $(".passwordStrength").show();
                        obj.parent().next().find(".Validform_checktip").hide();
                    }
                }
            }
        },
        callback: function(data) {
            if (data.success == true) {
			     var win = frameElement.api.opener;
                 win.reloadTable();
 				 win.tip(data.msg);
 				 frameElement.api.close();
            } else {
                if (data.responseText == '' || data.responseText == undefined) {
                    $.messager.alert('错误', data.msg);
                    $.Hidemsg();
                } else {
                    try {
                        var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
                        $.messager.alert('错误', emsg);
                        $.Hidemsg();
                    } catch(ex) {
                        $.messager.alert('错误', data.responseText + '');
                    }
                }
                return false;
            }
        }
    });
});
</script>

<script type="text/javascript">
   $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
	
	if(location.href.indexOf("mode=read")!=-1){
		//查看模式控件禁用
		$("#formobj").find(":input").attr("disabled","disabled");
	}
	if(location.href.indexOf("mode=onbutton")!=-1){
		//其他模式显示提交按钮
		$("#sub_tr").show();
	}
   });

  var neibuClickFlag = false;
  function neibuClick() {
	  neibuClickFlag = true; 
	  $('#btn_sub').trigger('click');
  }

</script>
 </body>
<script src = "webpage/${bussiPackage?replace('.','/')}/${entityPackage}/${entityName?uncap_first}.js"></script>		
</html>