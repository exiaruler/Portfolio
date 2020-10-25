/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

/**
 *
 * @author Eva
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
import uts.wsd.model.Payment;
import uts.wsd.model.CreditCard;
import uts.wsd.model.dao.DBManager;

public class PaymentConfirmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        validator.clear(session);
        // Payment-specific code
        String cardID = request.getParameter("cardIDconfirm");
        String paymentID = request.getParameter("paymentIDconfirm");
        Boolean paymentConfirm = true;
        Payment payment = null;
        CreditCard creditcard = null;
        
        // Initialise the models
        try {
            payment = manager.findPaymentID(Integer.parseInt(paymentID));
            session.setAttribute("payment", payment);
            creditcard = manager.findCreditCardID(Integer.parseInt(cardID));
            session.setAttribute("creditcard", creditcard);
            manager.updatePaymentConfirmation(Integer.parseInt(paymentID), paymentConfirm);
            // Send us back from Mordor
            request.getRequestDispatcher("payment-success.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentConfirmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
