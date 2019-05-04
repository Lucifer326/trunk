package com.jeecg.material_scrap.service.impl;

import com.jeecg.material_scrap.dao.ScrapDao;
import com.jeecg.material_scrap.entity.Scrap;
import com.jeecg.material_scrap.entity.ScrapLookTwoInformation;
import com.jeecg.material_scrap.service.ScrapService;
import com.jeecg.system_management.dao.MaterialRepSubstanceMessageDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ScrapService")
/**物资报废管理 Service的实现类
 * @author 任小昆
 */
public class ScrapServiceImpl extends CommonServiceImpl implements ScrapService {

    @Autowired
    public ScrapDao scrapDao;
    @Autowired
    private TypeServiceI typeServiceImpl;
    @Autowired
    private MaterialRepSubstanceMessageDao materialRepSubstanceMessageDao;
    /**
     * 显示报废的物品列表
     * @return
     */
    @Override
    public List<Scrap> showScrapList() {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return scrapDao.showScrapList(tsUser.getId());
    }

    /**
     * 插入数据
     * @param scrap
     * @param list
     */
    @Override
    public void insertData(Scrap scrap, List<ScrapLookTwoInformation> list) {
        super.save(scrap);
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                // 补全对象属性
                list.get(i).setRepositoryid(scrap.getUuid());
                super.save(list.get(i));
            }
        }
    }

    /**
     * 查询单据
     * @param scrapNumber
     * @return
     */
    @Override
    public List<Scrap> selectNumber(String scrapNumber,String wareHouse) {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return scrapDao.selectNumber(scrapNumber,wareHouse,tsUser.getId());
    }

    /**
     * 查看数据
     * @param uuid
     * @return
     */
    @Override
    public Scrap lookData(String uuid) {
        return scrapDao.lookData(uuid);
    }

    @Override
    public void testUpdateData(Scrap scrap,List<ScrapLookTwoInformation> list) {
        if (scrap!=null){
            Scrap scraps = scrapDao.lookData(scrap.getUuid());
            if (scraps!=null){
                scrap.setCreateBy(scraps.getCreateBy());
                scrap.setCreateDate(scraps.getCreateDate());
                scrap.setCreateName(scraps.getCreateName());
                scrap.setIsDeleted(scraps.getIsDeleted());
                scrap.setCompanyCode(scraps.getCompanyCode());
                scrap.setOrganizationCode(scraps.getOrganizationCode());
                super.saveOrUpdate(scrap);
                for(int i = 0 ;i<list.size();i++){
                    ScrapLookTwoInformation storage = list.get(i);
                    super.saveOrUpdate(storage);
                }
            }
        }
    }
    /**
     * 编辑数据
     * @param uuid
     * @return
     */
    @Override
    public Scrap editData(String uuid) {

        return scrapDao.editData(uuid);
    }

    /**
     * 物资信息的回显
     * @param uuid
     * @return
     */
    @Override
    public List<Map<String,Object>> scrapTwoLook(String uuid) {
        return scrapDao.scrapTwoLook(uuid);
    }

    /**
     * 删除报废的商品
     * @param uuid
     */
    @Override
    public void clearData(String[] uuid) {
        String param = "";
        for (int i = 0; i < uuid.length - 1; i++) {
            param += "?,";
        }
        param += "?";
        String sql = "update REP_SUBSTANCE_SCRAP set ISDELETED = 1 where UUID in (" + param + ")";
        System.out.println("sql1:" + sql);
        super.executeSql(sql, uuid);
    }

    /**
     * 获取对象
     *
     * @param uuid
     */
    @Override
    public Scrap readyData(String uuid) {
        Scrap scrap = findUniqueByProperty(Scrap.class, "uuid", uuid);
        return scrap;
    }

    @Override
    /**
     * @author wangguoqiang
     *
     */
    public List<Map<String, Object>> repositoryByStorenum(String storenum) {
        return scrapDao.repositoryByStorenum(storenum);
    }

    @Override
    /**
     * @author wangguoqiang
     */
    public List<Map<String, Object>> findRepSubstanceMessageBystorenum(String storenum) {
        return scrapDao.findRepSubstanceMessageBystorenum(storenum);
    }

    @Override
    public List<Map<String,Object>> findScarpDetail(String reviewId) {
        return scrapDao.findScarpDetail(reviewId);
    }

    @Override
    public Scrap findScarpById(String reviewId) {
        return scrapDao.findScarpById(reviewId);
    }

    @Override
    public void updatecount(List<Map<String,Object>> information) {
        String sql = "UPDATE REP_STORAGE_DETAIL SET AMOUNT=AMOUNT-? WHERE NUMBERID =? AND MESSAGEID=?";
        if(information.size()>0){
            for (int i = 0 ;i<information.size();i++){
                String storageid = (String)information.get(i).get("STORAGEID");
                Map<String,Object> map = scrapDao.findStorageid(storageid);
                String  subname = (String)information.get(i).get("SUBNAME");
                super.executeSql(sql,information.get(i).get("SCRAPTOTAL"),map.get("UUID"),information.get(i).get("MESSAGEID"));
            }
        }
    }

    @Override
    public void updateScarp(Scrap scrap) {
        super.saveOrUpdate(scrap);
    }

    @Override
    public Map<String, Object> findStoreNum(String uuid) {
        return scrapDao.findStoreNum(uuid);
    }

}
