/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.ProductDAO;
import Model.ProductModel;
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
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private ProductDAO ProductDAO;

    public void init() {
        ProductDAO = new ProductDAO();
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
                case "AddProduct":
                    AddProduct(request, response);
                    break;
                case "UpdateProduct":
                    UpdateProduct(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("method");
        HttpSession session = request.getSession();

        if (!action.equals("ViewCards")) {
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("/admin/login.jsp");
                return;
            }
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
                    DeleteProduct(request, response);
                    break;
                case "ViewAdd":
                    ViewAdd(request, response);
                    break;
                case "ViewCards":
                    ViewCards(request, response);
                    break;
                case "edit":
                    ViewEdit(request, response);
                    break;
                default:
                    out.write("Wrong Method");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void ViewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<ProductModel> Products;
        Products = ProductDAO.getAll();
        request.setAttribute("listProducts", Products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ProductTable.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<ProductModel> Products;
        Products = ProductDAO.getAll();
        request.setAttribute("listProducts", Products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ProductCards.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ProductForm.jsp");
        dispatcher.include(request, response);
    }

    protected void ViewEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductModel Product = ProductDAO.getOne(id);
        request.setAttribute("product", Product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("parts/ProductForm.jsp");
        dispatcher.include(request, response);
    }

    protected void AddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        Part imgfile = request.getPart("image");
        FileInputStream fis = (FileInputStream) imgfile.getInputStream();
        int length = Math.toIntExact(imgfile.getSize());
        ProductModel product = new ProductModel(title, description, price);
        ProductDAO.add(product, fis, (int) length);
        response.sendRedirect("/admin/index.jsp?method=ViewAll");
    }

    protected void UpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        ProductModel product = new ProductModel(title, description, price);
        Part imgfile = request.getPart("image");
        if (imgfile.getSize() > 0) {
            FileInputStream fis = (FileInputStream) imgfile.getInputStream();
            int length = Math.toIntExact(imgfile.getSize());
            ProductDAO.updateimg(id, product, fis, (int) length);
        } else {
            ProductDAO.update(id, product);
        }
        response.sendRedirect("/admin/index.jsp?method=ViewAll");
    }

    private void DeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO.delete(id);
        response.sendRedirect("/admin/index.jsp?method=ViewAll");
    }

}
