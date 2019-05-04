package com.jeecg.MaterialProducer.service;

import com.jeecg.MaterialProducer.entity.ScProductEntity;

import java.util.List;

/**
 * 物资生产商名录service层接口
 *@author 宋超
 */
public interface MaterialProducerService {
    /**
     * 查询所有生产商
     * @return
     */
    List<ScProductEntity> productList();

    /**
     * 根据id查询生产商
     * @param uuid
     * @return
     */
    ScProductEntity lookProductById(String uuid);

    /**
     * 修改
     * @param scProductEntity
     */
    void productUpd(ScProductEntity scProductEntity);

    /**
     * 删除
     * @param ids
     */
    void productDel(String[] ids);

    /**
     * 新增
     * @param scProductEntity
     */
    void productIns(ScProductEntity scProductEntity);

    /**
     * 条件查询
     * @param name 公司名称
     * @param product 主营产品
     * @param inPut 是否录入
     * @return
     */
    List<ScProductEntity> productQuery(String name, String product, String inPut);

}
