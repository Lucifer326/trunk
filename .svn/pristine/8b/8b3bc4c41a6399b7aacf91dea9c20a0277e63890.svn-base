package com.jeecg.MaterialsInPut.service;

import com.jeecg.MaterialsInPut.common.MaterialsInPutVo;
import com.jeecg.MaterialsInPut.common.RepSubstanceMessageVo;
import com.jeecg.MaterialsInPut.entity.RepSubstanceAccessory;
import com.jeecg.MaterialsInPut.entity.RepSubstanceStorageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.util.List;
import java.util.Map;

public interface MaterialsInputService extends CommonService {
	/**
	 * 查询全部
	 * @return
	 */
	List<Map<String, Object>> queryAll(MaterialsInPutVo vo);

	/**
	 * 查询物资入库详细和物资信息
	 * @param numberid
	 * @return
	 */
	List<Map<String, Object>> inStockLook(String numberid);

	/**
	 * 根据id查询物资入库
	 * @return
	 */
	Map<String, Object> queryOne(String uuid);

	/**
	 * 根据id查询物资入库(返回一个物资入库对象)
	 *
	 * @param uuid
	 * @return
	 */
	RepSubstanceStorageEntity queryOneEntityById(String uuid);

	/**
	 * 新建物资入库
	 * @return
	 */
	void saveOrupdateInput(RepSubstanceStorageEntity repSubstanceStorageEntity, String[] minuteids, String[] messageids, String[] prices, String[] outputAmounts,
	                       String[] purchaseDates, String[] manufactureDates, String[] overinsuredDates, String delUuid, String ctrl) throws Exception;

	/**
	 * 创建物资附件
	 * @param accessory
	 * @return
	 */
	String saveAccessoryMsg(RepSubstanceAccessory accessory);

	/**
	 * 修改
	 * @param repSubstanceStorageEntity
	 */
	//void updInStock(RepSubstanceStorageEntity repSubstanceStorageEntity);

	/**
	 * 批量删除
	 * @param del_uuids
	 */
	void delBatch(String[] del_uuids);

	/**
	 * 单个删除
	 * @param uuids
	 */
	//void delInStock(String uuids);

	/**
	 * 根据物资入库详细id查询物资入库详细和物资信息
	 */
	Map<String, Object> queryMinute(String uuid);

	/**
	 * 根据仓库id查询物资入库详细和入库单号
	 */
	List<Map<String, Object>> queryDetail(String warehouseId,String subId);

	/**
	 * 根据id查找附件信息(文件名，路径)
	 * @param uuid
	 * @return
	 */
	RepSubstanceAccessory findFileById(String uuid);

	/**
	 * 根据调拨通知id查询调拨单物资
	 * @param informid
	 * @return
	 */
	List<Map<String, Object>> querySubstance(String informid);

	/**
	 * 查询调拨单(以出库且未入库的)
	 * @param input
	 * @param output
	 * @return
	 */
	List<Map<String, Object>> seachAllocation(String input, String output);

	/**
	 * 根据调拨单据号修改调拨单信息
	 */
	void updAllocation(String allot_number);

	/**
	 * 搜索物资根据给定的条件
	 * @return List
	 */
	List<Map<String,Object>> searchRepSubstanceMessage(RepSubstanceMessageVo repSubstanceMessageVo, String selUuids);
}
