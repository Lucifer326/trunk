<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><t:mutiLang langKey="platform.title" /></title>
<t:base type="jquery,easyui,tools,autocomplete"></t:base>

<link rel="stylesheet" href="plug-in/easyui/themes/salvation/societySalvation/css/font-awesome.css"/>
<style>
.layout-panel,.panel{
overflow:visible;
}

li.divider {
    margin-top: 9px;
    margin-bottom: 0px;
    height: 1px;
    font-size: 0;
    background-color: #e5e5e5;
}
</style>
<script type="text/javascript">
$(function() {	
	$.ajax({
        url: "loginController.do?primaryMenuss",
        async:false,
        datatype:"json",
        success: function (data) { 
        	//输出顶部菜单
        	$("#List1").html(data.replaceAll("\"", ""));
			
    		$(".m_nav ul li a").click(function(){
	           $(".cs-west").prev().find('.panel-title').text("");
	           $(".cs-west").prev().find('.panel-title').text($(this).text());
       		 });
        	
    		//顶部菜单鼠标移动上时，添加样式“hover”
            $(".m_nav ul li").hover(function () {
                $(".m_nav ul li").removeClass("hover");
                $(this).addClass("hover");
            });
            //顶部菜单鼠标移开时，移除样式“hover”
            $(".m_nav ul li").mouseleave(function () {
                $(".m_nav ul li").removeClass("hover");
            });

	        $(".m_nav ul li").click(function(){
	            $(".m_nav ul li").removeClass("hover");
	            $(this).addClass("actived");
	            $(this).siblings().removeClass("actived");
	        });
	        
	        //一级菜单栏重写 bg dkk
	            var list1Width = 0;
	            for (var i = 0; i < $('#List1 li').length; i++) {
	           	 
	                list1Width += $('#List1 li').eq(i).outerWidth(true);
	               
	            }
	            $('#List1').width(list1Width+10);
	            if ($('.m_nav').width() * 0.95 > list1Width) {
	                $('.img1, .img2').hide();
	            }  
	            var currLeft = 0;
	            var isScroll = false;
	            $('.img1').click(function () {
	                currLeft = parseInt($('#List1').css('left'));
	                //console.log(wrapWidth)
	                if (currLeft < -50) {
	                    $('#List1').stop(true).animate({ 'left': currLeft + 100 + 'px' });
	                } else if (currLeft < 0) {
	                    $('#List1').stop(true).animate({ 'left': currLeft + 20 + 'px' });
	                }
	            });
	            $('.img2').click(function () {
	                currLeft = parseInt($('#List1').css('left'));
	              //  console.log($('#List1').css('left'));

	                if (-currLeft + $('.Cont').width() + 50 < list1Width) {
	                    $('#List1').stop(true).animate({ 'left': currLeft - 100 + 'px' });
	                } else if (-currLeft + $('.Cont').width() < list1Width) {
	                    $('#List1').stop(true).animate({ 'left': currLeft - 20 + 'px' });
	                }
	            });
	            $(document).click(function () {
	                isScroll = false;
	            })
	            $('.m_nav').click(function (e) {
	                e.stopPropagation();
	                isScroll = true;
	            })
	            if (navigator.userAgent.indexOf('Firefox') >= 0) {
	                document.body.addEventListener("DOMMouseScroll", function (event) {
	                    //console.log(event.detail)
	                    if (event.detail < 0 && isScroll) {
	                        $('.img1').click();
	                    } else if (event.detail > 0 && isScroll) {
	                        $('.img2').click();
	                    }

	                })
	            }
	            document.body.onmousewheel = function (event) {
	                event = event || window.event;
	             //   console.log(event.wheelDelta);
	                if (event.wheelDelta > 0 && isScroll) {
	                    $('.img1').click();
	                } else if (event.wheelDelta < 0 && isScroll) {
	                    $('.img2').click();
	                }
	            }
	           //一级菜单栏重写 结束
        }
    });
	 //默认选中第一个顶部菜单
	$("#List1 a").eq(0).trigger("click");
});
</script>

</head>
<body class="bodybg">
	<div class="easyui-layout master_wrap" data-options="fit:true">
	<input type="hidden" id="hidUrlParent" name ="hidUrlParent"/>
		<div data-options="region:'north',border:'0'" title="&nbsp" style="height: 116px; overflow: visible; background: #0665bf; border:none">
			<div class="header">
				<div class="logo fl">
					<a><t:mutiLang langKey="platform.common.title"/></a>
				</div>
				<div class="header_right fr">
					<ul>
						<li class="icon_home"><a href="">返回首页</a></li>
						<li class="icon_help"><a href="">文件下载</a></li>								
						<%-- <li class="icon_exit"><a onclick="exit('loginController.do?logout','<t:mutiLang langKey="common.exit.confirm"/>',1);">退出系统</a></li> --%>
						<li class="icon_pwd" style="position:relative;">
							<a href="#">${userName }【${currentOrgName }】</a>
							<ul class="listdown">
								<li>
									<a href="javascript:add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword','',550,200)">
										<i class="fa fa-pencil"></i>
										 <t:mutiLang langKey="common.change.password"/>
									</a>
								</li>
								<li>
									<a href="javascript:openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')">
										<i class="fa fa-user"></i>
										 <t:mutiLang langKey="common.profile"/>
									</a>
								</li>
								<!-- 用户存在多个归属组织机构时，提供切换组织机构功能 -->
	                            <c:if test="${fn:length(sessionScope.LOCAL_CLINET_USER_DEPART_LIST) > 1}">
	                            	<li>
	                            		<a href="javascript:add('<t:mutiLang langKey="common.change.depart"/>','userController.do?changeDepart','',550,300)">
	                            		<i class="fa fa-cogs"></i>
	                            		<t:mutiLang langKey="common.change.depart"/>
	                            		</a>
	                            	</li>
	                            </c:if>
								<li>
									<a href="javascript:add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle','',570,250)">
										<i class="fa fa-cog"></i>
										 <t:mutiLang langKey="common.my.style"/>
									</a>
								</li>

								<li>
									<a href="javascript:clearLocalstorage()">
										<i class="fa fa-trash"></i>
										<t:mutiLang langKey="common.clear.localstorage"/>
									</a>
								</li>
								
								<li>
									<a href="javascript:updateVersion()">
										<i class="fa fa-book"></i>
										<t:mutiLang langKey="common.update.version"/>
									</a>
								</li>
								
								<li class="divider"></li>

								<li>
									<a onclick="exit('loginController.do?logout','<t:mutiLang langKey="common.exit.confirm"/>',1);">
										<i class="fa fa-power-off"></i>
										 <t:mutiLang langKey="common.logout"/>
									</a>
								</li>
							</ul>
						</li>				
					</ul>
				</div>
				<div class="m_nav">
					<div class="img1"></div>
					<div class="Cont" id="ISL_Cont">
						<ul id="List1">

						</ul>
						<div style="clear: both"></div>
					</div>
					<div class="img2"></div>
				</div>
				<!--m_nav-->
			</div>
			<!--header-->
		</div>
		<!--north-->
		<div id="westbox" data-options="region:'west',split:true" title="办公中心" style="width: 200px;" class="cs-west"></div>
		<!--west-->
		<div data-options="region:'center',iconCls:'icon-ok',border:'false'" style="border: 0;">
			<div id="tabs" class="easyui-tabs" fit="true" border="false" style="width: 100%">
				<div title="首页" href="loginController.do?home" >
					<div class="cs-home-remark" ></div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',split:true" style="overflow: hidden; border: 0">
			<div class="footer1">河北中科恒运软件科技股份有限公司</div>
		</div>
		<!--south-->
	</div>
	<!--easyui-layout-->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
	<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"> 
    String.prototype.replaceAll = function(s1,s2) { 
        return this.replace(new RegExp(s1,"gm"),s2); 
    };   
   
/* 点击出现下拉菜单 */    
$(".icon_pwd").click(function(){
	$(this).find(".listdown").toggle();
})    
    
function addTab(title, url){	
    if ($('#tabs').tabs('exists', title)){
        $('#tabs').tabs('select', title);//选中并刷新
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if(url != undefined && currTab.panel('options').title != '首页') {
            $('#tabs').tabs('update',{
                tab:currTab,
                options:{
                    content:createFrame(url)
                }
            })
        }
    } else {
        var content = createFrame(url);
        
        $('#tabs').tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
    tabClose();
}
function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}
        
function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close',subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle =$(this).children(".tabs-closable").text();

        $('#mm').data("currtab",subtitle);
        $('#tabs').tabs('select',subtitle);
        return false;
    });
}       
//绑定右键菜单事件
function tabCloseEven() {
    //刷新
    $('#mm-tabupdate').click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if(url != undefined && currTab.panel('options').title != '首页') {
            $('#tabs').tabs('update',{
                tab:currTab,
                options:{
                    content:createFrame(url)
                }
            })
        }
    })
    //关闭当前
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close',currtab_title);
    })
    //全部关闭
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t != '首页') {
                $('#tabs').tabs('close',t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        var nextall = $('.tabs-selected').nextAll();        
        if(prevall.length>0){
            prevall.each(function(i,n){
                var t=$('a:eq(0) span',$(n)).text();
                if(t != '首页') {
                    $('#tabs').tabs('close',t);
                }
            });
        }
        if(nextall.length>0) {
            nextall.each(function(i,n){
                var t=$('a:eq(0) span',$(n)).text();
                if(t != '首页') {
                    $('#tabs').tabs('close',t);
                }
            });
        }
        return false;
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function(){
        var nextall = $('.tabs-selected').nextAll();
        if(nextall.length==0){
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        if(prevall.length==0){
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function(){
        $('#mm').menu('hide');
    })
}

$(function () {
    tabCloseEven();
/*     $('.cs-navi-tab').click(function () {
    	alert(0);
        var $this = $(this);

        var href = $this.attr('src');
        var title = $this.text();
        addTab(title, href);
    }); */
});

	
var isFirst = true;
function ChanageMenu(parent){
	var id =parent.id;
	 $.ajax({
	        url: "loginController.do?leftmenu",
	        async:false,
	        data:{
	        	parentid:id
			},
	        success: function (data) {
	        	$("#westbox").html(data.replaceAll("\"", ""));
	        	$.parser.parse($("#sel").parent());
	            if (isFirst) {
	                isFirst = false;
	                //var module = $("#hidUrlModule").val();
	                //$('#sel').accordion('select', module);
	            }
	            
	            $(".cs-west ul li").hide();//首先先让 ul下面的li隐藏
	            $(this).addClass("plus");
	            $(".cs-west span").click(function () {
	                $(this).next("ul").children().toggle("slow");  //当前被点击的span的同辈元素点击显示或隐藏
	                //  $(this).nextAll(".cs-west ul li").toggle(); //当前被点击的span的下面的所有的li元素点击显示或隐藏
	                if ($(this).hasClass("jia")) {
	                    $(this).removeClass("jia");
	                    $(this).addClass("plus");
	                } else if ($(this).hasClass("plus")) {
	                    $(this).removeClass("plus");
	                    $(this).addClass("jia");
	                }	            

	       		 });
	            //左侧菜单点击事件
	            $('.cs-navi-tab').click(function() {
	                var ptext=$(this).parents('#westbox').prev('.panel-header').find('.panel-title').text();
			        var $this = $(this);
			        var href = $this.attr('src');
			        var title = $this.text();
			        if(ptext=="农村低保"  || ptext=="城市低保" || ptext=="特困供养" || ptext=="临时救助" || ptext=="受灾人员救助"|| ptext=="城市优抚")
			        {
			         	title = ptext + "-" + $this.text();
			        }
			       
			        addTab(title, href);
			    });   
	    		
	            $(".cs-west a.cs-navi-tab").click(function () {
	            
	                $(".cs-west a.cs-navi-tab").removeClass("selected");
	                $(this).addClass("selected");
	            });
	            $(".cs-west ul li a.cs-navi-tab").click(function () {
	                $(".cs-west ul li a.cs-navi-tab").removeClass("actived");
	                $(this).addClass("actived");
	            });  	            
	        }
	 });
    
}

function updateVersion(){
    $.ajax({
    	url:'versionUpdateController.do?checkVersion',
    	type:'post',
    	dataType:'json',
    	success:function(data){
    		if(data=='已是最新版本，无需更新！'){
    			tip(data);
    		}else if(data=='有新版本，需要更新！'){
    			$.messager.confirm('操作确认','您确定要更新吗？',
    					function(flag){
    				if(flag==true){
    					$.ajax({
        	            	url:'versionUpdateController.do?updateVersion',
        	            	type:'post',
        	            	dataType:'json',
        	            	success:function(data1){
        	            		if(data1=='更新成功，请重启服务!'){
        	            			tip(data1);
        	            		}else if(data1=='更新失败，请重新操作！'){
        	            			tip(data1);
        	            		}else{
        	            			tip('服务器报错！');
        	            		}
        	            	},
        	            	error:function(e){
        	            		tip("出错了哦！");
        	    			}
        	            });
    				}else{
    					return;
    				}
    	        });
    		}else {
    			tip(data);
    		}
    	},
    	error:function(e){
    		tip("出错了哦！");
		}
    });
}
    </script>
</body>
</html>
