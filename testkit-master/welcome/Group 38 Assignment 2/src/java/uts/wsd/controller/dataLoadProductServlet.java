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
import uts.wsd.model.Product;

/**
 *
 * @author Sheng Wong
 */
public class dataLoadProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String id = request.getParameter("editProductID");
        String name = "";
        Double price = 0.00;
        String type = "";
        String desc = "";
        String brand = "";
        int stock = 0;

        try {
            Product exist = manager.findProductID(Integer.parseInt(id));
            if (exist == null) {
                session.setAttribute("existErr", "Product does not exist");
                request.getRequestDispatcher("editProduct.jsp").include(request, response);
            } else {
                name = exist.getProductName();
                price = exist.getProductPrice();
                desc = exist.getProductDescription();
                stock = exist.getProductStock();
                type = exist.getProductType();
                brand = exist.getProductBrand();

                session.setAttribute("product", exist);
                request.getRequestDispatcher("editProduct.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("editProduct.jsp");
    }
}
