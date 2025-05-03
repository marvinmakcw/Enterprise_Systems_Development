<%-- 
    Document   : loginError
    Created on : 2023年3月2日, 下午03:48:50
    Author     : zengjuncai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>InCorrect Password</p>
        <p>
            <% out.print("<a href=\"" + request.getContextPath() + "/main\">Login again</a>");%>
        </p>
    </body>
</html>
