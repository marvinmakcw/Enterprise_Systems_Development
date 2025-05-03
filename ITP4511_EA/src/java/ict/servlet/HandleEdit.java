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
import java.util.ArrayList;
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
@WebServlet(name = "HandleEdit", urlPatterns = {"/handleEdit"})
public class HandleEdit extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int phone_no = Integer.parseInt(request.getParameter("phone_no"));
        String role = request.getParameter("role");
        String confirm = request.getParameter("confirm");

        if ("add".equalsIgnoreCase(action)) {
            response.sendRedirect("handleUser?action=list");
        } else if ("edit".equalsIgnoreCase(action)) {
            if (!("true".equalsIgnoreCase(confirm))) {
                AdminList c = db.queryCustByID(id);
                request.setAttribute("user", c);
                request.setAttribute("action", "edit");

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/adminConfirmEdit.jsp");
                rd.forward(request, response);
            } else {
                AdminList user = db.queryCustByID(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setPhone_no(phone_no);
                user.setRole(role);
                db.editRecord(user);
                response.sendRedirect("handleUser?action=list");
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
