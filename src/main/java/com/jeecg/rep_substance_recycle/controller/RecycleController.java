package com.jeecg.rep_substance_recycle.controller;

import com.jeecg.MaterialsInPut.entity.RepStorageDetailEntity;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutLogicException;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutStatusCode;
import com.jeecg.MaterialsOutPut.controller.MaterialsOutPutDispatcherController;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import com.jeecg.MaterialsOutPut.service.RepOuthouseDetailService;
import com.jeecg.MaterialsOutPut.service.RepSubstanceOuthouseService;
import com.jeecg.rep_substance_recycle.entity.RepDetailedMaterialRecoveryEntity;
import com.jeecg.rep_substance_recycle.entity.RepSubstanceRecycleEntity;
import com.jeecg.rep_substance_recycle.service.RecycleService;
import com.jeecg.rep_substance_recycle.vo.RecycleVo;
import com.jeecg.system_management.common.CommonCode;
import com.jeecg.system_management.common.MaterialCategoryVo;
import com.jeecg.system_management.common.WarehouseQueryVo;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.pojo.RepSubstanceCategory;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import com.jeecg.system_management.service.MaterialCategoryService;
import com.jeecg.system_management.service.MaterialRepSubstanceMessageService;
import com.jeecg.system_management.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.jeecgframework.web.system.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物资回收管理
 *
 * @author 刘雨泽
 */
@Scope("prototype")
@Controller
@RequestMapping("/recycleController")
public class RecycleController extends BaseController {

    /**
     * 物资回收 接口
     */
    @Autowired
    private RecycleService recycleService;

    /**
     * 库房 接口
     */
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 物资详细 接口
     */
    @Autowired
    private MaterialRepSubstanceMessageService materialRepSubstanceMessageService;

    /**
     * 库房类别 接口
     */
    @Autowired
    private MaterialCategoryService materialCategoryService;

    /**
     * 出库 接口
     */
    @Autowired
    private RepOuthouseDetailService repOuthouseDetailService;

    /**
     * 数据字典
     */
    @Autowired
    private TypeServiceI typeServiceImpl;

    /**
     * 选择回收单进行审批
     * @param model
     * @return
     */
    @RequestMapping(params = "allocateValid")
    public String allocateValid(Model model){
        // 获取数据字典审批状态
        List<TSType> types = typeServiceImpl.getItems(CommonCode.SP_STATUS);
        model.addAttribute("types", types);
        return "jzwz/MaterialRecycling/AllocateValid";
    }

    /**
     * 审批回收单
     * @param uuid 回收单0id
     * @param approvalStatus 审批状态
     * @param approvalOpinion 审批内容
     * @param session
     * @return
     */
    @RequestMapping(params = "approval")
    @ResponseBody
    public AjaxJson approval(String uuid, String approvalStatus, String approvalOpinion, HttpSession session){
        AjaxJson json = new AjaxJson();
        if (StringUtils.isBlank(uuid)){
            json.setSuccess(false);
            json.setMsg(MaterialsOutPutStatusCode.PARAMS_ERROR.getMessage());
        }
        final String zero = "0";
        if (StringUtils.isBlank(approvalStatus) || zero.equals(approvalStatus)){
            json.setSuccess(false);
            json.setMsg("请选择审批状态");
        }
        // 获取审批人
        TSUser user = (TSUser) session.getAttribute("LOCAL_CLINET_USER");
        try {
            if (user != null){
                recycleService.approval(uuid,approvalStatus,user.getRealName(),approvalOpinion);
            }
        }catch (MaterialsOutPutLogicException e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }catch (Exception e){
            json.setSuccess(false);
            json.setMsg(MaterialsOutPutStatusCode.SYS_ERROR.getMessage());
            return json;
        }
        return json;
    }

    /**
     * 选择出库单
     * @param model
     * @return
     */
    @RequestMapping(params = "selectOutHouse")
    public String selectOutHouse(Model model){
        // 查询已审批的出库单
        List<Map<String, Object>> outPutList = recycleService.getAllOutHouse();
        model.addAttribute("outPutList", outPutList);
        return "jzwz/MaterialRecycling/SelectOutPutList";
    }

    /**
     * 调拨通知查询列表
     * @param input 入库
     * @param output 出库
     * @param model
     * @return
     */
    @RequestMapping(params = "seachAllocation")
    public String seachAllocation(String input, String output, Short isUse, Model model){
        List<Map<String, Object>> allocation = recycleService.seachAllocation(input, output, isUse);
        model.addAttribute("allocation", allocation);
        return "jzwz/AllocationNotice/SelectAllocationNoticeList";
    }

    /**
     * 查询物资回收结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "recycleList")
    public String recycleList(Model model){
        // 获取数据字典审批状态
        List<TSType> items = typeServiceImpl.getItems(CommonCode.SP_STATUS);
        //审批通过数据字典
        model.addAttribute("approved",items.get(0));
        //审批驳回数据字典
        model.addAttribute("rejection",items.get(1));
        List<RepSubstanceRecycleEntity> list = recycleService.recycleList();
        model.addAttribute("list", list);
        return "jzwz/MaterialRecycling/MaterialRecyclingList";
    }

    /**
     * 查询物资回收结果集 模糊查询
     * @param model
     * @param vo 查询条件
     * @return
     */
    @RequestMapping(params = "conditionList")
    public String conditionList(Model model, RecycleVo vo){
        // 获取数据字典审批状态
        List<RepSubstanceRecycleEntity> list = recycleService.conditionList(vo);
        model.addAttribute("list", list);
        model.addAttribute("vo", vo);
        return "jzwz/MaterialRecycling/MaterialRecyclingList";
    }

    /**
     * 显示物资回收页面 及页面展示
     * @param uuid
     * @param model
     * @return
     */
    @RequestMapping(params = "showMaterialRecyclingEdit")
    public String showMaterialRecyclingEdit(String uuid, Model model){
        // 根据uuid查询物资回收信息
        RepSubstanceRecycleEntity recycle = recycleService.lookRecycle(uuid);
        if (recycle != null){
            // 根据出库单id查询出库单
            RepSubstanceOuthouse outhouse = recycleService.searchNumberByUuid(recycle.getOuthouseId());
            model.addAttribute("outhouse", outhouse);
            // 根据物资回收信息的uuid得到 入库详细信息
            List<Map<String, Object>> recyclingDetails = recycleService.recyclingDetailsGet(recycle.getUuid());
            model.addAttribute("recyclingDetails", recyclingDetails);
        }
        model.addAttribute("recycle", recycle);
        return "jzwz/MaterialRecycling/MaterialRecyclingEdit";
    }

    /**
     * 修改物资回收
     * @param recycle
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "updateRecycle")
    public AjaxJson updateRecycle(RepSubstanceRecycleEntity recycle, HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();

        // 物资id
        String[] uuids = request.getParameterValues("id");
        // 回收数量
        String[] backNum = request.getParameterValues("backNum");
        // 入库详细id
        String[] storageDetail = request.getParameterValues("storageDetail");

        List<RepStorageDetailEntity> list = new ArrayList<RepStorageDetailEntity>();

        try {
            if (uuids != null){
                for (int i = 0; i < uuids.length; i++){
                    RepStorageDetailEntity storage = new RepStorageDetailEntity();
                    // 添加入库详细 物资信息id
                    storage.setMessageid(uuids[i]);
                    // 添加入库详细 入库详细id
                    storage.setStorageDetail(storageDetail[i]);
                    // 添加入库详细 库存数量
                    storage.setAmount(String.valueOf("".equals(backNum[i])?"0":backNum[i]));
                    // 添加入库详细 入库数量
                    storage.setOutputAmount(BigDecimal.valueOf(Double.valueOf("".equals(backNum[i])?"0":backNum[i])));
                    // 添加入库详细 状态为1 状态（新：0，回收：1，报废：2）
                    storage.setStatus((short) 1);
                    // 添加入库详细 是否删除(0 未删除 1已删除)
                    storage.setIsdeleted((short) 0);
                    list.add(storage);
                }
            }
            recycleService.updateRecycleAndMaterialRecovery(recycle, list);
        }catch (Exception e){
            ajaxJson.setMsg(e.getMessage());
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * 显示物资回收添加页面
     * @return
     */
    @RequestMapping(params = "showAdd")
    public String showAdd(){
        return "jzwz/MaterialRecycling/MaterialRecyclingAdd";
    }

    /**
     * 添加物资回收数据
     * @param recycle
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "addRecycle")
    public AjaxJson addRecycle(RepSubstanceRecycleEntity recycle, HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();

        // 回收数量
        String[] backNum = request.getParameterValues("backNum");
        // 物资id
        String[] uuid = request.getParameterValues("uuid");
        // 入库详细id
        String[] storageDetail = request.getParameterValues("storageDetail");


        List<RepStorageDetailEntity> list = new ArrayList<RepStorageDetailEntity>();

        try {
            if (uuid != null){
                for (int i = 0; i < uuid.length; i++){

                    RepStorageDetailEntity storage = new RepStorageDetailEntity();
                    // 添加入库详细 物资信息id
                    storage.setMessageid(uuid[i]);
                    // 添加入库详细 入库详细id
                    storage.setStorageDetail(storageDetail[i]);
                    // 添加入库详细 库存数量
                    storage.setAmount(String.valueOf("".equals(backNum[i])?"0":backNum[i]));
                    // 添加入库详细 入库数量
                    storage.setOutputAmount(BigDecimal.valueOf(Double.valueOf("".equals(backNum[i])?"0":backNum[i])));
                    // 添加入库详细 状态为1 状态（新：0，回收：1，报废：2）
                    storage.setStatus((short) 1);
                    // 添加入库详细 是否删除(0 未删除 1已删除)
                    storage.setIsdeleted((short) 0);
                    list.add(storage);
                }
            }
            recycleService.addRecycleAndMaterialRecovery(recycle, list);
        }catch (Exception e){
            ajaxJson.setMsg(e.getMessage());
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * 查看物资回收
     * @param model
     * @param uuid 根据uuid查询
     * @return
     */
    @RequestMapping(params = "lookRecycle")
    public String lookRecycle(Model model, String uuid){
        // 获取物资回收信息
        RepSubstanceRecycleEntity recycle = recycleService.lookRecycle(uuid);
        if (recycle != null){
            // 获取 物资回收详细信息
            List<Map<String, Object>> recyclingDetails = recycleService.recyclingDetailsGet(recycle.getUuid());
            model.addAttribute("recyclingDetails", recyclingDetails);
        }
        model.addAttribute("recycle", recycle);

        return "jzwz/MaterialRecycling/MaterialRecyclingLook";
    }

    /**
     * 删除物资回收
     * @param uuid 根据uuid删除
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "deleteRecycles")
    public AjaxJson deleteRecycles(String[] uuid){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            recycleService.deleteRecycles(uuid);
        }catch (Exception e){
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * 查询仓库结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "selectWarehouse")
    public String selectWarehouse(Model model){
        // 获取数据字典库房类型
        List<TSType> items = typeServiceImpl.getItems(CommonCode.WAREHOUSE_TYPE);
        //取库房类别列表
        List<RepAccessRepository> repositories = warehouseService.repositoryList();
        model.addAttribute("items", items);
        model.addAttribute("list", repositories);
        return "jzwz/MaterialRecycling/SelectWarehouse";
    }

    /**
     * 查询仓库结果集 模糊查询
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping(params = "listQuery")
    public String listQuery(WarehouseQueryVo vo, Model model){
        // 获取数据字典库房类型
        List<TSType> items = typeServiceImpl.getItems(CommonCode.WAREHOUSE_TYPE);
        // 取库房类别列表
        List<RepAccessRepository> repositories = warehouseService.repositoryListByQuery(vo);
        model.addAttribute("items", items);
        model.addAttribute("list", repositories);
        model.addAttribute("vo", vo);
        return "jzwz/MaterialRecycling/SelectWarehouse";
    }

    /**
     * 查询物资信息结果集
     * @param model
     * @return
     */
    @RequestMapping(params = "materialInfoSelect")
    public String materialInfoSelect(Model model, String outHouseId){
        List<Map<String, Object>> allRepSubstanceMessage = repOuthouseDetailService.getByOuthouseId(outHouseId);
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        model.addAttribute("outHouseId", outHouseId);
        return "jzwz/MaterialRecycling/MaterialInfoManagementSelect";
    }

    /**
     * 查询物资信息结果集 模糊搜索
     * @param model
     * @param repSubstanceMessage
     * @param outHouseId 出库单
     * @return
     */
    @RequestMapping(params = "searchRepSubstanceMessage")
    public String searchRepSubstanceMessage(Model model, RepSubstanceMessage repSubstanceMessage, String outHouseId){
        // 获取条件
        String subCategory = repSubstanceMessage.getSubCategory();
        String subName = repSubstanceMessage.getSubName();
        String subNumber = repSubstanceMessage.getSubNumber();
        // 查询类别内容
        RepSubstanceCategory repSubstanceCategory = materialRepSubstanceMessageService.findRepSubstanceCategoryByName(subCategory);
        String subCatagory = "";
        if(repSubstanceCategory!=null){
            subCatagory= repSubstanceCategory.getUuid();
        }
        // 查询物资内容
        List<Map<String, Object>> allRepSubstanceMessage = recycleService.searchRepSubstanceMessage(subNumber, subName, subCatagory, outHouseId);
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        model.addAttribute("subNumber", subNumber);
        model.addAttribute("subName",subName);
        model.addAttribute("subCategory",subCategory);
        model.addAttribute("outHouseId", outHouseId);
        return "jzwz/MaterialRecycling/MaterialInfoManagementSelect";
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
        return "jzwz/MaterialRecycling/SelectMaterialCategory";
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
        return "jzwz/MaterialRecycling/SelectMaterialCategory";
    }


}
