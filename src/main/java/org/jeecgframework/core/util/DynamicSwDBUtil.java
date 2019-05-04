package org.jeecgframework.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.springframework.dao.DataAccessException;

/**
 * Spring JDBC 实时数据库访问   多数据源管理
 * @author weiya
 * @date 2018-01-10
 * @version 1.0
 */
public class DynamicSwDBUtil{
	private static final Logger logger = Logger.getLogger(DynamicDBUtil.class);
	 
	
	/**
	 * 处理多数据源事务
	 * @param map key数据源名称  value 对应的sql
	 * @return
	 */
	public static int dynamicDataDetail(Map<String,List<String>> map){   
		int totalMap=map.size();
		String[] dataSource = new String[totalMap]; 
		/**定义多数据源连接，处理数据源连接和SQL对应关系 **/
		Connection []con=new Connection[totalMap];//数据源连接
		ArrayList<String> sqlStr = new ArrayList<String>();//sql
		ArrayList<Connection> concs = new ArrayList<Connection>();//sql对应的连接 
		/**取数据源和sql**/
		int i=0;
		Iterator it = map.entrySet().iterator();  
		while (it.hasNext()) { 
		   Map.Entry entry = (Map.Entry) it.next();  
		   Object key = entry.getKey();  
		   Object value = entry.getValue();
		   /**取数据源连接**/
		   dataSource[i] = key.toString(); 
		   DynamicDataSourceEntity dynamicSourceEntity = new DynamicDataSourceEntity();
		   CommonDao commonDao=ApplicationContextUtil.getContext().getBean(CommonDao.class);
		   dynamicSourceEntity=(DynamicDataSourceEntity) commonDao.findUniqueByProperty(DynamicDataSourceEntity.class, "dbKey", dataSource[i]);
		   try {
				con[i] = DriverManager.getConnection(dynamicSourceEntity.getUrl(), dynamicSourceEntity.getDbUser(), dynamicSourceEntity.getDbPassword());
		   } catch (SQLException e) {
				e.printStackTrace();
		   }
		   /**数据源和sql对应**/
		   List<String> listsql = (List<String>) value;
		   for(int j=0;j<listsql.size();j++){
			   concs.add(con[i]);
			   sqlStr.add(listsql.get(j));
		   }
		   i++;
		} 
		/**持久化数据库**/
		JdbcDynamicTemplate jdbcDynamicTemplatek = new JdbcDynamicTemplate(); 
		int effectCount =0;
		try {
			effectCount = jdbcDynamicTemplatek.executeDynamic(concs,sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return effectCount;
	} 
}
