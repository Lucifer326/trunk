package com.jeecg.MaterialsOutPut.service.impl;

import com.jeecg.MaterialsOutPut.dao.RepOuthouseDetailMiniDao;
import com.jeecg.MaterialsOutPut.entity.RepOuthouseDetail;
import com.jeecg.MaterialsOutPut.service.RepOuthouseDetailService;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 甄磊超
 * @date 2019/4/2
 * @Description com.jeecg.MaterialsOutPut.service.impl
 */

@Service("repOuthouseDetailService")
@Transactional(rollbackFor = {java.lang.Exception.class})
public class RepOuthouseDetailServiceImpl extends CommonServiceImpl implements RepOuthouseDetailService{

    @Autowired
    private RepOuthouseDetailMiniDao repOuthouseDetailMiniDao;

    @Override
    public List<Map<String,Object>> getByOuthouseId(String outhouseId) {
        if (StringUtils.isBlank(outhouseId)){
            return Collections.EMPTY_LIST;
        }
        return repOuthouseDetailMiniDao.getByOuthouseId(outhouseId);
    }

    @Override
    public List<Map<String, Object>> getByDetilId(String uuid) {
        if (StringUtils.isBlank(uuid)){
            return Collections.EMPTY_LIST;
        }
        return repOuthouseDetailMiniDao.getByDetilId(uuid);
    }

    /**
     * @auther wangguoqiang
     * @param uuid
     * @return
     */
    @Override
    public List<Map<String, Object>> findAllRepSubstanceMessage(String uuid) {
        return  repOuthouseDetailMiniDao.findRepSubstanceMessageById(uuid);
    }
}
