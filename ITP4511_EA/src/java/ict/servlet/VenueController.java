package ict.servlet;

import ict.bean.VenueBean;
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

@WebServlet(name = "VenueController", urlPatterns = {"/VenueController"})
public class VenueController extends HttpServlet {

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
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<VenueBean> venues = db.getAllVenue();
            request.setAttribute("venues", venues);
            HttpSession session = request.getSession(true);
            session.setAttribute("venues", venues);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/ShowVenueList.jsp");
            rd.forward(request, response);
        } /*else if ("add".equalsIgnoreCase(action)) {
            String imageListId = request.getParameter("imageListId");
            String venueName = request.getParameter("venueName");
            String venueType = request.getParameter("venueType");
            String venueCapacity = request.getParameter("venueCapacity");
            String venueLocation = request.getParameter("venueLocation");
            String venueDesc = request.getParameter("venueDesc");
            String venuePIC = request.getParameter("venuePIC");
            int venueHourlyFee = Integer.parseInt(request.getParameter("venueHourlyFee"));
            PrintWriter out = response.getWriter();
            out.println(db.addVenue(imageListId, venueName, venueType, venueCapacity, venueLocation, venueDesc, venuePIC, venueHourlyFee));
            response.sendRedirect("/Assignment/staffFunction/staffListVenue.jsp");

        }*/ else {
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
