/* [start] */
$(document).ready(function () {
	var appID = Util.param('appID');
	var funcID = Util.param('funcID');
	/*$("body").toggleLoading({msg:"正在加载数据,请稍等..."});
	$.post('../funcCode/GetList?appID='+appID+'&funcID='+funcID+'&id=0'
		,function(resp){
		$("body").toggleLoading();
		$('#tree').tree({data:resp});
	},"json");*/
	$('#tree').tree({
		url:'../funcCode/GetList?appID='+appID+'&funcID='+funcID+'&id=0',
		onBeforeExpand:function(node){
			$('#tree').tree("options").url = '../funcCode/GetList?appID='+appID+'&funcID='+funcID+'&id='+node.id;
			return true;
		}
	});
});
function save(){
	var nodes = $('#tree').tree('getChecked');
	var s = [];
	for(var i=0;i<nodes.length;i++){
		s.push(nodes[i].id);
	}
	$.post("../funcCode/save",{
		"funcID":Util.param('funcID'),
		"nodes":s.join()
	},function(resp){
		if(resp.success){
			window.opener.location.reload();
			window.close();
		}
		$.messager.alert("提示", resp.message);
	},"json");
}
