package org.jeecgframework.web.demo.ws.test;
//
import javax.jws.WebParam;
import javax.jws.WebService;

import org.jeecgframework.web.demo.entity.TestEntity;
//
//
//
//
//如果不写targetNamespace的值时，默认是包反转，比如服务器项目中包是com.gstd.hw，那么默认值为hw.gstd.com，如果在另外  
//的项目客户端中调用，则创建接口类HelloWorld时，类名可以不一样，但是targetNamespace必须一样。不然调用不成功！最好自己定义一个名称
@WebService(targetNamespace ="http://org.jeecgframework.web.demo.ws.test")
public interface JeecgWServiceTestI<T>{
//	
	public void save(TestEntity entity);
	@SuppressWarnings("hiding")
	public <T> void saveEntity(T entity);
	public String outName(@WebParam(name="name")String name);
	
}
