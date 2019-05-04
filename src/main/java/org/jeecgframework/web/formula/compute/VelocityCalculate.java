package org.jeecgframework.web.formula.compute;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class VelocityCalculate implements CalculateI {

	@Override
	public String getFormulaVal(String formulaexp,Map argVal,String tableName) {
		// TODO Auto-generated method stub
		//实现利用模版引擎Velocity进行计算的功能
		//分为表内公式和表间公式
		return null;
	}

}