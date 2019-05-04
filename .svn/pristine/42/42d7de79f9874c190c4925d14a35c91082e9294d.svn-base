package com.jeecg.system_management.service;

import com.jeecg.system_management.common.MaterialCategoryVo;
import com.jeecg.system_management.pojo.RepSubstanceCategory;
import org.jeecgframework.core.common.model.json.AjaxJson;

import java.util.List;

/**
 * 物资类别管理service
 *
 * @author 苑希阳
 */
public interface MaterialCategoryService {
    /**
     * 获取物资类别列表
     *
     * @return categoryList
     */
    List<RepSubstanceCategory> materialCategoryList();

    /**
     * 新增物资类别
     *
     * @param category 物资类别
     * @return ajaxJson
     */
    AjaxJson materialCategorySave(RepSubstanceCategory category);

    /**
     * 查找uuid对应的物资类别
     *
     * @param uuid
     * @return category
     */
    RepSubstanceCategory materialCategorySee(String uuid);

    /**
     * 物资类别修改
     *
     * @param category 物资类别
     * @return ajaxJson
     */
    AjaxJson materialCategoryModify(RepSubstanceCategory category);

    /**
     * 物资类别删除，支持批量删除
     *
     * @param ids
     * @return ajaxJson
     */
    AjaxJson materialCategoryDelete(String ids);

    /**
     * 条件查询
     *
     * @param vo 查询条件
     * @return categoryList
     */
    List<RepSubstanceCategory> materialCategoryByQuery(MaterialCategoryVo vo);

    /**
     * 校验物资类别名称唯一性
     *
     * @param categoryName
     * @return
     */
    AjaxJson checkCategoryName(String categoryName);
}
