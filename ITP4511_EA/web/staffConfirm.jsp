<%-- 
    Document   : staffConfirm
    Created on : 2023年4月28日, 上午07:23:23
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, ict.bean.*" %>

<%
    VenueBean c = (VenueBean) request.getAttribute("venue");

    String type = (String) request.getAttribute("action");
    String url = type.equalsIgnoreCase("edit") ? "venueEdit" : "handleVenue";
    int id = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("id")) : c.getId();
    String name = type.equalsIgnoreCase("edit") ? request.getParameter("name") : c.getName();
    String vtype = type.equalsIgnoreCase("edit") ? request.getParameter("type") : c.getType();
    int capacity = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("capacity")) : c.getCapacity();
    String location = type.equalsIgnoreCase("edit") ? request.getParameter("location") : c.getLocation();
    String description = type.equalsIgnoreCase("edit") ? request.getParameter("description") : c.getDescription();
    int person_ic = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("person_ic")) : c.getPerson_ic();
    int booking_fee = type.equalsIgnoreCase("edit") ? Integer.parseInt(request.getParameter("booking_fee")) : c.getBooking_fee();
    String status = type.equalsIgnoreCase("edit") ? request.getParameter("status") : c.getStatus();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
    </head>
    <body>
        <form action="<%= url%>" method="get">
            <input type="hidden" name="action" value="<%= type%>" />
            <input type="hidden" name="confirm" value="true" />
            <h2><span style="text-transform: uppercase"><%= type%></span> Venue:</h2>
            <h3>Are you sure to make the following changes?</h3>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th> 
                    <th>Type</th> 
                    <th>Capacity</th> 
                    <th>Location</th>
                    <th>Description</th> 
                    <th>Person_ic</th> 
                    <th>Booking_fee</th> 
                    <th>Status</th>
                </tr>
                <tr>
                    <td><input type="text" name="id" value="<%= id%>" readonly /></td>
                    <td><input type="text" name="name" value="<%= name%>" readonly /></td>
                    <td><input type="text" name="type" value="<%= vtype%>" readonly /></td> 
                    <td><input type="text" name="capacity" value="<%= capacity%>" readonly /></td>
                    <td><input type="text" name="location" value="<%= location%>" readonly /></td>
                    <td><input type="text" name="description" value="<%= description%>" readonly /></td>
                    <td><input type="text" name="person_ic" value="<%= person_ic%>" readonly /></td> 
                    <td><input type="text" name="booking_fee" value="<%= booking_fee%>" readonly /></td>
                    <td><input type="text" name="status" value="<%= status%>" readonly /></td>
                </tr>
            </table><br/>
            <input type="submit" value="Confirm" />
            <button><a href="handleVenue?action=list" style="text-decoration: none; color: black">Cancel</a></button>
        </form>
    </body>
</html>
