
$.ajaxSetup({
	cache : false
		// 关闭AJAX相应的缓存
});


function dbFormatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.srcDbID
			+ ')">' + value + '</a>';
}
function goDbDetail(dbID) {
	window.open(
					'DbDetail.html?DbID=' + dbID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
}
function dbObjFormatter(value, rowData, rowIndex) {
	return '<a id="DbObjbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbObjDetail('
			+ rowData.srcObjID + ')">' + value + '</a>';
}
function goDbObjDetail(objID) {
	window
			.open(
					'DbObjDetail.html?ObjID=' + objID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}

function dbColFormatter(value,rowData,rowIndex){
	return '<a id="DbColbtn' + rowIndex
	+ '" href="javascript:void(0)"  onclick="goDbColDetail('
	+ rowData.srcID + ')">' + value + '</a>';
}

function goDbColDetail(colID) {
	window
			.open(
					'DbColDetail.html?colID=' + colID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

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