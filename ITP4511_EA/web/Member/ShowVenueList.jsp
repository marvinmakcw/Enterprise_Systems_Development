<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.VenueBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue List</title>
    </head>
    <body>
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
        <h1><jsp:getProperty name="userInfo" property="username"/></h1>
        <%
            ArrayList<VenueBean> venues = (ArrayList<VenueBean>) request.getAttribute("venues");
            out.println("<h1>Show Venue List</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Id</th><th>Name</th><th>Type</th><th>Capacity</th><th>Location</th><th>Description</th><th>Booking Fee</th><th>Status</th>");
            out.println("</tr>");
            for (int i = 0; i < venues.size(); i++) {
                VenueBean vb = venues.get(i);
                out.println("<tr>");
                out.println("<td>" + vb.getId() + "</td>");
                out.println("<td>" + vb.getName() + "</td>");
                out.println("<td>" + vb.getType() + "</td>");
                out.println("<td>" + vb.getCapacity() + "</td>");
                out.println("<td>" + vb.getLocation() + "</td>");
                out.println("<td>" + vb.getDescription() + "</td>");
                out.println("<td>" + vb.getBooking_fee() + "</td>");
                out.println("<td>" + vb.getStatus() + "</td>");
                out.println("<td><a href='Member/createBooking.jsp?&Id="
                        + vb.getId()
                        + "'>Book</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <a href='memberMain.jsp'>Back to home page</a>
    </body>
</html>
