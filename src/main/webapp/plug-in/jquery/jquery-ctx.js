//js获取项目根路径，如： http://localhost:8083/jeecg
var ctx = function getRootPath_dc() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    }else{
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}();
//获取当前网址，如： http://localhost:8083/jeecg/login/login.jsp  
var curWwwPath = function(){
	return window.document.location.href;
}();

//获取主机地址，如： http://localhost:8080 
var localhostPaht = function(){
	return window.location.protocol + '//' + window.location.host;;
}();

//获取项目名称  /jeecg
var projectName = function(){
	 var pathName = window.location.pathname.substring(1);
	 var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	 return webName;
}();