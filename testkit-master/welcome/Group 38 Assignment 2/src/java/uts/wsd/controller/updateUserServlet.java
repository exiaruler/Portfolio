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
public class updateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String userID = request.getParameter("userID");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        User user = new User(Integer.parseInt(userID), email, password);
        DBManager manager = (DBManager) session.getAttribute("manager");
        session.setAttribute("updatedUserErr", "");
        session.setAttribute("updatedUserSuccess", "");
        
        if (validator.checkNameEmpty(name)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Name is empty");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Name format is incorrect");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (validator.checkEmailEmpty(email)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Email is empty");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Email format is incorrect");   
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (validator.checkPassEmpty(password)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Password is empty");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Password format is incorrect");
            request.getRequestDispatcher("editUser.jsp").include(request, response); 
        } else if (validator.checkPhoneEmpty(phone)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Phone is empty");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("updatedUserErr", "Update was not successful - Error: Phone format is incorrect");
            request.getRequestDispatcher("editUser.jsp").include(request, response);
        } else {
            try {
                if (user != null) {
                    manager.updateUser(userID, name, email, password, phone);
                    user = manager.findUser(userID);
                    session.setAttribute("user", user);
                    Customer customer = manager.findCustomerByID(Integer.parseInt(userID));
                    Staff staff = manager.findStaff(Integer.parseInt(userID));
                    if (customer != null) {
                        session.setAttribute("customer", customer);
                    } else if (staff != null) {
                        session.setAttribute("staff", staff);
                    }
                    
                    session.setAttribute("updatedUserSuccess", "Update was successful!");
                    request.getRequestDispatcher("editUser.jsp").include(request, response);
                } else {
                    session.setAttribute("updatedUserErr", "Update was not successful");
                    request.getRequestDispatcher("editUser.jsp").include(request, response);
                }

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(editUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }
}
