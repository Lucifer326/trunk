package com.jeecg.MaterialProducer.dao;

import com.jeecg.MaterialProducer.entity.MaterialPurchaseRecordEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;

/**
 * 购买记录dao层接口
 *@author 宋超
 */
@MiniDao
public interface MaterialPurchaseRecordDao {

    /**
     * 查询列表
     * @return 购买记录列表
     */
    List<MaterialPurchaseRecordEntity> list();

    /**
     * 条件查询
     * @param productCategory 物品种类
     * @param buyFromTime 购买时间
     * @param buyToTime 结束时间
     * @return 符合条件的购买记录集合
     */
    @Arguments({"productCategory", "buyFromTime", "buyToTime"})
    List<MaterialPurchaseRecordEntity> search(String productCategory, String buyFromTime, String buyToTime);
}
