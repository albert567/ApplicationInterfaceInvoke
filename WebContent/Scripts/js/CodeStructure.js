$(document).ready(function(){
	$('#txtAppID').textbox('setValue',Util.param('appId'));
	//findInfo();
});

function removeit(sele) {
	if (sele == null) {
		$.messager.alert("提示", "请选择要删除的行!");
		return;
	}
	$.messager.confirm('确认', '[' + sele.name + ']可能有子节点,您确定要删除吗？', function(r) {
		if (r) {
			$("body").toggleLoading({msg:"正在删除数据,请稍等..."});
			$.post("../code/remove", sele, function(msg) {
				$("body").toggleLoading();
				$.messager.alert("提示", msg.message);
				if (msg.success) {
					findInfo();
				}
			}, "json");
		}
	});
}

function afterLoad(p){
	$("#tb").children().last().remove();
	$("#tb").children().last().remove();
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
		url : "../code/Import/"+Util.param('appId'),
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


function operator(value, rowData, rowIndex){
	var x=$('<div>').css('float','left');
		x.link({tip:'新增',icon:'icon-Extension-page_add',click:'append('+rowData.id+')'})
		.link({tip:'编辑',icon:'icon-Extension-page_edit',click:'edit('+JSON.stringify(rowData).replace(/"/g,"'")+')'})
		.link({tip:'删除',icon:'icon-Extension-Empty',click:'removeit('+JSON.stringify(rowData).replace(/"/g,"'")+')'});
	return x.html();
}



/*[start] 编辑*/
function edit(sele) {
	if (sele == null) {
		$.messager.alert("提示", "请选择要操作的行!");
		return;
	}
	$('#parentID').combobox('clear');
	var url = '../code/GetCode?appID='+Util.param('appId');
	$('#parentID').combobox('reload',url);
	
	$("#editForm").form('load', sele);
	$('#edit').window({
		title : '修改目录'
	}).window('open');
}

function append(id) {
	$('#parentID').combobox('clear');
	var url = '../code/GetCode?appID='+Util.param('appId');
	$('#parentID').combobox('reload',url);
	
	$("#editForm").form('load', {
		appID: Util.param('appId'),
		name : '',
		parentID : id
	});
	
	$('#edit').window({
		title : '新增目录'
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
		url : "../code/save",
		onSubmit : function() {
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
function findInfo() {
	var keywords = $('#txtKeyWords').textbox('getText');
	console.log(keywords);
	if(keywords==''){
		$('#dg').treegrid('options').url='../code/GetRoot?appID='+Util.param('appId');
		$('#dg').treegrid('load')
	}else{
		$('#findForm').form('submit', {
			url : "../code/GetList",
			success : function(data) {
				$('#dg').treegrid('loadData',validResp(data));
			}
		});
	}
}
