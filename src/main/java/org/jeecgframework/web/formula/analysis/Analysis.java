package org.jeecgframework.web.formula.analysis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Analysis implements AnalysisI{
	
	private SQLAnalysis sqlAnalysis;
	@Autowired
	public void setSqlAnalysis(SQLAnalysis sqlAnalysis) {
		this.sqlAnalysis = sqlAnalysis;
	}
	public String getAnalysis(String formulaContent,String tableName,Map colMap) throws FormulaException{
		String formulaResult = "";
		try{
			formulaResult = sqlAnalysis.getAnalysis(formulaContent,tableName,colMap);
		} catch (FormulaException fx){
			throw fx;
		} finally {
			
		}
		return formulaResult;
	}

}
