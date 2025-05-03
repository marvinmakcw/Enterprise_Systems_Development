<%-- 
    Document   : welcome
    Created on : 2023年3月2日, 下午03:49:03
    Author     : zengjuncai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <b>
            Hello, <jsp:getProperty name="userInfo" property="username" />
        </b>
        <p>Welcome to the ICT</p>
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" name="logoutButton">
        </form>
        <hr>
        <a href="BrandController?action=list">getAllBrands</a><br />
    </body>
</html>
