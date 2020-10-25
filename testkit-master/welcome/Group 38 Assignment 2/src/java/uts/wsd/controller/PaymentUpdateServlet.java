package uts.wsd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class PaymentUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        validator.clear(session);
        DBManager manager = (DBManager) session.getAttribute("manager");
        // Payment-specific code
        String cardID = request.getParameter("cardID");
        String name = request.getParameter("name");
        String number = request.getParameter("cc-number");
        String cvv = request.getParameter("cvv");
        String date = request.getParameter("expiry-date");
        String paymentID = request.getParameter("paymentID");
        String paymentConfirm = request.getParameter("paymentConfirm");
        String paymentCreditCard = request.getParameter("paymentCreditCard");
        String paymentPaypal = request.getParameter("paymentPaypal");
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Payment payment = null;
        CreditCard creditcard = null;

        if (validator.checkCreditCardEmpty(name, number, cvv, date)) {
            session.setAttribute("ccEmptyErr", "Please enter all fields.");
            request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
        } else {
            // This is the same form, so we're using the same validation as paymentservlet
            if (!validator.validateName(name)) {
                session.setAttribute("ccNameErr", "Error: Name format is incorrect. Please enter using the following format: FirstName LastName.");
                request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
            } else if (!validator.validateCreditCardNo(number)) {
                session.setAttribute("ccNumberErr", "Error: Credit card number format is incorrect. Please enter a valid credit card number.");
                request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
            } else if (!validator.validateCreditCardSecurityNo(cvv)) {
                session.setAttribute("ccCvvErr", "Error: Credit card CVV format is incorrect. Please try again with 3-4 digits.");
                request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
            } else if (!validator.validateCreditCardExpiryDate(date)) {
                session.setAttribute("ccDateErr", "Error: Date format is incorrect or the card has already expired. Please try again.");
                request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
            } else if (manager == null) {
                session.setAttribute("managerErr", "Error: Connection to database not established. Time to panic.");
                request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
            } else {
                try {
                    // This is agnostic to payment method so we're updating it even though the values don't change if CC -> CC
                    // The definition of safe, redundant code
                    manager.updatePayment(Integer.parseInt(paymentID), Boolean.parseBoolean(paymentCreditCard), Boolean.parseBoolean(paymentConfirm), Boolean.parseBoolean(paymentPaypal));
                    // We need this to put us back on step 1 of the confirm page.
                    session.setAttribute("payment", payment);
                    // update credit card
                    String temp = "01/".concat(date);
                    LocalDate localDate = LocalDate.parse(temp, ccMonthFormatter);
                    manager.updateCreditCard(Integer.parseInt(cardID), name, Long.parseLong(number), Integer.parseInt(cvv), localDate);
                    creditcard = manager.findCreditCard(Long.parseLong(number));
                    // Set new credit card
                    session.setAttribute("creditcard", creditcard);
                    request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
