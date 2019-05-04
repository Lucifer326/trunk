package org.jeecgframework.web.system.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.springframework.stereotype.Repository;

@Repository
@MiniDao
public interface TypegroupMiniDao {
	
	List<TSTypegroup> getAll();
	
	@Arguments({"parentId"})
	List<TSTypegroup> getChildren(String parentId);

	@Arguments({"typegroupcode"})
	TSTypegroup getDictionaryType(String typegroupcode);

	@Arguments({"typeCode"})
	int getCountNum(String typeCode);
	
	@Arguments({"typeCode","id"})
	int getCountNumUpdate(String typeCode,String id);
	

}
