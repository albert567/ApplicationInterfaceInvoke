/* [start] */
$(document).ready(function () {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtFuncName").textbox('setValue',Util.param("funcName"));
	$("#txtFuncDesc").textbox('setValue',Util.param("description"));
	findInfo();
});
function afterLoad(p){
	var id=Util.param("funcID");
	var count=0;
	$("#tb").link({text:'源代码',icon:'icon-Extension-page_code',
		url:'FuncCodeList.html?appID='+Util.param("appID")
		+'&appName='+escape(Util.param("appName"))
		+'&funcID='+Util.param("funcID")
		+'&funcName='+escape(Util.param("funcName"))
		+'&description='+escape(Util.param("description"))});
	
	$.get("../Attachment/count/function/"+id,function(resp){
		count=resp.count;
		$("#tb").link({text:'文档('+count+')',icon:"icon-Extension-attach",
						url:"./Attachment.html?from="
					    + Util.encode({"type":"function","id":id,"p":JSON.parse(Base64.decode(p))}),onlyRow:false,height:600,width:600});
	},"json");
}
function operator(value, rowData, rowIndex){
	var x;
	if(rowData.objTypeID=='0401'||rowData.objTypeID=='0404'){
		x=Util.rowOperator(rowIndex).link({text:'参数',icon:'icon-Extension-tag',url:'ProcedureParam.html?dbID='+rowData.dbID
			+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
			+'&objName='+escape(rowData.objName)+'&objType='+escape(rowData.objTypeName)
			+'&description='+escape(rowData.objDesc)})
			.link({text:'关联对象',icon:'icon-Extension-cmy',url:'DataBaseObjRel.html?dbID='+rowData.dbID
			+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
			+'&objName='+escape(rowData.objName)+'&objType='+escape(rowData.objTypeName)
			+'&description='+escape(rowData.objDesc)})
			.html();
	}else{
		var x=Util.rowOperator(rowIndex).link({text:'字段',icon:'icon-Extension-tag_blue',url:'DataBaseObjCol.html?dbID='+rowData.dbID
			+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
			+'&objName='+escape(rowData.objName)+'&objType='+escape(rowData.objTypeName)
			+'&objTypeID='+rowData.objTypeID
			+'&description='+escape(rowData.objDesc)})
			.link({text:'关联对象',icon:'icon-Extension-cmy',url:'DataBaseObjRel.html?dbID='+rowData.dbID
			+'&objID='+rowData.objID+'&dbName='+escape(rowData.dbName)
			+'&objName='+escape(rowData.objName)+'&objType='+escape(rowData.objTypeName)
			+'&description='+escape(rowData.objDesc)})
			.html();
	}
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
			+ rowData.objID + ',\''+rowData.objTypeID+'\')">' + value + '</a>';
}
function goDbObjDetail(objID,typeId) {
	var url;
	if(typeId=='0401'||typeId=='0404'){
		url='ProcObjDetail.html?ObjID=' + objID;
	}else{
		url='DbObjDetail.html?ObjID=' + objID;
	}
	window
			.open(
					 url,
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}

function getObjList(){
	$('#cmbObj').combobox('clear');
	var url = '../dbobj/getDbObj?DbID='+$('#cmbDb').combobox('getValue');
	$('#cmbObj').combobox('reload',url);
}

function getEditObjList(){
	$('#cmbobjID').combobox('clear');
	var url = '../dbobj/getDbObj?DbID='+$('#cmbdbID').combobox('getValue');
	$('#cmbobjID').combobox('reload',url);
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
			$.post("../funcObj/remove", {
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

	$('#cmbobjID').combobox('clear');
	var url = '../dbobj/getDbObj?DbID='+sele.dbID;
	$('#cmbobjID').combobox('reload',url);

	$("#editForm").form('load', sele);
	$('#editFuncObj').window({
		title : '修改对象关联'
	}).window('open');
}

function append() {
	$("#editForm").form('load', {
		id : '-1',
		funcID : Util.param("funcId"),
		dbID : '',
		objID : ''
	});
	$('#editFuncObj').window({
		title : '新增对象关联'
	}).window('open');
}

function cancelEdit() {
	$.messager.confirm('确认', '您确认要取消当前编辑吗？', function(r) {
		if (r) {
			$('#editFuncObj').window('close');
		}
	});
}
function submitEdit() {
	$('#editForm').form('submit', {
		url : "../funcObj/save",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			$.messager.alert("提示", data.message);
			if (data.success) {
				$('#editFuncObj').window('close');
				findInfo();
			}
		}
	});
}

function findInfo() {
	var keyWords = $('#txtKeyWords').val();
	var dbText = $('#cmbDb').combobox('getText');
	var objText = $('#cmbObj').combobox('getText');
	$('#dg').datagrid({
		url: '../funcObj/GetList',
		queryParams:{
			"funcID" : Util.param("funcId"),
			"dbName" : dbText,
			"objName" : objText,
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
		url : "../funcObj/Import",
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