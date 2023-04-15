<%@ page import="com.yxt.oa.utils.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yxt.oa.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
    <title>部门列表</title>
</head>

<body>
<script type="text/javascript">
    function del(dno) {
        var ok = window.confirm("是否删除？");
        if (ok) {
            document.location.href = "<%=request.getContextPath()%>/dept/delete?dno=" + dno;
        }
    }
</script>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%" cellspacing="0">
    <tr>
        <th>dno</th>
        <th>loginName</th>
        <th>realName</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${deptlist}" var="v" varStatus="status">
        <tr>
            <td>${v.id}</td>
            <td>${v.loginName}</td>
            <td>${v.realName}</td>
            <td>
                <a href="javascript:void(0)" onclick="del(${v.id})">删除</a>
                <a href="${pageContext.request.contextPath}/dept/detail?dno=${v.id}">修改</a>
                <a href="${pageContext.request.contextPath}/dept/detail?dno=${v.id}">详情</a>
            </td>
        </tr>
    </c:forEach>






</table>
<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增</a><br>
<%=request.getSession() + "111"%>
<HR>

</body>
</html>
