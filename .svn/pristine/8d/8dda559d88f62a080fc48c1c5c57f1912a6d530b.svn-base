//表单验证
function validateProtocalForm(form){
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    //正则：保留二位小数
    var year =  /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/;
    if($.trim(form.$("#hello1").val()).length == 0){
        showErrorMsg("请输入协议签订日期");
        return false;
    }
    if(!date.test($.trim(form.$("#hello1").val()))){
        showErrorMsg("请选择正确日期");
        return false;
    }
    if($.trim(form.$("#txtin").val()).length == 0){
        showErrorMsg("请选择协议储备物资");
        return false;
    }
    if($.trim(form.$("#txtin").val()).length > 100){
        showErrorMsg("协议储备物资最大长度100位");
        return false;
    }
    if($.trim(form.$("#txtTypee").val()).length == 0){
        showErrorMsg("请输入物资规格");
        return false;
    }
    if($.trim(form.$("#txtTypee").val()).length > 100){
        showErrorMsg("物资规格最大长度100位");
        return false;
    }
    if($.trim(form.$("#txtWork").val()).length == 0){
        showErrorMsg("请输入协议储备数量");
        return false;
    }
    if(!number.test($.trim(form.$("#txtWork").val()))){
        showErrorMsg("协议储备数量请输入数字");
        return false;
    }
    if($.trim(form.$("#txtWork").val()).length > 10){
        showErrorMsg("协议储备数量最大长度10位");
        return false;
    }
    if($.trim(form.$("#protocolYear").val()).length > 36){
        showErrorMsg("协议年限最大长度36位");
        return false;
    }
    if(!year.test($.trim(form.$("#protocolYear").val()))){
        showErrorMsg("请输入正确协议年限");
        return false;
    }
    if($.trim(form.$("#Text1").val()).length > 18){
        showErrorMsg("协议单价最大长度18位");
        return false;
    }
    if($.trim(form.$("#Text1").val()).length == 0){
        showErrorMsg("请输入协议单价");
        return false;
    }
    if($.trim(form.$("#txt1").val()).length == 0){
        showErrorMsg("请输入协议公司");
        return false;
    }
    if($.trim(form.$("#txt1").val()).length > 100){
        showErrorMsg("协议公司最大长度100位");
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
function checkForm(form) {
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    if ($.trim(form.$("#house").val()).length <= 0){
        layer.msg("请填写入库仓库", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(form.$("#house").val()).length > 36){
        layer.msg("入库仓库最大长度36位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(form.$("#hello").val()).length <= 0){
        layer.msg("请填写入库时间", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if(!date.test($.trim(form.$("#hello").val()))){
        layer.msg("请正确填写入库时间", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(form.$("#Text2").val()).length <= 0){
        layer.msg("请填写入库人", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    if ($.trim(form.$("#Text2").val()).length > 100){
        layer.msg("入库人最大长度100位", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    return true;
}