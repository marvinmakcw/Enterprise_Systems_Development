<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="c" scope="request" class="ict.bean.CustomerBean" />
        <% 
            String type = c.getCustId() != null ? "edit" : "add";
            String id = c.getCustId() != null ? c.getCustId(): "" ;
            String name = c.getName() != null ? c.getName(): "" ;
            String tel = c.getTel() != null ? c.getTel(): "" ;
            String age = Integer.toString(c.getAge()) != null ? Integer.toString(c.getAge()) : "" ;
        %>
        <form method=“get" action="handleEdit">
            <input type="hidden" name="action" value="<%= type %>" />
            ID  <input name="id" type="text" value="<%= id %>"/> <br>
            Name <input name="name" type="text" value="<%= name %>"/> <br>
            Tel <input name="tel" type="text" value="<%= tel %>"/> <br>
            Age <input name="age" type="text" value="<%= age %>"/> <br>
            <td><input type="submit" value="submit"/> <br>
        </form>
        <br/>    
        <a href="welcome.jsp">Back to menu</a>
    </body>
</html>
