package uts.wsd.controller;

import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import uts.wsd.model.dao.DBConnector;
import uts.wsd.model.dao.DBManager;
import uts.wsd.model.User;

public class UserTest {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;

    public static void main(String[] args) throws SQLException {
        (new UserTest()).runQueries();
    }

    public UserTest() {
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
                    testAdd();
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
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }

    private void testAdd() {
        System.out.print("Full name: ");
        String name = in.nextLine();

        System.out.print("Email: ");
        String email = in.nextLine();

        System.out.print("Password: ");
        String password = in.nextLine();

        System.out.print("Role (Customer or Staff): ");
        String role = in.nextLine();

        System.out.print("Phone:");
        String phone = in.nextLine();

        try {
            db.addUser(name, email, password, role, phone);
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User added");
    }

    private void testRead() throws SQLException {
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        User user = db.findUserEmailPass(email, password);

        if (user != null) {
            System.out.println("User " + user.getEmail() + " exists in database.");
        } else {
            System.out.println("User does not exist or invalid password");
        }
    }

    private void testUpdate() {
        System.out.print("Select email to update: ");
        String currentEmail = in.nextLine();
        try {
            if (db.checkUser(currentEmail)) {

                System.out.print("Email: ");
                String email = in.nextLine();
                System.out.print("Password: ");
                String password = in.nextLine();

                System.out.print("Name: ");
                String name = in.nextLine();
                System.out.print("Phone: ");
                String phone = in.nextLine();
                
                db.updateUser(currentEmail, name, email, password, phone);
                System.out.println("User updated");
            } else {
                System.out.println("User does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void testDelete() {
        System.out.print("Email to delete: ");
        String email = in.nextLine();

        try {
            if (db.checkUser(email)) {
                db.deleteUser(email);
                System.out.println("User updated");
            } else {
                System.out.println("User does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
