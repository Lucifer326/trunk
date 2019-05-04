package org.jeecgframework.web.demo.service;
import java.io.Serializable;

import javax.jws.WebService;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.TestEntity;

@WebService
public interface TestServiceI extends CommonService{
	
	public String getString(String name);
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 自定义按钮-sql增强-测试
	 * @param id
	 * @return
	 */
	 public boolean doTest1Sql(TestEntity t);
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TestEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TestEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TestEntity t);
}
