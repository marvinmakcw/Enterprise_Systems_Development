/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.VenueBean;
import ict.db.VenueDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author osacr
 */
@WebServlet(name = "HandleVenue", urlPatterns = {"/handleVenue"})
public class VenueServlet extends HttpServlet {

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
        String confirm = request.getParameter("confirm");

        if ("list".equalsIgnoreCase(action)) {
            ArrayList<VenueBean> venue = db.queryCust();
            request.setAttribute("venue", venue);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/staffVenue.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            if (id != null) {
                RequestDispatcher rd;
                if (!("true".equalsIgnoreCase(confirm))) {
                    VenueBean c = db.queryCustByID(id);
                    request.setAttribute("venue", c);
                    request.setAttribute("action", "delete");

                    rd = getServletContext().getRequestDispatcher("/staffConfirm.jsp");
                    rd.forward(request, response);
                } else {
                    boolean isSuccess = db.delRecord(id);
                    rd = getServletContext().getRequestDispatcher("/handleVenue?action=list");
                    rd.forward(request, response);
                }
            }
        } else if ("getEditVenue".equalsIgnoreCase(action)) {
            if (id != null) {
                VenueBean venue = db.queryCustByID(id);
                request.setAttribute("c", venue);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/staffEditVenue.jsp");
                rd.forward(request, response);
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
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String capacity = request.getParameter("capacity");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String person_ic = request.getParameter("person_ic");
        String booking_fee = request.getParameter("booking_fee");
        String status = request.getParameter("status");
        String targetURL;

        if (!db.isNewVenue(name)) {
            targetURL = "addVenueError.jsp";
            request.getSession().setAttribute("error", "Venue already exist");
        } else {
            db.addVenue(name, type, capacity, location, description, person_ic, booking_fee, status);
            targetURL = "staffAddVenue.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
