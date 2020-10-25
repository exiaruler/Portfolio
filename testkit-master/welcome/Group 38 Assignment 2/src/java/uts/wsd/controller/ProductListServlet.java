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
import uts.wsd.model.Product;
import uts.wsd.model.dao.DBManager;

/**
 *
 * @author Sheng Wong
 */
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        ArrayList<Product> productList = null;

        validator.clear(session);

        try {
            productList = manager.fetchProducts();
            session.setAttribute("productList", productList);
            request.getRequestDispatcher("product.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
