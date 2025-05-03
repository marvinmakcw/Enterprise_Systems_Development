/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.UserInfo;
import ict.bean.AdminList;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author osacr
 */
@WebServlet(name = "HandleUser", urlPatterns = {"/handleUser"})
public class HandleUser extends HttpServlet {

    private UserDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        dbUrl += "?autoReconnect=true&useSSL=false";
        db = new UserDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String confirm = request.getParameter("confirm");

        if ("list".equalsIgnoreCase(action)) {
            ArrayList<AdminList> user = db.queryCust();
            request.setAttribute("user", user);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/adminUserList.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            if (id != null) {
                RequestDispatcher rd;
                if (!("true".equalsIgnoreCase(confirm))) {
                    AdminList c = db.queryCustByID(id);
                    request.setAttribute("user", c);
                    request.setAttribute("action", "delete");

                    rd = getServletContext().getRequestDispatcher("/adminConfirmEdit.jsp");
                    rd.forward(request, response);
                } else {
                    boolean isSuccess = db.delRecord(id);
                    rd = getServletContext().getRequestDispatcher("/handleUser?action=list");
                    rd.forward(request, response);
                }
            }
        } else if ("getEditUser".equalsIgnoreCase(action)) {
            if (id != null) {
                AdminList user = db.queryCustByID(id);
                request.setAttribute("c", user);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/adminEdit.jsp");
                rd.forward(request, response);
            }
        } else if ("search".equalsIgnoreCase(action)) {
            String name = request.getParameter("name");
            if (name != null) {
                ArrayList<AdminList> user;
                user = db.queryCustByName(name);
                request.setAttribute("user", user);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/adminUserList.jsp");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String targetURL;

        if (!db.isNewUser(username)) {
            targetURL = "addUserError.jsp";
            request.getSession().setAttribute("error", "User already exist");
        } else if (!password.equals(cpassword)) {
            targetURL = "addUserError.jsp";
            request.getSession().setAttribute("error", "Password not match");
        } else {
            db.addAdminUser(username, password, phone, role);
            targetURL = "adminAddUser.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }


//// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}
