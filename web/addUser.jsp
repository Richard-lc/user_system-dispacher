<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 2020/12/25
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<h1>添加用户</h1>
<form method="post" action="UserServlet/addUser">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="李四"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value="123"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="radio" value="1" name="sex">男
                <input type="radio" value="2" name="sex">女
            </td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><input type="number" name="age" value="18"></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="text" name="email" value="qf@123.com"></td>
        </tr>
        <tr>
            <td>生日：</td>
            <td><input type="date" name="birthday" value="2013-02-06"></td>
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
