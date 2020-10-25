/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
@author Sheng Wong
 */
package uts.wsd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.Product;
import uts.wsd.model.dao.DBManager;

/**
 *
 * @author user 1
 */
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();//
        Validator validator = new Validator();//

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("name");

        String search = request.getParameter("search");
        DBManager manager = (DBManager) session.getAttribute("manager");
        validator.clear(session);

        ArrayList<Product> searchProduct = null;

        if (search != null) {
            try {
                searchProduct = manager.findProductList(name, type);
                if (manager.checkProductID(Integer.parseInt(id))) {
                    session.setAttribute("searchProduct", searchProduct);
                    request.getRequestDispatcher("searchProduct.jsp").include(request, response);
                } else {
                    session.setAttribute("existErr", "Device does not exist");
                    request.getRequestDispatcher("product.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
