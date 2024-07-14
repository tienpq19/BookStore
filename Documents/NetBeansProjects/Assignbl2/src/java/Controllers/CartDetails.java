/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.OrderCookie;

/**
 *
 * @author TienP
 */
public class CartDetails extends HttpServlet {

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
            out.println("<title>Servlet CartDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartDetails at " + request.getContextPath() + "</h1>");
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

        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("num"));
        OrderCookie cart = new OrderCookie();
        Cookie[] arr = request.getCookies();
        String text = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    text = c.getValue();
                    c.setMaxAge(0);
                    break;
                }
            }
        }
        if (text.isBlank()) {
            text = id + ":" + quantity;
            Cookie c = new Cookie("cart", text);
            c.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(c);
        } else {
            List<OrderCookie> orderlist = cart.getCartFromCookies(text);
            String newText = "";
            for (OrderCookie or : orderlist) {
                if (or.getBookId().getId() == id) {
                    or.setQuantity(quantity);
                }
                if (newText.isEmpty()) {
                    newText = or.getBookId().getId() + ":" + or.getQuantity();
                } else {
                    newText = newText + "/" + or.getBookId().getId() + ":" + or.getQuantity();
                }
            }
            newText= newText+"/"+id+":"+quantity;
            Cookie c = new Cookie("cart", newText);
            c.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(c);
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
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("num"));
        OrderCookie cart = new OrderCookie();
        Cookie[] arr = request.getCookies();
        String text = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    text = c.getValue();
                    c.setMaxAge(0);
                    break;
                }
            }
        }
        List<OrderCookie> orderlist = cart.getCartFromCookies(text);
        String newText = "";
        for (OrderCookie or : orderlist) {
            if (or.getBookId().getId() == id) {
                or.setQuantity(quantity);
            }
            if (newText.isEmpty()) {
                newText = or.getBookId().getId() + ":" + or.getQuantity();
            } else {
                newText = newText + "/" + or.getBookId().getId() + ":" + or.getQuantity();
            }
        }
        Cookie c = new Cookie("cart", newText);
        c.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(c);
        List<OrderCookie> list = cart.getCartFromCookies(newText);
        double total = cart.Calculate(list);
        request.setAttribute("total", total);
        request.setAttribute("list", list);
        request.getRequestDispatcher("View/cart.jsp").forward(request, response);
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
