package org.jeecgframework.web.formula.analysis;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class FormulaAnalysis implements AnalysisI {

	//公式解析过程实现
	@Override
	public String getAnalysis(String formulaContent,String tableName,Map colMap) throws FormulaException {
		// TODO Auto-generated method stub		
		//将[基本工资]+[岗位工资]解析为物理列字段
		StringBuffer sb = new StringBuffer(100);

		//字段解析
		String analysisContent;
		analysisContent = StringUtil.replaceAllValueByMap(formulaContent, "[", "]", colMap);
		if (analysisContent.indexOf("请核查")>=0){
			throw new FormulaException("标识符合[]不能正确闭合，请核查！");
		}
		//sb.append(analysisContent);
		//函数面板解析

		analysisContent = StringUtil.replaceAllValueByMap(analysisContent, "【", "】", FormulaConstant.funMap);
		if (analysisContent.indexOf("请核查")>=0){
			throw new FormulaException( "标识符合【】不能正确闭合，请核查！");
		}
		
		analysisContent = StringUtil.replaceAllValueByMap(analysisContent, "［", "］", FormulaConstant.typeItemMap);
		if (analysisContent.indexOf("请核查")>=0){
			throw new FormulaException( "标识符合【】不能正确闭合，请核查！");
		}
		if (analysisContent.indexOf("CASE WHEN")>=0)
			analysisContent = StringUtil.correctCaseWhen(analysisContent);
		sb.append(analysisContent);
		
		return sb.toString();
	}


}
