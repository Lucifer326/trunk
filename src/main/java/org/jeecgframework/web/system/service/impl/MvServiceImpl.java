package org.jeecgframework.web.system.service.impl;

import org.jeecgframework.web.system.service.MvServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSModelview;
import org.springframework.beans.BeansException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.ContextLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Title: service implement
 * @Description: 视图扩展
 * @author:zhengxi
 * @date 2017-10-20 10:29:30
 * @version V1.0
 *
 **/

@Service("MvService")
@Transactional
public class MvServiceImpl extends CommonServiceImpl implements MvServiceI {

	private Map ListToMap(List list) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;
	}

	public Map<String, String> gettsfunctionMenu(String menuid) {
		String SQL = "select functionurl||'&'||'clickFunctionId='||id,functionname from t_s_function t where parentfunctionid is null  and functiontype='0' order by functionorder";
		if (menuid != null && !menuid.equals(""))
			SQL = "select functionurl||'&'||'clickFunctionId='||id,functionname from t_s_function t where parentfunctionid ='"
					+ menuid + "' and functiontype='0' order by functionorder";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}

	public List<TSFunction> gettsfunctionMenudata(String id) {
		String hql = "from TSFunction t where id =? and functiontype='0' order by functionorder ";
		List<TSFunction> res = commonDao.findHql(hql, id);
		return res;

	}

	public String gettsfunctionview(String url)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		String res = null;
		try {

			String controller = url.substring(0, url.indexOf(".do?"));
			String method = url.substring(url.indexOf("?") + 1);
			WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
			Object o = webContext.getBean(controller);
		
			Method m = o.getClass().getDeclaredMethod(method, getMethodParamTypes(o, method));
			Class<?> type = m.getReturnType();
			String types = type.toString();
			if (types.indexOf("ModelAndView") == -1) {
				return "fk";
			}
			int paralen = getMethodParamTypes(o, method).length;
			// Type t = o.getAnnotatedReturnType().getType();
		

			try {
				ModelAndView xAndView;
				try {
					
					Object[] args = new Object[paralen];
			xAndView = (ModelAndView) m.invoke(o.getClass().newInstance(),args);//根据参数个数确定数组长度并自动初始化为null
					res = xAndView.getViewName();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "fk";
				}

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fk";
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fk";
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fk";
			}

			return res;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fk";
		}
	}

	public static Class[] getMethodParamTypes(Object classInstance, String methodName) throws ClassNotFoundException {
		Class[] paramTypes = null;
		Method[] methods = classInstance.getClass().getMethods();// 全部方法
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equals(methods[i].getName())) {// 和传入方法名匹配
				Class[] params = methods[i].getParameterTypes();
				paramTypes = new Class[params.length];
				for (int j = 0; j < params.length; j++) {
					paramTypes[j] = Class.forName(params[j].getName());
				}
				break;
			}
		}	
		return paramTypes;
	}

	public int getchid(String pid) {
		String hql;
		if (pid == "0") {
			hql = "SELECT 1 from TSModelview t where pid=NULL" ;

		} else {
			hql = "SELECT 1 from TSModelview t where pid='" + pid + "'";
		}
		//int counts=commonDao.executeHql(hql);
		List<TSModelview> count = commonDao.findByQueryString(hql);
		int counts = count.size();
		return counts;

	}

}