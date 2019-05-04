package com.jeecg.MaterialsOutPut.service;

import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import org.jeecgframework.core.common.service.CommonService;

import java.util.List;
import java.util.Map;

/**
 * @author 甄磊超
 * @date 2019/4/2
 */
public interface RepOuthouseDetailService extends CommonService {

    /**
     * 根据出库单主键获取对应的出库详细 出库单 物资 物资类别 入库单 入库单详细 调拨单信息
     * @param outhouseId 出库单主键
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getByOuthouseId(String outhouseId);

    /**
     * 根据出库详细id获取 出库详细 出库单 物资 物资类别 入库单 入库单详细 调拨单信息
     * @param uuid 出库详细的主键
     * @return
     */
    List<Map<String,Object>> getByDetilId(String uuid);
    /**
     * @author 王国强
     * 根据调拨单查询对相应的物资信息
     */
    List<Map<String,Object>> findAllRepSubstanceMessage(String uuid);

}
