package com.jeecg.MaterialsInPut.controller;

import com.jeecg.MaterialsInPut.common.MaterialsInPutVo;
import com.jeecg.MaterialsInPut.common.RepSubstanceMessageVo;
import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsInPut.entity.RepSubstanceStorageEntity;
import com.jeecg.MaterialsInPut.service.MaterialsInputService;
import com.jeecg.system_management.common.CommonCode;
import com.jeecg.system_management.common.WarehouseQueryVo;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.service.MaterialRepSubstanceMessageService;
import com.jeecg.system_management.service.WarehouseService;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("materialsInputController")
public class MaterialsInputController extends BaseController {

    @Resource
    private MaterialsInputService materialsInputService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private TypeServiceI typeServiceImpl;

    @Resource
    private MaterialRepSubstanceMessageService materialRepSubstanceMessageService;

    /**
     * 物资入库列表
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "queryAll")
    public String queryAll(Model model, MaterialsInPutVo vo) {
        List<Map<String, Object>> maps = materialsInputService.queryAll(vo);
        model.addAttribute("maps", maps);
        model.addAttribute("vo", vo);
        return "jzwz/MaterialsInPut/InStockList";
    }

    /**
     * 物资入库查看信息
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "inStockLook")
    public String inStockLook(Model model, String ctrl, String uuid) {
        //物资入库详细和物资信息
        List<Map<String, Object>> list = materialsInputService.inStockLook(uuid);

        //物资入库
        Map<String, Object> map = materialsInputService.queryOne(uuid);
        //查询数据字典
        List<TSType> purpose = typeServiceImpl.getItems(CommonCode.JZ_PURPOSE);
        List<TSType> source = typeServiceImpl.getItems(CommonCode.WZ_SOURCE);
        //放入model
        model.addAttribute("purpose", purpose);
        model.addAttribute("source", source);
        model.addAttribute("list", list);
        model.addAttribute("repSubstanceStorageEntity", map);

        if ("query".equals(ctrl)) {
            return "jzwz/MaterialsInPut/InStockLook";
        } else {
            return "jzwz/MaterialsInPut/InStockEdit";
        }
    }

    /**
     * 进入新增页面
     */
    @RequestMapping(params = "toAdd2")
    public String toAdd2(Model model) {
        //查询数据字典
        List<TSType> purpose = typeServiceImpl.getItems(CommonCode.JZ_PURPOSE);
        List<TSType> source = typeServiceImpl.getItems(CommonCode.WZ_SOURCE);
        model.addAttribute("purpose", purpose);
        model.addAttribute("source", source);
        return "jzwz/MaterialsInPut/add2";
    }

    /**
     * 新增或修改
     *
     * @return
     */
    @RequestMapping(params = "saveOrupdate")
    @ResponseBody
    public AjaxJson saveOrupdate(RepSubstanceStorageEntity repSubstanceStorageEntity, HttpServletRequest request, String ctrl,
                                 @RequestParam("file") MultipartFile file, Model model) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtil.isEmpty(repSubstanceStorageEntity.getStorageWarehouse())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("入库仓库不能为空");
            return ajaxJson;
        }
        if (StringUtil.isEmpty(repSubstanceStorageEntity.getSubstanceSource())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("物资来源不能为空");
            return ajaxJson;
        }
        if (StringUtil.isEmpty(repSubstanceStorageEntity.getSourceObject())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("来源对象不能为空");
            return ajaxJson;
        }
        if (StringUtil.isEmpty(repSubstanceStorageEntity.getStoragePerson())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("入库人不能为空");
            return ajaxJson;
        }
        if (repSubstanceStorageEntity.getStorageDate() == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("入库日期不能为空");
            return ajaxJson;
        }
        if (StringUtil.isEmpty(repSubstanceStorageEntity.getDonateArea())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("捐赠区域不能为空");
            return ajaxJson;
        }
        //获取物资入库详细uuid
        String[] minuteids = request.getParameterValues("minuteid");
        //获取物资信息uuid
        String[] messageids = request.getParameterValues("messageid");
        //获取物资单价(用于计算总价,不需要存入数据库)
        String[] prices = request.getParameterValues("price");
        //获取入库数量
        String[] outputAmounts = request.getParameterValues("outputAmount");
        //=================================================这三个日期需要验证
        //获取购置日期
        String[] purchaseDates = request.getParameterValues("purchaseDate");
        //获取生产日期
        String[] manufactureDates = request.getParameterValues("manufactureDate");
        //获取过保日期
        String[] overinsuredDates = request.getParameterValues("overinsuredDate");

        String delUuid = request.getParameter("del_uuid");

        String replace = repSubstanceStorageEntity.getSourceObject().replace(",", "");
        repSubstanceStorageEntity.setSourceObject(replace);

        if (!StringUtil.isEmpty(file.getOriginalFilename())) {
            //文件上传
            try {
                //取文件名
                String originalFilename = file.getOriginalFilename();
                //取文件后缀
                String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
                //拼写图片url
                String path = request.getServletContext().getRealPath("/fileUpload");
                String fileName = UUIDGenerator.generate() + extName;
                File file1 = new File(path, fileName);
                file.transferTo(file1);
                //文件信息(原文件名，现文件存放路径及名称)
                RepSubstanceAccessory accessory = new RepSubstanceAccessory();
                accessory.setAccessoryName(originalFilename);
                accessory.setAccessoryPath(path + "\\" + fileName);
                //新增附件信息，将返回的uuid填充到物资入库信息中，使其关联
                String uuid = materialsInputService.saveAccessoryMsg(accessory);
                //填充附件信息Uuid
                repSubstanceStorageEntity.setAccessory(uuid);
            } catch (Exception e) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("文件上传失败");
                return ajaxJson;
            }
        }
        try {
            //保存物资入库和物资入库详细
            materialsInputService.saveOrupdateInput(repSubstanceStorageEntity, minuteids, messageids, prices, outputAmounts, purchaseDates, manufactureDates, overinsuredDates, delUuid, ctrl);
            materialsInputService.updAllocation(repSubstanceStorageEntity.getSourceObject());
        } catch (BusinessException e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("发生未知错误");
            e.printStackTrace();
        }

        return ajaxJson;
    }

    /**
     * 删除
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public AjaxJson delete(String uuids) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtil.isEmpty(uuids)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除失败");
        } else {
            String[] delUuids = uuids.split("-");
            //执行批量删除
            try {
                materialsInputService.delBatch(delUuids);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("删除成功");
            } catch (Exception e) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("删除失败");
                e.printStackTrace();
            }
        }
        return ajaxJson;
    }

    /**
     * 进入选择仓库
     */
    @RequestMapping(params = "selectWarehouse")
    public String toSelectWarehouse(Model model) {
        List<RepAccessRepository> repositories = warehouseService.repositoryList();
        List<TSType> warehouseType = typeServiceImpl.getItems(CommonCode.WAREHOUSE_TYPE);
        model.addAttribute("warehouseType", warehouseType);
        model.addAttribute("repositories", repositories);
        return "jzwz/MaterialsInPut/SelectWarehouse";
    }

    /**
     * 选择仓库条件查询
     */
    @RequestMapping(params = "conditionalQueryWarehouse")
    public String selectWarehouse(Model model, WarehouseQueryVo vo) {
        List<RepAccessRepository> repositories;
        repositories = warehouseService.repositoryListByQuery(vo);
        List<TSType> warehouseType = typeServiceImpl.getItems(CommonCode.WAREHOUSE_TYPE);
        model.addAttribute("warehouseType", warehouseType);
        model.addAttribute("repositories", repositories);
        model.addAttribute("repositories", repositories);
        model.addAttribute("vo", vo);
        return "jzwz/MaterialsInPut/SelectWarehouse";
    }

    /**
     * 修改-->查看(根据物资入库详细uuid查询物资入库详细和物资信息)
     *
     * @return
     */
    @RequestMapping(params = "inStockAddLook")
    public String toInStockAddLook(Model model, String uuid) {
        Map<String, Object> map = materialsInputService.queryMinute(uuid);
        model.addAttribute("map", map);
        return "jzwz/MaterialsInPut/InStockAddLook";
    }

    /**
     * 文件下载
     *
     * @param uuid
     * @param request
     * @param response
     */
    @RequestMapping(params = "fileDown")
    public void fileDown(String uuid, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtil.isEmpty(uuid)) {
            try {
                RepSubstanceAccessory fileById = materialsInputService.findFileById(uuid);
                String path = fileById.getAccessoryPath();
                String accessoryName = fileById.getAccessoryName();
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping(params = "inStockAdd")
    public String toInStockAdd() {
        return "jzwz/MaterialsInPut/InStockAdd";
    }

    /**
     * 选择区划页面
     *
     * @return
     */
    @RequestMapping(params = "selectDiv")
    public String showSelectDiv() {
        return "jzwz/CommonPage/SelectDiv";
    }

    /**
     * 根据调拨通知id查询调拨物资信息
     */
    @RequestMapping(params = "substance")
    @ResponseBody
    public List<Map<String, Object>> substance(String informid) {
        List<Map<String, Object>> substance = materialsInputService.querySubstance(informid);
        return substance;
    }

    /**
     * 调拨通知查询列表
     * @param input 入库
     * @param output 出库
     * @param model
     * @return
     */
    @RequestMapping(params = "seachAllocation")
    public String seachAllocation(String input, String output, Model model) {
        List<Map<String, Object>> allocation = materialsInputService.seachAllocation(input, output);
        model.addAttribute("allocation", allocation);
        return "jzwz/AllocationNotice/SelectAllocationNoticeList";
    }

    /**
     * 查询物资信息结果集 模糊搜索
     * @return
     */
    @RequestMapping(params = "searchRepSubstanceMessage")
    public String searchRepSubstanceMessage(Model model, RepSubstanceMessageVo repSubstanceMessageVo, String selUuids){
        
        List<Map<String, Object>> allRepSubstanceMessage = materialsInputService.searchRepSubstanceMessage(repSubstanceMessageVo,selUuids);
        model.addAttribute("substanceMessages", allRepSubstanceMessage);
        model.addAttribute("vo", repSubstanceMessageVo);
        model.addAttribute("selUuids", selUuids);
        return "jzwz/MaterialsInPut/MaterialInfoManagementSelect";
    }
}
