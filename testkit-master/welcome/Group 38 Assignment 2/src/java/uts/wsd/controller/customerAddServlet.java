package uts.wsd.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samuelli
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.dao.DBManager;
import uts.wsd.model.Customer;

public class customerAddServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();  
        String email = request.getParameter("email");  
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        DBManager manager = (DBManager) session.getAttribute("manager");
        validator.clear(session);
        
     
        if(validator.checkCustomerEmpty(name, phone, email, password)){
             session.setAttribute("emptyErr", "Please enter all fields");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        }else if (validator.checkNameEmpty(name)) {
            session.setAttribute("nameErr", "Error: Name is empty");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (validator.checkEmailEmpty(email)) {
            session.setAttribute("emailErr", "Error: Email is empty");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");         
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (validator.checkPassEmpty(password)) {
            session.setAttribute("passErr", "Error: Password is empty");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");        
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (validator.checkPhoneEmpty(phone)) {
            session.setAttribute("phoneErr", "Error: Phone is empty");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Error: Phone format is incorrect");
            request.getRequestDispatcher("addCustomer.jsp").include(request, response);
        } else {
            try {
                Customer exist = manager.findCustomer(name, phone);
                if (exist != null) {
                    session.setAttribute("existErr", "Customer already exists in the database");
                    request.getRequestDispatcher("addCustomer.jsp").include(request, response);
                } else {
                    manager.addCustomer(email, name, password,phone);
                    Customer customer =manager.findCustomer(name, phone);
                     
                    session.setAttribute("customer", customer);
                     session.setAttribute("added", "Customer Added");
                    request.getRequestDispatcher("addCustomer.jsp").include(request, response); 
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(customerAddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }

    }
    
}
