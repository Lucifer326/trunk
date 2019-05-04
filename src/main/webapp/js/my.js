/*
*新疆民政信息管理平台
*朱志强
*2016-12-7
*/


/*lists页面搜索条件，点击切换显示部分与全部。*/


$(function(){

	var arrl=$(".arrowshow").children("div").length;
	if(arrl > 1)
	{
		$(".fa-angle-down").show();
	}else{
		$(".fa-angle-down").hide();
	}

	$(".fa-angle-down").click(function(){
	    $(this).parents(".form-group").find(".morebox").css("display","block");
	    $(this).hide();
	    $(this).siblings().show();
	    var fh=$(this).parents(".form-group").height();
	    var btnhh=$(".btnh").height();
	    var mt=(fh-btnhh)/2;
	    $(".btnh").css("marginTop",mt)


	})
	 $(".fa-angle-up").click(function(){
	    $(this).parents(".form-group").find(".morebox").hide();
	    $(this).hide().siblings().show();
	    $(".btnh").css("marginTop",0)
	}) 


})