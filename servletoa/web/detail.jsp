
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
</head>
<body>
<h3 align="center">用户发送servlet请求，servlet操作数据库，响应发送重定向对象，浏览器重新访问</h3>
<form action="${pageContext.request.contextPath}/dept/edit" method="post">
    序号：<input type="text" name="dno" value="${dept.id}"><br>
    编号：<input type="text" name="loginName" value="${dept.loginName}"><br>
    名称：<input type="text" name="realName" value="${dept.realName}"><br>
    <input type="submit" value="修改">
</form>

</body>
</html>
