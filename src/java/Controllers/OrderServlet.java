/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.OrderDAO;

import Model.OrderModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private OrderDAO OrderDAO;

    public void init() {
        OrderDAO = new OrderDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("method");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("/admin/login.jsp");
            return;
        }
      /*  try {
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("/admin/login.jsp");
                return;
            }
            switch (action) {
                case "AddOrder":
                 //   AddOrder(request, response);
                    break;
                case "UpdateOrder":
                 //   UpdateOrder(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("method");
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("/admin/login.jsp");
            return;
        }

        if (action == null) {
            PrintWriter out = response.getWriter();
            out.write("Wrong Method");
        }
        PrintWriter out = response.getWriter();

        try {
            switch (action) {
                case "ViewAll":
                    ViewAll(request, response);
                    break;
                case "delete":
                    DeleteOrder(request, response);
                    break;
                case "edit":
                    ViewEdit(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void ViewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<OrderModel> Orders;
        Orders = OrderDAO.getAll();
        request.setAttribute("listOrders", Orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/OrderTable.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderModel Order = OrderDAO.getOne(id);
        request.setAttribute("order", Order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/OrderForm.jsp");
        dispatcher.include(request, response);
    }


/*    protected void UpdateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        OrderModel client = new OrderModel(fname, lname, address, phone);
        OrderDAO.update(id, client);
        response.sendRedirect("/admin/clients.jsp?method=ViewAll");
    } */

    private void DeleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDAO.delete(id);
        response.sendRedirect("/admin/orders.jsp?method=ViewAll");
    }

}
