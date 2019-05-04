package org.jeecgframework.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 2018-3-9
 * @author weiya
 *
 */
public class TransInterceptor implements HandlerInterceptor {
    private static String transpage; 
	static{
		 PropertiesUtil util = new PropertiesUtil("dbconfig.properties");
		 transpage =  util.readProperty("transpage"); 
	}
	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		
		 HttpSession session = ContextHolderUtils.getSession();
		 String trsession = (String) (null==session.getAttribute("transtion")?"":session.getAttribute("transtion"));
		 
		 if(null==session.getAttribute("transtion") || "null".equals((String)session.getAttribute("transtion")) || ((String)session.getAttribute("transtion")).equals("")){
			 trsession="false";
		 }
		 if(!transpage.equals("nopage")){ 
			 if(request.getRequestURI().equals("/jeecg/loginController.do") && (request.getQueryString().equals("login") || request.getQueryString().equals("hplusbluehome"))
					 && trsession.equals("true") ){
				 request.getRequestDispatcher(transpage).forward(request, response); 
			 }
		 }
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
