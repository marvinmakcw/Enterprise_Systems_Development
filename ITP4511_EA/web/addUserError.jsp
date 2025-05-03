<%-- 
    Document   : addUserError
    Created on : 2023年4月27日, 上午01:23:40
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User Error</title>
    </head>
    <body>
        <%
            String msg = (String) session.getAttribute("error");
        %>
        <%= msg%>
        <p style="color:red">
            <% out.print("<a href=\"" + request.getContextPath() + "/adminAddUser.jsp\">Try again</a>");%>
        </p>
    </body>
</html>
