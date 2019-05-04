package org.jeecgframework.web.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.RoleService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.bssys_deploy.service.BssysDeployServiceI;

/**
 * 
 * @author  zdl
 *
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BssysDeployServiceI bssysDeployService;
	
	

	@Override
	public boolean lockRole(String id) {
		int i = this.commonDao.executeSql("update t_s_role set status = '0' where status = '1' and id=?", id);
		if(i==1){
			return true;
		}
		return false;
	}
	@Override
	public boolean unLockRole(String id) {
		int i = this.commonDao.executeSql("update t_s_role set status = '1' where status = '0' and id=?", id); 
		if(i==1){
			return true;
		}
		return false;
	}
	@Override
	public List<TSUser> getUserListByRoleId(String roleId) {
		String hql = " From TSUser u where exists(select 1 from TSRoleUser tr where tr.TSUser.id = u.id and tr.TSRole.id = ? )";
		List<TSUser> list = commonDao.findHql(hql, roleId);
		return list;
	}
	@Override
	public List<TSUser> getUserListForLockRole(String roleId) {
		String hql = " From TSUser u where exists(select 1 from TSRoleUserBase tr where tr.TSUser.id = u.id and tr.TSRole.id = ? )";
		List<TSUser> list = commonDao.findHql(hql, roleId);
		return list;
	}
	@Override
	public <T> List<T> getAllRoles() {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class);
		cq.add(Restrictions.eq("status","1"));
		return commonDao.getListByCriteriaQuery(cq, false);
	}
	
	@Override
	public int deleteRole(String roleId) {
		//判断角色是否可删除
		List<TSUser> list = this.getUserListByRoleId(roleId);
		if(list != null && list.size() > 0) {
			//当角色存在关联用户时，是否允许删除，1：是；0：否，默认为否。当配置“是”时，验证是否有级联用户，若有，给出提示，用户确认后级联删除用户、权限；当配置“否”时，验证是否有级联用户，有任何一个则不允许删除
			String roleUserRelation = bssysDeployService.getValue("001003002", null);
			if(roleUserRelation==null || roleUserRelation.equals("")){
				roleUserRelation = "0";
			}
			if(roleUserRelation.equals("0")) {
				return -1;
			}
		}
		
		//删除关联用户
		String hql = "delete From TSRoleUserBase ru where ru.TSRole.id = ?";
		this.commonDao.executeHql(hql, roleId);
		
		//删除关联菜单
		String hql2 = "delete From TSRoleFunctionBase rf where rf.TSRole.id = ?";
		this.commonDao.executeHql(hql2, roleId);
		
		//删除关联组织
		String hql3 = "delete From TSRoleOrgBase ro where ro.tsRole.id = ?";
		this.commonDao.executeHql(hql3, roleId);
				
		//删除角色
		String hql4 = "delete From TSRole r where r.id = ?";
		int i = this.commonDao.executeHql(hql4, roleId);
		return i;
	}
	@Override
	public TSRole getRole(String id) {
		if(id==null ||id.equals("")){
			return null;
		}
		return (TSRole) commonDao.getEntity(TSRole.class, id);
	}
	@Override
	public List<TSRole> queryRoleList(TSRole role) {
		String hql = " From TSRole r where status = 1";
		if(role==null) {
			return commonDao.findByHql(hql, null);
		}
		
		int i=0;
		HashMap<Integer, Object> params = new HashMap<>();
		if(role.getRoleCode()!=null && !role.getRoleCode().equals("")) {
			hql = hql + " and r.roleCode like ?";
			params.put(i, "%" + role.getRoleCode() + "%");
			i = i + 1;
		}
		
		if(role.getRoleName()!=null && !role.getRoleName().equals("")) {
			hql = hql + " and r.roleName like ?";
			params.put(i, "%" + role.getRoleName() + "%");
			i = i + 1;
		}
		
		return commonDao.findByHql(hql, params);
	}
	@Override
	public TSRole saveRole(TSRole role) {
		commonDao.saveOrUpdate(role);
		return role;
	}
	
}
