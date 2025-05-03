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
            Hello, <jsp:getProperty name="userInfo" property="username" /> (<jsp:getProperty name="userInfo" property="role" />) (<jsp:getProperty name="userInfo" property="id" />)
        </b>
        <p>Welcome to the EPL Booking System</p>
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" name="logoutButton">
        </form>
        <hr>
        <a href="VenueController?action=list">Venue Booking</a><br/>        
        <%
            
            int id = userInfo.getId();
        %>
        <a href="GuestListController?action=list&memberId=<%=id%>">Guest List Management</a><br/>
        <a href="BookingController?action=list&memberId=<%=id%>">Check Personal Booking Records</a>
    </body>
</html>
