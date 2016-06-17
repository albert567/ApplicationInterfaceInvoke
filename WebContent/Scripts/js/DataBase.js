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
			/*$("#tb").link({text:'数据库对象',url:"DataBaseObj.html?dbID={id}"})
			.link({text:'数据展示',url:"DbDataDisplay.html?dbID={id}",onlyRow:true});*/
			$("#tb")//.link({text:'数据展示',icon:"icon-Extension-chart_organisation",url:"DbDataDisplay.html?dbID={id}",onlyRow:true})
			.link({text:'数据展示',icon:"icon-Extension-chart_organisation",url:"Cola.html?type=DATABASE&id={id}",onlyRow:false});
			findInfo();
		});
$.ajaxSetup({
	cache : false
// 关闭AJAX相应的缓存
});

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).link({text:'数据库对象',icon:'icon-Extension-database',url:'DataBaseObj.html?dbID='+rowData.id+'&dbName='+escape(rowData.name)
				+'&dbEn='+escape(rowData.nameEn)+'&description='+escape(rowData.description)})
	.html();
	return x;
}

function Dbformatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.id
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
	$.messager.confirm('确认', '您确认要删除数据库[' + sele.name + ']吗？', function(r) {
		if (r) {
			$.post("../database/remove", {
				"DbID" : sele.id
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
	$('#editDb').window({
		title : '修改数据库'
	}).window('open');
}

function append() {
	$("#editForm").form('load', {
		id : '-1',
		name : '',
		nameEN : '',
		isValid : '1',
		description : ''
	});
	$('#editDb').window({
		title : '新增数据库'
	}).window('open');
}

function cancelEdit() {
	$.messager.confirm('确认', '您确认要取消当前编辑吗？', function(r) {
		if (r) {
			$('#editDb').window('close');
		}
	});
}
function submitEdit() {
	$('#editForm').form('submit', {
		url : "../database/save",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			$.messager.alert("提示", data.message);
			if (data.success) {
				$('#editDb').window('close');
				findInfo();
			}
		}
	});
}

function findInfo() {
	var dbnum = "0";
	var DbValue = $('#cmbDb').combobox('getValue');
	var DbText = $('#cmbDb').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");
	var sDb = "";
	if (DbValue == -1) {
		sDb = "";
	} else {
		dbnum = "1";
		sDb = DbText;
	}
	$('#dg').datagrid({
			url: '../database/getDbList',
		queryParams:{
			"name" : sDb,
			"isValid" : (isValid==null||isValid=="")?1:isValid
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
		return row.isValid;
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
		url : "../database/Import",
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