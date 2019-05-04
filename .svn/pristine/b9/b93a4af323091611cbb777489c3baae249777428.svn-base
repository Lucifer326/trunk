package com.jeecg.material_scrap.service;

import com.jeecg.material_scrap.entity.Scrap;
import com.jeecg.material_scrap.entity.ScrapLookTwoInformation;
import org.jeecgframework.core.common.service.CommonService;
import java.util.List;
import java.util.Map;

/**物资报废管理 Service
 * @author 任小昆
 */
public interface ScrapService extends CommonService {
    /**
     * 显示报废的物品列表
     * @return
     */
    List<Scrap> showScrapList();


    /**
     * 插入数据
     * @param scrap
     * @param list
     */
    void insertData(Scrap scrap, List<ScrapLookTwoInformation> list);


    /**
     * 查询单据
     * @param scrapNumber
     * @return
     */

    List<Scrap> selectNumber(String scrapNumber,String wareHouse);

    /**
     * 查看数据
     * @param uuid
     * @return
     */
    Scrap lookData(String uuid);

    /**
     * 更新数据
     * @param scrap,list
     *
     *
     */
    void testUpdateData(Scrap scrap,List<ScrapLookTwoInformation> list);


    /**
     * 编辑数据
     * @param uuid
     * @return
     */
    Scrap editData(String uuid);

    /**
     * 物资信息的回显
     * @param uuid
     * @return
     */
    List<Map<String,Object>> scrapTwoLook(String uuid);

    /**
     * 删除报废的商品
     * @param uuid
     */

    void clearData(String[] uuid);

    /**
     * 审批前的数据准备
     *
     * @param uuid 物资报废所对应的主键
     */
    Scrap readyData(String uuid);





    /**
     * 审批
     * @param uuid 物资报废所对应的主键

     */
    //void approval(String uuid);
    /**
     * 审批出库单
     * @param outhoseId 出库主键
     * @param approvalStatus 审批状态
     * @param approver 审批人
     * @param approvalOpinion 审批意见
     */
   //void approval(String outhoseId, String approvalStatus, String approver, String approvalOpinion);
    /**
     * @author wangguoqiang
     * @param storenum
     */
    List<Map<String,Object>> repositoryByStorenum(String storenum );
    /**
     * @author wangguoqiang
     * @param storenum
     */
    List<Map<String, Object>> findRepSubstanceMessageBystorenum(String storenum);
    /**
     *wangguoqiang
     */
    //为审批提供数据，去修改库存量
    List<Map<String,Object>> findScarpDetail( String reviewId);
    Scrap findScarpById(String reviewId);
    //审批后去数据库修改库存数量
    void updatecount(List<Map<String,Object>> information);

    void  updateScarp(Scrap scrap);
    //获取出库单回显
    Map<String,Object> findStoreNum(String uuid);
}
