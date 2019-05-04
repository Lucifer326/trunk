<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="h" uri="/hplus-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
  <link href="plug-in/hplus/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/style.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/summernote/summernote.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/summernote/summernote-bs3.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/clockpicker/clockpicker.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/chosen/chosen.css" rel="stylesheet"/>
    <link href="plug-in/hplus/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet"/>
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/style.css" type="text/css"/>
  <link rel="stylesheet" href="plug-in/Validform/css/metrole/tablefrom.css" type="text/css"/>
  <!-- 全局js -->
    <script src="plug-in/hplus/js/jquery.min.js"></script>
    <script src="plug-in/hplus/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="plug-in/hplus/js/content.js"></script>
    <!--时间-->
    <script src="plug-in/hplus/js/plugins/layer/laydate/laydate.js"></script>
    <!--编辑器-->
    <script src="plug-in/hplus/js/plugins/summernote/summernote.min.js"></script>
    <script src="plug-in/hplus/js/plugins/summernote/summernote-zh-CN.js"></script>
    <!-- 上传 -->
    <script src="plug-in/hplus/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
    <!-- 下拉列表 -->
    <script src="plug-in/hplus/js/plugins/chosen/chosen.jquery.js"></script>
    <!--固定格式-->
    <script src="plug-in/hplus/js/plugins/jasny/jasny-bootstrap.min.js"></script>


	  <script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
	   <!--<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>-->

	  <script type="text/javascript" src="plug-in/tools/layertools_zh-cn.js"></script>
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
<script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


	function addTabRow(){
		 var tr =  $("#add_${entityName?uncap_first}_table_template tr").clone();
	 	 $("#add_${entityName?uncap_first}_table").append(tr);
	 	 resetTrNum('add_${entityName?uncap_first}_table');
	}

	function delTabRow(){
		   	$("#add_${entityName?uncap_first}_table").find("input:checked").parent().parent().remove();   
	        resetTrNum('add_${entityName?uncap_first}_table'); 
	}
	

    
  
    //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
   
</script>

<div id="${entityName?uncap_first}_table" class="tab-pane">
	<div class="row">
		 <div class="ibox-content" style="border: none">
			 <div class="example-wrap">
             	<div class="example">
		
		
					<div class="fl">
						<button type="button" class="btn btn-outline btn-primary" id="add${entityName}Btn" onclick="addTabRow();">新建</button>
                        <button type="button" class="btn btn-outline btn-danger" id="del${entityName}Btn" onclick="delTabRow();">删除</button>
					</div>
					<div style="width: 100%; float: left; margin-top: 30px;">
						<table width="100%" cellpadding="0" cellspacing="0"  class="crease" id="${entityName?uncap_first}_tab">
							<thead>
								<tr>
									<td align="center"  style="width: 25px;">序号</td>
									<td align="center" style="width: 25px;">操作</td>
									 <#list pageColumns as po>
											 <#assign check = 0 >
											  <#list foreignKeys as key>
											  <#if fieldMeta[po.fieldName]==key?uncap_first>
											  <#assign check = 1 >
											  <#break>
											  </#if>
											  </#list>
											  <#if check==0>
											  <td align="center">
													${po.content}
											  </td>
											  </#if>
								      </#list>
								</tr>
							</thead>
							<tbody id="add_${entityName?uncap_first}_table">
							<#-- --author: jg_renjie ------start----date:20160328-------for: #1004 [代码生成器] coding生成一对多代码多个bug和解决办法 -->	
							<#--<c:if test="${"$"}{fn:length(${entityName?uncap_first}List)  <= 0 }">
									<tr>
										<td align="center"><div style="width: 25px;" name="xh">1</div></td>
										<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
										<#list columns as po>
										<#if po.isShow=="N">
											<input name="${entityName?uncap_first}List[0].${po.fieldName}" type="hidden"/>
										</#if>
										</#list>
										<#list pageColumns as po>
										  <#assign check = 0 >
										  <#list foreignKeys as key>
										  <#if fieldMeta[po.fieldName]==key?uncap_first>
										  <#assign check = 1 >
										  <#break>
										  </#if>
										  </#list>
										  <#if check==0>
										  <td align="center">
											<#if po.showType == "text">
											  	<input name="${entityName?uncap_first}List[0].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="text" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if>>
												<#elseif po.showType=='password'>
													<input name="${entityName?uncap_first}List[0].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="password" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if></#if>>
												<#elseif po.showType=='radio'>
													<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}"
																<#if po.dictTable?if_exists?html != ''>dictTable="po.dictTable" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> defaultVal="${'$'}{${entityName?uncap_first}Page.${po.fieldName}}" hasLabel="false" divClass="radio radio-inline radio-danger"   title="${po.content}"></h:dictSelect>  
												<#elseif  po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>
													<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}"
																<#if po.dictTable?if_exists?html != ''>dictTable="po.dictTable" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> defaultVal="${'$'}{${entityName?uncap_first}Page.${po.fieldName}}" hasLabel="false"  title="${po.content}"></h:dictSelect>     
												<#elseif po.showType=='date'>
													<input name="${entityName?uncap_first}List[0].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if>>
											      <#elseif po.showType=='datetime'>
											      	<input name="${entityName?uncap_first}List[0].${po.fieldName}" maxlength="${po.length?c}" 
												  		type="text"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if>>
											       <#elseif po.showType=='file'>
													<input type="hidden" id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" />
																<a  target="_blank" id="${entityName?uncap_first}List[0].${po.fieldName}_href">未上传</a>
																<br>
															   <input class="ui-button" type="button" value="上传附件"
																			onclick="commonUpload(commonUploadDefaultCallBack,'${entityName?uncap_first}List\\[0\\]\\.${po.fieldName}')"/> 
											       <#else>
											       	<input name="${entityName?uncap_first}List[0].${po.fieldName}" maxlength="${po.length?c}" 
												  		type="text" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
											  </#if>
											  <label class="Validform_label" style="display: none;">${po.content?if_exists?html}</label>
											</td>
										  </#if>
							            </#list>
						   			</tr>
							</c:if>-->
							<#-- --author: jg_renjie ------end----date:20160328-------for: #1004 [代码生成器] coding生成一对多代码多个bug和解决办法 -->	
							<c:if test="${"$"}{fn:length(${entityName?uncap_first}List)  > 0 }">
								<c:forEach items="${"$"}{${entityName?uncap_first}List}" var="poVal" varStatus="stuts">
									<tr>
										<td align="center"><div style="width: 25px;" name="xh">${'$'}{stuts.index+1 }</div></td>
										<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
										<#list columns as po>
										<#if po.isShow=="N">
											<#if po.showType=='date'>
												<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" type="hidden" value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName} }' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"/>
											<#elseif po.showType=='datetime'>
												<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" type="hidden" value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName} }' type='date' pattern='yyyy-MM-dd hh:mm:ss'/>"/>
											<#else>
												<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" type="hidden" value="${'$'}{poVal.${po.fieldName} }"/>
											</#if>
										</#if>
										</#list>
										<#list pageColumns as po>
										  <#assign check = 0 >
										  <#list foreignKeys as key>
										  <#if fieldMeta[po.fieldName]==key?uncap_first>
										  <#assign check = 1 >
										  <#break>
										  </#if>
										  </#list>
										  <#if check==0>
										   <td align="center">
										   <#if po.showType == "text">
											  	<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="text" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }">
												<#elseif po.showType=='password'>
													<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="password" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }">
												<#elseif po.showType=='radio'>
													<h:dictSelect field="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" type="${po.showType?if_exists?html}"
															<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> defaultVal="${'$'}{poVal.${po.fieldName} }" hasLabel="false" divClass="radio radio-inline radio-danger"  title="${po.content}"></h:dictSelect>     
												
												<#elseif  po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>
													<h:dictSelect field="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" type="${po.showType?if_exists?html}"
																<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> defaultVal="${'$'}{poVal.${po.fieldName} }" hasLabel="false"  title="${po.content}"></h:dictSelect>     
												<#elseif po.showType=='date'>
													<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.length?c}" 
											  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"  <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if> value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName}}' type="date" pattern="yyyy-MM-dd"/>">
											      <#elseif po.showType=='datetime'>
											      	<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.length?c}" 
												  		type="text"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if> value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName}}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
											      <#elseif po.showType=='file'>
											        <input type="hidden" id="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}"  value="${'$'}{poVal.${po.fieldName} }"/>
																<c:if test="${'$'}{empty poVal.${po.fieldName}}">
																	<a  target="_blank" id="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}_href">未上传</a>
																</c:if>
																<c:if test="${'$'}{!empty poVal.${po.fieldName}}">
																	<a  href="${'$'}{poVal.${po.fieldName}}"  target="_blank" id="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}_href">下载</a>
																</c:if>
																<br>
															   <input class="ui-button" type="button" value="上传附件"
																			onclick="commonUpload(commonUploadDefaultCallBack,'${entityName?uncap_first}List\\[${'$'}{stuts.index }\\]\\.${po.fieldName}')"/> 
											       <#else>
											       	<input name="${entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.length?c}" 
												  		type="text" class="inputxt"  style="width:120px;" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else> <#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }">
											  </#if>
											  <label class="Validform_label" style="display: none;">${po.content?if_exists?html}</label>
										   </td>
										  </#if>
						   			 	</#list>
						   			</tr>
								</c:forEach>
							</c:if>	
							</tbody>
						</table>
					</div>
		
				</div>
			</div>
		</div>
	</div>		
 </div>
 
 
 