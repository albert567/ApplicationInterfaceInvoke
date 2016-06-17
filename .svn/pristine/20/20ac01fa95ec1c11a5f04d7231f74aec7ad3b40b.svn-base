/*[start]*/
$(document).ready(function() {
	$("#txtDbName").textbox('setValue',Util.param("dbName"));
	$("#txtObjName").textbox('setValue',Util.param("objName"));
	$("#txtObjType").textbox('setValue',Util.param("objType"));
	$("#txtObjDesc").textbox('setValue',Util.param("description"));

	$('#cmbObj').combobox("clear")
    $('#cmbObj').combobox('loadData', []);
    var url = '../objrel/getDbObj?objID=' + Util.param("objID");
    $('#cmbObj').combobox('reload', url);

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

function Dbformatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.childDbID
			+ ')">' + value + '</a>';
}
function goDbDetail(DbID) {
	window.open(
					'DbDetail.html?DbID=' + DbID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
}
function DbObjformatter(value, rowData, rowIndex) {
	return '<a id="DbObjbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbObjDetail('
			+ rowData.childID + ')">' + value + '</a>';
}
function goDbObjDetail(objID) {
	window.open(
					'DbObjDetail.html?ObjID=' + objID + '',
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
	$.messager.confirm('确认', '您确认要删除关联['+sele.childDbName+']数据库中的[' + sele.childName + ']对象吗？', function(r) {
				if (r) {
					$.post("../objrel/remove", {
								"relID" : sele.id
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
    var url = '../dbobj/getDbObj?DbID=' + sele.childDbID;
    $('#cmbobjID').combobox('reload', url);
    sele.parentName=Util.param('objName');

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改关联对象'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('dbID'))){
		$("#cmbdbID").combobox('select',Util.param('dbID'));
	}
	$("#editForm").form('load', {
				parentID:Util.param('objID'),
				parentName:Util.param('objName'),
				childDbID:'',
				childID:'',
				isValid : '1'
			});
	$('#edit').window({
				title : '新增关联对象'
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
				url : "../objrel/save",
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
function findInfo() {
	var ObjText = $('#cmbObj').combobox('getText');
	var ChildTypeText = $('#cmbObjType').combobox('getText');

	var isValid = $("#cmbIsValid").combobox("getValue");
	$('#dg').datagrid({
				url: '../objrel/getObjList',
			queryParams:{
				"parentID": Util.param("objID"),
				"childName" : ObjText,
				"childTypeName":ChildTypeText,
				"isValid" : isValid
			}
	});
}


function getEditDbObj() {
	$('#cmbobjID').combobox("clear")
	$('#cmbobjID').combobox('loadData', []);
	var url = '../dbobj/getDbObj?DbID='
			+ $('#cmbdbID').combobox('getValue');
	$('#cmbobjID').combobox('reload', url);
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
		url : "../objrel/Import",
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