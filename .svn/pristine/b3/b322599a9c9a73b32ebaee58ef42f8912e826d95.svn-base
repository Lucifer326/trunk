package org.jeecgframework.core.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;

public  class JdbcDynamicTemplate  extends JdbcAccessor{
	//static PropertiesUtil util = new PropertiesUtil("dbConfig.properties");
	private static int muldstimeout; 
	
	static{
		 PropertiesUtil util = new PropertiesUtil("dbconfig.properties");
		 muldstimeout =  Integer.parseInt(util.readProperty("muldstimeout")); 
	}
	
	/** Custom NativeJdbcExtractor */
	private NativeJdbcExtractor nativeJdbcExtractor;
	/**
	 * Set a NativeJdbcExtractor to extract native JDBC objects from wrapped handles.
	 * Useful if native Statement and/or ResultSet handles are expected for casting
	 * to database-specific implementation classes, but a connection pool that wraps
	 * JDBC objects is used (note: <i>any</i> pool will return wrapped Connections).
	 */
	public void setNativeJdbcExtractor(NativeJdbcExtractor extractor) {
		this.nativeJdbcExtractor = extractor;
	}
	/**
	 * Return the current NativeJdbcExtractor implementation.
	 */
	public NativeJdbcExtractor getNativeJdbcExtractor() {
		return this.nativeJdbcExtractor;
	}

	/**
	 * If this variable is set to a non-zero value, it will be used for setting the
	 * fetchSize property on statements used for query processing.
	 */
	private int fetchSize = 0;
	/**
	 * Set the fetch size for this JdbcTemplate. This is important for processing
	 * large result sets: Setting this higher than the default value will increase
	 * processing speed at the cost of memory consumption; setting this lower can
	 * avoid transferring row data that will never be read by the application.
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 * @see java.sql.Statement#setFetchSize
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * Return the fetch size specified for this JdbcTemplate.
	 */
	public int getFetchSize() {
		return this.fetchSize;
	}

	/**
	 * If this variable is set to a non-zero value, it will be used for setting the
	 * maxRows property on statements used for query processing.
	 */
	private int maxRows = 0;
	/**
	 * Set the maximum number of rows for this JdbcTemplate. This is important
	 * for processing subsets of large result sets, avoiding to read and hold
	 * the entire result set in the database or in the JDBC driver if we're
	 * never interested in the entire result in the first place (for example,
	 * when performing searches that might return a large number of matches).
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 * @see java.sql.Statement#setMaxRows
	 */
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	/**
	 * Return the maximum number of rows specified for this JdbcTemplate.
	 */
	public int getMaxRows() {
		return this.maxRows;
	}


	/**
	 * If this variable is set to a non-zero value, it will be used for setting the
	 * queryTimeout property on statements used for query processing.
	 */
	private int queryTimeout = 0;
	/**
	 * Set the query timeout for statements that this JdbcTemplate executes.
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 * <p>Note: Any timeout specified here will be overridden by the remaining
	 * transaction timeout when executing within a transaction that has a
	 * timeout specified at the transaction level.
	 * @see java.sql.Statement#setQueryTimeout
	 */
	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	/**
	 * Return the query timeout for statements that this JdbcTemplate executes.
	 */
	public int getQueryTimeout() {
		return this.queryTimeout;
	}

	/** If this variable is false, we will throw exceptions on SQL warnings */
	private boolean ignoreWarnings = true;
	/**
	 * Set whether or not we want to ignore SQLWarnings.
	 * <p>Default is "true", swallowing and logging all warnings. Switch this flag
	 * to "false" to make the JdbcTemplate throw a SQLWarningException instead.
	 * @see java.sql.SQLWarning
	 * @see org.springframework.jdbc.SQLWarningException
	 * @see #handleWarnings
	 */
	public void setIgnoreWarnings(boolean ignoreWarnings) {
		this.ignoreWarnings = ignoreWarnings;
	}

	/**
	 * Return whether or not we ignore SQLWarnings.
	 */
	public boolean isIgnoreWarnings() {
		return this.ignoreWarnings;
	}

	/**
	 * Construct a new JdbcTemplate for bean usage.
	 * <p>Note: The DataSource has to be set before using the instance.
	 * @see #setDataSource
	 */
	public JdbcDynamicTemplate() {
	}

	/**
	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
	 * <p>Note: This will not trigger initialization of the exception translator.
	 * @param dataSource the JDBC DataSource to obtain connections from
	 */
	public JdbcDynamicTemplate(DataSource dataSource) {
		setDataSource(dataSource);
		afterPropertiesSet();
	}
	/**
	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
	 * <p>Note: Depending on the "lazyInit" flag, initialization of the exception translator
	 * will be triggered.
	 * @param dataSource the JDBC DataSource to obtain connections from
	 * @param lazyInit whether to lazily initialize the SQLExceptionTranslator
	 */
	public JdbcDynamicTemplate(DataSource dataSource, boolean lazyInit) {
		setDataSource(dataSource);
		setLazyInit(lazyInit);
		afterPropertiesSet();
	}
    /**
     * 处理多数据源事务
     * @param con
     * @param sqlstr
     * @return
     * @throws DataAccessException
     */
	public int executeDynamic(List<Connection> con,List sqlstr) throws DataAccessException, TimeoutException{
		boolean r=true;
		Statement stmt = null;
		int returnResult=1;
		Connection conToUse = null;
		
		try {
			for(int i=0;i<con.size();i++){
				conToUse = con.get(i);
				if (this.nativeJdbcExtractor != null &&
						this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeStatements()) {
					conToUse = this.nativeJdbcExtractor.getNativeConnection(con.get(i));
				}
				stmt = conToUse.createStatement();
				//applyStatementSettings(stmt);
				Statement stmtToUse = stmt;
				if (this.nativeJdbcExtractor != null) {
					stmtToUse = this.nativeJdbcExtractor.getNativeStatement(stmt);
				}
				boolean b = conToUse.getAutoCommit();
				if (b)
					conToUse.setAutoCommit(false);
		        try{
		        	 String sql=sqlstr.get(i).toString();
		        	 /**取锁表后才允许等待的最长时间时间**/
		        	 stmtToUse.setQueryTimeout(muldstimeout);
				     stmtToUse.executeUpdate(sql);
				}catch(Exception e){
					returnResult=0;
					r=false;
					e.printStackTrace(); 
					for (int k=0;k<con.size();k++){
						conToUse = con.get(k);
						conToUse.rollback();
					}
				}finally {
					stmtToUse.close();
					handleWarnings(stmt);
				}
			}
			if (r){
				for (int k=0;k<con.size();k++){
					conToUse = con.get(k);
					conToUse.commit();
				}
				returnResult=1;
			} else{
				for (int k=0;k<con.size();k++){
					conToUse = con.get(k);
					conToUse.rollback();
				}
				returnResult=0;
			}
			return returnResult;
		}catch (SQLException ex) {
			JdbcUtils.closeStatement(stmt);
			throw getExceptionTranslator().translate("StatementCallback", "", ex);
		}finally {
			JdbcUtils.closeStatement(stmt);
			try {
				conToUse.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (int k=0;k<con.size();k++){
				try {
					if (!con.get(k).isClosed())
						con.get(k).close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}			
		}
	}
	
	 

	/**
	 * Prepare the given JDBC Statement (or PreparedStatement or CallableStatement),
	 * applying statement settings such as fetch size, max rows, and query timeout.
	 * @param stmt the JDBC Statement to prepare
	 * @throws SQLException if thrown by JDBC API
	 * @see #setFetchSize
	 * @see #setMaxRows
	 * @see #setQueryTimeout
	 * @see org.springframework.jdbc.datasource.DataSourceUtils#applyTransactionTimeout
	 */
	protected void applyStatementSettings(Statement stmt) throws SQLException {
		int fetchSize = getFetchSize();
		if (fetchSize > 0) {
			stmt.setFetchSize(fetchSize);
		}
		int maxRows = getMaxRows();
		if (maxRows > 0) {
			stmt.setMaxRows(maxRows);
		}
		DataSourceUtils.applyTimeout(stmt, getDataSource(), getQueryTimeout());
	}

	/**
	 * Throw an SQLWarningException if we're not ignoring warnings,
	 * else log the warnings (at debug level).
	 * @param stmt the current JDBC statement
	 * @throws SQLWarningException if not ignoring warnings
	 * @see org.springframework.jdbc.SQLWarningException
	 */
	protected void handleWarnings(Statement stmt) throws SQLException {
		if (isIgnoreWarnings()) {
			if (logger.isDebugEnabled()) {
				SQLWarning warningToLog = stmt.getWarnings();
				while (warningToLog != null) {
					logger.debug("SQLWarning ignored: SQL state '" + warningToLog.getSQLState() + "', error code '" +
							warningToLog.getErrorCode() + "', message [" + warningToLog.getMessage() + "]");
					warningToLog = warningToLog.getNextWarning();
				}
			}
		}
		else {
			handleWarnings(stmt.getWarnings());
		}
	}
	 

 

	 

	 

	 
 
 

	 
	/**
	 * Throw an SQLWarningException if encountering an actual warning.
	 * @param warning the warnings object from the current statement.
	 * May be {@code null}, in which case this method does nothing.
	 * @throws SQLWarningException in case of an actual warning to be raised
	 */
	protected void handleWarnings(SQLWarning warning) throws SQLWarningException {
		if (warning != null) {
			throw new SQLWarningException("Warning not ignored", warning);
		}
	}
	/**
	 * Determine SQL from potential provider object.
	 * @param sqlProvider object that's potentially a SqlProvider
	 * @return the SQL string, or {@code null}
	 * @see SqlProvider
	 */
	private static String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		}
		else {
			return null;
		}
	}
	 
}
