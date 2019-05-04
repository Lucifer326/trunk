package com.jeecg.system_management.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;
import java.util.Map;

@MiniDao
/**
 * 救灾物资库存管理Dao
 *
 * @author 王国强
 */
public interface MaterialReliefFormDao {

    /**
     * 查询所有的库存信息
     * @return List<Map<String, Object>>
     */
    @Arguments({"userId"})
    List<Map<String, Object>> findMaterialReliefFormList(String userId);
    /**
     * 根据前台传来的仓库级别或者仓库名称模糊查询所有信息
     * @param organizationClassify
     * @param repositoryName
     * @return List
     */
    @Arguments({"organizationClassify","repositoryName","userId"})
    List<Map<String, Object>> findMaterialReliefFormListLike(String organizationClassify, String repositoryName,String userId);
}
