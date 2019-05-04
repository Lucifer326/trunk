/**  
 * @Title: ReleaseServiceImpl.java
 * @Package org.jeecgframework.web.system.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author wangsong
 * @date 2017年12月25日
 * @version V1.0  
 */
package org.jeecgframework.web.system.service.impl;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSReleaseEntity;
import org.jeecgframework.web.system.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ReleaseServiceImpl
 * @Description: TODO(版本发布)
 * @author 王松
 * @date 2017年12月25日
 *
 */
@Service("releaseService")
@Transactional
public class ReleaseServiceImpl extends CommonServiceImpl implements ReleaseService{
	
	@Autowired
	private CommonDao commonDao;
	/**
	 * 保存版本
	 * @param release
	 * @return
	 */
	@Override
	public TSReleaseEntity saveRelease(TSReleaseEntity release) {
		commonDao.saveOrUpdate(release);
		return release;
	}

}
