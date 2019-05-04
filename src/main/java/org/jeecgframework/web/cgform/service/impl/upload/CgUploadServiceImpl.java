package org.jeecgframework.web.cgform.service.impl.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.dao.upload.CgFormUploadDao;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.upload.CgUploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("cgUploadService")
@Transactional
public class CgUploadServiceImpl extends CommonServiceImpl implements CgUploadServiceI {
	private static final Logger logger = Logger
			.getLogger(CgUploadServiceImpl.class);
	@Autowired
	private CgFormUploadDao cgFormUploadDao;
	
	public void deleteFile(CgUploadEntity file) {
		//step.1 删除附件
		String sql = "select * from t_s_attachment where id = ?";
		Map<String, Object> attachmentMap = commonDao.findOneForJdbc(sql, file.getId());
		//附件相对路径
		String realpath = (String) attachmentMap.get("realpath");
		String fileName = FileUtils.getFilePrefix2(realpath);
		
		//获取部署项目绝对地址
		String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
		FileUtils.delete(realPath+realpath);
		FileUtils.delete(realPath+fileName+".pdf");
		FileUtils.delete(realPath+fileName+".swf");
		//step.2 删除数据
		commonDao.delete(file);
	}
	
	public void deleteFile(String ids) {
		if(ids==null || ids.equals("")){
			return;
		}
		
		String[] id = ids.split(",");
		if(id.length==0) {
			return;
		}
		
		//获取部署项目绝对地址
		String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
		for(int i=0; i<id.length; i++) {
			if(id[i]==null || id[i].equals("")) {
				continue;
			}
			CgUploadEntity entity = findUniqueByProperty(CgUploadEntity.class, "id", id[i]);
			if(entity == null) {
				continue;
			}
			
			//附件相对路径
			String realpath = (String) entity.getRealpath();
			String fileName = FileUtils.getFilePrefix2(realpath);
			
			FileUtils.delete(realPath + realpath);
			FileUtils.delete(realPath + fileName+".pdf");
			FileUtils.delete(realPath + fileName+".swf");
			//step.2 删除数据
			commonDao.delete(entity);
			logger.info(realpath +  " 删除成功！");
		}
		
	}

	
	public void writeBack(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl) {
		try{
			cgFormUploadDao.updateBackFileInfo(cgFormId, cgFormName, cgFormField, fileId, fileUrl);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件上传失败："+e.getMessage());
		}
	}


	@Override
	public boolean isFileExists(String cgFormId, String fileName) {
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
		String hql = "select count(c) from CgUploadEntity c where c.cgformId = ? and c.attachmenttitle = ? and c.extend = ?";
		List<Object> list = commonDao.findHql(hql, cgFormId,name,extend);
		if(list == null || list.size()==0) {
			return false;
		}
		
		Long l = (Long) list.get(0);
		if(l.intValue() > 0)  {
			return true;
		}
		return false;
	}


	@Override
	public void uploadFiles(UploadFile uploadFile, String cgFormId, String cgFormName,
			String cgFormField) {
		try {
			MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
			multipartRequest.setCharacterEncoding("UTF-8");

			String uploadbasepath = uploadFile.getBasePath();// 文件上传根目录
			if (uploadbasepath == null) {
				uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
			}
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			// 文件数据库保存路径
			String path = uploadbasepath + "/";// 文件保存在硬盘的相对路径
			String realPath = multipartRequest.getSession().getServletContext().getRealPath("/") + "/" + path;// 文件的硬盘真实路径
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdirs();// 创建根目录
			}
			if (uploadFile.getCusPath() != null) {
				realPath += uploadFile.getCusPath() + "/";
				path += uploadFile.getCusPath() + "/";
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs();// 创建文件自定义子目录
				}
			} else {
				realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
				path += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdir();// 创建文件时间子目录
				}
			}
			String entityName = uploadFile.getObject().getClass().getSimpleName();
			// 设置文件上传路径
			if (entityName.equals("TSTemplate")) {
				realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")
						+ ResourceUtil.getConfigByName("templatepath") + "/";
				path = ResourceUtil.getConfigByName("templatepath") + "/";
			} else if (entityName.equals("TSIcon")) {
				realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")
						+ uploadFile.getCusPath() + "/";
				path = uploadFile.getCusPath() + "/";
			}
			
			//上传附件
			String fileName = "";
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();// 获取上传文件对象
				fileName = mf.getOriginalFilename();// 获取文件名
				if(fileName==null || fileName.equals("")) {
					continue;
				}
				logger.info("开始上传附件： " + fileName + "......");
				String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
				String myfilename = "";
				String noextfilename = "";// 不带扩展名
				if (uploadFile.isRename()) {
					noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);// 自定义文件名称
					myfilename = noextfilename + "." + extend;// 自定义文件名称
				} else {
					myfilename = fileName;
				}

				String savePath = realPath + myfilename;// 文件保存全路径
				String fileprefixName = FileUtils.getFilePrefix(fileName);
				
				File savefile = new File(savePath);
				if ("txt".equals(extend)) {
					// 利用utf-8字符集的固定首行隐藏编码原理
					// Unicode:FF FE UTF-8:EF BB
					byte[] allbytes = mf.getBytes();
					try {
						String head1 = toHexString(allbytes[0]);
						
						String head2 = toHexString(allbytes[1]);
						
						if ("ef".equals(head1) && "bb".equals(head2)) {
							// UTF-8
							String contents = new String(mf.getBytes(), "UTF-8");
							if (StringUtils.isNotBlank(contents)) {
								OutputStream out = new FileOutputStream(savePath);
								out.write(contents.getBytes());
								out.close();
							}
						} else {
							// for：TXT文件预览出现乱码的错误
							// GBK
							String contents = new String(mf.getBytes(), "GBK");
							OutputStream out = new FileOutputStream(savePath);
							out.write(contents.getBytes());
							out.close();
							// for：TXT文件预览出现乱码的错误
						}
					} catch (Exception e) {
						String contents = new String(mf.getBytes(), "UTF-8");
						if (StringUtils.isNotBlank(contents)) {
							OutputStream out = new FileOutputStream(savePath);
							out.write(contents.getBytes());
							out.close();
						}
					}
				} else {
					FileCopyUtils.copy(mf.getBytes(), savefile);
				}
				if (uploadFile.getSwfpath() != null) {
					// 转SWF
					SwfToolsUtil.convert2SWF(savePath);
				}
				
				// 保存数据
				CgUploadEntity uploadEntity = new CgUploadEntity();
				uploadEntity.setCgformName(cgFormName);
				uploadEntity.setCgformField(cgFormField);
				uploadEntity.setCgformId(cgFormId);
				uploadEntity.setAttachmenttitle(fileprefixName);
				uploadEntity.setExtend(extend);
				uploadEntity.setRealpath(path + myfilename);
				if (uploadFile.getByteField() != null) {
					// 二进制文件保存在数据库中
					uploadEntity.setAttachmentcontent(StreamUtils.InputStreamTOByte(mf.getInputStream()));
				}
				if(uploadFile.getSwfpath() != null) {
					uploadEntity.setSwfpath(path + FileUtils.getFilePrefix(myfilename) + ".swf");
				}
				
				saveOrUpdate(uploadEntity);
				logger.info("附件： " + fileName + " 上传成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return;
	}

	private String toHexString(int index){
        String hexString = Integer.toHexString(index);   
        // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充   
        hexString = hexString.substring(hexString.length() -2);  
        return hexString;
	}
}
