<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPL Booking System</title>
    </head>
    <body>
        <b>
            Hello, <jsp:getProperty name="userInfo" property="username" /> (<jsp:getProperty name="userInfo" property="role" />)
        </b>
        <ul>
            <li><a href="staffAddVenue.jsp">Add Venue</a></li>
            <li><a href="handleVenue?action=list">Venue List</a></li>
        </ul>
        <p>Welcome to the EPL Booking System</p>
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" name="logoutButton">
        </form>
        <hr>
    </body>
</html>
