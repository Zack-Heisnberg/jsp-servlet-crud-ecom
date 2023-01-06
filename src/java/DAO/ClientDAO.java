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

import Model.ClientModel;
import java.io.FileNotFoundException;

public class ClientDAO {

    private static final String SELECT_ONE_CLIENT = "select * from clients where id = ?;";
    private static final String SELECT_ALL_CLIENTS = "select * from clients";
    private static final String DELETE_CLIENT_SQL = "delete from clients where id = ?;";
    private static final String INSERT_CLIENT_SQL = "INSERT INTO clients" + "  (fname, lname, address, phone) VALUES "
            + " (?, ?, ?, ?);";
    private static final String UPDATE_CLIENT_SQL = "update clients set fname = ?,lname= ?, address =?, phone =? where id = ?;";

    public List<ClientModel> getAll() throws SQLException, ClassNotFoundException {
        List<ClientModel> list = new ArrayList<>();
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            list.add(new ClientModel(id, fname, lname, address, phone));
        }
        return list;
    }

    public ClientModel getOne(int id) throws SQLException, ClassNotFoundException {
        ClientModel client = null;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_CLIENT);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            client = new ClientModel(id, fname, lname, address, phone);
        }

        System.out.println(client);
        return client;
    }

    public int add(ClientModel Client) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, Client.getFname());
        preparedStatement.setString(2, Client.getLname());
        preparedStatement.setString(3, Client.getAddress());
        preparedStatement.setString(4, Client.getPhone());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int key = rs.next() ? rs.getInt(1) : 0;
        return key;
    }

    public void update(int id, ClientModel Client) throws SQLException, ClassNotFoundException {
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL);
        preparedStatement.setString(1, Client.getFname());
        preparedStatement.setString(2, Client.getLname());
        preparedStatement.setString(3, Client.getAddress());
        preparedStatement.setString(4, Client.getPhone());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection connection = new Database().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }

}
