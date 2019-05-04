package com.jeecg.rep_protocol_store_message.service;

import com.jeecg.rep_protocol_store_message.entity.RepProtocolStoreMessageEntity;
import com.jeecg.rep_protocol_store_message.vo.ProtocolVo;

import java.util.List;

/**
 * @author 刘雨泽
 */
public interface ProtocalService {

    public List<RepProtocolStoreMessageEntity> list();

    RepProtocolStoreMessageEntity look(String uuid);

    void saveProtocal(RepProtocolStoreMessageEntity protocol);

    RepProtocolStoreMessageEntity edit(String uuid);

    void updateProtocal(RepProtocolStoreMessageEntity protocol);

    void deleteProtocolidByIds(String[] protocolid);

    List<RepProtocolStoreMessageEntity> conditionList(ProtocolVo vo);
}
