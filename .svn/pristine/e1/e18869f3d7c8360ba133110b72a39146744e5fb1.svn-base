/*[start]*/
$(document).ready(function() {
	$("#txtDbName").textbox('setValue',Util.param("dbName"));
	$("#txtObjName").textbox('setValue',Util.param("objName"));
	$("#txtColName").textbox('setValue',Util.param("colName"));
	$("#txtColDesc").textbox('setValue',Util.param("description"));
	var themecombo1 = [{
						'IsValid' : '1',
						'Text' : '有效'
					}, {
						'IsValid' : '0',
						Text : '无效'
					}];
	$("#cmbIsValid").combobox("loadData", themecombo1).combobox(
					"setValue", '1');
	$("#cmbIsValid2").combobox("loadData", themecombo1).combobox(
					"setValue", '1');
});
$.ajaxSetup({
	cache : false
		// 关闭AJAX相应的缓存
});

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).html();
	return x;
}

function dbFormatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.srcDbID
			+ ')">' + value + '</a>';
}
function goDbDetail(DbID) {
	window.open(
					'DbDetail.html?DbID=' + DbID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
}
function dbObjFormatter(value, rowData, rowIndex) {
	return '<a id="DbObjbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbObjDetail('
			+ rowData.srcObjID + ')">' + value + '</a>';
}
function goDbObjDetail(objID) {
	window
			.open(
					'DbObjDetail.html?ObjID=' + objID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function dbColFormatter(value,rowData,rowIndex){
	return '<a id="DbColbtn' + rowIndex
	+ '" href="javascript:void(0)"  onclick="goDbColDetail('
	+ rowData.srcID + ')">' + value + '</a>';
}

function goDbColDetail(colID) {
	window
			.open(
					'DbColDetail.html?colID=' + colID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}

/*[end]*/
function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除源数据['+sele.srcDbName+']数据库中的[' + sele.srcObjName + ']对象中的['+sele.srcName+']吗？', function(r) {
				if (r) {
					$.post("../colsrc/remove", {
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

	$('#cmbobjID').combobox("clear")
    $('#cmbobjID').combobox('loadData', []);
    var url = '../dbobj/getDbObj?DbID=' + sele.srcDbID;
    $('#cmbobjID').combobox('reload', url);

    $('#cmbsrcID').combobox("clear")
    $('#cmbsrcID').combobox('loadData', []);
    var url = '../dbcol/getDbCol?ObjID=' + sele.srcObjID;
    $('#cmbsrcID').combobox('reload', url);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改字段源数据'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('dbID'))){
		$("#cmbdbID").combobox('select',Util.param('dbID'));
	}
	$("#editForm").form('load', {
				colID:Util.param("colID"),
				srcDbID : '',
				srcObjID : '',
				srcID : '',
				isValid : '1'
			});
	$('#edit').window({
				title : '新增字段源数据'
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
				url : "../colsrc/save",
				onSubmit : function() {
					validCbox(["#cmbdbID","#cmbobjID","#cmbsrcID","#cmbIsValid2"]);
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
	var DbText = $('#cmbDb').combobox('getText');
	var ObjText = $('#cmbObj').combobox('getText');
	var ColText = $('#cmbCol').combobox('getText');
	var TypeText = $('#cmbColType').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");

	$('#dg').datagrid({
				url: '../colsrc/getColSrcList',
			queryParams:{
				"colID":Util.param('colID'),
				"srcDbName" : DbText,
				"srcObjName": ObjText,
				"srcName" : ColText,
				"srcTypeName" : TypeText,
				"isValid" : isValid
			}
	});
}

function getDbObj() {
	$('#cmbObj').combobox("clear")
	$('#cmbObj').combobox('loadData', []);
	var url = '../dbobj/getDbObj?DbID='
			+ $('#cmbDb').combobox('getValue');
	$('#cmbObj').combobox('reload', url);
}

function getEditDbObj() {
	$('#cmbobjID').combobox("clear")
	$('#cmbobjID').combobox('loadData', []);
	var url = '../dbobj/getDbObj?DbID='
			+ $('#cmbdbID').combobox('getValue');
	$('#cmbobjID').combobox('reload', url);
}

function getEditObjCol(){
	$('#cmbsrcID').combobox("clear")
	$('#cmbsrcID').combobox('loadData', []);
	var url = '../dbcol/getDbCol?ObjID='
			+ $('#cmbobjID').combobox('getValue');
	$('#cmbsrcID').combobox('reload', url);
}

function getObjCol() {
	$('#cmbCol').combobox("clear")
	$('#cmbCol').combobox('loadData', []);
	var url = '../dbcol/getDbCol?ObjID='
			+ $('#cmbObj').combobox('getValue');
	$('#cmbCol').combobox('reload', url);
}


function forIsValid(val, row, index) {
	var ret = "";
	if (row.isValid == "0") {
		ret = '无效';
		return ret;
	} else if (row.isValid == "1") {
		ret = '有效';
		return ret;
	} else {
		return val;
	}
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
		url : "../colsrc/Import",
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
				var msgDiv = "<div style='display:none'><div id='msgDialog' >"
						+ msg + "</div></div>";
				var msg = '导入失败!<br/><a id="errMsg" class="easyui-linkbutton" href="javascript:void(0)">>>查看详情</a>'
						+ msgDiv;

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