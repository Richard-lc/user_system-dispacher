<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 2020/12/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<h1>修改用户信息</h1>
<form method="post" action="UserServlet/updateUser">
    <table>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="hidden" name="id" value="${requestScope.user.id}">
                <input type="text" name="username" value="${requestScope.user.username}">
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value="${requestScope.user.password}"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.user.sex==1}">
                        <input type="radio" value="1" name="sex" checked="checked">男
                        <input type="radio" value="2" name="sex">女
                    </c:when>
                    <c:when test="${requestScope.user.sex==2}">
                        <input type="radio" value="1" name="sex">男
                        <input type="radio" value="2" name="sex" checked="checked">女
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><input type="number" name="age" value="${requestScope.user.age}"></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="text" name="email" value="${requestScope.user.email}"></td>
        </tr>
        <tr>
            <td>生日：</td>
            <td><input type="date" name="birthday" value="${requestScope.user.birthday}"></td>
        </tr>
        <tr>
            <td>
                <button type="submit">提交</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
