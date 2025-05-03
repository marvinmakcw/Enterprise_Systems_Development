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

@WebServlet(name = "GuestController", urlPatterns = {"/GuestController"})
public class GuestController extends HttpServlet {

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
        String listId = request.getParameter("listId");
        if ("list".equalsIgnoreCase(action) && listId != null) {
            ArrayList<GuestBean> GuestLists = db.getGuestByGuestListId(listId);
            request.setAttribute("guestLists", GuestLists);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/createGuestList.jsp");
            rd.forward(request, response);
        } else if ("add".equalsIgnoreCase(action) && listId != null) {
            request.getSession().setAttribute("cg", "old");
            request.getSession().setAttribute("title", "Guest List ID: ");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String guestId = request.getParameter("guestId");
            db.addGuest(guestId, listId, name, email, memberId);
            request.setAttribute("listId", listId);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/createGuestList.jsp");
            rd.forward(request, response);

        } else if ("delete".equalsIgnoreCase(action) && listId != null) {
            String guestId = request.getParameter("guestId");
            db.delGuest(guestId);
            request.setAttribute("GuestListId", listId);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/createGuestList.jsp");
            rd.forward(request, response);

        } else if ("edit".equalsIgnoreCase(action) && listId != null) {
            String guestId = request.getParameter("guestId");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            db.editGuest(guestId, name, email);
            request.setAttribute("GuestListId", listId);
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
