package org.jeecgframework.web.formula.analysis;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String replaceValueByMap(String Context,String start,String end,Map colMap) throws FormulaException{
		int pos1,pos2;
		String colname_CH,result;
		pos1 = Context.indexOf(start);
		if (pos1 < 0)
			return Context;
		pos2 = Context.indexOf(end,pos1+1);
		if (pos2 < 0){
			throw new FormulaException("标识符合"+start+end+"不能正确闭合，请核查！");
		}			
		colname_CH = Context.substring(pos1+1, pos2);
		result = Context.replace(start+colname_CH+end,FormulaConstant.colSplit+colMap.get(colname_CH).toString()+FormulaConstant.colSplit);
		return result;
	}
	public static String replaceAllValueByMap(String Context,String start,String end,Map colMap) throws FormulaException{
		String analysisContent = Context;
		StringBuffer sb = new StringBuffer(100);
		do {
			analysisContent =		StringUtil.replaceValueByMap(analysisContent, start, end, colMap);			
		} while (analysisContent.indexOf(start)>=0);
		return sb.append(analysisContent).toString();
	}
	public static String replaceAllValueByMap(String Context,Map colMap) throws FormulaException{
		String analysisContent = Context;
		Iterator<Entry<String,String>> it = colMap.entrySet().iterator();
		while (it.hasNext()){
			Entry<String, String> key = it.next();
			analysisContent = analysisContent.replace(key.getKey(), key.getValue());
		}
		return analysisContent;
	}
	public static String getMidValueBySplit(String Context,String start,String end) throws FormulaException{
		int pos1,pos2;
		pos1 = Context.indexOf(start);
		if (pos1 < 0)
			return "";
		pos2 = Context.indexOf(end, pos1+1);
		if (pos2 < 0){
			throw new FormulaException("标识符合"+start+end+"不能正确闭合，请核查！");
		}			
		return Context.substring(pos1+1, pos2);
	}
	public static String getFactorByMap(String Context,String start,String end,Map colMap) throws FormulaException{
		int pos1,pos2;
		String colname_CH;
		StringBuffer sb = new StringBuffer(100);
		pos1 = Context.indexOf(start);
		while (pos1 >= 0){
			pos2 = Context.indexOf(end,pos1 +1);
			if (pos2 < 0){
				throw new FormulaException("标识符合"+start+end+"不能正确闭合，请核查！");
			}			
			colname_CH = Context.substring(pos1+1, pos2);
			if (sb.toString().indexOf(colMap.get(colname_CH).toString())<0)
				sb.append(colMap.get(colname_CH).toString()).append(",");
			pos1 = Context.indexOf(start,pos2);
		}
		return sb.toString();
	}
	public static boolean matches(String str,String reg){
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if (m.find())
			return true;
		else
			return false;
	} 

	public static String ObjectConvertString(Object param){
		String value = "";
		if (param instanceof Integer) {
			value = String.valueOf(((Integer) param).intValue());
		} else if (param instanceof String) {
			value = (String) param;
		} else if (param instanceof Double) {
			double d = ((Double) param).doubleValue();
			value = String.valueOf(d);
		} else if (param instanceof Float) {
			float f = ((Float) param).floatValue();
			value = String.valueOf(f);
		} else if (param instanceof Long) {
			long l = ((Long) param).longValue();
			value = String.valueOf(l);
		} else if (param instanceof BigDecimal) {
			float bd = ((BigDecimal) param).floatValue();
			value = String.valueOf(bd);
		} 
		return value;
	}
	public static String getSelfrelaCol(String Context,String reg) throws FormulaException{
		int pos1,pos2=0;
		StringBuffer sb = new StringBuffer(100);
		pos1 = Context.indexOf(reg);
		while (pos1 >= 0 && pos2>=0){
			pos2 = Context.indexOf(" ",pos1 +1);
			if (pos2 < 0){
				pos2 = Context.indexOf("=",pos1+1);
			}
			if (pos2 < 0){
				sb.append(Context.substring(pos1)).append(",");
			} else{
				sb.append(Context.substring(pos1, pos2 - 1)).append(",");
				pos1 = Context.indexOf(reg,pos2);
			}
		}
		return sb.toString();
	}

	public static String getSelfusedColFromWhere(String Context,String reg,String split,String hasUsedCol) throws FormulaException{
		int pos1,pos2=0;
		StringBuffer sb = new StringBuffer(hasUsedCol);
		String col = "";
		String tabled = split+reg+".";
		pos1 = Context.indexOf(tabled);
		while (pos1 >= 0 && pos2>=0){
			pos2 = Context.indexOf(split,pos1+1);
			if (pos2>0){
				col = Context.substring(pos1+tabled.length(), pos2);
				if (hasUsedCol.indexOf(col+",")<0)
					sb.append(col+",");
				pos1 = Context.indexOf(tabled,pos2);
			}
		}
		return sb.toString();
	}
	public static String getUUID(){  
		String id=UUID.randomUUID().toString();//生成的id942cd30b-16c8-449e-8dc5-028f38495bb5中间含有横杠，<span style="color: rgb(75, 75, 75); font-family: Verdana, Arial, Helvetica, sans-serif; line-height: 20.7999992370605px;">用来生成数据库的主键id是很实用的。</span>  
		id=id.replace("-", "");//替换掉中间的那个斜杠  
		return id;  
	} 

	/**
	 * 处理解析sql case when end
	 * @param String sql
	 * @return String
	 * @auth		郑熙
	 */

	public static String  correctCaseWhen(String caseStatement){
		String start="CASE WHEN";
		String end = "END";
		String newchar = "WHEN";
		int random = getRandom();
		String[] cases = caseStatement.split(end);
		if (cases.length<=0)
			return caseStatement;
		String result="";
		String tmp="";
		for (int i=0;i<cases.length;i++){
			//tmp = cases[i].substring(j+start.length(),cases[i].length());
			tmp = cases[i];
			if (tmp.trim().indexOf(newchar)<=0){
				result+=cases[i];
				continue;
			}
			tmp = tmp.replaceFirst(start,String.valueOf(random));
			System.out.println(tmp);
			tmp = tmp.replace(start, newchar);
			tmp = tmp.replaceFirst(String.valueOf(random),start);
			result+=tmp+end;
		}
		return result;
	}
	public static void main(String[] arg){
		System.out.println(StringUtil.correctCaseWhen(" 34324 !CASE WHEN! 000 !THEN! A !CASE WHEN! 111 !THEN! B !ELSE! C !END! "));
		/*String sql=" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.1 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.2 "+
				" !ELSE! 0  !END!!CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.3 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.4 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.5 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.6 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.7 "+
				" !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.8 "+
				" !ELSE! 0  !END! !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 0.9 "+
				"  !CASE WHEN!(  {残疾等级名称}  !=!  !'30401214'!  !OR!   {残疾等级名称}  !=!  !'30401213'!)  !THEN!  {城市低保标准}  !*! 1.2 "+
				" !ELSE! 0  !END! ";
		System.out.println(StringUtil.correctCaseWhen(sql));*/
	}
	private static int getRandom(){
		Random r=new java.util.Random();
		return r.nextInt(); 
	}
	
}
