package org.jeecgframework.web.demo.ws.test;
//
import javax.jws.WebParam;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.TestEntity;
import org.jeecgframework.web.demo.service.TestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
//
//
//
public  class JeecgWServiceTestImpl<T>  implements  JeecgWServiceTestI<T> {

	@Autowired
	private TestServiceI testService;
	
	@Autowired
	private CommonService commonService;
	@Override
	public String outName(@WebParam(name="name")String name) {
		// TODO Auto-generated method stub

		TestEntity en = new TestEntity();
		en.setName(name);
		System.out.println("=================>"+en.getName());
//
		return en.getName();
	}
	@Override
	public void save(TestEntity entity) {
		TestEntity entitys = null;
		try{
			testService.save(entity);
			System.out.println("==================save ok================");
		}catch(Exception e){
			System.out.println("==================save error================");
			e.printStackTrace();
		}
	}
	@SuppressWarnings("hiding")
	@Override
	public <T> void saveEntity(T entity) {
		// TODO Auto-generated method stub
		try{
			System.out.println("==================saveEntity ok================");
			commonService.save(entity);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("==================saveEntity error================");
		}
	}
}
