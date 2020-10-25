/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import uts.wsd.model.AccessLogs;
import uts.wsd.model.dao.DBManager;

/**
 *
 * @author Eric
 */
public class searchLogsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = request.getParameter("userID");
        String date = request.getParameter("date");
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        ArrayList<AccessLogs> accessLogs;
        
        validator.clear(session);
        if (validator.checkLogDateEmpty(date)) {
            session.setAttribute("logDateErr", "Error: Date empty (e.g. dd/MM/yyyy)");
            request.getRequestDispatcher("accessLog.jsp").include(request, response);
        } else if (!validator.validateLogDate(date)) {
            session.setAttribute("logDateErr", "Error: Incorrect date format (e.g. dd/MM/yyyy)");
            request.getRequestDispatcher("accessLog.jsp").include(request, response);
        } else {
            try {
                accessLogs = manager.fetchAccessLogDate(userID, date);
                session.setAttribute("accessLog", accessLogs);
                request.getRequestDispatcher("accessLog.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AccessLogs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
