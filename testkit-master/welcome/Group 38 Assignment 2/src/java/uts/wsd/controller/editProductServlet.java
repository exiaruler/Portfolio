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
public class editProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        String desc = request.getParameter("desc");
        String stock = request.getParameter("stock");
        String brand = request.getParameter("brand");
        String type = request.getParameter("type");

        System.out.println(name);
        System.out.println(price);
        System.out.println(type);
        System.out.println(desc);
        System.out.println(brand);
        System.out.println(stock);

       

        validator.clear(session);
        
        if (validator.checkEmptyProduct(name, price, desc, stock, type, brand)) {
            session.setAttribute("emptyErr", "Please enter all fields");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductName(name)) {
            session.setAttribute("prdErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductPrice(price)) {
            session.setAttribute("priceErr", "Error: Price format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductDesc(desc)) {
            session.setAttribute("descErr", "Error: Description format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductStock(stock)) {
            session.setAttribute("stockErr", "Error: Stock format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductType(type)) {
            session.setAttribute("typeErr", "Error: Type format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else if (!validator.validateProductBrand(brand)) {
            session.setAttribute("brandErr", "Error: Brand format is incorrect");
            request.getRequestDispatcher("editProduct.jsp").include(request, response);
        } else {
             Product product = new Product(Integer.parseInt(id), name, Double.parseDouble(price), desc, Integer.parseInt(stock), type, brand);
            try {
                Product exist = manager.findProductID(Integer.parseInt(id));
                if (exist == null) {
                    session.setAttribute("existErr", "Product does not exist");
                    request.getRequestDispatcher("editProduct.jsp").include(request, response);
                } else {
                    manager.updateProduct(Integer.parseInt(id), name, Double.parseDouble(price), desc, Integer.parseInt(stock), type, brand);
                    product = manager.findProductID(Integer.parseInt(id));
                    session.setAttribute("product", product);
                    session.setAttribute("edited", "Product has been updated");
                    request.getRequestDispatcher("editProduct.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("editProduct.jsp");
        }
    }
}
