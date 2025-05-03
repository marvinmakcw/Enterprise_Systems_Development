<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successful</title>
    </head>
    <body>
        <%
            String msg = (String) session.getAttribute("successful");
        %>
        <%= msg%>
        <p>
            <% out.print("<a href=\"" + request.getContextPath() + "/login.jsp\">Login now</a>");%>
        </p>
    </body>
</html>
