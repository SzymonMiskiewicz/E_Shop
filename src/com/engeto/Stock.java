package com.engeto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Stock implements GoodsMethods {

    private static Integer id;
    private static Item item;
    private static  List<Item>items = new ArrayList<>();
    private static BigDecimal newPrice;
    private static final String URL = "jdbc:mysql://localhost:3306/eshop";
    private static final String USER = "eshopuser";
    private static final String PASSWORD = "Test1234";


    private static final String LOAD_ITEM_BY_ID = "SELECT * FROM item WHERE id = ?";
//            ("partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)");

    private static final String DELETE_ALL_ITEMS_WHICH_ARE_NOT_IN_STOCK = "DELETE FROM item WHERE numberInStock ="+ 0;

    private static final String LOAD_ALL_AVAILABLE_ITEMS = "SELECT * FROM item WHERE numberInStock > 1";

    private static final String SAVE_ITEMS =
            "INSERT INTO item (partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)";


    private static final String UPDATE_PRICE = "UPDATE item SET price = "+ newPrice + "WHERE id = "+id;

    Connection dataBaseConnection ;

    public Stock() throws SQLException {
        this.dataBaseConnection = DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public static void setId(Integer id) {Stock.id = id;}

    @Override
    public Item loadItemById(Integer id) {

        try(
                PreparedStatement preparedStatement = dataBaseConnection.prepareStatement
                        (LOAD_ITEM_BY_ID)) {


            preparedStatement.setInt(1,id);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {

                item = new Item();
                item.setId(resultset.getInt("id"));
                item.setPartNo(resultset.getString("partNo"));
                item.setSerialNo(resultset.getString("serialNo"));
                item.setName(resultset.getString("name"));
                item.setDescription(resultset.getString("description"));
                item.setNumberInStock(resultset.getInt("numberInStock"));
                item.setPrice(resultset.getBigDecimal("price"));

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void deleteAllOutOfStockItems() {

        try(
                PreparedStatement preparedStatement = dataBaseConnection.prepareStatement
                        (DELETE_ALL_ITEMS_WHICH_ARE_NOT_IN_STOCK)){


            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> loadAllAvailableItems() {

        try(
                PreparedStatement preparedStatement = dataBaseConnection.prepareStatement
                        (LOAD_ALL_AVAILABLE_ITEMS)) {

            items.add(item);

        }catch (SQLException e){
            e.printStackTrace();
        }
            return items;
    }

    @Override
    public void saveItem(Item item) {

        try (
                PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(SAVE_ITEMS)) {

            preparedStatement.setString(1, item.getPartNo());
            preparedStatement.setString(2, item.getSerialNo());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5, item.getNumberInStock());
            preparedStatement.setBigDecimal(6, item.getPrice());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {

        try(
                PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(SAVE_ITEMS)) {

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
