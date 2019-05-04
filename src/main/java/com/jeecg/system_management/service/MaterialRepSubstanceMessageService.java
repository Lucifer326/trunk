package com.jeecg.system_management.service;

import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;

import java.util.List;
import java.util.Map;
/**
 * 物资信息管理Service实现类
 * @author 王国强
 */
public interface MaterialRepSubstanceMessageService extends CommonService {
    /**
     * 获取物资类别集合 显示到选择按钮菜单后的小窗口
     * @return List
     */
    List<RepSubstanceCategory> findAllRepSubstanceCategory();
    /**
     * 获取所有的物资信息，返回list集合给前台
     * @return List
     */
    List<Map<String,Object>> findAllRepSubstanceMessage();
    /**
     * 保存物资信息功能
     * @param repSubstanceMessage
     * @return AjaxJson
     */
    AjaxJson saveRepSubstanceMessage(RepSubstanceMessage repSubstanceMessage);
    /**
     * 查询物资根据id
     * @param uuid
     * @return RepSubstanceMessage
     */
    RepSubstanceMessage findRepSubstanceMessageById(String uuid);
    /**
     * 根据id更新物资信息
     * @param repSubstanceMessage
     * @return AjaxJson
     */
    AjaxJson updateRepSubstanceMessage(RepSubstanceMessage repSubstanceMessage);
    /**
     * 删除物资信息
     * @param uuids
     * @return int
     */
    int deleteRepSubstanceMessage(String uuids);
    /**
     * 查询物资类别id根据名称
     * @param subCategory
     * @return RepSubstanceCategory
     */
    RepSubstanceCategory findRepSubstanceCategoryByName(String subCategory);
    /**
     * 查询物资类别名称根据id
     * @param categoryUuid
     * @return RepSubstanceCategory
     */
    RepSubstanceCategory findRepSubstanceCategoryById(String categoryUuid);
    /**
     * 搜索物资根据给定的条件
     * @param subNumber
     * @param subName
     * @param subCatagory
     * @return List
     */
    List<Map<String,Object>> searchRepSubstanceMessage(String subNumber,String subName,String subCatagory);
}
