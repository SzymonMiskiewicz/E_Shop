package com.engeto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {



    public static void main(String[] args)  {

        Stock stock = new Stock();

        //loads item by id
        System.out.println(stock.loadItemById(2));
        // end region

        // delete items which are not in stock
        stock.deleteAllOutOfStockItems();
        //end region

        // load all available items
        for (Item item: stock.loadAllAvailableItems()) {
            System.out.println(item);
        }
        //end region

        //add new items
        Item printer = new Item(
                5,"105","5000","Laser Printer HP 102",
                "recomended to print photos",12, BigDecimal.valueOf(400.20));
        Item usbCable = new Item(
                6,"106","6000","Cable USB",
                "USB cable type C",6, BigDecimal.valueOf(11.50));
        stock.saveItem(printer);
        stock.saveItem(usbCable);
        //end region

        // update price in DB
        stock.updatePrice(4,BigDecimal.valueOf(22.50));
        //end region

        try {
            stock.dataBaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
