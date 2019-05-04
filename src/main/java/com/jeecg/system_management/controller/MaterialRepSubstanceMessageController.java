package com.jeecg.system_management.controller;

import com.jeecg.system_management.common.MaterialCategoryVo;
import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import com.jeecg.system_management.service.MaterialCategoryService;
import com.jeecg.system_management.service.MaterialRepSubstanceMessageService;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * 物资信息管理controller
 * @author  王国强
 */
@Controller
@RequestMapping("MaterialManagement")
public class MaterialRepSubstanceMessageController extends BaseController {
    @Autowired
    private MaterialRepSubstanceMessageService materialRepSubstanceMessageService;
    @Resource
    private MaterialCategoryService materialCategoryServiceImpl;

    /**
     * 展示物资信息页面，并显示所有信息
     * @param model
     * @return
     */
    @RequestMapping(params = "MaterialInfoManagement")
    public String showPageAndList(Model model) {
        List<Map<String,Object>> list = materialRepSubstanceMessageService.findAllRepSubstanceMessage();
        model.addAttribute("repSubstanceMessageList", list);
        return "jzwz/MaterialManagement/MaterialInfoManagement/MaterialInfoManagementList";
    }
    /**
     * 按下选择按钮后，显示物资类别窗口
     * @param model
     * @return
     */
    @RequestMapping(params = "MaterialCategoryList")
    public String materialCategoryList(Model model) {
        List<RepSubstanceCategory> categoryList = materialRepSubstanceMessageService.findAllRepSubstanceCategory();
        model.addAttribute("categoryList", categoryList);
        return "jzwz/MaterialManagement/MaterialInfoManagement/SelectMaterialCategory";
    }
    /**
     * 保存物资功能
     * @param model
     * @param repSubstanceMessage
     * @return
     */
    @RequestMapping(params = "saveRepSubstanceMessage")
    @ResponseBody
    public AjaxJson saveRepSubstanceMessage(Model model,RepSubstanceMessage repSubstanceMessage) {
        AjaxJson json = new AjaxJson();
        if(StringUtil.isNotEmpty(repSubstanceMessage.getSubCategory())){
            String subCategory = repSubstanceMessage.getSubCategory();
            RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryByName(subCategory);
            repSubstanceMessage.setSubCategory(repSubstanceCategory.getUuid());
        }else {
            json.setSuccess(false);
            json.setMsg("添加失败");
            return json;
        }
        if(StringUtil.isEmpty(repSubstanceMessage.getSubName())){
            json.setSuccess(false);
            json.setMsg("添加失败");
            return json;
        }
        json = materialRepSubstanceMessageService.saveRepSubstanceMessage(repSubstanceMessage);
        return json;
    }
    /**
     * 查看功能，前台传入id，后台去查询，返回给前台数据
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(params = "findRepSubstanceMessageById")
    public String findRepSubstanceMessageById(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        System.out.println(uuid+".......");
        RepSubstanceMessage repSubstanceMessage = materialRepSubstanceMessageService.findRepSubstanceMessageById(uuid);
        String categoryUuid = repSubstanceMessage.getSubCategory();
        System.out.println(categoryUuid);
        RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryById(categoryUuid);
        System.out.println(repSubstanceCategory.getCategoryName());
        repSubstanceMessage.setSubCategory(repSubstanceCategory.getCategoryName());
        model.addAttribute("repSubstanceMessage", repSubstanceMessage);
        return "jzwz/MaterialManagement/MaterialInfoManagement/MaterialInfoManagementLook";
    }
    /**
     * 修改物资信息，回显数据
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(params = "viewAndUpdateRepSubstanceMessageById")
    public String updateRepSubstanceMessageById(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        RepSubstanceMessage repSubstanceMessage = materialRepSubstanceMessageService.findRepSubstanceMessageById(uuid);
        String categoryUuid = repSubstanceMessage.getSubCategory();
        RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryById(categoryUuid);
        repSubstanceMessage.setSubCategory(repSubstanceCategory.getCategoryName());
        repSubstanceCategory.setUuid(uuid);
        model.addAttribute("repSubstanceMessage", repSubstanceMessage);
        return "jzwz/MaterialManagement/MaterialInfoManagement/MaterialInfoManagementEdit";
    }
    /**
     * 保存修改功能，更新物资信息
     * @param model
     * @param repSubstanceMessage
     * @param request
     * @return
     */
    @RequestMapping(params = "updateRepSubstanceMessage")
    @ResponseBody
    public AjaxJson updateRepSubstanceMessage(Model model, RepSubstanceMessage repSubstanceMessage,HttpServletRequest request) {
        AjaxJson json = new AjaxJson();
        if(StringUtil.isNotEmpty(repSubstanceMessage.getSubCategory())){
            String subCategory = repSubstanceMessage.getSubCategory();
            RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryByName(subCategory);
            repSubstanceMessage.setSubCategory(repSubstanceCategory.getUuid());
        }else {
            json.setSuccess(false);
            json.setMsg("添加失败");
            return json;
        }
        if(StringUtil.isEmpty(repSubstanceMessage.getSubName())){
            json.setSuccess(false);
            json.setMsg("添加失败");
            return json;
        }
       json = materialRepSubstanceMessageService.updateRepSubstanceMessage(repSubstanceMessage);
        return json;
    }
    /**
     * 删除物资信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(params = "deleteRepSubstanceMessage")
    public String deleteRepSubstanceMessage(Model model,HttpServletRequest request){
        String uuids = request.getParameter("uuid");
        materialRepSubstanceMessageService.deleteRepSubstanceMessage(uuids);
        return "MaterialManagement.do?MaterialInfoManagement";
    }
    /**
     * 根据条件搜索物资信息 物资编号 物资品名 物资类别名称
     * @param model
     * @param repSubstanceMessage
     * @return
     */
    @RequestMapping(params = "searchRepSubstanceMessage")
    public String searchRepSubstanceMessage(Model model,RepSubstanceMessage repSubstanceMessage){
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
        List<Map<String,Object>> list = materialRepSubstanceMessageService.searchRepSubstanceMessage(subNumber,subName,subCatagory);
        model.addAttribute("repSubstanceMessageList", list);
        model.addAttribute("subCategory",subCategory);
        model.addAttribute("subName",subName);
        model.addAttribute("subNumber",subNumber);
        return "jzwz/MaterialManagement/MaterialInfoManagement/MaterialInfoManagementList";
    }

    /**
     *
     * 根据条件查询物资类别信息
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping(params = "searchRepSubstanceCategory")
    public String searchRepSubstanceCategory(MaterialCategoryVo vo, Model model){
        //查询条件vo非空判断
        if(null != vo){
            //执行条件查询
            List<RepSubstanceCategory> list = materialCategoryServiceImpl.materialCategoryByQuery(vo);
            model.addAttribute("categoryList", list);
            //条件回显
            model.addAttribute("vo", vo);
            return "jzwz/MaterialManagement/MaterialInfoManagement/SelectMaterialCategory";
        }
        //查询条件vo为空，直接返回
        return "jzwz/MaterialManagement/MaterialInfoManagement/SelectMaterialCategory";
    }
}
