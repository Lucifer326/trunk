package org.jeecgframework.core.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * Spring JDBC 实时数据库访问
 * 
 * @author chenguobin
 * @date 2014-09-05
 * @version 1.0
 */
public class DynamicDBUtil {
	private static final Logger logger = Logger.getLogger(DynamicDBUtil.class);

	private static BasicDataSource getDataSource(final DynamicDataSourceEntity dynamicSourceEntity) {
		BasicDataSource dataSource = new BasicDataSource();

		String driverClassName = dynamicSourceEntity.getDriverClass();
		String url = dynamicSourceEntity.getUrl();
		String dbUser = dynamicSourceEntity.getDbUser();
		String dbPassword = dynamicSourceEntity.getDbPassword();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	private static JdbcTemplate getJdbcTemplate(String dbKey) {
		DynamicDataSourceEntity dynamicSourceEntity = DynamicDataSourceEntity.DynamicDataSourceMap.get(dbKey);

		BasicDataSource dataSource = getDataSource(dynamicSourceEntity);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	/**
	 * getNamedParameterJdbcTemplate
	 * 
	 * @param dbKey
	 * @return NamedParameterJdbcTemplate
	 * @author shijunbao
	 */
	private static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String dbKey) {
		DynamicDataSourceEntity dynamicSourceEntity = DynamicDataSourceEntity.DynamicDataSourceMap.get(dbKey);
		BasicDataSource dataSource = getDataSource(dynamicSourceEntity);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		return namedParameterJdbcTemplate;
	}

	/**
	 * 该方法只是方便用于main方法测试调用
	 * 
	 * @param dynamicSourceEntity
	 * @return JdbcTemplate
	 */
	private static JdbcTemplate getJdbcTemplate(DynamicDataSourceEntity dynamicSourceEntity) {
		BasicDataSource dataSource = getDataSource(dynamicSourceEntity);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	/**
	 * Executes the SQL statement in this <code>PreparedStatement</code> object,
	 * which must be an SQL Data Manipulation Language (DML) statement, such as
	 * <code>INSERT</code>, <code>UPDATE</code> or <code>DELETE</code>; or an
	 * SQL statement that returns nothing, such as a DDL statement.
	 */
	public static int update(final String dbKey, String sql, Object... param) {
		int effectCount = 0;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			effectCount = jdbcTemplate.update(sql);
		} else {
			effectCount = jdbcTemplate.update(sql, param);
		}

		return effectCount;
	}

	/**
	 * updateBySPS
	 * 
	 * @param dbKey
	 * @param sql
	 * @param paramSource
	 * @return
	 * @author shijunbao
	 */
	public static int updateBySPS(final String dbKey, String sql, SqlParameterSource paramSource) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(dbKey);
		int effectCount = namedParameterJdbcTemplate.update(sql, paramSource);
		return effectCount;
	}

	/**
	 * 批量更新
	 * 
	 * @param dbKey
	 * @param sql
	 * @param pss
	 * @return
	 */
	public static int[] updateBatch(final String dbKey, String sql, BatchPreparedStatementSetter pss) {
		int effectCount[] = { 0 };
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		effectCount = jdbcTemplate.batchUpdate(sql, pss);

		return effectCount;
	}

	/**
	 * 批量更新
	 * 
	 * @param dbKey
	 * @param sql
	 * @param batchArgs
	 * @return
	 * @author shijunbao
	 */
	public static int[] updateBatch(final String dbKey, String sql, SqlParameterSource[] batchArgs) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(dbKey);
		return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
	}

	public static Object findOne(final String dbKey, String sql, Object... param) {
		List<Map<String, Object>> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = jdbcTemplate.queryForList(sql);
		} else {
			list = jdbcTemplate.queryForList(sql, param);
		}

		if (ListUtils.isNullOrEmpty(list)) {
			logger.error("Except one, but not find actually");
		}

		if (list.size() > 1) {
			logger.error("Except one, but more than one actually");
		}

		return list.get(0);
	}

	public static List<Map<String, Object>> findList(final String dbKey, String sql, Object... param) {
		List<Map<String, Object>> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = jdbcTemplate.queryForList(sql);
		} else {
			list = jdbcTemplate.queryForList(sql, param);
		}
		return list;
	}

	// add-begin--Author:luobaoli Date:20150620 for：增加返回值为List的方法
	public static <T> List<T> findList(final String dbKey, String sql, Class<T> clazz, Object... param) {
		List<T> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = jdbcTemplate.queryForList(sql, clazz);
		} else {
			list = jdbcTemplate.queryForList(sql, clazz, param);
		}
		return list;
	}

	// add-end--Author:luobaoli Date:20150620 for：增加返回值为List的方法
	// Date:20170518 for：增加返回值为List对象的方法
	public static <T> List<T> findListForClass(final String dbKey, String sql, Class<T> clazz, Object... param) {
		List<T> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
		} else {
			list = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), param);
		}
		return list;
	}

	/**
	 * 增加返回值为List对象的方法
	 * 
	 * @param dbKey
	 * @param sql
	 * @param clazz
	 * @param paramSource
	 * @return
	 * @author shijunbao
	 */
	public static <T> List<T> findListForClassSPS(final String dbKey, String sql, Class<T> clazz, SqlParameterSource paramSource) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(dbKey);
		List<T> list = (List<T>) namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper<T>(clazz));
		return list;
	}

	public static void main(String[] args) {
		DynamicDataSourceEntity dynamicSourceEntity = new DynamicDataSourceEntity();

		String dbKey = "SAP_DB";
		String driverClassName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@10.10.0.59:1521:mid";
		String dbUser = "CRM";
		String dbPassword = "CRM2013";

		dynamicSourceEntity.setDbKey(dbKey);
		dynamicSourceEntity.setDriverClass(driverClassName);
		dynamicSourceEntity.setUrl(url);
		dynamicSourceEntity.setDbUser(dbUser);
		dynamicSourceEntity.setDbPassword(dbPassword);
		
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dynamicSourceEntity);
		

		String sql = "select ak.VKBUR, ak.KUNNR, ak.BSTNK, ak.VBELN, ak.MAHDT, ak.BSTDK from VBAK ak where ak.VKORG = '6002'";
		// List<Map<String, Object>> list = DynamicDBUtil.getList(jdbcTemplate,
		// sql);
		// System.out.println(list.size());
	}

}
