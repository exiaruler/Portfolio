/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eric
 */
public class User {
    private int userID;
    private String email;
    private String password;
    
    public User(int userID, String email, String password){
        this.userID = userID;
        this.email = email;
        this.password = password;
    }
    
    
    /*
    public static void createUser(HttpSession session,
                                  String name, String phone, String role, int userID) {
        if(role.matches("Customer")){
            Customer customer = new Customer(name, phone, userID);
            session.setAttribute("customer", customer);
        }
        else if(role.matches("Staff")){
            Staff staff = new Staff(name, phone, userID);
            session.setAttribute("staff", staff);
        }
    }
    
    public static Customer checkCustomer(HttpSession session){
        Customer customer = (Customer)session.getAttribute("customer");
        return customer;
    }
    
    public static Staff checkStaff(HttpSession session){
        Staff staff = (Staff)session.getAttribute("staff");
        return staff;
    }
    
    public static String getName(HttpSession session){
        Customer customer = checkCustomer(session);
        Staff staff = checkStaff(session);
        
        if(customer != null){
            return customer.getName();
        }
        else if(staff != null){
            return staff.getName();
        }
        else{
            return "n/a";
        }
    }
    */
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }

    public int getUserID() {
        return userID;
    }    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*
    public static String getPhone(HttpSession session){
        Customer customer = checkCustomer(session);
        Staff staff = checkStaff(session);
        
        if(customer != null){
            return customer.getPhone();
        }
        else if(staff != null){
            return staff.getPhone();
        }
        else{
            return "n/a";
        }
    }
    
    public static String getRole(HttpSession session){
        Customer customer = checkCustomer(session);
        Staff staff = checkStaff(session);
        
        if(customer != null){
            return "Customer";
        }
        else if(staff != null){
            return "Staff";
        }
        else{
            return "Anonymous";
        }
    }
    */
}
