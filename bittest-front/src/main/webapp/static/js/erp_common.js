var commonErp = (function () {
    var instance;
    var Erp = function () {
        var me = this;
        me.map = new Map();
        me.initTopMenusHTML = function () {//填充每个topmenu的html

        }
        me.loadDefaultSidebar = function () {//加载菜单
            me.initTopMenusHTML();
            $('#topSidebar').html(me.topMenu.html);
            me.loadDefaultPage();
        }
        me.loadDefaultPage = function () {//加载默认显示的页面
            var a = $('#topSidebar').find("a")[0];
            $(a).click();
            var li = $('#leftSidebar').find("li")[0];
            $(li).click();
        }
        me.ajaxRequestForcontent = function (url, paraData, domId) {
            me.releaseLastAjax();
            $('#loading_cover').show();
            me.trimForm();
            var ajaxRequest = $.ajax({
                type: "post",
                url: url,
                cache: false,
                async: true,
                data: paraData + "&_t=" + new Date().getTime(),
                complete: function (data) {
                    $('.theme-popover-mask').fadeOut(1);
                    $('#' + domId).html(data.responseText);
                    $('#loading_cover').hide();
                }
            });
            me.ajaxRequesArr.push(ajaxRequest);//临时存放ajax请求对象
        }

        me.topMenu = {
            html: ''

        }
        me.trimForm = function () {
            if ($('form').length > 0) {
                $('form').find('input[type=text]').each(function () {
                    $(this).val($(this).val().trim());
                });
            }
        }

        me.getHTML = function (key) {
            return me.map.get(key);
        }
        
        me.postMultipartAjaxCallBack = function (form, url, fn, fnP1) {
            me.trimForm();
            me.releaseLastAjax();

            $.ajax({
                type: "post",
                url: url,
                cache: false,
                async: true,
                data: new FormData(form),
                processData: false,
                contentType: false,
                success: function (result) {

                    fn.call(fnP1, result, fnP1);//回调
                    console.log(1);
                }
            })
        }

        me.postAjaxCallBack = function (paraData, url, fn, fnP1) {
            me.trimForm();
            me.releaseLastAjax();

            $.ajax({
                type: "post",
                url: url,
                cache: false,
                async: true,
                timeout : 60000, //超时时间设置，单位毫秒
                data: paraData + "&_t=" + new Date().getTime(),
                success: function (result) {

                    fn.call(fnP1, result, fnP1);//回调
                    console.log(1);
                }
            })
        }
        me.getJsonpAjax = function (url) {
            me.trimForm();
            $.ajax({
                type: "get",
                url: url + "&_=" + new Date().getTime(),
                dataType: 'jsonp',
                cache: false,
                async: true,
                success: function (result) {
                    //console.info(result);
                }
            })
        }
        me.getAjaxCallBack = function (url, fn, fnP1) {
            me.trimForm();
            $.ajax({
                type: "get",
                url: url + "&_t=" + new Date().getTime(),
                cache: false,
                async: true,
                success: function (result) {
                    fn.call(null, result, fnP1);//回调
                }
            })
        }

        me.ajaxRequesArr = [];

        me.releaseLastAjax = function () {
            for (var i = 0; i < me.ajaxRequesArr.length; i++) {
                var status = me.ajaxRequesArr[i].status;
                if (status != 200) {
                    me.ajaxRequesArr[i].abort();
                }
            }
            me.ajaxRequesArr = [];
        }
        me.dynamicTableClick = function (obj) {//点击按钮增加动态表格
            var tag = obj.attr('tag');
            me.addDynamicTable(tag);
        }
        me.addDynamicTable = function (id) {
            var trObj = $('#' + id + 'Div tr:last-child');
            $('#' + id + '  table').append("<tr>" + $(trObj).html() + "</tr>");
            me.reloadIndex(id)
        }

        me.delDynamicTable = function (obj) {//点击按钮删除动态表格
            obj.parent().parent().remove();
            me.reloadIndex(obj.attr('tag'));
        }

        me.reloadIndex = function (id) {//重新生成动态表格的下标

            var index = 0;
            var trindex = 0
            var trCount = $('#' + id).find('tr').length;//动态表格的行数
            var firstTr = $('#' + id + ' tr:first-child');//得到动态表格的第一行
            var trhead = $(firstTr).attr("head");//
            $('#' + id).find('tr').each(function () {//循环动态表格行
                if (trindex == 0 && typeof(trhead) != 'undefined') {//判断是否是表头行
                    //这里each不支持break、continue?
                } else {
                    var trObjEach = this;
                    $(trObjEach).find(":input").each(function () {//循环动态表格具体行里所有的组件
                        var name = $(this).attr("name");
                        if (typeof(name) == 'undefined') {
                            return;
                        }
                        if (name.indexOf('.') < 0) {
                            return;
                        }
                        var nameArr = name.split(".");
                        var nameClass = nameArr[0];
                        if (nameClass.split("[").length != 0) {//用于编辑状态下，页面加载时重新计算下标（加载进来时，下标是1开始的）
                            nameClass = nameClass.split("[")[0];
                        }
                        if (nameArr.length >= 2) {
                            name = nameClass + "[" + index + "]." + nameArr[1];
                            $(this).attr("name", name);
                        }
                        //如果有id，则只要生成不一样即可
                        var id = $(this).attr("id");
                        if (typeof(id) != 'undefined') {
                            $(this).attr("id", id + index);
                        }

                        var tag = $(this).attr("tag");
                        if (typeof(tag) != 'undefined') {
                            if (tag == "optionSort") {
                                var optionSelectedValue = $(this).val();
                                // var defaultText =$(this+' option[value=""]').text();
                                var defaultText = $(this).find(' option[value=""]').text();
                                $(this).html("");
                                $(this).append("<option value=''>" + defaultText + "</option>");
                                for (var optionIndex = 1; optionIndex < trCount + 1; optionIndex++) {
                                    var selected;
                                    if (optionIndex == optionSelectedValue) {
                                        selected = "selected";
                                    } else {
                                        selected = ""
                                    }
                                    $(this).append("<option value='" + optionIndex + "' " + selected + ">" + optionIndex + "</option>");
                                }
                            }
                        }
                    });
                    index++;
                }
                trindex++;
            })
        }

        me.reloadSelect = function (url, cId) {
            me.reloadSelect2(url, cId, null);
        }
        me.reloadSelect2 = function (url, cId, defaultOption) {
            var ajaxRequest = $.ajax({
                type: "post",
                url: url,
                cache: false,
                async: false,
                success: function (result) {
                    $('#' + cId).empty();
                    var html = '';
                    if (defaultOption != null) {
                        html = defaultOption;
                    } else {
                        html = '<option value="">请选择</option>';
                    }
                    if (result.status == true) {
                        for (var key in result.data) {//遍历Map
                            html += "<option value='" + key + "'>" + result.data[key] + "</option>";
                        }
                        $('#' + cId).html(html);
                    } else {
                        alert(result.reason);
                    }
                }
            });
            me.ajaxRequesArr.push(ajaxRequest);//临时存放ajax请求对象
        }
    }
    return {
        getInstance: function () {//子页面都是导到index.jsp，所以对象可以只初始化一次
            if (!instance) {
                instance = new Erp();
            }
            return instance;
        }
    }
})()
/*console.log(docElementArr.eq(index).is("input:checkbox"));
 console.log(docElementArr.eq(index).is("input:text"));
 console.log(docElementArr.eq(index).is("select"));
 var docElementArr = $(obj+" :input");
 var docElementCount = docElementArr.size();
 if(docElementCount>0){
 for(var index= 0;index<docElementCount;index++){

 }*/


var DocumentElementMap = {
    elment: {
        'text': 'input:text',
        'checkbox': 'input:checkbox',
        'select': 'select',
        'button': 'button',
        'radio': 'input:radio',
        'hidden': 'input:hidden'
    },
    docElementArr: {},
    docElementCount: 0,
    clear: function (contextObj) {
        this.docElementArr = $("#" + contextObj + " :input");
        this.docElementCount = this.docElementArr.size();
        for (var index = 0; index < this.docElementCount; index++) {
            var contextElement = this.docElementArr.eq(index);
            for (var e in this.elmentClear) {
                if (contextElement.is(e)) {
                    if (typeof(this.elmentClear[e]) == "function") {
                        this.elmentClear[e](contextElement);
                    }
                }
            }
        }
    },
    elmentClear: {
        'input:text': function (obj) {
            obj.val('');
        },
        'input:checkbox': function (obj) {
            obj.removeAttr("checked");
        },
        'select': function (obj) {
            obj.get(0).selectedIndex = 0;
        },
        'button': function (obj) {

        },
        'input:radio': function (obj) {
            obj.removeAttr("checked");
        },
        'input:hidden': function (obj) {

        }
    }

}

function showLoading() {
    $('body').css("overflow", "hidden")
    $("#loading_cover").show();
}
function hideLoading() {
    $('body').css("overflow", "")
    $("#loading_cover").hide();
}
(function ($) {
    String.prototype.trim = function () {
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
    String.prototype.ltrim = function () {
        return this.replace(/(^\s*)/g, "");
    }
    String.prototype.rtrim = function () {
        return this.replace(/(\s*$)/g, "");
    }
})(jQuery); 