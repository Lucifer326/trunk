/*
*新疆民政信息管理平台
*朱志强
*2016-12-7
*/




 /*sorting页-升序-降序*/

 window.onload=function ()
{
	var oBtn=document.getElementById('btn1');
	var oTab=document.getElementById('tab1');
	var bAsc=true;	//是否是升序排列
	
	oBtn.onclick=function ()
	{
		var arr=[];
		var i=0;
		//1.转成数组
		for(i=0;i<oTab.tBodies[0].rows.length;i++)
		{
			arr[i]=oTab.tBodies[0].rows[i];
		}
		
		//2.数组排序
		arr.sort(updown);

		function updown(tr1, tr2){
			if(bAsc)
			{
				return parseInt(tr1.cells[0].innerHTML)-parseInt(tr2.cells[0].innerHTML);
			}
			else
			{
				return parseInt(tr2.cells[0].innerHTML)-parseInt(tr1.cells[0].innerHTML);
			}
		}
		
		//3.重新插入
		for(i=0;i<arr.length;i++)
		{
			oTab.tBodies[0].appendChild(arr[i]);
		}
		
		bAsc=!bAsc;
		

	};
};


