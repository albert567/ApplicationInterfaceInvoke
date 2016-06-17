/* [start] */
$(document).ready(function () {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtAppType").textbox('setValue',Util.param("appTypeName"));
	$("#txtAppDesc").textbox('setValue',Util.param("description"));
});
function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex)
	.link({text:'数据库对象',icon:'icon-Extension-database',url:'DataBaseObj.html?dbID='+rowData.dbID+'&dbName='+escape(rowData.dbName)
		+'&dbEn='+escape(rowData.dbNameEn)+'&description='+escape(rowData.dbDes)})
	.html();
	return x;
}

function Dbformatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.dbID
			+ ')">' + value + '</a>';
}
function goDbDetail(DbID) {
	window.open(
					'DbDetail.html?DbID=' + DbID + '',
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
	$.messager.confirm('确认', '您确认要删除[' + sele.dbName + ']吗？', function(r) {
		if (r) {
			$.post("../appDb/remove", {
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
	$('#editAppDb').window({
		title : '修改数据库关联'
	}).window('open');
}

function append() {
	$("#editForm").form('load', {
		id : '-1',
		appID : Util.param("appId"),
		dbID : '',
		description : ''
	});
	$('#editAppDb').window({
		title : '新增数据库关联'
	}).window('open');
}

function cancelEdit() {
	$.messager.confirm('确认', '您确认要取消当前编辑吗？', function(r) {
		if (r) {
			$('#editAppDb').window('close');
		}
	});
}
function submitEdit() {
	$('#editForm').form('submit', {
		url : "../appDb/save",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			$.messager.alert("提示", data.message);
			if (data.success) {
				$('#editAppDb').window('close');
				findInfo();
			}
		}
	});
}

function findInfo() {
	var keyWords = $('#txtKeyWords').val();
	var AppValue = $('#cmbApp').combobox('getValue');
	var DbText = $('#cmbDb').combobox('getText');
	$('#dg').datagrid({
		url: '../appDb/GetList',
		queryParams:{
			"appID" : AppValue,
			"dbName" : DbText,
			"description" : keyWords
		}
	});
}

/*[start] 导入*/
function imp(){
	$('#imp').window('open');
}

function cancelImp() {
	$('#imp').window('close');
}

function subImp() {
	$('#impForm').form('submit', {
		url : "../appDb/Import",
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
				afterImport(data.uuid);
				//$.messager.alert("提示", "导入成功!共导入了 " + data.total + "条数据!");
				$('#imp').window('close');
				findInfo();
			} else {
				var msg = "";
				$.each(data.errMsg, function(i, m) {
							msg += m + "<br/>";
						});

				$("#msgDialog").html(msg);
				var msg = '导入失败!<br/><a id="errMsg" class="easyui-linkbutton" href="javascript:void(0)">>>查看详情</a>';

				$.messager.alert("提示", msg);
				$('#msgDialog').dialog({
							title : '导入错误日志',
							width : 400,
							height : 400,
							closed : true,
							modal : true
						});
				$("#errMsg").click(function() {
							$('#msgDialog').dialog("open");
						});

			}
		},
		error:function(){
			$("body").toggleLoading();
			$.messager.alert("提示", "操作失败!");
		}
	});
}
/*[end] 导入数据*/