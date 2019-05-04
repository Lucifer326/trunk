function checkForm(form, form1) {
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    if ($.trim(form.$("#house").val()).length <= 0){
        layer.msg("请填写入库仓库", {time:2000, icon:7, shift:1}, function () {
            form.$("#house").focus();
        });
        return false;
    }
    if ($.trim(form.$("#house").val()).length > 36){
        layer.msg("入库仓库最大长度36位", {time:2000, icon:7, shift:1}, function () {
            form.$("#house").focus();
        });
        return false;
    }
    if ($.trim(form.$("#hello").val()).length <= 0){
        layer.msg("请填写入库时间", {time:2000, icon:7, shift:1}, function () {
            form.$("#hello").focus();
        });
        return false;
    }
    if(!date.test($.trim(form.$("#hello").val()))){
        layer.msg("请正确填写入库时间", {time:2000, icon:7, shift:1}, function () {
            form.$("#hello").focus();
        });
        return false;
    }
    if ($.trim(form.$("#Text2").val()).length <= 0){
        layer.msg("请填写入库人", {time:2000, icon:7, shift:1}, function () {
            form.$("#Text2").focus();
        });
        return false;
    }
    if ($.trim(form.$("#Text2").val()).length > 100){
        layer.msg("入库人最大长度18位", {time:2000, icon:7, shift:1}, function () {
            form.$("#Text2").focus();
        });
        return false;
    }
    if ($.trim(form.$("#outHouseNumber").val()).length <= 0){
        layer.msg("请选择出库单", {time:2000, icon:7, shift:1}, function () {
            form.$("#outHouseNumber").focus();
        });
        return false;
    }
    if($(form1).find("input[name=backNum]") == undefined || $(form1).find("input[name=backNum]").length == 0){
        layer.msg("请新建物资信息", {time:3000, icon:7, shift:1}, function () {});
        return false;
    }
    var flag = true;
    $.each($(form1).find("input[name=backNum]"),function(){
        if ($.trim($(this).parent().parent().find("input[name=subName]").val()).length == 0){
            layer.msg("请选择物资信息", {time:3000, icon:7, shift:1}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()) <= 0){
            layer.msg("请输入大于0的回收数量", {time:3000, icon:7, shift:1}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()).length == 0){
            layer.msg("请输入回收数量", {time:3000, icon:7, shift:1}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()).length >= 18){
            layer.msg("回收数量最大长度18位数字", {time:3000, icon:7, shift:1}, function () {});
            flag = false;
            return false;
        }
    });
    return flag;
}