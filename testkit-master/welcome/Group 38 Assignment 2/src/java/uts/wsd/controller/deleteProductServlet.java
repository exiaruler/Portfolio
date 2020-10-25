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
public class deleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String id = request.getParameter("editProductID");
        String name = "";
        String type = "";

        validator.clear(session);

        try {
            Product exist = manager.findProductID(Integer.parseInt(id));
            if (exist == null) {
               session.setAttribute("existErr", "Product does not exists");
                request.getRequestDispatcher("deleteProductSuccess.jsp").include(request, response);
            } else {
                
                 name = exist.getProductName();
                type = exist.getProductType();
                manager.deleteProduct(name, type);

                request.getRequestDispatcher("deleteProductSuccess.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
