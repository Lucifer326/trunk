package org.jeecgframework.web.servlet.service;



import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("viewResolverService")
@Transactional
public class ViewResolverServiceImpl  extends CommonServiceImpl implements ViewResolverServiceI{
	public String getExtendModelAndView (String name){
		String sql="SELECT VIEWNAME FROM T_S_MODELVIEW WHERE VIEWNAMEORI = '"+name+"'";
		List<String> list = this.commonDao.findListbySql(sql);
		if (list.size()>0)
			return list.get(0).toString();
		else
			return name;
	}
	
}
