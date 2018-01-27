<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>

	<form action="save.do" method="post">
		ID: <input type="text" name="goodsId"/> <br/>
		NAME:<input type="text" name="goodsName"/> <br/>
		PRICE:<input type="text" name="goodsPrice"/> <br/>
		<input type="submit" value="添加一个商品"/>
	</form>
	
	<form action="update.do" method="post">
		ID: <input type="text" name="goodsId"/> <br/>
		NAME:<input type="text" name="goodsName"/> <br/>
		PRICE:<input type="text" name="goodsPrice"/> <br/>
		<input type="submit" value="更新一个商品"/>
	</form>
	
	<form action="delete.do" method="post">
		ID: <input type="text" name="goodsId"/> <br/>
		<input type="submit" value="删除一个商品"/>
	</form>
	
	<form action="selectById.do" method="post">
		ID: <input type="text" name="goodsId" id="gi"/> <br/>
		<input type="button" value="查询一个商品" onclick="selectById()"/>
	</form>
	
	<form action="selectAll.do" method="post">
		<input type="button" value="查询所有商品" onclick="selectAll(1,3)"/>
	</form>
	
	<div id="box">
	</div>
	<div>
		<a href="#" onclick="toPrePage()">上一页</a>
		<a href="#" onclick="toNextPage()">下一页</a>
	</div>
<script>
function selectById(){
	var goodsId = document.getElementById("gi");
	var xmlHttp = null;
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else{
		//专门是针对IE6
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.status == 200 && xmlHttp.readyState == 4){
			var box = document.getElementById("box");
			box.innerHTML="";
			var goods = eval("("+xmlHttp.responseText +")");
			box.innerHTML += "ID="+goods["goodsId"] +
		",NAME="+goods["goodsName"]+",PRICE="+goods["goodsPrice"];
		}
	}
  xmlHttp.open("get","selectById.do?goodsId="+goodsId.value,true);
  xmlHttp.send();
}

//接受全局的pageGoods->
var pageGoods="";

function selectAll(page,pageSize){
	var xmlHttp = null;
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.status == 200 && xmlHttp.readyState == 4){
			var box = document.getElementById("box");
			box.innerHTML="";
			var map = eval("("+xmlHttp.responseText +")");
			pageGoods = map.pageGoods;
			var goods = pageGoods.list; //分页后商品集合
			for(var i=0;i<goods.length;i++)
			{
				box.innerHTML += "ID="+goods[i]["goodsId"] +
				",NAME="+goods[i]["goodsName"]+",PRICE="
				+goods[i]["goodsPrice"]+"<br/>";
			}
		}
	}
	xmlHttp.open("get", "selectAll.do?page="+page+"&pageSize="+pageSize, true);
	xmlHttp.send();
}

function toPrePage(){
	if(pageGoods.hasPreviousPage==true)
		selectAll(pageGoods.prePage,3)
}

function toNextPage(){
	if(pageGoods.hasNextPage==true)
		selectAll(pageGoods.nextPage,3);
}

</script>
</body>
</html>