package com.jeecg.rep_substance_recycle.service;

import com.jeecg.MaterialsInPut.entity.RepStorageDetailEntity;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import com.jeecg.rep_substance_recycle.entity.RepDetailedMaterialRecoveryEntity;
import com.jeecg.rep_substance_recycle.entity.RepSubstanceRecycleEntity;
import com.jeecg.rep_substance_recycle.vo.RecycleVo;

import java.util.List;
import java.util.Map;

/**
 * @author 刘雨泽
 */
public interface RecycleService {

    /**
     * 查询物资回收信息列表
     * @return
     */
    List<RepSubstanceRecycleEntity> recycleList();

    /**
     * 根据条件查询物资回收信息列表
     * @param vo 其他查询条件
     * @return
     */
    List<RepSubstanceRecycleEntity> conditionList(RecycleVo vo);

    /**
     * 根据uuid查询物资回收信息
     * @param uuid
     * @return
     */
    RepSubstanceRecycleEntity lookRecycle(String uuid);

    /**
     * 根据uuid删除物资回收信息
     * @param uuid
     */
    void deleteRecycles(String[] uuid);

    /**
     * 根据uuid查询物资回收信息列表
     * @param uuid
     * @return
     */
    List<Map<String, Object>> recyclingDetailsGet(String uuid);

    /**
     * 新增物资回收信息和入库信息
     * @param recycle
     * @param list
     */
    void addRecycleAndMaterialRecovery(RepSubstanceRecycleEntity recycle, List<RepStorageDetailEntity> list);

    /**
     * 修改物资回收信息和入库信息
     * @param recycle
     * @param list
     */
    void updateRecycleAndMaterialRecovery(RepSubstanceRecycleEntity recycle, List<RepStorageDetailEntity> list);

    /**
     * 根据动态出入库条件查询调拨通知列表
     * @param input 入库
     * @param output 出库
     * @param isUse 是否使用
     * @return
     */
    List<Map<String,Object>> seachAllocation(String input, String output, Short isUse);

    /**
     * 查询有效的出库单
     * @return
     */
    List<Map<String,Object>> getAllOutHouse();

    /**
     * 根据根据出库单id查询出库单
     * @param outhouseId
     * @return
     */
    RepSubstanceOuthouse searchNumberByUuid(String outhouseId);

    /**
     * 修改审批
     * @param uuid 回收id
     * @param approvalStatus 审批状态
     * @param realName 审批人
     * @param approvalOpinion 审批意见
     */
    void approval(String uuid, String approvalStatus, String realName, String approvalOpinion);

    /**
     * 模糊查询根据出库单获得物资信息
     * @param subNumber
     * @param subName
     * @param subCatagory
     * @param outHouseId
     * @return
     */
    List<Map<String,Object>> searchRepSubstanceMessage(String subNumber, String subName, String subCatagory, String outHouseId);

/*    RepSubstanceRecycleEntity recyclingEdit(String uuid);

    List<Map<String, Object>> recoveryEdit(String uuid);*/
}
