/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.controller.Validator;
import uts.wsd.model.Customer;
import uts.wsd.model.dao.DBManager;
/**
 *
 * @author kamenriderfourze
 */
public class customerListServlet  extends HttpServlet{
  @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        ArrayList<Customer> customerList = null;

        validator.clear(session);

        try {
            customerList = manager.fetchCustomer();
            session.setAttribute("customerList", customerList);
            request.getRequestDispatcher("customer.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
