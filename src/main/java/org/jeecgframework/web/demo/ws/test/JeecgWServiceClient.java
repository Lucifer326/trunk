package org.jeecgframework.web.demo.ws.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * 
 * @ClassName: JeecgWServiceClient
 * @Description: cxf客户端测试类
 * @author huangzq
 * @date 2015-12-31 下午05:44:50
 * 
 */
public class JeecgWServiceClient {

	/*public static void main11(String[] args) {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setServiceClass(JeecgWServiceI.class);
		bean.setAddress("http://localhost:8080/jeecg/cxf/JeecgWService");
		JeecgWServiceI client = (JeecgWServiceI) bean.create();
		System.out.println(client.sayHello());
	}
	
	public static void main(String[] args) {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setServiceClass(JeecgWServiceI.class);
		bean.setAddress("http://localhost:8080/jeecg/cxf/JeecgWDemoService");
		JeecgWServiceI client = (JeecgWServiceI) bean.create();
		System.out.println(client.sayHello());
	}*/
	
	
/*	public static void maintest(String[] args) {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setServiceClass(TestServiceI.class);
		bean.setAddress("http://localhost:8080/jeecg/cxf/TestService");
		TestServiceI client = (TestServiceI) bean.create();
		System.out.println(client.getString("aaaaaaaaaaaaaaaaa"));
//		System.out.println(client.save(entity));
	}*/
	
	
	/**
	 * limingtao
	 * 1.字符串outName
	 * 2.实体saveEntity
	 * 3.泛型实体save
	 */
	public static void main(String[] args) {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setServiceClass(JeecgWServiceTestI.class);
		bean.setAddress("http://10.10.0.141:8080/jeecg/cxf/JeecgWServiceTest");
		JeecgWServiceTestI<?> client = (JeecgWServiceTestI<?>) bean.create();
		
/*		TestEntity test = new TestEntity();
		test.setName("ceshi cxf");
		
		 * 第一：泛型实体
		 
		client.save(test);//泛型实体
		
		
		 * 第二：实体
		 
		client.saveEntity(test);//实体
*/		
		/*
		 * 第三：字符串
		 */
		String text = client.outName("=================ok================");
		System.out.println(text);
	}
}
