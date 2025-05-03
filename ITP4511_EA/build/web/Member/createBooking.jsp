<%@page import="ict.db.DB"%>
<%@page import="ict.bean.GuestBean"%>
<%@page import="ict.bean.VenueBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.time.LocalTime, java.time.format.DateTimeFormatter, java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Booking</title>
    </head>
    <body>
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
        <h1><jsp:getProperty name="userInfo" property="username"/></h1>
        <%
            DB db;
            String dbUser = this.getServletContext().getInitParameter("dbUser");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            db = new DB(dbUrl, dbUser, dbPassword);

            String type = request.getParameter("bookingRequestId") != null ? "edit" : "create";
            String selectedVenue = request.getParameter("Id");
            ArrayList<VenueBean> venues = db.getAllVenue();
            ArrayList listId = new ArrayList();
            ArrayList listName = new ArrayList();
            for (int i = 0; i < venues.size(); i++) {
                listId.add(venues.get(i).getId());
                listName.add(venues.get(i).getName());
            }

            ArrayList dates = new ArrayList();
            ArrayList timeSlots = new ArrayList();

            dates.add("2023-04-29");
            dates.add("2023-04-30");
            dates.add("2023-05-01");
            dates.add("2023-05-02");
            dates.add("2023-05-03");
            dates.add("2023-05-04");
            dates.add("2023-05-05");

            LocalTime time = LocalTime.of(9, 0);
            for (int i = 0; i < 9; i++) {
                String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                timeSlots.add(formattedTime);
                time = time.plusHours(1);
            }

            ArrayList<GuestBean> guestLists = db.getGuestListByMemberId(String.valueOf(userInfo.getId()));
        %>



        <form method="get" action="../BookingController">
            <input type="hidden" name="action" value="<%=type%>">
            <input type="hidden" name="memberId" value="<%=userInfo.getId()%>">
            <%
                if (request.getParameter("bookingRequestId") != null) {
                    out.println("<h4>Update Booking Request</h4>");
                    out.println("<p>BookingRequestId: " + request.getParameter("bookingRequestId") + "</p>");
                    out.println("<input type='hidden' name='bookingRequestId' value='" + request.getParameter("bookingRequestId") + "'>");
                } else {
                    out.println("<h4>Add Booking Request<h4>");
                }

            %>
            Selected Venue:
            <select name="selectVenue">
                <%                    for (int i = 0; i < venues.size(); i++) {

                        if ((listId.get(i)).equals(selectedVenue)) {
                            out.println("<option value='" + listId.get(i) + "' selected>" + listName.get(i) + "</option>");
                        } else {
                            out.println("<option value='" + listId.get(i) + "'>" + listName.get(i) + "</option>");
                        }
                    }
                %>
                
            </select><br/>
            Select Date:
            <select name="selectDate">  
                <%
                    for (int i = 0; i < dates.size(); i++) {
                        out.println("<option value='" + dates.get(i) + "'>" + dates.get(i) + "</option>");

                    }
                %>
            </select><br/>
            Select Start Time:
            <select name="selectStartTime">  
                <% for (int i = 0; i < timeSlots.size(); i++) {
                        out.println("<option value='" + timeSlots.get(i) + "'>" + timeSlots.get(i) + "</option>");
                    }
                %>
            </select><br/>
            Select End Time:
            <select name="selectEndTime">  
                <% for (int i = 0; i < timeSlots.size(); i++) {
                        out.println("<option value='" + timeSlots.get(i) + "'>" + timeSlots.get(i) + "</option>");
                    }
                %>
            </select><br/>
            Select Guest List:
            <select name="selectGuestList">  
                <%                    for (int i = 0; i < guestLists.size(); i++) {
                        out.println("<option value='" + guestLists.get(i).getListId() + "'>" + guestLists.get(i).getListId() + "</option>");
                    }
                %>
            </select><br/>
            <input type="submit" value="submit"><br>

        </form>
        <a href='../memberMain.jsp'>Back to home page</a>
    </body>
</html>
