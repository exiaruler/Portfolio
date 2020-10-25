package uts.wsd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.Payment;
import uts.wsd.model.CreditCard;
import uts.wsd.model.dao.DBManager;

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        validator.clear(session);
        DBManager manager = (DBManager) session.getAttribute("manager");

        // Payment-specific code
        String name = request.getParameter("name");
        String number = request.getParameter("cc-number");
        String cvv = request.getParameter("cvv");
        String date = request.getParameter("expiry-date");
        String paymentConfirm = request.getParameter("paymentConfirm");
        String paymentCreditCard = request.getParameter("paymentCreditCard");
        String paymentPaypal = request.getParameter("paymentPaypal");
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Payment payment = null;
        CreditCard creditcard = null;

        if (validator.checkCreditCardEmpty(name, number, cvv, date)) {
            session.setAttribute("ccEmptyErr", "Please enter all fields.");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        } else {
            if (!validator.validateName(name)) {
                session.setAttribute("ccNameErr", "Error: Name format is incorrect. Please enter using the following format: FirstName LastName.");
                request.getRequestDispatcher("payment.jsp").include(request, response);
            } else if (!validator.validateCreditCardNo(number)) {
                session.setAttribute("ccNumberErr", "Error: Credit card number format is incorrect. Please enter a valid credit card number.");
                request.getRequestDispatcher("payment.jsp").include(request, response);
            } else if (!validator.validateCreditCardSecurityNo(cvv)) {
                session.setAttribute("ccCvvErr", "Error: Credit card CVV format is incorrect. Please try again with 3-4 digits.");
                request.getRequestDispatcher("payment.jsp").include(request, response);
            } else if (!validator.validateCreditCardExpiryDate(date)) {
                session.setAttribute("ccDateErr", "Error: Date format is incorrect or the card has already expired. Please try again.");
                request.getRequestDispatcher("payment.jsp").include(request, response);
            } else if (manager == null) {
                session.setAttribute("managerErr", "Error: Connection to database not established. Time to panic.");
                request.getRequestDispatcher("payment.jsp").include(request, response);
            } else {
                try {
                    Integer feedback = manager.addPayment(Boolean.parseBoolean(paymentCreditCard), Boolean.parseBoolean(paymentConfirm), Boolean.parseBoolean(paymentPaypal));
                    payment = manager.findPaymentID(feedback);
                    session.setAttribute("payment", payment);
                    String temp = "01/".concat(date);
                    LocalDate localDate = LocalDate.parse(temp, ccMonthFormatter);
                    manager.addCreditCard(name, Long.parseLong(number), Integer.parseInt(cvv), localDate, feedback);
                    creditcard = manager.findCreditCard(Long.parseLong(number));
                    session.setAttribute("creditcard", creditcard);
                    request.getRequestDispatcher("payment-confirmation.jsp").include(request, response);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
