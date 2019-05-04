package com.jeecg.rep_protocol_store_message.dao;

import com.jeecg.rep_protocol_store_message.entity.RepProtocolStoreMessageEntity;
import com.jeecg.rep_protocol_store_message.vo.ProtocolVo;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.Date;
import java.util.List;

@MiniDao
public interface ProtocalDao {


    /**
     * 查询结果集
     * @return
     */
    @Arguments({"userId"})
    public List<RepProtocolStoreMessageEntity> list(String userId);

    /**
     * 根据id查询数据
     * @param uuid
     * @return
     */
    @Arguments({"uuid"})
    RepProtocolStoreMessageEntity look(String uuid);

    /**
     * 根据id回显需要修改的数据
     * @param uuid
     * @return
     */
    @Arguments({"uuid"})
    RepProtocolStoreMessageEntity edit(String uuid);

    /**
     *
     * 根据查询条件查询
     * @param protocolNumber 查询编号
     * @param protocolCompany 查询的公司
     * @param protocolStorage 查询的储备物资
     * @param protocolYear 查询的年限
     * @param protocolDate 起始时间
     * @param protocolToDate 结束时间
     * @return
     */
    @Arguments({"protocolNumber", "protocolCompany", "protocolStorage", "protocolYear", "protocolDate", "protocolToDate","userId"})
    List<RepProtocolStoreMessageEntity> conditionList(String protocolNumber, String protocolCompany, String protocolStorage, String protocolYear, String protocolDate, String protocolToDate,String userId);
}
