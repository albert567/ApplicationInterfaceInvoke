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