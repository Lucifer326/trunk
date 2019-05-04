package com.jeecg.MaterialProducer.service.impl;

import com.jeecg.MaterialProducer.dao.MaterialPurchaseRecordDao;
import com.jeecg.MaterialProducer.entity.MaterialPurchaseRecordEntity;
import com.jeecg.MaterialProducer.service.MaterialPurchaseRecordService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MaterialPurchaseRecordServiceImpl
 * @Description 物资生产商service层实现类
 * @Author 宋超
 * @Date 2019/4/4、19:48
 * @Version 1.0
 **/
@Service
public class MaterialPurchaseRecordServiceImpl extends CommonServiceImpl implements MaterialPurchaseRecordService {

    @Autowired
    private MaterialPurchaseRecordDao materialPurchaseRecordDao;

    /**
     * 购买记录列表
     *
     * @return
     */
    @Override
    public List<MaterialPurchaseRecordEntity> list(){
        return materialPurchaseRecordDao.list();
    }

    /**
     * 购买记录条件查询
     * @param productCategory 物品种类
     * @param buyTime 时间
     * @return
     */
    @Override
    public List<MaterialPurchaseRecordEntity> search(String productCategory, Date buyTime) {
        String buyFormTime = "";
        String buyToTime = "";

        if (buyTime != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            buyFormTime = format.format(buyTime);

            Calendar c = Calendar.getInstance();
            c.setTime(buyTime);
            // 今天+1天
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date tomorrow = c.getTime();
            buyToTime = format.format(tomorrow);
        }
        return materialPurchaseRecordDao.search(productCategory, buyFormTime, buyToTime);
    }
}
