<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
        <div class="pui-layout" style="width:95%;">
            <br/>
            <h4>流转意见</h4>  
            <div class="pui-timeline">
                <div class="pui-timeline-list">
                	
                	<div class="pui-timeline-item" style="padding-left: 32px">
                        <label class="pui-badge pui-badge-info pui-badge-dot"></label>
                           <div class="pui-timeline-divider"> 开始申请</div>
                    </div> 
                    <c:forEach items="${bpmLogList}" var="bpmLog">
				     	 <div class="pui-timeline-item" style="left: -22px;">
		                        <label class="pui-badge" ><img src="plug-in/planeui/app/images/13.jpg" class="pui-img-circle pui-img-sm" /></label>
		                        <div class="pui-timeline-item-context">
		                        	<div class="pui-timeline-divider">${bpmLog.task_node }</div>
		                            <div class="pui-tooltip pui-tooltip-bordered pui-tooltip-arrow-lt ">
		                               		<summary><h6>${bpmLog.op_name } <small><fmt:formatDate value="${bpmLog.op_time }" pattern="yyyy-MM-dd HH:mm:ss"/></small></h6></summary>  
		                               		 ${bpmLog.memo }
		                            </div>
		                        </div>
		                    </div> 
		
				     </c:forEach>
                    
                    <div class="pui-timeline-item" style="padding-left: 32px">
                        <label class="pui-badge pui-badge-info pui-badge-dot"></label>
                        <div class="pui-timeline-item-context">
                            
                        </div>
                    </div> 
                </div>
                
                <form id="formobj" method="post" action="" class="pui-form-label-left">
					   <input name="taskId" id="taskId" type="hidden" value="${taskId}" />
					   <input name="bormoney" id="bormoney" type="hidden" vartype="B" value="${bormoney}">
					   <input name="keys" id="keys" type="hidden" />
					   <input name="values" id="values" type="hidden" />
					   <input name="types" id="types" type="hidden" />
					   <input name="nextCodeCount" id="nextCodeCount" type="hidden" value="${nextCodeCount}"/>
				       <div class="pui-form-group">
				       		<c:if test="${taskName != null }">
			                <span><b>当前任务：${taskName}</b></span><br>
		    	 			</c:if>
		                    <label><t:mutiLang langKey="common.handel.suggestion"/>：</label>
		                    <textarea name="reason"  datatype="*" vartype="S" style="height:100px"></textarea>          
		                </div>
			         <div style="display:none">
			         		<input type="radio" name="model" value="1" onchange="changeModel(1);" checked/><t:mutiLang langKey="common.model.one"/>
							 <input type="radio" name="model" value="2" onchange="changeModel(2);"/><t:mutiLang langKey="common.model.more"/>
							 <span id="manyModel" style="display:none">
							 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red"><t:mutiLang langKey="common.model.more.all"/>：</span>
								<c:forEach items="${transitionList}" var="trans">
									<input type="checkbox" name="transition" value="${trans.nextnode}" checked disabled>${trans.Transition }
								</c:forEach>
					  		</span>
					  		<c:if test="${histListSize > 0 }">
						  		<input type="radio" name="model" value="3" onchange="changeModel(3);"/><t:mutiLang langKey="common.reject"/>
						  		<span id="rejectModel" style="display:none">
						  			<select name="rejectModelNode">
						  				<c:forEach items="${histListNode}" var="histNode">
						  					<option value="${histNode.task_def_key_}">${histNode.name_ }</option>
						  				</c:forEach>
						  			</select>
						  		</span>
					  		</c:if>
					  		<t:mutiLang langKey="common.next.operator"/>：
							 <input name="last" id="last" readonly="readonly"> 
							 <input name="id" type="hidden" value="" id="id">  
							 <t:choose hiddenName="id" hiddenid="id" url="activitiController.do?goEntrust" name="entrusterList" icon="icon-search" title="common.user.list" textname="last" isclear="true"></t:choose><t:mutiLang langKey="common.noselect.default"/>
			         </div>   
         			<div class="pui-form-group">
         			<div id="singleModel" style="display:black">
						<input type="hidden" name="option" id="option" />
			  			<input type="hidden" name="nextnode" id="nextnode" />
						<c:forEach items="${transitionList}" var="trans">
							<input type="button" buttongroup="buttongroup" class="pui-btn pui-btn-primary" onclick="disabledButton();procPass('${trans.Transition }','${trans.nextnode}')" value="${trans.Transition }">
						</c:forEach>
			  		</div>
			  		<div id="manyModelButton" style="display:none">
			  			<input type="button" buttongroup="buttongroup" class="pui-btn pui-btn-primary" onclick="disabledButton();manyModelSubmit();" value='<t:mutiLang langKey="common.submit"/>'>
			  			<input type="hidden" name="transStr" id="transStr">
			  		</div> 
			  		</div>
			  		</form> 
			  		
            </div>
        </div>
       <br/> <br/> <br/> <br/>