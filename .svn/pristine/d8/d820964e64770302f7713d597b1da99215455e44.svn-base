package org.jeecgframework.web.system.service.impl;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSDepartImport;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.DepartService;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.jeecgframework.web.system.service.UserOrgService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("departService")
@Transactional
public class DepartServiceImpl implements DepartService {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private RoleUserService roleUserServicer;
	@Autowired
	private UserService userService;
	@Autowired
	private TypeServiceI typeService;

	/**
	 * 根据父id查询组织列表
	 * 
	 * @param parentdepartid
	 * @return
	 */
	public List<TSDepart> getDepartlistByParentId(String parentdepartid) {
		String hql = "from TSDepart t1 where t1.TSPDepart.id = ? order by t1.orgCode";
		return this.commonDao.findHql(hql, parentdepartid);
	}

	/**
	 * 根据区划id查询组织列表
	 * 
	 * @param regionId
	 * @return
	 */
	public List<TSDepart> getDepartlistByRegionId(String regionId) {
		String hql = "from TSDepart t1 where t1.regionId = ? and not exists(from TSDepart t2 where t2.regionId = ? and t2.id = t1.TSPDepart.id) order by t1.orgCode";
		return this.commonDao.findHql(hql, regionId, regionId);
	}

	/**
	 * 根据组织id查找 所有的用户 个数
	 * 
	 * @param departId
	 * @return
	 */
	public Long getUsersCount(String departId) {

		Long i = this.commonDao.getCountForJdbc("select count(1) from t_s_user_org where org_id='" + departId + "'");
		return i;
	}

	/**
	 * 删除组织机构
	 * 
	 * @param departId
	 * @return
	 */
	public int deleteDept(String departId) {
		if (departId == null || "".equals(departId)) {
			return 0;
		}
		// 获取部门对象
		TSDepart depart = getDepart(departId);
		if (depart == null) {
			return 0;
		}

		// 删除用户列表

		// 通过部门id 获得 部门下所有的用户
		List<TSUserOrg> userList = userOrgService.getUserOrgListByOrgId(departId);

		if (null != userList && userList.size() > 0) {

			for (int i = 0; i < userList.size(); i++) {
				// 根据用户id 获得
				List<TSUserOrg> tempList = userOrgService.getUserOrgListByUserId(userList.get(i).getTsUser().getId());

				if (null != tempList && tempList.size() == 1) {

					userService.deleteUserById(userList.get(i).getTsUser().getId());

				} else if (tempList.size() > 1) {

					userOrgService.deleteUserOrgByOrgIdAndUserId(departId, userList.get(i).getTsUser().getId());
				}

			}
		}

		// 删除子部门
		List<TSDepart> childDepartList = depart.getTSDeparts();
		if (childDepartList != null && childDepartList.size() > 0) {
			// 删除子部门
			for (int i = 0; i < childDepartList.size(); i++) {
				TSDepart child = childDepartList.get(i);
				deleteDept(child.getId());
			}
		}

		// 删除本部门
		String hql = "delete From TSDepart t where t.id = ?";
		int count = commonDao.executeHql(hql, departId);
		return count;
	}

	@Override
	public TSDepart getDepart(String id) {
		if (id == null || "".equals(id)) {
			return null;
		}
		return (TSDepart) commonDao.getEntity(TSDepart.class, id);
	}

	/**
	 * 删除用户组织机构关联关系，当用户只归属于当前组织机构时，级联删除用户
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteUserOrg(String userId, String orgId) {
		if (userId == null || userId.equals("")) {
			return 0;
		}

		if (orgId == null || orgId.equals("")) {
			return 0;
		}

		// 查询是否存在要删除的关联关系
		TSUserOrg userOrg = userOrgService.getUserOrg(userId, orgId);
		if (userOrg == null) {// 不存在要删除的关联关系，直接返回
			return 0;
		}

		// 查询用户是否存在多个归属组织机构
		List<TSUserOrg> list = userOrgService.getUserOrgListByUserId(userId);

		if (null != list && list.size() > 1) {// 存在多个组织机构，则只删除用户组织关联关系，以及用户和此组织机构相关的角色
			// 删除用户和此组织机构相关的角色
			roleUserServicer.deleteRoleUserByUserIdAndOrgId(userId, orgId);
			// 删除用户组织关联关系
			int count = userOrgService.deleteUserOrgByOrgIdAndUserId(userId, orgId);
			return count;
		} else {// 只有一个归属组织机构，则删除用户
			return userService.deleteUserById(userId);
		}
	}

	/**
	 * 生成机构编码
	 * 
	 * @param pid
	 * @return
	 */
	public String generateOrgCode(String pid) {

		if (pid == null || pid.equals("")) {

			// 查询所有子集的最大机构编码
			String hql = "from TSDepart t where t.TSPDepart is null order by t.orgCode desc";

			String maxOrgCode = null;

			List<TSDepart> list = commonDao.findHql(hql);

			if (list == null || list.size() == 0) {
				return "000";
			}
			maxOrgCode = list.get(0).getOrgCode();
			return dealOrgCode(maxOrgCode);
		}

		// 查询父级下的所有子集按区划code 降序排列
		String hql = "from TSDepart t where t.TSPDepart.id = ? order by t.orgCode desc";
		String maxOrgCode = null;
		List<TSDepart> list = commonDao.findHql(hql, pid);

		if (list == null || list.size() == 0) {
			TSDepart parentDept = this.getDepart(pid);
			return parentDept.getOrgCode() + "001";
		}
		maxOrgCode = list.get(0).getOrgCode();
		return dealOrgCode(maxOrgCode);
	}

	/**
	 * 字符串运算的方法
	 * 
	 * @param orgCode
	 * @return
	 */
	private String dealOrgCode(String orgCode) {
		int n = orgCode.length();// 取出字符串的长度
		int num = Integer.parseInt(orgCode) + 1;// 将该数字加一
		String added = String.valueOf(num);

		int n1 = added.length();
		if (n > n1) {
			for (int i = 0; i < n - n1; i++) {
				added = "0" + added;
			}
		}
		return added;
	}

	/**
	 * 新增
	 */
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	/**
	 * 编辑
	 */
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq, Boolean ispage) {
		return commonDao.getListByCriteriaQuery(cq, ispage);
	}

	public TSDepart getTSDepartBydepartname(String parentdepartname) {
		String hql = "from TSDepart where departname = ?";
		List<TSDepart> departList = commonDao.findHql(hql, parentdepartname);
		if (departList.size() > 0) {
			return departList.get(0);
		}
		return null;
	}

	/**
	 * 组织机构导入功能保存导入数据
	 */
	public void saveTSDepartList(List<TSDepartImport> tsDeparts) {

		if (null == tsDeparts || tsDeparts.size() == 0) {
			return;
		}

		// 记录区划编码与区划id对应关系
		HashMap<String, String> map = new HashMap<>();

		for (int i = 0; i < tsDeparts.size(); i++) {

			TSDepartImport d = tsDeparts.get(i);
			// 转换为TSDepart
			TSDepart depart = this.getTSDepartBydepartname(d.getDepartname());
			if (depart == null) {
				depart = new TSDepart();
			}
			// 组织机构名称
			depart.setDepartname(d.getDepartname());
			// 机构类型
			if (StringUtils.isNotEmpty(proceString(d.getOrgType()))) {
				TSType tsType = typeService.getTypenameByTypegroupcodeAndTypecode("orgtype", d.getOrgType());
				if (tsType != null) {
					depart.setOrgType(tsType.getTypename());
				}
			}
			depart.setOrgType(proceString(d.getOrgType()));
			// 组织机构描述
			depart.setDescription(proceString(d.getDescription()));
			// 电话
			depart.setMobile(proceString(d.getMobile()));
			// 传真
			depart.setFax(proceString(proceString(d.getFax())));
			// 地址
			depart.setAddress(proceString(d.getAddress()));
			// 区划id
			depart.setRegionId(d.getRegionId());

			// 父级id
			if (map.containsKey(d.getParentdepartname())) {
				TSDepart parentDepart = new TSDepart();
				parentDepart.setId(map.get(d.getParentdepartname()));
				depart.setTSPDepart(parentDepart);

			} else {
				TSDepart parentDepart = this.getTSDepartBydepartname(StringUtil.replaceBlank(d.getParentdepartname()));
				depart.setTSPDepart(parentDepart);
				map.put(d.getParentdepartname(), parentDepart.getId());
			}
			if (depart.getId() == null || depart.getId().equals("")) {// 新增

				// 机构编码
				String pid = depart.getTSPDepart().getId();
				String maxOrgCode = generateOrgCode(pid);

				depart.setOrgCode(maxOrgCode);
			}
			// 进行保存操作
			save(depart);
		}
	}

	/**
	 * 处理字符串
	 * 
	 * @param src
	 * @return
	 */
	private String proceString(String src) {
		if (src != null && src.length() > 0) {
			if (StringUtil.isFloatNumeric(src)) {
				String[] s = src.split("[.]");
				return s[0];
			}
		}
		return src;
	}

	@Override
	public List<TSDepart> getDepartListByRegionIdAndName(String name, String regionId) throws Exception{
		//String hql = "from TSDepart t1 where t1.departname = ? and t1.id in (select id from TSDepart t2 start with t2.regionId=? connect by prior t2.id =  t.parentdepartid)";
		//return this.commonDao.findHql(hql, name, regionId);
		List<TSDepart> list = new ArrayList<TSDepart>();
		List<Object[]> objList = this.commonDao.findListbySql(" select id,departname,description,parentdepartid,org_code,org_type,mobile,fax,address,region_id from t_s_depart t where t.departname like '%"+name+"%' and t.id in( select id from t_s_depart t1 start with t1.region_id ='"+regionId+"' connect by prior t1.id =  t1.parentdepartid ) ");
		//commonDao.findByQueryString(query)
		if(objList.size()>0){
			for(int i=0;i<objList.size();i++){
				TSDepart depart = new TSDepart();
				depart.setId(objList.get(i)[0]==null?"":objList.get(i)[0].toString());
				depart.setDepartname(objList.get(i)[1]==null?"":objList.get(i)[1].toString());
				depart.setDescription(objList.get(i)[2]==null?"":ClobToString((Clob)objList.get(i)[2]));
				depart.setParentdepartid(objList.get(i)[3]==null?"":objList.get(i)[3].toString());
				depart.setOrgCode(objList.get(i)[4]==null?"":objList.get(i)[4].toString());
				depart.setOrgType(objList.get(i)[5]==null?"":objList.get(i)[5].toString());
				depart.setMobile(objList.get(i)[6]==null?"":objList.get(i)[6].toString());
				depart.setFax(objList.get(i)[7]==null?"":objList.get(i)[7].toString());
				depart.setAddress(objList.get(i)[8]==null?"":objList.get(i)[8].toString());
				depart.setRegionId(objList.get(i)[9]==null?"":objList.get(i)[9].toString());
				list.add(depart);
			}			
		}
		return list;		
	}
	
	
	//oracle.sql.Clob类型转换成String类型

	public String ClobToString(Clob clob) throws Exception{
	String reString = "";
	Reader is = clob.getCharacterStream();// 得到流
	BufferedReader br = new BufferedReader(is);
	String s = br.readLine();
	StringBuffer sb = new StringBuffer();
	while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
	sb.append(s);
	s = br.readLine();
	}
	reString = sb.toString();
	return reString;
	}
}