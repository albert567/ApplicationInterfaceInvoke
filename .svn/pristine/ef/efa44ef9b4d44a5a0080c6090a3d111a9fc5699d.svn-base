/*[start] */
$(document).ready(function() {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtInterName").textbox('setValue',Util.param("interName"));
	$("#txtInterType").textbox('setValue',Util.param("interTypeName"));
	$("#txtInterDesc").textbox('setValue',Util.param("description"));

	var themecombo1 = [{
		'IsValid': '1',
		'Text': '有效'
	}, {
		'IsValid': '0',
		'Text': '无效'
	}];
	$("#cmbIsValid").combobox("loadData", themecombo1);
	$("#cmbIsValid2").combobox("loadData", themecombo1);
});

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).link({text:'参数',url:'ProcedureParam.html?dbID='+rowData.dbID
		+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
		+'&objName='+escape(rowData.objName)+'&objType='+escape(rowData.objTypeName)
		+'&description='+escape(rowData.objDesc)})
	.html();
	return x;
}
function dbFormatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.dbID
			+ ')">' + value + '</a>';
}
function goDbDetail(dbID) {
	window.open(
					'DbDetail.html?DbID=' + dbID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
}
function dbObjFormatter(value, rowData, rowIndex) {
	return '<a id="DbObjbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbObjDetail('
			+ rowData.objID + ')">' + value + '</a>';
}
function goDbObjDetail(objID) {
	window
			.open(
					'DbObjDetail.html?ObjID=' + objID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
/*[end]*/
function findInfo() {
    var DbText = $('#cmbDb').combobox('getText');
    var ObjText = $('#cmbObj').combobox('getText');

    $('#dg').datagrid({
				url: '../interProc/getDbObjList',
    queryParams:{
    	"interID":Util.param('interID'),
    	"dbName":DbText,
    	"objName":ObjText
    }});
}
function getDbObj() {
	$('#cmbObj').combobox("clear")
	$('#cmbObj').combobox('loadData', []);
	var url = '../dbobj/getProcedure?DbID='
			+ $('#cmbDb').combobox('getValue');
	$('#cmbObj').combobox('reload', url);
}
function getEditDbObj() {
	$('#cmbobjID').combobox("clear")
	$('#cmbobjID').combobox('loadData', []);
	var url = '../dbobj/getProcedure?DbID='
			+ $('#cmbdbID').combobox('getValue');
	$('#cmbobjID').combobox('reload', url);
}

/*[start] 增删改*/

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

function edit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}

	$('#cmbobjID').combobox("clear")
    $('#cmbobjID').combobox('loadData', []);
    var url = '../dbobj/getDbObj?DbID=' + sele.dbID;
    $('#cmbobjID').combobox('reload', url);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改数据库接口'
			}).window('open');
}

function append() {
	$("#editForm").form('load', {
				interID:Util.param('interID'),
				dbID:'',
				objID:'',
				isValid : '1'
			});
	$('#edit').window({
				title : '新增数据库接口'
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
				url : "../interProc/save",
				onSubmit : function() {
					validCbox(["#cmbdbID","#cmbobjID"]);
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

function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除数据库接口['+sele.objName+']吗？', function(r) {
				if (r) {
					$.post("../interProc/remove", {
								"interProID" : sele.id
							}, function(msg) {
								$.messager.alert("提示", msg.message);
								if (msg.success) {
									findInfo();
								}
							}, "json");
				}
			});
}
/*[end]*/
/*[start] 导入*/
function imp(){
	$('#imp').window('open');
}

function cancelImp() {
	$('#imp').window('close');
}

function subImp() {
	$('#impForm').form('submit', {
		url : "../interProc/Import",
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