/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.VenueBean;
import ict.db.VenueDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author osacr
 */
@WebServlet(name = "VenueEdit", urlPatterns = {"/venueEdit"})
public class VenueEdit extends HttpServlet {

    private VenueDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        dbUrl += "?autoReconnect=true&useSSL=false";
        db = new VenueDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        int person_ic = Integer.parseInt(request.getParameter("person_ic"));
        int booking_fee = Integer.parseInt(request.getParameter("booking_fee"));
        String status = request.getParameter("status");
        String confirm = request.getParameter("confirm");

        if ("add".equalsIgnoreCase(action)) {
            response.sendRedirect("handleVenue?action=list");
        } else if ("edit".equalsIgnoreCase(action)) {
            if (!("true".equalsIgnoreCase(confirm))) {
                VenueBean c = db.queryCustByID(id);
                request.setAttribute("venue", c);
                request.setAttribute("action", "edit");

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/staffConfirm.jsp");
                rd.forward(request, response);
            } else {
                VenueBean venue = db.queryCustByID(id);
                venue.setName(name);
                venue.setType(type);
                venue.setCapacity(capacity);
                venue.setLocation(location);
                venue.setDescription(description);
                venue.setPerson_ic(person_ic);
                venue.setBooking_fee(booking_fee);
                venue.setStatus(status);
                db.editRecord(venue);
                response.sendRedirect("handleVenue?action=list");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
