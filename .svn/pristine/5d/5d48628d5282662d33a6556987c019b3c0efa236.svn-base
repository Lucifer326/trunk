//表单验证
function validateForm(form){
    //正则：匹配姓名
    var contactName = /^[\u4E00-\u9FA5A-Za-z\s]+(·.[\u4E00-\u9FA5A-Za-z]+)*$/;
    //匹配电话号码
    var ph= /^(0|86|17951)?(13[0-9]|15[012356789]|17[01678]|18[0-9]|14[57])[0-9]{8}$/;
    var mb= /^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    //邮箱验证
    var emailCheck = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
    //网址验证
    var urlCheck = /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
    /**公司名称验证*/
    if($.trim(form.$("#name").val()).length == 0){
        showErrorMsg("请输入公司名称");
        return false;
    }
    if($.trim(form.$("#name").val()).length > 50){
        showErrorMsg("公司名称最大长度50位");
        return false;
    }
    /**公司简介验证*/
    if($.trim(form.$("#companyInfo").val()).length > 500){
        showErrorMsg("公司简介最大长度500位");
        return false;
    }
    /**主营产品验证*/
    if($.trim(form.$("#product").val()).length == 0){
        showErrorMsg("请输入公司主营产品");
        return false;
    }
    if($.trim(form.$("#product").val()).length > 100){
        showErrorMsg("公司主营产品最大长度100位");
        return false;
    }
    /**联系人姓名验证*/
    if($.trim(form.$("#contact").val()).length > 36){
        showErrorMsg("联系人最大长度36位");
        return false;
    }
    if(!contactName.test($.trim(form.$("#contact").val()))){
        showErrorMsg("请输入正确联系人姓名");
        return false;
    }

    /**联系电话验证*/
    if(!ph.test($.trim(form.$("#phone").val())) && !mb.test($.trim(form.$("#phone").val()))){
        showErrorMsg("请输入正确的电话号码");
        return false;
    }
    if($.trim(form.$("#phone").val()).length > 36){
        showErrorMsg("联系电话最大长度36位");
        return false;
    }
    /**邮箱验证*/
    if($.trim(form.$("#email").val()).length !==0){
        if(!emailCheck.test($.trim(form.$("#email").val()))){
            showErrorMsg("请输入正确邮箱地址");
            return false;
        }
        if($.trim(form.$("#email").val()).length > 36){
            showErrorMsg("邮箱最大长度36位");
            return false;
        }
    }

    /**验证网址*/
    if($.trim(form.$("#url").val()) !=0){
        if(!urlCheck.test($.trim(form.$("#url").val()))){
            showErrorMsg("请输入正确网址");
            return false;
        }
        if($.trim(form.$("#url").val()).length > 36){
            showErrorMsg("网址最大长度36位");
            return false;
        }
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