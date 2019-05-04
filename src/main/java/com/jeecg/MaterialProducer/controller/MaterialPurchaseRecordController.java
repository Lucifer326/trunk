package com.jeecg.MaterialProducer.controller;

import com.jeecg.MaterialProducer.dao.MaterialPurchaseRecordDao;
import com.jeecg.MaterialProducer.entity.MaterialPurchaseRecordEntity;
import com.jeecg.MaterialProducer.service.MaterialPurchaseRecordService;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MaterialPurchaseRecordController
 * @Description 购买记录查询
 * @Author God
 * @Date 2019/4/4、19:45
 * @Version 1.0
 **/
@Controller
@RequestMapping("materialPurchaseRecordController")
public class MaterialPurchaseRecordController extends BaseController {

    @Autowired
    private MaterialPurchaseRecordService materialPurchaseRecordService;

    /**
     * 购买记录列表
     * @param model
     * @return
     */
    @RequestMapping(params = "list")
    public String list(Model model){
        List<MaterialPurchaseRecordEntity> list = materialPurchaseRecordService.list();
        model.addAttribute("list", list);
        return "jzwz/MaterialProducer/MaterialProducerRecord";
    }

    /**
     * 购买记录条件查询
     * @param model
     * @param productCategory 物品种类
     * @param buyTime 购买时间
     * @return
     */
    @RequestMapping(params = "search")
    public String search(Model model, String productCategory, Date buyTime){
        List<MaterialPurchaseRecordEntity> list = materialPurchaseRecordService.search(productCategory, buyTime);
        model.addAttribute("productCategory", productCategory);
        model.addAttribute("list", list);
        model.addAttribute("buyTime", buyTime);
        return "jzwz/MaterialProducer/MaterialProducerRecord";
    }
}
