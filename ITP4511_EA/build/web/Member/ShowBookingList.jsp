<%@page import="ict.db.DB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.BookingBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking List</title>
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

            ArrayList<BookingBean> bookings = db.getBookRequestByMemberId(String.valueOf(userInfo.getId()));
            out.println("<h1>Booking List Record</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Booking ID</th><th>Date</th><th>Venue Name</th><th>Start Time</th><th>End Time</th><th>Status</th>"
                    + "<th>Guest List ID</th><th>Request Time</th>");
            out.println("</tr>");
            for (int i = 0; i < bookings.size(); i++) {
                BookingBean booking = bookings.get(i);
                String name = "";
                switch (booking.getVenueId()) {
                    case 1:
                        name = "Tuen Mun IVE";
                        break;
                    case 2:
                        name = "Sha Tin IVE";
                        break;
                    case 3:
                        name = "Tsing Yi IVE";
                        break;
                    case 4:
                        name = "Lee Wai Lee IVE";
                        break;
                    case 5:
                        name = "Chai Wan IVE";
                        break;
                }
                out.println("<tr>");
                out.println("<td>" + booking.getId() + "</td>");
                out.println("<td>" + booking.getDate() + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + booking.getStartTime() + "</td>");
                out.println("<td>" + booking.getEndTime() + "</td>");
                out.println("<td>" + booking.getStatus() + "</td>");
                out.println("<td>" + booking.getListId() + "</td>");
                out.println("<td>" + booking.getRequestTime() + "</td>");
                out.println("<td><a href='http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Member/createBooking.jsp?action=edit&Id=" + booking.getId() + "'>Edit</a></td>");

                out.println("<td><a href='#' onclick=\"if (confirm(\'Are you sure you want to cancel booking?" + "ID : " + booking.getId()
                        + "\')) {window.location.href="
                        + "\'http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/BookingController?action=cancel&Id=" + booking.getId() + "\'"
                        + ";};\">Cancel Booking</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <a href='../memberMain.jsp'>Back to home page</a>
    </body>
</html>
