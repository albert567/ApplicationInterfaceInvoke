/*[start]*/
$(document).ready(function() {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtInterName").textbox('setValue',Util.param("interName"));
	$("#txtMethodName").textbox('setValue',Util.param("methodName"));
	$("#txtMethodType").textbox('setValue',Util.param("methodTypeName"));
	$("#txtMethodDesc").textbox('setValue',Util.param("description"));
	var themecombo1 = [{
		'IsValid': '1',
		'Text': '有效'
	}, {
		'IsValid': '0',
		'Text': '无效'
	}];
	$("#cmbIsValid2").combobox("loadData", themecombo1);
	findInfo();
});
$.ajaxSetup({
	cache : false
	// 关闭AJAX相应的缓存
});

function operator(value, rowData, rowIndex){

	var x=Util.rowOperator(rowIndex).html();
	return x;
}

function Appformatter(value, rowData, rowIndex) {
    return '<a id="Appbtn' + rowIndex + '" href="javascript:void(0)"  onclick="goAppDetail(' + rowData.appID + ')">' + value + '</a>';
}
function Interformatter(value, rowData, rowIndex) {
    return '<a id="Interbtn' + rowIndex + '" href="javascript:void(0)"  onclick="goInterDetail(' + rowData.interID + ')">' + value + '</a>';
}
function Meformatter(value, rowData, rowIndex) {
    return '<a id="Mebtn' + rowIndex + '" href="javascript:void(0)"  onclick="goMeDetail(' + rowData.methodID + ')">' + value + '</a>';
}
function goAppDetail(AppID) {
    window.open('AppDetail.html?ApplicationID=' + AppID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function goInterDetail(InterID) {
    window.open('InterfaceDetail.html?InterfaceID=' + InterID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function goMeDetail(MeID) {
    window.open('MethodDetail.html?MethodID=' + MeID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
/*[start] 查询*/
function findInfo() {
    var keyWords = $('#sKeyWords').val();
    var appnum = "0";
    var AppValue = $('#cmbApp').combobox('getValue');
    var AppText = $('#cmbApp').combobox('getText');
    var internum = "0";
    var InterValue = $('#cmbInterface').combobox('getValue');
    var InterText = $('#cmbInterface').combobox('getText');
    var methodnum = "0";
    var MethodValue = $('#cmbMethod').combobox('getValue');
    var MethodText = $('#cmbMethod').combobox('getText');
    var sApp = "";
    var sInter = "";
    var sMethod = "";
    if (AppValue == -1) {
        sApp = "";
    } else {
        appnum = "1";
        sApp = AppText;
    }

    if (InterValue == -1) {
        sInter = "";
    } else  {
            internum = "1";
            sInter = InterText;
        }

    if (MethodValue == -1) {
        methodnum = "";
    } else  {
            methodnum = "1";
            sMethod = MethodText;
        }
    var condition = new Object();
    condition.AppName = sApp;
    condition.AppID = appnum;
    condition.InterName = sInter;
    condition.InterID = internum;
    condition.MethodName = sMethod;
    condition.MethodID = methodnum;
    condition.IDs = keyWords;
    var json = JSON.stringify(condition);
    $('#dg').datagrid({
				url: '../interInvoke/getInterInvokeList',
				queryParams: {
					"invokeMethodID":Util.param('method'),
			        "appName":sApp,
			        "interName":sInter,
			        "methodName":sMethod,
			        "description":keyWords
			    }});
}

function getInterface() {
    $('#cmbInterface').combobox("clear")
    $('#cmbMethod').combobox("clear")
    $('#cmbInterface').combobox('loadData', []);
    $('#cmbMethod').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + $('#cmbApp').combobox('getValue');
    $('#cmbInterface').combobox('reload', url);
    $('#cmbInterface2').combobox('loadData',  $('#cmbInterface').combobox("getData"));
}
function getEditInterface() {
    $('#cmbInterface2').combobox("clear")
    $('#cmbMethod2').combobox("clear")
    $('#cmbInterface2').combobox('loadData', []);
    $('#cmbMethod2').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + $('#cmbApp2').combobox('getValue');
    $('#cmbInterface2').combobox('reload', url);
}

function getInvokeInter() {
    $('#cmbInvokeInter2').combobox("clear")
    $('#cmbInvokeMethod2').combobox("clear")
    $('#cmbInvokeInter2').combobox('loadData', []);
    $('#cmbInvokeMethod2').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + $('#cmbInvokeApp2').combobox('getValue');
    $('#cmbInvokeInter2').combobox('reload', url);
}

function getMethod() {
    $('#cmbMethod').combobox("clear")
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface').combobox('getValue');
    $('#cmbMethod').combobox('reload', url);
}

function getEditMethod() {
    $('#cmbMethod2').combobox("clear")
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface2').combobox('getValue');
    $('#cmbMethod2').combobox('reload', url);
}

function getInvokeMethod() {
    $('#cmbInvokeMethod2').combobox("clear")
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInvokeInter2').combobox('getValue');
    $('#cmbInvokeMethod2').combobox('reload', url);
}

/*[end]*/
/*[start] 增删改*/
function edit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}

	$('#cmbInterface2').combobox("clear")
    $('#cmbInterface2').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + sele.invokeAppID;
    $('#cmbInterface2').combobox('reload', url);

    $('#cmbMethod2').combobox("clear")
    $('#cmbMethod2').combobox('loadData', []);
    var url2 = '../method/getMethodName?InterfaceID=' + sele.invokeInterID;
    $('#cmbMethod2').combobox('reload', url2);


    $('#cmbInvokeInter2').combobox("clear")
    $('#cmbInvokeInter2').combobox('loadData', []);
    var url3 = '../appInter/GetInterName?ApplicationID=' + sele.appID;
    $('#cmbInvokeInter2').combobox('reload', url3);

    $('#cmbInvokeMethod2').combobox("clear")
    $('#cmbInvokeMethod2').combobox('loadData', []);
    var url4 = '../method/getMethodName?InterfaceID=' + sele.interID;
    $('#cmbInvokeMethod2').combobox('reload', url4);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改应用接口调用'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('appId'))){
		$("#cmbApp2").combobox('select',Util.param('appId'));
	}

	$('#cmbMethod2').combobox("clear")
    $('#cmbMethod2').combobox('loadData', []);
    var url2 = '../method/getMethodName?InterfaceID=' + Util.param('interId');
    $('#cmbMethod2').combobox('reload', url2);

	$("#editForm").form('load', {
				interInvokeID : '-1',
				invokeAppID: Util.param('appId'),
				invokeInterID: Util.param('interId'),
				invokeMethodID: Util.param('method'),
				appID: '',
				interID:'',
				methodID:'',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增应用接口调用'
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
				url : "../interInvoke/save",
				onSubmit : function() {
					validCbox(["#cmbApp2","#cmbInterface2","#cmbMethod2","#cmbInvokeMethod2","#cmbInvokeInter2","#cmbInvokeApp2"]);
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
	$.messager.confirm('确认', '您确认要删除['+sele.interInvokeName+']应用调用[' + sele.methodName + ']方法吗？', function(r) {
				if (r) {
					$.post("../interInvoke/remove", {
								"interInvokeID" : sele.interInvokeID
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
		url : "../interInvoke/Import",
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