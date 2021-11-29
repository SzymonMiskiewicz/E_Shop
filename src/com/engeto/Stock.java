package com.engeto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Stock implements GoodsMethods {

    private static final String LOAD_ITEM_BY_ID = "SELECT * FROM item WHERE id =" + id;
//            ("partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)");

    private static final String DELETE_ALL_ITEMS_WHICH_ARE_NOT_IN_STOCK = "DELETE FROM item WHERE numberInStock ="+ 0;

    private static final String LOAD_ALL_AVAILABLE_ITEMS = "SELECT * FROM List<Item> WHERE numberInStock > 1";

    private static final String SAVE_ITEMS =
            "INSERT INTO item (partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)";

    private static final String UPDATE_PRICE = "UPDATE item SET price ="+ newPrice + "WHERE id = "+id ;

    private static final String URL = "jdbc:mysql://localhost:3306/eshop";
    private static final String USER = "eshopuser";
    private static final String PASSWORD = "Test1234";

    private Item item;
    private List<Item>items = new ArrayList<>();

    public Stock() throws SQLException {
        Connection dataBaseConnection = DriverManager.getConnection(
                URL, USER, PASSWORD);


        dataBaseConnection.close();

    @Override
    public Item loadItemById(Integer id) {
        PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(LOAD_ITEM_BY_ID);

            preparedStatement.setString(1, item.getPartNo());
            preparedStatement.setString(2, item.getSerialNo());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5, item.getNumberInStock());
            preparedStatement.setBigDecimal(6, item.getPrice());

            preparedStatement.executeUpdate();

    }

    @Override
    public void deleteAllOutOfStockItems() {
        PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(
                DELETE_ALL_ITEMS_WHICH_ARE_NOT_IN_STOCK);

        preparedStatement.executeUpdate();


    }

    @Override
    public List<Item> loadAllAvailableItems() {
        PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(LOAD_ALL_AVAILABLE_ITEMS);

            items.add(item);

            preparedStatement.executeUpdate();
    }

    @Override
    public void saveItem(Item item) {
        PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(SAVE_ITEMS);
            preparedStatement.setString(1, item.getPartNo());
            preparedStatement.setString(2, item.getSerialNo());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5, item.getNumberInStock());
            preparedStatement.setBigDecimal(6, item.getPrice());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {

        PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(SAVE_ITEMS);


        preparedStatement.executeUpdate();

    }

}
}
