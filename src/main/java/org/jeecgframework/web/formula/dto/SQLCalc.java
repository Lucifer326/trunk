package org.jeecgframework.web.formula.dto;

import java.io.Serializable;

public class SQLCalc implements Serializable {

	private static final long serialVersionUID = -9159725751909823183L;

	private String Name;
	private String ID;
	private String FromItem;
	private String ContentOri;
	private String SQLExpress;
	private String TypeID;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFromItem() {
		return FromItem;
	}
	public void setFromItem(String fromItem) {
		FromItem = fromItem;
	}
	public String getContentOri() {
		return ContentOri;
	}
	public void setContentOri(String contentOri) {
		ContentOri = contentOri;
	}
	public String getSQLExpress() {
		return SQLExpress;
	}
	public void setSQLExpress(String sQLExpress) {
		SQLExpress = sQLExpress;
	}
	public String getTypeID() {
		return TypeID;
	}
	public void setTypeID(String typeID) {
		TypeID = typeID;
	}
	
}
