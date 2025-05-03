<%-- 
    Document   : staffVenue
    Created on : 2023年4月28日, 上午04:04:29
    Author     : osacr
--%>
<%@page import="java.util.*, ict.bean.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<VenueBean> venue = (ArrayList<VenueBean>) request.getAttribute("venue");
            out.println("<h1>Venue</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>id</th><th>name</th><th>type</th><th>capacity</th><th>location</th>"
                    + "<th>description</th><th>person_ic</th><th>booking_fee</th><th>status</th>");
            out.println("</tr>");
            for (int i = 0; i < venue.size(); i++) {
                VenueBean c = venue.get(i);
                out.println("<tr>");

                out.println("<td>" + c.getId() + "</td>");
                out.println("<td>" + c.getName() + "</td>");
                out.println("<td>" + c.getType() + "</td>");
                out.println("<td>" + c.getCapacity() + "</td>");
                out.println("<td>" + c.getLocation() + "</td>");
                out.println("<td>" + c.getDescription() + "</td>");
                out.println("<td>" + c.getPerson_ic() + "</td>");
                out.println("<td>" + c.getBooking_fee() + "</td>");
                out.println("<td>" + c.getStatus() + "</td>");
                out.println("<td><a href=\"handleVenue?action=delete&id=" + c.getId() + "\">delete</a></td>");
                out.println("<td><a href=\"handleVenue?action=getEditVenue&id=" + c.getId() + "\">edit</a></td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
        <br/>    
        <a href="staffMain.jsp">Back to Main page</a>
    </body>
</html>
