/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.BookCate;
import model.Data;
import model.History;

/**
 *
 * @author TienP
 */
public class chart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet chart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet chart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        int[] months = {1, 2, 3, 4};
        // Thêm các phần tử vào danh sách
        DAO dao = new DAO();
        
        ArrayList<Data> list11 = dao.getDatabyMonth(11, months);
        ArrayList<Data> list10 = dao.getDatabyMonth(10, months);
        ArrayList<Data> list9 = dao.getDatabyMonth(9, months);
        ArrayList<Data> list8 = dao.getDatabyMonth(8, months);
        ArrayList<Data> list7 = dao.getDatabyMonth(7, months);
        ArrayList<Data> list6 = dao.getDatabyMonth(6, months);
        ArrayList<Data> list5 = dao.getDatabyMonth(5, months);
        ArrayList<Data> list4 = dao.getDatabyMonth(4, months);
        ArrayList<Data> list3 = dao.getDatabyMonth(3, months);
        ArrayList<Data> list2 = dao.getDatabyMonth(2, months);
        ArrayList<Data> list1 = dao.getDatabyMonth(1, months);
        ArrayList<Data> total = dao.getDataTotalbyMonth(months);
        request.setAttribute("list11", list11);
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);
        request.setAttribute("list4", list4);
        request.setAttribute("list5", list5);
        request.setAttribute("list6", list6);
        request.setAttribute("list7", list7);
        request.setAttribute("list8", list8);
        request.setAttribute("list9", list9);      
        request.setAttribute("list10", list10);
        request.setAttribute("total", total);
//        request.setAttribute("scienceData", scienceData);
//        request.setAttribute("novelData", novelData);
//        request.setAttribute("totalData", totalData);

        request.getRequestDispatcher("View/dashboard.jsp").forward(request, response);
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
