/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.AccessLogs;
import uts.wsd.model.Customer;
import uts.wsd.model.Staff;
import uts.wsd.model.User;
import uts.wsd.model.dao.DBManager;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();//1- retrieve the current session
        Validator validator = new Validator();//2- create an instance of the Validator class  
        String email = request.getParameter("email");//3- capture the posted email     
        String password = request.getParameter("password");//4- capture the posted password    
        DBManager manager = (DBManager) session.getAttribute("manager");//5- retrieve the manager instance from session
        User user = null;
        validator.clear(session);

        if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");//8-set incorrect email error to the session           
            request.getRequestDispatcher("login.jsp").include(request, response);//9- redirect user back to the login.jsp     
        } else if (validator.checkEmailEmpty(email)) {
            session.setAttribute("emailErr", "Error: Email is empty");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");//11-set incorrect password error to the session           
            request.getRequestDispatcher("login.jsp").include(request, response);//12- redirect user back to the login.jsp          
        } else if (validator.checkPassEmpty(password)) {
            session.setAttribute("passErr", "Error: Password is empty");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            try {
                user = manager.findUserEmailPass(email, password);
                if (user != null) {
                    int userID = user.getUserID();
                    Customer customer = manager.findCustomerByID(userID);
                    Staff staff = manager.findStaff(userID);
                    if (customer != null) {
                        session.setAttribute("customer", customer);
                        session.setAttribute("user", user);
                    } else if (staff != null) {
                        session.setAttribute("staff", staff);
                        session.setAttribute("user", user);
                    }
                    LocalDateTime currDateTime = LocalDateTime.now();
                    String currentDate = currDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String currentTime = currDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    manager.addAccessLog(Integer.toString(userID), currentDate, currentTime, "Login");
                    request.getRequestDispatcher("main.jsp").include(request, response);
                } else {
                    session.setAttribute("existErr", "User does not exists in the database or password invalid");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
                //6- find user by email and password
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }
}
