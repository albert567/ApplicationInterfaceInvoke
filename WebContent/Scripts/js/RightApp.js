var role=Util.paramObj("role");
$(document).ready(
		function() {
			$("#roleid").textbox("setValue",role.id);
			$("title").html("["+role.name+"]应用权限");
			loadComboboxs();
			findInfo();
			$("#tb")
			.link({text:'添加',icon:'icon-add',click:'append()'});

});
function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left')
	//.link({tip:'编辑',icon:'icon-edit',click:'edit('+rowIndex+')'})
	.link({tip:'删除',icon:'icon-remove',click:'removeit('+rowIndex+')'})
	.html();
	return x;
}

function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除[' + sele.name + ']吗？', function(r) {
		if (r) {
			$.post("../rights/app/remove", {
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

function loadApp(){
	$('#dataId').combobox("clear")
    $('#dataId').combobox('loadData', []);
    var url = '../rights/app/getName?roleId=' + role.id;
    $('#dataId').combobox('reload', url);
}

function edit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	//loadApp();
	$("#editForm").form('load', sele);
	$('#edit').window({
		title : '修改'
	}).window('open');
}

function append() {
	loadApp();
	$("#editForm").form('load', {
		id : '-1',
		roleId:role.id,
		dataId : ''
	});
	$('#edit').window({
		title : '新增'
	}).window('open');
}

function cancelEdit() {
	$.messager.confirm('确认', '您确认要取消当前编辑吗？', function(r) {
		if (r) {
			$('#edit').window('close');
		}
	});
}
function submitEdit() {
	$('#editForm').form('submit', {
		url : "../rights/app/save",
		onSubmit : function() {

			validCbox("#dataId");
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			$.messager.alert("提示", data.message);
			if (data.success) {
				$('#edit').window('close');
				findInfo();
			}
		}
	});
}

function findInfo() {
	var appnum = "0";
	var name = $('#cmbName').combobox('getValue');
	var nameText = $('#cmbName').combobox('getText');
	var desc = $("#cmbDescription").combobox("getValue");
	var descText = $('#cmbDescription').combobox('getText');
	var sName = "";
	if (name == -1) {
		sName = "";
	} else {
		sName = nameText;
	}
	var sDesc="";
	if (desc == -1) {
		sDesc = "";
	} else {
		sDesc = descText;
	}
	$('#dg').datagrid({
				url: '../rights/app/getList',
					queryParams:{
						"roleId":role.id,
		"name" : sName,
		"description":sDesc
	}});
}

function loadComboboxs() {
	$.ajax({
		url : '../rights/app/getAll?roleId='+role.id,
		type : 'post',
		success : function(data) {
			$("#cmbName").combobox("loadData", data);
			$("#cmbDescription").combobox("loadData", data);
		}
	});
}