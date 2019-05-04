package org.jeecgframework.web.formula.compute;

import java.util.Map;

import org.jeecgframework.web.formula.analysis.FormulaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Calculate implements CalculateI {
	
	private SQLCalculate sqlCalc;
	
	private VelocityCalculate velocityCalc;

	@Autowired
	public Calculate(VelocityCalculate velocityCalc,SQLCalculate sqlCalc){
		this.velocityCalc = velocityCalc;
		this.sqlCalc = sqlCalc;
	}
	@Override
	public String getFormulaVal(String formulaexp,Map argVal,String tableName) throws Exception{
		// TODO Auto-generated method stub
		if (formulaexp.indexOf(FormulaConstant.velocity)>-1)
			return velocityCalc.getFormulaVal(formulaexp, argVal,tableName);
		else {
			return sqlCalc.getFormulaVal(formulaexp, argVal,tableName);
		}
	}


}
