<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_depart_list" class="easyui-layout" fit="true">

	<div region="center" style="padding: 0px; border: 0px">
		<!--显示当前菜单位置  -->
		<%-- <div class="loaction" style="height:30px; padding-top:10px; border-top:solid 1px #AFAFAF;" >
			<p><span >${currentLocation}</span></p>
     </div> --%>
		<t:datagrid name="departList" title="common.department.list"
			actionUrl="departController.do?departgrid" treegrid="true"
			idField="departid" pagination="false" width='100%' fitColumns="true">
			<t:dgCol title="common.id" field="id" treefield="id" hidden="true"></t:dgCol>
			<t:dgCol title="common.department.name" field="departname"
				treefield="text" width="20"></t:dgCol>
			<t:dgCol title="position.desc" field="description" treefield="src"
				width="10"></t:dgCol>
			<t:dgCol title="common.org.code" field="orgCode"
				treefield="fieldMap.orgCode" width="10"></t:dgCol>
			<t:dgCol title="common.org.type" field="orgType" dictionary="orgtype"
				treefield="fieldMap.orgType" width="10"></t:dgCol>
			<t:dgCol title="common.mobile" field="mobile"
				treefield="fieldMap.mobile" width="10"></t:dgCol>
			<t:dgCol title="common.fax" field="fax" treefield="fieldMap.fax"
				width="10"></t:dgCol>
			<t:dgCol title="common.address" field="address"
				treefield="fieldMap.address" width="10"></t:dgCol>
			<t:dgCol title="common.operation" field="opt" width="10"></t:dgCol>
			<%-- <t:dgDelOpt url="departController.do?del&id={id}" title="common.delete"></t:dgDelOpt> --%>


			<t:dgFunOpt funname="delDept(id)" title="common.delete"></t:dgFunOpt>
			<t:dgFunOpt funname="queryUsersByDepart(id)" title="view.member"></t:dgFunOpt>
			<%-- <t:dgFunOpt funname="setRoleByDepart(id,text)" title="role.set"></t:dgFunOpt> --%>
		</t:datagrid>
		<div id="departListtb">
		    <div style="padding: 6px 0px; float: left; margin-left: 5px;">
			<span style="float: right"> <a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-undo"
				onclick="searchReset()">重置</a>
			</span> <span style="float: right"> <a href="#" 
			class="easyui-linkbutton" icon="icon-search" onclick="searchDepart();">查询</a>
			</span> <span style="float: right">组织机构名称：<input
				id="searchParam" type="text" name="searchParam"
				style="height: 30px;" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
			<div style="height: 30px; padding-top: 10px; border-top: solid 1px #e0e0e0;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add"
					onclick="addOrg()"><t:mutiLang langKey="common.add.param"
						langArg="common.department" /></a> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-edit"
					onclick="update('<t:mutiLang langKey="common.edit.param" langArg="common.department"/>','departController.do?update','departList','800px','500px')"><t:mutiLang
						langKey="common.edit.param" langArg="common.department" /></a> <a
					href="#" class="easyui-linkbutton" plain="true" icon="icon-put"
					onclick="ImportXls()"><t:mutiLang langKey="excelImport"
						langArg="common.department" /></a> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-putout"
					onclick="ExportXls()"><t:mutiLang langKey="excelOutput"
						langArg="common.department" /></a> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-putout"
					onclick="ExportXlsByT()"><t:mutiLang langKey="templateDownload"
						langArg="common.department" /></a> <a href="#"
					class="easyui-linkbutton" plain="true" icon="icon-reload"
					onclick="refreshData()">刷新</a>
			</div>
		</div>
	</div>
</div>
<div
	data-options="region:'west',
	title:'行政区划',
	collapsed:false,
	split:true,
	border:false,"
	style="width: 250px; overflow: hidden;">
	<div class="easyui-panel" style="padding: 0px; border: 0px" fit="true"
		border="false" id="regionListpanel"></div>
</div>

<div
	data-options="region:'east',
	title:'<t:mutiLang langKey="member.list"/>',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 400px; overflow: hidden;" id="eastPanel">
	<div class="easyui-panel" style="padding: 0px; border: 0px" fit="true"
		border="false" id="userListpanel"></div>
</div>

<script type="text/javascript">

    $(function() {
    	$('#regionListpanel').tree({
			url : 'systemRegionController.do?asynDatagrid',
			onSelect : function(node) {
				loadDepart(node);
			},
			onLoadSuccess: function (data) {
                $("#regionListpanel li:eq(0)").find("div").addClass("tree-node-selected");   //设置第一个节点高亮  
                var n = $("#regionListpanel").tree("getSelected");               
                if (n != null) {
                    $("#regionListpanel").tree("select", n.target);    //相当于默认点击了一下第一个节点，执行onSelect方法  
                }
            } 
		});
    });
    function loadDepart(node){
    	var url = 'departController.do?departgrid&regionId='+node.id;
    	loadData(url);
    }
    //刷新
    function reloadTable(){
    	try{
    		var url = 'departController.do?departgrid';
    		var rowData = $('#regionListpanel').tree('getSelected');
    		if(rowData){
    			url += '&regionId='+rowData.id;
    		}
    		loadData(url);
    	}catch(ex){}
    }
    
    function loadData(url){
    	$.get(
	    		url,function(data){
	    			data = eval(JSON.parse(data));
	    			if(data.length){
		    			$('#departList').treegrid('loadData',{total:data.length,rows:data});
	    			}else{
	    				$('#departList').treegrid('loadData',{total:0,rows:[]});
	    			}
	    		}
	    	);
    }
    
    function refreshData(){
    	$('#departList').treegrid('reload');
    	$('#regionListpanel').tree('reload');
    }
    
    function addOrg() {
    	var id = "";
        var rowsData = $('#departList').datagrid('getSelections');
        if (rowsData.length == 1) {
            id = rowsData[0].id;
        }
    	var judgeUrl= 'departController.do?judgeParentDept&id='+id;
    	$.ajax({
    		type : 'POST',
    		url : judgeUrl,// 请求的action路径
    		success : function(data) {
    			var d = $.parseJSON(data);
    			
    			if (d.success) {
    				//成功
    				var regionId = '';
    		        var rowData = $('#regionListpanel').tree('getSelected');
    		        if(rowData){
    		        	regionId = rowData.id;
    		        }
    		        var url = "departController.do?add&id=" + id+"&regionId="+regionId;
    		        var width = "800px";
    		        var height = "500px";
    		        add('<t:mutiLang langKey="common.add.param" langArg="common.department"/>', url, "departList",width,height);
    				
    			} else {
    				//失败
    				tip(d.msg);
    			}
    		}
    	});
        
    }

    function queryUsersByDepart(departid){
        var title = '<t:mutiLang langKey="member.list"/>';
        if(li_east == 0 || $('#main_depart_list').layout('panel','east').panel('options').title != title){
            $('#main_depart_list').layout('expand','east');
        }
        <%--$('#eastPanel').panel('setTitle','<t:mutiLang langKey="member.list"/>');--%>
        $('#main_depart_list').layout('panel','east').panel('setTitle', title);
        $('#main_depart_list').layout('panel','east').panel('resize', {width: 500});
        $('#userListpanel').panel("refresh", "departController.do?userList&departid=" + departid);
    }
    /**
     * 为 组织机构 设置 角色
     * @param departid 组织机构主键
     * @param departname 组织机构名称
     */
    function setRoleByDepart(departid, departname){
        var currentTitle = $('#main_depart_list').layout('panel', 'east').panel('options').title;
        if(li_east == 0 || currentTitle.indexOf("<t:mutiLang langKey="current.org"/>") < 0){
            $('#main_depart_list').layout('expand','east');
        }
        var title = departname + ':<t:mutiLang langKey="current.org"/>';
        $('#main_depart_list').layout('panel','east').panel('setTitle', title);
        $('#main_depart_list').layout('panel','east').panel('resize', {width: 200});
        var url = {
            <%--title :"test",--%>
            href:"roleController.do?roleTree&orgId=" + departid
        }
        $('#userListpanel').panel(url);
        $('#userListpanel').panel("refresh");
    }
    
  	//删除组织机构
    function delDept(id){
	  var judgeUrl= 'departController.do?judgeDelDept&id='+id;
    	$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		url : judgeUrl,// 请求的action路径
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			var d = $.parseJSON(data);
    			if (d.success) {
    				if(d.msg != ""){
    					//可以删除，但存在关联用户，需用户确认后才可继续删除
    					var tabName= 'departList';
    					var url= 'departController.do?delDept&id='+id;
    					$.dialog.confirm(d.msg, function(){
    						doSubmit(url,tabName);
    						rowid = '';
    					}, function(){
    					});
    				}else {
    					//可以删除，且不存在关联用户
    					var tabName= 'departList';
    					var url= 'departController.do?delDept&id='+id;
    					$.dialog.confirm('<t:mutiLang langKey="confirm.delete.this.record"/>', function(){
    						doSubmit(url,tabName);
    						rowid = '';
    					}, function(){
    					});
    				}
    			}else{
    				//不可删除
    				tip(d.msg);
    			}
    		}
    	});
    }
    
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'departController.do?upload', "departList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("departController.do?exportXls","departList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("departController.do?exportXlsByT","departList");
    }
    
  	//重置
	function searchReset() {
		$("#departListtb").find(":input").val("");
	}
  	
	//查询功能
	function searchDepart(){		
		var url = 'departController.do?departgrid';
		var regionId = $("#regionListpanel").tree("getSelected").id;
		var searchParam = $('#searchParam').val();				
		//如果查询的 值  不为 空 去后台 查询数据 返给前台 
		if(searchParam !=''){
						
			$.ajax({
				  
				  type: 'POST',
				  url: url,
				  data: {"name" : searchParam,
					     "regionId":regionId
				  },
				 
				  success: function(data){
					  
					  data = eval(JSON.parse(data));
		    			
					  //如果有数据 展示在页面 
					   if(data.length){
			    			$('#departList').treegrid('loadData',{total:data.length,rows:data});
			    						    			
		    			}else{
		    				$('#departList').treegrid('loadData',{total:0,rows:[]});
		    			} 					   
				  },
				});
			
		}else{
			reloadTable();
		}
	}
    
</script>