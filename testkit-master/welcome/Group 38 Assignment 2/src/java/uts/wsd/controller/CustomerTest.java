package uts.wsd.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.wsd.model.dao.DBConnector;
import uts.wsd.model.dao.DBManager;
import uts.wsd.model.Customer;
/**
 *
 * @author kamenriderfourze
 */
public class CustomerTest {
private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;

    public static void main(String[] args) throws SQLException {
        (new CustomerTest()).runQueries();
    }

    public CustomerTest() {
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
                case 's':
                    showAll();
                    break;
                    case 'a':
                    changeStatus();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }
 
private void testAdd(){
        System.out.print("Customer name: ");
        String name=in.nextLine(); 
         System.out.print("Customer email: ");
        String email=in.nextLine(); 
         System.out.print("Customer password: ");
        String password=in.nextLine();
         System.out.print("Customer phone: ");
        String phone=in.nextLine(); 
        try{
            db.addCustomer(email, name, password, phone);
        }catch (SQLException ex){
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE,null,ex); 
        }
        System.out.println("Customer is added to to database"); 
            
        }
    private void testRead() throws SQLException{
        System.out.print("Customer name: ");
        String name=in.nextLine();
        System.out.print("Customer phone: ");
        String phone=in.nextLine();
        Customer customer=db.findCustomer(name,phone);
        if(customer !=null){
            System.out.println("Customer "+customer.getName()+" exists in database");
        }else
        {System.out.println("Customer does not exist"); 
    }
    
    }
    
    private void testUpdate(){
        System.out.print("customer user id:");
       String id=in.nextLine();
        try{
            if(db.checkCustomerID(id)){
          System.out.print("customer exist, update details");
                    System.out.print("New Customer name: ");
        String name2=in.nextLine(); 
         System.out.print("New Customer phone: ");
        String phone2=in.nextLine(); 
        
         System.out.print("Customer email: ");
        String email=in.nextLine(); 
         System.out.print("Customer password: ");
        String password=in.nextLine();

        db.updateCustomer(Integer.parseInt(id), name2, phone2,email,password);
          System.out.println("customer updated!");
            }else{
                System.out.println("customer does not exist");
            }
        }catch (SQLException ex){
         Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    private void testDelete(){
          System.out.print("UserID: ");
          String id=in.nextLine();
        try{
            if(db.checkCustomerID(id)){
        db.deleteCustomer(id);
          System.out.println("customer deleted!");
            }else{
                System.out.println("customer does not exist");
            }
        }catch (SQLException ex){
         Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void showAll(){
        try{
            ArrayList<Customer>customers=db.fetchCustomer();
            System.out.println("Customer table: ");
            customers.stream().forEach((customer)->{
                System.out.printf("%-20s %-30s %-20s %-10s \n",customer.getName(),customer.getPhone(),customer.getUserID(),customer.getStatus());
            });
            System.out.println();
        }catch (SQLException ex){
             Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void changeStatus(){
                System.out.print("customer id:");
        int id = Integer.parseInt(in.nextLine());
        try{
            if(db.checkCustID(id)){
          System.out.println("customer exist, update status begin");
                    System.out.print("Enter Status ");
        String status=in.nextLine(); 
        db.updateCustomerStatus(id, status);
          System.out.println("customer updated!");
            }else{
                System.out.println("customer does not exist");
            }
        }catch (SQLException ex){
         Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    
    
   
}