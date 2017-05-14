<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${contextPath}/ssm/jquery/jquery.js"></script>
<title>Insert title here</title>
<script type="text/javascript">


$(document).on("click",".btn",function(){
	 
	 clickBtn(this); 
});

 function clickBtn(object){
	
	 $("<button></button").addClass("btn").append(Math.random()).appendTo($("body"));
 }
	 $(function(){
		 
	/* 	 $("div:first").click(function(){
			 
			 alert($(this).text());
		 }); */
		 $(".container .box:first").click(function(){
			 
			  alert($(".box").parents("#s").text());
			  /* alert($(".box").parents().html()); */
		 });
		 
	 });
 
</script>
<style type="text/css">
.container{
margin-top:20px;
 width: 50px;
 height: 50px;
 background: black;
}
 .box{
 width:20px;
 height:20px;
 background:red;
 
 }
.container1{
 width: 50px;
 height: 50px;
 background: red;
}
.container2{
 width: 50px;
 height: 50px;
 background: blue;
}
.container3{
 width: 50px;
 height: 50px;
 background: black;
}

</style>
</head>
<body>
  <form action="emp-test" mthod="post">
   <input type="radio" value="1" name="sex" />男
   <input type="radio" value="0" name="sex" />女
   <input type="checkbox" value="4" name="grade" />班级
   <input type="checkbox" value="5" name="grade" />班级
   <input type="checkbox" value="6" name="grade" />班级
   <select name="select">
    <option value="7">选择1</option>
    <option value="8">选择2</option>
    <option value="9">选择3</option>
    
   </select>
   <input type="submit" value="提交"/>
  </form>
  
  <button class="btn" ><p>jj</p></button>
  <div id="s">
  <div class="container">
   <div class="box">1</div>
   <div class="box">2</div>
   container
  </div>
  123
  </div>
  <div class="container1">
  container1
  </div>
  <div class="container2">
  container2
  </div>
  <div class="container3">
  container3
  </div>
</body>
</html>