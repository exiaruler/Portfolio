/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Eric
 */
public class AccessLogs implements java.io.Serializable {
    private int accessID;
    private int userID;
    private String dateAccessed;
    private String timeAccessed;
    private String action;

    public AccessLogs(int accessID, int userID, String dateAccessed, String timeAccessed, String action) {
        this.accessID = accessID;
        this.userID = userID;
        this.dateAccessed = dateAccessed;
        this.timeAccessed = timeAccessed;
        this.action = action;
    }

    public int getAccessID() {
        return accessID;
    }

    public void setAccessID(int accessID) {
        this.accessID = accessID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(String dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public String getTimeAccessed() {
        return timeAccessed;
    }

    public void setTimeAccessed(String timeAccessed) {
        this.timeAccessed = timeAccessed;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
}
