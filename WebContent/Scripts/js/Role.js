$(document).ready(
		function() {
			loadComboboxs();
			findInfo();
			$("#tb")
			.link({text:'添加',icon:'icon-add',click:'append()'});
});
function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left')
	.link({tip:'编辑',icon:'icon-edit',click:'edit('+rowIndex+')'})
	.link({tip:'删除',icon:'icon-remove',click:'removeit('+rowIndex+')'})
	.separator()
	.link({text:'用户授权',icon:'icon-Extension-group_gear',url:'RoleUser.html?role='+Util.encode(rowData)})
	.separator()
	.link({text:'应用授权',icon:'icon-application',url:'RightApp.html?role='+Util.encode(rowData)})
	.link({text:'数据库授权',icon:'icon-Extension-databases',url:'RightDb.html?role='+Util.encode(rowData)})
	.html();
	return x;
}

function Appformatter(value, rowData, rowIndex) {
	return '<a href="javascript:void(0)"  onclick="goDetail(\'' +Util.encode(rowData)+ '\')">' + value + '</a>';
}
function goDetail(id) {
	window
			.open(
					'RoleDetail.html?role=' + id,
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
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
			$.post("../rights/role/remove", {
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

function edit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$("#editForm").form('load', sele);
	$('#edit').window({
		title : '修改'
	}).window('open');
}

function append() {
	$("#editForm").form('load', {
		id : '-1',
		name : '',
		description : ''
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
		url : "../rights/role/save",
		onSubmit : function() {
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
				url: '../rights/role/getList',
					queryParams:{
		"name" : sName,
		"description":sDesc
	}});
}

function loadComboboxs() {
	$.ajax({
		url : '../rights/role/getRole',
		type : 'post',
		success : function(data) {
			$("#cmbName").combobox("loadData", data);
			$("#cmbDescription").combobox("loadData", data);
		}
	});
}