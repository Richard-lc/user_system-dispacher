<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 2020/12/25
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <style>
        tr {
            height: 40px;
        }
    </style>
</head>
<body>
<h1>LOGIN</h1>
<form method="post" action="/AdminServlet/login">
    <table>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" value="admin">
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="password" value="123456">
            </td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td>
                <input type="text" name="code"><img src="CodeServlet?action=getCode">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">登录</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
