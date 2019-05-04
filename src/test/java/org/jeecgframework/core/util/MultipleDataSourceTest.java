package org.jeecgframework.core.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.AbstractUnitTest;
import org.junit.Test;

public class MultipleDataSourceTest extends AbstractUnitTest {
	
	@Test
	public void multiDataSourceTestLocalServer() {		
		
		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_AA.add("update aaa set age='100' where id='20'");
		listSQL_AA.add("delete aaa where id='20'");
		
		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("update bbb set age='100' where id='20'");
		listSQL_BB.add("delete bbb where id='20'");

		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa", listSQL_AA);
		map.put("oracle_bbb", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void multiDataSourceTestRemoteServer() {		
		
		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_AA.add("update aaa set age='100' where id='20'");
		listSQL_AA.add("delete aaa where id='20'");

		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("update bbb set age='100' where id='20'");
		listSQL_BB.add("delete bbb where id='20'");

		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa_server", listSQL_AA);
		map.put("oracle_bbb_server", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void multiDataSourceTestLocalAndRemoteServer() {		
		
		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_AA.add("update aaa set age='100' where id='20'");
		listSQL_AA.add("delete aaa where id='20'");

		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("update bbb set age='100' where id='20'");
		listSQL_BB.add("delete bbb where id='20'");

		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa", listSQL_AA);
		map.put("oracle_bbb_server", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void multiDataSourceTestLocalServer_exception() {		
		
		
		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa", listSQL_AA);
		map.put("oracle_bbb", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_CC = new ArrayList<String>();
		List<String> listSQL_DD = new ArrayList<String>();
				
		listSQL_CC.add("update aaa set age='200' where id='20'");
		listSQL_CC.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_CC.add("delete aaa where id='20'");

		listSQL_DD.add("delete bbb where id='20'");
		listSQL_DD.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_DD.add("update bbb set age='200' where id='20'");


		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put("oracle_aaa", listSQL_CC);
		map1.put("oracle_bbb", listSQL_DD);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map1);
		    assertEquals(0,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_EE = new ArrayList<String>();
		List<String> listSQL_FF = new ArrayList<String>();
		
		listSQL_EE.add("delete aaa where id='20'");
		listSQL_FF.add("delete bbb where id='20'");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map2 = new HashMap<String, List<String>>();
		map2.put("oracle_aaa", listSQL_EE);
		map2.put("oracle_bbb", listSQL_FF);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map2);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void multiDataSourceTestRemoteServer_exception() {		
		

		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa_server", listSQL_AA);
		map.put("oracle_bbb_server", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_CC = new ArrayList<String>();
		List<String> listSQL_DD = new ArrayList<String>();
				
		listSQL_CC.add("update aaa set age='200' where id='20'");
		listSQL_CC.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_CC.add("delete aaa where id='20'");

		listSQL_DD.add("delete bbb where id='20'");
		listSQL_DD.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_DD.add("update bbb set age='200' where id='20'");


		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put("oracle_aaa_server", listSQL_CC);
		map1.put("oracle_bbb_server", listSQL_DD);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map1);
		    assertEquals(0,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_EE = new ArrayList<String>();
		List<String> listSQL_FF = new ArrayList<String>();
		
		listSQL_EE.add("delete aaa where id='20'");
		listSQL_FF.add("delete bbb where id='20'");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map2 = new HashMap<String, List<String>>();
		map2.put("oracle_aaa_server", listSQL_EE);
		map2.put("oracle_bbb_server", listSQL_FF);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map2);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void multiDataSourceTestLocalAndRemoteServer_exception() {		
		
		/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("delete aaa where id='20'");
		listSQL_AA.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_BB.add("delete bbb where id='20'");
		listSQL_BB.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa", listSQL_AA);
		map.put("oracle_bbb_server", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_CC = new ArrayList<String>();
		List<String> listSQL_DD = new ArrayList<String>();
				
		listSQL_CC.add("update aaa set age='200' where id='20'");
		listSQL_CC.add("insert into aaa (id,name,age) values ('20','XXX',100)");
		listSQL_CC.add("delete aaa where id='20'");

		listSQL_DD.add("delete bbb where id='20'");
		listSQL_DD.add("insert into bbb (id,name,age) values ('20','XXX',100)");
		listSQL_DD.add("update bbb set age='200' where id='20'");


		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put("oracle_aaa", listSQL_CC);
		map1.put("oracle_bbb_server", listSQL_DD);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map1);
		    assertEquals(0,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		List<String> listSQL_EE = new ArrayList<String>();
		List<String> listSQL_FF = new ArrayList<String>();
		
		listSQL_EE.add("delete aaa where id='20'");
		listSQL_FF.add("delete bbb where id='20'");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map2 = new HashMap<String, List<String>>();
		map2.put("oracle_aaa", listSQL_EE);
		map2.put("oracle_bbb_server", listSQL_FF);
		
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map2);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
    

  	public void multiDataSourceTestLocalAndRemoteServer_lock() {	  	
    	/** 定义两个list,分别存两个数据源的sql **/
		List<String> listSQL_AA = new ArrayList<String>();
		List<String> listSQL_BB = new ArrayList<String>();
		
		listSQL_AA.add("insert into aaa (id,name,age) values ('40','XXX',100)");
		listSQL_BB.add("insert into bbb (id,name,age) values ('40','XXX',100)");
		
		/** 定义Map,将两个数据源和List加入Map中 **/
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("oracle_aaa", listSQL_AA);
		map.put("oracle_bbb_server", listSQL_BB);
		/**
		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
		 **/
		try
		{
		    int result = DynamicSwDBUtil.dynamicDataDetail(map);
		    assertEquals(1,result);
		/** 返回值：数值类型，1代表成功，0代表失败。 **/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
  		List<String> listSQL_CC = new ArrayList<String>();
  		List<String> listSQL_DD = new ArrayList<String>();
  				
  		listSQL_CC.add("insert into aaa (id,name,age) values ('30','XXX',100)");
  		listSQL_CC.add("delete aaa where id='20'");
  		listSQL_DD.add("insert into bbb (id,name,age) values ('30','XXX',100)");
  		listSQL_DD.add("delete bbb where id='20'");

  		/** 定义Map,将两个数据源和List加入Map中 **/
  		Map<String, List<String>> map1 = new HashMap<String, List<String>>();
  		map1.put("oracle_aaa", listSQL_CC);
  		map1.put("oracle_bbb_server", listSQL_DD);
  		
  		/**
  		 * 调用DynamicSwDBUtil中 dynamicDataDetail(Map<String,List<String>> map)方法
  		 **/
  		try
  		{
  		    int result = DynamicSwDBUtil.dynamicDataDetail(map1);
  		  assertEquals(0,result);
  		/** 返回值：数值类型，1代表成功，0代表失败。 **/
  		}
  		catch(Exception ex)
  		{
  			ex.printStackTrace();
  		}
  	}

}
