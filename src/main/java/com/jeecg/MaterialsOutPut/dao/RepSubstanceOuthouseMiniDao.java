package com.jeecg.MaterialsOutPut.dao;

import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsOutPut.entity.RepSubstanceOuthouse;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 甄磊超
 * @date 2019/4/2
 */
@Repository("repSubstanceOuthouseMiniDao")
public interface RepSubstanceOuthouseMiniDao {

    /**
     * 查询所有符合查询条件的出库信息 联合查询附件信息 仓库信息
     * @param outhouseNumber 出库单号
     * @param repositoryName 仓库名称
     * @param outhouseDate 出库日期
     * @return
     */
    @Arguments({"outhouseNumber","repositoryName","outhouseDate","userId"})
    List<Map<String,Object>> getAll(String outhouseNumber, String repositoryName, String outhouseDate,String userId);

//    List<Map<String,Object>> getAllWithRepository();
    /**
     * 根据主键查询出库详细信息
     * @param uuid
     * @return
     */
    @Arguments({"uuid","userId"})
    Map<String,Object> getDetilById(String uuid,String userId);
    /**
     * 附件名字的查询
     * wangguoqiang
     * @param accessoryId
     * @return
     */
    @Arguments({"accessoryId"})
    public RepSubstanceAccessory findAccessoryById(String accessoryId);

}
