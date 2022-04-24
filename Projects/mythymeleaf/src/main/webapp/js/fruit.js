function delFruit(fid){
    if(confirm("是否确认删除?")){
        window.location.href="del.do?fid="+fid;
    }
}

function page(pageNo){
    if(pageNo <=0){
        pageNo = 1;
    }
    window.location.href="fruit?pageNo=" + pageNo;
}