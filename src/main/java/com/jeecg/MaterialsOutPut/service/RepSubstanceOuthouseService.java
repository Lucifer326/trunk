package com.jeecg.MaterialsOutPut.service;

import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutQueryVo;
import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import org.jeecgframework.core.common.service.CommonService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author 甄磊超
 * @date 2019/4/2
 * @Description com.jeecg.MaterialsOutPut.service
 */
public interface RepSubstanceOuthouseService extends CommonService {

    /**
     * 根据条件查询物资出库和出库详细信息
     * @param vo 封住装查询条件
     * @return 返回查询结果
     */
    List<Map<String,Object>> getAllByCondition(MaterialsOutPutQueryVo vo);

    /**
     * 获取所有未被删除的物资出库和物资详细信息
     * @return
     */
    List<Map<String,Object>> getAll();

    /**
     *根据物资入库的id获取物资出库和出库详细信息
     * @param uuid
     * @return
     */
    Map<String,Object> getDetilById(String uuid);

    /**
     * 修改物资出库信息
     * @param outhouse 物资出库单信息
     * @param warehouseId 出库仓库id
     * @param outhouseAmounts 出库数量
     * @param subIds 物资信息id
     */
    void updOuthouse(RepSubstanceOuthouse outhouse,String warehouseId,Long[] outhouseAmounts, String[] subIds,String[] storageDetails,String originalFilename,String path);

    /**
     *新增出库信息
     * @param outhouse 物资出库单信息
     * @param warehouseId 出库仓库id
     * @param outhouseAmounts 出库数量
     * @param subIds 物资信息id
     * @return 是否成功
     */
    void addOutHose(RepSubstanceOuthouse outhouse,String warehouseId,Long[] outhouseAmounts, String[] subIds,String[] storageDetails,String originalFilename,String path);

    /**
     * 批量删除
     * @param uuids 以分隔符号连接的主键字符串
     * @param regx 分割符号,默认分隔符为逗号
     */
    void rmvOutPut(String uuids,String regx);


    /**
     * 审批出库单
     * @param outhoseId 出库主键
     * @param approvalStatus 审批状态
     * @param approver 审批人
     * @param approvalOpinion 审批意见
     */
    void approval(String outhoseId, String approvalStatus, String approver, String approvalOpinion);

    /**
     * 附件名字的查询
     * wangguoqiang
     * @param accessoryId
     * @return
     */
    RepSubstanceAccessory findAccessoryById(String accessoryId);
}
