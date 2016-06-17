
function dbFormatter(value, rowData, rowIndex) {
	return '<a id="Dbbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbDetail(' + rowData.dbID
			+ ')">' + value + '</a>';
}
function goDbDetail(DbID) {
	window.open(
					'DbDetail.html?DbID=' + DbID + '',
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
}
function dbObjFormatter(value, rowData, rowIndex) {
	return '<a id="DbObjbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goDbObjDetail('
			+ rowData.objID + ')">' + value + '</a>';
}
function goDbObjDetail(objID) {
	window
			.open(
					'DbObjDetail.html?ObjID=' + objID + '',
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
