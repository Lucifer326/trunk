package com.jeecg.allocation_notice.controller;

import com.jeecg.allocation_notice.entity.RepAllotInform;
import com.jeecg.allocation_notice.entity.RepInformDetail;
import com.jeecg.allocation_notice.service.NoticeAllocationServiceI;
import com.jeecg.system_management.common.CommonCode;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import com.jeecg.system_management.service.WarehouseService;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;



@Scope("prototype")
@Controller
@RequestMapping("/NoticeController")
/**
 *@author deng_fuhai
 */
public class NoticeAllocationController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(NoticeAllocationController.class);

    /**
     * 库房 接口
     */
    @Autowired
    private WarehouseService warehouseService;
    /**
     * serviceImpl接口
     */
    @Autowired
    private NoticeAllocationServiceI noticeAllocationService;
    /**
     *数据字典接口
     */
    @Autowired
    private TypeServiceI typeServiceImpl;
    /**
     * 功能描述: 展示调拨列表
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        String pagestr = request.getParameter("page");
        int page=1;
        if(pagestr!=""&&pagestr!=null){
            page =Integer.parseInt(pagestr);
        }
        int rows=10;
        String rowsstr = request.getParameter("rows");
        if(rowsstr!=""&&rowsstr!=null){
            rows =Integer.parseInt(rowsstr);
        }
        //不知道是啥
        String where="";
        String order="";
        List<RepAllotInform> list = noticeAllocationService.getList(page, rows, where, order);
        System.out.println(list);
        request.setAttribute("list",list);
        return new ModelAndView("jzwz/AllocationNotice/AllocationNoticeList");
    }

    /**
     * 功能描述:查看调拨单据号的详细信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param request
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(params = "look")
    public String chakan(HttpServletRequest request, Model model) {

        String uuid = request.getParameter("uuid");
        if(uuid==null&&"".equals(uuid)){
            return "jzwz/AllocationNotice/AllocationNoticeLook";
        }
        //根据调拨通知的uuid获得对应的调拨详情中的对象repAllotInformGetByid
        RepAllotInform repAllotInform1 = noticeAllocationService.repAllotInformGetByid(uuid);
        RepAccessRepository repAccessRepository = noticeAllocationService.respoityGet(repAllotInform1.getRepositoryid());
        List<Map<String, Object>> maps = noticeAllocationService.detailGet(repAllotInform1.getUuid());
        model.addAttribute("acc",repAccessRepository);
        model.addAttribute("rep",repAllotInform1);
        model.addAttribute("map",maps);
        return "jzwz/AllocationNotice/AllocationNoticeLook";
    }

    /**
     * 功能描述:添加调拨通知的信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(params = "datalist")
    public String noticeDataParation(Model model) {

        return "jzwz/AllocationNotice/MaterialAllocationAdd";
    }

    /**
     * 功能描述:仓储机构的信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(params = "repisorityhouse")
    public String cangchudata(Model model) {
        List<RepAccessRepository> repositories = warehouseService.repositoryList();
        model.addAttribute("list", repositories);
        //库房类型数据字典的准备
        List<TSType> items = typeServiceImpl.getItems(CommonCode.WAREHOUSE_TYPE);
        model.addAttribute("approval_status", items);
        return "jzwz/AllocationNotice/SelectWarehouse";
    }

    /**
     * 功能描述:添加调拨通知的信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param repAllotInform
     * @param request
     * @return org.jeecgframework.core.common.model.json.AjaxJson
     */
    @RequestMapping(params = "add")
    @ResponseBody
    public AjaxJson tianjia(RepAllotInform repAllotInform, HttpServletRequest request ) {
        AjaxJson ajaxJson = new AjaxJson();

            //存放着物资信息的uuid
            String[] messageid = request.getParameterValues("messageid");

            String[] priceTotal = request.getParameterValues("priceTotal");
            String[] allotAmount = request.getParameterValues("allotAmount");


            List<RepInformDetail> objects = new ArrayList<RepInformDetail>();
        try {
            if(messageid!=null&&messageid.length>0){
                for (int i=0;i<messageid.length;i++) {

                    if(messageid[i]!=null&&!"".equals(messageid[i])){
                        RepInformDetail repInformDetail =new RepInformDetail();

                        repInformDetail.setMessageid(messageid[i]);

                        repInformDetail.setPriceTotal(Long.parseLong("".equals(priceTotal[i])?"0":priceTotal[i]));

                        repInformDetail.setAllotAmount(Long.parseLong("".equals(allotAmount[i])?"0":allotAmount[i]));

                        objects.add(repInformDetail);
                    }

                }
            }else{
                RepInformDetail repInformDetail =new RepInformDetail();


                repInformDetail.setPriceTotal(new Long(0));
                repInformDetail.setAllotAmount(new Long(0));
                objects.add(repInformDetail);

            }



            ajaxJson = noticeAllocationService.noticeSave(repAllotInform, objects);

        }catch (Exception e){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }

        return ajaxJson;

    }

    /**
     * 功能描述:修改调拨通知页面的数据准备
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(params = "dataParUpdate")
    public String dataParUpdate(Model model,HttpServletRequest request) {

        //获得调拨详情中的uuid
        String uuid = request.getParameter("uuid");
        if(uuid==null&&"".equals(uuid)){
            return "jzwz/AllocationNotice/AllocationEdit";
        }
        //根据调拨通知的uuid获得对应的调拨详情中的对象repAllotInformGetByid
        RepAllotInform repAllotInform1 = noticeAllocationService.repAllotInformGetByid(uuid);

        List<Map<String, Object>> maps = noticeAllocationService.detailGet(repAllotInform1.getUuid());
        //需要调拨通知对应的仓储机构
        RepAccessRepository repAccessRepository = noticeAllocationService.respoityGet(repAllotInform1.getRepositoryid());
        model.addAttribute("jigou",repAccessRepository);
        model.addAttribute("rep",repAllotInform1);
        model.addAttribute("map",maps);
        System.out.println(maps);

        return "jzwz/AllocationNotice/NoticeAllocationEdit";
    }


   /**
    * 功能描述: 更新数据
    * @author deng_fuhai
    * @date 2019/4/11 0011
    * @param repAllotInform
    * @param request
    * @return org.jeecgframework.core.common.model.json.AjaxJson
    */
    @RequestMapping(params = "updateNotice")
    @ResponseBody
    public AjaxJson updateNotice(RepAllotInform repAllotInform,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
         int messageStart=2;
        //获得表单提交的更新的数据
        //1.获得原来的物资信息主键id
        //需要更新的的物资详情uuid
        ArrayList<String> updstr = new ArrayList<>();
        String[] uuid = request.getParameterValues("uuid");
        if(uuid!=null&&!"".equals(uuid)&&uuid.length>=messageStart){
            for (int i=0;i<uuid.length-1;i++){
                updstr.add(uuid[i+1]);
            }
        }
        //更改repAllotInform的uuid
        repAllotInform.setUuid(uuid[0]);
        //2.获得删除的原来的物资信息主键id（可能批量删除造成多个uuid拼串，先截取，再传值）
        //删除的物资详情uuid
        List<String> delstr = new ArrayList<>();
        String[] deluuids = request.getParameterValues("deluuid");
        if(deluuids!=null&&!"".equals(deluuids)){
        for (String str:deluuids ) {
            String[] split = str.split(",");
            for (String str1:split ){
                delstr.add(str1);
            }
        }}
        //获取更新页面的物资详情的信息
        String[] messageid = request.getParameterValues("xid");
        String[] priceTotal = request.getParameterValues("priceTotal");
        String[] allotAmount = request.getParameterValues("allotAmount");
        //4.按照顺序填充物资详情的信息（service层判断如果 有详情uuid就更新，没有就添加，）
        //更新的详情
        List<RepInformDetail> updobjects = new ArrayList<RepInformDetail>();
        //添加的详情
        List<RepInformDetail> addobjects = new ArrayList<RepInformDetail>();
        for (int i=0;i<updstr.size();i++) {
            RepInformDetail repInformDetail =new RepInformDetail();
            repInformDetail.setUuid(uuid[i+1]);
            //调拨详情的物资uuid
            repInformDetail.setInformid(repAllotInform.getUuid());
            repInformDetail.setMessageid(messageid[i]);
                if(priceTotal[i]!=null&&!"".equals(priceTotal[i])) {
                repInformDetail.setPriceTotal(Long.parseLong("".equals(priceTotal[i])?"0":priceTotal[i]));
            }
            if(allotAmount[i]!=null){

                repInformDetail.setAllotAmount(Long.parseLong("".equals(allotAmount[i])?"0":allotAmount[i]));
            }
            updobjects.add(repInformDetail);
        }
        if(messageid!=null){
        for (int i=updstr.size();i<messageid.length;i++) {
            RepInformDetail repInformDetail =new RepInformDetail();
            repInformDetail.setInformid(repAllotInform.getUuid());
            repInformDetail.setMessageid(messageid[i]);
            if(priceTotal[i]!=null&&!"".equals(priceTotal[i])) {
                repInformDetail.setPriceTotal(Long.parseLong("".equals(priceTotal[i])?"0":priceTotal[i]));
            }
            if(allotAmount[i]!=null) {
                repInformDetail.setAllotAmount(Long.parseLong("".equals(allotAmount[i])?"0":allotAmount[i]));
            }
            addobjects.add(repInformDetail);
        }
        }
        try {
            RepAllotInform repAllotInform1 = noticeAllocationService.repAllotInformGetByid(repAllotInform.getUuid());
            ajaxJson = noticeAllocationService.InformDetailupdate(repAllotInform,repAllotInform1, updobjects,addobjects,delstr);
            ajaxJson.setSuccess(true);
        }catch (Exception e){
           ajaxJson.setMsg(e.getMessage());
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    /**
     * 功能描述:删除调拨通知
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param model
     * @param request
     * @return org.jeecgframework.core.common.model.json.AjaxJson
     */
    @RequestMapping(params = "delNotice")
    @ResponseBody
    public AjaxJson delNotice(Model model,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String uuid = request.getParameter("uuid");
            String[] split = uuid.split(",");
            System.out.println(split);
            for (String str:split) {
                noticeAllocationService.InformdelByid(str);
            }
            ajaxJson.setMsg("删除成功");
            ajaxJson.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    /**
     * 功能描述: 根据调拨单据号模糊查询列表
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param model
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(params = "showlikeBydanjuhao")
    public String show(Model model,HttpServletRequest request) {
        String diaobodanjuhao = request.getParameter("diaobodanjuhao");

        String project = request.getParameter("project");

        String date = request.getParameter("date");
        List<RepAllotInform> list = noticeAllocationService.NoticelistLike(diaobodanjuhao,project,date);
        System.out.println(list);
        request.setAttribute("list",list);
        request.setAttribute("diaobodanjuhao",diaobodanjuhao);
        request.setAttribute("project",project);
        Date date1 = DateUtils.str2Date(date, new SimpleDateFormat("yyyy-MM-dd"));
        request.setAttribute("date",date1);


        return "jzwz/AllocationNotice/AllocationNoticeList";
    }


}
