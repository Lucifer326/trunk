//物资出库的表单验证
function outHouseCheckForm(content_window,form) {
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    if ($.trim(content_window.$("#outhouseNumber").val()).length == 0){
        layer.msg("请填写出库仓库", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#outhousePerson").val()).length == 0 ){
        layer.msg("请填写出库人姓名", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#outhousePerson").val()).length >= 6){
        layer.msg("出库人姓名不得超过6个字符", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#hello").val()).length == 0){
        layer.msg("请填出库日期", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#receiver").val()).length == 0){
        layer.msg("请填写领取人", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#Text1").val()).length == 0){
        layer.msg("请选择调拨单", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#txt").val()).length == 0){
        layer.msg("请选择出库仓库", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#remark").val()).length > 120){
        layer.msg("备注信息不得超过120个字符", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($(form).find("input[name=outhouseAmount]") == undefined || $(form).find("input[name=outhouseAmount]").length == 0){
        layer.msg("请新建物资信息", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    var flag = true;
    $.each($(form).find("input[name=outhouseAmount]"),function(){
        if ($.trim($(this).parent().parent().find("input[name=storageNumber]").val()).length == 0){
            layer.msg("请选择入库单号", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if (parseInt($(this).val()) <= 0){
            layer.msg("请选择库存数大于0的入库单", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()).length == 0){
            layer.msg("请输入出库数量", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if (!number.test($.trim($(this).val()))){
            layer.msg("出库数量请输入数字", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if (parseInt($(this).val()) > parseInt($(this).parent().parent().find("input[name=amount]").val())){
            layer.msg("库存不足，请重新输入出库数量或选择库存数量充足的入库单", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
    });
    return flag;
}
function outHouseDetailCheckForm(content_window,form) {
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    if ($.trim(content_window.$("#txt").val()).length == 0){
        layer.msg("请填写出库仓库", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#outhousePerson").val()).length == 0 ){
        layer.msg("请填写出库人姓名", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#outhousePerson").val()).length >= 6){
        layer.msg("出库人姓名不得超过6个字符", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#hello").val()).length == 0){
        layer.msg("请填出库日期", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#receiver").val()).length == 0){
        layer.msg("请填写领取人", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#Text4").val()).length == 0){
        layer.msg("请选择调拨单", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#txt").val()).length == 0){
        layer.msg("请选择出库仓库", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(content_window.$("#remark").val()).length > 120){
        layer.msg("备注信息不得超过120个字符", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }

    if($(form).find("input[name=outhouseAmount]") == undefined || $(form).find("input[name=outhouseAmount]").length == 0){
        layer.msg("请新建物资信息", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    var flag = true;
    $.each($(form).find("input[name=outhouseAmount]"),function(){
        if ($.trim($(this).parent().parent().find("input[name=storageNumber]").val()).length == 0){
            layer.msg("请选择入库单号", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if (parseInt($(this).val()) <= 0){
            layer.msg("请选择库存数大于0的入库单", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()).length == 0){
            layer.msg("请输入出库数量", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if (!number.test($.trim($(this).val()))){
            layer.msg("出库数量请输入数字", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
       if (parseInt($(this).val()) > parseInt($(this).parent().parent().find("input[name=amount]").val())){
           layer.msg("库存不足，请重新输入出库数量或选择库存数量充足的入库单", {time:3000, icon:5, shift:6}, function () {});
           flag = false;
           return false;
       }
    });
    return flag;
}