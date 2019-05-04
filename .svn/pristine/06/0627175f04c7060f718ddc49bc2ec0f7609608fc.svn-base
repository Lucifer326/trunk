package org.jeecgframework.tag.comdesign.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.entity.TreeEntity;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * 控件接口类
 *
 */
public interface ControlGeneratorService{
	
	
	
	public TSAttachment getTSAttachment(String id);
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public <T> Serializable save(T entity);
	
	public CgformControlDesignEntity getControlDesign(String id);

	public PageList queryControlDesignList(int pageNo, int pageSize,String typeId);
	public int delete(String id);
	/**
	 * 测试弹窗树和下拉树数据源的接口方法
	 * @param parentid
	 * @param ids
	 * @return
	 */
	public List<TreeEntity> getDepartInfo(String parentid,String ids);
	
	public List<TreeEntity> getTSTypegroups(String parentid,String ids);

	public List<Map<String, Object>> getListByTable(String dictTable, String dictField, String dictText, String condition,
			String dynamicCondition);
	/**
	 * 保存附件
	 */
	public String uploadFile(String ctxPath,String bizPath,MultipartFile mf,String memoryType);
	
	
	/**
	 * 附件删除
	 */
	public boolean delFile(File fileDelete);
	/**
	 * 删除数据库文件
	 * @param id 数据库保存的文件 对应的id
	 * @return
	 */
	public int delDbFileByid(String id);
	public PageList getdatalistgrid(String tableName,DataGrid dataGrid,JSONArray jsonArray);
	public List<TreeEntity> getfunctionInfo(String parentid,String ids);
}
