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
public class addProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String brand = request.getParameter("brand");
        String stock = request.getParameter("stock");

        validator.clear(session);

        System.out.println(name);
        System.out.println(price);
        System.out.println(type);
        System.out.println(desc);
        System.out.println(brand);
        System.out.println(stock);

        if (validator.checkEmptyProduct(name, type, price, brand, stock, desc)) {
            session.setAttribute("emptyErr", "Please enter all fields");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductName(name)) {
            session.setAttribute("prdErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductType(type)) {
            session.setAttribute("typeErr", "Error: Type format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductPrice(price)) {
            session.setAttribute("priceErr", "Error: Price format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductDesc(desc)) {
            session.setAttribute("descErr", "Error: Description format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductBrand(brand)) {
            session.setAttribute("brandErr", "Error: Brand format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else if (!validator.validateProductStock(stock)) {
            session.setAttribute("stockErr", "Error: Stock format is incorrect");
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
        } else {
            try {
                Product exist = manager.findProduct(name, type);
                if (exist != null) {
                    session.setAttribute("existErr", "Device already exists");
                    request.getRequestDispatcher("addProduct.jsp").include(request, response);
                } else {
                    manager.addProduct(name, Double.parseDouble(price), desc, Integer.parseInt(stock), brand, type);
                    Product product = manager.findProduct(name, type);

                    session.setAttribute("product", product);
                    session.setAttribute("added", "Product was added succesfully");
                    request.getRequestDispatcher("addProduct.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("addProduct.jsp");
        }
    }
}
