package com.jeecg.rep_protocol_store_message.controller;

import com.jeecg.MaterialProducer.entity.ScProductEntity;
import com.jeecg.MaterialProducer.service.MaterialProducerService;
import com.jeecg.rep_protocol_store_message.entity.RepProtocolStoreMessageEntity;
import com.jeecg.rep_protocol_store_message.service.ProtocalService;
import com.jeecg.rep_protocol_store_message.vo.ProtocolVo;
import com.jeecg.system_management.common.MaterialCategoryVo;
import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import com.jeecg.system_management.service.MaterialCategoryService;
import com.jeecg.system_management.service.MaterialRepSubstanceMessageService;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 协议储备信息管理
 * @author 刘雨泽
 */
@Scope("prototype")
@Controller
@RequestMapping("/protocalController")
public class ProtocalController extends BaseController {

    /**
     * 协议储备接口
     */
    @Autowired
    private ProtocalService protocalService;

    /**
     * 协议生产商接口
     */
    @Autowired
    private MaterialProducerService materialProducerService;

    /**
     * 物资详细接口
     */
    @Autowired
    private MaterialRepSubstanceMessageService materialRepSubstanceMessageService;

    /**
     * 物资类别接口
     */
    @Autowired
    private MaterialCategoryService materialCategoryService;

    /**
     * 查询结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "list")
    public String list(Model model){
        List<RepProtocolStoreMessageEntity> list = protocalService.list();
        model.addAttribute("lists", list);
        return "jzwz/ProtocolReserveInfoManage/ProtocolReserveInfoList";
    }

    /**
     * 添加时页面准备
     * @return
     */
    @RequestMapping(params = "addProtocal")
    public String addProtocal(){
        return "jzwz/ProtocolReserveInfoManage/ProtocolReserveInfoAdd";
    }

    /**
     * 添加
     * @param protocol
     * @return
     */
    @RequestMapping(params = "aProtocal")
    @ResponseBody
    public AjaxJson saveProtocal(RepProtocolStoreMessageEntity protocol){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            protocalService.saveProtocal(protocol);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 删除
     * @param protocolid
     * @return
     */
    @RequestMapping(params = "deleteProtocals")
    @ResponseBody
    public AjaxJson deleteProtocals(String protocolid){
        String[] protocolids = protocolid.split(",");
        AjaxJson ajaxJson = new AjaxJson();
        try {
            protocalService.deleteProtocolidByIds(protocolids);
            ajaxJson.setMsg("删除成功,一共删除" + protocolids.length +"条数据");
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    /**
     * 查看数据
     * @param uuid
     * @param model
     * @return
     */
    @RequestMapping(params = "lookProtocal")
    public String lookProtocal(@RequestParam("uuid") String uuid, Model model){
        RepProtocolStoreMessageEntity protocol = protocalService.look(uuid);
        model.addAttribute("protocol", protocol);
        return "jzwz/ProtocolReserveInfoManage/ProtocolReserveInfoLook";
    }

    /**
     * 修改页面的数据回显
     * @param uuid
     * @param model
     * @return
     */
    @RequestMapping(params = "editProtocal")
    public String editProtocal(@RequestParam("uuid") String uuid, Model model){
        RepProtocolStoreMessageEntity protocol = protocalService.edit(uuid);
        model.addAttribute("protocol", protocol);
        return "jzwz/ProtocolReserveInfoManage/ProtocolReserveInfoEdit";
    }

    /**
     * 修改
     * @param protocol
     * @return
     */
    @RequestMapping(params = "uProtocal")
    @ResponseBody
    public AjaxJson updateProtocal(RepProtocolStoreMessageEntity protocol){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            protocalService.updateProtocal(protocol);

        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }//

    /**
     * 查询物资生产商结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "materialProducer")
    public String materialProducer(Model model){
        List<ScProductEntity> productList = materialProducerService.productList();
        model.addAttribute("productList", productList);
        return "jzwz/ProtocolReserveInfoManage/MaterialProducerSelect";
    }

    /**
     * 查询物资生产商结果集 模糊搜索
     * @param model
     * @param name
     * @param product
     * @return
     */
    @RequestMapping(params = "productQuery")
    public String productQuery(Model model, String name, String product){
        List<ScProductEntity> productList = materialProducerService.productQuery(name, product, "");
        model.addAttribute("productList", productList);
        model.addAttribute("name",name);
        model.addAttribute("product",product);
        return "jzwz/ProtocolReserveInfoManage/MaterialProducerSelect";
    }

    /**
     * 查询物资信息结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "materialCategory")
    public String materialCategory(Model model){
        List<Map<String, Object>> allRepSubstanceMessage = materialRepSubstanceMessageService.findAllRepSubstanceMessage();
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        return "jzwz/ProtocolReserveInfoManage/MaterialInfoManagementSelect";
    }

    /**
     * 查询物资信息结果集 模糊搜索
     * @param model
     * @param repSubstanceMessage
     * @return
     */
    @RequestMapping(params = "searchRepSubstanceMessage")
    public String searchRepSubstanceMessage(Model model, RepSubstanceMessage repSubstanceMessage){
        String subCategory = repSubstanceMessage.getSubCategory();
        String subName = repSubstanceMessage.getSubName();
        String subNumber = repSubstanceMessage.getSubNumber();
        System.out.println(subNumber+subName+subCategory);
        RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryByName(subCategory);
        String subCatagory = "";
        if(repSubstanceCategory!=null){
            subCatagory= repSubstanceCategory.getUuid();
        }
        System.out.println(subNumber+subName+subCatagory);
        List<Map<String, Object>> allRepSubstanceMessage = materialRepSubstanceMessageService.searchRepSubstanceMessage(subNumber, subName, subCatagory);
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        model.addAttribute("subNumber", subNumber);
        model.addAttribute("subName",subName);
        model.addAttribute("subCategory",subCategory);
        return "jzwz/ProtocolReserveInfoManage/MaterialInfoManagementSelect";
    }

    /**
     * 查询物资类别结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "materialCategoryList")
    public String materialCategoryList(Model model){
        List<RepSubstanceCategory> repSubstanceCategories = materialCategoryService.materialCategoryList();
        model.addAttribute("categoryList", repSubstanceCategories);
        return "jzwz/ProtocolReserveInfoManage/SelectMaterialCategory";
    }

    /**
     * 查询物资类别结果集 模糊搜索
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(params = "searchCategoryByNumAndName")
    public String searchCategoryByNumAndName(Model model, MaterialCategoryVo vo){
        List<RepSubstanceCategory> repSubstanceCategories = materialCategoryService.materialCategoryByQuery(vo);
        model.addAttribute("categoryList", repSubstanceCategories);
        model.addAttribute("vo", vo);
        return "jzwz/ProtocolReserveInfoManage/SelectMaterialCategory";
    }

    /**
     *  页面模糊查询
     * @param model
     * @return
     */
    @RequestMapping(params = "queryProtocal")
    public String queryProtocal(ProtocolVo vo, Model model){
        List<RepProtocolStoreMessageEntity> list = protocalService.conditionList(vo);
        model.addAttribute("lists", list);
        model.addAttribute("vo", vo);
        return "jzwz/ProtocolReserveInfoManage/ProtocolReserveInfoList";
    }
}
