package org.jeecgframework.web.formula.compute;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.formula.analysis.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("sqlCompute")
@Transactional
public class SQLCompute extends CommonServiceImpl implements CalculateI{
	public Long getFormulaValLong(String SQLSelect){
		return this.getCountForJdbc(SQLSelect);		
	}
	public List getFormulaValues(String SQLSelect){
		return this.findForJdbc(SQLSelect);		
	}
	public String getFormulaVal(String sql) throws Exception{
		String val="";
		try {
			Map<String ,Object> result =  this.findOneForJdbc(sql,null);
			if (result==null)
				return "";
			Iterator<Entry<String, Object>> it = result.entrySet().iterator();
			if (it.hasNext()){
				Entry<String, Object> key = it.next();
				val = StringUtil.ObjectConvertString(key.getValue());
				//val =((BigDecimal)(key.getValue())).toString();
			}
		}catch (Exception ex){
			throw ex;
		}	
		return val;
	}
	@Override
	public String getFormulaVal(String formulaexp, Map argVal,String tableName) throws Exception {
		// TODO Auto-generated method stub
		try{
			return this.getFormulaVal(formulaexp);
		} catch (Exception ex){
			throw ex;
		}	
	}

}
