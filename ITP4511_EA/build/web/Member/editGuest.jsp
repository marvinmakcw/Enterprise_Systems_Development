<%@page import="ict.db.DB"%>
<%@page import="ict.bean.GuestBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Guest List</title>
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

            ArrayList<GuestBean> guestLists = (ArrayList<GuestBean>) db.getGuestByGuestListId(request.getParameter("listId"));
            if (session.getAttribute("cg") == "new") {
                out.println("<h1>" + session.getAttribute("title") + db.getNewId("guest", "listId") + "</h1>");
            } else {
                out.println("<h1>" + session.getAttribute("title") + request.getParameter("listId") + "</h1>");
            }
            out.println("<form method='get' action='../GuestController'>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Guest Id</th><th>Guest Name</th><th>Guest Email</th>");
            out.println("</tr>");
            for (int i = 0; i < guestLists.size(); i++) {
                GuestBean c = guestLists.get(i);
                if (request.getParameter("guestId").equals(Integer.toString(c.getGuestId()))) {
                    out.println("<tr>");
                    out.println("<td>" + c.getGuestId() + "</td>");
                    out.println("<input type='hidden' name='action' value='edit'>");
                    out.println("<input type='hidden' name='listId' value='" + request.getParameter("listId") + "'>");
                    out.println("<input type='hidden' name='guestId' value='" + c.getGuestId() + "'>");
                    out.println("<td><input type='text' name='name' value='" + c.getName() + "'></td>");
                    out.println("<td><input type='text' name='email' value='" + c.getEmail() + "'></td>");
                    out.println("<td><input type='submit' value='Confirm Edit'></td>");
                    out.println("</tr>");
                } else {
                    out.println("<tr>");
                    out.println("<td>" + c.getGuestId() + "</td>");
                    out.println("<td>" + c.getName() + "</td>");
                    out.println("<td>" + c.getEmail() + "</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            int id = userInfo.getId();
        %>
        <a href='../GuestListController?action=list&memberId=<%=id%>'>Back to guest list</a><br>
        <a href='../memberMain.jsp'>Back to home page</a>

    </body>
</html>
