/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

/**
 *
 * @author kamenriderfourze
 */
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

public class customerDeleteServlet  extends HttpServlet{
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                  throws ServletException, IOException {
       HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        String id=request.getParameter("editID");
        String name="";
        String phone="";
        int userID;
        boolean status=false;
        
        try{
            Customer exist=manager.findCustomerByID(Integer.parseInt(id));
            if(exist==null){
                session.setAttribute("existErr","Customer does not exist");
                request.getRequestDispatcher("customerDelete.jsp").include(request, response);
            }else
            { 
                name=exist.getName();
            phone=exist.getPhone();
            userID=exist.getUserID();
            status=exist.getStatus();
            
            session.setAttribute("customer", exist);
            request.getRequestDispatcher("customerDelete.jsp").include(request, response);
            }
        }catch (SQLException ex){
               ex.printStackTrace();
        }
        response.sendRedirect("customerDelete.jsp");
   }
}
