/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;
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
/**
 *
 * @author Eva
 */
public class PaymentDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        validator.clear(session);
        // Payment-specific code
        String cardID = request.getParameter("cardIDdelete");
        String paymentID = request.getParameter("paymentIDdelete");
        
        // Initialise the models
        try {
            // Credit card first to try preventing SQL error
            manager.deleteCreditCard(Integer.parseInt(cardID));
            manager.deletePayment(Integer.parseInt(paymentID));
            // Reset session
            session.setAttribute("payment", "");
            session.setAttribute("creditcard", "");
            // Send us back to the valleys
            request.getRequestDispatcher("payment.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
