package org.jeecgframework.web.formula.analysis;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UnitTest {
	public static void main(String arg[]){
		UnitTest test = new UnitTest();
		test.analysisTest();
		//test.test();

	} 
	public void test(){
		String SQLStatement = "SELECT MONEYS FROM TMP2,TMP, WHERE TYPEID = '独生子女费标准' AND TMP.a = TMP2.a";
		//SQLStatement = "TMP ";
		String tableName="tmp";
		String reg = "\\,TMP\\,";
		try{
				boolean rgx = SQLStatement.matches(reg);//SQLStatement.matches("\\,TMP\\s");
				 System.out.println("rgx:" + rgx); //true    
				 Pattern p = Pattern.compile(reg);
			      Matcher m = p.matcher(SQLStatement);
			      if (m.find())
			    	  System.out.println("find:"); //true    
		} catch (PatternSyntaxException e){
			System.out.println(e);
		}
		
		String phone = "13539770000";
        //检查phone是否是合格的手机号(标准:1开头，第二位为3,5,8，后9位为任意数字)
        System.out.println(phone + ":" + phone.matches("1[358][0-9]{9,9}")); //true    
		return;

	}
	private String analysisTest(){
		Analysis analysis = new Analysis();
		FormulaAnalysis formulaAnalysis = new FormulaAnalysis();
		SQLAnalysis sqlAnalysis = new SQLAnalysis(formulaAnalysis);
		analysis.setSqlAnalysis(sqlAnalysis);
		String formulaContent = "[基本工资]+[交通补贴]+[高温补贴]";
		formulaContent = "【如果】 [性别] = '女' 【那么】 100 【否则】 1 【结束】 ";
		formulaContent = "[基本工资]+[交通补贴]+[高温补贴]+{女职工卫生费标准}";
		formulaContent = "[基本工资]+[交通补贴]+[高温补贴]+{独生子女费标准}";
		formulaContent = "[基本工资]+[交通补贴]+[高温补贴]+{独生子女费标准}+{女职工卫生费标准}";
		formulaContent = "[基本工资]+[交通补贴]+[高温补贴]+{独生子女费标准}+{女职工卫生费标准}+{独生子女费标准}";
		formulaContent = "【如果】 [性别] = '女' 【那么】  [基本工资]+[交通补贴] 【否则】 [高温补贴]+{独生子女费标准}+{女职工卫生费标准}+{独生子女费标准} 【结束】";
		/*{独生子女费标准}
		SELECT MONEYS FROM TMP2,TMP WHERE TYPEID = '独生子女费标准' AND TMP.a = TMP2.a
		{女职工卫生费标准}
		SELECT MONEYS FROM TMP2 WHERE TYPEID = '女职工卫生费标准'"*/		
		String tableName = "tmp";
		String result = "";
		Map colMap = new HashMap();
		colMap.put("编号", "a");
		colMap.put("姓名", "b");
		colMap.put("性别", "c");
		colMap.put("基本工资", "d");
		colMap.put("交通补贴", "e");
		colMap.put("高温补贴", "f");
		try {
			result = analysis.getAnalysis(formulaContent, tableName, colMap);
		} catch (FormulaException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(result);
		return result;

	}
}
