$(function(){
	var type="新增";
	 var id=Util.param("method");
	 if(!Util.empty(id)){
	 	type="编辑";

	 }
});
function getEditInterface(){
	
}


function edit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}

	$('#cmbInterface2').combobox("clear")
    $('#cmbInterface2').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + sele.appID;
    $('#cmbInterface2').combobox('reload', url);
	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改接口方法'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('appId'))){
		$("#cmbApp2").combobox('select',Util.param('appId'));
	}
	$("#editForm").form('load', {
				methodID : '-1',
				methodName: '',
				appID: Util.param('appId'),
				interID: Util.param('interId'),
				methodType:'',
				rw: '1',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增接口方法'
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
				url : "../interMethod/save",
				onSubmit : function() {
					validCbox(["#cmbApp2","#cmbInterface2"]);
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