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
public class editUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = request.getParameter("userID");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        try {
            User exist = manager.findUser(userID);
            if (exist == null) {
                session.setAttribute("existErr", "User does not exists in the database");
                request.getRequestDispatcher("editUser.jsp").include(request, response);
            } else {
                session.setAttribute("user", exist);
                Customer customer = manager.findCustomerByID(Integer.parseInt(userID));
                Staff staff = manager.findStaff(Integer.parseInt(userID));
                if (customer != null) {
                    session.setAttribute("customer", customer);
                } else if (staff != null) {
                    session.setAttribute("staff", staff);
                }
                session.setAttribute("updatedUserErr", "");
                session.setAttribute("updatedUserSuccess", "");
                request.getRequestDispatcher("editUser.jsp").include(request, response);
            }

        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(editUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}
