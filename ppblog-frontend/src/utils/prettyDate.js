const prettyDate = (date) => {
    var before = Date.parse(date) / 1000;
    var now = Date.parse(new Date()) / 1000;
    var limit = now - before;
    var content = "";
    if (limit < 60) {
        content = "刚刚";
    } else if (limit >= 60 && limit < 3600) {
        content = Math.floor(limit / 60) + "分钟前";
    } else if (limit >= 3600 && limit < 86400) {
        content = Math.floor(limit / 3600) + "小时前";
    } else if (limit >= 86400 && limit < 2592000) {
        content = Math.floor(limit / 86400) + "天前";
    } else if (limit >= 2592000 && limit < 31104000) {
        content = Math.floor(limit / 2592000) + "个月前";
    } else {
        content = "很久前";
    }
    return content;
}

export default prettyDate