package org.jeecgframework.tag.core.hplus;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeecgframework.p3.core.utils.common.StringUtils;


/**
 * 
 * 类描述：图片上传标签
 * 
 * 
 * @date： 日期：2018-1-8 时间：下午15:57:30
 * @version 1.0
 */
public class ImageUploadTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	protected String name;//表单id
	protected String fileType;//图片的文件类型设置
	protected String fileSize;//图片的文件大小设置
	protected String value;//默认值
	protected String height;//图片显示高度
	protected String width;//图片显示宽度
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		
		this.fileSize = fileSize;		
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public StringBuffer end() {
		
		StringBuffer sb = new StringBuffer();
		
		if (StringUtils.isBlank(width)) {
			width ="100";
		} 
		if (StringUtils.isBlank(height)) {
			height ="100";
		}
		if (StringUtils.isBlank(fileSize)) {
			fileSize ="6";
		}

		//sb.append("<script src=\"webpage/jeecg/image/ajaxfileupload.js\"></script>");
		
		sb.append("<script type=\"text/javascript\">");
		
		
		if(StringUtils.isNotBlank(fileType)){
			//将图片文件格式用,号切割 放入数组中。
			sb.append("var "+name+"str = \""+fileType+"\";");
			sb.append("var "+name+"arr = new Array();");
			sb.append(" "+name+"arr = "+name+"str.split(',');");
		}
		sb.append("$(function(){");
		sb.append("$(\'#"+name+"defaultImage\').on(\'click\',function(){");
		
		//触发click方法
		sb.append("$(\'#"+name+"fileInput\').click();");
		sb.append("});");
		
		//触发click方法
		sb.append("$(\'#"+name+"imageUploadLink\').on(\'click\',function(){");
		sb.append("$(\'#"+name+"fileInput\').click();");
		sb.append("});");
		
		sb.append("});");
		
		//上传的方法
		sb.append("function "+name+"uploadHead(){");
		sb.append("var filepath = $('#"+name+"fileInput').val();");
		
		//验证图片的格式
		sb.append("if(!"+name+"isImage(filepath)){");
		sb.append("return false;");
		sb.append("}");
		
		//验证图片的大小是否在范围内
		sb.append("if(!"+name+"checkFileSize(filepath)){");
		sb.append("return false;");
		sb.append("}");
		
		sb.append("$.ajaxFileUpload({");
		sb.append("url:\"imagesController.do?ajaxUpload\",");//需要链接到服务器地址
		sb.append("secureuri:false,");
		sb.append("fileElementId:\""+name+"fileInput\",");//文件选择框的id属性
		sb.append("dataType: \'json\',");//json
		
		sb.append("success: function (data) {");
		sb.append("var path = jQuery.parseJSON(data).imagePath;");
		sb.append("var oldName = jQuery.parseJSON(data).oldName;");
		sb.append("if(data ==\"1\"){");
		sb.append("alert(\"图片最大值为10M！！\");");
		sb.append("return");
		sb.append("}else if(data ==\"2\"){");
		sb.append("alert(\"图片格式不正确！！\");");
		sb.append("return");
		sb.append("}");
			
		sb.append("$(\"#"+name+"defaultImage\").attr(\"src\",\"imagesController.do?readImage&imagePath=\"+path);");
		sb.append("$(\'#"+name+"imageAddress\').val(path);");
		sb.append("$(\'#"+name+"defaultImage\').css(\"display\",\"block\");");
		sb.append("$(\'#"+name+"oldName\').val(oldName);");
		sb.append("},");
		
		sb.append("error:function(XMLHttpRequest, textStatus, errorThrown){");
		sb.append("alert(\'上传失败！\');");
		sb.append("}");
		sb.append("});");
		sb.append("}");
		
		//检查是否为图片的方法 
		sb.append("function "+name+"isImage(filepath) {");
		sb.append("var extStart = filepath.lastIndexOf(\".\");");
		sb.append("var ext = filepath.substring(extStart, filepath.length).toLowerCase();");
		
		sb.append("if("+name+"arr.indexOf(ext) == -1){");
		sb.append("alert(\"图片只能是"+fileType+"格式喔\");");
		sb.append("return false;");
		sb.append("}");
		sb.append("return true;");
		sb.append("}");		
  		
		
		//检查图片大小，不能超过设置的大小，默认为6M,支持IE、filefox、chrome
		sb.append("function "+name+"checkFileSize(filepath) {");
		sb.append("var maxsize = "+fileSize+" * 1024 * 1024;");
		sb.append("var errMsg = \"上传的图片文件不能超过"+fileSize+"M！\";");
		sb.append("var tipMsg = \"您的浏览器暂不支持上传头像，确保上传文件不要超过"+fileSize+"M，建议使用IE、FireFox、Chrome浏览器。\";");
		sb.append("try {");
		sb.append("var filesize = 0;");
		sb.append("var ua = window.navigator.userAgent;");
		sb.append("if (ua.indexOf(\"MSIE\") >= 1) {");
		sb.append("var img = new Image();");
		sb.append("img.src = filepath;");
		sb.append("filesize = img.fileSize;");
		sb.append("} else {");
		sb.append("filesize = $(\"#"+name+"fileInput\")[0].files[0].size;");
		sb.append("}");
		
		sb.append("if (filesize > 0 && filesize > maxsize) {");
		sb.append("alert(errMsg);");
		sb.append("return false;");
		sb.append("}");
		sb.append("} catch (e) {");
		sb.append("alert(\"头像上传失败，请重试\");");
  				
		sb.append("return false;");
		sb.append("}");
		sb.append("return true;");
		sb.append("}");
		
		sb.append("</script>");
					
		if(StringUtils.isNotBlank(value)){
			sb.append("<input type=\"hidden\" id=\""+name+"imageAddress\" name=\"imageAddress\" value="+value+">");
			sb.append("<a href=\"javascript:void(0);\" id=\""+name+"imageUploadLink\">[图片上载]</a>");
			sb.append("<a href="+value+" target=\"_blank\" id=\""+name+"imageAddress_href\" href="+value+">[下载]</a>");	
			
			sb.append("<img id=\""+name+"defaultImage\" src="+value+" alt=\"图片上载\" width="+width+" height="+height+" >");
			
			sb.append("<script type=\"text/javascript\">");
			
			sb.append("function imageAddressCallback(url,name){");			
			sb.append("$(\"#imageAddress_href\").attr('href',url).html('下载');");
			sb.append("$(\"#imageAddress\").val(url);");
			sb.append("}");		
			sb.append("</script>");
			
		}else{
			
			sb.append("<input type=\"hidden\" id=\""+name+"imageAddress\" name=\"imageAddress\" value=\"webpage/jeecg/image/default.jpg\"/>");
			sb.append("<a href=\"javascript:void(0);\" id=\""+name+"imageUploadLink\">[图片上载]</a>");
			
			sb.append("<img id=\""+name+"defaultImage\" src=\"webpage/jeecg/image/default.jpg\" alt=\"图片上载\" width="+width+" height="+height+">");
		}
		
		sb.append("<input type=\"file\" onchange=\""+name+"uploadHead();\" id=\""+name+"fileInput\" name=\"fileInput\" style=\"display:none\"/>");
		
		return sb;
	}
	
	
}
