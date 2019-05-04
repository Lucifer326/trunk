package org.jeecgframework.workflow.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.formula.service.FormulaServiceI;
import org.jeecgframework.workflow.dao.ActivitiDao;

/**
 * 
 * 类名：FlowUtil
 * 功能：流程实例运行中辅助类
 * 详细：此类暴露给流程，可在流程定义使用表达式来使用此类的方法，必须由Spring创建才有效
 * 
 * 对外暴露的名称 flowUtil
 * 
 * 作者：jeecg
 * 版本：1.0
 * 日期：2013-8-10 下午4:28:40
 *
 */
public class FlowHorse {
	/**
	 * 获取某个人的上级领导（单个）
	 * Horse_Little
	 * 测试
	 * @param applyUserId
	 * @return
	 */
	public String getGatewayExpressTest(String applyUserId){
		/*ActivitiDao activitiDao=ApplicationContextUtil.getContext().getBean(ActivitiDao.class);
		String gangWeiName = activitiDao.getGangWeiName(applyUserId);
		return gangWeiName;*/
		return applyUserId.equals("horse1")?"1":"2";
	}
	public String getDoMan(String applyUserId){
		FormulaServiceI formulaService=(FormulaServiceI)ApplicationContextUtil.getContext().getBean("formulaService");
		Map a = formulaService.getFormulaName("TMP","g");
		return applyUserId.equals("horse1")?"horse2,horse4":"horse3";
	}
}
