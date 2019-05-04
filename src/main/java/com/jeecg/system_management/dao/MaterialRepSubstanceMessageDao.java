package com.jeecg.system_management.dao;

import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * 物资信息管理Dao
 * @author 王国强
 */
@MiniDao
public interface MaterialRepSubstanceMessageDao extends IGenericBaseCommonDao{
    /**
     * 获取物资类别集合 显示到选择按钮菜单后的小窗口
     * @return List
     */
    @Arguments({"userId"})
    List<RepSubstanceCategory> findAllRepSubstanceCategory(String userId);
    /**
     * 查询数据库，获得所有的物资信息
     * @return List
     */
    @Arguments({"userId"})
    List<Map<String,Object>> findAllRepSubstanceMessage(String userId);
    /**
     * 查看物资信息根据id
     * @param uuid
     * @return RepSubstanceMessage
     */
    @Arguments({"RSMid"})
    RepSubstanceMessage findRepSubstanceMessageById(String uuid);
    /**
     * 查询物资类别id根据名称
     * @param subCategory
     * @return RepSubstanceCategory
     */
    @Arguments({"subCategory"})
    RepSubstanceCategory findRepSubstanceCategoryByName(String subCategory);
    /**
     * 查询物资类别名称根据ID
     * @param categoryUuid
     * @return RepSubstanceCategory
     */
    @Arguments({"categoryUuid"})
    RepSubstanceCategory findRepSubstanceCategoryById(String categoryUuid);
    /**
     * 根据条件查询物资信息
     * @param subNumber
     * @param subName
     * @param subCatagory
     * @return List
     */
    @Arguments({"subNumber","subName","subCatagory","userId"})
    public List<Map<String, Object>> searchRepSubstanceMessage(String subNumber, String subName, String subCatagory,String userId);
}


