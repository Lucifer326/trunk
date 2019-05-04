package com.jeecg.system_management.service.impl;

import com.jeecg.system_management.dao.MaterialReliefFormDao;
import com.jeecg.system_management.service.MaterialReliefFormService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 救灾物资库存管理service实现类
 * @author  王国强
 */
@Service
public class MaterialReliefFormServiceImpl extends CommonServiceImpl implements MaterialReliefFormService {

    @Autowired
    private MaterialReliefFormDao materialReliefFormDao;
    @Override
    /**
     * 查询库存信息的方法的具体实现
     * @param repositoryName
     * @return List
     */
    public List<Map<String, Object>> findMaterialReliefFormList() {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return materialReliefFormDao.findMaterialReliefFormList(tsUser.getId());
    }
    /**
     * 根据条件查询库存信息的方法的具体实现
     * @param repositoryName
     * @param organizationClassify
     * @return List
     */
    @Override
    public List<Map<String, Object>> findMaterialReliefFormListLike(String organizationClassify,String repositoryName) {
        TSUser tsUser = ResourceUtil.getSessionUserName();
        return materialReliefFormDao.findMaterialReliefFormListLike(organizationClassify,repositoryName,tsUser.getId());
    }

}
