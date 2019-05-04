package org.jeecgframework.web.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("userOrgService")
@Transactional
public class UserOrgServiceImpl implements UserOrgService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public TSUserOrg getUserOrg(String id) {
		String hql = "From TSUserOrg where id = ?";
		List<TSUserOrg> list = this.commonDao.findHql(hql, id);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public TSUserOrg getUserOrg(String userId, String orgId) {
		String hql = "From TSUserOrg uo where uo.tsUser.id = ? and uo.tsDepart.id = ?";
		List<TSUserOrg> list = this.commonDao.findHql(hql, userId,orgId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public TSUserOrg saveUserOrg(TSUserOrg userOrg) {
		commonDao.saveOrUpdate(userOrg);
		return userOrg;
	}

	@Override
	public int deleteUserOrg(String id) {
		String hql = "delete From TSUserOrg where id = ?";
		int i = this.commonDao.executeHql(hql, id);
		return i;
	}

	@Override
	public int deleteUserOrgByUserId(String userId) {
		String hql = "delete From TSUserOrg uo where uo.tsUser.id = ?";
		int i = this.commonDao.executeHql(hql, userId);
		return i;
	}

	@Override
	public int deleteUserOrgByOrgId(String orgId) {
		String hql = "delete From TSUserOrg uo where uo.tsDepart.id = ?";
		int i = this.commonDao.executeHql(hql, orgId);
		return i;
	}

	@Override
	public List<TSUserOrg> getUserOrgListByUserId(String userId) {
		String hql = "From TSUserOrg uo where uo.tsUser.id = ? order by uo.tsDepart.departname";
		List<TSUserOrg> list = this.commonDao.findHql(hql, userId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public List<TSUserOrg> getUserOrgListByOrgId(String orgId) {
		String hql = "From TSUserOrg uo where uo.tsDepart.id = ? order by uo.tsUser.userName";
		List<TSUserOrg> list = this.commonDao.findHql(hql, orgId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}
	
	@Override
	public int deleteUserOrgByOrgIdAndUserId(String userId, String orgId) {
		String hql = "delete From TSUserOrg uo where uo.tsUser.id = ? and uo.tsDepart.id = ?";
		int i = this.commonDao.executeHql(hql, userId,orgId);
		return i;
	}

	@Override
	public PageList queryUserOrgListNotContainRole(int pageNo, int pageSize, TSUser user, TSDepart depart,
			String roleId) {
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setCurPage(pageNo);
		hqlQuery.setPageSize(pageSize);
		
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Type> types = new ArrayList<Type>();
		
		String hql = "select u From TSUserOrg u where u.tsUser.status = 1";
		if(roleId!=null &&!"".equals(roleId)) {
			hql = hql + " and not exists(select 1 from TSRoleUser ru where ru.TSUser.id = u.tsUser.id and ru.tSDepart.id = u.tsDepart.id and ru.TSRole.id = ?)";
			params.add(roleId);
			types.add(StringType.INSTANCE);
		}
		
		//拼接用户查询条件
		if(user != null) {
			if(user.getUserName()!=null &&!"".equals(user.getUserName())) {
				hql = hql + " and u.tsUser.userName like ?";
				params.add("%" + user.getUserName() + "%");
				types.add(StringType.INSTANCE);
			}
			
			if(user.getRealName()!=null &&!"".equals(user.getRealName())) {
				hql = hql + " and u.tsUser.realName like ?";
				params.add("%" + user.getRealName() + "%");
				types.add(StringType.INSTANCE);
			}
		}
		
		//拼接部门查询条件
		if(depart != null) {
			if(depart.getDepartname()!=null &&!"".equals(depart.getDepartname())) {
				hql = hql + " and u.tsDepart.departname like ?";
				params.add("%" + depart.getDepartname() + "%");
				types.add(StringType.INSTANCE);
			}
			
		}
		
		hql = hql + " order by u.tsUser.userName";
		
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
	public List<TSUserOrg> getUserOrgListByIds(String ids) {
		if(ids==null || "".equals(ids)) {
			return null;
		}
		
		String newIds = "";
		String[] idArray = ids.split(",");
		for(int i=0; i<idArray.length; i++) {
			String id = idArray[i];
			if(id!=null && !id.trim().equals("")) {
				newIds = newIds + id.trim() + "','";
			}
		}
		
		newIds = "'" + newIds.substring(0, newIds.length()-2);
		String hql = "From TSUserOrg uo where uo. id in (" + newIds + ") ";
		List<TSUserOrg> list = this.commonDao.findByHql(hql, null);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}

}
