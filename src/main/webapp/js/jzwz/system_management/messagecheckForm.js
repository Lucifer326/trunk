//表单验证
function validateProtocalForm(form){
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    //匹配小数
    var float = /^([0-9]{1,}[.][0-9]*)$/;
    var reg = /^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^([^0][0-9]+|0)$/;10
    if($.trim(form.$("#subName").val()).length == 0){
        layer.msg("请输入物资品名！", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#subName").val()).length > 10){
        layer.msg("物资品名最大长度10位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#txt1").val()).length == 0){
        layer.msg("请选择物资类别", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#specificationType").val()).length == 0){
        layer.msg("请输入规格型号", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#specificationType").val()).length > 8){
        layer.msg("规格型号最大长度8位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#isrecycle").val()).length == 0){
        layer.msg("请选择是否可回收", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#supplier").val()).length == 0){
        layer.msg("请输入供应商", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#supplier").val()).length > 15){
        layer.msg("供应商最大长度15位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#unit").val()).length == 0){
        layer.msg("请选择物资单位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#price").val()).length == 0){
        layer.msg("请输入单价", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if(!number.test($.trim(form.$("#price").val()))){
        layer.msg("单价请输入整数数字", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#price").val()).length > 8){
        layer.msg("单价最大长度8位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if($.trim(form.$("#remark").val()).length > 50){
        layer.msg("备注信息最大长度50位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    return true;
}
//显示错误信息
function showErrorMsg(msg){
    swal({
        title: msg,
        zIndex: 19891014,
        type: "warning",
        confirmButtonText: "确定"
    });
}
