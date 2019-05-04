package org.jeecgframework.codegenerate.generate;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.codegenerate.pojo.CreateFileProperty;
import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
import org.jeecgframework.codegenerate.util.CodeDateUtils;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.codegenerate.util.NonceUtils;
import org.jeecgframework.codegenerate.util.def.FtlDef;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.generate.GenerateEntity;

public class CgformCodeGenerate
  implements ICallBack
{
  private static final Log log = LogFactory.getLog(CgformCodeGenerate.class);

  private String entityPackage = "test";
  private String entityName = "Person";
  private String tableName = "person";
  private String ftlDescription = "公告";
  private String primaryKeyPolicy = "uuid";
  private String sequenceCode = "";
  private String[] foreignKeys;
  public static int FIELD_ROW_NUM = 1;
  private String cgformJspHtml;
  private SubTableEntity sub;
  private GenerateEntity subG;
  private CreateFileProperty subFileProperty;
  private String policy;
  private String[] array;
  private GenerateEntity cgformConfig;
  private static CreateFileProperty createFileProperty = new CreateFileProperty();
  
  static { createFileProperty.setActionFlag(true);
    createFileProperty.setServiceIFlag(true);
    createFileProperty.setJspFlag(true);
    createFileProperty.setServiceImplFlag(true);
    createFileProperty.setJspMode("01");
    createFileProperty.setPageFlag(true);
    createFileProperty.setEntityFlag(true);
  }

  public CgformCodeGenerate()
  {
  }

  public CgformCodeGenerate(CreateFileProperty createFileProperty2, GenerateEntity generateEntity)
  {
    this.entityName = generateEntity.getEntityName();
    this.entityPackage = generateEntity.getEntityPackage();
    this.tableName = generateEntity.getTableName();
    this.ftlDescription = generateEntity.getFtlDescription();
    FIELD_ROW_NUM = 1;
    createFileProperty = createFileProperty2;
    createFileProperty.setJspMode(createFileProperty2.getJspMode());
    this.primaryKeyPolicy = generateEntity.getPrimaryKeyPolicy();
    this.sequenceCode = "";
    this.cgformConfig = generateEntity;
  }

  public CgformCodeGenerate(SubTableEntity sub, GenerateEntity subG, CreateFileProperty subFileProperty, String policy, String[] array)
  {
    this.entityName = subG.getEntityName();
    this.entityPackage = subG.getEntityPackage();
    this.tableName = subG.getTableName();
    this.ftlDescription = subG.getFtlDescription();
    createFileProperty = subFileProperty;
    FIELD_ROW_NUM = 1;
    this.primaryKeyPolicy = policy;
    this.sequenceCode = "";
    this.cgformConfig = subG;
    this.foreignKeys = array;
    this.sub = sub;
    this.subG = subG;
    this.subFileProperty = subFileProperty;
    this.policy = policy;
  }

  public Map<String, Object> execute()
  {
    Map data = new HashMap();
    Map fieldMeta = new HashMap();
    Map dictMap = new HashMap();
    data.put("bussiPackage", CodeResourceUtil.bussiPackage);

    data.put("entityPackage", this.entityPackage);

    data.put("entityName", this.entityName);

    data.put("tableName", this.tableName);

    data.put("ftl_description", this.ftlDescription);

    data.put(FtlDef.JEECG_TABLE_ID, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);

    data.put(FtlDef.JEECG_PRIMARY_KEY_POLICY, this.primaryKeyPolicy);
    data.put(FtlDef.JEECG_SEQUENCE_CODE, this.sequenceCode);

    data.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));

    data.put("foreignKeys", this.foreignKeys);

    data.put(FtlDef.FIELD_REQUIRED_NAME, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1));

    data.put(FtlDef.SEARCH_FIELD_NUM, Integer.valueOf(StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1));

    data.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));
    try {
      List<CgFormFieldEntity> columns = this.cgformConfig.deepCopy().getCgFormHead().getColumns();
      String type;
      for (CgFormFieldEntity cf : columns) {
        type = cf.getType();
        if ("string".equalsIgnoreCase(type))
          cf.setType("java.lang.String");
        else if ("Date".equalsIgnoreCase(type))
          cf.setType("java.util.Date");
        else if ("double".equalsIgnoreCase(type))
          cf.setType("java.lang.Double");
        else if ("int".equalsIgnoreCase(type))
          cf.setType("java.lang.Integer");
        else if ("BigDecimal".equalsIgnoreCase(type))
          cf.setType("java.math.BigDecimal");
        else if ("Text".equalsIgnoreCase(type))
          cf.setType("java.lang.String");
        else if ("Blob".equalsIgnoreCase(type)) 
          cf.setType("java.sql.Blob");
       //Online表单在线开发时 添加Clob类型，实体类对应为String类型
        else if ("Clob".equalsIgnoreCase(type)) {
              cf.setType("java.lang.String");
        }
        String fieldName = cf.getFieldName();
        String fieldNameV = JeecgReadTable.formatField(fieldName);
        cf.setFieldName(fieldNameV);
        fieldMeta.put(fieldNameV, fieldName.toUpperCase());
      }
      List pageColumns = new ArrayList();
      for (CgFormFieldEntity cf : columns) {
        if ((StringUtils.isNotEmpty(cf.getIsShow())) && ("Y".equalsIgnoreCase(cf.getIsShow()))) {
          pageColumns.add(cf);
        }
      }

      data.put("cgformConfig", this.cgformConfig);
      data.put("fieldMeta", fieldMeta);
      data.put("columns", columns);
      data.put("pageColumns", pageColumns);
      data.put("buttons", this.cgformConfig.getButtons() == null ? new ArrayList(0) : this.cgformConfig.getButtons());
      data.put("buttonSqlMap", this.cgformConfig.getButtonSqlMap() == null ? new HashMap(0) : this.cgformConfig.getButtonSqlMap());
      data.put("buttonJavaMap", this.cgformConfig.getButtonJavaMap() == null ? new HashMap(0) : this.cgformConfig.getButtonJavaMap());
      data.put("packageStyle", this.cgformConfig.getPackageStyle());
      data.put("dictMap", this.cgformConfig.getDictMap() == null ? new ArrayList(0) : this.cgformConfig.getDictMap());
    } catch (Exception e) {
      e.printStackTrace();
    }
    long serialVersionUID = NonceUtils.randomLong() + 
      NonceUtils.currentMills();
    data.put("serialVersionUID", String.valueOf(serialVersionUID));
    return data;
  }

  public void generateToFile() throws TemplateException, IOException {
    log.info("----jeecg---Code----Generation----[单表模型:" + this.tableName + "]------- 生成中。。。");

    CgformCodeFactory codeFactory = new CgformCodeFactory();
    codeFactory.setProjectPath(this.cgformConfig.getProjectPath());
    codeFactory.setPackageStyle(this.cgformConfig.getPackageStyle());
    if (this.cgformConfig.getCgFormHead().getJformType().intValue() == 1)
      codeFactory.setCallBack(new CgformCodeGenerate(createFileProperty, this.cgformConfig));
    else {
      codeFactory.setCallBack(new CgformCodeGenerate(this.sub, this.subG, this.subFileProperty, "uuid", this.foreignKeys));
    }

    if (createFileProperty.isJspFlag()) {
      if ("03".equals(createFileProperty.getJspMode())) {
        codeFactory.invoke("onetomany/cgform_jspSubTemplate.ftl", "jspList");
      } else if ("06".equals(createFileProperty.getJspMode())) {
        codeFactory.invoke("onetomany/cgform_jspBootstrapSubTemplate.ftl", "jspList");
      } else if ("10".equals(createFileProperty.getJspMode())) {//hplus风格一对一或多
          codeFactory.invoke("onetomany/cgform_jspHPlusSubTemplate.ftl", "jspList");
      }
      else {
        if (StringUtils.isNotEmpty(this.cgformJspHtml)) {
          codeFactory.invokeNotFlt(this.cgformJspHtml, "jsp_add");
          codeFactory.invokeNotFlt(this.cgformJspHtml.replace("doAdd", "doUpdate"), "jsp_update");
        }
        else if ("01".equals(createFileProperty.getJspMode()))
        {
          codeFactory.invoke("cgform_jspTableTemplate_add.ftl", "jsp_add");
          codeFactory.invoke("cgform_jspTableTemplate_update.ftl", "jsp_update");
        } else if ("02".equals(createFileProperty.getJspMode()))
        {
          codeFactory.invoke("cgform_jspDivTemplate_add.ftl", "jsp_add");
          codeFactory.invoke("cgform_jspDivTemplate_update.ftl", "jsp_update");
        } else if ("05".equals(createFileProperty.getJspMode())) {
          codeFactory.invoke("cgform_jspBootstrapTemplate_add.ftl", "jsp_add");
          codeFactory.invoke("cgform_jspBootstrapTemplate_update.ftl", "jsp_update");
        }else if("07".equals(createFileProperty.getJspMode())){
          codeFactory.invoke("cgform_jspHPlusTemplate_add.ftl", "jsp_add");
          codeFactory.invoke("cgform_jspHPlusTemplate_update.ftl", "jsp_update");    
          codeFactory.invoke("cgform_jspHPlusListTemplate.ftl", "jspList"); 
        }
        if(!"07".equals(createFileProperty.getJspMode())&&!"08".equals(createFileProperty.getJspMode())&&!"09".equals(createFileProperty.getJspMode()))
		{
	        codeFactory.invoke("cgform_jspListTemplate.ftl", "jspList");
	        codeFactory.invoke("cgform_jsListEnhanceTemplate.ftl", "jsList");
	        codeFactory.invoke("cgform_jsEnhanceTemplate.ftl", "js");
 
		}
    }
    }
    if (createFileProperty.isServiceImplFlag()) {
      codeFactory.invoke("cgform_serviceImplTemplate.ftl", "serviceImpl");
    }
    if (createFileProperty.isServiceIFlag()) {
      codeFactory.invoke("cgform_serviceITemplate.ftl", "service");
    }
    if (createFileProperty.isActionFlag()) {
      codeFactory.invoke("cgform_controllerTemplate.ftl", "controller");
    }
    if (createFileProperty.isEntityFlag())
    {
      codeFactory.invoke("cgform_entityTemplate.ftl", "entity");
    }
    log.info("----jeecg----Code----Generation-----[单表模型：" + this.tableName + "]------ 生成完成。。。");
  }

  public void generateToFileUserDefined() throws TemplateException, IOException {
    log.info("----jeecg---Code----Generation----[单表模型:" + this.tableName + "]------- 生成中。。。");

    CgformCodeFactory codeFactory = new CgformCodeFactory();
    codeFactory.setProjectPath(this.cgformConfig.getProjectPath());
    codeFactory.setPackageStyle(this.cgformConfig.getPackageStyle());
    if (this.cgformConfig.getCgFormHead().getJformType().intValue() == 1)
      codeFactory.setCallBack(new CgformCodeGenerate(createFileProperty, this.cgformConfig));
    else {
      codeFactory.setCallBack(new CgformCodeGenerate(this.sub, this.subG, this.subFileProperty, "uuid", this.foreignKeys));
    }
    String path = createFileProperty.getJspMode().replace(".", "/");
    log.info("----jeecg------path----[" + path + "]-------");
    if (createFileProperty.isJspFlag()) {
      if (path.endsWith("onetomany")) {
        codeFactory.invokeUserDefined(path + "/cgform_jspSubTemplate.ftl", "jspList");
      } else {
        codeFactory.invokeUserDefined(path + "/cgform_jspTemplate_add.ftl", "jsp_add");
        codeFactory.invokeUserDefined(path + "/cgform_jspTemplate_update.ftl", "jsp_update");
        codeFactory.invokeUserDefined(path + "/cgform_jspListTemplate.ftl", "jspList");
        codeFactory.invokeUserDefined(path + "/cgform_jsListEnhanceTemplate.ftl", "jsList");
        codeFactory.invokeUserDefined(path + "/cgform_jsEnhanceTemplate.ftl", "js");
      }
    }
    if (createFileProperty.isServiceImplFlag()) {
      codeFactory.invokeUserDefined(path + "/cgform_serviceImplTemplate.ftl", "serviceImpl");
    }
    if (createFileProperty.isServiceIFlag()) {
      codeFactory.invokeUserDefined(path + "/cgform_serviceITemplate.ftl", "service");
    }
    if (createFileProperty.isActionFlag()) {
      codeFactory.invokeUserDefined(path + "/cgform_controllerTemplate.ftl", "controller");
    }
    if (createFileProperty.isEntityFlag())
    {
      codeFactory.invokeUserDefined(path + "/cgform_entityTemplate.ftl", "entity");
    }
    log.info("----jeecg----Code----Generation-----[单表模型：" + this.tableName + "]------ 生成完成。。。");
  }

  public GenerateEntity getCgformConfig() {
    return this.cgformConfig;
  }

  public void setCgformConfig(GenerateEntity cgformConfig) {
    this.cgformConfig = cgformConfig;
  }
  public String getCgformJspHtml() {
    return this.cgformJspHtml;
  }
  public void setCgformJspHtml(String cgformJspHtml) {
    this.cgformJspHtml = cgformJspHtml;
  }
}