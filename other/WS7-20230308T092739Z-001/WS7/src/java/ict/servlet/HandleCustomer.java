/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HandleCustomer", urlPatterns = {"/handleCustomer"})
public class HandleCustomer extends HttpServlet {
    private CustomerDB db;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        
        dbUrl += "?autoReconnect=true&useSSL=false";
        db = new CustomerDB(dbUrl, dbUser, dbPassword);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String confirm = request.getParameter("confirm");

        if ("list".equalsIgnoreCase(action)) {
           // call the query db to get retrieve for all customer 
            ArrayList<CustomerBean> customers = db.queryCust();
            // set the result into the attribute	 
            request.setAttribute("customers", customers);
            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listCustomers.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)){
            if (id != null){
                RequestDispatcher rd;
                if (!("true".equalsIgnoreCase(confirm))){
                    CustomerBean c = db.queryCustByID(id);
                    request.setAttribute("customer", c);
                    request.setAttribute("action", "delete");
                    
                    rd = getServletContext().getRequestDispatcher("/confirmation.jsp");
                    rd.forward(request, response);
                } 
                else {
                    boolean isSuccess = db.delRecord(id);
                    rd = getServletContext().getRequestDispatcher("/handleCustomer?action=list");
                    rd.forward(request, response);
                }
            }
        } else if ("getEditCustomer".equalsIgnoreCase(action)){
            if (id != null){
                CustomerBean customer = db.queryCustByID(id);
                request.setAttribute("c", customer);
                
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editCustomer.jsp");
                rd.forward(request, response);
            }
        } else if ("search".equalsIgnoreCase(action)) { 
            String name = request.getParameter("name");
            if (name != null) {
                ArrayList<CustomerBean> customers;
                customers = db.queryCustByName(name);
                request.setAttribute("customers", customers);
                
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/listCustomers.jsp");
                rd.forward(request, response);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
