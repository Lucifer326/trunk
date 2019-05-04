package com.jeecg.rep_substance_recycle.service.impl;

import com.jeecg.MaterialsInPut.entity.RepStorageDetailEntity;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutLogicException;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutStatusCode;
import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import com.jeecg.MaterialsOutPut.service.RepSubstanceOuthouseService;
import com.jeecg.rep_substance_recycle.dao.RecycleDao;
import com.jeecg.rep_substance_recycle.entity.RepSubstanceRecycleEntity;
import com.jeecg.rep_substance_recycle.service.RecycleService;
import com.jeecg.rep_substance_recycle.vo.RecycleVo;
import com.jeecg.system_management.common.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 刘雨泽
 */
@Service
public class RecycleServiceImpl extends CommonServiceImpl implements RecycleService {

    @Autowired
    private RecycleDao recycleDao;

    /**
     * 数据字典
     */
    @Autowired
    private TypeServiceI typeServiceImpl;

    /**
     * 物资出库 服务.
     */
    @Autowired
    private RepSubstanceOuthouseService repSubstanceOuthouseService;


    /**
     * 查询物资回收结果集
     * @return
     */
    @Override
    public List<RepSubstanceRecycleEntity> recycleList() {
        TSUser user = ResourceUtil.getSessionUserName();
        return recycleDao.recycleList(user.getId());
    }

    /**
     * 查询物资回收结果集 模糊查询
     * @param vo 查询条件
     * @return
     */
    @Override
    public List<RepSubstanceRecycleEntity> conditionList(RecycleVo vo) {
        TSUser user = ResourceUtil.getSessionUserName();
        String inDate = "";
        String outDate = "";
        if (vo != null){
            if (vo.getRecycleNumber() != null && !"".equals(vo.getRecycleNumber())){
                vo.setRecycleNumber(vo.getRecycleNumber().trim());
            }
            if (vo.getStorageWarehouse() != null && !"".equals(vo.getRecycleNumber())){
                vo.setStorageWarehouse(vo.getStorageWarehouse().trim());
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (vo.getInDate() != null){
                inDate = format.format(vo.getInDate());
            }
            if (vo.getOutDate() != null){
                outDate = format.format(vo.getOutDate());
            }
        }
        return recycleDao.conditionList(vo, inDate, outDate, user.getId());
    }

    /**
     * 查询物资回收信息
     * @param uuid
     * @return
     */
    @Override
    public RepSubstanceRecycleEntity lookRecycle(String uuid) {
        return recycleDao.lookRecycle(uuid);
    }

    /**
     * 删除物资回收
     * @param uuid 根据uuid删除
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRecycles(String[] uuid) {

        // 取到该id下有多少个物资回收详细物资 物资从表
        for(int i = 0; i < uuid.length; i++){
            String id = uuid[i];
            String sql = "update REP_STORAGE_DETAIL set ISDELETED = 1 where NUMBERID = ?";
            super.executeSql(sql, id);
        }

        // 修改物资回收表isdelete 物资主表
        String param = "";
        for (int i = 0; i < uuid.length - 1; i++){
            param += "?,";
        }
        param += "?";

        String sql = "update REP_SUBSTANCE_RECYCLE set ISDELETED = 1 where uuid in (" + param +")";
        super.executeSql(sql, uuid);

    }

    /**
     * 得到入库详细信息
     * @param uuid 物资回收信息的uuid
     * @return
     */
    @Override
    public List<Map<String, Object>> recyclingDetailsGet(String uuid) {
        // 获取回收单id
        RepSubstanceRecycleEntity recycleEntitie = recycleDao.recyclingDetailsGet(uuid);
        // 入库信息集合
        List<Map<String, Object>> materialInfos = new ArrayList<>();

        if (recycleEntitie != null){
            // 通过回收单id  获取入库信
            // 物资信息集合
            List<Map<String, Object>> warehouseInfos = recycleDao.warehouseInfosGet(recycleEntitie.getUuid());

            if (warehouseInfos != null){
                for (int j = 0; j< warehouseInfos.size(); j++){
                    // 物资信息
                    Map<String, Object> warehouse = warehouseInfos.get(j);
                    if (warehouse != null){
                        // 通过入库物资信息的物资id 获取物资信息
                        // 入库信息
                        Map<String, Object> material = recycleDao.materialInfosGet(warehouse.get("MESSAGEID"));
                        if (material != null){
                            // 补全物资信息数量
                            material.put("NUMBER", warehouse.get("OUTPUT_AMOUNT"));
                            // 补全物资类别名称(根据物资类别id)
                            String category = recycleDao.searchCategoryById((String) material.get("SUB_CATEGORY"));
                            material.put("CATEGORY", category);
                            materialInfos.add(material);
                        }
                    }
                }
            }
        }
        return materialInfos;
    }

    /**
     * 判断物资回收数量是否小于物资出库数量
     * @param recycle
     * @param list
     */
    private void checkrecoveryNum(RepSubstanceRecycleEntity recycle, List<RepStorageDetailEntity> list){

        // 判断物资回收数量是否小于物资出库数量
        if (list != null && recycle != null && list.size() > 0){

            // 判断是否出现相同物资
            for (int a = 0; a < list.size(); a++){
                for (int b = 0; b < list.size(); b++){
                    if (list.get(a).getMessageid().equals(list.get(b).getMessageid())){
                        throw  new BusinessException("存在相同物资,请归纳");
                    }
                }
            }

            TSUser user = ResourceUtil.getSessionUserName();
            // 根据出库单id和userid查询出库物资详细表中的物资id 和 出库数量
            List<RepOuthouseDetail> outhouseDetil = recycleDao.getDetilById(recycle.getOuthouseId(), user.getId());
            if (outhouseDetil != null && outhouseDetil.size() != 0){
                // 临时集合
                Map<String, Long> recoveryNumMap = new HashMap<>(outhouseDetil.size());
                // 遍历物资 使用冒泡排序的双循环两两比较将物资信息id相同的数量进行累加 为下面判断做准备
                for (int k = 0; k < outhouseDetil.size(); k++){
                    // 判断该id是否出现过
                    if (recoveryNumMap != null && recoveryNumMap.size() != 0 && recoveryNumMap.containsKey(outhouseDetil.get(k).getMessageid())){
                        // 下一个id循环
                        continue;
                    }
                    // 保存出现过的id 将物资id与出库数量封装到map
                    // recoveryNumMap.put(outhouseDetil.get(k).getMessageid(), outhouseDetil.get(k).getOuthouseAmount());
                    long sum = 0;
                    for (int m = k; m < outhouseDetil.size(); m++) {
                        // 判断是否是相同的物资
                        if (outhouseDetil.get(k).getMessageid().equals(outhouseDetil.get(m).getMessageid())){
                            // 累加
                            sum = sum + outhouseDetil.get(m).getOuthouseAmount();
                            // 修改临时map的value值
                            recoveryNumMap.put(outhouseDetil.get(k).getMessageid(), sum);
                        }
                    }
                }

                // 遍历物资出库的物资信息
                //for (int i = 0; i < recoveryNumMap.size(); i++){
                // 根据回收物资来遍历
                for (int j = 0; j < list.size(); j++){
                    // 判断是否是同一物资
                    if (recoveryNumMap.containsKey(list.get(j).getMessageid())){
                        // 判断回收数量是不是小于出库数量 -1小于  0等于 1大于
                        if (list.get(j).getOutputAmount().compareTo(BigDecimal.valueOf(recoveryNumMap.get(list.get(j).getMessageid()))) == 1){
                            // 给出提示
                            throw  new BusinessException("回收数量大于出库数量,请认真核对");
                        }
                    }
                }
                //}
            }
        }
    }

    /**
     * 添加物资回收数据
     * @param recycle
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecycleAndMaterialRecovery(RepSubstanceRecycleEntity recycle, List<RepStorageDetailEntity> list) {
        // 判断物资回收数量是否小于物资出库数量
        checkrecoveryNum(recycle, list);

        // 保存物资回收信息
        super.save(recycle);

        if (list != null && list.size()!=0){
            for (int i = 0; i < list.size(); i++){
                if (StringUtil.isEmpty(list.get(i).getMessageid())){
                    throw  new BusinessException("物资品名请选择");
                }
                // 补全对象属性
                list.get(i).setNumberid(recycle.getUuid());
                if (!"".equals(list.get(i).getMessageid())){
                    // 保存物资回收详细(入库信息)
                    super.save(list.get(i));
                }
            }
        }else {
            throw  new BusinessException("物资回收明细不能为空");
        }
    }

    /**
     * 修改物资回收
     * @param recycle
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecycleAndMaterialRecovery(RepSubstanceRecycleEntity recycle, List<RepStorageDetailEntity> list) {

        // 判断物资回收数量是否小于物资出库数量
        checkrecoveryNum(recycle, list);

        // 得到原来的物资回收信息
        if (recycle!=null){
            RepSubstanceRecycleEntity entity = recycleDao.lookRecycle(recycle.getUuid());
            if (entity!=null){
                recycle.setCreateBy(entity.getCreateBy());
                recycle.setCreateDate(entity.getCreateDate());
                recycle.setCreateName(entity.getCreateName());
                recycle.setIsdeleted(entity.getIsdeleted());
                recycle.setSysCompanyCode(entity.getSysCompanyCode());
                recycle.setSysOrgCode(entity.getSysOrgCode());
            }
        }
        // 更新物资回收信息
        super.saveOrUpdate(recycle);

        // 删除回收详细表字段Repositoryid为uuid所有的数据  从新添加
        String sql = "delete from REP_STORAGE_DETAIL where NUMBERID = ?";
        super.executeSql(sql, recycle.getUuid());

        if (list != null && list.size() != 0){
            // 从新添加回收详细表数据
            for (int i = 0; i < list.size(); i++){
                if (StringUtil.isEmpty(list.get(i).getMessageid())){
                    throw  new BusinessException("物资品名请选择");
                }
                // 补全对象属性
                list.get(i).setNumberid(recycle.getUuid());
                if (!"".equals(list.get(i).getMessageid())) {
                    super.saveOrUpdate(list.get(i));
                }
            }
        }else {
            throw  new BusinessException("物资回收明细不能为空");
        }
    }

    /**
     * 调拨通知查询列表
     * @param input 入库
     * @param output 出库
     * @return
     */
    @Override
    public List<Map<String, Object>> seachAllocation(String input, String output, Short isUse ) {
        return recycleDao.seachAllocation(input, output, isUse);
    }

    /**
     * 查询已审批的出库单
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllOutHouse() {
        TSUser user = ResourceUtil.getSessionUserName();
        return recycleDao.getAllOutHouse(user.getId());
    }

    /**
     * 根据出库单id查询出库单
     * @param outhouseId
     * @return
     */
    @Override
    public RepSubstanceOuthouse searchNumberByUuid(String outhouseId) {
        return recycleDao.searchNumberByUuid(outhouseId);
    }

    /**
     *
     * @param uuid 回收id
     * @param approvalStatus 审批状态
     * @param approver 审批人
     * @param approvalOpinion 审批意见
     */
    @Override
    public void approval(String uuid, String approvalStatus, String approver, String approvalOpinion) {
        List<TSType> items = typeServiceImpl.getItems(CommonCode.SP_STATUS);

        //判断审批状态 审批通过
        if (items.get(0).getTypecode().equals(approvalStatus)){
            //根据出库单的主键查询出库的详细信息
            String hql = "FROM RepStorageDetailEntity WHERE numberid=?";
            List<RepStorageDetailEntity> details = super.findHql(hql, uuid);

            if (details != null && details.size() > 0){
                // 根据入库详细id修改入库详细储存量
                for (int i = 0; i < details.size(); i++){
                    String updatesql = "UPDATE REP_STORAGE_DETAIL SET AMOUNT=AMOUNT+? WHERE UUID=?";
                    super.executeSql(updatesql, details.get(i).getAmount(), details.get(i).getStorageDetail());
                }
            }

        }else if (items.get(1).getTypecode().equals(approvalStatus)){
            //审批驳回
        }else{
            //审批状态异常
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.APPROVAL_STATUS_ERROR.getMessage());
        }
        //修改出库单的审批状态
        String sql = "UPDATE REP_SUBSTANCE_RECYCLE SET APPROVAL_STATUS = ?,APPROVER = ?,APPROVAL_OPINION = ? WHERE UUID = ?";
        Integer line = super.executeSql(sql, approvalStatus, approver, approvalOpinion,uuid);
        if (line <= 0 ){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> searchRepSubstanceMessage(String subNumber, String subName, String subCatagory, String outHouseId) {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return recycleDao.searchRepSubstanceMessage(subNumber, subName, subCatagory, outHouseId, tsUser.getId());
    }


}