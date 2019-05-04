package org.jeecgframework.tag.comdesign.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.entity.TreeEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 控件类接口实现类
 * @author Administrator
 *
 */

@Service("controlGeneratorService")
@Transactional
public class ControlGeneratorServiceImpl implements ControlGeneratorService{

	@Autowired
	private CommonDao commonDao;

	public TSAttachment getTSAttachment(String id) {
		if (id == null || "".equals(id)) {
			return null;
		}
		return (TSAttachment) commonDao.getEntity(TSAttachment.class, id);
	}
	/**
	 * 新增
	 */
	public <T> Serializable save(T entity) {

		return commonDao.save(entity);
	}
	/**
	 * 根据id获取实体
	 */
	public CgformControlDesignEntity getControlDesign(String id) {
		
		if (id == null || "".equals(id)) {
			return null;
		}
		return (CgformControlDesignEntity) commonDao.getEntity(CgformControlDesignEntity.class, id);
	}
	/**
	 * 根据控件类型id获取控件集合
	 */
	public PageList queryControlDesignList(int pageNo, int pageSize,String typeId) {
		
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setCurPage(pageNo);
		hqlQuery.setPageSize(pageSize);
		
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Type> types = new ArrayList<Type>();
		
		String hql = "from CgformControlDesignEntity c where c.typeId = ? order by updateDate desc";
		
		params.add(typeId);
		hqlQuery.setQueryString(hql);
		types.add(StringType.INSTANCE);
		
		boolean needParameter = false;
		if(params.size() > 0) {
			hqlQuery.setParam(params.toArray());
			Type[] tt = new Type[types.size()];
			for(int i=0;i<types.size();i++) {
				tt[i] = types.get(i);
			}
			hqlQuery.setTypes(tt);
			needParameter = true;
		}
		PageList pageList = commonDao.getPageList(hqlQuery, needParameter);
		
		return pageList;
	}
	@Override
	public int delete(String id) {
		// 删除
		String hql = "delete From CgformControlDesignEntity c where c.id = ?";
		int count = commonDao.executeHql(hql, id);
		return count;
	}
	
	
	/**
	 * 树形弹窗数据源  以组织机构为例
	 * @param parentid 父id 
	 * @param ids  选中状态节点id
	 * @return TreeEntity实体集合
	 */
	public List<TreeEntity> getDepartInfo(String parentid,String ids){
		
		//声明一个部门集合
		List<TSDepart> tSDeparts = new ArrayList<TSDepart>();
		
		StringBuffer hql = new StringBuffer(" from TSDepart t ");
		
		if(StringUtils.isNotBlank(parentid)){//parentid不为空时 根据parentid查询部门数据
			
			hql.append(" where TSPDepart.id = ?");
			tSDeparts = this.commonDao.findHql(hql.toString(), parentid);
		
		} else {//parentid为空时 需判断用户是否管理员
			
			/*只展示当前登陆用户相关的组织机构树，管理员除外*/
			HttpSession session = ContextHolderUtils.getSession();
			TSUser currentUser = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			
			if(!currentUser.getStatus().equals(Globals.User_ADMIN)) {//非管理员 按照当前用户的部门id查询数据
				TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
				hql.append(" where id = ?");
				tSDeparts = this.commonDao.findHql(hql.toString(),dept.getId());
			
			}else {//管理员 从根节点加载全部数据
				hql.append(" where TSPDepart is null");
				tSDeparts = this.commonDao.findHql(hql.toString());
			}
		}
		//声明一个TreeEntity实体集合
		List<TreeEntity> dataList = new ArrayList<TreeEntity>();
		
		if(tSDeparts.size()>0){
			 
			String sql = null;
			Object[] params = null;			
			//遍历部门集合     给TreeEntity实体的id name isChecked pId isParent 属性 赋值
			 for(TSDepart t : tSDeparts){
				
				 TreeEntity tDb = new TreeEntity();
				
				 tDb.setId(t.getId());
				
				 tDb.setName(t.getDepartname());
				
				
				 if(t.getTSPDepart() != null ){
					
					tDb.setpId(t.getTSPDepart().getId());
				
				 }
				
				 if(StringUtil.isNotEmpty(ids)){
					
					if(ids.indexOf(t.getId())!=-1) {
						
						tDb.setChecked(true);
						
					}
				}
				//根据id判断是否有子节点
				sql = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				params = new Object[]{t.getId()};
				long count = this.commonDao.getCountForJdbcParam(sql, params);
				
				if(count>0){//有子节点
					
					tDb.setParent(true);
				
				}else{//没有子节点
					
					tDb.setParent(false);
				}
				//将对象放入集合中
				dataList.add(tDb);
			}
						
		}
	
		return dataList;
	}
	/**
	 * 下拉树获取 字典类型列表typegroups
	 * 
	 * @param parentid 父id 
	 * @param ids  选中状态节点id
	 * @return TreeEntity实体集合
	 */
	public List<TreeEntity> getTSTypegroups(String parentid,String ids){
		
		//声明一个部门集合
		List<TSTypegroup> tSTypegroups = new ArrayList<TSTypegroup>();
		
		StringBuffer hql = new StringBuffer(" from TSTypegroup t");
		
		if(StringUtils.isNotBlank(parentid)){//parentid不为空时 根据parentid查询部门数据
			
			hql.append(" where TSTypegroup.id = ?");
			hql.append(" and isDeleted=0 order by orderInLevel");
			tSTypegroups = this.commonDao.findHql(hql.toString(), parentid);
		
		} else {//parentid为空时 需判断用户是否管理员
			
			
				hql.append(" where TSTypegroup is null");
				hql.append(" and isDeleted=0 order by orderInLevel");
				tSTypegroups = this.commonDao.findHql(hql.toString());
			
		}
		//声明一个TreeEntity实体集合
		List<TreeEntity> dataList = new ArrayList<TreeEntity>();
		
		if(tSTypegroups.size()>0){
			 
			String sql = null;
			Object[] params = null;			
			//遍历部门集合     给TreeEntity实体的id name isChecked pId isParent 属性 赋值
			 for(TSTypegroup t : tSTypegroups){
				
				 TreeEntity tDb = new TreeEntity();
				
				 tDb.setId(t.getTypegroupcode());
				
				 tDb.setName(t.getTypegroupname());
				
				
				 if(t.getTSTypegroup() != null ){
					
					tDb.setpId(t.getTSTypegroup().getId());
				
				 }
				
				 if(StringUtil.isNotEmpty(ids)){
					
					if(ids.indexOf(t.getId())!=-1) {
						
						tDb.setChecked(true);
						
					}
				}
				//根据id判断是否有子节点
				sql = "select count(1) from t_s_typegroup t where t.parent_id = ?";
				params = new Object[]{t.getId()};
				long count = this.commonDao.getCountForJdbcParam(sql, params);
				
				if(count>0){//有子节点
					
					tDb.setParent(true);
				
				}else{//没有子节点
					
					tDb.setParent(false);
				}
				//将对象放入集合中
				dataList.add(tDb);
			}
						
		}
	
		return dataList;
	}
	
	public List<Map<String, Object>> getListByTable(String dictTable, String dictField, String dictText, String condition,
			String dynamicCondition) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select");
		if(StringUtils.isNotEmpty(dictField)){
			sql.append(" "+dictField+" as id");
		}
		if(StringUtils.isNotEmpty(dictText)){
			sql.append(","+dictText+" as name");
		}
		
		if(StringUtils.isNotEmpty(dictTable)){
			sql.append(" from "+dictTable + " t_ ");
		}
		
		boolean isContainWhere = false;
		if(!condition.equals("null")) {//StringUtils.isNotEmpty(condition)!condition.equals("null")
			condition = dealCondition(condition);
			sql.append(" where " + condition);
			isContainWhere = true;
		}
		
		if(!dynamicCondition.equals("null")) {
			dynamicCondition = dealCondition(dynamicCondition);
			if(isContainWhere){//已包含where
				
				sql.append(" AND " + dynamicCondition);
			}else{//不包含where
				
				sql.append(" Where" + dynamicCondition);
			}
		}
		
		
		List<Map<String, Object>> dataList = commonDao.findForJdbc(sql.toString());
		
		List<Map<String, Object>> newDataList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> m = null;
		for(int i = 0;i<dataList.size();i++){
			
			m = dataList.get(i);
			Map<String, Object> newRowMap = new HashMap<>();
			Set<String> set = m.keySet();  
			Iterator<String> it = set.iterator();  
			while (it.hasNext()) {  
			  String str = it.next();
			  Object value = m.get(str);
			  newRowMap.put(str.toLowerCase(), value);
			}  
			newDataList.add(newRowMap);
		}

		return newDataList;
	}
	
	/**
	 * 处理查询条件，删除查询条件开头的“and”、 “where”
	 * @param condi
	 * @return
	 */
	private String dealCondition(String condi) {
		if(StringUtils.isEmpty(condi)) {
			return "";
		}
		
		condi = condi.trim();
		if(condi.toUpperCase().startsWith("WHERE ")) {
			return condi.substring(5);
		}
		
		if(condi.toUpperCase().startsWith("AND ")) {
			return condi.substring(3);
		}
		
		return condi;
	}
	
	/**
	 * 文件上传
	 */
	public String uploadFile(String ctxPath,String bizPath,MultipartFile mf,String memoryType) {
		
		try {
		String fileName = null;
		String nowday=new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		File file = new File(ctxPath+File.separator+bizPath+File.separator+nowday);
		
		fileName = mf.getOriginalFilename();// 获取文件名
		if(StringUtils.isNotEmpty(memoryType)){
			
			if(memoryType.equals("db")){//存储到数据库中
				
				String title = "";
				String extend = "";
				TSAttachment tst = new TSAttachment();
				
				tst.setAttachmentcontent(mf.getBytes());//源文件
				
				if(fileName.indexOf(".") != -1){
					
					
					title = fileName.substring(0, fileName.lastIndexOf("."));
					extend = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
				}
				tst.setAttachmenttitle(title);//文件名称
				tst.setExtend(extend);//后缀名
				
				Date date = new Date();
				Timestamp creatDate = new Timestamp(date.getTime());
				tst.setCreatedate(creatDate);//创建时间
				
				
				commonDao.saveOrUpdate(tst);
				return tst.getId();
			}
		}
		
		if (!file.exists()) {
			file.mkdirs();// 创建文件根目录
		}
       
		String savePath = file.getPath() + File.separator + fileName;
		
		File savefile = new File(savePath);
		
		FileCopyUtils.copy(mf.getBytes(), savefile);
		
		String dbpath = bizPath+File.separator+nowday+File.separator+fileName;
		
		return dbpath;
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 附件删除
	 */
	public boolean delFile(File fileDelete) {
		boolean isSueecss = false;
		
		if(fileDelete.delete()){
			isSueecss = true;
		}
		return isSueecss;
	}
	/**
	 * 删除数据库文件
	 * @param id 数据库保存的文件 对应的id
	 * @return
	 */
	public int delDbFileByid(String id) {

		if(id==null || id.equals("")) {
			
			return -1;
		}

		String hql = "delete from TSAttachment  where id=?";
		
		int i = this.commonDao.executeHql(hql, id);
		
		return i;

	}
	public PageList getdatalistgrid(String tableName,DataGrid dataGrid,JSONArray jsonArray) {
		
		String relation="";//关系
		String condiName="";//条件名
		String operator="";//操作符
		String condiVal="";//条件值
		String newOperator="";//
		//String resultsort="";//排序
		if(StringUtil.isEmpty(tableName)){
			return null;
		}
		//拼接SQL
		StringBuffer sql = new StringBuffer();
		
		sql.append("select ");
		
		if(StringUtil.isEmpty(dataGrid.getField())){
			return null;
		}
		int indexb = dataGrid.getField().lastIndexOf(","); //去掉最后一个','号
		sql.append(dataGrid.getField().substring(0, indexb));
			
		sql.append(" from "+tableName);
			int num=0;
		if(jsonArray != null){
			
			for(int i=0;i<jsonArray.length();i++){
				condiVal = jsonArray.getJSONObject(i).getString("condiVal");
				if(StringUtil.isEmpty(condiVal)){
					continue;
				}
				if(num <1){
					sql.append(" where");
				}
				num++;
				relation = jsonArray.getJSONObject(i).getString("relation");
				condiName = jsonArray.getJSONObject(i).getString("condiName");
				operator = jsonArray.getJSONObject(i).getString("operator");
				//resultsort = jsonArray.getJSONObject(i).getString("resultsort");
				switch (operator) {
				case "eq"://等于
					newOperator="=";
					break;
				case "gt"://大于
					newOperator=">";
					break;
				case "lt"://小于
					newOperator="<";
					break;
				case "gte"://大于等于
					newOperator=">=";
					break;
				case "lte"://小于等于
					newOperator="<=";
					break;
				case "nq"://不等于
					newOperator="<>";
					break;
				case "like"://大于等于
					newOperator="like";
					//如果操作符为like时，要对条件值进行添加%%
					condiVal="%"+condiVal+"%";
					break;
				default:
					break;
				}
				if(sql.toString().endsWith("where")){ //已where结尾
					sql.append(" "+condiName+" "+newOperator+" '"+condiVal+"'");
				}else{
					sql.append(" "+relation+" "+condiName+" "+newOperator+" '"+condiVal+"'");
				}
			}
		}
		HqlQuery hqlQuery = new HqlQuery("");
		
		hqlQuery.setQueryString(sql.toString());
		hqlQuery.setDataGrid(dataGrid);
		hqlQuery.setCurPage(dataGrid.getPage());
		hqlQuery.setPageSize(dataGrid.getRows());
		
		PageList pageList = commonDao.getPageListBySql(hqlQuery,false);
		
		return pageList;
	}
	
	/**
	 * 树形弹窗数据源  以菜单为例
	 * @param parentid 父id 
	 * @param ids  选中状态节点id
	 * @return TreeEntity实体集合
	 */
	public List<TreeEntity> getfunctionInfo(String parentid,String ids){
		
		//声明一个菜单集合
		List<TSFunction> TSFunctions = new ArrayList<TSFunction>();
		
		
		StringBuffer hql = new StringBuffer(" from TSFunction t ");
		
//		if(StringUtils.isNotBlank(parentid)){//parentid不为空时 根据parentid查询菜单数据
//			
//			hql.append(" where TSFunction.id = ?");
//			TSFunctions = this.commonDao.findHql(hql.toString(), parentid);
//		
//		}
//		else {//parentid为空时 需判断用户是否管理员
			
			/*只展示当前登陆用户相关的组织机构树，管理员除外*/
//			HttpSession session = ContextHolderUtils.getSession();
//			TSUser currentUser = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
//			
//			if(!currentUser.getStatus().equals(Globals.User_ADMIN)) {//非管理员 按照当前用户的部门id查询数据
			//	TSFunction dept = (TSFunction) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
			//	hql.append(" where id = ?");
			//	TSFunctions = this.commonDao.findHql(hql.toString(),dept.getId());
			
			//}else {//管理员 从根节点加载全部数据
				//hql.append(" where TSFunction is null");
				TSFunctions = this.commonDao.findHql(hql.toString());
			//}
		//}
		//声明一个TreeEntity实体集合
		List<TreeEntity> dataList = new ArrayList<TreeEntity>();
		
		if(TSFunctions.size()>0){
			 
			String sql = null;
			Object[] params = null;			
			//遍历菜单集合     给TreeEntity实体的id name isChecked pId isParent 属性 赋值
			 for(TSFunction t : TSFunctions){
				
				 TreeEntity tDb = new TreeEntity();
				
				 tDb.setId(t.getId());
				
				 tDb.setName(t.getFunctionName());
				
				
				 if(t.getTSFunction() != null ){
					
					tDb.setpId(t.getTSFunction().getId());
				
				 }
				
				 if(StringUtil.isNotEmpty(ids)){
					
					if(ids.indexOf(t.getId())!=-1) {
						
						tDb.setChecked(true);
						
					}
				}
				//根据id判断是否有子节点
				sql = "select count(1) from t_s_function t where t.parentfunctionid = ?";
				params = new Object[]{t.getId()};
				long count = this.commonDao.getCountForJdbcParam(sql, params);
				
				if(count>0){//有子节点
					
					tDb.setParent(true);
				
				}else{//没有子节点
					
					tDb.setParent(false);
				}
				//将对象放入集合中
				dataList.add(tDb);
			}
						
		}
	
		return dataList;
	}
}
