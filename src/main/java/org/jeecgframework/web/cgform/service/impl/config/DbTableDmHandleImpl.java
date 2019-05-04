package org.jeecgframework.web.cgform.service.impl.config;

import org.apache.commons.lang.StringUtils;

import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;


/**
 * dm的表工具类
 * dm语句结尾不能使用 ;
 */
public class DbTableDmHandleImpl implements DbTableHandleI {

	
	public String getAddColumnSql(ColumnMeta columnMeta) {
		return " ADD  "+getAddFieldDesc(columnMeta)+"";
	}

	
	public String getReNameFieldName(ColumnMeta columnMeta) {
		return "RENAME COLUMN  "+columnMeta.getOldColumnName() +" TO "+columnMeta.getColumnName()+"";
	}

	
	public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta) {
		return " MODIFY  "+getUpdateFieldDesc(cgformcolumnMeta,datacolumnMeta)+"";
	}

	/**
	 * 根据数据类型转换类类型
	 */
	public String getMatchClassTypeByDataType(String dataType,int digits) {
		String result ="";
		if (dataType.equalsIgnoreCase("varchar")) {
			result="string";
		} else if(dataType.equalsIgnoreCase("varchar2")){
			result="string";
		}else if(dataType.equalsIgnoreCase("double")){
			result="double";
		}else if (dataType.equalsIgnoreCase("number") && digits==0) {
			result="int";
		}else if (dataType.equalsIgnoreCase("number") && digits!=0) {
			result="double";
		}else if (dataType.equalsIgnoreCase("int")) {
			result="integer";
		}else if (dataType.equalsIgnoreCase("text")) {
			result="text";
		}else if (dataType.equalsIgnoreCase("blob")) {
			result="blob";
		}else if ((dataType.equalsIgnoreCase("Date"))|| dataType.equalsIgnoreCase("Datetime")){
			result="date";
		}
		return result;
	}

	/**
	 * 删除表
	 */
	public String dropTableSQL(String tableName) {
		return " DROP TABLE  "+tableName.toLowerCase()+" ";
	}

	/**
	 * 删除字段
	 */
	public String getDropColumnSql(String fieldName) {
		return " DROP COLUMN "+fieldName.toUpperCase()+"";
	}
	
	/**
	 * 添加字段
	 * @param columnMeta
	 * @return
	 */
	private String getAddFieldDesc(ColumnMeta columnMeta) {
		String result ="";
		if(columnMeta.getColunmType().equalsIgnoreCase("string")){
			result = columnMeta.getColumnName()+" VARCHAR("+columnMeta.getColumnSize()+")";
		}else if(columnMeta.getColunmType().equalsIgnoreCase("date")){
			result = columnMeta.getColumnName()+" DATE";
		}else if(columnMeta.getColunmType().equalsIgnoreCase("int")){
			//NUMBER("+columnMeta.getColumnSize()+")
			result = columnMeta.getColumnName()+" INTEGER ";
		}else if(columnMeta.getColunmType().equalsIgnoreCase("double")){
			result = columnMeta.getColumnName()+" DOUBLE("+columnMeta.getColumnSize()+")";
		}else if(columnMeta.getColunmType().equalsIgnoreCase("bigdecimal")){
			result = columnMeta.getColumnName()+" DECIMAL("+columnMeta.getColumnSize()+")";
		}else if(columnMeta.getColunmType().equalsIgnoreCase("text")){ 
			result = columnMeta.getColumnName()+" TEXT ";
			
		}else if(columnMeta.getColunmType().equalsIgnoreCase("blob")){
			result = columnMeta.getColumnName()+" BLOB("+columnMeta.getColumnSize()+")";
		}
		result += (StringUtils.isNotEmpty(columnMeta.getFieldDefault())?" DEFAULT "+columnMeta.getFieldDefault():" ");
		result += (columnMeta.getIsNullable().equals("Y")?" NULL":" NOT NULL");
		return result;
	}
	/**
	 * 更新字段
	 * @param cgformcolumnMeta
	 * @param datacolumnMeta
	 * @return
	 */
	private String getUpdateFieldDesc(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta) {
		String result ="";
		String isnull="";
		//oracle对于是否为空必须跟原来的比对
		if (!datacolumnMeta.getIsNullable().equals(cgformcolumnMeta.getIsNullable())) {
			isnull=(cgformcolumnMeta.getIsNullable().equals("Y")?"NULL":"NOT NULL");
		}
		if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("string")){
				result = cgformcolumnMeta.getColumnName()+" VARCHAR("+cgformcolumnMeta.getColumnSize()+")"+isnull;
			
		}
		else if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("text")){
			result = cgformcolumnMeta.getColumnName()+" VARCHAR2("+cgformcolumnMeta.getColumnSize()+")"+isnull;
			
		}
		else if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("date")){
				result = cgformcolumnMeta.getColumnName()+" DATE "+isnull;
			
		}else if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("int")){
				result = cgformcolumnMeta.getColumnName()+" INTEGER "+isnull;
			
		}else if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("double")){
				result = cgformcolumnMeta.getColumnName()+" DOUBLE("+cgformcolumnMeta.getColumnSize()+") "+isnull;
				//" DECIMAL("+cgformcolumnMeta.getColumnSize()+")"+isnull;
		}else if(cgformcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")){
			
			result = cgformcolumnMeta.getColumnName()+ " DECIMAL("+cgformcolumnMeta.getColumnSize()+","+cgformcolumnMeta.getDecimalDigits()+")"+isnull;
		}
		result += (StringUtils.isNotEmpty(cgformcolumnMeta.getFieldDefault())?" DEFAULT "+cgformcolumnMeta.getFieldDefault():" ");
		result += isnull;
		return result;
	}

	/**
	 * 添加注释
	 */
	public String getCommentSql(ColumnMeta columnMeta) {
		return "COMMENT ON COLUMN "+columnMeta.getTableName()+"."+columnMeta.getColumnName()+" IS '" +columnMeta.getComment()+"'";
	}

	/**
	 * 处理特殊SQL
	 */
	public String getSpecialHandle(ColumnMeta cgformcolumnMeta,
			ColumnMeta datacolumnMeta) {
		return null;
	}

}
