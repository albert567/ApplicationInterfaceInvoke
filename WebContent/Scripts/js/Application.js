$(document).ready(
		function() {
			loadComboboxs();
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

			/*$("#tb").link({text:'功能',url:"AppFunction.html?appId={id}"})
			.link({text:'接口',url:"AppInterface.html?appId={id}"})
			.link({text:'数据展示',url:"AppDataDisplay.html?appID={id}",onlyRow:true});*/
			$("#tb")//.link({text:'数据展示',icon:"icon-Extension-chart_organisation",url:"AppDataDisplay.html?appID={id}",onlyRow:true})
			.link({text:'数据展示',icon:"icon-Extension-chart_organisation",url:"Cola.html?type=APPLICATION&id={id}",onlyRow:false});
			findInfo();
		});

var _p0;
function afterLoad(p){
	var _p=JSON.parse(Base64.decode(p));
	_p0=_p;
}

function openFuncAttachment(appID,id){
	Util.openUrl({url:"./Attachment.html?from="+ Util.encode({"type":"function","appID":appID,"id":id}),height:600,width:600});
}

function openAppAttachment(appID,id){
	_p0.a=true;
	_p0.d=true;
	Util.openUrl({url:"./Attachment.html?from="+ Util.encode({"type":"application","appID":appID,"id":appID,"p":_p0}),height:600,width:600});
}

function operator(value, rowData, rowIndex){
	//parentID=-1用于应用功能页面,文档按钮显示
	var x=Util.rowOperator(rowIndex).link({text:'功能',icon:'icon-Extension-wrench',url:'AppFunction.html?appId='+rowData.id+'&parentID=-1'+'&appName='+escape(rowData.name)
		+'&appTypeName='+escape(rowData.appTypeName)+'&description='+escape(rowData.description)})
	.link({text:'接口',icon:'icon-Extension-link',url:'AppInterface.html?appId='+rowData.id+'&appName='+escape(rowData.name)
		+'&appTypeName='+escape(rowData.appTypeName)+'&description='+escape(rowData.description)})
	.link({text:'数据库',icon:'icon-Extension-databases',url:'AppDataBase.html?appId='+rowData.id+'&appName='+escape(rowData.name)
		+'&appTypeName='+escape(rowData.appTypeName)+'&description='+escape(rowData.description)})
	.link({text:'源代码',icon:'icon-Extension-page_code',url:'CodeStructure.html?appId='+rowData.id+'&appName='+escape(rowData.name)
		+'&appTypeName='+escape(rowData.appTypeName)+'&description='+escape(rowData.description)});
	var id=-1;
	var appID = rowData.id;
	x.link({text:'公共文档',icon:"icon-Extension-attach",
		click:"openAppAttachment("+appID+","+id+")",onlyRow:false})
	 .link({text:'功能文档',icon:"icon-Extension-attach",
				click:"openFuncAttachment("+appID+","+id+")",onlyRow:false});	
	return x.html();
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

function Appformatter(value, rowData, rowIndex) {
	return '<a id="Appbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goAppDetail(' + rowData.id
			+ ')">' + value + '</a>';
}
function goAppDetail(AppID) {
	window
			.open(
					'AppDetail.html?ApplicationID=' + AppID + '',
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
			$.post("../application/remove", {
				"applicationID" : sele.id
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
	$('#editApp').window({
		title : '修改应用系统'
	}).window('open');
}

function append() {
	$("#editForm").form('load', {
		id : '-1',
		name : '',
		appType : '',
		valid : '1',
		description : ''
	});
	$('#editApp').window({
		title : '新增应用系统'
	}).window('open');
}

function cancelEdit() {
	$.messager.confirm('确认', '您确认要取消当前编辑吗？', function(r) {
		if (r) {
			$('#editApp').window('close');
		}
	});
}
function submitEdit() {
	$('#editForm').form('submit', {
		url : "../application/save",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			$.messager.alert("提示", data.message);
			if (data.success) {
				$('#editApp').window('close');
				findInfo();
			}
		}
	});
}

function findInfo() {
	var appnum = "0";
	var AppValue = $('#cmbApp').combobox('getValue');
	var AppText = $('#cmbApp').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");
	var sApp = "";
	var sDb = "";
	if (AppValue == -1) {
		sApp = "";
	} else {
		appnum = "1";
		sApp = AppText;
	}

	$('#dg').datagrid({
				url: '../application/getAppList',
					queryParams:{
		"name" : sApp,
		"sid" : appnum,
		"valid" : (isValid==null||isValid=="")?1:isValid
	}});
}

function loadComboboxs() {
	$.ajax({
		url : '../application/getApp',
		type : 'post',
		success : function(data) {
			var objdata = data;
			var themecombo2 = [ {
				'Name' : objdata[0].name,
				'ID' : objdata[0].id
			} ];
			for (var i = 1; i < objdata.length; i++) {
				themecombo2.push({
					"Name" : objdata[i].name,
					"ID" : objdata[i].id
				});
			}
			$("#cmbApp").combobox("loadData", themecombo2);
		}
	});
}
function forIsValid(val, row, index) {
	var ret = "";
	if (row.valid == "1") {
		ret = '有效';
		return ret;
	} else if (row.valid == "0") {
		ret = '无效';
		return ret;
	} else {
		return row.valid;
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
		url : "../application/Import",
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