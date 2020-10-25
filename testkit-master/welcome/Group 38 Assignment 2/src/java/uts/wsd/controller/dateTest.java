/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class dateTest {

    public static void main(String[] args) throws SQLException {
        SimpleDateFormat currDate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat currTime = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String currentDate = currDate.format(date);
        String currentTime = currTime.format(date);
        System.out.println(currentDate);
        System.out.println(currentTime);
        
        String phonePattern = "\\d{10,10}";
        Pattern regEx = Pattern.compile(phonePattern);
        Matcher match = regEx.matcher("0412345678");
        System.out.println(match.matches());
    }
}
