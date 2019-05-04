package org.jeecgframework.web.cgform.service.upload;

import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
/**
 * 
 * @Title:CgUploadServiceI
 * @description:智能表单文件上传服务
 * @author 赵俊夫
 * @date Jul 24, 2013 9:55:07 PM
 * @version V1.0
 */
public interface CgUploadServiceI extends CommonService{
	/**
	 * 删除文件
	 * @param file
	 */
	public void deleteFile(CgUploadEntity file);
	
	/**
	 * 删除文件
	 * @param ids，多个id用“,”隔开
	 */
	public void deleteFile(String ids);
	/**
	 * 将文件信息回填到智能表单的表中
	 * @param cgFormId
	 * @param cgFormName
	 * @param cgFormField
	 */
	public void writeBack(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl);
	
	/**
	 * 验证文件是否已经存在
	 * @param cgFormId
	 * @param fileName
	 * @return 若文件存在，return true；否则，return false
	 */
	public boolean isFileExists(String cgFormId,String fileName);
	
	/**
	 * 上传附件
	 * @param request
	 * @param cgFormId
	 * @param cgFormName
	 * @param cgFormField
	 */
	public void uploadFiles(UploadFile uploadFile,String cgFormId,String cgFormName,String cgFormField);
}
