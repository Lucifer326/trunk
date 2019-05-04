package org.jeecgframework.web.formula.service;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.dto.DictColAndItem;
import org.jeecgframework.web.formula.dto.FormulaFactorTree;
import org.jeecgframework.web.formula.dto.RefedCol;
import org.jeecgframework.web.formula.dto.SQLCalc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("formulaService")
@Transactional
public class FormulaServiceImpl  extends CommonServiceImpl implements FormulaServiceI{
	@Override
	public String getSQLComputeExpression(String SQLComputeName) throws FormulaException{
		//return this.findListbySql(SQLComputeName);
		String queryComuteExpression = "SELECT COMPUTESQL FROM CGFORM_SQLCALC WHERE NAME='"+SQLComputeName+"'";
		List<String> SQLExp = this.findListbySql(queryComuteExpression);
		if (SQLExp.size()<=0){
			throw new FormulaException("在数据库中未找到SQL计算器名称为{"+SQLComputeName+"}的相关记录，请核查！");
		}
		if (SQLExp.size()>1){
			throw new FormulaException("在数据库中找到SQL计算器名称为{"+SQLComputeName+"}的相关记录超过1条，请核查！");
		}
		return SQLExp.get(0).toString();
	}
	@Override
	public String getFormulaExp(String TableName,String ColumnName) throws FormulaException{
		String SQL = "SELECT EXPRESSION FROM CGFORM_FORMULA WHERE TABLENAME='"+TableName+"' AND NAME='"+ColumnName+"'";
		List<String> SQLExp = this.findListbySql(SQL);
		if (SQLExp.size()<=0){
			throw new FormulaException("在数据库中未找到表【"+TableName+"】的公式列【"+ColumnName+"】的相关记录，请核查！");
		}
		if (SQLExp.size()>1){
			throw new FormulaException("在数据库中找到表【"+TableName+"】的公式列【"+ColumnName+"】的相关记录超过1条，请核查！");
		}
		return SQLExp.get(0).toString();
	}
	@Override
	public String[] GetFromulaComputeCol(String TableName) {
		String SQL = "SELECT NAME FROM CGFORM_FORMULA WHERE TABLENAME='"+TableName+"'";
		List<String> SQLExp = this.findListbySql(SQL);
		StringBuffer sb = new StringBuffer(100);
		for(int i=0;i<SQLExp.size();i++){
			sb.append(SQLExp.get(i).toString()).append(",");
		}
		if (sb.length()>0)
			return sb.toString().split(",");
		else
			return null;
	}
	@Override
	public Map GetFromulaComputeAllArg(String TableName) {
		// TODO Auto-generated method stub

		String SQL = "SELECT FORMULANAME,REFCOLUMN,REFCOLTYPE FROM CGFORM_FORMULA_DETAIL WHERE TABLENAME='"+TableName+"' ORDER BY FORMULANAME";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap2(SQLExp);
		return argMap;
	}
	@Override
	public Map GetFromulaComputeArg(String tableName,String columnName) {
		String SQL = "SELECT REFCOLUMN,REFCOLTYPE FROM CGFORM_FORMULA_DETAIL WHERE TABLENAME='"+tableName+"' AND FORMULANAME='"+columnName+"' ";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	private Map ListToMap2(List list){
		Map argMap = new HashMap();
		Map argMap2 = new HashMap();
		String formulaName = "",last="";
		String refColumn="";
		String refColType="";
		//Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			formulaName = tm[0].toString();
			if (!formulaName.equals(last)&&!last.equals("")){
				argMap.put(last, argMap2);
				argMap2 = new HashMap();
			}
			last = formulaName;
			refColumn =tm[1].toString();
			refColType =tm[2].toString();
			argMap2.put(refColumn, refColType);
		}
		if (list.size()>0)
			argMap.put(formulaName, argMap2);
		return argMap;
	}
	@Override
	public Map getFormulaTable() {
		// TODO Auto-generated method stub
		String SQL = "select a.TABLE_NAME,a.CONTENT from CGFORM_HEAD a,CGFORM_FIELD b where a.ID = b.TABLE_ID AND b.IFFORMULA = '1'";
		List<Object> SQLExp = this.findListbySql(SQL);
		return ListToMap(SQLExp);
	}
	private Map ListToMap(List list){
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;
	}
	@Override
	public Map getFormulaColumn() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String SQL = "SELECT A.TABLE_NAME,B.FIELD_NAME,B.CONTENT FROM CGFORM_HEAD A,CGFORM_FIELD B WHERE A.ID = B.TABLE_ID AND B.IFFORMULA = '1' ORDER BY A.TABLE_NAME";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap2(SQLExp);
		return argMap;
	}
	@Override
	public List<RefedCol> getRefedCol(String tableName) {
		// TODO Auto-generated method stub
		String SQL = "SELECT B.CONTENT,B.FIELD_NAME,B.DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FIELD B WHERE A.ID = B.TABLE_ID AND A.TABLE_NAME = '"+tableName+"' AND B.IFFORMULA <> '1' AND B.IS_SHOW='Y'";
		List<Object> SQLExp = this.findListbySql(SQL);
		List<RefedCol> colList = new ArrayList<RefedCol>();
		for (Object a:SQLExp){
			Object[] b = (Object[]) a;
			RefedCol refedCol = new RefedCol();
			refedCol.setColCName(b[0].toString());
			refedCol.setColName(b[1].toString());
			refedCol.setDictField(b[2]==null?"":b[2].toString());
			colList.add(refedCol);
		}
		return colList;
	}
	@Override
	public boolean saveFormula(String tableName,String columnname,String formulaName, String calcExpressOriginal,String calcExpress,String checkPass,String usedCol,String datarange,String datarangeResult) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(100);
		String[] usedCols = usedCol.split(",");
		sb.append("UPDATE CGFORM_FORMULA SET (ORIGINAL,EXPRESSION,COMPUTETYPE,CHECKPASS,DATARANGEORI,DATARANGE) = (SELECT '"+calcExpressOriginal+"','"+calcExpress+"','3','"+checkPass+"','"+datarange+"','"+datarangeResult+"' FROM DUAL ) WHERE NAME='"+formulaName+"' AND TABLENAME='"+tableName+"'");
		try{
			this.updateBySqlString(sb.toString());
			if (usedCols.length>0){
				String formulaDetailSQL = "DELETE FROM CGFORM_FORMULA_DETAIL WHERE FORMULANAME= '"+formulaName+"' AND TABLENAME='"+tableName+"'";
				this.updateBySqlString(formulaDetailSQL);
				for (String uCol:usedCols){
					formulaDetailSQL = "INSERT INTO CGFORM_FORMULA_DETAIL (ID,COLUMNNAME,FORMULANAME,TABLENAME,REFCOLUMN,REFCOLTYPE)  SELECT SUBSTR(SYS_GUID(),0),'"+columnname+"','"+formulaName+"','"+tableName+"','"+uCol+"',CASE WHEN \"TYPE\" = 'string' THEN	'3' WHEN \"TYPE\" = 'int' THEN	'1' WHEN \"TYPE\" = 'integer' THEN	'1' WHEN \"TYPE\" = 'double' THEN	'2' WHEN \"TYPE\" = 'decimal' THEN	'2' WHEN \"TYPE\" = 'float' THEN '2' ELSE '3' END AS COLTYPE FROM CGFORM_FIELD A,CGFORM_HEAD B WHERE UPPER(A.FIELD_NAME) = '"+uCol.toUpperCase()+"' AND A.TABLE_ID = B.ID AND B.TABLE_NAME = '"+tableName+"'";
					this.updateBySqlString(formulaDetailSQL);
				}
			}
		} catch(Exception ex){
			throw ex;
		}
		return true;
	} 
	@Override
	public boolean saveFormulaName(String tableName, String columnName, String formulaName) throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(100);
		sb.append("INSERT INTO CGFORM_FORMULA (ID,NAME,TABLENAME,COLUMNNAME,TABLEID,CHECKPASS) SELECT SUBSTR(SYS_GUID(),0),'"+formulaName+"','"+tableName+"','"+columnName+"',A.ID,'0' FROM CGFORM_HEAD A WHERE A.TABLE_NAME='"+tableName+"'");
		try{
			this.updateBySqlString(sb.toString());
		} catch(Exception ex){
			throw ex;
		}
		return true;
	}
	@Override
	public Map getFormulaName(String tablename, String columnname) {
		// TODO Auto-generated method stub
		String SQL = "SELECT B.TABLENAME||'^'||B.COLUMNNAME,B.NAME FROM CGFORM_FORMULA B WHERE TABLENAME = '"+tablename+"' AND B.COLUMNNAME = '"+columnname+"' ";
		if ((tablename==null||tablename.equals(""))&&(columnname==null||columnname.equals(""))){
			SQL = "SELECT B.TABLENAME||'^'||B.COLUMNNAME,B.NAME FROM CGFORM_FORMULA B ";
		}
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public String getformulaOriginal(String tablename, String formulaName) {
		// TODO Auto-generated method stub
		String SQL = "SELECT B.ORIGINAL||','||B.DATARANGEORI FROM CGFORM_FORMULA B WHERE TABLENAME = '"+tablename+"' AND B.NAME = '"+formulaName+"' ";
		List<String> SQLExp = this.findListbySql(SQL);
		if (SQLExp.size()>0)
			return SQLExp.get(0)==null?"":SQLExp.get(0).toString();
		else
			return null;
	}
	@Override
	public boolean saveSQLCalcName(String tableName, String sqlCalcName) throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(100);
		sb.append("INSERT INTO CGFORM_SQLCALC (ID,NAME,TABLENAME,IFPRIVATE) SELECT SUBSTR(SYS_GUID(),0),'"+sqlCalcName+"','"+tableName+"','0' FROM DUAL ");
		try{
			this.updateBySqlString(sb.toString());
		} catch(Exception ex){
			throw ex;
		}
		return true;
	} 
	@Override
	public Map getSQLCalcName(String tablename) {
		// TODO Auto-generated method stub
		String SQL = "SELECT B.ID,B.NAME FROM CGFORM_SQLCALC B,CGFORM_SQLCALCTABLE C WHERE UPPER(C.TABLEID) = UPPER('"+tablename+"') AND C.NAME = B.NAME ";
		if (tablename==null||tablename.equals(""))
			SQL = "SELECT B.ID,B.NAME FROM CGFORM_SQLCALC B";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public Map getSQLCalcExpress(String SQLCalcName) {
		String SQL = "SELECT CASE WHEN B.CONTENTORI=NULL THEN '' ELSE B.CONTENTORI END,FROMITEM||'#'||CASE WHEN B.TYPEID=NULL THEN '' ELSE B.TYPEID END FROM CGFORM_SQLCALC B WHERE B.NAME = '"+SQLCalcName+"' ";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map SQLCalcMap = new HashMap();
		if (SQLExp.size()>0){
			Map<Object, Object> map = new HashMap<Object, Object>();
			for (Iterator iterator = SQLExp.iterator(); iterator.hasNext();) {
				Object[] tm = (Object[]) iterator.next();
				SQLCalcMap.put("calccontent", tm[0]==null?"":tm[0].toString());
				SQLCalcMap.put("fromitem", tm[1]==null?"":tm[1].toString());
			}
			SQL = "SELECT B.TABLEID,TABLENAME FROM CGFORM_SQLCALCTABLE B WHERE B.NAME = '"+SQLCalcName+"' ";
			SQLExp = this.findListbySql(SQL);
			if (SQLExp.size()>0){
				Map tablMap = ListToMap(SQLExp);
				SQLCalcMap.put("tablesmap", tablMap);
			}
			return SQLCalcMap;
		}
		else
			return null;
	}
	@Override
	public boolean saveCalc(Map argMap)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(100);
		String tableName  = argMap.get("tablename").toString();
		String sqlCalcName = argMap.get("sqlcalcname").toString();
		String contentOri = argMap.get("sqlexpressori").toString();
		String computeSQL = argMap.get("sqlexpressresult").toString();
		String ifcheck =  argMap.get("ifcheck").toString();
		String selecttableids =  argMap.get("selecttableids").toString();
		String selecttablenames =  argMap.get("selecttablenames").toString();
		String typeitemid = argMap.get("typeitemid").toString();
		String ifprivate="0";
		if (computeSQL.toUpperCase().indexOf(tableName.toUpperCase())>-1)
			ifprivate="1";
		sb.append("UPDATE CGFORM_SQLCALC SET (CONTENTORI,COMPUTESQL,IFPRIVATE,FROMITEM,TYPEID) = (SELECT '"+contentOri+"','"+computeSQL+"','"+ifprivate+"','"+ifcheck+"','"+typeitemid+"' FROM DUAL) WHERE NAME='"+sqlCalcName+"' ");//AND TABLENAME='"+tableName+"'");
		try{
			this.updateBySqlString(sb.toString());
			String ids[] = selecttableids.split(",");
			String tablename[] = selecttablenames.split(",");
			if (ids.length>0){
				String formulaDetailSQL = "DELETE FROM CGFORM_SQLCALCTABLE WHERE NAME= '"+sqlCalcName+"' ";
				this.updateBySqlString(formulaDetailSQL);
				for (int i=0;i<ids.length;i++){
					formulaDetailSQL = "INSERT INTO CGFORM_SQLCALCTABLE (ID,NAME,TABLEID,TABLENAME)  SELECT SUBSTR(SYS_GUID(),0),'"+sqlCalcName+"','"+ids[i]+"','"+tablename[i]+"' FROM DUAL ";
					this.updateBySqlString(formulaDetailSQL);
				}
			}
		} catch(Exception ex){
			throw ex;
		}
		return true;
	}
	@Override
	public boolean delFormulaName(String tablename, String formulaname)  throws SQLException,Exception{
		// TODO Auto-generated method stub
		String delFormulaSQL = "DELETE CGFORM_FORMULA WHERE TABLENAME='"+tablename+"' AND NAME = '"+formulaname+"' ";
		try{
			this.updateBySqlString(delFormulaSQL);
			delFormulaSQL = "DELETE CGFORM_FORMULA_DETAIL WHERE TABLENAME='"+tablename+"' AND FORMULANAME = '"+formulaname+"' ";
			this.updateBySqlString(delFormulaSQL);
		} catch(Exception ex){
			throw ex;
		}
		return true;
	}
	@Override
	public boolean delCalcName(String tablename, String sqlCalcName)  throws SQLException,Exception {
		String delFormulaSQL = "DELETE CGFORM_SQLCALC WHERE NAME='"+sqlCalcName+"'";
		try{
			this.updateBySqlString(delFormulaSQL);
		} catch(Exception ex){
			throw ex;
		}
		return true;
	}
	@Override
	public boolean saveRegion(String tablename, String formulaname, String regionids) throws SQLException, Exception {
		// TODO Auto-generated method stub
		String saveRegionSQL = "";
		String[] regionID = regionids.split(",");
		if (regionID.length>0){
			saveRegionSQL="DELETE FROM CGFORM_FORMULA_REGION WHERE TABLENAME='"+tablename+"' AND FORMULANAME='"+formulaname+"'";
			try{
				this.updateBySqlString(saveRegionSQL);
			} catch(Exception ex){
				throw ex;
			}
		}
		for (String id:regionID){
			saveRegionSQL="INSERT INTO CGFORM_FORMULA_REGION (ID,TABLENAME,FORMULANAME,REGIONID) SELECT SUBSTR(SYS_GUID(),0),'"+tablename+"','"+formulaname+"','"+id+"' FROM DUAL ";
			try{
				this.updateBySqlString(saveRegionSQL);
			} catch(Exception ex){
				throw ex;
			}
		} 
		return true;
	}
	@Override
	public String getFormulaRegion(String tablename, String formulaname) {
		// TODO Auto-generated method stub
		String SQL = "SELECT B.REGIONID FROM CGFORM_FORMULA_REGION B WHERE TABLENAME = '"+tablename+"' AND B.FORMULANAME = '"+formulaname+"' ";
		List<String> SQLExp = this.findListbySql(SQL);
		if (SQLExp.size()>0){
			StringBuffer sb = new StringBuffer(100);
			for (int i=0;i<SQLExp.size();i++){
				sb.append(SQLExp.get(i).toString()).append(",");
			}
			return sb.toString();
		}
		else
			return "";
	}
	@Override
	public Map getFormulaName() {
		// TODO Auto-generated method stub
		return getFormulaName("","");
	}
	@Override
	public List<FormulaFactorTree> getFormulaFactorTree(String tablename) {
		// TODO Auto-generated method stub
		StringBuffer sbSQL = new StringBuffer(100);
		sbSQL.append("SELECT B.TABLENAME,B.COLUMNNAME,B.NAME AS FORMULANAME,A.CONTENT AS TABLENAMEC,C.CONTENT AS COLUMNNAMEC,C.DICT_FIELD AS DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FORMULA B ,CGFORM_FIELD C");
		sbSQL.append(" WHERE A.TABLE_NAME = B.TABLENAME");
		sbSQL.append(" AND A.ID=C.TABLE_ID");
		sbSQL.append(" AND C.FIELD_NAME = B.COLUMNNAME");
		sbSQL.append(" AND UPPER(a.TABLE_NAME) = '").append(tablename).append("'");
		sbSQL.append(" ORDER BY TABLENAME,COLUMNNAME,NAME");
		return this.convertObject(this.findListbySql(sbSQL.toString()));
	}
	private List<FormulaFactorTree> convertObject(List ObjectList){
		List<FormulaFactorTree> treeList = new ArrayList<FormulaFactorTree>();
		for (Object a:ObjectList){
			Object[] b = (Object[]) a;
			FormulaFactorTree formulaFactor = new FormulaFactorTree();
			formulaFactor.setTableName(b[0].toString());
			formulaFactor.setColumnName(b[1].toString());
			formulaFactor.setFormulaName(b[2].toString());
			formulaFactor.setTableNamec(b[3].toString());
			formulaFactor.setColumnNamec(b[4].toString());
			formulaFactor.setDictfield(b[5]==null?"":b[5].toString());
			treeList.add(formulaFactor);
		}
		return treeList;
	}
	@Override
	public List<DictColAndItem> getDictColAndItem(String tableName){
		// TODO Auto-generated method stub
		StringBuffer sbSQL = new StringBuffer(100);
		sbSQL.append("SELECT B.FIELD_NAME,B.CONTENT,B.DICT_FIELD,D.TYPECODE,D.TYPENAME FROM CGFORM_FIELD B,CGFORM_HEAD A,T_S_TYPEGROUP C,T_S_TYPE D ");
		sbSQL.append("WHERE A.ID = B.TABLE_ID AND B.DICT_FIELD= C.TYPEGROUPCODE AND C.ID = D.TYPEGROUPID ");
		sbSQL.append("AND UPPER(A.TABLE_NAME) = '"+tableName.toUpperCase()+"' ");
		sbSQL.append("ORDER BY B.FIELD_NAME,D.TYPECODE");
		return this.convert2DictColAndItem(this.findListbySql(sbSQL.toString()));
	}
	@Override
	public Map getAllDictType(String Id){
		// TODO Auto-generated method stub
		StringBuffer sbSQL = new StringBuffer(100);
		sbSQL.append("SELECT ID,TYPEGROUPNAME AS TYPE_NAME FROM T_S_TYPEGROUP ");
		sbSQL.append("WHERE IS_DELETED = '0' ");
		if (Id==null||Id.equals(""))
			sbSQL.append("AND PARENT_ID IS NULL ");
		else
			sbSQL.append("AND PARENT_ID ='"+Id+"' ");
		sbSQL.append("ORDER BY ORDER_IN_LEVEL ");
		List<Object> SQLExp = this.findListbySql(sbSQL.toString());
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	private List<DictColAndItem> convert2DictColAndItem(List ObjectList){
		List<DictColAndItem> treeList = new ArrayList<DictColAndItem>();
		for (Object a:ObjectList){
			Object[] b = (Object[]) a;
			DictColAndItem dictColAndItem = new DictColAndItem();
			dictColAndItem.setFieldName(b[0].toString());
			dictColAndItem.setFieldCName(b[1].toString());
			dictColAndItem.setDictField(b[2].toString());
			dictColAndItem.setTypeCode(b[3].toString());
			dictColAndItem.setTypeName(b[4].toString());
			treeList.add(dictColAndItem);
		}
		return treeList;
	}
	@Override
	public boolean saveFormulaDataRange(String tablename, String formulaname, String datarange,
			String datarangeResult) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("UPDATE CGFORM_FORMULA SET (DATARANGEORI,DATARANGE) = (SELECT '"+datarange+"','"+datarangeResult+"' FROM DUAL) WHERE NAME='"+formulaname+"' AND TABLENAME='"+tablename+"'");
		try{
			this.updateBySqlString(sb.toString());
		} catch(Exception ex){
			throw ex;
		}
		return true;
	}
	@Override
	public Map getTableList(String calcname) {
		// TODO Auto-generated method stub
		String SQL = "SELECT TABLE_NAME,CONTENT||'^'||(case when b.id is null then '0' else '1' end) from CGFORM_HEAD a left join cgform_sqlcalctable b on a.table_name = b.tableid and b.name = '"+calcname+"' ";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public Map getAllDictItem(String Id) {
		// TODO Auto-generated method stub
		String SQL = "SELECT TYPECODE AS ITEM_KEY,TYPENAME AS ITEM_VALUE FROM T_S_TYPE WHERE TYPEGROUPID = '"+Id+"' AND IS_DELETED = '0' ORDER BY ORDER_IN_LEVEL ";
		List<Object> SQLExp = this.findListbySql(SQL);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public List<SQLCalc> getSQLCalc(String tablename) {
		String SQL = "SELECT B.ID,B.NAME,B.FROMITEM,B.CONTENTORI,B.COMPUTESQL,B.TYPEID FROM CGFORM_SQLCALC B,CGFORM_SQLCALCTABLE C WHERE UPPER(C.TABLEID) = UPPER('"+tablename+"') AND C.NAME = B.NAME ";

		List<Object> SQLExp = this.findListbySql(SQL);
		List<SQLCalc> treeList = new ArrayList<SQLCalc>();
		for (Object a:SQLExp){
			Object[] b = (Object[]) a;
			SQLCalc sqlCalc = new SQLCalc();
			sqlCalc.setID(b[0].toString());
			sqlCalc.setName(b[1].toString());
			sqlCalc.setFromItem(b[2].toString());
			sqlCalc.setContentOri(b[3].toString());
			sqlCalc.setSQLExpress(b[4].toString());
			sqlCalc.setTypeID(b[5]==null?"":b[5].toString());
			treeList.add(sqlCalc);
		}
		return treeList;
	}
	@Override
	public Map getAllItems(String typeID) {
		String sql = "SELECT TYPECODE AS ITEM_KEY,TYPENAME AS ITEM_VALUE FROM T_S_TYPE WHERE TYPEGROUPID =  '"+typeID+"' UNION ALL SELECT TYPEGROUPCODE AS TYPE_CODE,TYPEGROUPNAME AS TYPE_NAME FROM T_S_TYPEGROUP WHERE PARENT_ID = '"+typeID+"'";
		List<Object> SQLExp = this.findListbySql(sql);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public Map getTypeItemMap() {
		// TODO Auto-generated method stub
		String sql = "select B.TYPENAME AS ITEM_VALUE,''''||TYPECODE||'''' from T_S_TYPE B UNION ALL SELECT TYPEGROUPNAME AS TYPE_NAME,''''||TYPEGROUPCODE||'''' FROM T_S_TYPEGROUP ";
		List<Object> SQLExp = this.findListbySql(sql);
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}
	@Override
	public Map getItemByTableAndCol(String tableName, String colName) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(100);
		/*sb.append("select B.TYPE_CODE,B.TYPE_NAME from CGFORM_DICTIONARY_TYPE A,CGFORM_DICTIONARY_TYPE B ");
		sb.append("WHERE A.TYPE_CODE=(SELECT B.DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FIELD B ");
		sb.append("WHERE A.ID = B.TABLE_ID AND UPPER(A.TABLE_NAME) = '"+tableName.toUpperCase()+"' AND UPPER(B.FIELD_NAME)='"+colName.toUpperCase()+"') AND B.PARENT_ID = A.ID ");
		sb.append("UNION ALL ");
		sb.append("select B.ITEM_KEY,B.ITEM_VALUE from CGFORM_DICTIONARY_TYPE A,CGFORM_DICTIONARY_ITEM B ");
		sb.append("WHERE A.TYPE_CODE=(SELECT B.DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FIELD B ");
		sb.append("WHERE A.ID = B.TABLE_ID AND UPPER(A.TABLE_NAME) = '"+tableName+"' AND UPPER(B.FIELD_NAME)='"+colName.toUpperCase()+"') AND B.TYPE_ID = A.ID ");*/

		sb.append("select B.TYPEGROUPCODE AS TYPE_CODE,B.TYPEGROUPNAME AS TYPE_NAME from T_S_TYPEGROUP A,T_S_TYPEGROUP B ");
		sb.append("WHERE A.TYPEGROUPCODE=(SELECT B.DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FIELD B ");
		sb.append("WHERE A.ID = B.TABLE_ID AND UPPER(A.TABLE_NAME) = '"+tableName.toUpperCase()+"' AND UPPER(B.FIELD_NAME)='"+colName.toUpperCase()+"') AND B.PARENT_ID = A.ID ");
		sb.append("UNION ALL ");
		sb.append("select B.TYPECODE AS ITEM_KEY,B.TYPENAME AS ITEM_VALUE from T_S_TYPEGROUP A,T_S_TYPE B ");
		sb.append("WHERE A.TYPEGROUPCODE=(SELECT B.DICT_FIELD FROM CGFORM_HEAD A,CGFORM_FIELD B ");
		sb.append("WHERE A.ID = B.TABLE_ID AND UPPER(A.TABLE_NAME) = '"+tableName+"' AND UPPER(B.FIELD_NAME)='"+colName.toUpperCase()+"') AND B.TYPEGROUPID = A.ID ");
		List<Object> SQLExp = this.findListbySql(sb.toString());
		Map argMap = ListToMap(SQLExp);
		return argMap;
	}

}
