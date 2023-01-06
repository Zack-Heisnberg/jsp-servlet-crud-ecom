/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ClientModel;
import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductCartDAO;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author zakaria
 */
@WebServlet(name = "NewOrder", urlPatterns = {"/NewOrder"})
public class NewOrder extends HttpServlet {

    private ClientDAO ClientDAO;
    private ProductCartDAO ProductCartDAO;
    private OrderDAO OrderDAO;

    public void init() {
        ClientDAO = new ClientDAO();
        ProductCartDAO = new ProductCartDAO();
        OrderDAO = new OrderDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {

        try {
            // client
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            ClientModel client = new ClientModel(fname, lname, address, phone);
            int clientid = ClientDAO.add(client);

            // order
            Float total = Float.valueOf(request.getParameter("totalinput"));
            int orderid = OrderDAO.add(clientid, total);

            // ProductCart
            String[] productids = request.getParameterValues("productids");
            String[] productqts = request.getParameterValues("productqts");
            String[] productprices = request.getParameterValues("productprices");
            for (int i = 0; i < productids.length; i++) {
                /*
                In Production we must check for product price and total here and 
                validate if data was changed by client for security
                this version is for Education only so no need
                 */
                int productid = parseInt(productids[i]);
                int productqt = parseInt(productqts[i]);
                int productprice = parseInt(productprices[i]);
                ProductCartDAO.add(orderid, productid, productqt, productprice);
            }
            request.setAttribute("fname", fname);
            request.setAttribute("orderid", orderid);
            RequestDispatcher dispatcher = request.getRequestDispatcher("done.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(NewOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
