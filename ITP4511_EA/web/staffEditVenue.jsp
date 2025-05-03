<%-- 
    Document   : staffEditVenue
    Created on : 2023年4月28日, 上午09:14:29
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Venue Information</title>
    </head>
    <body>
        <jsp:useBean id="c" scope="request" class="ict.bean.VenueBean" />
        <%
            String type = Integer.toString(c.getId()) != null ? "edit" : "add";
            String id = Integer.toString(c.getId()) != null ? Integer.toString(c.getId()) : "";
            String name = c.getName() != null ? c.getName() : "";
            String vtype = c.getType() != null ? c.getType() : "";
            String capacity = Integer.toString(c.getCapacity()) != null ? Integer.toString(c.getCapacity()) : "";
            String location = c.getLocation() != null ? c.getLocation() : "";
            String description = c.getDescription() != null ? c.getDescription() : "";
            String person_ic = Integer.toString(c.getPerson_ic()) != null ? Integer.toString(c.getPerson_ic()) : "";
            String booking_fee = Integer.toString(c.getBooking_fee()) != null ? Integer.toString(c.getBooking_fee()) : "";
            String status = c.getStatus() != null ? c.getStatus() : "";
        %>
        <form method=“get" action="venueEdit">
            <input type="hidden" name="action" value="<%= type%>" />
            ID  <input name="id" type="text" value="<%= id%>" readonly/> <br>
            Name <input name="name" type="text" value="<%= name%>"/> <br>
            Type <input name="type" type="text" value="<%= vtype%>"/> <br>
            Capacity <input name="capacity" type="text" value="<%= capacity%>"/> <br>
            Location <input name="location" type="text" value="<%= location%>"/> <br>
            description <input name="description" type="text" value="<%= description%>"/> <br>
            person_ic <input name="person_ic" type="text" value="<%= person_ic%>"/> <br>
            booking_fee <input name="booking_fee" type="text" value="<%= booking_fee%>"/> <br>
            status <input name="status" type="text" value="<%= status%>"/> <br>
            <td><input type="submit" value="submit"/> <br>
        </form>
        <br/>    
        <a href="staffMain.jsp">Back to Main Page</a>
    </body>
</html>
