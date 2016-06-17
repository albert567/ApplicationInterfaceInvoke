/* [start] */
$(document).ready(function() {
	$("#txtAppName").textbox('setValue',Util.param("appName"));
	$("#txtAppType").textbox('setValue',Util.param("appTypeName"));
	$("#txtAppDesc").textbox('setValue',Util.param("description"));
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

var _p0;
function afterLoad(p){
	var id=Util.param("parentID");
	console.log("id="+id);
	var appID = Util.param("appId");
	var count=0;
	var _p=JSON.parse(Base64.decode(p));
	_p0=_p;
	if(id<0){
		_p.a=false;
		_p.d=false;
		console.log(_p);
	}
	//console.log(_p);
	$.get("../Attachment/count/function/"+appID+"/"+id,function(resp){
		count=resp.count;
		$("#tb").link({text:'功能文档('+count+')',icon:"icon-Extension-attach",
						url:"./Attachment.html?from="
							+ Util.encode({"type":"function","appID":appID,"id":id,"p":_p}),onlyRow:false,height:600,width:600});
	},"json");
	/*if(id<0){
		_p.a=true;
		_p.d=true;
	}*/
	$.get("../Attachment/count/application/"+appID+"/"+id,function(resp){
		count=resp.count;
		$("#tb").link({text:'公共文档('+count+')',icon:"icon-Extension-attach",
						url:"./Attachment.html?from="
							+ Util.encode({"type":"application","appID":appID,"id":appID,"p":_p}),onlyRow:false,height:600,width:600});
	},"json");
}

function openAttachment(appID,id){
	_p0.a=true;
	_p0.d=true;
	Util.openUrl({url:"./Attachment.html?from="+ Util.encode({"type":"function","appID":appID,"id":id,"p":_p0}),height:600,width:600});
}

function operator(value, rowData, rowIndex){
	var x=Util.rowOperator(rowIndex)
		.link({text:'数据库对象',icon:'icon-Extension-database',
			url:'AppFuncObj.html?appID='+rowData.appID
			+'&appName='+escape(rowData.appName)
			+'&funcID='+rowData.id
			+'&funcName='+escape(rowData.name)
			+'&description='+escape(rowData.description)})
		.link({text:'源代码',icon:'icon-Extension-page_code',
			url:'FuncCodeList.html?appID='+rowData.appID
			+'&appName='+escape(rowData.appName)
			+'&funcID='+rowData.id
			+'&funcName='+escape(rowData.name)
			+'&description='+escape(rowData.description)});
	var id=rowData.id;
	var appID = rowData.appID;
	x.link({text:'文档',icon:"icon-Extension-attach",
				click:"openAttachment("+appID+","+id+")",onlyRow:false});
	return x.html();
}

function Appformatter(value, rowData, rowIndex) {
	return '<a id="Appbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goAppDetail('
			+ rowData.appID + ')">' + value + '</a>';
}
function goAppDetail(AppID) {
	window
			.open(
					'AppDetail.html?ApplicationID=' + AppID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}
/* [end] */

/*[start] 导入*/
function imp(){
	$('#imp').window('open');
}

function cancelImp() {
	$('#imp').window('close');
}

function subImp() {
	$('#impForm').form('submit', {
		url : "../appFunc/Import",
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

/*删除*/
function removeit(index) {
	$("#dg").datagrid("selectRow",index);
	var sele = $("#dg").datagrid("getSelected");
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '您确认要删除[' + sele.appName + ']应用中的['
					+ sele.name + ']功能吗？', function(r) {
				if (r) {
					$.post("../appFunc/remove",sele, function(msg) {
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

	if(sele.parentID==-1){
		sele.parentID='';
    }

	$("#editForm").form('load', sele);
	$('#edit').window({
				title : '修改应用功能'
			}).window('open');
}

/*新增*/
function append() {
	$("#editForm").form('load', {
				id : '-1',
				name : '',
				appID : Util.param("appId"),
				parentID: Util.param("parentID")==-1?'':Util.param("parentID"),
				isValid : '1',
				description : ''
			});
	$('#edit').window({
				title : '新增应用功能'
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
	if($('#parentID').combobox('getValue')==null){
		$('#parentID').combobox('setValue',-1);
	}
	$('#editForm').form('submit', {
				url : "../appFunc/save",
				onSubmit : function() {
					if($("#parentID").combobox("getValue")==''){
						$("#parentID").combobox("setValue",-1);
					}
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
/*查询列表*/
function findInfo() {
	var keyWords = $('#txtKeyWords').val();
	var AppValue = $('#cmbApp').combobox('getValue');
	var InterText = $('#cmbInterface').combobox('getText');
	var isValid = $("#cmbIsValid").combobox("getValue");
	var parentText = $('#cmbParent').combobox('getText');
	var sApp = "";
	var sInter = "";

	$('#dg').datagrid({url: '../appFunc/GetList',
						queryParams:{
				"appID" : AppValue,
				"name" : InterText,
				//"parentName":parentText,
				"isValid" : isValid,
				"description" : keyWords,
				"parentID":Util.param('parentID')==null?-1:Util.param('parentID')
			}});
}

function getNameList() {
	$('#cmbInterface').combobox("clear");
	//$('#cmbInterface').combobox('loadData', []);
	var url = '../appFunc/GetName/'+ $('#cmbApp').combobox('getValue');
	$('#cmbInterface').combobox('reload', url);
}

function getEditList(){
	$('#parentID').combobox("clear");
	var url = '../appFunc/GetName/'+ Util.param("appId");
	$('#parentID').combobox('reload', url);
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