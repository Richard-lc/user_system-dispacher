<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 2020/12/25
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>欢迎【${sessionScope.admin.username}】登录 <a href="/AdminServlet/logout">注销</a></div>
<h1>用户列表</h1>
<a href="/addUser.jsp">添加用户</a>
<table border="1px">
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>性别</th>
        <th>年龄</th>
        <th>邮箱</th>
        <th>生日</th>
        <th>操作</th>
    </tr>
    <c:forEach var="u" items="${userPage.data}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${u.sex==1?"男":"女"}</td>
            <td>${u.age}</td>
            <td>${u.email}</td>
            <td>${u.birthday}</td>
            <td>
                <a href="getUserById?id=${u.id}">编辑</a>
                <a href="deleteUserById?id=${u.id}&pageNum=${userPage.pageNum}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div>当前页：${userPage.pageNum}/${userPage.totalPage}</div>
<div>共${userPage.totalCount}条数据</div>
<a href="getUserPage">首页</a>
<c:if test="${userPage.pageNum>1}">
    <a href="getUserPage?pageNum=${userPage.pageNum-1}">上一页</a>
</c:if>
<c:if test="${userPage.pageNum<userPage.totalPage}">
    <a href="getUserPage?pageNum=${userPage.pageNum+1}">下一页</a>
</c:if>
<a href="getUserPage?pageNum=${userPage.totalPage}">尾页</a>
</body>
</html>
