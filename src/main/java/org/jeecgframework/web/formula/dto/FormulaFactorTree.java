package org.jeecgframework.web.formula.dto;

import java.io.Serializable;

public class FormulaFactorTree implements Serializable {

	private static final long serialVersionUID = -9159725751909823192L;

	private String tableName;
	private String columnName;
	private String formulaName;
	private String tableNamec;
	private String columnNamec;
	private String dictfield;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public String getTableNamec() {
		return tableNamec;
	}
	public void setTableNamec(String tableNamec) {
		this.tableNamec = tableNamec;
	}
	public String getColumnNamec() {
		return columnNamec;
	}
	public void setColumnNamec(String columnNamec) {
		this.columnNamec = columnNamec;
	}
	public String getDictfield() {
		return dictfield;
	}
	public void setDictfield(String dictfield) {
		this.dictfield = dictfield;
	}
	
}
