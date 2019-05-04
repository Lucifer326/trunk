<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

        <div class="pui-layout" style="width:100%;">
        	<div id="coords" style='overflow: scroll; height: 100%; width: 100%;' >
				 <img src="activitiController.do?traceImage&processInstanceId=${processInstanceId}&isIframe" >
			</div>
            <br/>
            <h4>流程历史跟踪</h4>  
            <div class="pui-timeline">
                <div class="pui-timeline-list">
                    
                    <div id="container" class="pui-timeline-item" style="padding-left: 32px">
                        <label class="pui-badge pui-badge-info pui-badge-dot"></label>
                        <div class="pui-timeline-item-context">
                        </div>
                    </div> 
                </div>
                
			  		
            </div>
        </div>
        <br/> <br/> <br/> <br/>
