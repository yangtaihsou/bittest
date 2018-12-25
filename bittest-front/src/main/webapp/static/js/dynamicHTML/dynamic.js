function appendHTMLById(key) {
    reflash();
    $.ajax({
        type: "get",
        url: "/dynamicHTML/getHTML",
        async: true,
        data: "key=" + key + "&_t=" + new Date().getTime(),
        success: function (data) {
            if (data.status) {
                var str = data.reason.split("##@@");
                for (var i = 0; i < str.length; i++) {
                    var tempstr = str[i].split("##@");
                    var row = $("<tr id=addItem" + i + "></tr>");
                    var td1 = $("<td class='col-lg-2'  ></td>");
                    var td2 = $("<td   ></td>");
                    var td3 = $("<td  colspan='2' ></td>");
                    td1.append(tempstr[1]);
                    td2.append(tempstr[0]);
                    row.append(td1);
                    row.append(td2);
                    row.append(td3);
                    $('#itemtable tr:last').before(row);
                }
            }
        }
    });
}

function appendHTMLByType(key, id, item) {
    reflash();
    if (key == id) {
        appendHTMLById(item);
        return;
    }
    $.ajax({
        type: "get",
        url: "/dynamicHTML/getType",
        async: true,
        data: "key=" + key + "&_t=" + new Date().getTime(),
        success: function (data) {
            if (data.status) {
                var str = data.reason.split("##@@");
                for (var i = 0; i < str.length; i++) {
                    var tempstr = str[i].split("##@");
                    var row = $("<tr id=addItem" + i + "></tr>");
                    var td1 = $("<td class='col-lg-2'  ></td>");
                    var td2 = $("<td></td>");
                    var td3 = $("<td colspan='2' ></td>");
                    td1.append(tempstr[1]);
                    td2.append(tempstr[0]);
                    row.append(td1);
                    row.append(td2);
                    row.append(td3);
                    $('#itemtable tr:last').before(row);
                }
            }
        }
    });
}
function reflash() {
    $("tr[id^='addItem']").remove();
}