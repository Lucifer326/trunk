package org.jeecgframework.web.system.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypeUnlessRegionEntity;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.springframework.stereotype.Repository;

import com.jeecg.system_region.entity.SystemRegionEntity;

@Repository
@MiniDao
public interface TypeMiniDao {
	
	List<SystemRegionEntity> getAll();
	
	@Arguments({"typegroupId"})
	List<TSType> getAllTypes(String typegroupId);
	
	@Arguments({"typePid"})
	List<TSType> getChildren(String typePid);
	//查找
	@Arguments({"typegroupeId","typeId"})
	List<TSTypeUnlessRegionEntity> getRegionId(String typegroupeId,String typeId);
	//删除
	@Arguments({"typeId","dictionaryId"})
	int deletedRegionId(String typeId,String dictionaryId);
	
	@Arguments({"typegroupId"})
	List<TSType> getItems(String typegroupId);
	
	@Arguments({"typegroupid","regionId"})
	List<TSType> getItemsByTypeIdAndRegionId(String typegroupid,String regionId);
	
	@Arguments({"typecode","typegroupid","regionId"})
	List<TSType> getItemsByTypeIdAndRegionIdAndItemKey(String typecode,String typegroupid,String regionId);
	
	@Arguments({"typegroupcode","regionId"})
	List<TSType> getItemsByTypegroupcodeAndRegionId(String typegroupcode,String regionId);
}
