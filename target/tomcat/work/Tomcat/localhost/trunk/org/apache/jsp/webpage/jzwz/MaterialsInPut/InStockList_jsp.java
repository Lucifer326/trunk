/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-04-18 08:43:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.webpage.jzwz.MaterialsInPut;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.jeecgframework.core.util.StringUtil;

public final class InStockList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>列表页</title>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/plugins/bootstrap-table/bootstrap-table.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/animate.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/style.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/plugins/sweetalert/sweetalert.css\" rel=\"stylesheet\">\r\n");
      out.write("    <script>\r\n");
      out.write("        //=======================================选择仓库\r\n");
      out.write("        function SelectWarehouse() {\r\n");
      out.write("            layer.open({\r\n");
      out.write("                type: 2,\r\n");
      out.write("                title: '选择仓库',\r\n");
      out.write("                maxmin: true,\r\n");
      out.write("                zIndex: 1000,\r\n");
      out.write("                shadeClose: true, //点击遮罩关闭层\r\n");
      out.write("                area: ['800px', '500px'],\r\n");
      out.write("                content: 'materialsInputController.do?selectWarehouse',\r\n");
      out.write("                btn: ['确定', '取消'],\r\n");
      out.write("                yes: function (index, layero) {\r\n");
      out.write("                    var data = $(layero).find(\"iframe\")[0].contentWindow.CheckSelect();\r\n");
      out.write("                    if (data != false) {\r\n");
      out.write("                        var str = data.split(',');\r\n");
      out.write("                        $(\"#txtWareHouse\").val(str[0]);\r\n");
      out.write("\t                    $(\"#storageWarehouse\").val(str[1]);\r\n");
      out.write("                        layer.close(index);\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"gray-bg\">\r\n");
      out.write("<div class=\"wrapper wrapper-content animated fadeInRight\">\r\n");
      out.write("    <div class=\"alert alert-success whj_location\"><span class=\"c6\">当前位置：自治区救灾物资信息管理系统 &nbsp > &nbsp</span>物资入库</div>\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-sm-12\">\r\n");
      out.write("            <div class=\"ibox float-e-margins\">\r\n");
      out.write("                <div class=\"ibox-title\">\r\n");
      out.write("                    <h5>搜索条件</h5>\r\n");
      out.write("                    <div class=\"ibox-tools\">\r\n");
      out.write("                        <a class=\"collapse-link\">\r\n");
      out.write("                            <i class=\"fa fa-chevron-up\"></i>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"ibox-content whj-padding\">\r\n");
      out.write("                    <form id=\"search_form\" class=\"form-horizontal m-t\" role=\"form\"\r\n");
      out.write("                          action=\"materialsInputController.do?queryAll\" method=\"post\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <ul class=\"ulist\">\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <span class=\"fl labeltext\">入库单号：</span>\r\n");
      out.write("                                    <div class=\"fl\">\r\n");
      out.write("                                        <input type=\"text\" name=\"storageNumber\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.storageNumber}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("                                               class=\"form-control\" onkeyup=\"this.value=this.value.replace(/[`~!@·%#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）_+——|{}【】‘；：”“'。，、？]/g,'');\"/>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"clear\"></div>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <span class=\"fl labeltext\">入库仓库：</span>\r\n");
      out.write("                                    <div class=\"fl\">\r\n");
      out.write("                                        <input type=\"text\" id=\"txtWareHouse\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.repositoryName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("                                               class=\"form-control\" name=\"repositoryName\">\r\n");
      out.write("                                        <input type=\"hidden\" id=\"storageWarehouse\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.storageWarehouse}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"form-control\" name=\"storageWarehouse\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <button class=\"btn btn-primary btna \" id=\"btna\" type=\"button\"\r\n");
      out.write("                                            onclick=\"SelectWarehouse()\">&nbsp;选择\r\n");
      out.write("                                    </button>\r\n");
      out.write("                                    <div class=\"clear\"></div>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <span class=\"fl labeltext\">入库日期：</span>\r\n");
      out.write("                                    <input id=\"hello\" name=\"startDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.startDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("                                           class=\"laydate-icon form-control layer-date fl w-148\">\r\n");
      out.write("                                    <span class=\"fl communicate\">―</span>\r\n");
      out.write("                                    <input id=\"hello1\" name=\"endDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.endDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("                                           class=\"laydate-icon form-control layer-date fl w-148\">\r\n");
      out.write("                                    <div class=\"clear\"></div>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <button class=\"btn btn-primary btnh\" id=\"btnh\" type=\"submit\"><i\r\n");
      out.write("                                            class=\"fa fa-search\"></i>&nbsp;搜索\r\n");
      out.write("                                    </button>\r\n");
      out.write("                                    <input class=\"btn btn-warning btnh\" id=\"reset\" type=\"button\"\r\n");
      out.write("                                           style=\"width: 70px; margin-left: 20px;\" value=\"重置\">\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                <!--ibox-content-->\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!--row-->\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-sm-12\">\r\n");
      out.write("            <div class=\"ibox float-e-margins\">\r\n");
      out.write("                <div class=\"ibox-content\">\r\n");
      out.write("                    <div class=\"example-wrap\">\r\n");
      out.write("                        <div class=\"example\">\r\n");
      out.write("                            <div class=\"btn-fl\">\r\n");
      out.write("                                <button type=\"button\" class=\"btn btn-outline  btn-primary\" id=\"Add2\">新建</button>\r\n");
      out.write("                                <button type=\"button\" class=\"btn btn-outline  btn-success\" id=\"Look\">查看</button>\r\n");
      out.write("                                <button type=\"button\" class=\"btn btn-outline  btn-warning\" id=\"Edit\">修改</button>\r\n");
      out.write("                                <button type=\"button\" class=\"btn btn-outline  btn-danger\" id=\"del\">删除</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <table id=\"exampleTableEvents\" data-mobile-responsive=\"true\">\r\n");
      out.write("                                <thead>\r\n");
      out.write("                                <tr>\r\n");
      out.write("                                    ");
      out.write("\r\n");
      out.write("                                    <th></th>\r\n");
      out.write("                                    <th data-field=\"number\">序号</th>\r\n");
      out.write("                                    <th data-field=\"num\">入库单号</th>\r\n");
      out.write("                                    <th data-field=\"n\">采购批次</th>\r\n");
      out.write("                                    <th data-field=\"type\">入库仓库</th>\r\n");
      out.write("                                    <th data-field=\"size\">入库人</th>\r\n");
      out.write("                                    <th data-field=\"name\">入库日期</th>\r\n");
      out.write("                                </tr>\r\n");
      out.write("                                </thead>\r\n");
      out.write("                                <tbody id=\"table_content\">\r\n");
      out.write("                                ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                                </tbody>\r\n");
      out.write("                            </table>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- End Example Events -->\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!--row-->\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jzwz/MaterialsInPut/MaterialsInPut.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- 全局js -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrap.min.js\"></script>\r\n");
      out.write("<!-- 自定义js -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/content.js\"></script>\r\n");
      out.write("<!-- Bootstrap table -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/bootstrap-table/bootstrap-table.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Peity -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/demo/bootstrap-table-demo.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/layer/laydate/laydate.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/layer/layer.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/sweetalert/sweetalert.min.js\"></script>\r\n");
      out.write("<!-- 个人js -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/my.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    //外部js调用\r\n");
      out.write("    laydate({\r\n");
      out.write("        elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'\r\n");
      out.write("        event: 'focus' //响应事件。如果没有传入event，则按照默认的click\r\n");
      out.write("    });\r\n");
      out.write("    laydate({\r\n");
      out.write("        elem: '#hello1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'\r\n");
      out.write("        event: 'focus' //响应事件。如果没有传入event，则按照默认的click\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"input[name=btSelectAll]\").on('click', function () {\r\n");
      out.write("\r\n");
      out.write("        if ($(\"input[name='btSelectAll']\").is(':checked')) {\r\n");
      out.write("\r\n");
      out.write("            $(\"#exampleTableEvents\").children('tbody').children('tr').each(function () {\r\n");
      out.write("                $(this).addClass(\"selected\");\r\n");
      out.write("            })\r\n");
      out.write("        } else {\r\n");
      out.write("\r\n");
      out.write("            $(\"#exampleTableEvents\").children('tbody').children('tr').each(function () {\r\n");
      out.write("                $(this).removeClass(\"selected\");\r\n");
      out.write("            })\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    //=========================================================重置\r\n");
      out.write("    $(\"#reset\").click(function () {\r\n");
      out.write("        $(\"#search_form input[name]\").val(\"\");\r\n");
      out.write("        $(\"#search_form input[name]\").val(\"\");\r\n");
      out.write("    });\r\n");
      out.write("    //========================================================================新建\r\n");
      out.write("    $('#Add2').on('click', function () {\r\n");
      out.write("        layer.open({\r\n");
      out.write("            type: 2,\r\n");
      out.write("            title: '新建',\r\n");
      out.write("            maxmin: true,\r\n");
      out.write("            zIndex: 100,\r\n");
      out.write("            shadeClose: true, //点击遮罩关闭层\r\n");
      out.write("            area: ['800px', '500px'],\r\n");
      out.write("            content: 'materialsInputController.do?toAdd2',\r\n");
      out.write("            btn: ['确定', '取消'],\r\n");
      out.write("            yes: function (index, layero) {\r\n");
      out.write("\r\n");
      out.write("                if (verification(layero.find(\"iframe\")[0].contentWindow,\"add_form\")) {\r\n");
      out.write("                    //======================================提交表单\r\n");
      out.write("                    var form = layero.find(\"iframe\")[0].contentWindow.document.getElementById(\"add_form\");\r\n");
      out.write("                    var fileForm = new FormData(form);\r\n");
      out.write("\r\n");
      out.write("                    //发送ajax请求添加数据\r\n");
      out.write("\t                materialsInPutAdd(fileForm,index);\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("    //======================================================================查看\r\n");
      out.write("    $('#Look').on('click', function () {\r\n");
      out.write("        //定义一个数组，用于存放所选中的id值\r\n");
      out.write("        var ids = [];\r\n");
      out.write("        //遍历所有选中的复选框，并将id值添加到数组中\r\n");
      out.write("        $('#table_content input[type=checkbox]:checked').each(function () {\r\n");
      out.write("            ids.push($(this).attr('values'));\r\n");
      out.write("        });\r\n");
      out.write("        //根据ids数组的长度判断所选择的条数\r\n");
      out.write("        if (ids.length > 1) {\r\n");
      out.write("            layer.alert(\"只能选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        } else if (ids.length == 0) {\r\n");
      out.write("            layer.alert(\"请选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //获取所选择的的id\r\n");
      out.write("        var id = ids[0];\r\n");
      out.write("        layer.open({\r\n");
      out.write("            type: 2,\r\n");
      out.write("            title: '查看',\r\n");
      out.write("            maxmin: true,\r\n");
      out.write("            shadeClose: true, //点击遮罩关闭层\r\n");
      out.write("            area: ['800px', '500px'],\r\n");
      out.write("            content: 'materialsInputController.do?inStockLook&ctrl=query&uuid=' + id,\r\n");
      out.write("            btn: ['关闭']\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    //===============================================================================修改\r\n");
      out.write("    $('#Edit').on('click', function () {\r\n");
      out.write("        //定义一个数组，用于存放所选中的id值\r\n");
      out.write("        var ids = [];\r\n");
      out.write("        //遍历所有选中的复选框，并将id值添加到数组中\r\n");
      out.write("        $('#table_content input[type=checkbox]:checked').each(function () {\r\n");
      out.write("            ids.push($(this).attr('values'));\r\n");
      out.write("        });\r\n");
      out.write("        //根据ids数组的长度判断所选择的条数\r\n");
      out.write("        if (ids.length > 1) {\r\n");
      out.write("            layer.alert(\"只能选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        } else if (ids.length == 0) {\r\n");
      out.write("            layer.alert(\"请选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //获取所选择的的id\r\n");
      out.write("        var id = ids[0];\r\n");
      out.write("        layer.open({\r\n");
      out.write("            type: 2,\r\n");
      out.write("            title: '修改',\r\n");
      out.write("            maxmin: true,\r\n");
      out.write("            zIndex: 100,\r\n");
      out.write("            shadeClose: true, //点击遮罩关闭层\r\n");
      out.write("            area: ['800px', '500px'],\r\n");
      out.write("            content: 'materialsInputController.do?inStockLook&ctrl=edit&uuid=' + id,\r\n");
      out.write("            btn: ['确定', '取消'],\r\n");
      out.write("            yes: function (index, layero) {\r\n");
      out.write("                if (verification(layero.find(\"iframe\")[0].contentWindow,\"edit_form\")) {\r\n");
      out.write("                    //======================================提交表单\r\n");
      out.write("                    var form = layero.find(\"iframe\")[0].contentWindow.document.getElementById(\"edit_form\");\r\n");
      out.write("                    var fileForm = new FormData(form);\r\n");
      out.write("                    //发送ajax请求执行修改\r\n");
      out.write("\t                materialsInPutEdit(fileForm, index);\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("    //================================================================删除\r\n");
      out.write("    $('#del').click(function () {\r\n");
      out.write("        //定义一个数组，用于存放所选中的id值\r\n");
      out.write("        var ids = [];\r\n");
      out.write("        //遍历所有选中的复选框，并将id值添加到数组中\r\n");
      out.write("        $('#table_content input[type=checkbox]:checked').each(function () {\r\n");
      out.write("            ids.push($(this).attr('values'));\r\n");
      out.write("        });\r\n");
      out.write("        //根据ids数组的长度判断所选择的条数\r\n");
      out.write("        if (ids.length > 1) {\r\n");
      out.write("            layer.alert(\"只能选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        } else if (ids.length == 0) {\r\n");
      out.write("            layer.alert(\"请选择一条记录！\");\r\n");
      out.write("            return false;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        // var id = $('#table_content input[type=checkbox]:checked').attr('values');\r\n");
      out.write("\r\n");
      out.write("        //获取所选择的的id\r\n");
      out.write("        var id = ids[0];\r\n");
      out.write("        swal({\r\n");
      out.write("            title: \"您确定要删除这条信息吗\",\r\n");
      out.write("            text: \"删除后将无法恢复，请谨慎操作！\",\r\n");
      out.write("            type: \"warning\",\r\n");
      out.write("            showCancelButton: true,\r\n");
      out.write("            confirmButtonColor: \"#DD6B55\",\r\n");
      out.write("            confirmButtonText: \"删除\",\r\n");
      out.write("            cancelButtonText: \"取消\",\r\n");
      out.write("            closeOnConfirm: false\r\n");
      out.write("        }, function () {\r\n");
      out.write("            //发送ajax请求执行删除操作\r\n");
      out.write("\t        materialsInPutdelete(id);\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\":button[name='refresh']\").click(function () {\r\n");
      out.write("\t    $(\"#search_form\").submit();\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /webpage/jzwz/MaterialsInPut/InStockList.jsp(134,32) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/webpage/jzwz/MaterialsInPut/InStockList.jsp(134,32) '${maps}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${maps}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /webpage/jzwz/MaterialsInPut/InStockList.jsp(134,32) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("row");
    // /webpage/jzwz/MaterialsInPut/InStockList.jsp(134,32) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("i");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                                    <tr>\r\n");
          out.write("                                        <td><input type=\"checkbox\" values=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.uuid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\"></td>\r\n");
          out.write("                                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.storage_Number}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.purchase_Batch}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.repository_Name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.storage_Person}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                        <td>");
          if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("                                    </tr>\r\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /webpage/jzwz/MaterialsInPut/InStockList.jsp(142,44) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${row.storage_Date}", java.util.Date.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /webpage/jzwz/MaterialsInPut/InStockList.jsp(142,44) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setPattern("yyyy-MM-dd");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }
}