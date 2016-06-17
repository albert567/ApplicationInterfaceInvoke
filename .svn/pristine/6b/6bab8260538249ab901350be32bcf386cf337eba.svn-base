/*[start]*/
$(document).ready(function() {
	$("#txtDbName").textbox('setValue',Util.param("dbName"));
	$("#txtObjName").textbox('setValue',Util.param("objName"));
	$("#txtObjType").textbox('setValue',Util.param("objType"));
	$("#txtObjDesc").textbox('setValue',Util.param("description"));

	$('#cmbParam').combobox("clear")
    $('#cmbParam').combobox('loadData', []);
    var url = '../proparam/getParamName?objID=' + Util.param("objID");
    $('#cmbParam').combobox('reload', url);

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
	var x=Util.rowOperator(rowIndex).html();
	return x;
}
function findInfo() {
    var keyWords = $('#txtKeyWords').val();

    var ParamText = $('#cmbParam').combobox('getText');
    var ParamTypeText = $('#cmbParamType').combobox('getText');
    var inOrOut = $('#cmbInOrOut').combobox('getValue');
    var isValid = $("#cmbIsValid").combobox("getValue");

    $('#dg').datagrid({
    	url: '../proparam/getParamList',
    	queryParams:{
    		"objID":Util.param('objID'),
        	"name":ParamText,
        	"typeName":ParamTypeText,
        	"description":keyWords,
        	"isValid":isValid,
        	"inOrOut":inOrOut
        }
    });
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


function forSn(val, row, index) {
   return val>0?val:'';
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

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改过程参数'
			}).window('open');
}

function append() {
	$("#editForm").form('load', {
				objID:Util.param('objID'),
				sn:1,
				name:'',
				typeID:'',
				inOrOut: '0',
				length: '-1',
				isValid : '1',
				description:''
			});
	$('#edit').window({
				title : '新增过程参数'
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
				url : "../proparam/save",
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

function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除参数['+sele.name+']吗？', function(r) {
				if (r) {
					$.post("../proparam/remove", {
								"paramID" : sele.id
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
		url : "../proparam/Import",
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