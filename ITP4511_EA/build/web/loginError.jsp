<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incorrect Password</title>
    </head>
    <body>
        <p>Incorrect Password</p>
        <p>
            <% out.print("<a href=\"" + request.getContextPath() + "/main\">Login again</a>");%>
        </p>
    </body>
</html>
