/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Carts;
import model.OrderCookie;

/**
 *
 * @author TienP
 */
public class checkout extends HttpServlet {

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
            out.println("<title>Servlet checkout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkout at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        DAO dao = new DAO();
        if (session.getAttribute("account") != null) {
            Integer accIDInt = (Integer) session.getAttribute("accID");
            String accID = accIDInt != null ? accIDInt.toString() : null;
            ArrayList<Carts> list = dao.cartById(accID);
            Carts ct = new Carts();
            double total = ct.Calculate(list);
            request.setAttribute("total", total);
            request.setAttribute("list", list);
            request.getRequestDispatcher("View/checkout.jsp").forward(request, response);
        } else {
            OrderCookie oc = new OrderCookie();
            Cookie[] arr = request.getCookies();
            String text = "";
            if (arr != null) {
                for (Cookie c : arr) {
                    if (c.getName().equals("cart")) {
                        text = c.getValue();
                        break;
                    }
                }
            }
            List<OrderCookie> list = oc.getCartFromCookies(text);
            double total = oc.Calculate(list);
            request.setAttribute("total", total);
            request.setAttribute("list", list);
            request.getRequestDispatcher("View/checkout.jsp").forward(request, response);
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
        DAO dao = new DAO();
        String name = request.getParameter("firstname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address") + "," + request.getParameter("city")
                + "," + request.getParameter("state");
        String status = "Pending confirmation";
        HttpSession session = request.getSession();

        String payMethod = request.getParameter("payment-method");

        if (session.getAttribute("accID") != null) {
            Integer accIDInt = (Integer) session.getAttribute("accID");
            String accID = accIDInt.toString();
            dao.insertOrders(name, phone, address, status, accID, payMethod);
            String orderID = dao.takeOrderId();
            ArrayList<Carts> list = dao.cartById(accID);
            for (Carts c : list) {
                dao.insertOrderDetails(orderID, c.getBookId().getId(),
                        c.getQuantity(), c.getBookId().getPrice());
                dao.updateQuantity(-c.getQuantity(), c.getBookId().getId());
                
            }
            dao.deleteCart(accID);
            response.sendRedirect("Home?success=order_placed");
        } else {
            dao.insertOrders(name, phone, address, status, "3", payMethod);
            OrderCookie oc = new OrderCookie();
            Cookie[] arr = request.getCookies();
            String text = "";
            if (arr != null) {
                for (Cookie c : arr) {
                    if (c.getName().equals("cart")) {
                        text = c.getValue();
                        c.setMaxAge(0);
                        response.addCookie(c);
                        break;
                    }
                }
            }
            
            String orderID = dao.takeOrderId();
            List<OrderCookie> list = oc.getCartFromCookies(text);
            for (OrderCookie c : list) {
                dao.insertOrderDetails(orderID, c.getBookId().getId(),
                        c.getQuantity(), c.getBookId().getPrice());
                dao.updateQuantity(-c.getQuantity(), c.getBookId().getId());
            }
            response.sendRedirect("Home?success=order_placed");
        }

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
