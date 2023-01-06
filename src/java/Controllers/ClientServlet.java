/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.ClientDAO;

import Model.ClientModel;
import java.io.FileInputStream;
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
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    private ClientDAO ClientDAO;

    public void init() {
        ClientDAO = new ClientDAO();
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
        try {
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("/admin/login.jsp");
                return;
            }
            switch (action) {
                case "AddClient":
                    AddClient(request, response);
                    break;
                case "UpdateClient":
                    UpdateClient(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                    DeleteClient(request, response);
                    break;
                case "ViewAdd":
                    ViewAdd(request, response);
                    break;
                case "edit":
                    ViewEdit(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void ViewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<ClientModel> Clients;
        Clients = ClientDAO.getAll();
        request.setAttribute("listClients", Clients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ClientTable.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ClientForm.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        ClientModel Client = ClientDAO.getOne(id);
        request.setAttribute("client", Client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ClientForm.jsp");
        dispatcher.include(request, response);
    }

    protected void AddClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        ClientModel client = new ClientModel(fname, lname, address, phone);
        ClientDAO.add(client);
        response.sendRedirect("/admin/clients.jsp?method=ViewAll");
    }

    protected void UpdateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        ClientModel client = new ClientModel(fname, lname, address, phone);
        ClientDAO.update(id, client);
        response.sendRedirect("/admin/clients.jsp?method=ViewAll");
    }

    private void DeleteClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        ClientDAO.delete(id);
        response.sendRedirect("/admin/clients.jsp?method=ViewAll");
    }

}
