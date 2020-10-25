/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.wsd.model.dao.DBManager;

/**
 *
 * @author Eric
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = request.getParameter("userID");
        DBManager manager = (DBManager) session.getAttribute("manager");
        try {
            LocalDateTime currDateTime = LocalDateTime.now();
            String currentDate = currDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String currentTime = currDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            manager.addAccessLog(userID, currentDate, currentTime, "Logout");
            session.invalidate();
            request.getRequestDispatcher("logout.jsp").include(request, response);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

    }
}
