<%-- 
    Document   : adminPage
    Created on : 2023年4月15日, 下午04:18:45
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to EPL Booking System</h1>
        <b>
            Hello, <jsp:getProperty name="userInfo" property="username" /> (<jsp:getProperty name="userInfo" property="role" />)
        </b>
        <ul>
            <li><a href="adminAddUser.jsp">Add User</a></li>
            <li><a href="handleUser?action=list">User List</a></li>
        </ul>
        <br>
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" name="logoutButton">
        </form>
    </body> 
</html>
