
 var selectConditionArr=new Array();//所有拼装好的查询条件数组
  //点击添加查询条件
  function addCondition(){
    		//查看条件是否为空  
	  		var controlId=$("#controlId").val();	//控件id
    		var relation=$("#relation").val();	//关系	
    		var operator=$("#operator").val();//操作符
    		//var resultsort=$("#resultsort").val();//排序	
    		
    		for(var p in selectConditionArr){
    			if(controlId == selectConditionArr[p].controlId){
    				alert("控件已关联条件");
    				return;
    			}
    		}
    		if(controlId == ""){
    			alert("控件id不能为空");
    			return;
    		}
    					$("#d_table").append('<tr">'+
    							'+<td><input type="checkbox"></td>'+
    							'+<td>'+controlId+'</td>'+
    							'+<td>'+relation+'</td>'+
    							'+<td>'+operator+'</td>'+
    							/*'+<td>'+resultsort+'</td>'+*/
    							'+</tr>');	
    					saveCondition();
  }
  //数据回显时清空查询条件
  function clearCondition(){
	  var checkbox=$('#d_table').find('tr');
		 checkbox.each(function(index,el){			
					$(el).remove();
			})
  }
	//点击删除查询条件
	 function delCondition(){
		 var checkbox=$('#d_table').find('tr');
			
		 checkbox.each(function(index,el){			
				
			 if($(el).find('input[type=checkbox]').prop('checked')){
					$(el).remove();
					saveCondition();
				}
			})
     } 
	  
	  //保存的时候 获取所有的查询条件
	  function saveCondition(){
		  selectConditionArr.splice(0,selectConditionArr.length);//保存之前 清空数组
		  $('#d_table').find('tr').each(function(index,el){
			  selectConditionArr.push({
				  controlId:$(el).find('td:eq(1)').html(),	
				  relation:$(el).find('td:eq(2)').html(),	
				  operator:$(el).find('td:eq(3)').html(),	
				})
			})
		  var selectConditionjs = JSON.stringify(selectConditionArr);
		  $("#selectConditionList").val(selectConditionjs).blur();
		  	
		  	var controlId=$("#controlId").val('');	//控件id
  			var relation=$("#relation").val('');	//关系	
  			var operator=$("#operator").val('');//操作符
		  
	  }