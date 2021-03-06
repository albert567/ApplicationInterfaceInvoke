/*[start]*/
$(document).ready(function() {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtAppType").textbox('setValue',Util.param("appTypeName"));
	$("#txtAppDesc").textbox('setValue',Util.param("description"));
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

	$("#tb").link({text:'数据展示',icon:"icon-Extension-chart_organisation",url:"Cola.html?type=APPLICATION&id="+Util.param("appID")});
});

/*function afterLoad(p){
	var id = -1;
	var appID = Util.param("appId");
	var count=0;
	$.get("../Attachment/count/function/"+appID+"/"+id,function(resp){
		count=resp.count;
		$("#tb").link({text:'文档('+count+')',icon:"icon-Extension-attach",
						url:"./Attachment.html?from="
							+ Util.encode({"type":"function","appID":appID,"id":id}),onlyRow:false,height:600,width:600});
	},"json");
}*/

function operator(value, rowData, rowIndex){
	var x;
	if('0202'==rowData.interfaceTypeID){
		x=Util.rowOperator(rowIndex)
		.link({text:'数据库接口',icon:'icon-Extension-hammer',url:'InterProcedure.html?appId='+rowData.appID
				+'&interId='+rowData.interID+'&appName='+escape(rowData.appName)
				+'&interName='+escape(rowData.interName)+'&interTypeName='+escape(rowData.interfaceType)
				+'&description='+escape(rowData.description)})
		.html();
	}else{
		x=Util.rowOperator(rowIndex)
		.link({text:'方法',icon:'icon-Extension-hammer',url:'InterfaceMethod.html?appId='+rowData.appID
			+'&interId='+rowData.interID+'&appName='+escape(rowData.appName)
			+'&interName='+escape(rowData.interName)+'&interTypeName='+escape(rowData.interfaceType)
			+'&description='+escape(rowData.description)})
		.html();
	}
	return x;
}

function appFormatter(value, rowData, rowIndex) {
	return '<a id="Appbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goAppDetail('
			+ rowData.appID + ')">' + value + '</a>';
}
function interFormatter(value, rowData, rowIndex) {
	return '<a id="Interbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goInterDetail(\''
			+ rowData.interID + '\',\''+rowData.interfaceTypeID+'\')">' + value + '</a>';
}
function goAppDetail(appID) {
	window
			.open(
					'AppDetail.html?ApplicationID=' + appID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function goInterDetail(interID,typeId) {
	var url;
	if(typeId=='0202'){
		url = 'InterProceDetail.html?InterfaceID=' + interID;
	}else{
		url = 'InterfaceDetail.html?InterfaceID=' + interID;
	}
	window
			.open(
					url,
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
	$.messager.confirm('确认', '您确认要删除接口[' + sele.interName + ']吗？', function(r) {
				if (r) {
					$.post("../appInter/remove", {
								"interfaceID" : sele.interID
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
	$('#edit').window({
				title : '修改应用接口'
			}).window('open');
}

function append() {
	$("#editForm").form('load', {
				interID : '-1',
				interName : '',
				appID : Util.param("appId"),
				interfaceType : '',
				valid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增应用接口'
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
				url : "../appInter/save",
				onSubmit : function() {
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
	var appnum = "0";
	var AppValue = $('#cmbApp').combobox('getValue');
	var AppText = $('#cmbApp').combobox('getText');
	var internum = "0";
	var InterValue = $('#cmbInterface').combobox('getValue');
	var InterText = $('#cmbInterface').combobox('getText');
	var typenum = "0";
	var TypeValue = $('#cmbInterfaceType').combobox('getValue');
	var TypeText = $('#cmbInterfaceType').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");
	var sApp = "";
	var sInter = "";
	var sType = "";

	if (AppValue == -1) {
		sApp = "";
	} else {
		appnum = "1";
		sApp = AppText;
	}

	if (InterValue == -1) {
		sInter = "";
	} else {
		internum = "1";
		sInter = InterText;
	}

	if (TypeValue == -1) {
		sType = "";
	} else {
		typenum = "1";
		sType = TypeText;
	}
	$('#dg').datagrid({
				url: '../appInter/GetAppInterList',
				queryParams:{
					"appName" : sApp,
					"appID" : AppValue,
					"interName" : sInter,
					"interID" : internum,
					"interfaceType" : sType,
					"valid" : isValid,
					"description" : keyWords
				}});
}

function getInterface() {
	$('#cmbInterface').combobox("clear")
	$('#cmbInterface').combobox('loadData', []);
	var url = '../appInter/GetInterName?ApplicationID='
			+ $('#cmbApp').combobox('getValue');
	$('#cmbInterface').combobox('reload', url);
}
function forIsValid(val, row, index) {
	var ret = "";
	if (row.valid == "0") {
		ret = '无效';
		return ret;
	} else if (row.valid == "1") {
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
		url : "../appInter/Import",
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