package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.jeecgframework.web.system.service.TypegroupServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 字典接口类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dictInterface")
public class DictInterface {

	
	private static final Logger logger = Logger.getLogger(DictInterface.class);
	
	@Autowired
	private TypegroupServiceI typegroupService;
	@Autowired
	private TypeServiceI typeService;
	@Autowired
	private SystemService systemService;
	
	/**
	 * 根据类别父节点id，获取这个类别的直接后代的列表;
	 * 如参数为空或空串，查找最顶层的分类列表
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(params = "getTypegroupList")
	public String getTypegroupList(String parentId){
		
		List<TSTypegroup> list= new ArrayList<TSTypegroup>();
		if(parentId == null || parentId == ""){
			
			list = typegroupService.getAll();
			
		}else{
			
			list = typegroupService.getChildren(parentId);
		}
		
		return JSONHelper.toJSONString(list);			
		
	}
	/**
	 * 根据字典唯一编码 
	 * 获得字典类型对象
	 * @param typegroupcode 字典类型编码
	 * @return
	 */
	@RequestMapping(params = "getDictionaryType")
	public TSTypegroup getDictionaryType(String typegroupcode){
		
		TSTypegroup typegroup = null;
				
		
		if(typegroupcode != null && typegroupcode != ""){
			typegroup = typegroupService.getDictionaryType(typegroupcode);
		}
		return typegroup;
	}
	
	/**
	 * 如typeId为空或指向类别树内部节点,则返回空ArrayList
	 * 如regionId为空    则返回所有当前类别下可使用的字典项
	 * 如regionId代表某行政区划
	 * 则查询该行政区划下所有当前类别下可使用的字典项
	 * @param typeId
	 * @param regionId
	 * @return
	 */
	@RequestMapping(params = "getDictionaryList")
	public String getDictionaryList(String typegroupId, String regionId){

		List<TSType> typeList = null;
		
		//判断字典类型id是否为空
		if(typegroupId != null && typegroupId != ""){
			
			//判断区划是否为空 
			if(regionId != null && regionId != ""){
			
				typeList = typeService.getItemsByTypeIdAndRegionId(typegroupId, regionId);
				
			}else{
				typeList = typeService.getItems(typegroupId);
			}
									
		}
		return JSONHelper.toJSONString(typeList);
		
	}
	/**
	 * 如typegroupId为空或指向类别树内部节点，则返回空
	 * 如regionId为空，则返回所有当前类别下可使用的字典项，
	 * 如regionId代表某行政区划，
	 * 则查询该行政区划下所有当前类别下可使用的字典项
	 * typecode 非空时查询键对应的可用字典项，为空时查询所有可用字典项
	 * @param typegroupId
	 * @param typecode 字典键
	 * @param regionId
	 * @return
	 */
	@RequestMapping(params = "getDictionaryItem")
	public String getDictionaryItem(String typecode, String typegroupid, String regionId){
		
		
			List<TSType> typeList = null;
		
			if(typegroupid != null && typegroupid != ""){
				
				if(regionId != null && regionId != ""){
				
					
					if(typecode != null && typecode != ""){
						
						typeList	= typeService.getItemsByTypeIdAndRegionIdAndItemKey(typecode,typegroupid, regionId);
					
					}else{
						
						typeList = typeService.getItemsByTypeIdAndRegionId(typegroupid, regionId);
					}
										
				}else{
					
					typeList = typeService.getItems(typegroupid);
				}
							
				
			}
						
		return JSONHelper.toJSONString(typeList);
		
		
	}
	
}
