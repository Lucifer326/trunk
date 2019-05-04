<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="h" uri="/hplus-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<script type="text/javascript">
$(document).ready(function(){
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
		}
    });
</script>

    <div id="${entityName?uncap_first}_table" class="tab-pane">
		<div class="row">
	    	<c:if test="${"$"}{fn:length(${entityName?uncap_first}List)  <= 0 }">                
	           <div>
		            <#list columns as po>
						<#if po.isShow=="N">
							<input name="${entityName?uncap_first}List[0].${po.fieldName}" type="hidden"  value="${'$'}{poVal.${po.fieldName}}"/>
						</#if>
					</#list>
	            </div>     
	           <#list pageColumns as po>
				  <#assign check = 0 >
				  <#list foreignKeys as key>
				  <#if fieldMeta[po.fieldName]==key?uncap_first>
				  <#assign check = 1 >
				  <#break>
				  </#if>
				  </#list>
				 <#if check==0>
					<#if po_index%2==0>
						  <div class="form-group draggable">
					</#if> 
	              <label class="col-sm-2 control-label">${po.content}：</label>
	              			<#if po.showType=='text'>
								<div class="col-sm-4">
									<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
						    	</div>
						    <#elseif po.showType=='popup'>
						    	<div class="col-sm-4">
									<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" class="form-control fl" style="width:70%" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if><#if po.dictTable?if_exists?html!=""> )"</#if> >
									<button class="btn btn-primary btnh" id="btn" style="width:30%" type="button"  onclick="inputClick($('#${po.fieldName}'),'${po.dictField}','${po.dictTable}'><i class="fa fa-search"></i>&nbsp;选择</button>
						    	</div>
						    <#elseif po.showType=='textarea'>
						    	<div class="col-sm-10">
						  	 		<textarea id="${entityName?uncap_first}List[0].${po.fieldName}" class="form-control" rows="6" name="${entityName?uncap_first}List[0].${po.fieldName}"></textarea>
						     	</div>
						     <#elseif po.showType=='password'>
						     	<div class="col-sm-4">
						      		<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="password" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
								</div>
							<#elseif po.showType=='radio' >
								<div class="col-sm-4">
									<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}" extendJson="{class:'form-control'}"
									<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> hasLabel="false" divClass="radio radio-inline radio-danger"   title="${po.content}"></h:dictSelect>     
								</div>
							<#elseif po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>	 
								<div class="col-sm-4">
									<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}" extendJson="{class:'form-control'}"
									<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> hasLabel="false"  title="${po.content}"></h:dictSelect>     
								</div>
							<#elseif po.showType=='date'>
								<div class="col-sm-4">
									<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text"   class="form-control" onClick="laydate()" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if>  type="date" pattern="yyyy-MM-dd"/> 
						    	</div>
						    <#elseif po.showType=='datetime'>
						    	<div class="col-sm-4">
									<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text"  class="form-control" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if>  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
						    	</div>
						    <#elseif po.showType=='file'>
						    	<div class="col-sm-10">
									<input type="hidden" id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" />
									<a  target="_blank" id="${entityName?uncap_first}List[0].${po.fieldName}_href">暂时未上传文件</a>
									<input class="form-control" type="button" value="上传附件"
													onclick="commonUpload(${entityName?uncap_first}List[0].${po.fieldName}Callback)"/>
								</div>
								<script type="text/javascript">
								function ${entityName?uncap_first}List[0].${po.fieldName}Callback(url,name){
									$("#${entityName?uncap_first}List[0].${po.fieldName}_href").attr('href',url).html('下载');
									$("#${entityName?uncap_first}List[0].${po.fieldName}").val(url);
								}
								</script>
						    <#else>
						    	<div class="col-sm-4">
						      		<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" style="width: 150px" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> >
								</div>
							</#if>
							<span class="Validform_checktip" style="float:left;height:0px;"></span>
							<label class="Validform_label" style="display: none">${po.content?if_exists?html}</label>						
	
							<#if (po_index%2==0)&&(!po_has_next)>
								</div>
							</#if>
							<#if (po_index%2!=0)>
								</div>
							</#if>
					</#if>
				</#list>
	        </c:if>     
	        
	        <c:if test="${"$"}{fn:length(${entityName?uncap_first}List) > 0 }">     
	        	<c:forEach items="${"$"}{${entityName?uncap_first}List}" var="poVal" varStatus="stuts">           
		           <div>
			            <#list columns as po>
							<#if po.isShow=="N">
								<input name="${entityName?uncap_first}List[0].${po.fieldName}" type="hidden"  value="${'$'}{poVal.${po.fieldName}}"/>
							</#if>
						</#list>
		            </div>     
		           <#list pageColumns as po>
					  <#assign check = 0 >
					  <#list foreignKeys as key>
					  <#if fieldMeta[po.fieldName]==key?uncap_first>
					  <#assign check = 1 >
					  <#break>
					  </#if>
					  </#list>
					 <#if check==0>
						<#if po_index%2==0>
							  <div class="form-group draggable">
						</#if> 
		              <label class="col-sm-2 control-label">${po.content}：</label>
		              			<#if po.showType=='text'>
									<div class="col-sm-4">
										<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }">
							    	</div>
							    <#elseif po.showType=='popup'>
							    	<div class="col-sm-4">
										<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" class="form-control fl" style="width:70%" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if><#if po.dictTable?if_exists?html!=""> )"</#if> value="${'$'}{poVal.${po.fieldName} }">
										<button class="btn btn-primary btnh" id="btn" style="width:30%" type="button"  onclick="inputClick($('#${po.fieldName}'),'${po.dictField}','${po.dictTable}'><i class="fa fa-search"></i>&nbsp;选择</button>
							    	</div>
							    <#elseif po.showType=='textarea'>
							    	<div class="col-sm-10">
							  	 		<textarea id="${entityName?uncap_first}List[0].${po.fieldName}" class="form-control" rows="6" name="${entityName?uncap_first}List[0].${po.fieldName}">${'$'}{poVal.${po.fieldName}}</textarea>
							     	</div>
							     <#elseif po.showType=='password'>
							     	<div class="col-sm-4">
							      		<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="password" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }">
									</div>
								<#elseif po.showType=='radio'>
									<div class="col-sm-4">
										<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}" extendJson="{class:'form-control'}"
										<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> hasLabel="false" defaultVal="${'$'}{poVal.${po.fieldName} }" divClass="radio radio-inline radio-danger"   title="${po.content}"></h:dictSelect>     
									</div>
								<#elseif po.showType=='select' || po.showType=='checkbox' || po.showType=='list'>	 
									<div class="col-sm-4">
										<h:dictSelect field="${entityName?uncap_first}List[0].${po.fieldName}" type="${po.showType?if_exists?html}" extendJson="{class:'form-control'}"
										<#if po.dictTable?if_exists?html != ''>dictTable="${po.dictTable?if_exists?html}" dictField="${po.dictField?if_exists?html}" dictText="${po.dictText?if_exists?html}"<#else>typeGroupCode="${po.dictField}"</#if> hasLabel="false" defaultVal="${'$'}{poVal.${po.fieldName} }"  title="${po.content}"></h:dictSelect>     
									</div>
								<#elseif po.showType=='date'>
									<div class="col-sm-4">
										<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text"   class="form-control" onClick="laydate()" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if> value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName}}'  type="date" pattern="yyyy-MM-dd"/> 
							    	</div>
							    <#elseif po.showType=='datetime'>
							    	<div class="col-sm-4">
										<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text"  class="form-control" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if> value="<fmt:formatDate value='${'$'}{poVal.${po.fieldName}}'  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
							    	</div>
							    <#elseif po.showType=='file'>
							    	<div class="col-sm-10">
										<input type="hidden" id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" />
										<a  target="_blank" id="${entityName?uncap_first}List[0].${po.fieldName}_href">暂时未上传文件</a>
										<input class="form-control" type="button" value="上传附件"
														onclick="commonUpload(${entityName?uncap_first}List[0].${po.fieldName}Callback)"/>
									</div>
									<script type="text/javascript">
									function ${entityName?uncap_first}List[0].${po.fieldName}Callback(url,name){
										$("#${entityName?uncap_first}List[0].${po.fieldName}_href").attr('href',url).html('下载');
										$("#${entityName?uncap_first}List[0].${po.fieldName}").val(url);
									}
									</script>
							    <#else>
							    	<div class="col-sm-4">
							      		<input id="${entityName?uncap_first}List[0].${po.fieldName}" name="${entityName?uncap_first}List[0].${po.fieldName}" type="text" style="width: 150px" class="form-control" <#if po.fieldValidType?if_exists?html != ''> datatype="${po.fieldValidType?if_exists?html}"<#else><#if po.type == 'int'> datatype="n"<#elseif po.type=='double'> datatype="/^(-?\d+)(\.\d+)?$/"<#else><#if po.isNull != 'Y'>datatype="*"</#if></#if></#if> value="${'$'}{poVal.${po.fieldName} }>
									</div>
								</#if>
								<span class="Validform_checktip" style="float:left;height:0px;"></span>
								<label class="Validform_label" style="display: none">${po.content?if_exists?html}</label>						
		
								<#if (po_index%2==0)&&(!po_has_next)>
									</div>
								</#if>
								<#if (po_index%2!=0)>
									</div>
								</#if>
						</#if>
					</#list>
				</c:forEach>
	        </c:if>     
	        
	               
	 	</div>
	 </div>                   
	       
	       
	                    

