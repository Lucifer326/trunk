
//添加校验，可以把该js添加到父窗口list中
function checkForm(form,form1) {
    //正则：匹配数字
    var number = /^[0-9]*$/;
    //正则: 匹配日期
    var date = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;

    if ($.trim(form.$("#txt").val()).length <=0){
        layer.msg("请填写救灾项目", {time:2000, icon:7, shift:1}, function () {
            form.$("#txt").focus();
        });
        return false;
    }
    if ($.trim(form.$("#txt").val()).length >16){
        layer.msg("救灾项目名称长度不能超过10位", {time:2000, icon:7, shift:1}, function () {
            form.$("#txt").focus();
        });
        return false;
    }

    if ($.trim(form.$("#callinDepartment").val()).length <= 0){
        layer.msg("请填写调入部门", {time:2000, icon:7, shift:1}, function () {
            form.$("#callinDepartment").focus();
        });
        return false;
    }
    if ($.trim(form.$("#callinDepartment").val()).length > 16){
        layer.msg("调入部门名称长度不能超过10位", {time:2000, icon:7, shift:1}, function () {
            form.$("#callinDepartment").focus();
        });
        return false;
    }

    if ($.trim(form.$("#hello").val()).length <= 0){
        layer.msg("请填写调拨日期", {time:2000, icon:7, shift:1}, function () {
            form.$("#hello").focus();
        });
        return false;
    }

    if ($.trim(form.$("#diaochubum").val()).length <= 0){
        layer.msg("请填写调出部门", {time:2000, icon:7, shift:1}, function () {
            form.$("#diaochubum").focus();
        });
        return false;
    }
    if ($.trim(form.$("#diaochubum").val()).length >16){
        layer.msg("调出部门名称长度不能超过10位", {time:2000, icon:7, shift:1}, function () {
            form.$("#diaochubum").focus();
        });
        return false;
    }
    if ($.trim(form.$("#wuziuse").val()).length <= 0){
        layer.msg("请填写物资用途", {time:2000, icon:7, shift:1}, function () {
            form.$("#wuziuse").focus();
        });
        return false;
    }
    if ($.trim(form.$("#wuziuse").val()).length > 16){
        layer.msg("物资用途长度不能超过16位", {time:2000, icon:7, shift:1}, function () {
            form.$("#wuziuse").focus();
        });
        return false;
    }

    if ($.trim(form.$("#Text4").val()).length <= 0){
        layer.msg("请填写调拨仓库", {time:2000, icon:7, shift:1}, function () {
            form.$("#Text4").focus();
        });
        return false;
    }
    if($(form1).find("input[name=mess]").val() == undefined ){
        layer.msg("请新建物资信息", {time:3000, icon:5, shift:6}, function () {});
        return false;
    }
    //return true;

    var flag = true;
    $.each($(form1).find("input[name=allotAmount]"),function(){

        if ($.trim($(this).val()).length == 0){
            layer.msg("请输入调拨数量", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }
        if ($.trim($(this).val()).length>12){
            layer.msg("调拨数量过大，请重新输入", {time:3000, icon:5, shift:6}, function () {});
            flag = false;
            return false;
        }

    });
    return flag;


}

