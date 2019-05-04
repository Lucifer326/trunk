package org.jeecgframework.web.system.service.impl;

import java.util.List;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.FunctionService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("functionService")
@Transactional
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public TSFunction getFunction(String id) {
		if(id==null ||id.equals("")){
			return null;
		}
		return (TSFunction) commonDao.getEntity(TSFunction.class, id);
	}

	@Override
	public List<TSFunction> getAllFunctions() {
		String hql = "from TSFunction f";
		return commonDao.findByHql(hql, null);
	}

	
	
	public int deleteFunction(String id) {
		if(id==null || id.equals("")) {
			return -1;
		}

		String hql2 = "delete from TSDataRule  where functionid=?";
		this.commonDao.executeHql(hql2, id);
		String hql3 = "delete from TSRoleFunctionBase where functionid=?";
		this.commonDao.executeHql(hql3, id);
		String hql4 = "delete from TSOperation  where functionid=?";
		this.commonDao.executeHql(hql4, id);
		String hql1 = "delete from TSFunction  where id= ?";
		int i = this.commonDao.executeHql(hql1, id);

		return i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public TSFunction saveFunction(TSFunction function) {
		commonDao.saveOrUpdate(function);
		return function;
	}

	@Override
	public List<TSFunction> getFunctionsForUser(String userId, String orgId) {
		//参数校验
		if(userId==null ||userId.equals("")){
			return null;
		}
		if(orgId==null ||orgId.equals("")){
			return null;
		}
		
		//判断用户
		TSUser user = userService.getUser(userId);
		if(user == null) {
			return null;
		}
		
		//管理员用户返回所有菜单
		if(user.getStatus() == Globals.User_ADMIN) {
			return getAllFunctions();
		}
		
		//根据用户查询其菜单
		String hql = "select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru ";
		hql = hql + " where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id ";
		hql = hql + " and ru.TSUser.id=? and ru.tSDepart.id=?";
		List<TSFunction> list = commonDao.findHql(hql, userId,orgId);
		if(list == null || list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public boolean menuAuthValidateByUrl(String userId, String orgId, String url) {
		//参数校验
		if(userId==null ||userId.equals("")){
			return false;
		}
		if(orgId==null ||orgId.equals("")){
			return false;
		}
		if(url==null ||url.equals("")){
			return false;
		}
		
		//判断用户
		TSUser user = userService.getUser(userId);
		if(user == null) {
			return false;
		}
		//管理员用户返回true
		if(user.getStatus() == Globals.User_ADMIN) {
			return true;
		}
		
		//根据用户查询其菜单
		String hql = "select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru ";
		hql = hql + " where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id ";
		hql = hql + " and ru.TSUser.id=? and ru.tSDepart.id=? and f.functionUrl like ?";
		List<TSFunction> list = commonDao.findHql(hql, userId,orgId,url + "%");
		if(list == null || list.size()==0) {
			return false;
		}
		return true;
		
	}

}
