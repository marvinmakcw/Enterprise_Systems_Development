<%-- 
    Document   : brands
    Created on : 2023年3月4日, 下午06:13:31
    Author     : zengjuncai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ict.bean.*,java.util.*"%>
<jsp:useBean id="brands" scope="request" class="ArrayList<Brand>" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All brands</title>
    </head>
    <body>
        
        <%
            Brand b;
            for(int i = 0; i < brands.size(); i++){
                b = brands.get(i);
                out.print("<a href=\"getPhones?action=list&brand=" + b.getName() + "\">" + b.getName() + "</a></br />");
            }
        %>
        <hr>
        <a href="createBrandForm.jsp">Create Brand</a><br />
    </body>
</html>
