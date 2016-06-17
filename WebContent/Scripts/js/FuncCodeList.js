/* [start] */
$(document).ready(function () {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtFuncName").textbox('setValue',Util.param("funcName"));
	$("#txtFuncDesc").textbox('setValue',Util.param("description"));
	$("#tb").link({text:'添加源码',icon:"icon-Extension-page_code",
		url:"AppFuncCode.html?appID="+Util.param("appID")+"&funcID="+Util.param('funcID'),height:600,width:520});
	findInfo();
});
function afterLoad(p){
	$("#tb").children().last().remove();
	$("#tb").children().last().remove();
}
function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex);
	x.find("a[title='编辑']").remove();
	return x.html();
}

function edit(index) {
	Util.openUrl({url:"./AppFuncCode.html?appID="+Util.param("appID")+"&funcID="+Util.param('funcID')});
}
function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除[' + sele.codeName + ']吗？', function(r) {
		if (r) {
			$.post("../funcCode/remove", {
				"id" : sele.id
			}, function(msg) {
				$.messager.alert("提示", msg.message);
				if (msg.success) {
					findInfo();
				}
			}, "json");
		}
	});
}

function findInfo() {
	var keyWords = $('#txtKeyWords').val();
	$('#dg').datagrid({
		url: '../funcCode/GetCodeList',
		queryParams:{
			"funcID" : Util.param('funcID'),
			"codeName" : keyWords
		}
	});
}
