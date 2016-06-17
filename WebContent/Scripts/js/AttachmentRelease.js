$(document).ready(
function() {
	$("#tb").link({text:'下载所选文档',icon:"icon-Extension-attach",click:"downAll()",onlyRow:true});
	findInfo();
});

function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left')
	.link({text:'下载',icon:'icon-Extension-download',click:"down("+rowIndex+")"});
	return x.html();
}
function release(value,rowData,rowIndex){
	return new Date(value).format("yyyy年M月d日 HH:mm:ss");
}

function down(index){
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	var form=$("<form id='subForm'>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action","../AttachmentRelease/Download");
	$("body").append(form);//将表单放置在web中
	form.append($("<input>").attr("name","uuid").attr("value",sele.uuid).attr("type","hidden"));
	form.submit();
	$("#subForm").html("");
}

function downAll(){
	var form=$("<form id='subForm'>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action","../AttachmentRelease/Download");
	$("body").append(form);//将表单放置在web中
	var lines=$("#dg").datalist("getSelections");
	if (lines.length <= 0) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$.each(lines,function(i,item){
		form.append($("<input>").attr("name","uuid").attr("value",item.uuid).attr("type","hidden"));
	});
	form.submit();
	$("#subForm").html("");
}
function findInfo() {
	var attachment=Util.paramObj("attachment");
	$('#dg').datagrid({url: '../AttachmentRelease/list/'+attachment.uuid});
}