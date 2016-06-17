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
	var rwcombo1 = [{
		'Rw': '1',
		'Text': '读'
	}, {
		'Rw': '0',
		'Text': '写'
	}];
	$("#cmbRw").combobox("loadData", rwcombo1);
	/*$("#tb").link({text:'参数',url:"MethodParam.html?appId={appID}&interId={interID}&method={methodID}"})
	.link({text:'数据库对象',url:"IM_DBOC_Rel.html?appId={appID}&interId={interID}&method={methodID}"});*/

});

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).link({text:'参数',icon:'icon-Extension-tag',url:'MethodParam.html?appId='+rowData.appID
		+'&interId='+rowData.interID+'&method='+rowData.methodID
		+'&appName='+escape(rowData.appName)+'&interName='+escape(rowData.interName)
		+'&methodName='+escape(rowData.methodName)+'&methodTypeName='+escape(rowData.methodTypeName)
		+'&description='+escape(rowData.description)})
	.link({text:'对象',icon:'icon-Extension-cmy',url:'IM_DBOC_Rel.html?appId='+rowData.appID
		+'&interId='+rowData.interID+'&method='+rowData.methodID
		+'&appName='+escape(rowData.appName)+'&interName='+escape(rowData.interName)
		+'&methodName='+escape(rowData.methodName)+'&methodTypeName='+escape(rowData.methodTypeName)
		+'&description='+escape(rowData.description)})
	.link({text:'调用',icon:'icon-Extension-arrow_right',url:'ApplicationInterfaceInvoke.html?appId='+rowData.appID
		+'&interId='+rowData.interID+'&method='+rowData.methodID
		+'&appName='+escape(rowData.appName)+'&interName='+escape(rowData.interName)
		+'&methodName='+escape(rowData.methodName)+'&methodTypeName='+escape(rowData.methodTypeName)
		+'&description='+escape(rowData.description)})
	//.link({text:'数据展示',url:'AppDataDisplay.html?appID={id}',onlyRow:true})
	.html();
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
/*[end]*/
function findInfo() {
    var keyWords = $('#txtKeyWords').val();
    var appnum = "0";
    var AppValue = $('#cmbApp').combobox('getValue');
    var AppText = $('#cmbApp').combobox('getText');
    var internum = "0";
    var InterValue = $('#cmbInterface').combobox('getValue');
    var InterText = $('#cmbInterface').combobox('getText');
    var intertypenum = "0";
    var InterTypeValue = $('#cmbInterfaceType').combobox('getValue');
    var InterTypeText = $('#cmbInterfaceType').combobox('getText');
    var methodnum = "0";
    var MethodValue = $('#cmbMethod').combobox('getValue');
    var MethodText = $('#cmbMethod').combobox('getText');
    var methodtypenum = "0";
    var MethodTypeValue = $('#cmbMethodType').combobox('getValue');
    var MethodTypeText = $('#cmbMethodType').combobox('getText');
    var isValid = $("#cmbIsValid").combobox("getValue");
    var sApp = "";
    var sInter = "";
    var sInterType = "";
    var sMethod = "";
    var sMethodType = "";
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
    if (InterTypeValue == -1) {
        sInterType = "";
    } else {
        intertypenum = "1";
        sInterType = InterTypeText;
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
    $('#dg').datagrid({
				url: '../method/getMeList',
    queryParams:{
    	"appID":AppValue,
    	"appName":sApp,
    	"interID":InterValue,
    	"interName":sInter,
    	"interfaceTypeName":sInterType,
    	"methodName":sMethod,
    	"methodTypeName":sMethodType,
    	"description":keyWords,
    	"isValid":isValid
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
	$('#cmbInterface2').combobox('loadData', []);
	var url = '../appInter/GetInterName?ApplicationID='+ $('#cmbApp2').combobox('getValue');
	$('#cmbInterface2').combobox('reload', url);
}

function getMethod() {
    $('#cmbMethod').combobox("clear")
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface').combobox('getValue');
    $('#cmbMethod').combobox('reload', url);
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

	$('#cmbInterface2').combobox("clear")
    $('#cmbInterface2').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + sele.appID;
    $('#cmbInterface2').combobox('reload', url);

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改接口方法'
			}).window('open');
}

function append() {
	if(!Util.empty(Util.param('appId'))){
		$("#cmbApp2").combobox('select',Util.param('appId'));
	}
	$("#editForm").form('load', {
				methodID : '-1',
				methodName: '',
				appID: Util.param('appId'),
				interID: Util.param('interId'),
				methodType:'',
				rw: '1',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增接口方法'
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
				url : "../method/save",
				onSubmit : function() {
					validCbox(["#cmbApp2","#cmbInterface2"]);
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
	$.messager.confirm('确认', '您确认要删除['+sele.appName+']应用中的[' + sele.interName + ']接口的'+sele.methodName+'方法吗？', function(r) {
				if (r) {
					$.post("../method/remove", {
								"methodID" : sele.methodID
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
		url : "../method/Import",
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