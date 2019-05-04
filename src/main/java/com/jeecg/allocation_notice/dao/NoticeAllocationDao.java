package com.jeecg.allocation_notice.dao;

import com.jeecg.allocation_notice.entity.RepAllotInform;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;
import java.util.Map;

/**
 * @author fuhai_deng
 * @Description:com.jeecg.allocation_notice.dao
 */
@MiniDao
public interface NoticeAllocationDao {
    @Arguments({ "page", "rows", "whereStr", "orderStr","uuid" })
    /**
     * 功能描述: 调拨列表
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param page
     * @param rows
     * @param where
     * @param order
     * @return java.util.List<com.jeecg.allocation_notice.entity.RepAllotInform>
     */
    List<RepAllotInform> listGet(int page, int rows, String where, String order,String uuid );
    @Arguments({ "diaobo", "project", "date","uuid" })
    /**
     * 功能描述: 模糊查询列表
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param diaobo
     * @param project
     * @param date
     * @return java.util.List<com.jeecg.allocation_notice.entity.RepAllotInform>
     */
    List<RepAllotInform> listLike(String diaobo, String project, String date,String uuid);
    @Arguments({ "diaobo" })
    /**
     * 功能描述: 查询单个调拨详情的对象字段
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> detailGet(String uuid);

    @Arguments({ "uuid" })
    /**
     * 功能描述: 根据uuid查询调拨信息
     * @author deng_fuhai
     * @date 2019/4/11 0011
     * @param uuid
     * @return com.jeecg.allocation_notice.entity.RepAllotInform
     */
    RepAllotInform repAllotGet(String uuid);
}
