var _from=null;
var _attachment=-1;
$(document).ready(
function() {
	_from=Util.options({"type":'function',"id":1,"appID":1,"p":{"i":false,"r":false}},Util.paramObj("from"));
	var type=_from.type;
	$('#cmbType').combobox("clear");
	var url = '../Attachment/getDocType/'+ type;
	$('#cmbType').combobox('reload', url);
	
	if(_from.p.a){$("#tb").link({text:'添加文档',icon:"icon-Extension-attach",click:"upload()",onlyRow:false});}
	$("#tb").link({text:'下载所选文档',icon:"icon-Extension-attach",click:"downAll()",onlyRow:true});
});

function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left')
	.link({text:'下载',icon:'icon-Extension-download',click:"down("+rowIndex+")"})
	.link({text:'修订',icon:'icon-Extension-tag_blue_add',click:"release("+rowIndex+")"})
	.link({text:'历史',icon:'icon-Extension-time_green',click:"releaseHis("+rowIndex+")"});
	//if(_from.p.d){x.link({text:'删除',icon:'icon-remove',click:'rm('+rowIndex+')'})}
	return x.html();
}
function release(index){
	 $("#dg").datagrid("selectRow",index);
	 var sele = $("#dg").datagrid("getSelected");
	 _attachment=sele.uuid;
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$("#cmbDocType").combobox('setValue',sele.docType).combobox('readonly',true);
	$('#upload').window('open');
}

function releaseHis(index){
	 $("#dg").datagrid("selectRow",index);
	 var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	Util.openUrl({url:"./AttachmentRelease.html?attachment="+Util.encode(sele),width:800,height:600});
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
	form.attr("action","../Attachment/Download");
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
	form.attr("action","../Attachment/zip");
	$("body").append(form);//将表单放置在web中
	var lines=$("#dg").datalist("getSelections");
	if (lines.length <= 0) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$.each(lines,function(i,item){
		form.append($("<input>").attr("name","name").attr("value",item.uuid).attr("type","hidden"));
	});
	form.submit();
	$("#subForm").html("");
}
/*
function rm(index){
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除[' + sele.path + ']吗？', function(r) {
		if (r) {
			$.post("../Attachment/remove",sele,function(resp){
				$.messager.alert("提示", resp.message);
				if(resp.success){
					findInfo();
				}
			},"json");
		}
	});


}
*/
function findInfo() {
	var docName = $('#docName').val();
	var vals = $('#cmbType').combobox('getValues');
	$('#dg').datagrid({url: '../Attachment/list',
			queryParams:{
				"type" : _from.type,
				"appID" : _from.appID,
				"id" : _from.id,
				"docType" : vals.join(','),
				"docName" : docName
			}});
}


/*[start] 导入*/
function upload(){
	_attachment=-1;
	$("#cmbDocType").combobox('setValue','').combobox('readonly',false);
	$('#upload').window('open');
}

function cancelUpload() {
	$('#upload').window('close');
}

function subUpload() {
	$('#impForm').form('submit', {
		url : "../Attachment/Upload/"+_from.type+"/"+_from.id+"/"+_attachment,
		onSubmit : function() {
			var x=$(this).form('enableValidation').form('validate');
			if(x){
				$("body").toggleLoading({msg:"上传中,请稍等..."});
			}
			return x;
		},
		success : function(data) {
			$("body").toggleLoading();
			var data = validResp(data);
			if (data.success) {
				//$.messager.alert("提示",data.msssage);
				$('#upload').window('close');
				findInfo();
				window.opener.location.reload();
			}
		},
		error:function(){
			$("body").toggleLoading();
			$.messager.alert("提示", "操作失败!");
		}
	});
}
/*[end] 导入数据*/