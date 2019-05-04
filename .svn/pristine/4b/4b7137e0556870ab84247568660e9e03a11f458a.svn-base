package org.jeecgframework.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 字典与行政关联表
 * @author onlineGenerator
 * @date 2017-08-01 20:49:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_type_unless_region", schema = "")
@SuppressWarnings("serial")
public class TSTypeUnlessRegionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**数据字典的外键*/
	@Excel(name="数据字典的外键")
	private java.lang.String dictionaryId;
	/**字典类型的键值*/
	@Excel(name="字典类型的键值")
	private java.lang.String typeId;
	/**行政区划的键值*/
	@Excel(name="行政区划的键值")
	private java.lang.String regionId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",insertable=true,nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据字典的外键
	 */
	@Column(name ="DICTIONARY_ID",insertable=true,nullable=true,length=32)
	public java.lang.String getDictionaryId(){
		return this.dictionaryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据字典的外键
	 */
	public void setDictionaryId(java.lang.String dictionaryId){
		this.dictionaryId = dictionaryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  字典类型的键值
	 */
	@Column(name ="TYPE_ID",insertable=true,nullable=true,length=32)
	public java.lang.String getTypeId(){
		return this.typeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  字典类型的键值
	 */
	public void setTypeId(java.lang.String typeId){
		this.typeId = typeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政区划的键值
	 */
	@Column(name ="REGION_ID",insertable=true,nullable=true,length=32)
	public java.lang.String getRegionId(){
		return this.regionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政区划的键值
	 */
	public void setRegionId(java.lang.String regionId){
		this.regionId = regionId;
	}
}
