package uts.wsd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.dao.DBManager;
import uts.wsd.model.Orders;

/**
 *
 * @author Ricky Felix
 */
public class addOrderServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        validator.clear(session);
        DBManager manager = (DBManager) session.getAttribute("manager");

    String order = request.getParameter("order");
    String date = request.getParameter("expiry-date");
    String custID = request.getParameter("customer");
    String shippingID = request.getParameter("shipping");
    
     try {
        Orders exist = manager.findOrders(Integer.parseInt(order), date);
            if (exist != null) {
                session.setAttribute("orderErr", "Order already exists");
                request.getRequestDispatcher("addOrders.jsp").include(request, response);
            } else {
                manager.addOrders(date, Integer.parseInt(custID), Integer.parseInt(shippingID));
                Orders orders = manager.findOrders(Integer.parseInt(order), date);
                session.setAttribute("orders", orders);
                session.setAttribute("added", "Product was added succesfully");
                request.getRequestDispatcher("addOrders.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}