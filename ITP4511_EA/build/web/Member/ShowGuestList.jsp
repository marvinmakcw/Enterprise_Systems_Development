<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.GuestBean"%>
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
            ArrayList<GuestBean> guestLists = (ArrayList<GuestBean>) request.getAttribute("guestLists");
            out.println("<h1>Guest List</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Guest List ID</th>");
            out.println("</tr>");
            for (int i = 0; i < guestLists.size(); i++) {
                GuestBean gb = guestLists.get(i);
                out.println("<tr>");
                out.println("<td>" + gb.getListId() + "</td>");
                out.println("<td><a href='GuestController?action=list&listId=" + gb.getListId() + "'>Edit this list</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <a href='GuestListController?action=create&memberId=<%=userInfo.getId()%>'>Create Guest List</a><br/>
        <a href='memberMain.jsp'>Back to home page</a>
    </body>
</html>
