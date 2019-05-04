package com.jeecg.system_region.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import com.jeecg.system_region.entity.SystemRegionEntity;

@Repository
public interface SystemRegionMiniDao {
	
	List<SystemRegionEntity> getAll();
	
	@Arguments({"pid"})
	List<SystemRegionEntity> getChildren(String pid);

}
