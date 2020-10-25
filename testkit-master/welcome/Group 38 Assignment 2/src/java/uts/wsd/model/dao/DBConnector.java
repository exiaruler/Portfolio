/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eric
 */
public class DBConnector extends DB {

    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        super.conn = DriverManager.getConnection(URL + db, dbuser, dbpass);
    }

    public Connection openConnection() {
        return this.conn; //super.conn;
    }

    public void closeConnection() throws SQLException {
        this.conn.close(); //super.conn.close();
    }
}