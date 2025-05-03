<%-- 
    Document   : phoneList
    Created on : 2023年3月4日, 下午07:00:08
    Author     : zengjuncai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ict.bean.*, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone List</title>
    </head>
    <body>
         <jsp:useBean id="phones" scope="request" class="ArrayList<Phone>" />
            <%
                for(int i = 0; i < phones.size(); i++){
                    Phone p = phones.get(i);
                    out.print("<table border='1'><tr><td>" + p.getName() + "</td><td><img src='" + p.getImg() + "'></img></td><td>" + p.getPrice() + "</td></tr></table><br />");
                }
            %>
            <a href="BrandController?action=list">Show Brand</a><br />
    </body>
</html>
