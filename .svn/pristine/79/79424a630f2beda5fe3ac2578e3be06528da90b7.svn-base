package com.jeecg.bssys_deploy.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import com.jeecg.bssys_deploy.entity.BssysDeployEntity;
import com.jeecg.bssys_deploy.entity.BssysDeploydisEntity;
@MiniDao
public interface BssysDeployDao {
	@Arguments({ "page", "rows", "whereStr", "orderStr" })
	List<BssysDeploydisEntity> getList(int page, int rows, String where, String order);

	@Arguments({ "whereStr" })
	int getCount(String where);
	
	@Arguments({ "depolyId", "disCode"})
	String getValue(String depolyId,String disCode);
	
	
	@Arguments({"code"})
	BssysDeployEntity getValueByCode(String code);
}
