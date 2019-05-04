/*$(document).ready(function(){
	var name="";
	$.ajax({
		  url:"systemRegionController.do?tree", 
			success:function(data){
				 $('#jstree1').jstree({
					 'plugins':['checkbox'], 
					 	"checkbox": {
		                    "keep_selected_style": false,//是否默认选中
		                    "three_state": false//父子级别级联选择
		                },
					    'core' : {
					    	'data':JSON.parse(data)
					  }
				});
			 }
		 });
});*/
function initTree(id, url) {
	var name = "";
	$.ajax({
		url : url,
		success : function(data) {
			$('#' + id).jstree({
				/*'plugins' : [ 'checkbox' ],
				"checkbox" : {
					"keep_selected_style" : false, // 是否默认选中
					"three_state" : false // 父子级别级联选择
				},*/
				'core' : {
					'data' : JSON.parse(data)
				}
			});
		}
	});
}