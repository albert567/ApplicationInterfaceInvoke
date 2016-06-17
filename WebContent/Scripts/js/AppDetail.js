function interFormatter(value, rowData, rowIndex) {
	return '<a id="Interbtn' + rowIndex
			+ '" href="javascript:void(0)"  onclick="goInterDetail(\''
			+ rowData.id + '\',\''+rowData.interfaceTypeID+'\')">' + value + '</a>';
}
function goInterDetail(interID,typeId) {
	var url;
	if(typeId=='0202'){
		url = 'InterProceDetail.html?InterfaceID=' + interID;
	}else{
		url = 'InterfaceDetail.html?InterfaceID=' + interID;
	}
	window
			.open(
					url,
					'newwin',
					'height=560, width=1000, top=80, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');

}