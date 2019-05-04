package org.jeecgframework.workflow.service;

import java.util.Map;

public interface ProcessService {
	public Map GetProcessType(String typeGroupId,String pId) ;
	public Map GetProcessByType(String typeid);
	public Map GetTaskNodeMenu(String menuid);
}
