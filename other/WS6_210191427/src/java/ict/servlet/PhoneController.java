/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.Brand;
import ict.bean.Phone;
import ict.db.BrandDB;
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
 * @author zengjuncai
 */
@WebServlet(name = "PhoneController", urlPatterns = {"/getPhones"})
public class PhoneController extends HttpServlet {
    private BrandDB brandDb;
    public void init() {
        brandDb = new BrandDB();    
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
        String action = request.getParameter("action");
        String br = request.getParameter("brand");
        if("list".equalsIgnoreCase(action)){
            String brand = null; 
            ArrayList<Phone> phoneList = null;
            ArrayList<Brand> brands = brandDb.getBrands();
            for(int i = 0; i < brands.size(); i++){
                if(brands.get(i).getName().equalsIgnoreCase(br)){
                    brand = br;
                }
            }
            
            phoneList = brandDb.getPhonesByBrand(brand);
            request.setAttribute("phones", phoneList);
            RequestDispatcher rd = this.getServletContext()
             .getRequestDispatcher("/phoneList.jsp");
            rd.forward(request, response); 
        }else{
            PrintWriter out = response.getWriter();
            out.println("NO such action or brand");
        }
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
