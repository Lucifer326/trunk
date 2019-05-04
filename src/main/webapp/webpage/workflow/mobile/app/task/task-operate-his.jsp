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
                
			  		
            </div>
        </div>
        <br/> <br/> <br/> <br/>
