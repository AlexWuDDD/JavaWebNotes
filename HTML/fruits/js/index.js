function $(id){
  return document.getElementById(id);
}

function trBindEvent(tr){
    //1、绑定鼠标悬浮设置背景颜色事件
    tr.onmouseover = showBGColor;
    tr.onmouseout = clearBGColor;

    var cells = tr.cells;
    var priceTD = cells[1];
    //2.绑定鼠标悬浮在单价单元格变手势的事件
    priceTD.onmouseover = showHand;
    //3.绑定鼠标点击单价单元格的事件
    priceTD.onclick = editPrice;

    //绑定删除小图标的点击事件
    var img = cells[4].firstChild;
    if(img && img.tagName == "IMG"){
      //绑定单击事件
      img.onclick=delFruit;
    }
}

window.onload=function(){
  //当页面加载完成，我们需要绑定各种事件
  updateZJ();
  var fruitTbl = $("tbl_fruit");
  //获取表格中的所有行
  var rows = fruitTbl.rows;
  // alert(rows.length)
  for(var i = 1; i < rows.length-1; ++i){
    var tr = rows[i];
    trBindEvent(tr);
  }
   
  $("btnadd").onclick=addFruit;
}

function addFruit(){
  var fname = $("fname").value;
  var fprice = parseInt($("fprice").value);
  var fcount = parseInt($("fcount").value);
  var fxj = fprice * fcount;

  var fruitTbl = $("tbl_fruit");
  var tr = fruitTbl.insertRow(fruitTbl.rows.length-1);
  var fnameTD = tr.insertCell();
  fnameTD.innerText = fname;
  var fpriceTD = tr.insertCell();
  fpriceTD.innerText= fprice;
  var fcountTD = tr.insertCell();
  fcountTD.innerText = fcount;
  var fxjTD = tr.insertCell();
  fxjTD.innerText = fxj;
  var fdelTD = tr.insertCell();
  fdelTD.innerHTML = "<img src='img/del.webp' class='delImg' />";

  updateZJ();
  trBindEvent(tr);
}


function delFruit(){
  if(event && event.srcElement && event.srcElement.tagName=="IMG"){
    //alert表示弹出一个对话框，只有确定按钮
    //confirm表示弹出一个对话框，有确定和取消按钮
    if(!window.confirm("是否确认删除当前行？")){
      return;
    }
    var img = event.srcElement;
    var tr = img.parentElement.parentElement;
    var fruitTbl = $("tbl_fruit");
    fruitTbl.deleteRow(tr.rowIndex);

    updateZJ();
  }
}

//当鼠标点击单价单元格时进行价格编辑
function editPrice(){
  //event 当前发生的事件
  if(event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    //目的是判断当前的td有子节点，而且第一个子节点是文本节点，TextNode对应的是3 ElementNode对应的是1
    if(td.firstChild && td.firstChild.nodeType == 3){
      //innerText 表示设置或者获取当前节点的内部文本
      var oldPrice = td.innerText;
      //innerHTML表示设置当前节点的内部HTML
      td.innerHTML="<input type='text' size='4' />";
      var input = td.firstChild;
      if(input.tagName=="INPUT"){
        input.value = oldPrice;
        //选中输入框内部的文本
        input.select();
        //4. 绑定输入框失去焦点事件，失去焦点更新单价
        input.onblur = updatePrice;
        //在输入框上绑定键盘摁下的事件，此处我需要能保证用户输入的是数字
        input.onkeydown = checkInput
      }
    }
  }
}

function checkInput(){
  var kc = event.keyCode;
  //0~9: 48~57
  //backspace: 8
  //enter: 13
  // console.log(keyCode);
  if(!((kc >=48 && kc<=57) || kc == 8 || kc == 13)){
    event.returnValue = false;
  }
  if(kc == 13){
    event.srcElement.blur();
  }
}

function updatePrice(){
    //event 当前发生的事件
    if(event && event.srcElement && event.srcElement.tagName=="INPUT"){
      var input = event.srcElement;
      var newPrice = input.value;
      //input节点的父节点时td
      var priceTD = input.parentElement;
      priceTD.innerText = newPrice;

      //更新当前行的小计这一个格子的值
      //priceTD.parentElement td的父元素是tr
      updateXJ(priceTD.parentElement);
    }
}

//更新指定行的小计
function updateXJ(tr){
  if(tr && tr.tagName=="TR"){
    var tds = tr.cells;
    var price = tds[1].innerText;
    var count = tds[2].innerText;

    //inenrText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
    var xj = parseInt(price)*parseInt(count);
    tds[3].innerText = xj;

    //更新总计
    updateZJ();
  }
}

function updateZJ(){
  var fruitTbl = $("tbl_fruit");
  var rows = fruitTbl.rows;
  var zj = 0;
  for(var i = 1; i < rows.length-1; ++i){
    var tr = rows[i];
    var tds = tr.cells;
    var xj = tds[3].innerText;
    zj += parseInt(xj);
  }
  var zjTd = rows[rows.length-1].cells[1];
  zjTd.innerText = zj;
}

function showBGColor(){
  //event 当前发生的事件
  if(event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    var tr = td.parentElement;
    tr.style.backgroundColor = "navy";
    var tds = tr.cells
    for(var i = 0; i< tds.length; ++i){
      tds[i].style.color="white";
    }
  }
}

function clearBGColor(){
  //event 当前发生的事件
  if(event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    var tr = td.parentElement;
    tr.style.backgroundColor = "transparent";
    var tds = tr.cells
    for(var i = 0; i< tds.length; ++i){
      tds[i].style.color="threeddarkshadow";
    }
  }
}

//当鼠标悬浮在单价单元格
function showHand(){
  if(event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    td.style.cursor="hand";
  }
}
