/*[start]*/
$(document).ready(function() {
	$("#txtDbName").textbox('setValue',Util.param("dbName"));
	$("#txtObjName").textbox('setValue',Util.param("objName"));
	$("#txtObjType").textbox('setValue',Util.param("objType"));
	$("#txtObjDesc").textbox('setValue',Util.param("description"));
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
	$("#tb").link({text:'关联对象',icon:"icon-Extension-cmy",url:"DataBaseObjRel.html?dbID="+Util.param("dbID")
		+'&objID='+Util.param("objID")+'&dbName='+escape(Util.param("dbName"))
		+'&objName='+escape(Util.param("objName"))+'&objType='+escape(Util.param("objType"))
		+'&description='+escape(Util.param("description"))});
});
$.ajaxSetup({
	cache : false
		// 关闭AJAX相应的缓存
});

function operator(value, rowData, rowIndex){
	var x;
	if("0402"==(Util.param("objTypeID"))||"0403"==(Util.param("objTypeID"))){
		x=Util.rowOperator(rowIndex).link({text:'字段数据源',icon:'icon-Extension-tag_blue',
			url:'DbColSource.html?dbID='+rowData.dbID
			+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
			+'&objName='+escape(rowData.objName)+'&colID='+rowData.colID
			+'&colName='+escape(rowData.columnName)
			+'&description='+escape(rowData.description)})
			.html();
	}else{
		x=Util.rowOperator(rowIndex).html();
	}

	return x;
}

function getSrc(value,rowData,rowIndex){
	return value;
}

function forSrc(value,rowData,rowIndex){
	if(value==null){
		return;
	}
	var content = '<a href="#" title="'+value+'" class="note">'+value+'</a>';
	return content;
}

function dbFormatter(value, rowData, rowIndex) {
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

function dbColFormatter(value,rowData,rowIndex){
	return '<a id="DbColbtn' + rowIndex
	+ '" href="javascript:void(0)"  onclick="goDbColDetail('
	+ rowData.colID + ')">' + value + '</a>';
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
	$.messager.confirm('确认', '您确认要删除['+sele.dbName+']数据库中的[' + sele.objName + ']对象中的['+sele.columnName+']吗？', function(r) {
				if (r) {
					$.post("../dbcol/remove", {
								"colID" : sele.colID
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
    var url = '../dbobj/getDbObj?DbID=' + sele.dbID;
    $('#cmbobjID').combobox('reload', url);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改对象字段'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('dbID'))){
		$("#cmbdbID").combobox('select',Util.param('dbID'));
	}
	$("#editForm").form('load', {
				colID : '-1',
				columnName : '',
				objID : Util.param('objID'),
				dbID : Util.param('dbID'),
				columnTypeID : '',
				length : '',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增对象字段'
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
				url : "../dbcol/save",
				onSubmit : function() {
					if($("#txtlength").textbox("getValue")==''){
						$("#txtlength").textbox("setValue",-1);
					}
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
	var keyWords = $('#txtKeyWords').val();
	var srcDesc = $('#txtColSrc').val();
	var dbnum = "0";
	var DbValue = $('#cmbDb').combobox('getValue');
	var DbText = $('#cmbDb').combobox('getText');
	var objnum = "0";
	var ObjValue = $('#cmbObj').combobox('getValue');
	var ObjText = $('#cmbObj').combobox('getText');

	var colnum = "0";
	var ColValue = $('#cmbCol').combobox('getValue');
	var ColText = $('#cmbCol').combobox('getText');

	var typenum = "0";
	var TypeValue = $('#cmbColType').combobox('getValue');
	var TypeText = $('#cmbColType').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");
	var sDb = "";
	var sObj = "";
	var sCol = "";
	var sType = "";

	if (DbValue == -1) {
		sDb = "";
	} else {
		dbnum = "1";
		sDb = DbText;
	}

	if (ObjValue == -1) {
		sObj = "";
	} else {
		objnum = "1";
		sObj = ObjText;
	}
	if (ColValue == -1) {
		sCol = "";
	} else {
		colnum = "1";
		sCol = ColText;
	}

	if (TypeValue == -1) {
		sType = "";
	} else {
		typenum = "1";
		sType = TypeText;
	}
	$('#dg').datagrid({
				url: '../dbcol/getColList',
			queryParams:{
				"dbID":DbValue,
				"dbName" : sDb,
				"objID":ObjValue,
				"objName" : sObj,
				"columnName" : sCol,
				"columnTypeName" : sType,
				"isValid" : isValid,
				"srcDesc" : srcDesc,
				"description" : keyWords
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
		url : "../dbcol/Import",
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