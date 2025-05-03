<%-- 
    Document   : adminConfirmEdit
    Created on : 2023年4月27日, 下午07:45:29
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, ict.bean.*" %>

<%
    AdminList c = (AdminList) request.getAttribute("user");
    
    String type = (String) request.getAttribute("action");
    String url = type.equalsIgnoreCase("edit") ? "handleEdit" : "handleUser";
    int id = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("id")) : c.getId();
    String username = type.equalsIgnoreCase("edit") ? request.getParameter("username") : c.getUsername();
    String password = type.equalsIgnoreCase("edit") ? request.getParameter("password") : c.getPassword();
    String role = type.equalsIgnoreCase("edit") ? request.getParameter("role") : c.getRole();
    int phone_no = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("phone_no")) : c.getPhone_no();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
    </head>
    <body>
        <form action="<%= url %>" method="get">
            <input type="hidden" name="action" value="<%= type %>" />
            <input type="hidden" name="confirm" value="true" />
            <h2><span style="text-transform: uppercase"><%= type %></span> User:</h2>
            <h3>Are you sure to make the following changes?</h3>
            <table border="1">
                <tr>
                    <th>User ID</th>
                    <th>Userame</th> 
                    <th>Password</th> 
                    <th>Phone_no</th> 
                    <th>Role</th>
                </tr>
                <tr>
                    <td><input type="text" name="id" value="<%= id %>" readonly /></td>
                    <td><input type="text" name="username" value="<%= username %>" readonly /></td>
                    <td><input type="text" name="password" value="<%= password %>" readonly /></td> 
                    <td><input type="text" name="phone_no" value="<%= phone_no %>" readonly /></td>
                    <td><input type="text" name="role" value="<%= role %>" readonly /></td>
                </tr>
            </table><br/>
            <input type="submit" value="Confirm" />
            <button><a href="handleUser?action=list" style="text-decoration: none; color: black">Return</a></button>
        </form>
    </body>
</html>
