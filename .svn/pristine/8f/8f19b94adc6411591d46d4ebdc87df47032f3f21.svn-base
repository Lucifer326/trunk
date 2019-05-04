//系统配置列表页面
//点击绑定行政区划按钮验证
function bindDis(){
	var row = getSelectRows();
	if(row.length > 0){
		//alert("id:" + row[0].id +";IsBinddisName:" +  row[0].IsBinddisName);
		//判断选中的信息是否是绑定行政区划
		if(row[0].IsBinddisName=="是"){
		   this.location.href="bssysDeploydisController.do?dislist&deploy_id=" + row[0].id ;
		}
		else {
			tip('所选数据不可进行绑定行政区划操作！');
		}
	}
	else{
		tip('请选中一条记录！');
	}
}