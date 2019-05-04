package com.jeecg.MaterialsOutPut.controller;

import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsInPut.service.MaterialsInputService;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutLogicException;
import com.jeecg.MaterialsOutPut.common.MaterialsOutPutQueryVo;
import com.jeecg.MaterialsOutPut.service.RepOuthouseDetailService;
import com.jeecg.MaterialsOutPut.service.RepSubstanceOuthouseService;
import com.jeecg.system_management.common.CommonCode;
import com.jeecg.system_management.common.WarehouseQueryVo;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用来跳转页面的控制器
 * @author 甄磊超
 * @date 2019/4/2
 * @Description com.jeecg.MaterialsOutPut.controller
 */
@Scope("prototype")
@Controller
@RequestMapping("/MaterialsOutPut")
public class MaterialsOutPutDispatcherController {

    @Autowired
    private RepSubstanceOuthouseService repSubstanceOuthouseService;

    @Autowired
    private MaterialsInputService materialsInputService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private RepOuthouseDetailService repOuthouseDetailService;

    @Autowired
    private TypeServiceI typeServiceImpl;

    private static Logger logger = Logger.getLogger(MaterialsOutPutDispatcherController.class);

    @RequestMapping(params = "AllocateValid")
    public String toAllocateValidPage(Model model){
        List<TSType> items = typeServiceImpl.getItems(CommonCode.SP_STATUS);
        model.addAttribute("approval_status",items);
        return "jzwz/MaterialsOutPut/AllocateValid";
    }

    @RequestMapping(params = "MaterialsLook")
    public String toMaterialsLookPage(@RequestParam(value = "uuid") String uuid, Model model){
        Map<String, Object> detil = repOuthouseDetailService.getByDetilId(uuid).get(0);
        //将其中数据使用数据字典中的值进行替换
        //是否
        for (TSType type:typeServiceImpl.getItems(CommonCode.SF_YN)) {
            if (type.getTypecode().equals(detil.get("isrecycle"))){
                detil.put("isrecycle",type.getTypename());
                break;
            }
        }
        //物资来源
        for (TSType type:typeServiceImpl.getItems(CommonCode.WZ_SOURCE)) {
            if (type.getTypecode().equals(detil.get("substance_source"))){
                detil.put("substance_source",type.getTypename());
                break;
            }
        }
        //捐赠用途
        for (TSType type:typeServiceImpl.getItems(CommonCode.JZ_PURPOSE)) {
            if (type.getTypecode().equals(detil.get("donation_purposes"))){
                detil.put("donation_purposes",type.getTypename());
                break;
            }
        }
        model.addAttribute("detil",detil);
        return "jzwz/MaterialsOutPut/MaterialsLook";
    }

    @RequestMapping(params = "MaterialsOutPutAdd")
    public String toMaterialsOutPutAddPage(){
        return "jzwz/MaterialsOutPut/MaterialsOutPutAdd";
    }

    @RequestMapping(params = "MaterialsOutPutEdit")
    public String toMaterialsOutPutEditPage(String uuid,Model model){
        if (StringUtils.isNotBlank(uuid)){
            try{
                Map<String, Object> detilById = repSubstanceOuthouseService.getDetilById(uuid);
                //获得附件的id去查询他的名字显示到页面上
                String accessoryId = (String)detilById.get("ACCESSORY");
                RepSubstanceAccessory accessory = repSubstanceOuthouseService.findAccessoryById(accessoryId);
                if(accessory!=null){
                    detilById.put("ACCESSORY",accessory.getAccessoryName());
                }
                model.addAttribute("outPut",detilById);
                String outhouseid = (String) detilById.get("uuid");
                List<Map<String, Object>> details = repOuthouseDetailService.getByOuthouseId(outhouseid);
                model.addAttribute("details",details);
            }catch (MaterialsOutPutLogicException e){

            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return "jzwz/MaterialsOutPut/MaterialsOutPutEdit";
    }

    @RequestMapping(params = "MaterialsOutPutList")
    public String toMaterialsOutPutListPage(MaterialsOutPutQueryVo vo, Model model){
        List<TSType> items = typeServiceImpl.getItems(CommonCode.SP_STATUS);
        model.addAttribute("approved",items.get(0));//审批通过数据字典
        model.addAttribute("rejection",items.get(1));//审批驳回数据字典
        if (vo == null){
            vo = new MaterialsOutPutQueryVo();
        }
        try{
            model.addAttribute("outPutList",repSubstanceOuthouseService.getAllByCondition(vo));
        }catch (MaterialsOutPutLogicException e){

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        model.addAttribute("vo",vo);
        return "jzwz/MaterialsOutPut/MaterialsOutPutList";
    }

    @RequestMapping(params = "MaterialsOutPutLook")
    public String toMaterialsOutPutLookPage(String uuid,Model model){
        if (StringUtils.isNotBlank(uuid)){
            try{
                Map<String,Object> detilById = repSubstanceOuthouseService.getDetilById(uuid);
                //获得附件的id去查询他的名字显示到页面上
                String accessoryId = (String)detilById.get("ACCESSORY");
                RepSubstanceAccessory accessory = repSubstanceOuthouseService.findAccessoryById(accessoryId);
                if(accessory!=null){
                    detilById.put("ACCESSORY",accessory.getAccessoryName());
                }
                model.addAttribute("outPut",detilById);
                String outhouseid = (String) detilById.get("uuid");
                List<Map<String, Object>> details = repOuthouseDetailService.getByOuthouseId(outhouseid);
                model.addAttribute("details",details);
            }catch (MaterialsOutPutLogicException e){

            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return "jzwz/MaterialsOutPut/MaterialsOutPutLook";
    }


    /**
     * 选择物资
     * @return
     */
    @RequestMapping(params = "MaterialsSelect")
    public String toMaterialsSelectPage(){

        return "jzwz/MaterialsOutPut/MaterialsSelect";
    }

    @RequestMapping(params = "SelectStorage")
    public String toSelectStoragePage(String warehouseId,String subId,Model model){
        //调用入库的service方法查询数据显示在页面
        model.addAttribute("storage",materialsInputService.queryDetail(warehouseId,subId));
        return "jzwz/MaterialsOutPut/SelectStorage";
    }

    @RequestMapping(params = "SelectAllocationNoticeList")
    public String toSelectAllocationNoticeListPage(){
        return "jzwz/MaterialsOutPut/SelectAllocationNoticeList";
    }

    @RequestMapping(params = "WareHouseSelect")
    public String toWareHouseSelectPage(WarehouseQueryVo vo,Model model){
        List<RepAccessRepository> repositories = warehouseService.repositoryListByQuery(vo);
        model.addAttribute("repositories", repositories);
        model.addAttribute("vo", vo);
        return "jzwz/MaterialsOutPut/WareHouseSelect";
    }

    /**
     * @auther wangguoqiang
     * @param model
     * @return
     */
    @RequestMapping(params = "materialCategory")
    public String materialCategory(Model model,String uuid){
        List<Map<String, Object>> allRepSubstanceMessage = repOuthouseDetailService.findAllRepSubstanceMessage(uuid);
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        return "jzwz/MaterialsOutPut/MaterialInfoManagementSelect";
    }
    /**
     * @author wangguoqiang
     *
     */
    @RequestMapping(params = "fileDown")
    public void fileDown(String uuid, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtil.isEmpty(uuid)) {
            Map<String, Object> detil = repSubstanceOuthouseService.getDetilById(uuid);
            String accessoryId = (String) detil.get("ACCESSORY");
            RepSubstanceAccessory accessory = new RepSubstanceAccessory();
            try {
                if (accessoryId != null || !"".equals(accessoryId)) {
                    accessory = repSubstanceOuthouseService.findAccessoryById(accessoryId);

                String path = accessory.getAccessoryPath();
                String accessoryName = accessory.getAccessoryName();
                // path是指欲下载的文件的路径。
                File file = new File(path);
                // 取得文件名。
                String filename = file.getName();
                // 取得文件的后缀名。
                String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(path));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(accessoryName.getBytes(), StandardCharsets.ISO_8859_1));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        }
        }
    }

