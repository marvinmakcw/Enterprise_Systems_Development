<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <%
            String msg = (String) session.getAttribute("error");
        %>
        <%= msg%>
        <p>
            <% out.print("<a href=\"" + request.getContextPath() + "/signup.jsp\">Sign up again</a>");%>
        </p>
    </body>
</html>
