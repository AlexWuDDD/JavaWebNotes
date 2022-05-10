function $(id){
    return document.getElementById(id);
}


function preRegist(){

    //DOM
    // let uname = document.getElementById("unameTxt");
    //BOM
    // document.forms[0].uname;

    let unameTxt = $("unameTxt");
    let unameSpan = $("unameSpan");
    //用户名应为6~16位数组和字母组成
    let unameReg = /[0-9a-zA-z]{6,16}/;
    let uname = unameTxt.value;
    if(!unameReg.test(uname)){
        unameSpan.style.visibility = "visible";
        return false;
    }
    else{
        unameSpan.style.visibility = "hidden";
    }

    //密码的长度至少为8位
    let pwdTxt = $("pwdTxt");
    let pwdSpan = $("pwdSpan");

    let pwdReg = /[0-9a-zA-Z]{8,}/;
    let pwd = pwdTxt.value;
    if(!pwdReg.test(pwd)){
        pwdSpan.style.visibility = "visible";
        return false;
    }
    else{
        pwdSpan.style.visibility = "hidden";
    }

    //密码两次输入不一致
    let pwdTxt2 = $("pwdTxt2");
    let pwdSpan2 = $("pwdSpan2");

    if( pwdTxt.value != pwdTxt2.value){
        pwdSpan2.style.visibility = "visible";
        return false;
    }
    else{
        pwdSpan2.style.visibility = "hidden";
    }

    //请输入正确的邮箱格式
    let emailTxt = $(emailTxt);
    let emailSpan = $(emailSpan);

    let emailReg = /^([A-Za-z0-9_\-\.\u4e00-\u9fa5])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,8})$/;
    if(!emailReg.test(emailTxt.value)){
        emailSpan.style.visibility = "visible";
        return false;
    }
    else{
        emailSpan.style.visibility = "hidden";
    }

    return true;
}


var xmlHttpRequest;
//如果想要发送异步请求，我们需要一个关键的对象 XMLHttpRequest

function createXMLHttpRequest(){
    if(window.XMLHttpRequest){
        //符合DOM2标准的浏览器，xmlHttpRequest
        xmlHttpRequest = new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        //IE
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(e){
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
}

function ckUname(uname){
    createXMLHttpRequest();
    xmlHttpRequest.open('GET',"user.do?operate=ckUname&uname="+uname,true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
        //xmlHttpRequest.responseText 表示 服务器端响应给我的文字内容
        let reponseText =  xmlHttpRequest.responseText;
        //{'uname': '1'}
        if(reponseText == "{'uname':'1'}"){
            alert("用户名已经被注册");
        }
        else{
            alert("用户名可以被注册");
        }
    }

}