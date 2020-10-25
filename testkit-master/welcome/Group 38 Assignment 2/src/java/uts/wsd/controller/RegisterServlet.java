/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.Customer;
import uts.wsd.model.Staff;
import uts.wsd.model.User;
import uts.wsd.model.dao.DBManager;

/**
 *
 * @author Eric
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();//1- retrieve the current session
        Validator validator = new Validator();//2- create an instance of the Validator class  
        String email = request.getParameter("email");//3- capture the posted email     
        String password = request.getParameter("password");//4- capture the posted password
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        DBManager manager = (DBManager) session.getAttribute("manager");//5- retrieve the manager instance from session 
        validator.clear(session);

        if (validator.checkNameEmpty(name)) {
            session.setAttribute("nameErr", "Error: Name is empty");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (validator.checkEmailEmpty(email)) {
            session.setAttribute("emailErr", "Error: Email is empty");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");//8-set incorrect email error to the session           
            request.getRequestDispatcher("register.jsp").include(request, response);//9- redirect user back to the login.jsp
        } else if (validator.checkPassEmpty(password)) {
            session.setAttribute("passErr", "Error: Password is empty");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");//11-set incorrect password error to the session           
            request.getRequestDispatcher("register.jsp").include(request, response);//12- redirect user back to the login.jsp   
        } else if (validator.checkPhoneEmpty(phone)) {
            session.setAttribute("phoneErr", "Error: Phone is empty");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Error: Phone format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else {
            try {
                User exist = manager.findUserEmailPass(email, password);
                if (exist != null) {
                    session.setAttribute("existErr", "User already exists in the database");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                } else {
                    manager.addUser(name, email, password, role, phone);
                    User user = manager.findUserEmailPass(email, password);
                    session.setAttribute("user", user);
                    
                    Customer customer = manager.findCustomerByID(user.getUserID());
                    Staff staff = manager.findStaff(user.getUserID());
                    if(customer != null){
                        session.setAttribute("customer", customer);
                        session.setAttribute("staff", null);
                    } else if(staff != null){
                        session.setAttribute("staff", staff);
                        session.setAttribute("customer", null);
                    }
                    request.getRequestDispatcher("main.jsp").include(request, response);
                }
                //6- find user by email and password
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
