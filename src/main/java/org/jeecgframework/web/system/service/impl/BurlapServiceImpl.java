/**  
 * @Title: BurlapServiceImpl.java
 * @Package org.jeecgframework.web.system.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 王松
 * @date 2017年12月28日
 * @version V1.0  
 */
package org.jeecgframework.web.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSReleaseEntity;
import org.jeecgframework.web.system.service.BurlapService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.caucho.burlap.server.BurlapServlet;

/**
 * @ClassName: BurlapServiceImpl
 * @Description: TODO(服务器端burlap远程接口实现类)
 * @author 王松
 * @date 2017年12月28日
 *
 */

public class BurlapServiceImpl extends BurlapServlet  implements BurlapService{
	
	private CommonService commonService;
	
	private void setSqlCompute() {
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
		Object o = webContext.getBean("commonService");
		this.commonService = (CommonService) o;
	}

	/**
	 * @Title: getReleaseCode
	 * @Description: TODO(获取版本信息集合)
	 * @param @return    参数
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	@Override
	public Map<String, String> getReleaseCode() {
		String hql="from TSReleaseEntity";
		if (this.commonService==null)
			this.setSqlCompute();
		List<TSReleaseEntity> list=this.commonService.findByQueryString(hql);
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).getGroupname()+"+"+list.get(i).getName(), list.get(i).getVersion());
		}
		return map;
	}


	

}
