/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.ProductCart;
import Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductCartDAO {

    private static final String SELECT_BYORDER_PRODUCTCART = "select * from cartProduct where orderid =?;";
    private static final String INSERT_PRODUCTCART_SQL = "INSERT INTO cartProduct" + "  (orderid, productid, qt, price) VALUES "
            + " (?, ?, ?, ?);";

    public List<ProductCart> getByOrder(int orderid) throws SQLException, ClassNotFoundException {
        ProductDAO ProductDAO = new ProductDAO();
        List<ProductCart> list = new ArrayList<>();
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BYORDER_PRODUCTCART);
        preparedStatement.setInt(1, orderid);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int productid = resultSet.getInt("productid");
            ProductModel product = ProductDAO.getOne(productid);
            int qt = resultSet.getInt("qt");
            float price = resultSet.getFloat("price");
            list.add(new ProductCart(id, product, qt, price));
        }
        return list;
    }

    public void add(int orderid, int productid, int qt, float price) throws SQLException, ClassNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTCART_SQL);
        preparedStatement.setInt(1, orderid);
        preparedStatement.setInt(2, productid);
        preparedStatement.setInt(3, qt);
        preparedStatement.setFloat(4, price);
        preparedStatement.executeUpdate();
    }

}
