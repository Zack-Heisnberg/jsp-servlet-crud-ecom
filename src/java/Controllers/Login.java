/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
@WebServlet(name = "Login", urlPatterns = {"/LoginPost"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.sendRedirect("/admin/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String username = request.getParameter("username");
           String password = request.getParameter("password");
           if (username.equals("nada") && password.equals("5964")) {
              HttpSession session=request.getSession();  
              session.setAttribute("admin",username);  
              response.sendRedirect("/admin/index.jsp?method=ViewAll");
           } else {
              request.setAttribute("alert", "Wrong Username / Password");
              request.getRequestDispatcher("/admin/login.jsp").forward(request, response);  
           }
    }

}
