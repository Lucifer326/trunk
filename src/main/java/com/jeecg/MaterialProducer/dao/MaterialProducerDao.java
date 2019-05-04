package com.jeecg.MaterialProducer.dao;

import com.jeecg.MaterialProducer.entity.ScProductEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;

/**
 * 物资生产商dao层接口
 *@author 宋超
 */

@MiniDao
public interface MaterialProducerDao {

    /**
     * 查询全部
     * @return 物资生产商列表
     */
    @Arguments({"userid"})
    List<ScProductEntity> productList(String userid);

    /**
     * 根据id查询
     * @param uuid
     * @return 当前id的信息
     */
    @Arguments({"uuid"})
    ScProductEntity lookProductById(String uuid);

    /**
     * 条件查询
     * @param name 公司名称
     * @param product 主营产品
     * @param inPut 是否录入民政部
     * @return 符合条件的物资生产商集合
     */
    @Arguments({"name", "product", "inPut","userId"})
    List<ScProductEntity> productQuery(String name, String product, String inPut,String userId);

    /**
     * 根据uuid删除
     * @param id
     */
    @Arguments({"id"})
    void productDelByUUID(String id);
}
