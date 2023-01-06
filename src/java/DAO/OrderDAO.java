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
import java.util.ArrayList;
import java.util.List;

import Model.OrderModel;
import Model.ProductCart;
import Model.ClientModel;

import java.io.FileNotFoundException;
import java.util.Date;
import javax.servlet.http.HttpServlet;

public class OrderDAO {

    private static final String SELECT_ONE_ORDER = "select * from orders where id =?;";
    private static final String SELECT_ALL_ORDERS = "select * from orders";
    private static final String DELETE_ORDER_SQL = "delete from orders where id = ?;";
    private static final String INSERT_ORDER_SQL = "INSERT INTO orders" + "  (clientid, total, time) VALUES "
            + " (?, ?, ?);";

    public List<OrderModel> getAll() throws SQLException, ClassNotFoundException {
        ClientDAO ClientDAO = new ClientDAO();
        ProductCartDAO ProductCartDAO = new ProductCartDAO();
        List<OrderModel> list = new ArrayList<>();
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int clientid = resultSet.getInt("clientid");
            ClientModel client = ClientDAO.getOne(clientid);
            List<ProductCart> products = ProductCartDAO.getByOrder(id);
            float total = resultSet.getFloat("total");
            Date date = resultSet.getDate("time");
            list.add(new OrderModel(id, client, products, total, date));
        }
        return list;
    }

    public OrderModel getOne(int id) throws SQLException, ClassNotFoundException {
        ClientDAO ClientDAO = new ClientDAO();
        ProductCartDAO ProductCartDAO = new ProductCartDAO();
        OrderModel order = null;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_ORDER);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int clientid = resultSet.getInt("clientid");
            List<ProductCart> products = ProductCartDAO.getByOrder(id);
            float total = resultSet.getFloat("total");
            Date date = resultSet.getDate("date");
            order = new OrderModel(id, ClientDAO.getOne(clientid), products, total, date);
        }
        return order;
    }

    public int add(int clientid, float total) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, clientid);
        preparedStatement.setFloat(2, total);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int key = rs.next() ? rs.getInt(1) : 0;
        return key;
    }

    /* public void update(int id, OrderModel Order) throws SQLException, ClassNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL);
        preparedStatement.setString(1, Order.getFname());
        preparedStatement.setString(2, Order.getLname());
        preparedStatement.setString(3, Order.getAddress());
        preparedStatement.setString(4, Order.getPhone());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    } */
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_SQL);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }

}
