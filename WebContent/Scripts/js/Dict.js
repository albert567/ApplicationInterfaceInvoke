$(document).ready(
		function() {
			var themecombo1 = [ {
				'IsValid' : '1',
				'Text' : '有效'
			}, {
				'IsValid' : '0',
				Text : '无效'
			} ];
			$("#cmbIsValid").combobox("loadData", themecombo1);
			$("#cmbIsValid2").combobox("loadData", themecombo1).combobox(
					"setValue", '1');
			findInfo();
		});
$.ajaxSetup({
	cache : false
// 关闭AJAX相应的缓存
});
function removeit(sele) {
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	if(sele.isValid==-1){
		$.messager.alert("提示", "此行数据不能操作!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除[' + sele.dvalue + ']吗？', function(r) {
		if (r) {
			$.post("../Dict/remove", sele, function(msg) {
				$.messager.alert("提示", msg.message);
				if (msg.success) {
					findInfo();
				}
			}, "json");
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
		url : "../Dict/Import",
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


function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left');
	if(rowData.dtype==0){
		x.link({tip:'新增',icon:'icon-Extension-page_add',click:'append('+rowData.dkey+')'});
	}else{
		x.link({tip:'编辑',icon:'icon-Extension-page_edit',click:'edit('+JSON.stringify(rowData).replace(/"/g,"'")+')'})
		.link({tip:'删除',icon:'icon-Extension-Empty',click:'removeit('+JSON.stringify(rowData).replace(/"/g,"'")+')'});

	}
	return x.html();
}



/*[start] 编辑*/
function edit(sele) {
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	if(sele.isValid==-1){
		$.messager.alert("提示", "此行数据不能操作!");
		return;
	}
	$("#editForm").form('load', sele);
	$('#edit').window({
		title : '修改数据字典'
	}).window('open');
}

function append(dtype) {
	$("#editForm").form('load', {
		id : '-1',
		dtype : dtype,
		dvalue : '',
		isValid : '1',
		description : ''
	});
	$('#edit').window({
		title : '新增数据字典'
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
		url : "../Dict/save",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = validResp(data);
			if (data.success) {
				$('#edit').window('close');
				findInfo();
			}
		}
	});
}
/*[end]*/
function findInfo() {
	$('#findForm').form('submit', {
		url : "../Dict/GetList",
		success : function(data) {
			$('#dg').treegrid('loadData',validResp(data));
		}
	});
}

function forIsValid(val, row, index) {
	var ret = "";
	if (row.isValid == "1") {
		ret = '有效';
		return ret;
	} else if (row.isValid == "0") {
		ret = '无效';
		return ret;
	} else {
		return '';
	}
}