/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.DAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author TienP
 */
public class SignUp extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet SignUp</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         request.getRequestDispatcher("View/signup.jsp").forward(request, response);;
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pwd = request.getParameter("psw");
        String pwd2 = request.getParameter("psw-repeat");
        DAO dao = new DAO();
        Account acc = dao.checkUser(user);
        if (acc != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/View/signup.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Username has already exist!!.</font>");
            rd.include(request, response);
        }else if(!pwd.equalsIgnoreCase(pwd2)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/View/signup.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Password is not correct!!.</font>");
            rd.include(request, response);
            
        }else{
            dao.insertAcc(user, pwd);
            HttpSession session = request.getSession();
            Account account = dao.checkAcc(user, pwd);
            session.setAttribute("account", account); 
            session.setAttribute("username", user);
            session.setAttribute("pass", pwd);
            session.setAttribute("accID", account.getAccId()); 
            session.setAttribute("role", account.getRole());
            session.setMaxInactiveInterval(30 * 60*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("Home");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
