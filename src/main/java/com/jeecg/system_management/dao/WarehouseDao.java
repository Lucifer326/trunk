package com.jeecg.system_management.dao;

import com.jeecg.system_management.common.WarehouseQueryVo;
import com.jeecg.system_management.pojo.RepAccessRepository;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;

/**
 * 仓储机构信息管理Dao
 *
 * @author 苑希阳
 */
@MiniDao
public interface WarehouseDao {

    /**
     * 查询仓储机构列表
     *
     * @param userId 用户id,需要根据此id做角色业务数据权限
     * @return
     */
    @Arguments("userId")
    List<RepAccessRepository> repositoryList(String userId);

    /**
     * 按条件查询库房列表
     *
     * @param repositoryName
     * @param activateTimeStart
     * @param activateTimeEnd
     * @param repositoryCategory
     * @param userId 用户id,需要根据此id做角色业务数据权限
     * @return
     */
    @Arguments({"repositoryName", "activateTimeStart", "activateTimeEnd", "repositoryCategory", "userId"})
    List<RepAccessRepository> repositoryListByQuery(String repositoryName, String activateTimeStart, String activateTimeEnd, String repositoryCategory, String userId);

    /**
     * 根据id查找仓库
     *
     * @param warhouseId
     * @return
     */
    @Arguments("warhouseId")
    RepAccessRepository repositoryFindById(String warhouseId);

    /**
     * 根据库房名称和库房区划查询库房
     *
     * @param repository
     * @return
     */
    @Arguments("repository")
    int countFindByNameAndArea(RepAccessRepository repository);

    /**
     * 根据库房编号查询库房
     *
     * @param repository
     * @return
     */
    @Arguments("repository")
    int countFindByNum(RepAccessRepository repository);
}
