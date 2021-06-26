function setSidebarActive(th){
    $(th).parent("li").siblings().removeClass("active");
    $(th).parent("li").addClass("active");
}
/*只能选择一个单选框且id不能为空则返回该对象的id属性值,如老王1的id"1"*/
function getCheckId() {
    var size = $("input:checkbox:checked").length;
    if(size!=1) {
        return ;
    }else {
        return $('input[type=checkbox]:checked').val();/*选中的单选框的value属性值*/
    }
}

function formSubmit (url,sTarget){
    document.forms[0].target = sTarget
    document.forms[0].action = url;
    document.forms[0].submit();
    return true;
}