package com.jeecg.allocation_notice.service;

import com.jeecg.allocation_notice.entity.RepAllotInform;
import com.jeecg.allocation_notice.entity.RepInformDetail;
import com.jeecg.system_management.pojo.RepAccessRepository;
import com.jeecg.system_management.pojo.RepSubstanceMessage;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;

import java.util.List;
import java.util.Map;

/**
 * @author fuhai_deng
 * @Description:com.jeecg.allocation_notice.service
 */
public interface NoticeAllocationServiceI extends CommonService {

    /**
     * 功能描述:查看调拨单的列表对象
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param page
     * @param rows
     * @param where
     * @param order
     * @return java.util.List<com.jeecg.allocation_notice.entity.RepAllotInform>
     */
    public List<RepAllotInform> getList(int page, int rows, String where, String order);

    /**
     * 功能描述:查询调拨单的调拨详情
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public List<Map<String,Object>> detailGet(String uuid);

    /**
     * 功能描述:添加调拨通知的信息（调拨通知和调拨通知详情表）
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param repAllotInform
     * @param list
     * @return org.jeecgframework.core.common.model.json.AjaxJson
     */
    public AjaxJson noticeSave(RepAllotInform repAllotInform, List<RepInformDetail> list);

    /**
     * 功能描述:查询调拨详情的对象by  uuid
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return com.jeecg.allocation_notice.entity.RepInformDetail
     */
    public RepInformDetail detailGetByid(String uuid);
    /**
     * 功能描述:*    根据调拨单据号查出调拨通知的相应信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return
     */



    public RepAllotInform repAllotInformGetByid(String uuid);
    /**
     * 功能描述:根据调拨单据号查出调拨通知的相应信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param danjuhao
     * @return
     */

    public RepAllotInform noticeGetBydanjuhao(String danjuhao);
    /**
     * 功能描述: 根据调拨单据号查出所有的调拨详情的信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param allotNumber
     * @return
     */

    public List<RepInformDetail> repInformDetailGetByid(String allotNumber);

    /**
     * 功能描述:   更新调拨数据
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param repAllotInform
     * @param repAllotInform1
     * @param list
     * @param list1
     * @param list2
     * @return org.jeecgframework.core.common.model.json.AjaxJson
     */
    public AjaxJson InformDetailupdate(RepAllotInform repAllotInform,RepAllotInform repAllotInform1, List<RepInformDetail> list,List<RepInformDetail> list1,List<String> list2);

    /**
     * 功能描述: 获得仓储机构信息byid
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return com.jeecg.system_management.pojo.RepAccessRepository
     */
    public RepAccessRepository respoityGet(String uuid);

    /**
     * 功能描述:根据调拨单据号更改调拨通知的可删除
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param allotNumber
     * @return void
     */
    public void InformdelByid(String allotNumber);

    /**
     * 功能描述: 查询物资信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param bainhao
     * @return com.jeecg.system_management.pojo.RepSubstanceMessage
     */
    public RepSubstanceMessage MessageGet(String bainhao);

    /**
     * 功能描述:模糊查询列表集合
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param diaobo
     * @param project
     * @param date
     * @return java.util.List<com.jeecg.allocation_notice.entity.RepAllotInform>
     */
    public List<RepAllotInform> NoticelistLike(String diaobo, String project, String date);


}
