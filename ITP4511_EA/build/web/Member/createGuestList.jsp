<%@page import="ict.db.DB"%>
<%@page import="ict.bean.GuestBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Guest List</title>
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

            out.println("<form method='get' action='GuestController'>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Guest Id</th><th>Guest Name</th><th>Guest Email</th>");
            out.println("</tr>");
            for (int i = 0; i < guestLists.size(); i++) {
                GuestBean c = guestLists.get(i);
                out.println("<tr>");
                out.println("<td>" + c.getGuestId() + "</td>");
                out.println("<td>" + c.getName() + "</td>");
                out.println("<td>" + c.getEmail() + "</td>");
                //out.println("<td>" + c.getGuestListId() + "</td>");
                out.println("<td><a href='Member/editGuest.jsp?guestId=" + c.getGuestId() + "&listId=" + request.getParameter("listId") + "'>Edit</a></td>");
                out.println("<td><a href='#' onclick=\"if (confirm(\'Are you sure you want to delete " + "Guest ID : " + c.getGuestId() + " Name : " + c.getName()
                        + "?\')) {window.location.href="
                        + "\'http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/GuestController?action=delete&guestId=" + c.getGuestId() + "&listId=" + c.getListId() + "\'"
                        + ";};\">Delete</a></td>");
                out.println("</tr>");
            }
        %>
    <tr>
        <td><input type='text' name='guestId' value='<%=db.getNewId("guest", "guestId")%>' readonly></td>
        <td><input type='text' name='name'></td>
        <td><input type='text' name='email'></td>
    <input type='hidden' name='action' value='add'>
    <%
        int id = userInfo.getId();
    %>
    <input type='hidden' name='memberId' value='<%=id%>'>
    <%
        if (session.getAttribute("cg") == "new") {
            out.println("<input type='hidden' name='listId' value='" + db.getNewId("guest", "listId") + "'>");
        } else {
            out.println("<input type='hidden' name='listId' value='" + request.getParameter("listId") + "'>");
        }
    %>
    <td><input type='submit' value='Create Guest'></td>
</tr>
</table>

<a href='GuestListController?action=list&memberId=<%=id%>'>Back to guest list</a><br>
<a href='memberMain.jsp'>Back to home page</a>
</body>
</html>
