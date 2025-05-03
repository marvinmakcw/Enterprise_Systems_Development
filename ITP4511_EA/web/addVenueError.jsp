<%-- 
    Document   : addVenueError
    Created on : 2023年4月28日, 上午07:44:11
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
        <%
            String msg = (String) session.getAttribute("error");
        %>
        <%= msg%>
        <p style="color:red">
            <% out.print("<a href=\"" + request.getContextPath() + "/staffAddVenue.jsp\">Try again</a>");%>
        </p>
    </body>
</html>
