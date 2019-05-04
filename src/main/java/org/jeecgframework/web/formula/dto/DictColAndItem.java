package org.jeecgframework.web.formula.dto;

import java.io.Serializable;

public class DictColAndItem implements Serializable {

	private static final long serialVersionUID = -9159725751909823193L;

	private String fieldName;
	private String fieldCName;
	private String dictField;
	private String typeCode;
	private String typeName;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldCName() {
		return fieldCName;
	}
	public void setFieldCName(String fieldCName) {
		this.fieldCName = fieldCName;
	}
	public String getDictField() {
		return dictField;
	}
	public void setDictField(String dictField) {
		this.dictField = dictField;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
