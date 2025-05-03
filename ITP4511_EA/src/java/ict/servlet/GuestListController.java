package ict.servlet;

import ict.bean.GuestBean;
import ict.db.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GuestListController", urlPatterns = {"/GuestListController"})
public class GuestListController extends HttpServlet {

    private DB db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new DB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String memberId = request.getParameter("memberId");
        if ("list".equalsIgnoreCase(action) && memberId != null) {
            request.getSession().setAttribute("cg", "old");
            request.getSession().setAttribute("title", "Guest List ID: ");
            ArrayList<GuestBean> GuestLists = db.getGuestListByMemberId(memberId);
            request.setAttribute("guestLists", GuestLists);
            HttpSession session = request.getSession(true);
            session.setAttribute("guestLists", GuestLists);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/ShowGuestList.jsp");
            rd.forward(request, response);
        } else if ("create".equalsIgnoreCase(action) && memberId != null) {
            request.getSession().setAttribute("cg", "new");
            request.getSession().setAttribute("title", "Create New Guest List ID: ");
            request.setAttribute("listId", db.getNewId("guest", "listId"));

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/createGuestList.jsp");
            rd.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
