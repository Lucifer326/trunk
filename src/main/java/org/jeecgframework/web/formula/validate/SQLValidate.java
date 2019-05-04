package org.jeecgframework.web.formula.validate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecgframework.web.formula.analysis.FormulaConstant;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.analysis.StringUtil;
import org.jeecgframework.web.formula.compute.CalculateI;
import org.jeecgframework.web.formula.compute.SQLCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
@Service
public class SQLValidate implements ValidateI {
	//@Autowired
	private CalculateI sqlCalculate;
	public void setSQLCalculate(){
			WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
			Object o = webContext.getBean("sqlCalculate");
			this.sqlCalculate = (CalculateI) o;
	}
	@Override
	public boolean validateFormula(String express,Map valMap,String tableName) throws FormulaException,Exception{
		// TODO Auto-generated method stub
		boolean ok=false;
		if (this.sqlCalculate ==null)
			this.setSQLCalculate();
		try{
			if (valMap!=null){
				Map virtualVal = new HashMap();
				Iterator<Entry<String,String>> it = valMap.entrySet().iterator();
				while (it.hasNext()){
					virtualVal.put(it.next().getValue(),"1");
				}
				if(FormulaConstant.reserveWordMap!=null){
					Iterator<Entry<String,String>> it1 = FormulaConstant.reserveWordMap.entrySet().iterator();
					while (it1.hasNext()){
						Entry<String, String> key = it1.next();
						virtualVal.put(key.getKey(),key.getValue());
					}
				}
				sqlCalculate.getFormulaVal(express.replace("1=1", "1=2"), virtualVal,tableName);
			} else{
				String sqlCalcExpress = express.toUpperCase().replace("WHERE", "WHERE 1=2 AND ");
				sqlCalcExpress = StringUtil.replaceAllValueByMap(sqlCalcExpress,FormulaConstant.reserveWordMap);
				sqlCalculate.getFormulaVal(sqlCalcExpress, null,tableName);
			}
			ok = true;
		} catch (FormulaException fx){
			ok = false;
			throw fx;
		} catch (Exception ex){
			ok = false;
			throw ex;
		} 
		return ok;
	}
	@Override
	public String validate(String express, String tableName, Map valMap) throws FormulaException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
