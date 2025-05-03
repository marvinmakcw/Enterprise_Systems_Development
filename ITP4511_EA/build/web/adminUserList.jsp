<%-- 
    Document   : adminUserList
    Created on : 2023年4月27日, 下午07:44:06
    Author     : osacr
--%>

<%@page import="java.util.*, ict.bean.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Main Page</title>
    </head>
    <body>
       
        <%
            ArrayList<AdminList> user = (ArrayList<AdminList>) request.getAttribute("user");
            out.println("<h1>User</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>id</th><th>username</th><th>password</th><th>phone_no</th><th>role</th>");
            out.println("</tr>");
            for (int i = 0; i < user.size(); i++) {
                AdminList c = user.get(i);
                out.println("<tr>");

                out.println("<td>" + c.getId() + "</td>");
                out.println("<td>" + c.getUsername() + "</td>");
                out.println("<td>" + c.getPassword() + "</td>");
                out.println("<td>" + c.getPhone_no() + "</td>");
                out.println("<td>" + c.getRole() + "</td>");
                out.println("<td><a href=\"handleUser?action=delete&id=" + c.getId() + "\">delete</a></td>");
                out.println("<td><a href=\"handleUser?action=getEditUser&id=" + c.getId() + "\">edit</a></td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
        <br/>    
        <a href="adminPage.jsp">Back to Main page</a>
    </body>
</html>
