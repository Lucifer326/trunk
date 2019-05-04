package com.jeecg.system_management.controller;


import com.fr.third.org.apache.poi.hssf.usermodel.HSSFCell;
import com.fr.third.org.apache.poi.hssf.usermodel.HSSFRow;
import com.fr.third.org.apache.poi.hssf.usermodel.HSSFSheet;
import com.fr.third.org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.jeecg.system_management.common.FileUtils;
import com.jeecg.system_management.dao.WarehouseDao;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.service.MaterialReliefFormService;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王国强
 *  系统管理下的救灾仓库库存管理功能
 */
@Controller
@RequestMapping("MaterialReliefManagement")
public class MaterialReliefFormController  extends BaseController {

    @Autowired
    private MaterialReliefFormService materialReliefFormService;
    @Autowired
    private WarehouseDao WarehouseDao;
    /**
     * 展示库存管理页面
     * @param model
     * @return
     */
    @RequestMapping(params = "materialReliefFormList")
    public String materialReliefFormList(Model model){
        List<Map<String,Object>> list = materialReliefFormService.findMaterialReliefFormList();
        TSUser tsUser = ResourceUtil.getSessionUserName();
        List<RepAccessRepository> repAccessRepositories = WarehouseDao.repositoryList(tsUser.getId());
        model.addAttribute("repAccessRepositories",repAccessRepositories);
        model.addAttribute("materialReliefFormList",list);
        return "jzwz/MaterialManagement/MaterialReliefManagement/MaterialReliefManagementForm";
    }
    /**
     * 根据条件去查询相应的信息
     * @param model
     * @param organizationClassify
     * @param repositoryName
     * @return
     */
    @RequestMapping(params = "materialReliefFormListLike")
    public String materialReliefFormListLike(Model model,String organizationClassify, String repositoryName){
        List<Map<String,Object>> list = materialReliefFormService.findMaterialReliefFormListLike(organizationClassify,repositoryName);
        TSUser tsUser = ResourceUtil.getSessionUserName();
        List<RepAccessRepository> repAccessRepositories = WarehouseDao.repositoryList(tsUser.getId());
        model.addAttribute("repAccessRepositories",repAccessRepositories);
        model.addAttribute("materialReliefFormList",list);
        //model.addAttribute("organizationClassify",organizationClassify);
        //model.addAttribute("repositoryName",repositoryName);
        return "jzwz/MaterialManagement/MaterialReliefManagement/MaterialReliefManagementForm";
    }
    /**
     * 导出功能
     * @param model
     * @return
     */
    @RequestMapping(params = "exportExcel")
    public void exportExcel(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String,Object>> list = materialReliefFormService.findMaterialReliefFormList();
        //在内存中创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("库存数据");
        //创建表头
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell((short) 0).setCellValue("仓库名称");
        headRow.createCell((short) 1).setCellValue("物资品名");
        headRow.createCell((short) 2).setCellValue("规格型号");
        headRow.createCell((short) 3).setCellValue("物资类别");
        headRow.createCell((short) 4).setCellValue("物资单位");
        headRow.createCell((short) 5).setCellValue("单价（元）");
        headRow.createCell((short) 6).setCellValue("供应商");
        //headRow.createCell((short) 7).setCellValue("购置日期");
        //headRow.createCell((short) 8).setCellValue("生产日期");
        //headRow.createCell((short) 9).setCellValue("过保日期");
        headRow.createCell((short) 7).setCellValue("总金额");
        headRow.createCell((short) 8).setCellValue("入库数量");
        for (Map<String,Object> map:list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            String REPOSITORY_NAME = (String)map.get("REPOSITORY_NAME");
            String SUB_NAME = (String)map.get("SUB_NAME");
            String SPECIFICATION_TYPE =(String)map.get("SPECIFICATION_TYPE");
            String CATEGORY_NAME = (String)map.get("CATEGORY_NAME");
            String UNIT = (String) map.get("UNIT");
            BigDecimal PRICE = (BigDecimal ) map.get("PRICE");
            String SUPPLIER = (String)map.get("SUPPLIER");
            Date PURCHASE_DATE = (Date) map.get("PURCHASE_DATE");
            //String PURCHASE = sdf.format(PURCHASE_DATE).toString();
            Date MANUFACTURE_DATE = (Date)map.get("MANUFACTURE_DATE");
            //String MANUFACTURE = sdf.format(MANUFACTURE_DATE).toString();
            Date OVERINSURED_DATE = (Date)map.get("OVERINSURED_DATE");
            //String OVERINSURED = sdf.format(OVERINSURED_DATE).toString();
            BigDecimal TOTAL_PRICES = (BigDecimal)map.get("TOTAL_PRICES");
            BigDecimal OUTPUT_AMOUNT = (BigDecimal)map.get("OUTPUT_AMOUNT");
            dataRow.createCell((short) 0).setCellValue(REPOSITORY_NAME);
            dataRow.createCell((short) 1).setCellValue(SUB_NAME);
            dataRow.createCell((short) 2).setCellValue(SPECIFICATION_TYPE);
            dataRow.createCell((short) 3).setCellValue(CATEGORY_NAME);
            dataRow.createCell((short) 4).setCellValue(UNIT);
            dataRow.createCell((short) 5).setCellValue(PRICE.toString());
            dataRow.createCell((short) 6).setCellValue(SUPPLIER);
            //dataRow.createCell((short) 7).setCellValue(PURCHASE_DATE);
            //dataRow.createCell((short) 8).setCellValue(MANUFACTURE_DATE);
            //dataRow.createCell((short) 9).setCellValue(OVERINSURED_DATE);
            dataRow.createCell((short) 10).setCellValue(TOTAL_PRICES.toString());
            dataRow.createCell((short) 11).setCellValue(OUTPUT_AMOUNT.toString());
        }
        //第三步：使用输出流进行文件下载（一个流、两个头）
        String filename = "救灾仓库库存管理数据.xls";
        ServletContext servletContext = request.getServletContext();
        String contentType = request.getServletContext().getMimeType(filename);
        try (ServletOutputStream out = response.getOutputStream()) {
            response.setContentType(contentType);
            //获取客户端浏览器类型
            String agent = request.getHeader("User-Agent");
            filename = FileUtils.encodeDownloadFilename(filename, agent);
            response.setHeader("content-disposition", "attachment;filename=" + filename);
            workbook.write(out);
        }

    }
}
