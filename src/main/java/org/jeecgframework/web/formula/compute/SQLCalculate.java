package org.jeecgframework.web.formula.compute;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecgframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
@Service("sqlCalculate")
public class SQLCalculate implements CalculateI {
	
	
	
	//@Autowired
	private CalculateI sqlCompute;
	private void setSqlCompute() {
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
		Object o = webContext.getBean("sqlCompute");
		this.sqlCompute = (CalculateI) o;
	}

	@Override
	public String getFormulaVal(String formulaExp,Map argVal,String tableName) throws Exception{
		// TODO Auto-generated method stub
		String formulaOri = formulaExp.toUpperCase().replace(tableName+".","");
		/*formulaOri = formulaOri.replace(" "+tableName+",", " DUAL,");
		formulaOri = formulaOri.replace(" "+tableName+" ", " DUAL ");
		formulaOri = formulaOri.replace(","+tableName+" ", ",DUAL ");
		formulaOri = formulaOri.replace(","+tableName+",", ",DUAL,");*/
		if (argVal!=null){
			Iterator<Entry<String,String>>  it = argVal.entrySet().iterator();
			String colName,colVal;
			while (it.hasNext()){
				Entry<String,String> key = it.next();
				colName = key.getKey().toUpperCase();
				colVal = key.getValue().toString();
				formulaOri = formulaOri.replace("!"+colName.toUpperCase()+"!", colVal);
				if (colName.indexOf("@")>=0)
				formulaOri = formulaOri.replace(colName.toUpperCase(), colVal);
				//formulaOri = formulaOri.replace("!"+tableName+"."+colName.toUpperCase()+"!", colVal);
			}	
		}
		if (sqlCompute==null)
			this.setSqlCompute();
		try{
			return sqlCompute.getFormulaVal(formulaOri.replace("!","").replace("^",""),null,tableName);
		}catch (Exception ex){
			throw ex;
		}	
	}

}
