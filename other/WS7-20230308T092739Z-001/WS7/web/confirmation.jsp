<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, ict.bean.*" %>

<%
    CustomerBean c = (CustomerBean) request.getAttribute("customer");
    
    String type = (String) request.getAttribute("action");
    String url = type.equalsIgnoreCase("edit") ? "handleEdit" : "handleCustomer";
    String id = type.equalsIgnoreCase("edit") ? request.getParameter("id") : c.getCustId();
    String name = type.equalsIgnoreCase("edit") ? request.getParameter("name") : c.getName();
    String tel = type.equalsIgnoreCase("edit") ? request.getParameter("tel") : c.getTel();
    int age = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("age")) : c.getAge();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <form action="<%= url %>" method="get">
            <input type="hidden" name="action" value="<%= type %>" />
            <input type="hidden" name="confirm" value="true" />
            <h2><span style="text-transform: uppercase"><%= type %></span> Customer:</h2>
            <h3>Are you sure to make the following changes?</h3>
            <table border="1">
                <tr>
                    <th>Cust ID</th>
                    <th>Name</th> 
                    <th>Tel</th> 
                    <th>Age</th>
                </tr>
                <tr>
                    <td><input type="text" name="id" value="<%= id %>" readonly /></td>
                    <td><input type="text" name="name" value="<%= name %>" readonly /></td>
                    <td><input type="text" name="tel" value="<%= tel %>" readonly /></td> 
                    <td><input type="text" name="age" value="<%= age %>" readonly /></td>
                </tr>
            </table><br/>
            <input type="submit" value="Continue" />
            <button><a href="handleCustomer?action=list" style="text-decoration: none; color: black">Return</a></button>
        </form>
    </body>
</html>
