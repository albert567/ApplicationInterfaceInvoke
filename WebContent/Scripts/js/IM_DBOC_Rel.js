/* [start] */
$(document).ready(function() {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtInterName").textbox('setValue',Util.param("interName"));
	$("#txtMethodName").textbox('setValue',Util.param("methodName"));
	$("#txtMethodType").textbox('setValue',Util.param("methodTypeName"));
	$("#txtMethodDesc").textbox('setValue',Util.param("description"));
	//loadComboboxs();
	var themecombo1 = [{
				'isValid' : 1,
				Text : '有效'
			}, {
				'isValid' : 0,
				Text : '无效'
			}];
	$("#cmbIsValid").combobox("loadData", themecombo1).combobox("setValue", 1);
	$("#cmbIsValid2").combobox("loadData", themecombo1).combobox("setValue",1);
});
$.ajaxSetup({
	cache : false
		// 关闭AJAX相应的缓存
	});
/* [end] */

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex).html();
	return x;
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
		url : "../IMDORelation/Import",
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

/*[start] 增删改*/
/*删除*/
function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除？', function(r) {
				if (r) {
					$.post("../IMDORelation/remove",sele, function(msg) {
								$.messager.alert("提示", msg.message);
								if (msg.success) {
									findInfo();
								}
							}, "json");
				}
			});
}

/*编辑*/
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

    $('#cmbMethod2').combobox("clear")
    $('#cmbMethod2').combobox('loadData', []);
    var url = '../method/getMethodName?InterfaceID=' + sele.interID;
    $('#cmbMethod2').combobox('reload', url);

    $('#cmbobjID').combobox("clear")
    $('#cmbobjID').combobox('loadData', []);
    var url = '../dbobj/getDbObj?DbID=' + sele.dbID;
    $('#cmbobjID').combobox('reload', url);

    $('#cmbColID').combobox("clear")
	$('#cmbColID').combobox('loadData', []);
	var url = '../dbcol/getDbCol?ObjID='+ sele.objID;
	$('#cmbColID').combobox('reload', url);

    if(sele.objID==-1){
    	sele.objID='';
    }

    if(sele.columnID==-1){
    	sele.columnID='';
    }

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改接口方法与数据对象关系'
			}).window('open');
}

/*新增*/
function append() {
	if(!Util.empty(Util.param('appId'))){
		$("#cmbApp2").combobox('select',Util.param('appId'));
	}
	if(!Util.empty(Util.param('interId'))){
		$("#cmbInterface2").combobox('select',Util.param('interId'));
	}
	if(!Util.empty(Util.param('dbID'))){
		$("#cmbdbID").combobox('select',Util.param('dbID'));
	}
	$("#editForm").form('load', {
				id:-1,
				appID:Util.param('appId'),
		    	interID: Util.param('interId'),
		    	methodID:Util.param('method'),
				dbID: Util.param('dbID'),
				objID: Util.param('objID'),
				columnID: -1,
				isValid : 1
			});
	$('#edit').window({
				title : '新增接口方法与数据对象关系'
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
				url : "../IMDORelation/save",
				onSubmit : function() {
					if($("#cmbobjID").combobox("getValue")==''){
						$("#cmbobjID").combobox("setValue",-1);
					}
					if($("#cmbColID").combobox("getValue")==''){
						$("#cmbColID").combobox("setValue",-1);
					}
					validCbox(["#cmbApp2","#cmbInterface2","#cmbMethod2","#cmbdbID"]);
					return $(this).form('enableValidation').form('validate');
				},
				success : function(data) {
					var data = validResp(data);
					if (data.success) {
						$('#edit').window('close');
						findInfo();
					}
				}
			});
}

/*[end]*/

/*[start] 查询*/
function findInfo() {
	 var appnum = "0";
    var AppValue = $('#cmbApp').combobox('getValue');
    var AppText = $('#cmbApp').combobox('getText');
    var internum = "0";
    var InterValue = $('#cmbInterface').combobox('getValue');
    var InterText = $('#cmbInterface').combobox('getText');
    var intertypenum = "0";
    var MethodValue = $('#cmbMethod').combobox('getValue');
    var MethodText = $('#cmbMethod').combobox('getText');

    var dbnum = "0";
	var DbValue = $('#cmbDb').combobox('getValue');
	var DbText = $('#cmbDb').combobox('getText');
	var objnum = "0";
	var ObjValue = $('#cmbObj').combobox('getValue');
	var ObjText = $('#cmbObj').combobox('getText');

	var colnum = "0";
	var ColValue = $('#cmbCol').combobox('getValue');
	var ColText = $('#cmbCol').combobox('getText');


    var isValid = $("#cmbIsValid").combobox("getValue");

	var sApp = "";
	var sInter = "";

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

	$('#dg').datagrid({
				url: '../IMDORelation/GetList',
				queryParams:{
					"appID":AppValue,
			    	"appName":sApp,
			    	"interID":InterValue,
			    	"interName":sInter,
			    	"methodID":MethodValue,
			    	"methodName":sMethod,
					"dbName" : sDb,
					"objName" : sObj,
					"columnName" : sCol,
					"isValid" : isValid
				}});
}

function getInterface() {
    $('#cmbInterface').combobox("clear");
    $('#cmbMethod').combobox("clear");
    $('#cmbParam').combobox("clear");
    $('#cmbInterface').combobox('loadData', []);
    $('#cmbMethod').combobox('loadData', []);
    $('#cmbParam').combobox('loadData', []);
    var url = '../appInter/GetInterName?ApplicationID=' + $('#cmbApp').combobox('getValue');
    $('#cmbInterface').combobox('reload', url);
}

function getEditInterface() {
	$('#cmbInterface2').combobox("clear")
	$('#cmbMethod2').combobox("clear")
	$('#cmbInterface2').combobox('loadData', []);
	$('#cmbMethod2').combobox('loadData', []);
	var url = '../appInter/GetInterName?ApplicationID='
			+ $('#cmbApp2').combobox('getValue');
	$('#cmbInterface2').combobox('reload', url);
}

function getMethod() {
    $('#cmbMethod').combobox("clear")
    $('#cmbParam').combobox("clear");
    $('#cmbMethod').combobox('loadData', []);
    $('#cmbParam').combobox('loadData', []);
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface').combobox('getValue');
    $('#cmbMethod').combobox('reload', url);
}

function getEditMethod() {
    $('#cmbMethod2').combobox("clear")
    $('#cmbMethod2').combobox('loadData', []);
    var url = '../method/getMethodName?InterfaceID=' + $('#cmbInterface2').combobox('getValue');
    $('#cmbMethod2').combobox('reload', url);
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

function getEditDbObjCol() {
	$('#cmbColID').combobox("clear")
	$('#cmbColID').combobox('loadData', []);
	var url = '../dbcol/getDbCol?ObjID='
			+ $('#cmbobjID').combobox('getValue');
	$('#cmbColID').combobox('reload', url);
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

/*[end]*/
}