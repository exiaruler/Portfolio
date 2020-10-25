/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import uts.wsd.model.Customer;
/**
 *
 * @author kamenriderfourze
 */
public class customerEditServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
         String id = request.getParameter("id");
        String email = request.getParameter("email");  
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String userID=request.getParameter("userID");
        boolean status=request.equals(true);
        DBManager manager = (DBManager) session.getAttribute("manager");
       
        

        validator.clear(session);
        
         if(validator.checkCustomerEmpty(name, phone, email, password)){
             session.setAttribute("emptyErr", "Please enter all fields");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        }else if (validator.checkNameEmpty(name)) {
            session.setAttribute("nameErr", "Error: Name is empty");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (validator.checkEmailEmpty(email)) {
            session.setAttribute("emailErr", "Error: Email is empty");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");         
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (validator.checkPassEmpty(password)) {
            session.setAttribute("passErr", "Error: Password is empty");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");        
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (validator.checkPhoneEmpty(phone)) {
            session.setAttribute("phoneErr", "Error: Phone is empty");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Error: Phone format is incorrect");
            request.getRequestDispatcher("editCustomer.jsp").include(request, response);
        } else  {
            Customer customer = new Customer(Integer.parseInt(id), name, phone,Integer.parseInt(userID),status);
            try {
                Customer exist = manager.findCustID(Integer.parseInt(id));
                if (exist == null) {
                    session.setAttribute("existErr", "Customer does not exists");
                    request.getRequestDispatcher("editCustomer.jsp").include(request, response);
                } else {
                    //manager.updateProduct(Integer.parseInt(id), name, Double.parseDouble(price), desc, Integer.parseInt(stock), type, brand);
                    manager.updateCustomer(Integer.parseInt(id), name, phone, email, password);
                    customer = manager.findCustID(Integer.parseInt(id));
                    session.setAttribute("customer", customer);
                    session.setAttribute("edited", "Customer was updated");
                    request.getRequestDispatcher("editCustomer.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("editCustomer.jsp");
        }
    }
}
