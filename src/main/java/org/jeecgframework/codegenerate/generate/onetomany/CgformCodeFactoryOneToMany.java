package org.jeecgframework.codegenerate.generate.onetomany;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.codegenerate.generate.BaseCodeFactory;
import org.jeecgframework.codegenerate.generate.ICallBack;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;

public class CgformCodeFactoryOneToMany extends BaseCodeFactory
{
  private ICallBack callBack;
  private String projectPath;

  public void generateFile(String templateFileName, String type, Map data)
    throws TemplateException, IOException
  {
    try
    {
      String entityPackage = data.get("entityPackage").toString();
      String entityName = data.get("entityName").toString();
      String fileNamePath = getCodePath(type, entityPackage, entityName);
      String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
      Template template = getConfiguration()
        .getTemplate(templateFileName);
      FileUtils.forceMkdir(new File(fileDir + "/"));
      Writer out = new OutputStreamWriter(new FileOutputStream(
        fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
      template.process(data, out);
      out.close();
    } catch (TemplateException e) {
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void generateFileUserDefined(String templateFileName, String type, Map data)
    throws TemplateException, IOException
  {
    try
    {
      String entityPackage = data.get("entityPackage").toString();
      String entityName = data.get("entityName").toString();
      String fileNamePath = getCodePath(type, entityPackage, entityName);
      String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
      Template template = getConfigurationUserDefined()
        .getTemplate(templateFileName);
      FileUtils.forceMkdir(new File(fileDir + "/"));
      Writer out = new OutputStreamWriter(new FileOutputStream(
        fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
      template.process(data, out);
      out.close();
    } catch (TemplateException e) {
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public String getProjectPath()
  {
    return this.projectPath;
  }

  public String getClassPath()
  {
    String path = getClass().getResource("/").getPath();
    return path;
  }

  public static void main(String[] args)
  {
    System.out.println(Thread.currentThread().getContextClassLoader().getResource("./").getPath());
  }

  public String getTemplatePath()
  {
    String path = getClassPath() + CodeResourceUtil.TEMPLATEPATH;
    return path;
  }

  public String getCodePath(String type, String entityPackage, String entityName)
  {
    String path = getProjectPath();
    String codePath = "";
    if ((this.packageStyle != null) && (CodeResourceUtil.PACKAGE_SERVICE_STYLE.equals(this.packageStyle))) {
      codePath = getCodePathServiceStyle(path, type, entityPackage, entityName);
    }
    else {
      codePath = getCodePathProjectStyle(path, type, entityPackage, entityName);
    }
    return codePath;
  }

  public void invoke(String templateFileName, String type) throws TemplateException, IOException {
    Map data = new HashMap();
    data = this.callBack.execute();
    generateFile(templateFileName, type, data);
  }

  public void invokeUserDefined(String templateFileName, String type) throws TemplateException, IOException {
    Map data = new HashMap();
    data = this.callBack.execute();
    generateFileUserDefined(templateFileName, type, data);
  }

  public ICallBack getCallBack() {
    return this.callBack;
  }

  public void setCallBack(ICallBack callBack) {
    this.callBack = callBack;
  }

  public void setProjectPath(String projectPath) {
    this.projectPath = projectPath;
  }
}