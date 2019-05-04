package com.jeecg.MaterialsInPut.service.impl;

import com.jeecg.MaterialsInPut.common.MaterialsInPutVo;
import com.jeecg.MaterialsInPut.common.RepSubstanceMessageVo;
import com.jeecg.MaterialsInPut.dao.MaterialsInputMiniDao;
import com.jeecg.MaterialsInPut.entity.RepStorageDetailEntity;
import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsInPut.entity.RepSubstanceStorageEntity;
import com.jeecg.MaterialsInPut.service.MaterialsInputService;
import com.jeecg.allocation_notice.entity.RepAllotInform;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = java.lang.Exception.class)
public class MaterialsInputServiceImpl extends CommonServiceImpl implements MaterialsInputService {

    @Resource
    private MaterialsInputMiniDao materialsInputMiniDao;

    /**
     * 物资入库列表
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAll(MaterialsInPutVo vo) {
        if (vo == null) {
            vo = new MaterialsInPutVo();
        }
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return materialsInputMiniDao.queryAll(vo, tsUser.getId());
    }

    /**
     * 物资入库详细和物资信息
     * @return
     */
    @Override
    public List<Map<String, Object>> inStockLook(String numberid) {
        List<Map<String, Object>> list = materialsInputMiniDao.inStockLook(numberid);
        return list;
    }

    /**
     * 根据id查询物资入库
     * @param uuid
     * @return
     */
    @Override
    public Map<String, Object> queryOne(String uuid) {
        return materialsInputMiniDao.queryOne(uuid);
    }

    /**
     * 根据id查询物资入库(返回一个物资入库对象)
     *
     * @param uuid
     * @return
     */
    @Override
    public RepSubstanceStorageEntity queryOneEntityById(String uuid) {
        return materialsInputMiniDao.queryOneStorageEntityById(uuid);
    }

    /**
     * 填充repSubstanceStorageEntity对象
     */
    public RepSubstanceStorageEntity fillRepSubstanceStorageEntity(RepSubstanceStorageEntity repSubstanceStorageEntity) {
        RepSubstanceStorageEntity repSubstanceStorageEntity1 = materialsInputMiniDao.queryOneStorageEntityById(repSubstanceStorageEntity.getUuid());
        repSubstanceStorageEntity.setCreateName(repSubstanceStorageEntity1.getCreateName());
        repSubstanceStorageEntity.setCreateBy(repSubstanceStorageEntity1.getCreateBy());
        repSubstanceStorageEntity.setCreateDate(repSubstanceStorageEntity1.getCreateDate());
        repSubstanceStorageEntity.setSysCompanyCode(repSubstanceStorageEntity1.getSysCompanyCode());
        repSubstanceStorageEntity.setSysOrgCode(repSubstanceStorageEntity1.getSysOrgCode());
        repSubstanceStorageEntity.setIsdeleted(repSubstanceStorageEntity1.getIsdeleted());
        repSubstanceStorageEntity.setBpmStatus(repSubstanceStorageEntity1.getBpmStatus());
        return repSubstanceStorageEntity;
    }

    /**
     * 新增物资入库和物资入库详细
     */
    @Override
    public void saveOrupdateInput(RepSubstanceStorageEntity repSubstanceStorageEntity, String[] minuteids, String[] messageids, String[] prices, String[] outputAmounts,
                                  String[] purchaseDates, String[] manufactureDates, String[] overinsuredDates, String delUuid, String ctrl) {
        AjaxJson ajaxJson = new AjaxJson();

        //验证数组长度相同且不为空
        if (repSubstanceStorageEntity == null || messageids == null || messageids.length != prices.length ||
                messageids.length != outputAmounts.length || messageids.length != purchaseDates.length ||
                messageids.length != manufactureDates.length || messageids.length != overinsuredDates.length) {
            throw new BusinessException("数据输入不完整不能保存");
        }

        //生成uuid(用于物资入库uuid)
        String generate = UUIDGenerator.generate();

        //如果
        if (StringUtils.isNotBlank(delUuid)) {
            //分割需要删除的uuid放入数组中
            String[] delUuids = delUuid.split("-");
            String param = "";
            for (int i = 0; i < delUuids.length - 1; i++) {
                param += "?,";
            }
            param += "?";
            String sql = "update REP_STORAGE_DETAIL set ISDELETED = 1 where UUID in (" + param + ")";
            super.executeSql(sql, delUuids);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //遍历参数放入物资入库详细对象中
        for (int i = 0; i < messageids.length; i++) {
            RepStorageDetailEntity repStorageDetailEntity;
            repStorageDetailEntity = new RepStorageDetailEntity();
            //物资入库详细uuid
            if (minuteids != null && !(minuteids.length <= i)) {
                repStorageDetailEntity = materialsInputMiniDao.queryOneDetailEntityById(minuteids[i]);
            }
            //物资入库uuid
            if ("save".equals(ctrl)) {
                //如果是新增,将新的物资入库uuid保存,
                repStorageDetailEntity.setNumberid(generate);
            } else {
                //如果是修改,
                repStorageDetailEntity.setNumberid(repSubstanceStorageEntity.getUuid());
            }

            //验证物资信息uuid
            if ("undefined".equals(messageids[i])) {
                throw new BusinessException("物资信息错误,请重新选择");
            }
            
            //物资信息uuid
            repStorageDetailEntity.setMessageid(messageids[i]);

            //验证单价
            if ("undefined".equals(prices[i])) {
                throw new BusinessException("物资信息错误,请重新选择");
            }
            //入库数量
            if (StringUtils.isBlank(outputAmounts[i])) {
                throw new BusinessException("入库数量不能为空");
            }
            if (outputAmounts[i].length() > 9) {
                throw new BusinessException("入库数量必须小于等于999999999");
            }
            if (Integer.parseInt(outputAmounts[i]) <= 0) {
                throw new BusinessException("入库数量必须大于等于0");
            }
            if(repStorageDetailEntity.getOutputAmount() == null || repStorageDetailEntity.getOutputAmount().toPlainString().equals(repStorageDetailEntity.getAmount())) {
                //入库数量
                repStorageDetailEntity.setOutputAmount(new BigDecimal(outputAmounts[i]));
                //库存量
                repStorageDetailEntity.setAmount(outputAmounts[i]);
            }
            //购置日期验证
            if (StringUtils.isBlank(purchaseDates[i])) {
                throw new BusinessException("购置日期不能为空");
            }
            //生产日期验证
            if (StringUtils.isBlank(manufactureDates[i])) {
                throw new BusinessException("生产日期不能为空");
            }
            //过保日期验证
            if (StringUtils.isBlank(overinsuredDates[i])) {
                throw new BusinessException("过保日期不能为空");
            }
            //购置日期
            Date purchaseDate = DateUtils.str2Date(purchaseDates[i], dateFormat);
            //生产日期
            Date manufactureDate = DateUtils.str2Date(manufactureDates[i], dateFormat);
            //过保日期
            Date overinsuredDate = DateUtils.str2Date(overinsuredDates[i], dateFormat);

            //验证时间先后顺序
            if (manufactureDate.compareTo(overinsuredDate) > 0) {
                throw new BusinessException("生产日期不能大于过保日期");
            }
            if (purchaseDate.compareTo(overinsuredDate) > 0) {
                throw new BusinessException("购置日期不能大于过保日期");
            }
            if (manufactureDate.compareTo(purchaseDate) > 0) {
                throw new BusinessException("生产日期不能大于购置日期");
            }

            //购置日期
            repStorageDetailEntity.setPurchaseDate(purchaseDate);
            //生产日期
            repStorageDetailEntity.setManufactureDate(manufactureDate);
            //过保日期
            repStorageDetailEntity.setOverinsuredDate(overinsuredDate);

            //总价
            repStorageDetailEntity.setTotalPrices(new BigDecimal(prices[i]).multiply(new BigDecimal(outputAmounts[i])));
            //是否删除
            repStorageDetailEntity.setIsdeleted((short) 0);
            //状态(新:0, 回收:1, 报废2)
            repStorageDetailEntity.setStatus((short) 0);

            //保存物资入库详细信息
            super.saveOrUpdate(repStorageDetailEntity);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("保存成功");
        }

        if ("save".equals(ctrl)) {
            //新增
            //设置uuid
            repSubstanceStorageEntity.setUuid(generate);
            //是否删除
            repSubstanceStorageEntity.setIsdeleted((short) 0);
            //保存物资入库信息
            super.save(repSubstanceStorageEntity);
        } else {
            //修改
            //查询数据填充到对象中
            repSubstanceStorageEntity = this.fillRepSubstanceStorageEntity(repSubstanceStorageEntity);
            //修改物资入库信息
            super.updateEntitie(repSubstanceStorageEntity);
        }
    }

    @Override
    public String saveAccessoryMsg(RepSubstanceAccessory accessory) {
        //新增
        super.save(accessory);
        //返回附件信息uuid
        return accessory.getUuid();
    }

    /**
     * 修改
     * @param repSubstanceStorageEntity
     */
	/*@Override
	public void updInStock(RepSubstanceStorageEntity repSubstanceStorageEntity) {

	}*/

    /**
     * 批量删除
     * @param delUuids
     */
    @Override
    public void delBatch(String[] delUuids) {
        for (int i = 0; i < delUuids.length; i++) {
            //修改物资入库信息的isDeleted状态为1，表示未删除
            RepSubstanceStorageEntity storageEntity = materialsInputMiniDao.queryOneStorageEntityById(delUuids[i]);
            storageEntity.setIsdeleted((short) 1);
            super.saveOrUpdate(storageEntity);

            //修改物资入库详情的isDeleted状态为1，表示未删除
            List<RepStorageDetailEntity> detailEntitys = materialsInputMiniDao.queryDetailEntityById(storageEntity.getUuid());
            for (RepStorageDetailEntity repStorageDetailEntity : detailEntitys) {
                repStorageDetailEntity.setIsdeleted((short) 1);
                super.saveOrUpdate(repStorageDetailEntity);
            }
        }
    }

    /**
     * 根据物资入库详细id查询物资入库详细和物资信息
     */
    @Override
    public Map<String, Object> queryMinute(String uuid) {
        return materialsInputMiniDao.queryMinute(uuid);
    }

    /**
     * 根据仓库id查询物资入库详细和入库单号
     */
    @Override
    public List<Map<String, Object>> queryDetail(String warehouseId, String subId) {
        return materialsInputMiniDao.getDetail(warehouseId, subId);
    }

    @Override
    public RepSubstanceAccessory findFileById(String uuid) {
        return materialsInputMiniDao.fileFindById(uuid);
    }

    /**
     * 根据调拨通知id查询调拨单物资
     * @param informid
     * @return
     */
    @Override
    public List<Map<String, Object>> querySubstance(String informid) {
        return materialsInputMiniDao.querySubstance(informid);
    }

    /**
     * 调拨通知查询列表
     * @param input 入库
     * @param output 出库
     * @return
     */
    @Override
    public List<Map<String, Object>> seachAllocation(String input, String output) {
        return materialsInputMiniDao.seachAllocation(input, output);
    }

    /**
     * 根据调拨单据号查询调拨单信息
     */
    @Override
    public void updAllocation(String allotNumber) {
        RepAllotInform repAllotInform = materialsInputMiniDao.queryAllocation(allotNumber);
        if (repAllotInform != null) {
            repAllotInform.setAllotStatus("2");
            super.saveOrUpdate(repAllotInform);
        }
    }

    /**
     * 根据条件查询物资信息
     * @return List
     */
    @Override
    public List<Map<String, Object>> searchRepSubstanceMessage(RepSubstanceMessageVo repSubstanceMessageVo, String selUuids) {
        //如果封装查询条件对象为null则创建一个对象
        if (repSubstanceMessageVo == null) {
            repSubstanceMessageVo = new RepSubstanceMessageVo();
        }

        //去掉selUuids最后的','
        if (StringUtils.isNotBlank(selUuids)) {
            selUuids = selUuids.substring(0, selUuids.length() - 3);
        }

        return materialsInputMiniDao.searchRepSubstanceMessage(repSubstanceMessageVo, selUuids, ResourceUtil.getSessionUserName().getId());
    }
}
