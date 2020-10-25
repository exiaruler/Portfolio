/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import uts.wsd.model.AccessLogs;
import uts.wsd.model.CreditCard;
import uts.wsd.model.Customer;
import uts.wsd.model.Orders;
import uts.wsd.model.Payment;
import uts.wsd.model.Product;
import uts.wsd.model.Staff;
import uts.wsd.model.User;
import uts.wsd.model.Orders;

/**
 *
 * @author Eric
 */
public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public void addAccessLog(String userID, String dateAction, String timeAction, String userAction) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  Users WHERE userID=" + userID;
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            st.executeUpdate("INSERT INTO AppAccessLog (userID, dateAccessed, timeAccessed, userAction) VALUES (" + userID + ",'" + dateAction + "','" + timeAction + "','" + userAction + "')");
        }
    }

    public ArrayList<AccessLogs> fetchAllAccessLog(String userID) throws SQLException, NullPointerException {
        ArrayList<AccessLogs> logs = new ArrayList<>();
        String query = "SELECT * FROM AppAccessLog WHERE userID=" + userID;
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int accessID = rs.getInt(1);
            int logUserID = rs.getInt(2);
            String dateAccessed = rs.getString(3);
            String timeAccessed = rs.getString(4);
            String userAction = rs.getString(5);
            AccessLogs accessLog = new AccessLogs(accessID, logUserID, dateAccessed, timeAccessed, userAction);
            logs.add(accessLog);
        }
        return logs;
    }

    public ArrayList<AccessLogs> fetchAccessLogDate(String userID, String date) throws SQLException, NullPointerException {
        ArrayList<AccessLogs> logs = new ArrayList<>();
        String query = "SELECT * FROM AppAccessLog WHERE userID=" + userID + "AND dateAccessed='" + date + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int accessID = rs.getInt(1);
            int logUserID = rs.getInt(2);
            String dateAccessed = rs.getString(3);
            String timeAccessed = rs.getString(4);
            String userAction = rs.getString(5);
            AccessLogs accessLog = new AccessLogs(accessID, logUserID, dateAccessed, timeAccessed, userAction);
            logs.add(accessLog);
        }
        return logs;
    }

    public boolean checkUser(int userID) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  USERS WHERE userID=" + userID;
        ResultSet rs = st.executeQuery(query);
        return rs.next();
    }

    public boolean checkUser(String email) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  USERS WHERE userEmail='" + email + "'";
        ResultSet rs = st.executeQuery(query);

        return rs.next();
    }

    //User data management 
    //Find user by userID
    public User findUser(String userID) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  USERS WHERE userID=" + userID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int ID = rs.getInt(1);
            String userEmail = rs.getString(2);
            String userPass = rs.getString(3);
            return new User(ID, userEmail, userPass);
        }
        return null;
    }

    //Find user by email and password in the database   
    public User findUserEmailPass(String email, String password) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  USERS WHERE userEmail='" + email + "' AND userPassword='" + password + "'";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int ID = rs.getInt(1);
            String userEmail = rs.getString(2);
            String userPass = rs.getString(3);

            if (email.equals(userEmail) && password.equals(userPass)) {
                return new User(ID, userEmail, userPass);
            }
        }
        return null;
    }

    //Find user by full name and phone
    public User findUserNamePhone(String name, String phone) throws SQLException, NullPointerException {
        String custQuery = "SELECT * FROM  CUSTOMER WHERE custName='" + name + "' AND custPhone='" + phone + "'";
        String staffQuery = "SELECT * FROM STAFF WHERE staffName=" + name + "' AND staffPhone='" + phone + "'";
        ResultSet custRs = st.executeQuery(custQuery);

        ResultSet userRs;
        if (custRs.next()) {
            String userID = custRs.getString(4);
            String userQuery = "SELECT * FROM  USERS WHERE userID=" + userID;
            userRs = st.executeQuery(userQuery);
            while (userRs.next()) {
                int ID = userRs.getInt(1);
                String email = userRs.getString(2);
                String password = userRs.getString(3);
                return new User(ID, email, password);
            }
        }
        ResultSet staffRs = st.executeQuery(staffQuery);
        if (staffRs.next()) {
            String userID = staffRs.getString(4);
            String userQuery = "SELECT * FROM  USERS WHERE userID=" + userID;
            userRs = st.executeQuery(userQuery);
            while (userRs.next()) {
                int ID = userRs.getInt(1);
                String email = userRs.getString(2);
                String password = userRs.getString(3);
                return new User(ID, email, password);
            }
        }

        return null;
    }

    //Add a user-data into the database
    public void addUser(String name, String email, String password, String role, String phone) throws SQLException {
        st.executeUpdate("INSERT INTO Users (userEmail, userPassword) VALUES ('" + email + "','" + password + "')");
        ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE userEmail='" + email + "' AND userPassword='" + password + "'");
        String userID;

        if (rs.next()) {
            System.out.println(role);
            if (role.matches("Staff")) {
                userID = rs.getString(1);
                System.out.println(role);
                st.executeUpdate("INSERT INTO Staff (staffName, staffPhone, userID, staffRole) VALUES ('" + name + "','" + phone + "'," + userID + ",'Staff')");
            } else if (role.matches("Customer")) {
                userID = rs.getString(1);
                System.out.println(role);
                st.executeUpdate("INSERT INTO Customer (custName, custPhone, userID, active) VALUES ('" + name + "','" + phone + "'," + userID + "," + true +")");
            }
        }
    }

    //update a user details in the database   
    public void updateUser(String userID, String name, String email, String password, String phone) throws SQLException {
        st.executeUpdate("UPDATE Users SET userEmail='" + email + "', userPassword='" + password + "'" + " WHERE userID=" + userID);

        String custQuery = "SELECT * FROM  Customer WHERE userID=" + userID;
        String staffQuery = "SELECT * FROM Staff WHERE userID=" + userID;

        ResultSet custRs = st.executeQuery(custQuery);
        if (custRs.next()) {
            st.executeUpdate("UPDATE Customer SET custName='" + name + "', custPhone='" + phone + "'" + " WHERE userID=" + userID);
        }
        ResultSet staffRs = st.executeQuery(staffQuery);
        if (staffRs.next()) {
            st.executeUpdate("UPDATE Staff SET staffName='" + name + "', staffPhone='" + phone + "'" + " WHERE userID=" + userID);
        }
    }

    //delete a user from the database   
    public void deleteUser(String userID) throws SQLException {
        System.out.println(userID);
        String query = "SELECT * FROM  USERS WHERE userID=" + userID;
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            String custQuery = "SELECT * FROM  Customer WHERE userID=" + userID;
            String staffQuery = "SELECT * FROM Staff WHERE userID=" + userID;

            ResultSet custRs = st.executeQuery(custQuery);
            if (custRs.next()) {
                st.executeUpdate("DELETE FROM Customer WHERE userID=" + userID);
                st.executeUpdate("DELETE FROM Users WHERE userID=" + userID);
            }

            ResultSet staffRs = st.executeQuery(staffQuery);
            if (staffRs.next()) {
                st.executeUpdate("DELETE FROM Staff WHERE userID=" + userID);
                st.executeUpdate("DELETE FROM Users WHERE userID=" + userID);
            }
        }
    }


    public Staff findStaff(int userID) throws SQLException {
        String query = "SELECT * FROM  Staff WHERE userID=" + userID + "";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String staffID = rs.getString(1);
            String staffName = rs.getString(2);
            String staffPhone = rs.getString(3);
            return new Staff(staffName, staffPhone, userID);
        }
        return null;
    }

    //Product Database Manager
    public Product findProduct(String name, String type) throws SQLException {
        String read = "SELECT * FROM PRODUCT WHERE productName = '" + name + "' AND productType='" + type + "'";
        ResultSet rs = st.executeQuery(read);
        while (rs.next()) {
            String productName = rs.getString(2);
            String productType = rs.getString(6);
            if (productName.equals(name) && productType.equals(type)) {
                int productID = rs.getInt(1);
                double productPrice = rs.getDouble(3);
                String productDescription = rs.getString(4);
                int productStock = rs.getInt(5);
                String productBrand = rs.getString(7);

                return new Product(productID, productName, productPrice, productDescription, productStock, productType, productBrand);
            }
        }
        return null;
    }

    public void addProduct(String name, double price, String description, int stock, String type, String brand) throws SQLException {
        st.executeUpdate("INSERT INTO PRODUCT(productName, productPrice, productDescription, productStock, productType, productBrand)" + "VALUES ('" + name + "', " + price + ", '" + description + "', " + stock + ", '" + type + "','" + brand + "')");

    }

    public void updateProduct(int id, String name, double price, String description, int stock, String type, String brand) throws SQLException {
        st.executeUpdate("UPDATE PRODUCT SET productName ='" + name + "',productPrice = " + price + ", productDescription = '" + description + "', productStock = " + stock + ", productType = '" + type + "', productbrand = '" + brand + "' WHERE productID = " + id + " ");
    }

    public void deleteProduct(String name, String type) throws SQLException {

        st.executeUpdate("DELETE FROM Product WHERE productName='" + name + "' and productType = '" + type + "'");

    }

    public ArrayList<Product> fetchProducts() throws SQLException {
        String fetch = "SELECT * FROM PRODUCT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Product> temp = new ArrayList<>();

        while (rs.next()) {
            int productID = rs.getInt(1);
            String productName = rs.getString(2);
            double productPrice = rs.getDouble(3);
            String productDescription = rs.getString(4);
            int productStock = rs.getInt(5);
            String productType = rs.getString(6);
            String productBrand = rs.getString(7);
            temp.add(new Product(productID, productName, productPrice, productDescription, productStock, productType, productBrand));
        }
        return temp;
    }

    public ArrayList<Product> findProductList(String name, String type) throws SQLException {
        String fetch = "SELECT * FROM PRODUCT WHERE productName= '" + name + "' AND productType='" + type + "'";
        if (name.equals("") && !type.equals("")) {
            fetch = "SELECT * FROM PRODUCT WHERE productType ='" + type + "'";
        } else if (!name.equals("") && type.equals("")) {
            fetch = "SELECT * FROM PRODUCT WHERE productName ='" + name + "'";
        } else if (name.equals("") && type.equals("")) {
            fetch = "SELECT * FROM PRODUCT";
        }

        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Product> temp = new ArrayList<>();

        while (rs.next()) {
            String productName = rs.getString(2);
            String productType = rs.getString(6);
            if (productName.equals(name) && productType.equals(type)) {
                int productID = rs.getInt(1);
                double productPrice = rs.getDouble(3);
                String productDescription = rs.getString(4);
                int productStock = rs.getInt(5);
                String productBrand = rs.getString(7);
                temp.add(new Product(productID, productName, productPrice, productDescription, productStock, productType, productBrand));
            }
        }
        return temp;
    }

    public boolean checkProductID(int id) throws SQLException {
        String fetch = "SELECT productID from Product";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int productId = rs.getInt(1);
            if (productId == id) {
                return true;
            }
        }
        return false;
    }

    public Product findProductID(int id) throws SQLException {
        String fetch = "SELECT * FROM  PRODUCT WHERE productID=" + id + "";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int productID = rs.getInt(1);
            String productName = rs.getString(2);
            double productPrice = rs.getDouble(3);
            String productDescription = rs.getString(4);
            int productStock = rs.getInt(5);
            String productType = rs.getString(6);
            String productBrand = rs.getString(7);

            return new Product(productID, productName, productPrice, productDescription, productStock, productType, productBrand);

        }

        return null;

    }

    public boolean checkProduct(String name, String type) throws SQLException {
        String fetch = "SELECT * FROM PRODUCT WHERE productName= '" + name + "' AND productType='" + type + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String productName = rs.getString(2);
            String productType = rs.getString(6);
            if (productName.equals(name) && productType.equals(type)) {
                return true;
            }
        }
        return false;
    }

//Add a user-data into the database   
    public void addCustomer(String email, String name, String password, String dob, String number) throws SQLException {                   //code for add-operation       
        st.executeUpdate("INSERT INTO CUSTOMER" + "VALUES ('" + name + ", " + "," + number + "'");
        st.executeUpdate("INSERT INTO USERS" + "VALUES ('" + email + "," + password + "," + dob + ",");
    }

//update a user details in the database   
    public void updateCustomer(String email, String name, String password, String number) throws SQLException {
        //code for update-operation   
        st.executeUpdate("UPDATE CUSTOMER SET CUSTNAME='" + name + "',CUSTPHONE='" + number);
        st.executeUpdate("UPDATE USERS SET USEREMAIL='" + email + "',USERPASSWORD='" + password);
    }

//delete a user from the database   
    public boolean checkPaymentID(int id) throws SQLException {
        String fetch = "SELECT paymentID from Payment";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int productId = rs.getInt(1);
            if (productId == id) {
                return true;
            }
        }
        return false;
    }

    // Returns integer of paymentID
    public Integer addPayment(boolean paymentCreditCard, boolean paymentConfirm, boolean paymentPaypal) throws SQLException {
        st.executeUpdate("INSERT INTO Payment(paymentCreditCard , paymentConfirm, paymentPaypal)" + "VALUES (" + paymentCreditCard + ", " + paymentConfirm + "," + paymentPaypal + ")", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
            int paymentID = rs.getInt(1);
            return paymentID;
        }
        return null;
    }

    public void addCreditCard(String creditName, Long creditNo, int creditSecurityNo, LocalDate creditExpiryDate, int paymentID) throws SQLException {
        st.executeUpdate("INSERT INTO CreditCard(creditName, creditNo, creditSecurityNo, creditExpiryDate, paymentID)" + "VALUES ('" + creditName + "', " + creditNo + "," + creditSecurityNo + ", '" + creditExpiryDate + "', " + paymentID + ")");
    }

    public CreditCard findCreditCard(Long number) throws SQLException {
        String fetch = "SELECT * FROM CreditCard WHERE creditNo=" + number;
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int cardID = rs.getInt(1);
            String creditName = rs.getString(2);
            Long creditNo = rs.getLong(3);
            int creditSecurityNo = rs.getInt(4);
            Date creditExpiryDate = rs.getDate(5);
            int paymentID = rs.getInt(6);
            return new CreditCard(cardID, creditName, creditNo, creditSecurityNo, creditExpiryDate);
        }
        return null;
    }

    public void updatePaymentConfirmation(int id, Boolean bool) throws SQLException {
        st.executeUpdate("UPDATE PAYMENT SET PaymentCONFIRM=" + bool + " WHERE paymentID=" + id);
    }

    public void updatePayment(int id, boolean paymentCreditCard, boolean paymentConfirm, boolean paymentPaypal) throws SQLException {
        st.executeUpdate("UPDATE PAYMENT SET paymentCreditCard=" + paymentCreditCard + ", SET paymentConfirm=" + paymentConfirm + ", SET paymentPaypal=" + paymentPaypal + " WHERE paymentID=" + id);
    }

    public Payment findPaymentID(int id) throws SQLException {
        String fetch = "SELECT * FROM PAYMENT WHERE paymentID=" + id;
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int paymentID = rs.getInt(1);
            Boolean paymentCreditCard = rs.getBoolean(2);
            Boolean paymentConfirm = rs.getBoolean(3);
            Boolean paymentPaypal = rs.getBoolean(4);
            return new Payment(paymentID, paymentCreditCard, paymentConfirm, paymentPaypal);
        }
        return null;
    }

    public CreditCard findCreditCardID(int number) throws SQLException {
        String fetch = "SELECT * FROM CreditCard WHERE cardID=" + number;
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int cardID = rs.getInt(1);
            String creditName = rs.getString(2);
            Long creditNo = rs.getLong(3);
            int creditSecurityNo = rs.getInt(4);
            Date creditExpiryDate = rs.getDate(5);
            int paymentID = rs.getInt(6);
            return new CreditCard(cardID, creditName, creditNo, creditSecurityNo, creditExpiryDate);
        }
        return null;
    }

    public void updateCreditCard(int cardID, String creditName, Long creditNo, int creditSecurityNo, LocalDate creditExpiryDate) throws SQLException {
        st.executeUpdate("UPDATE CreditCard SET creditName='" + creditName + "', SET creditNo=" + creditNo + ", SET creditSecurityNo=" + creditSecurityNo + ", SET creditExpiryDate='" + creditExpiryDate + "' WHERE cardID=" + cardID);
    }

    public void deleteCreditCard(int cardID) throws SQLException {
   
        st.executeUpdate("DELETE FROM CreditCard WHERE cardID=" + cardID);
    }

    public void deletePayment(int id) throws SQLException {
      
        st.executeUpdate("DELETE FROM Payment WHERE PaymentID=" + id);
    }
//Customer
//customer 
    public Customer findCustomer(String name, String phone) throws SQLException {
        String fetch = "SELECT * FROM CUSTOMER WHERE CUSTNAME= '" + name + "' AND CUSTPHONE='" + phone + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String customerName = rs.getString(2);
            if (customerName.equals(name)) {
                String customerPhone = rs.getString(3);
                int customerUserID = rs.getInt(4);
                boolean status = rs.getBoolean(5);
                int customerID=rs.getInt(1);
                return new Customer(customerID,customerName, customerPhone, customerUserID, status);
            }
        }
        return null;    
    }

    
    public void addCustomer(String email, String name, String password, String number) throws SQLException {
        boolean active = true;
        st.executeUpdate("INSERT INTO Users (USEREMAIL, USERPASSWORD) VALUES ('" + email + "','" + password + "')");
        ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE USEREMAIL='" + email + "' AND USERPASSWORD='" + password + "'");
        String userID;

        if (rs.next()) {
            userID = rs.getString(1);
            st.executeUpdate("INSERT INTO Customer (CUSTNAME, CUSTPHONE, USERID) VALUES ('" + name + "','" + number + "'," + userID + ")");
            st.executeUpdate("UPDATE CUSTOMER SET STATUS ='" + active + "' WHERE USERID = " + userID + " ");
        }

    }


    public void updateCustomer(int id, String name, String phone, String email, String password) throws SQLException {
     
        st.executeUpdate("UPDATE CUSTOMER SET CUSTNAME ='" + name + "', CUSTPHONE = '" + phone + "' WHERE USERID = " + id + " ");
        st.executeUpdate("UPDATE USERS SET USEREMAIL ='" + email + "', USERPASSWORD = '" + password + "' WHERE USERID = " + id + " ");

    }

    public void updateCustomerStatus(int id, String status) throws SQLException {
        boolean active;
        if (status.matches("act")) {
            active = true;
            st.executeUpdate("UPDATE CUSTOMER SET STATUS ='" + active + "' WHERE CUSTID = " + id + " ");
            System.out.print("customer activated");
        } else if (status.matches("deact")) {
            active = false;
            st.executeUpdate("UPDATE CUSTOMER SET STATUS ='" + active + "' WHERE CUSTID = " + id + " ");
            System.out.print("customer deactivated");
        }

    }

    public void deleteCustomer(String userID) throws SQLException {
   
                st.executeUpdate("DELETE FROM CUSTOMER WHERE USERID=" + userID);
                st.executeUpdate("DELETE FROM USERS WHERE USERID=" + userID);
            

        
    }

    public ArrayList<Customer> fetchCustomer() throws SQLException {
        String fetch = "SELECT * FROM CUSTOMER ";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Customer> temp = new ArrayList();

        while (rs.next()) {
            String customerName = rs.getString(2);
            String customerNumber = rs.getString(3);
            int customerUserID = rs.getInt(4);
            boolean status = rs.getBoolean(5);
             int customerID=rs.getInt(1);
            temp.add(new Customer(customerID,customerName, customerNumber, customerUserID, status));
        }
        return temp;
    }

    public boolean checkCustomer(String name, String phone) throws SQLException {
        String fetch = "SELECT * FROM CUSTOMER WHERE CUSTNAME= '" + name + "' AND CUSTPHONE='" + phone + "'";
        ResultSet rs = st.executeQuery(fetch);

        if (rs.next()) {
            String customerName = rs.getString(2);
            String customerPhone = rs.getString(3);
            if (customerName.equals(name) && customerPhone.equals(phone)) {
                return true;
            }
        }
        return false;
    }
    //checks userID
    public boolean checkCustomerID(String id) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  USERS WHERE USERID=" + id;
        ResultSet rs = st.executeQuery(query);
        return rs.next();

    }

    public boolean checkCustID(int id) throws SQLException, NullPointerException {
        String query = "SELECT * FROM  CUSTOMER WHERE CUSTID=" + id;
        ResultSet rs = st.executeQuery(query);
        return rs.next();

    }
     public Customer findCustID(int id) throws SQLException {
        String fetch = "SELECT * FROM  CUSTOMER WHERE CUSTID=" + id + "";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int customerID=rs.getInt(1);
            
            if (customerID==id) {
                String customerPhone = rs.getString(3);
                int customerUserID = rs.getInt(4);
                boolean status = rs.getBoolean(5);
                 String customerName = rs.getString(2);
                return new Customer(customerID,customerName, customerPhone, customerUserID, status);
            }
        }
        return null;    

    }
         public Customer findCustomerByID(int userID) throws SQLException {
        String query = "SELECT * FROM  CUSTOMER WHERE USERID=" + userID + "";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int custID = rs.getInt(1);
            String custName = rs.getString(2);
            String custPhone = rs.getString(3);
            Boolean active = rs.getBoolean(4);
             int customerID=rs.getInt(1);
            return new Customer(customerID,custName, custPhone, custID, active);
        }
        return null;
    }
    
//Order Database Manager
    
//Create: 
//Register user / Anonymous user can 
//create (save, submit) an order for IoT devices
    
    public Orders createOrders(int customer) throws SQLException {
        String fetch = "SELECT * FROM ORDERS WHERE custID = '" + customer + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int custID = rs.getInt(3);
            if (custID == (customer)) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                int shippingID = rs.getInt(4);
                
            return new Orders(orderID, orderDate, custID, shippingID);
            }
           //need else 
        }
        return null;
    }
    
    
//Read: 
//A customer user can view their saved order details, 
    public Orders savedOrders(int id) throws SQLException {
        String query = "SELECT * FROM ORDERS WHERE orderID=" + id + "";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int orderID = rs.getInt(1);
            String orderDate = rs.getString(2);
            int custID = rs.getInt(3);
            int shippingID = rs.getInt(4);
            
            return new Orders(orderID, orderDate, custID, shippingID);
        }
        return null;
    }
   
    
//A customer can view order history list and
    public Orders historyOrders(int customer, int order) throws SQLException {
        String fetch = "SELECT * FROM ORDERS WHERE customerID = '" + customer + "' AND orderID='" + order + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int custID = rs.getInt(3);
            if (custID==(customer)) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                int shippingID = rs.getInt(4);
                return new Orders(orderID, orderDate, custID, shippingID);
            }
        }
        return null;
    }
    
//and search the orders based on the order number and date
    
    public Orders findOrders(int id, String date) throws SQLException {
        String read = "SELECT * FROM ORDERS WHERE orderID = '" + id + "' AND orderDate='" + date + "'";
        ResultSet rs = st.executeQuery(read);
        while (rs.next()) {
            int orderID = rs.getInt(1);
            String orderDate = rs.getString(2);
            if ( (orderID == id) && (orderDate.equals(date)) ) {
                int custID = rs.getInt(3);  
                int shippingID = rs.getInt(4);
                
                return new Orders(orderID, orderDate, custID, shippingID);
            }
        }
        return null;
    }
    
//Update: A customer can update their saved order before final submission
    
    public void addOrders(String date, int customer, int shipping, int payment) throws SQLException {
        st.executeUpdate("INSERT INTO ORDERS (orderDate, custID, shippingID, paymentID)" + "VALUES ('" + date + "', " + customer + ", '" + shipping + "', " + payment + "')");
    }

    public void updateOrders (int id, String date, int payment) throws SQLException {
        st.executeUpdate("UPDATE ORDERS SET orderID ='" + id + "',orderDate " + date + ", payment= '" + payment + "' WHERE orderID = " + id + " ");
    }
    
//Delete: A customer can delete their saved order before final submission    
    
    public void deleteOrders(int order, int customer) throws SQLException {
        st.executeUpdate("DELETE FROM Orders WHERE orderID='" + order + "' and customerID = '" + customer + "'");
    }

}
