<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
    <t:datagrid name="tSUsercardinfoList" checkbox="false" pagination="true" fitColumns="true"  actionUrl="userController.do?userCardInfoDatagrid" idField="id" fit="true" >
   <t:dgCol title="主键"  field="id"   hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="userName"       width="140"></t:dgCol>
   <t:dgCol title="身份证号"  field="userCardId"      width="260"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"   hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"   hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd hh:mm:ss" hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"   hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"   hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd hh:mm:ss"     width="170"></t:dgCol>
  </t:datagrid>
  </div>
 </div>	
 <script type="text/javascript">
 
 </script>