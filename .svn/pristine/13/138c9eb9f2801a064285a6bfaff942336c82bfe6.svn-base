<ul id=${tree_id} class='${tree_class}' style='${tree_style}'></ul>
<script type='text/javascript'>
<#if tree_type=="jstree">
	$(document).ready(function(){
		var name="";
		$.ajax({
			  url:'${tree_url}', 
				success:function(data){
					 $('#${tree_id}').jstree({
						 'plugins':[<#if tree_checkBox>'checkbox',</#if><#if tree_contextmenu>'contextmenu',</#if>], 
						 <#if tree_checkBox>
						 	"checkbox": {
			                    "keep_selected_style": false,//是否默认选中
			                    "three_state": false//父子级别级联选择
			                },
			             </#if>
			             <#if tree_contextmenu>
						 	"contextmenu": {
			                    "items":{
			                    	<#list tree_contextmenuFunctions as f >
			                    		"${f[0]}":{
			                    			"label":"${f[0]}",
	                                		"action":${f[1]}
			                    		},
			                    	</#list>
			                    }
			                },
			             </#if>
						    'core' : {
						    	'data':JSON.parse(data)
						  }
					});
				 }
			 });
	});
	<#if tree_isChecked>
	function treeChecked() {
	    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var ids = $('#${tree_id}').jstree().get_selected(); //获取所有选中的节点ID
	    var nodes = $('#${tree_id}').jstree().get_selected(true); //获取所有选中的节点对象
	    var names="";
	    $.each( nodes, function(i, n){
	    	if (i==0){
	    		names+= n.text;
	    	}
	    	else{
	    		names+= ","+n.text;
	    	}
		});
	    return ids+"|" +names;
	}
	</#if>
</#if>
</script>