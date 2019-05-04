package com.jeecg.MaterialsOutPut.dao;

import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author 甄磊超
 * @date 2019/4/2
 * @Description com.jeecg.MaterialsOutPut.dao
 */
@Repository("repOuthouseDetailMiniDao")
public interface RepOuthouseDetailMiniDao {

    /**
     * 根据出库单主键获取对应的出库详细 出库单 物资 物资类别 入库单 入库单详细 调拨单信息
     * @param outhouseid 出库单主键
     * @return List<Map<String,Object>>
     */
    @Arguments({"outhouseid"})
    List<Map<String,Object>> getByOuthouseId(String outhouseid);


    /**
     * 根据出库详细id获取 出库详细 出库单 物资 物资类别 入库单 入库单详细 调拨单信息
     * @param uuid 出库详细的主键
     * @return
     */
    @Arguments({"uuid"})
    List<Map<String,Object>> getByDetilId(String uuid);

    /**
     * @author wangguoqiang
     *根据调拨单查询对应的物资
     */
    @Arguments({"uuid"})
    List<Map<String,Object>> findRepSubstanceMessageById(String uuid);

}
