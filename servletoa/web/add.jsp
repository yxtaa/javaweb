<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增</title>
</head>
<body>
    <h3>欢迎，${userName}</h3>
    <h1>新增</h1>
    <form action="${pageContext.request.contextPath}/dept/add" method="post">
        编号 <input type="text" name="dno"/><BR>
        名称 <input type="text" name="loginName"><br>
        位置 <input type="text" name="realName"><br>
        <input type="submit" value="保存"><br>
    </form>
</body>
</html>
