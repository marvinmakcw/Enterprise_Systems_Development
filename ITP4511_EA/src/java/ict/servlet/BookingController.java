package ict.servlet;

import ict.bean.BookingBean;
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

@WebServlet(name = "BookingController", urlPatterns = {"/BookingController"})
public class BookingController extends HttpServlet {

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
        String id = request.getParameter("Id");
        if ("list".equalsIgnoreCase(action) && memberId != null) {
            // call the query db to get retrieve for all customer 
            ArrayList<BookingBean> bookings = db.getBookRequestByMemberId(memberId);
            // set the result into the attribute 
            request.setAttribute("bookings", bookings);
            // redirect the result to the listCustomers.jsp 
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/Member/ShowBookingList.jsp");
            rd.forward(request, response);
        } else if ("create".equalsIgnoreCase(action)) {
            String selectVenue = request.getParameter("selectVenue");
            String selectDate = request.getParameter("selectDate");
            String selectStartTime = request.getParameter("selectStartTime");
            String selectEndTime = request.getParameter("selectEndTime");
            String selectGuestList = request.getParameter("selectGuestList");
            db.createBookRequest(selectVenue, selectDate, memberId, selectStartTime, selectEndTime, selectGuestList);
            response.sendRedirect("Member/ShowBookingList.jsp");

        } else if ("edit".equalsIgnoreCase(action) && id != null) {
            String bookingRequestId = request.getParameter("bookingRequestId");
            String selectVenue = request.getParameter("selectVenue");
            String selectDate = request.getParameter("selectDate");
            String selectStartTime = request.getParameter("selectStartTime");
            String selectEndTime = request.getParameter("selectEndTime");
            String selectGuestList = request.getParameter("selectGuestList");
            db.editBookRequest(selectVenue, selectDate, memberId, selectStartTime, selectEndTime, selectGuestList);
            response.sendRedirect("Member/ShowBookingList.jsp");
        } else if ("cancel".equalsIgnoreCase(action)) {
            String bookingRequestId = request.getParameter("bookingRequestId");
            db.cancelBooking(bookingRequestId);
            response.sendRedirect("Member/ShowBookingList.jsp");
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
