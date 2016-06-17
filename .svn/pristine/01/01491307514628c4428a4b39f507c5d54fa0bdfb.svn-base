Date.prototype.format=function(format){
	var o={
		"M+":this.getMonth()+1,//month
		"d+":this.getDate(),//day
		"h+":this.getHours(),//hour
		"m+":this.getMinutes(),//minute
		"s+":this.getSeconds(),//second
		"q+":Math.floor((this.getMonth()+3)/3),//quarter
		"S":this.getMilliseconds()//millisecond
	}
	if(/(y+)/.test(format))
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+k+")").test(format))
	format=format.replace(RegExp.$1,RegExp.$1.length==1?o[k]:("00"+o[k]).substr((""+o[k]).length));
	return format;
}
function setTime(){
	$("#startTime").datetimebox("setValue",new Date().format("yyyy-MM-dd 0:0"));
	$("#endTime").datetimebox("setValue",new Date().format("yyyy-MM-dd 24:00"));
}
$(function(){
	setTime();
});
function loadTitle(){
	$.get("../log/list/title/"+$("#type").combobox("getValue"),function(title){
			var ctitle=[];
			ctitle.push({"title":"日志时间","width":150,"field":"logTime","formatter":function(value,row,index){
				var v=new Date()
				v.setTime(value);
				return v.format("yy年MM月dd日 hh:mm:ss");
			}});
			ctitle.push({"title":"操作类型","width":60,"field":"dataOperation"});
			ctitle.push({"title":"操作人","width":60,"field":"userName"});
			ctitle.push({"title":"数据唯一标识","width":280,"field":"dataUUID","formatter":function(value){
				return value;
				//return "<a href='javascript:void(0)' onclick='Util.openUrl(\"../DataHis.html?data="+value+"\")'>"+value+"</a>";
			}});
			$.each(title,function(i,item){
				//item.hidden=false;
				if(item.formatter!=null){
					eval("var "+item.field+"="+item.formatter);
					item.formatter=eval(item.field);
				}
				ctitle.push(Util.options({"title":undefined,"field":undefined,"hidden":undefined,"checkbox":undefined,"formatter":undefined},item,true));
			});
			//ctitle.push({"title":"操作数据","field":"logMsg"});
			$("#dg").html("").datagrid({
						singleSelect:true,
	    				height:(window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight)-150,
						rownumbers:true,
						url:"../log/list",
						queryParams:formData("#search"),
					    pagination:true,
		               	pageSize : 12,
		                pageList: [12, 24, 36],
					    columns:[ctitle]
					});
		});
}
function findInfo(){
	$("#dg").datagrid("load",formData("#search"));
}
function formData(selector){
	var data={};
	$(selector+" input").each(function(item,i){
		if(typeof $(this).attr("name")!="undefined"){
			if(typeof data[$(this).attr("name")]=="undefined"){
				data[$(this).attr("name")]=$(this).val();
			}else{
				data[$(this).attr("name")]=data[$(this).attr("name")]+","+$(this).val();
			}
		}
	});
	return data;
}