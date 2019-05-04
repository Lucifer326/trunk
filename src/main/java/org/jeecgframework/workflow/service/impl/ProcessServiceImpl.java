package org.jeecgframework.workflow.service.impl;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.dto.DictColAndItem;
import org.jeecgframework.web.formula.dto.FormulaFactorTree;
import org.jeecgframework.web.formula.dto.RefedCol;
import org.jeecgframework.web.formula.dto.SQLCalc;
import org.jeecgframework.workflow.service.ProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("processServiceImpl")
@Transactional
public class ProcessServiceImpl  extends CommonServiceImpl implements ProcessService{
	
	@Override
	public Map GetProcessType(String typeGroupId,String pId) {
		String SQL = "SELECT ID,TYPENAME FROM T_S_TYPE WHERE TYPEPID='"+pId+"' AND TYPEGROUPID='"+typeGroupId+"'";
		if(pId==null||pId.equals(""))
			SQL = "SELECT ID,TYPENAME FROM T_S_TYPE WHERE TYPEPID IS NULL AND TYPEGROUPID='"+typeGroupId+"'";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	
	private Map ListToMap(List list){
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;
	}

	@Override
	public Map GetProcessByType(String typeid) {
		String SQL = "select PROCESSKEY||'^'||b.online_id,a.PROCESSNAME from T_P_PROCESS a,t_s_busconfig b where a.id = b.processid AND a.TYPEID='"+typeid+"'";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}

	@Override
	public Map GetTaskNodeMenu(String menuid) {
		String SQL = "select functionurl||'&'||'clickFunctionId='||id,functionname from t_s_function t where parentfunctionid is null  and functiontype='1' order by functionorder";
		if (menuid!=null&&!menuid.equals(""))
			SQL = "select functionurl||'&'||'clickFunctionId='||id,functionname from t_s_function t where parentfunctionid ='"+menuid+"' and functiontype='1' order by functionorder";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	

}
