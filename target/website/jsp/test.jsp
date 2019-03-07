<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/9
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/article/addArticle" method="post" enctype="multipart/form-data" id="form1">
    <input type="file" name="articleCoverPhoto">
    <input type="file" name="articleEnclosure">
    <button id="bb">提交</button>
</form>
<input type="text" id = "test" />
<input type="button" value="提交5" id="btn"/>

<form action="/article/addFileTest" method="post">
    <button type="submit">查看权限</button>
</form>
<img src="http://192.168.1.113:8080/virtualFiles/pic2/wlw-s.jpg">
<form id="ff" enctype="multipart/form-data">
    <input type="file" name="files"/>
</form>
文件1:<input type="file" name="files" id="f1"/><br>
文件2:<input type="file" name="files" id="f2"/><br>

<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $("#btn").on("click",function(){
        alert("wochulaile")
        $.ajax({
            url: '/user/deleteUser',
            type: 'POST',
            data: JSON.stringify(3),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            success: show,
        });
        function show(data){
            console.log(data.name);
        }
    })
    $("#bb").on("click",function(){
        var formData = new FormData($('#form1')[0]);
        $.ajax({
            url: 'http://127.0.0.1:8080/article/addArticle',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: show,
        });
        function show(data){
            alert("成功");
        }
    })
</script>
</body>
</html>
