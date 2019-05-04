package org.jeecgframework.web.formula.service;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.dto.RefedCol;
import org.jeecgframework.web.formula.dto.SQLCalc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface FormulaServiceI  extends CommonService{
	public String getSQLComputeExpression(String SQLComputeName) throws FormulaException;
	public String getFormulaExp(String TableName,String ColumnName) throws FormulaException;
	public String[] GetFromulaComputeCol(String	TableName);
	public Map GetFromulaComputeAllArg(String	TableName);
	public Map GetFromulaComputeArg(String tableName,String columnName);
	public Map getFormulaTable();
	public Map getFormulaColumn();
	/*public Map getRefedCol(String tableName);*/
	public List<RefedCol> getRefedCol(String tableName);
	public boolean saveCalc(Map argMap) throws Exception;
	public boolean saveFormulaName(String tableName, String columnName, String formulaName) throws SQLException;
	public boolean saveSQLCalcName(String tableName,String sqlCalcName) throws SQLException;
	public Map getFormulaName();
	public Map getFormulaName(String tablename,String columnname);
	public String getformulaOriginal(String tablename,String formulaName);
	public Map getSQLCalcName(String tablename);
	public Map getSQLCalcExpress(String SQLCalcName);
	public List<SQLCalc> getSQLCalc(String tablename);
	public boolean saveFormula(String tableName,String columnname, String formulaName, String calcExpressOriginal,String calcExpress,String checkPass,String usedCol,String datarange,String datarangeResult) throws Exception;
	public boolean delFormulaName(String tablename,String formulaname) throws SQLException,Exception;
	public boolean delCalcName(String tablename,String sqlCalcName) throws SQLException,Exception;
	public boolean saveRegion(String tablename,String formulaname,String regionids) throws SQLException,Exception;
	public String getFormulaRegion(String tablename,String formulaname);
	public List getFormulaFactorTree(String tablename);
	public List getDictColAndItem(String tableName);
	public boolean saveFormulaDataRange(String tablename,String formulaname,String datarange,String datarangeResult);
	public Map getAllDictType(String Id);
	public Map getTableList(String calcname);
	public Map getAllDictItem(String Id);
	public Map getAllItems(String sql);
	public Map getTypeItemMap();
	public Map getItemByTableAndCol(String tableName,String colName);
}
