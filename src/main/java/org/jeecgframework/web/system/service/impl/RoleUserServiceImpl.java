package org.jeecgframework.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUserBase;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.RoleService;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("roleUserService")
@Transactional
public class RoleUserServiceImpl implements RoleUserService {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public TSRoleUserBase saveRoleUser(TSRoleUserBase roleUser) {
		commonDao.saveOrUpdate(roleUser);
		return roleUser;
	}

	@Override
	public int deleteRoleUser(String id) {
		String hql = "delete From TSRoleUserBase where id = ?";
		int i = this.commonDao.executeHql(hql, id);
		return i;
	}

	@Override
	public TSRoleUser getRoleUser(String id) {
		String hql = "From TSRoleUser where id = ?";
		List<TSRoleUser> list = this.commonDao.findHql(hql, id);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public TSRoleUser getRoleUser(String roleId, String userId, String departId) {
		String hql = "From TSRoleUser ru where ru.TSRole.id = ? and ru.TSUser.id = ? and ru.tSDepart.id = ?";
		List<TSRoleUser> list = this.commonDao.findHql(hql, roleId, userId, departId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<TSRoleUser> getRoleUserListByUserId(String userId) {
		String hql = "From TSRoleUser ru where ru.TSUser.id = ? order by ru.TSRole.roleName";
		List<TSRoleUser> list = this.commonDao.findHql(hql, userId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public List<TSRoleUser> getRoleUserListByUserId(String userId, String departId) {
		String hql = "From TSRoleUser ru where ru.TSUser.id = ? and ru.tSDepart.id = ? order by ru.TSRole.roleName ";
		List<TSRoleUser> list = this.commonDao.findHql(hql, userId, departId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public List<TSRoleUser> getRoleUserListByRoleId(String roleId) {
		String hql = "From TSRoleUser ru where ru.TSRole.id = ? order by ru.TSUser.userName";
		List<TSRoleUser> list = this.commonDao.findHql(hql, roleId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public int deleteRoleUserByUserId(String userId) {
		String hql = "delete From TSRoleUserBase ru where ru.TSUser.id = ?";
		int i = this.commonDao.executeHql(hql, userId);
		return i;
	}

	@Override
	public int deleteRoleUserByRoleId(String roleId) {
		String hql = "delete From TSRoleUserBase ru where ru.TSRole.id = ?";
		int i = this.commonDao.executeHql(hql, roleId);
		return i;
	}

	@Override
	public int deleteRoleUserByUserIdAndOrgId(String userId, String orgId) {
		String hql = "delete From TSRoleUserBase ru where ru.TSUser.id = ? and ru.tSDepart.id = ?";
		int i = this.commonDao.executeHql(hql, userId,orgId);
		return i;
	}

	@Override
	public PageList getRoleUserListByRoleId(int pageNo, int pageSize, TSUser user, String roleId) {
		if(roleId==null || "".equals(roleId)) {
			return null;
		}
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setCurPage(pageNo);
		hqlQuery.setPageSize(pageSize);
		
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Type> types = new ArrayList<Type>();
		
		String hql = "From TSRoleUser ru where ru.TSRole.id = ? ";
		params.add(roleId);
		types.add(StringType.INSTANCE);
		
		if(user != null) {
			if(user.getUserName()!=null &&!"".equals(user.getUserName())) {
				hql = hql + " and ru.TSUser.userName like ?";
				params.add("%" + user.getUserName() + "%");
				types.add(StringType.INSTANCE);
			}
			
			if(user.getRealName()!=null &&!"".equals(user.getRealName())) {
				hql = hql + " and ru.TSUser.realName like ?";
				params.add("%" + user.getRealName() + "%");
				types.add(StringType.INSTANCE);
			}
		}
		
		hql = hql + " order by ru.TSUser.userName";
		
		hqlQuery.setQueryString(hql);
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
		return commonDao.getPageList(hqlQuery, needParameter);
	}

	@Override
	public int saveRoleUser(String roleId, String userOrgIds) {
		if(roleId==null || "".equals(roleId)) {
			return 0;
		}
		
		if(userOrgIds==null || "".equals(userOrgIds)) {
			return 0;
		}
		
		TSRole role = roleService.getRole(roleId);
		if(role == null) {
			return 0;
		}
		
		List<TSUserOrg> list = userOrgService.getUserOrgListByIds(userOrgIds);
		if(list==null || list.size()==0) {
			return 0;
		}
		
		for(int i=0; i<list.size(); i++) {
			TSUserOrg userOrg = list.get(i);
			TSRoleUserBase roleUser = new TSRoleUserBase();
			roleUser.setTSRole(role);
			roleUser.settSDepart(userOrg.getTsDepart());
			roleUser.setTSUser(userOrg.getTsUser());
			saveRoleUser(roleUser);
		}
		return list.size();
	}
	
	@Override
	public TSOperation getOperationById(String id) {
		String hql = "From TSOperation where id = ?";
		List<TSOperation> list = this.commonDao.findHql(hql, id);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<TSOperation> getOperationByCode(String code) {
		String hql = "From TSOperation where operationcode = ?";
		List<TSOperation> list = this.commonDao.findHql(hql, code);
		return list;
	}
	@Override
	public List<TSRoleUser> getRoleUserListByUserName(String userName) {
		String hql = "From TSRoleUser ru where ru.TSUser.userName = ? order by ru.TSRole.roleName";
		List<TSRoleUser> list = this.commonDao.findHql(hql, userName);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}
}
