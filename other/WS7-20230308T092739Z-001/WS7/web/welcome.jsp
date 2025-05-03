<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Welcome to the ICT</h3>
        <ul>
            <li><a href="editCustomer.jsp">Add Customer</a></li>
            <li><a href="handleCustomer?action=list">List Customer</a></li>
            <li><form action="handleCustomer?action=search" method="get">
                    <input type="hidden" name="action" value="search" />
                    Search Customer: <br/>
                    <input type="text" name="name" value=""/>
                    <input type="submit" value="submit" />
                </form></li>
        </ul>
        <br/><input type="submit" value="Logout" name="logoutButton">

    </body>
</html>
