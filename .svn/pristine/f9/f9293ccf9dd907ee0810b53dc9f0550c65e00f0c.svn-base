var from=Util.param("from");
	if(from==null){
		from="d248a966-888a-42d9-8e4d-cdbf725ff327";
	}
$(function(){

	$("#roll").on("click",function(){
		$.messager.confirm("提示","确认要撤销本次导入吗?<br/>本次导入的数据将会被清空!",function(ok){
			if (ok) {
			$("body").toggleLoading({msg:"正在撤销本次导入的数据,请稍等..."});
			$.post("../log/rollback", {
				"uuid" : from
			}, function(msg) {
				$("body").toggleLoading();
				$.messager.alert("提示", msg.message);
				if (msg.success) {
					window.opener.location.reload();
					window.close();
				}
			}, "json");
		}
		});
	});



	loadTabs(from);
	/*标签页*/
	function loadTabs(from){
		$.get("../log/list/tabs/"+from,function(data){
			$("#tabs").tabs({
	    		border:false,
	    		height:(window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight)-100
			});
			$.each(data,function(i,item){
				$("#tabs").tabs("add",{
					id:item.id,
					title:item.name,
					content:"<div id='tb_"+item.id+"'>"
				});
			});
			$.each($("#tabs").tabs("tabs"),function(i,item){
				loadTitle($(this).attr("id"));
			});
		});
	}
	/*表格标题*/
	function loadTitle(table){
		$.get("../log/list/title/"+table,function(data){
			$.each(data,function(i,item){
				//item.hidden=false;
				if(item.formatter!=null){
					eval("var "+item.field+"="+item.formatter);
					item.formatter=eval(item.field);
				}
				data[i]=Util.options({"title":undefined,"field":undefined,"hidden":undefined,"checkbox":undefined,"formatter":undefined},item,true);
			});
			$("#tb_"+table).datagrid({
			    url:"../log/list/u/"+from+"/"+table,
			    pagination:true,
               	pageSize : 12,
                pageList: [12, 24, 36],
			    columns:[data]
			});
		});
	}
});
