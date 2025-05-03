<%-- 
    Document   : adminEdit
    Created on : 2023年4月27日, 下午07:49:40
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Information</title>
    </head>
    <body>
        <jsp:useBean id="c" scope="request" class="ict.bean.AdminList" />
        <% 
            String type = Integer.toString(c.getId()) != null ? "edit" : "add";
            String id = Integer.toString(c.getId()) != null ? Integer.toString(c.getId()): "" ;
            String username = c.getUsername() != null ? c.getUsername(): "" ;
            String password = c.getPassword() != null ? c.getPassword(): "" ;
            String phone_no = Integer.toString(c.getPhone_no()) != null ? Integer.toString(c.getPhone_no()): "" ;
            String role = c.getRole() != null ? c.getRole(): "" ;
        %>
        <form method=“get" action="handleEdit">
            <input type="hidden" name="action" value="<%= type %>" />
            ID  <input name="id" type="text" value="<%= id %>" readonly/> <br>
            UserName <input name="username" type="text" value="<%= username %>"/> <br>
            Password <input name="password" type="text" value="<%= password %>"/> <br>
            Phone_no <input name="phone_no" type="text" value="<%= phone_no %>"/> <br>
            Role <input name="role" type="text" value="<%= role %>"/> <br>
            <td><input type="submit" value="submit"/> <br>
        </form>
        <br/>    
        <a href="adminPage.jsp">Back to Main Page</a>
    </body>
</html>
