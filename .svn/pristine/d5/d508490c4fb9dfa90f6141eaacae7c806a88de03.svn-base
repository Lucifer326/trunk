package com.jeecg.system_management.service.impl;

import com.jeecg.system_management.dao.MaterialRepSubstanceMessageDao;
import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import com.jeecg.system_management.service.MaterialRepSubstanceMessageService;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 物资信息管理Service实现类
 * @author 王国强
 */
@Service
public class MaterialRepSubstanceMessageServiceImpl extends CommonServiceImpl implements MaterialRepSubstanceMessageService {
    @Resource
    private MaterialRepSubstanceMessageDao materialRepSubstanceMessageDao;
    /**
     * 获取物资类别集合 显示到选择按钮菜单后的小窗口
     * @return
     */
    @Override
    public List<RepSubstanceCategory> findAllRepSubstanceCategory(){
        TSUser tsUser = ResourceUtil.getSessionUserName();
        List<RepSubstanceCategory> repSubstanceCategoryList = materialRepSubstanceMessageDao.findAllRepSubstanceCategory(tsUser.getId());
        return repSubstanceCategoryList;
    }
    /**
     * 查询数据库，获得所有的物资信息，
     * @return List
     */
    @Override
    public List<Map<String,Object>> findAllRepSubstanceMessage(){
        TSUser tsUser = ResourceUtil.getSessionUserName();
        List<Map<String,Object>> list = materialRepSubstanceMessageDao.findAllRepSubstanceMessage(tsUser.getId());
        return list;
    }
    /**
     * 保存物资信息
     * @return AjaxJson
     */
    @Override
    public AjaxJson saveRepSubstanceMessage(RepSubstanceMessage repSubstanceMessage){
        AjaxJson json = new AjaxJson();
        Serializable save = super.save(repSubstanceMessage);
        if (save!=null) {
            json.setMsg("添加成功");
        }else{
            json.setMsg("添加失败");
        }
        return json;
    }
    /**
     * 查看物资信息根据id
     * @param uuid
     * @return RepSubstanceMessage
     */
    @Override
    public RepSubstanceMessage findRepSubstanceMessageById(String uuid) {
        RepSubstanceMessage repSubstanceMessage = materialRepSubstanceMessageDao.findRepSubstanceMessageById(uuid);
        return repSubstanceMessage;
    }
    /**
     * 更新物资信息
     * @param repSubstanceMessage
     * @return AjaxJson
     */
    @Override
    public AjaxJson  updateRepSubstanceMessage(RepSubstanceMessage repSubstanceMessage) {
        AjaxJson json = new AjaxJson();
        try {
            //修改时更正有些信息丢失问题
            RepSubstanceMessage rep = materialRepSubstanceMessageDao.findRepSubstanceMessageById(repSubstanceMessage.getUuid());
            repSubstanceMessage.setCreateBy(rep.getCreateBy());
            repSubstanceMessage.setCreateDate(rep.getCreateDate());
            super.saveOrUpdate(repSubstanceMessage);
            json.setMsg("更新成功");
        } catch (Exception e) {
            json.setMsg("更新失败");
            e.printStackTrace();
        }
        return  json;
    }
    /**
     * 物资信息删除功能
     * @param uuids
     * @return int
     */
    @Override
    public int deleteRepSubstanceMessage(String uuids) {
        String[] split = uuids.split(",");
        String sql = "UPDATE REP_SUBSTANCE_MESSAGE SET ISDELETED = 1 WHERE UUID in( ";
        for( int i=0;i<split.length;i++){
            sql+="?,";
        }
        String s = sql.substring(0,sql.length()-1);
        s += ")";
        int line = super.executeSql(s, split);
        return line;
    }
    /**
     * 查询物资类别id根据名称
     * @param subCategory
     * @return RepSubstanceCategory
     */
    @Override
    public RepSubstanceCategory findRepSubstanceCategoryByName(String subCategory) {
        return  materialRepSubstanceMessageDao.findRepSubstanceCategoryByName(subCategory);
    }
    /**
     * 查询物资类别名称根据id
     * @param categoryUuid
     * @return RepSubstanceCategory
     */
    @Override
    public RepSubstanceCategory findRepSubstanceCategoryById(String categoryUuid) {
        return materialRepSubstanceMessageDao.findRepSubstanceCategoryById(categoryUuid);
    }
    /**
     * 根据条件查询物资信息
     * @param subNumber
     * @param subName
     * @param subCatagory
     * @return List
     */
    @Override
    public List<Map<String, Object>> searchRepSubstanceMessage(String subNumber, String subName, String subCatagory) {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return materialRepSubstanceMessageDao.searchRepSubstanceMessage(subNumber,subName,subCatagory,tsUser.getId());
    }
}
