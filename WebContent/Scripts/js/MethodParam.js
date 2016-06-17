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
	$("#cmbIsValid").combobox("loadData", themecombo1);
	$("#cmbIsValid2").combobox("loadData", themecombo1);
	var inOrOutcombo1 = [{
		'InOrOut': '2',
		'Text': '入参、出参'
	},{
		'InOrOut': '1',
		'Text': '出参'
	}, {
		'InOrOut': '0',
		'Text': '入参'
	}];
	$("#cmbInOrOut").combobox("loadData", inOrOutcombo1);
	$("#cmbInOrOut2").combobox("loadData", inOrOutcombo1);
});
$.ajaxSetup({
	cache : false
	// 关闭AJAX相应的缓存
});

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).html();
	return x;
}

function appFormatter(value, rowData, rowIndex) {
    return '<a id="Appbtn' + rowIndex + '" href="javascript:void(0)"  onclick="goAppDetail(' + rowData.appID + ')">' + value + '</a>';
}
function interFormatter(value, rowData, rowIndex) {
    return '<a id="Interbtn' + rowIndex + '" href="javascript:void(0)"  onclick="goInterDetail(' + rowData.interID + ')">' + value + '</a>';
}
function meFormatter(value, rowData, rowIndex) {
    return '<a id="Mebtn' + rowIndex + '" href="javascript:void(0)"  onclick="goMeDetail(' + rowData.methodID + ')">' + value + '</a>';
}
function goAppDetail(appID) {
    window.open('AppDetail.html?ApplicationID=' + appID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function goInterDetail(interID) {
    window.open('InterfaceDetail.html?InterfaceID=' + interID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
function goMeDetail(methodID) {
    window.open('MethodDetail.html?MethodID=' + methodID + '', 'newwin'
                     , 'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}

function forSN(val, row, index) {
	  return val>0?val:'';
}

function findInfo() {
    var keyWords = $('#txtKeyWords').val();
    var appnum = "0";
    var AppValue = $('#cmbApp').combobox('getValue');
    var AppText = $('#cmbApp').combobox('getText');
    var internum = "0";
    var InterValue = $('#cmbInterface').combobox('getValue');
    var InterText = $('#cmbInterface').combobox('getText');
    var intertypenum = "0";
    var MethodValue = $('#cmbMethod').combobox('getValue');
    var MethodText = $('#cmbMethod').combobox('getText');
    var methodtypenum = "0";
    var MethodTypeValue = $('#cmbMethodType').combobox('getValue');
    var MethodTypeText = $('#cmbMethodType').combobox('getText');
    var paramnum = "0";
    var ParamValue = $('#cmbParam').combobox('getValue');
    var ParamText = $('#cmbParam').combobox('getText');
    var paramtypenum = "0";
    var ParamTypeValue = $('#cmbParamType').combobox('getValue');
    var ParamTypeText = $('#cmbParamType').combobox('getText');
    var inOrOut = $('#cmbInOrOut').combobox('getValue');
    var isValid = $("#cmbIsValid").combobox("getValue");
    var sApp = "";
    var sInter = "";
    var sMethod = "";
    var sMethodType = "";
    var sParam = "";
    var sParamType = "";

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
    if (MethodValue == -1) {
        methodnum = "";
    } else {
        methodnum = "1";
        sMethod = MethodText;
    }
    if (MethodTypeValue == -1) {
        sMethodType = "";
    } else {
        methodtypenum = "1";
        sMethodType = MethodTypeText;
    }
    if (ParamValue == -1) {
        paramnum = "";
    } else {
        paramnum = "1";
        sParam = ParamText;
    }
    if (ParamTypeValue == -1) {
        sParamType = "";
    } else {
        paramtypenum = "1";
        sParamType = ParamTypeText;
    }

    $('#dg').datagrid({
    	url: '../param/getPaList',
    	queryParams:{
    		"appID":AppValue,
        	"appName":sApp,
        	"interID":InterValue,
        	"interName":sInter,
        	"methodID":MethodValue,
        	"methodName":sMethod,
        	"methodTypeName":sMethodType,
        	"paramName":sParam,
        	"paramTypeName":sParamType,
        	"description":keyWords,
        	"isValid":isValid,
        	"inOrOut":inOrOut
        }
    });
}

function getInterface() {
    $('#cmbInterface').combobox("clear");
    $('#cmbMethod').combobox("clear");
    $('#cmbParam').combobox("clear");
    var url = '../appInter/GetInterName?ApplicationID=' + $('#cmbApp').combobox('getValue');
    $('#cmbInterface').combobox('reload', url);
}

function getEditInterface() {
	$('#cmbInterface2').combobox("clear")
	$('#cmbMethod2').combobox("clear")
	var url = '../appInter/GetInterName?ApplicationID='
			+ $('#cmbApp2').combobox('getValue');
	$('#cmbInterface2').combobox('reload', url);
}

function getMethod() {
    $('#cmbMethod').combobox("clear")
    $('#cmbParam').combobox("clear");
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface').combobox('getValue');
    $('#cmbMethod').combobox('reload', url);
}

function getEditMethod() {
    $('#cmbMethod2').combobox("clear")
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface2').combobox('getValue');
    $('#cmbMethod2').combobox('reload', url);
}

function getParam() {
    $('#cmbParam').combobox("clear")
    var url = '../param/getParamName?MethodID=' + $('#cmbMethod').combobox('getValue');
    $('#cmbParam').combobox('reload', url);
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

function forInOrOut(val, row, index) {
    var ret = "";
    if (row.inOrOut == "0") {
        ret = '入参';
        return ret;
    } else if (row.inOrOut == "1") {
        ret = '出参';
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

	$('#cmbInterface2').combobox("clear")
    /*$('#cmbInterface2').combobox('loadData', []);*/
    var url = '../appInter/GetInterName?ApplicationID=' + sele.appID;
    $('#cmbInterface2').combobox('reload', url);

    $('#cmbMethod2').combobox("clear")
    /*$('#cmbMethod2').combobox('loadData', []);*/
    var url = '../method/getMethodName?InterfaceID=' + sele.interID;
    $('#cmbMethod2').combobox('reload', url);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改方法参数'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('appId'))){
		$("#cmbApp2").combobox('select',Util.param('appId'));
	}

	if(!Util.empty(Util.param('interId'))){
		$('#cmbMethod2').combobox("clear")
	    /*$('#cmbMethod2').combobox('loadData', []);*/
	    var url = '../method/getMethodName?InterfaceID=' + Util.param('interId');
	    $('#cmbMethod2').combobox('reload', url);
	}
	$("#editForm").form('load', {
				paramID:'-1',
				paramName:'',
				appID: Util.param('appId'),
				interID: Util.param('interId'),
				methodID : Util.param('method'),
				paramTypeID:'',
				paramTypeName:'',
				inOrOut: '0',
				length: '-1',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增方法参数'
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
				url : "../param/save",
				onSubmit : function() {
					validCbox(["#cmbApp2","#cmbInterface2","#cmbMethod2"]);
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
	$.messager.confirm('确认', '您确认要删除['+sele.appName+']应用中的[' + sele.interName + ']接口的['+sele.methodName+']方法的['+sele.paramName+']参数吗？', function(r) {
				if (r) {
					$.post("../param/remove", {
								"paramID" : sele.paramID
							}, function(msg) {
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
		url : "../param/Import",
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