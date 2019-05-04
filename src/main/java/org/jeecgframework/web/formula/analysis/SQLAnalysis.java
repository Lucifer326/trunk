package org.jeecgframework.web.formula.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecgframework.web.formula.service.FormulaServiceI;
import org.jeecgframework.web.formula.service.FormulaServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SQLAnalysis implements AnalysisI {
	private AnalysisI formulaAnalysis;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FormulaServiceI formulaServiceImpl;
	/*@Autowired
	public SQLAnalysis(@Qualifier(value="FormulaAnalysis") AnalysisI formulaAnalysis){
		this.formulaAnalysis = formulaAnalysis;
	}*/
	@Autowired
	public SQLAnalysis(FormulaAnalysis formulaAnalysis){
		this.formulaAnalysis = formulaAnalysis;
	}
	@Override
	public String getAnalysis(String formulaContent,String tableName,Map colMap) throws FormulaException {
		// TODO Auto-generated method stub
		//公式表达式解析
		String analysisResult = formulaAnalysis.getAnalysis(formulaContent,tableName,colMap);
		boolean lb_refSelf = false;
		//SQL解析过程实现
		if (analysisResult.toUpperCase().indexOf("SELECT")>-1)
			return analysisResult;
		if (analysisResult.indexOf("{")<0){
			analysisResult = " SELECT " + analysisResult+" FROM DUAL ";
			return analysisResult;
		}else{
			//如果包含表内公式
			//以上判断缺失
			lb_refSelf = true;
		}
		//存在SQL计算器
		Map SQLComputeMap = new HashMap();
		String SQLComputeName,SQLStatement = "";
		boolean lb=false;
		int i =1;
		String formulaContentTmp = formulaContent;
		StringBuffer sb = new StringBuffer(100);
		StringBuffer sbSQLComputeNameOut = new StringBuffer(100);
		int pos1,pos2;
		List<String> aliases = new ArrayList<String>();
		while (true) {
			SQLComputeName = StringUtil.getMidValueBySplit(formulaContentTmp, "{", "}");
			if (SQLComputeName.equals(""))
				break;
			if (FormulaConstant.unitTest){
				if (SQLComputeName.equals("女职工卫生费标准"))
					SQLStatement = "SELECT MONEYS FROM TMP2 WHERE TYPEID = '女职工卫生费标准'";
				if (SQLComputeName.equals("独生子女费标准"))
					SQLStatement = "SELECT MONEYS FROM TMP2,TMP WHERE TYPEID = '独生子女费标准' AND TMP.ID = TMP2.a";					
			}
			else
				SQLStatement = formulaServiceImpl.getSQLComputeExpression(SQLComputeName).toUpperCase();			
			//与本表有关联关系
			if (StringUtil.matches(SQLStatement.toUpperCase(),"\\s"+tableName.toUpperCase()+"\\s")||
					StringUtil.matches(SQLStatement.toUpperCase(),"\\s"+tableName.toUpperCase()+"\\,")||
					StringUtil.matches(SQLStatement.toUpperCase(),"\\,"+tableName.toUpperCase()+"\\,")||
					StringUtil.matches(SQLStatement.toUpperCase(),"\\,"+tableName.toUpperCase()+"\\s")){
				lb = true;
				sbSQLComputeNameOut.append(SQLComputeName+",");				
				pos1 = SQLStatement.toUpperCase().indexOf(" FROM ");
				pos2 = SQLStatement.toUpperCase().indexOf(tableName.toUpperCase()+".ID");
				String[] selfCol = StringUtil.getSelfrelaCol(SQLStatement.toUpperCase(), tableName.toUpperCase()+".").split(",");
				for (String col:selfCol){
					if (col.equals(""))
						continue;
					SQLStatement=SQLStatement.replace(col,"!"+col+"!");
				}
				if (pos2==-1||pos1<pos2){
					//SQLStatement=SQLStatement.replace(" FROM "," as val,"+tableName.toUpperCase()+".ID as ID "+"FROM ");
					SQLStatement=SQLStatement.replace(" FROM "," as val "+"FROM ");
					SQLStatement=" ("+SQLStatement+") ^SQLCOM"+i+"^";
					aliases.add(SQLStatement);
					//SQLStatement="("+SQLStatement+") ";//oracle不支持在此使用别名
					i++;
				}else{
					//暂不处理
				}
			} else {
				pos1 = SQLStatement.toUpperCase().indexOf(" FROM ");
				SQLStatement=SQLStatement.replace(" FROM "," as val "+"FROM ");
				//SQLStatement="SELECT ("+SQLStatement+") as ^SQLCOM"+i+"^ FROM DUAL";
				SQLStatement=" ("+SQLStatement+") ^SQLCOM"+i+"^";
				aliases.add(SQLStatement);
				//SQLStatement=" ("+SQLStatement+") ";//oracle不支持在此使用别名
				i++;
			}
			SQLComputeMap.put(SQLComputeName, SQLStatement);
			formulaContentTmp=formulaContentTmp.replace("{"+SQLComputeName+"}","");				
		}
		//if (!lb){//和本表无关
		if(1==2){	
			analysisResult = " SELECT " + analysisResult;
			Iterator<Entry<String, String>> it = SQLComputeMap.entrySet().iterator();
			while (it.hasNext()){
				Entry<String, String> key = it.next();
				sb.append(analysisResult.replace("{"+key.getKey()+"}","("+key.getValue()+")"));
			}
			
			sb.append(" FROM DUAL ");
			if(aliases.size() > 0){
				for(String alias : aliases){
					int j = 0;
					sb.append("," + alias +"^SQLCOM"+j+"^");
					j++;
				}
			}
			//analysisResult  = sb.append(" FROM DUAL ").toString();
			analysisResult  = sb.toString();
		} else {
			//公式定义原文
			/*d+e+f+{独生子女费标准}+{女职工卫生费标准}+{独生子女费标准}
			{独生子女费标准}
			SELECT MONEYS FROM TMP2,TMP WHERE TYPEID = '独生子女费标准' AND TMP.a = TMP2.a
			{女职工卫生费标准}
			SELECT MONEYS FROM TMP2 WHERE TYPEID = '女职工卫生费标准'*/
			//解析结果示例
			/*select d+e+f + sqlcom1.val+ sqlcom2.val + sqlcom3.val from tmp as self,			 
			 (SELECT MONEYS as val ,tmp.a as a FROM TMP2,tmp WHERE TYPEID = '女职工卫生费标准' and tmp2.a = tmp.a) sqlcom1,
			  (SELECT max(MONEYS) as val FROM TMP2 WHERE TYPEID = '女职工卫生费标准') sqlcom2,
			  (SELECT MONEYS as val ,tmp.a as a FROM TMP2,tmp WHERE TYPEID = '女职工卫生费标准' and tmp2.a = tmp.a) sqlcom3
			where self.a=sqlcom1.a and self.a = sqlcom3.a*/
			/* SELECT !d!+!e!+!f!+SQLCOM1.val+SQLCOM2.val+SQLCOM1.val 
			 FROM TMP as SELF,
			 (SELECT MONEYS as val,TMP.ID as ID FROM TMP2,TMP 
			 WHERE TYPEID = '独生子女费标准' AND TMP.ID = TMP2.a) as SQLCOM1,
			 (SELECT MONEYS as val FROM TMP2 WHERE TYPEID = '女职工卫生费标准') as SQLCOM2 
			 WHERE  SQLCOM1.ID=SELF.ID AND  1=1 */
			// SELECT !CASE WHEN! !c! = '女' !THEN!  !d!+!e! !ELSE! !f!+SQLCOM1.val+SQLCOM2.val+SQLCOM1.val !END! FROM TMP as SELF,(SELECT MONEYS as val,TMP.ID as ID FROM TMP2,TMP WHERE TYPEID = '独生子女费标准' AND TMP.ID = TMP2.a) as ^SQLCOM1^,(SELECT MONEYS as val FROM TMP2 WHERE TYPEID = '女职工卫生费标准') as ^SQLCOM2^ WHERE  SQLCOM1.ID=SELF.ID AND  1=1 
			//SELECT CASE WHEN c = '女' THEN  d+e ELSE f+SQLCOM1.val+SQLCOM2.val+SQLCOM1.val END FROM TMP as SELF,(SELECT MONEYS as val,TMP.ID as ID FROM TMP2,TMP WHERE TYPEID = '独生子女费标准' AND TMP.ID = TMP2.a) as SQLCOM1,(SELECT MONEYS as val FROM TMP2 WHERE TYPEID = '女职工卫生费标准') as SQLCOM2 WHERE  SQLCOM1.ID=SELF.ID AND  1=1 
			//analysisResult = " SELECT " + analysisResult + " FROM "+tableName.toUpperCase() + " as SELF,";
			analysisResult = " SELECT " + analysisResult + " FROM DUAL,";
			Iterator<Entry<String, String>> it = SQLComputeMap.entrySet().iterator();
			String sqlcomTableNamme;
			StringBuffer sbMtable=new StringBuffer(100);
			StringBuffer sbWhere = new StringBuffer(" WHERE ");
			String selectExp=analysisResult;
			while (it.hasNext()){
				Entry<String, String> key = it.next();
				sqlcomTableNamme = key.getKey();
				SQLStatement = key.getValue();
				pos1 = SQLStatement.indexOf("^SQLCOM");
				if (pos1>0){
					pos2 = SQLStatement.indexOf("^",pos1+1);
					i = Integer.parseInt(SQLStatement.substring(pos1+"^SQLCOM".length(),pos2));
				}				
				selectExp = (selectExp.replace("{"+key.getKey()+"}","SQLCOM"+i+".val"));
				sbMtable.append(SQLStatement);
				if (it.hasNext()){
					sbMtable.append(",");
				}
				if (it.hasNext()){
					//sbWhere.append(" SQLCOM"+i+".ID="+"SELF.ID AND ");
					sbWhere.append(" 1=1 AND ");
				} else
				{
					sbWhere.append(" 1=1 ");
				}
			}
			sb.append(selectExp).append(sbMtable).append(sbWhere);
			analysisResult  = sb.toString();
			
		}
		return analysisResult;
	}

}
