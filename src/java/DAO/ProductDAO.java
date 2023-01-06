/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ProductModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProductDAO {

    private static final String SELECT_ONE_PRODUCT = "select * from products where id =?;";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id = ?;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + "  (title, description, price, image) VALUES "
            + " (?, ?, ?, ?);";
    private static final String UPDATE_PRODUCT_SQL = "update products set title = ?,description= ?, price =? where id = ?;";
    private static final String UPDATE_PRODUCTIMG_SQL = "update products set title = ?,description= ?, price =?, image=? where id = ?;";

    public List<ProductModel> getAll() throws SQLException, ClassNotFoundException {
        List<ProductModel> list = new ArrayList<>();
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");
            list.add(new ProductModel(id, title, description, price));
        }
        return list;
    }

    public ProductModel getOne(int id) throws SQLException, ClassNotFoundException {
        ProductModel product = null;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_PRODUCT);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");
            product = new ProductModel(id, title, description, price);
        }
        return product;
    }

    public void add(ProductModel Product, FileInputStream fis, int length) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        preparedStatement.setString(1, Product.getTitle());
        preparedStatement.setString(2, Product.getDescription());
        preparedStatement.setFloat(3, Product.getPrice());
        preparedStatement.setBinaryStream(4, (InputStream) fis, (int) (length));
        preparedStatement.executeUpdate();
    }

    public void update(int id, ProductModel Product) throws SQLException, ClassNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
        preparedStatement.setString(1, Product.getTitle());
        preparedStatement.setString(2, Product.getDescription());
        preparedStatement.setFloat(3, Product.getPrice());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }

    public void updateimg(int id, ProductModel Product, FileInputStream fis, int length) throws SQLException, ClassNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTIMG_SQL);
        preparedStatement.setString(1, Product.getTitle());
        preparedStatement.setString(2, Product.getDescription());
        preparedStatement.setFloat(3, Product.getPrice());
        preparedStatement.setBinaryStream(4, (InputStream) fis, (int) (length));
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }

}
