package org.jeecgframework.web.formula.validate;

import java.util.Map;

import org.jeecgframework.web.formula.analysis.Analysis;
import org.jeecgframework.web.formula.analysis.AnalysisI;
import org.jeecgframework.web.formula.analysis.FormulaConstant;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validate implements ValidateI {

	//@Autowired
	private AnalysisI analysis;
	//@Autowired
	private SQLValidate sqlValidate;
	//@Autowired
	private VelocityValidate velocityValidate;
	@Autowired
	public Validate(Analysis analysis,SQLValidate sqlValidate,VelocityValidate velocityValidate){
		this.analysis = analysis;
		this.sqlValidate = sqlValidate;
		this.velocityValidate = velocityValidate;
	}
	
	public String validate(String express, String tableName,Map valMap) throws FormulaException,Exception {
		// TODO Auto-generated method stub
		try {
			String validateExp = this.analysis.getAnalysis(express, tableName, valMap);
			this.validateFormula(validateExp,valMap,tableName);
			return validateExp;
		} catch (FormulaException fx){
			throw fx;
		} catch (Exception ex){
			throw ex;
		}
	}
	@Override
	public boolean validateFormula(String express,Map valMap,String tableName) throws FormulaException,Exception{
		try {
			if (express.indexOf(FormulaConstant.velocity)>-1)
				return velocityValidate.validateFormula(express,valMap,tableName);
			else
				return sqlValidate.validateFormula(express,valMap,tableName);
		} catch (FormulaException fx){
			throw fx;
		} catch (Exception ex){
			throw ex;
		}
	}

}
