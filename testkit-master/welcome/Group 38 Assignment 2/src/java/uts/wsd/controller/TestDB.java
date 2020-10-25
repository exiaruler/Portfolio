package uts.wsd.controller;

import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.*;
import uts.wsd.model.dao.DBConnector;
import uts.wsd.model.dao.DBManager;
import uts.wsd.model.Product;

public class TestDB {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;

    public static void main(String[] args) throws SQLException {
        (new TestDB()).runQueries();
    }

    public TestDB() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.print("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }

    private void runQueries() throws SQLException {
        char c;
        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'c':
                    testAddy();
                    break;
                case 'z':
                    testAddx();
                    break;
                case 'r':
                    testRead();
                    break;
                case 'u':
                    testUpdate();
                    break;
                case 'd':
                    testDelete();
                    break;
                case 's':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }

    private void testAdd() {
        System.out.print("Product Name: ");
        String name = in.nextLine();

        System.out.print("Product Description: ");
        String desc = in.nextLine();

        System.out.print("Product Type: ");
        String type = in.nextLine();
        System.out.print("Product Brand: ");
        String brand = in.nextLine();
        System.out.print("Product Price : ");
        double price = Double.parseDouble(in.nextLine());
        System.out.print("product Stock:");
        int stock = Integer.parseInt(in.nextLine());

        try {
            db.addProduct(name, price, desc, stock, type, brand);
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Product added");
    }

     private void testAddy() {
        System.out.print("Credit card: ");
        Boolean b1 = Boolean.parseBoolean(in.nextLine());  
         System.out.print("Credit payment: ");
        Boolean b2 = Boolean.parseBoolean(in.nextLine());  
         System.out.print("Credit payal: ");
        Boolean b3 = Boolean.parseBoolean(in.nextLine());  


        try {
            db.addPayment(b1, b2, b3);
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("payment method added");
    }
    
    private void testAddx() {
        System.out.print("Credit Name: ");
        String name = in.nextLine();

        System.out.print("Credit No: ");
        long creditNo = Long.parseLong(in.nextLine());

        System.out.print("Credit Exp");

        System.out.print("Enter the year: ");
        int year = Integer.parseInt(in.nextLine());
        System.out.print("Enter the month: ");
        int month = Integer.parseInt(in.nextLine());
         System.out.print("Enter the day: ");
        int day = Integer.parseInt(in.nextLine());
        //Getting the current date value
        LocalDate givenDate = LocalDate.of(year, month, day);
        System.out.print("creditsec Stock:");
        int credSec = Integer.parseInt(in.nextLine());

        try {
            db.addCreditCard(name, creditNo, credSec, givenDate);
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("card added");
    }

    private void testRead() throws SQLException {
        System.out.print("Product Name: ");
        String name = in.nextLine();
        System.out.print("Product Type: ");
        String type = in.nextLine();

        Product product = db.findProduct(name, type);

        if (product != null) {
            System.out.println("Product " + product.getProductName() + "exists in database.");
        } else {
            System.out.println("Product does not exist");
        }
    }

    private void testUpdate() {
        System.out.print("product id:");
        int id = Integer.parseInt(in.nextLine());

        try {
            if (db.checkProductID(id)) {
                System.out.print("Product Name: ");
                String name = in.nextLine();
                System.out.print("Product Type: ");
                String type = in.nextLine();
                System.out.print("Product Price : ");
                double price = Double.parseDouble(in.nextLine());
                System.out.print("Product Description  ");
                String desc = in.nextLine();
                System.out.print("product Stock:");
                int stock = Integer.parseInt(in.nextLine());

                System.out.print("Product Brand: ");
                String brand = in.nextLine();
                db.updateProduct(id, name, price, desc, stock, type, brand);
                System.out.println("Product was updated");
            } else {
                System.out.println("Product does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void testDelete() {
        System.out.print("Product Name: ");
        String name = in.nextLine();
        System.out.print("Product Type: ");
        String type = in.nextLine();

        try {
            if (db.checkProduct(name, type)) {
                db.deleteProduct(name, type);
                System.out.println("product deleted");
            } else {
                System.out.println("product does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAll() {
        try {
            ArrayList<Product> products = db.fetchProducts();
            System.out.println("Product Table: ");
            products.stream().forEach((product) -> {
                System.out.printf("%-20s %-30s %-20s %-10s \n", product.getProductName(), product.getProductPrice(), product.getProductDescription(), product.getProductStock(), product.getProductType(), product.getProductBrand());
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
