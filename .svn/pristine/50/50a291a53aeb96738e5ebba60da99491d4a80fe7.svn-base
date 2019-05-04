package org.jeecgframework.codegenerate.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.codegenerate.util.CodeStringUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CodeFactory extends BaseCodeFactory
{
  private ICallBack callBack;

  public void generateFile(String templateFileName, String type, Map data)
  {
    try
    {
      String entityPackage = data.get("entityPackage").toString();
      String entityName = data.get("entityName").toString();
      String fileNamePath = getCodePath(type, entityPackage, entityName);
      String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
      Template template = getConfiguration().getTemplate(templateFileName);
      FileUtils.forceMkdir(new File(fileDir + "/"));
      Writer out = new OutputStreamWriter(new FileOutputStream(
        fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
      template.process(data, out);
      out.close();
    } catch (TemplateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getProjectPath()
  {
    String path = System.getProperty("user.dir").replace("\\", "/") + "/";
    return path;
  }

  public String getClassPath()
  {
    String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
    return path;
  }

  public static void main(String[] args)
  {
    System.out.println(getProjectPath());
  }

  public String getTemplatePath()
  {
    String path = getClassPath() + CodeResourceUtil.TEMPLATEPATH;
    return path;
  }

  public String getCodePath(String type, String entityPackage, String entityName)
  {
    String path = getProjectPath();
    StringBuilder str = new StringBuilder();
    if (StringUtils.isNotBlank(type)) {
      String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
      str.append(path);
      if (("jsp".equals(type)) || ("jspList".equals(type)))
        str.append(CodeResourceUtil.JSPPATH);
      else {
        str.append(CodeResourceUtil.CODEPATH);
      }
      if ("Action".equalsIgnoreCase(codeType))
        str.append(StringUtils.lowerCase("action"));
      else if ("ServiceImpl".equalsIgnoreCase(codeType))
        str.append(StringUtils.lowerCase("service/impl"));
      else if ("ServiceI".equalsIgnoreCase(codeType))
        str.append(StringUtils.lowerCase("service"));
      else if (!"List".equalsIgnoreCase(codeType))
      {
        str.append(StringUtils.lowerCase(codeType));
      }
      str.append("/");
      str.append(StringUtils.lowerCase(entityPackage));
      str.append("/");

      if (("jsp".equals(type)) || ("jspList".equals(type))) {
        String jspName = StringUtils.capitalize(entityName);

        str.append(CodeStringUtils.getInitialSmall(jspName));
        str.append(codeType);
        str.append(".jsp");
      } else {
        str.append(StringUtils.capitalize(entityName));
        str.append(codeType);
        str.append(".java");
      }
    } else {
      throw new IllegalArgumentException("type is null");
    }
    return str.toString();
  }

  public void invoke(String templateFileName, String type) {
    Map data = new HashMap();
    data = this.callBack.execute();
    generateFile(templateFileName, type, data);
  }

  public ICallBack getCallBack() {
    return this.callBack;
  }

  public void setCallBack(ICallBack callBack) {
    this.callBack = callBack;
  }

  public static enum CodeType
  {
    serviceImpl("ServiceImpl"), 
    service("ServiceI"), controller("Controller"), entity("Entity"), jsp(""), jspList("List");

    private String type;

    private CodeType(String type) { this.type = type; }

    public String getValue()
    {
      return this.type;
    }
  }
}