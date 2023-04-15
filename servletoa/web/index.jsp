<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/11
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>

<!DOCTYPE html>
<html>
  <head>
    <title>oa主页</title>
  </head>
  <body>
  <a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>
    <h1>用户登录</h1>
  <form action="<%=request.getContextPath()%>/user/login" method="post">
      用户名称：<input type="text" name="userName"><br>
      用户密码：<input type="text" name="passWord"><br>
      <input type="submit" value="登录">
  </form>
  </body>
</html>
