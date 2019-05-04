package com.jeecg.MaterialsOutPut.service.impl;

import com.jeecg.MaterialsInPut.entity.RepStorageDetailEntity;
import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutLogicException;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutQueryVo;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutStatusCode;
import com.jeecg.MaterialsOutPut.dao.RepSubstanceOuthouseMiniDao;
import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import com.jeecg.MaterialsOutPut.service.RepSubstanceOuthouseService;
import com.jeecg.system_management.common.CommonCode;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author 甄磊超
 * @date 2019/4/2
 */
@Service("repSubstanceOuthouseService")
@Transactional(rollbackFor = {java.lang.Exception.class})
public class RepSubstanceOuthouseServiceImpl extends CommonServiceImpl implements RepSubstanceOuthouseService {

    @Autowired
    private RepSubstanceOuthouseMiniDao repSubstanceOuthouseMiniDao;

    @Autowired
    private TypeServiceI typeServiceImpl;

    private static Logger logger = Logger.getLogger(RepSubstanceOuthouseServiceImpl.class);


    @Override
    public List<Map<String,Object>> getAllByCondition(MaterialsOutPutQueryVo vo) {
        if (vo == null){
            vo = new MaterialsOutPutQueryVo();
        }
        if (StringUtils.isNotBlank(vo.getOuthouseNumber())){
            vo.setOuthouseNumber(vo.getOuthouseNumber().trim());
        }

        if (StringUtils.isNotBlank(vo.getRepositoryName())){
            vo.setRepositoryName(vo.getRepositoryName().trim());
        }
        String date2Str = "";
        if (vo.getOuthouseDate() != null){
            date2Str = DateUtils.date2Str(vo.getOuthouseDate(), new SimpleDateFormat("yyyy-MM-dd"));
        }
        List<Map<String,Object>> list = repSubstanceOuthouseMiniDao.getAll(vo.getOuthouseNumber(),vo.getRepositoryName(),date2Str,ResourceUtil.getSessionUserName().getId());
       /* RepSubstanceAccessory accessory = new RepSubstanceAccessory();
        for (int i = 0;i<list.size();i++ ) {
            String accessoryId = list.get(i).get("ACCESSORY").toString();
            if(accessoryId!=null||!accessoryId.equals("")){
                accessory = repSubstanceOuthouseMiniDao.findAccessoryById(accessoryId);
                list.get(i).put("ACCESSORY",accessory.getAccessoryName());
            }
        }*/
        return list;
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return repSubstanceOuthouseMiniDao.getAll(null,null,null,ResourceUtil.getSessionUserName().getId());
    }

    @Override
    public Map<String, Object> getDetilById(String uuid) {
        if (StringUtils.isBlank(uuid)){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        uuid = uuid.trim();
        return repSubstanceOuthouseMiniDao.getDetilById(uuid,ResourceUtil.getSessionUserName().getId());
    }

    @Override
    public void updOuthouse(RepSubstanceOuthouse outhouse,String warehouseId,Long[] outhouseAmounts, String[] subIds,String[] storageDetails,String originalFilename,String path){
        if (outhouse == null || StringUtils.isBlank(outhouse.getUuid()) || subIds == null || StringUtils.isBlank(warehouseId) || outhouseAmounts == null || subIds.length != outhouseAmounts.length){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        RepSubstanceAccessory accessory = new RepSubstanceAccessory();
        if((originalFilename != null || !"".equals(originalFilename))&&(path == null || !"".equals(path))){
            accessory.setAccessoryName(originalFilename);
            accessory.setAccessoryPath(path);
            String save = (String)super.save(accessory);
            outhouse.setAccessory(save);
        }
        //查询原来的出库单信息
        Map<String, Object> detilById = repSubstanceOuthouseMiniDao.getDetilById(outhouse.getUuid(),ResourceUtil.getSessionUserName().getId());
        //修改调拨单的使用状态和出库状态 0表示已使用 1表示未使用
        String allotInformSql = "UPDATE REP_ALLOT_INFORM SET ISUSE = ? WHERE UUID = ? AND (ALLOT_STATUS is null or ALLOT_STATUS = '1')";
        Integer allotInformLine1 = super.executeSql(allotInformSql, 1,detilById.get("requisition"));
        if (allotInformLine1 <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
        }
        String outhoseHql = "UPDATE RepSubstanceOuthouse SET outhouseNumber = ?,outhousePerson = ?,outhouseDate = ?,receiver = ?,requisition = ?,accessory = ?,remark = ? WHERE UUID = ?";
        Integer outhoseLine = super.executeHql(outhoseHql, outhouse.getOuthouseNumber(), outhouse.getOuthousePerson(), outhouse.getOuthouseDate(), outhouse.getReceiver(), outhouse.getRequisition(), outhouse.getAccessory(), outhouse.getRemark(),outhouse.getUuid());
        if (outhoseLine == null || outhoseLine <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
        }
        //super.saveOrUpdate(outhouse);
        //删除所有指定出库单的出库详细信息
        String sql = "DELETE FROM REP_OUTHOUSE_DETAIL d WHERE OUTHOUSEID = ? AND (d.ISDELETED IS NULL OR d.ISDELETED = 0)";
        executeSql(sql, outhouse.getUuid());
        //保存出库详细信息
        for (int i = 0;i<subIds.length;i++) {
            RepOuthouseDetail detail = new RepOuthouseDetail();
            detail.setOuthouseid(outhouse.getUuid());
            detail.setMessageid(subIds[i]);
            detail.setOuthouseAmount(outhouseAmounts[i]);
            detail.setOuthouseWarehouse(warehouseId);
            detail.setStorageDetail(storageDetails[i]);
            if(super.save(detail) == null){
                throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.SAVE_FAIL.getMessage());
            }
        }
        //修改调拨单的使用状态和出库状态 0表示已使用 1表示未使用
        Integer allotInformLine = super.executeSql(allotInformSql, 0,outhouse.getRequisition());
        if (allotInformLine <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
        }
    }

    @Override
    public void addOutHose(RepSubstanceOuthouse outhouse,String warehouseId,Long[] outhouseAmounts, String[] subIds,String[] storageDetails,String originalFilename,String path){
        if (outhouse == null || subIds == null || StringUtils.isBlank(warehouseId) || outhouseAmounts == null || subIds.length != outhouseAmounts.length
                || storageDetails == null || storageDetails.length !=subIds.length){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        //保存出库信息
        RepSubstanceAccessory accessory = new RepSubstanceAccessory();
        String uuid = UUIDGenerator.generate();
        //添加入库id
        outhouse.setUuid(uuid);
        //增加附件信息
        if((originalFilename != null || !"".equals(originalFilename))&&(path == null || !"".equals(path))){
            accessory.setAccessoryName(originalFilename);
            accessory.setAccessoryPath(path);
            String save = (String)super.save(accessory);
            outhouse.setAccessory(save);
        }
        if (super.save(outhouse) == null ){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.SAVE_FAIL.getMessage());
        }
        for (int i = 0;i<subIds.length;i++) {
            RepOuthouseDetail detail = new RepOuthouseDetail();
            detail.setOuthouseid(uuid);
            detail.setMessageid(subIds[i]);
            detail.setOuthouseAmount(outhouseAmounts[i]);
            detail.setOuthouseWarehouse(warehouseId);
            detail.setStorageDetail(storageDetails[i]);
            if(super.save(detail) == null){
                throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.SAVE_FAIL.getMessage());
            }
        }
        //修改调拨单的使用状态和出库状态 0表示已使用 1表示未使用
        String allotInformSql = "UPDATE REP_ALLOT_INFORM SET ISUSE = ? WHERE UUID = ? AND (ALLOT_STATUS is null or ALLOT_STATUS = '1')";
        Integer allotInformLine = super.executeSql(allotInformSql, 0,outhouse.getRequisition());
        if (allotInformLine <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
        }
    }

    @Override
    public void rmvOutPut(String uuid,String regx) {
        if (StringUtils.isBlank(uuid)){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        //默认使用逗号分隔
        if(StringUtils.isBlank(regx)){
            regx = ",";
        }
        String[] split = uuid.split(regx);
        if(split.length <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        StringBuffer sb = new StringBuffer("UPDATE REP_SUBSTANCE_OUTHOUSE SET ISDELETED = 1 WHERE UUID IN (");
        for (int i = 0; i<split.length;i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        //删除物资出库
        if(super.executeSql(sb.toString(), split) <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.DEL_FAIL.getMessage());
        }
        //修改调拨单的使用状态  出库状态 0表示已使用 1表示未使用
        StringBuffer allotInformSql = new StringBuffer( "UPDATE REP_ALLOT_INFORM SET ISUSE = 1 WHERE UUID IN (SELECT REQUISITION FROM REP_SUBSTANCE_OUTHOUSE WHERE UUID in(");
        for (int i = 0; i<split.length;i++) {
            allotInformSql.append("?,");
        }
        allotInformSql.deleteCharAt(allotInformSql.lastIndexOf(","));
        allotInformSql.append(")) AND (ALLOT_STATUS is null or ALLOT_STATUS = '1')");
        String s = allotInformSql.toString();
        Integer allotInformLine = super.executeSql(s,split);
        if (allotInformLine <= 0){
            throw new MaterialsOutPutLogicException("不能删除已审批通过的出库单");
        }
        //删除物资出库详细
        StringBuffer sb1 = new StringBuffer("UPDATE REP_OUTHOUSE_DETAIL SET ISDELETED = 1 WHERE OUTHOUSEID IN (");
        for (int i = 0; i<split.length;i++) {
            sb1.append("?,");
        }
        sb1.deleteCharAt(sb1.lastIndexOf(","));
        sb1.append(")");
        if(super.executeSql(sb1.toString(), split) <= 0){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.DEL_FAIL.getMessage());
        }

    }


    /**
     * 附件名字的查询
     * wangguoqiang
     * @param accessoryId
     * @return
     */
    @Override
    public RepSubstanceAccessory findAccessoryById(String accessoryId) {
        if (StringUtils.isBlank(accessoryId)){
            return null;
        }
        return repSubstanceOuthouseMiniDao.findAccessoryById(accessoryId);
    }

    @Override
    public void approval(String outhoseId, String approvalStatus, String approver, String approvalOpinion) {
        if (StringUtils.isBlank(outhoseId)){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        List<TSType> items = typeServiceImpl.getItems(CommonCode.SP_STATUS);

        //判断审批状态
        if (items.get(0).getTypecode().equals(approvalStatus)){//审批通过
            //根据出库单的主键查询出库的详细信息
            String hql = "FROM RepOuthouseDetail WHERE outhouseid=?";
            List<RepOuthouseDetail> details = super.findHql(hql, outhoseId);

            //修改调拨单的使用状态(0表示已使用 1表示未使用)和出库状态 (2表示已入库,3表示已出库)
            String allotInformSql = "UPDATE REP_ALLOT_INFORM SET ISUSE = 0,ALLOT_STATUS = ? WHERE UUID IN (SELECT REQUISITION FROM REP_SUBSTANCE_OUTHOUSE WHERE UUID = ?)";
            Integer allotInformLine = super.executeSql(allotInformSql, "3",outhoseId);
            if (allotInformLine <= 0){
                throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.UPD_FAIL.getMessage());
            }
            //根据出库详细中的入库详细主键修改入库单的库存量
            String sql = "UPDATE REP_STORAGE_DETAIL SET AMOUNT=AMOUNT-? WHERE UUID =?";
            for (RepOuthouseDetail detail:details) {
                //查询指定入库详细信息
                RepStorageDetailEntity rsd = super.findUniqueByProperty(RepStorageDetailEntity.class, "uuid", detail.getStorageDetail());
                //若库存数量减去出库量小于0则抛出异常(库存量不足审批失败)
                if (StringUtils.isNumeric(rsd.getAmount())){
                    if (Integer.parseInt(rsd.getAmount())-detail.getOuthouseAmount() < 0){
                        throw new MaterialsOutPutLogicException("库存量不足,审批失败");
                    }
                }
                super.executeSql(sql, detail.getOuthouseAmount(), detail.getStorageDetail());
            }
        }else if (items.get(1).getTypecode().equals(approvalStatus)){//审批驳回

        }else{//审批状态异常
            throw new MaterialsOutPutLogicException("请选择审批状态");
        }
        //修改出库单的审批状态
        String sql = "UPDATE REP_SUBSTANCE_OUTHOUSE SET APPROVAL_STATUS = ?,APPROVER = ?,APPROVAL_OPINION = ? WHERE UUID = ?";
        Integer line = super.executeSql(sql, approvalStatus, approver, approvalOpinion,outhoseId);
        if (line <= 0 ){
            throw new MaterialsOutPutLogicException(MaterialsOutPutStatusCode.APPROVAL_FAIL.getMessage());
        }
    }

}
