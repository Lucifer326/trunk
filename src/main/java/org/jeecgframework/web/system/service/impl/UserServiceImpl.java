package org.jeecgframework.web.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUserBase;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.pojo.base.TSUserCardInfoEntity;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.UserOrgService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * @author  张代浩
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private RoleUserService roleUserService;

	public TSUser checkUserExits(TSUser user){
		return this.commonDao.getUserByUserIdAndUserNameExits(user);
	}
	public String getUserRole(TSUser user){
		return this.commonDao.getUserRole(user);
	}
	
	public void pwdInit(TSUser user,String newPwd) {
			this.commonDao.pwdInit(user,newPwd);
	}
	
	@Override
	public TSUser getUserByLoginName(String loginName) {
		if(loginName==null || "".equals(loginName)) {
			return null;
		}
		
		String hql = " From TSUser u where u.status in (1,-1) and u.userName = ?";
		List<TSUser> list = this.commonDao.findHql(hql, loginName);
		if(list==null || list.size()==0) {
			return null;
		}
		
		return list.get(0);
	}
	
	@Override
	public List<TSDepart> getUserDepartList(String userId) {
		if(userId==null || "".equals(userId)) {
			return null;
		}
		
		String hql = "select uo.tsDepart from TSUserOrg uo where uo.tsUser.id = ? order by uo.tsDepart.departname";
		List<TSDepart> list = this.commonDao.findHql(hql, userId);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}
	
	@Override
	public int deleteUserById(String id) {
		if(id==null || id.equals("")){
			return 0;
		}
		//删除用户角色关联关系
		String hql = "delete From TSRoleUserBase t where t.TSUser.id = ?";
		this.commonDao.executeHql(hql, id);
		
		//删除用户部门关联关系
		hql = "delete From TSUserOrg t where t.tsUser.id = ?";
		this.commonDao.executeHql(hql, id);
		
		//删除用户
		hql = "delete From TSUser t where t.id = ?";
		int i = this.commonDao.executeHql(hql, id);
		return i;
	}
	
	@Override
	public TSUser getUser(String id) {
		if(id==null || id.equals("")){
			return null;
		}
		
		return (TSUser) this.commonDao.getEntity(TSUser.class, id);
	}
	
	@Override
	public PageList queryUser(int pageNo, int pageSize, TSUser user, String orgId) {
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setCurPage(pageNo);
		hqlQuery.setPageSize(pageSize);
		
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Type> types = new ArrayList<Type>();
		
		String hql = "select u From TSUser u where u.status in (1,0) ";
		if(user != null) {
			if(user.getUserName()!=null &&!"".equals(user.getUserName())) {
				hql = hql + " and u.userName like ?";
				params.add("%" + user.getUserName() + "%");
				types.add(StringType.INSTANCE);
			}
			
			if(user.getRealName()!=null &&!"".equals(user.getRealName())) {
				hql = hql + " and u.realName like ?";
				params.add("%" + user.getRealName() + "%");
				types.add(StringType.INSTANCE);
			}
		}
		
		if(orgId!=null && !orgId.equals("")) {
			hql = hql + " and exists(select 1 from TSUserOrg uo where u.id = uo.tsUser.id and uo.tsDepart.id = ?)";
			params.add(orgId);
			types.add(StringType.INSTANCE);
		}
		
		hql = hql + " order by userName";
		
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
		PageList pageList = commonDao.getPageList(hqlQuery, needParameter);
		List<TSUser> list = pageList.getResultList();
		if(list == null || list.size() ==0) {
			return pageList;
		}
		
		//设置用户角色显示
		List<TSUser> result = new ArrayList<TSUser>();
		
		for(int i=0; i<list.size(); i++) {
			TSUser tempUser = list.get(i);
			result.add(tempUser);
			List<TSRoleUser> ruList = roleUserService.getRoleUserListByUserId(tempUser.getId());
			if(ruList==null || ruList.size()==0) {
				tempUser.setUserKey(null);
				continue;
			}
			String userKey = "";
			for(int m=0; m<ruList.size(); m++) {
				TSRoleUser tempRu = ruList.get(m);
				if(tempRu.gettSDepart() != null && tempRu.getTSRole()!=null) {
					userKey = userKey + tempRu.gettSDepart().getDepartname() + "-" + tempRu.getTSRole().getRoleName() + ","; 
				}
			}
			if(userKey.length()>1){
				userKey = userKey.substring(0, userKey.length()-1);
			}
			tempUser.setUserKey(userKey);
		}
		pageList.setResultList(result);
		
		return pageList;
	}
	@Override
	public TSUser saveUser(TSUser user) {
		this.commonDao.saveOrUpdate(user);
		return user;
	}
	@Override
	public List<TSUser> getAllUser() {
		String hql = "select u From TSUser u where u.status = 1 ";
		List<TSUser> list = this.commonDao.findHql(hql);
		if(list==null || list.size()==0) {
			return null;
		}
		return list;
	}
	@Override
	public TSUser saveUser(TSUser user, String orgIds, String roleIds) {
		//若用户id不为空，表示用户已存在，先删除其角色或部门信息
		if(user.getId()!=null && !"".equals(user.getId())){
			userOrgService.deleteUserOrgByUserId(user.getId());
			roleUserService.deleteRoleUserByUserId(user.getId());
		}
		
		//保存用户
		commonDao.saveOrUpdate(user);
		
		//保存用户组织关联关系
		if(orgIds!=null && !"".equals(orgIds)) {
			List<TSUserOrg> userOrgList = new ArrayList<TSUserOrg>();
	        List<String> orgIdList = extractIdListByComma(orgIds);
	        for (String orgId : orgIdList) {
	            TSDepart depart = new TSDepart();
	            depart.setId(orgId);

	            TSUserOrg userOrg = new TSUserOrg();
	            userOrg.setTsUser(user);
	            userOrg.setTsDepart(depart);

	            userOrgList.add(userOrg);
	            userOrgService.saveUserOrg(userOrg);
	        }
		}
		
		//保存用户角色关联关系
		if(roleIds!=null && !"".equals(roleIds)) {
			List<String> roleIdList = extractIdListByComma(roleIds);
			for(String roleId : roleIdList) {
				String[] s = roleId.split("_");
				TSRoleUserBase rUser = new TSRoleUserBase();
				TSDepart dept = new TSDepart();
				dept.setId(s[0]);
				
				TSRole role = new TSRole();
				role.setId(s[1]);
				
				rUser.setTSRole(role);
				rUser.setTSUser(user);
				rUser.settSDepart(dept);
				roleUserService.saveRoleUser(rUser);
			}
		}
		//
		return null;
	}
	
	/**
	 * 
	 * @param ids 由","分隔的多个id
	 * @return
	 */
	protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.hasText(ids)) {
            for (String id : ids.split(",")) {
                if (StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }
	/**
	 * 查询登陆用户及下级关联的部门列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<TSDepart> getUserNextDepartList(String userId) {
		if(userId==null || "".equals(userId)) {
			return null;
		}
		List<TSDepart> list=getUserDepartList(userId);
		String orgCode=null;
		List<TSDepart> list1=new ArrayList<TSDepart>();
		List<TSDepart> list2=new ArrayList<TSDepart>();
		String hql = "from TSDepart t where t.orgCode like concat(?,'%')";
		if(list.size()<=1){
			orgCode=list.get(0).getOrgCode();
			list1 = this.commonDao.findHql(hql, orgCode);
		}else{
			for(int i=0;i<list.size();i++){
				orgCode=list.get(i).getOrgCode();
				list2 = this.commonDao.findHql(hql, orgCode);
				list1.removeAll(list2);
				list1.addAll(list2);
			}
		}
		if(list1==null || list1.size()==0) {
			return null;
		}
		return list1;
	}
	/**
	 * 查询登陆用户和代理人及下级关联的部门列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<TSDepart> getUserAndAgentNextDepartList(String userId) {
		if(userId==null || "".equals(userId)) {
			return null;
		}
		String[] ids=userId.split(",");
		List<TSDepart> list1=new ArrayList<TSDepart>();
		List<TSDepart> list2=new ArrayList<TSDepart>();
		List<TSDepart> list3=new ArrayList<TSDepart>();
		for(int j=0;j<ids.length;j++){
			TSUser loginUser = this.getUserByLoginName(ids[j]);
			List<TSDepart> list=getUserDepartList(loginUser.getId());
			String orgCode=null;
			String hql = "from TSDepart t where t.orgCode like concat(?,'%')";
			if(list.size()<=1){
				orgCode=list.get(0).getOrgCode();
				list3 = this.commonDao.findHql(hql, orgCode);
				list1.removeAll(list3);
				list1.addAll(list3);
			}else{
				for(int i=0;i<list.size();i++){
					orgCode=list.get(i).getOrgCode();
					list2 = this.commonDao.findHql(hql, orgCode);
					list3.removeAll(list2);
					list3.addAll(list2);
					list1.removeAll(list3);
					list1.addAll(list3);
				}
			}
		}
		if(list1==null || list1.size()==0) {
			return null;
		}
		return list1;
	}
	
	@Override
	public List<TSUserCardInfoEntity> getUserCardInfoList(String userName) {
		List<TSUserCardInfoEntity> userCardInfoList=null;
		String hql = "from TSUserCardInfoEntity  where  userName=? order by id desc";
		userCardInfoList =commonDao.findHql(hql, userName);
		return userCardInfoList;
	}
	@Override
	public TSUserCardInfoEntity saveUserCardInfo(TSUserCardInfoEntity userCardInfo) {
		commonDao.saveOrUpdate(userCardInfo);
		return null;
	}
	
}
