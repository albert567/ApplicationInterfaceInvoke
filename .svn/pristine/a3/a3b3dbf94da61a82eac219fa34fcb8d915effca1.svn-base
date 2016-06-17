$(function() {
	var keyWords = Util.param("value");//$('#txtKeyWords').val();
	$('#dg').datagrid({
			url: '../search/getList',
		queryParams:{
			"description" : keyWords
		}
	});
});

function Nameformatter(value, rowData, rowIndex) {
	return '<a id="Appbtn' + rowIndex
	+ '" href="javascript:void(0)"  onclick="Util.openUrl(\''
	+ rowData.url + '\')">' + value + '</a>';
}