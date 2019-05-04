/**  
 * @Title: VersionUpServiceImpl.java
 * @Package org.jeecgframework.web.system.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 王松
 * @date 2017年12月27日
 * @version V1.0  
 */
package org.jeecgframework.web.system.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.XMLWriter;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.BurlapService;
import org.jeecgframework.web.system.service.VersionUpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName: VersionUpServiceImpl
 * @Description: TODO(版本更新模块)
 * @author 王松
 * @date 2017年12月27日
 *
 */
@Service("versionUpService")
@Transactional
public class VersionUpServiceImpl extends CommonServiceImpl implements VersionUpService {

	/**
	 * @Title: updatePom @Description: TODO(修改和添加pom文件内容) @param @param
	 * filename @param @return 参数 @return int 返回类型 @throws
	 */
	@Override
	public void updatePom(String filename) {
		Map<String, String> map = new HashMap<String, String>();
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
		BurlapService client = (BurlapService)webContext.getBean("burlapServiceClient");
		map = client.getReleaseCode();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(filename));
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String version = map.get(key);
				int index=key.indexOf("+");
				String groupId=key.substring(0, index);
				String artifactId=key.substring(index+1, key.length());
				Element root = document.getRootElement();
				Element properties = root.element("properties");
				if(groupId.equals("pt")){
					Element property = properties.element(groupId+"."+artifactId + ".version");
					if (property != null) {
						property.setText(version);
					} else {
						Element property1 = properties.addElement(groupId+"."+artifactId + ".version");
						property1.setText(version);
						Element dependencies = root.element("dependencies");
						Element dependency = dependencies.addElement("dependency");
						Element groupId1 = dependency.addElement("groupId");
						groupId1.setText(groupId);
						Element artifactId1 = dependency.addElement("artifactId");
						artifactId1.setText(artifactId);
						Element version1 = dependency.addElement("version");
						version1.setText("${" + artifactId + ".version}");
					}
				}else{
					Element property = properties.element(artifactId + ".version");
					if (property != null) {
						property.setText(version);
					} else {
						Element property1 = properties.addElement(artifactId + ".version");
						property1.setText(version);
						Element dependencies = root.element("dependencies");
						Element dependency = dependencies.addElement("dependency");
						Element groupId1 = dependency.addElement("groupId");
						groupId1.setText(groupId);
						Element artifactId1 = dependency.addElement("artifactId");
						artifactId1.setText(artifactId);
						Element version1 = dependency.addElement("version");
						version1.setText("${" + artifactId + ".version}");
					}
				}
			}
			OutputFormat format = OutputFormat.createPrettyPrint(); // 设置XML文档输出格式
			format.setEncoding("UTF-8");
			format.setSuppressDeclaration(true);
			format.setIndent(true); // 设置是否缩进
			format.setIndent("   "); // 以空格方式实现缩进
			format.setNewlines(true); // 设置是否换行
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File(filename)),"UTF-8"), format);
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: checkVersion @Description: TODO(检查版本是否有更新) @param @param
	 * filename @param @return 参数 @return int 返回类型 @throws
	 */
	@Override
	public int checkVersion(String filename) {
		int result = 1;
		Map<String, String> map = new HashMap<String, String>();
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
		BurlapService client = (BurlapService)webContext.getBean("burlapServiceClient");
		map = client.getReleaseCode();
		try {  
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(filename));
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String version = map.get(key);
				int index=key.indexOf("+");
				String groupId=key.substring(0, index);
				String artifactId=key.substring(index+1, key.length());
				Element root = document.getRootElement();
				Element properties = root.element("properties");
				if (groupId.equals("pt")) {
					Element property = properties.element(groupId+"."+artifactId + ".version");
					if (property != null) {
						if (!(property.getText().equals(version))) {
							result = 2;
							break;
						}
					} else {
						result = 2;
						break;
					}
				}else{
					Element property = properties.element(artifactId + ".version");
					if (property != null) {
						if (!(property.getText().equals(version))) {
							result = 2;
							break;
						}
					} else {
						result = 2;
						break;
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
