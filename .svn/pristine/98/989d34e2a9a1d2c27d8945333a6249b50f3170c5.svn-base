package org.jeecgframework.tag.comdesign.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.enums.StoreUploadFilePathEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.hplus.WebUploaderTag;
import org.jeecgframework.web.system.controller.core.SystemController;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;



/***
 * 附件上传控制器
 * @author zkx
 * 日期：2018-1-15
 */
@Scope("prototype")
@Controller
@RequestMapping("/webUploaderController")
public class WebUploaderController {

	private static final Logger logger = Logger.getLogger(SystemController.class);
	
	
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	/**
	 * 通过设置的属性生成前端代码，返给前台页面
	 * @param popupTreeTag
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "webUploaderSourceCode")
	@ResponseBody
	public AjaxJson webUploaderSourceCode(WebUploaderTag webUploaderTag, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		//调用标签类的方法 获取源码Code
		String sourceCode = webUploaderTag.end().toString();
		
		j.setSuccess(true);
		j.setMsg(sourceCode);
		
		return j;
	}
	
	/**
	 * 新增
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WebUploaderTag webUploaderTag, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "控件添加成功";
		
		String historyId = request.getParameter("historyId");
		String source = request.getParameter("source");
		String controlName = request.getParameter("controlName");
		String typeId = request.getParameter("typeId");
		
		CgformControlDesignEntity controlDesign = null;
		
		if(historyId == null || historyId.equals("")) {
			
			controlDesign = new CgformControlDesignEntity();
		
			//设置控件名称
			controlDesign.setName(controlName);
		}else{
			
			controlDesign = controlGeneratorService.getControlDesign(historyId);
			
		}
		
		//设置属性
		String objectToJson = JSON.toJSONString(webUploaderTag);
		controlDesign.setProperty(objectToJson);
		//设置源码
		controlDesign.setSourceCode(source);
		//设置控件类型
		controlDesign.setTypeId(typeId);
		//设置修改时间
		controlDesign.setUpdateDate(new Date());
		
		//获取当前登录用户
		HttpSession session = ContextHolderUtils.getSession();
		TSUser currentUser =  (TSUser)session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		//设置修改人
		controlDesign.setCreateBy(currentUser.getRealName());
			
		try {
			controlGeneratorService.save(controlDesign);
			message = controlDesign.getId();
		} catch (Exception e) {
			e.printStackTrace();
			message = "控件添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	
	/**
	 * WebUploader
	 * 文件上传处理/删除处理
	 */
	@RequestMapping(params="filedeal")
    @ResponseBody
    public AjaxJson filedeal(HttpServletRequest request, HttpServletResponse response) {
        
		AjaxJson j = new AjaxJson();
        String msg="啥都没干-没传参数吧！";
        String upFlag=request.getParameter("isup");
        String delFlag=request.getParameter("isdel");
        String memoryType=request.getParameter("memoryType");//文件存储方式
        
        //String ctxPath = request.getSession().getServletContext().getRealPath("");
        String ctxPath=ResourceUtil.getConfigByName("webUploadpath");//demo中设置为D://upFiles,实际项目应因事制宜
        
        try {
	        //如果是上传操作
	        if("1".equals(upFlag)){
	        	
	        	String bizType=request.getParameter("bizType");//上传业务名称
	        	String bizPath=StoreUploadFilePathEnum.getPath(bizType);//根据业务名称判断上传路径
	        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	            MultipartFile mf=multipartRequest.getFile("file");// 获取上传文件对象
	        	
	            String dbpath = controlGeneratorService.uploadFile(ctxPath, bizPath,mf,memoryType);

				msg="上传成功";
				j.setMsg(msg);
				j.setObj(dbpath);
				//1、将文件路径赋值给obj,前台可获取之,随表单提交,然后数据库中存储该路径
	          
			//如果是删除操作
	        }else if("1".equals(delFlag)){
	        	String path=request.getParameter("path");
	        	
	        	if(path.indexOf(".") != -1){
	        		
	        		String delpath=ctxPath+File.separator+path;
	        		
	        		File fileDelete = new File(delpath);
	        		if (!fileDelete.exists() || !fileDelete.isFile()) {
	        			msg="警告: " + delpath + "不存在!";
	        			j.setSuccess(true);//不存在前台也给他删除
	        		}else{
	        			
	        			boolean isSuccess = controlGeneratorService.delFile(fileDelete);
	        			
	        			if(isSuccess){
	        				msg="--------成功删除文件---------"+delpath;
	        			}else{
	        				j.setSuccess(false);
	        				msg="没删除成功--jdk的问题还是你文件的问题请重新试试";
	        			}
	        		}
	        	}else{
	        		
	        		int i = controlGeneratorService.delDbFileByid(path);
        			
        			if(i>0){
        				msg="--------成功删除数据库文件---------";
        			}else{
        				j.setSuccess(false);
        				msg="没删除成功--jdk的问题还是你文件的问题请重新试试";
        			}
	        	}
	        }else{
	        	throw new BusinessException("没有传参指定上传还是删除操作！");
	        }
        } catch (BusinessException b) {
			j.setSuccess(false);
			logger.info(b.getMessage());
		}
    	logger.info("-----webUploaderController.do?filedeal------------"+msg);
		j.setMsg(msg);
        return j;
    }
	/**
	 * 获取图片流/获取文件用于下载
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="showOrDownByurl",method = RequestMethod.GET)
	public void getImgByurl(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String flag=request.getParameter("down");//是否下载否则展示图片
		String dbpath = request.getParameter("dbPath");
		if("1".equals(flag)){
			response.setContentType("application/x-msdownload;charset=utf-8");
			String fileName=dbpath.substring(dbpath.lastIndexOf(File.separator)+1);
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		}else{
			response.setContentType("image/jpeg;charset=utf-8");
		}
	
		InputStream inputStream = null;
		OutputStream outputStream=null;
		try {
			String localPath=ResourceUtil.getConfigByName("webUploadpath");
			
			String newdbpath = localPath+File.separator+dbpath;
			
			if(dbpath.indexOf(".") != -1){//服务器存储   
				inputStream = new BufferedInputStream(new FileInputStream(newdbpath));
			}else{//数据库存取，根据id从数据库读取文件数据
				TSAttachment tachment = controlGeneratorService.getTSAttachment(dbpath);
				
				inputStream = new BufferedInputStream(new ByteArrayInputStream(tachment.getAttachmentcontent()));
			}
			
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
	        int len;
	        while ((len = inputStream.read(buf)) > 0) {
	            outputStream.write(buf, 0, len);
	        }
	        response.flushBuffer();
		} catch (Exception e) {
			logger.info("--通过流的方式获取文件异常--"+e.getMessage());
		}finally{
			if(inputStream!=null){
				inputStream.close();
			}
			if(outputStream!=null){
				outputStream.close();
			}
		}
	}	
	
}
