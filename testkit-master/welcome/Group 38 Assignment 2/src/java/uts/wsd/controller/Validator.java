/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.controller;

import java.io.Serializable;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eric
 */
public class Validator implements Serializable {

    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,3})*)";
    private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private String passwordPattern = "^[a-z0-9]{4,50}$";
    private String phonePattern = "\\d{10,10}";
    private String logDatePattern = "^(0{1,1}[1-9]|[12][0-9]|3[01])[\\/](0{1,1}[1-9]|1[012])[\\/]\\d{4}$";
    
    private String productNamePattern = "^([A-Za-z0-9,()\\/\\\\\\.-]\\s?)*$";
    private String descriptionPattern = "^([A-Za-z0-9,()\\/\\\\\\.-]\\s?)*$";
    private String pricePattern = "[0-9]+(\\.[0-9]{1,2})?$";
    private String stockPattern = "^[1-9]+[0-9]*$";
/*^([A-Za-z0-9]\\s?)+([,]\\s?([A-Za-z0-9]\\s?)+)([-]\\s)([\/]\\s)([(]\\s)([)]\\s)s*$*/
    private String productTypePattern = "^([A-Za-z0-9,()\\/\\\\\\.-]\\s?)*$";
    private String productBrandPattern = "^([A-Za-z0-9,()\\/\\\\\\.-]\\s?)*$";

    private String creditCardNoPattern = "^3(?:[47]\\d([ -]?)\\d{4}(?:\\1\\d{4}){2}|0[0-5]\\d{11}|[68]\\d{12})$|^4(?:\\d\\d\\d)?([ -]?)\\d{4}(?:\\2\\d{4}){2}$|^6011([ -]?)\\d{4}(?:\\3\\d{4}){2}$|^5[1-5]\\d\\d([ -]?)\\d{4}(?:\\4\\d{4}){2}$|^2014\\d{11}$|^2149\\d{11}$|^2131\\d{11}$|^1800\\d{11}$|^3\\d{15}$";
    // Only accepts 3-4 digits
    private String creditCardSecurityNoPattern = "^([0-9]{3,4})$";

    public Validator() {
    }

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();
    }

    public boolean checkEmailEmpty(String email) {
        return email.isEmpty();
    }

    public boolean checkNameEmpty(String name) {
        return name.isEmpty();
    }

    public boolean checkPassEmpty(String password) {
        return password.isEmpty();
    }

    public boolean checkPhoneEmpty(String phone) {
        return phone.isEmpty();
    }
    
    public boolean checkLogDateEmpty(String date) {
        return date.isEmpty();
    }
    
    public boolean validateEmail(String email) {
        return validate(emailPattern, email);
    }

    public boolean validateName(String name) {
        return validate(namePattern, name);
    }

    public boolean validatePassword(String password) {
        return validate(passwordPattern, password);
    }

    public boolean validatePhone(String phone) {
        return validate(phonePattern, phone);
    }
    
    public boolean validateLogDate(String date) {
        return validate(logDatePattern, date);
    }

    public boolean checkEmptyProduct(String name, String type, String price, String brand, String stock, String description) {
        return name.isEmpty() || type.isEmpty() || price.isEmpty() || brand.isEmpty() || stock.isEmpty() || description.isEmpty();
    }

    public boolean validateProductName(String name) {
        return validate(productNamePattern, name);
    }

    public boolean validateProductType(String name) {
        return validate(productTypePattern, name);
    }

    public boolean validateProductDesc(String name) {
        return validate(descriptionPattern, name);
    }

    public boolean validateProductPrice(String name) {
        return validate(pricePattern, name);
    }

    public boolean validateProductStock(String name) {
        return validate(stockPattern, name);
    }

    public boolean validateProductBrand(String name) {
        return validate(productBrandPattern, name);
    }

    public boolean validateCreditCardExpiryDate(String date) throws DateTimeParseException {
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            YearMonth lastValidMonth = YearMonth.parse(date, ccMonthFormatter);
            return !YearMonth.now(ZoneId.systemDefault()).isAfter(lastValidMonth);
        } catch (DateTimeParseException dtpe) {
            dtpe.printStackTrace();
        }
        return false;
    }

    public boolean validateCreditCardName(String name) {
        return validate(namePattern, name);
    }

    public boolean validateCreditCardNo(String number) {
        return validate(creditCardNoPattern, number);
    }

    public boolean validateCreditCardSecurityNo(String number) {
        return validate(creditCardSecurityNoPattern, number);
    }

    public boolean checkCreditCardEmpty(String name, String number, String cvv, String date) {
        return name.isEmpty() || number.isEmpty() || cvv.isEmpty() || date.isEmpty();
    }
    public boolean checkCustomerEmpty(String name, String phone, String email, String password) {
        return name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty();
    }


    public void clear(HttpSession session) {
        session.setAttribute("logDateErr", "");
        session.setAttribute("emailErr", "");
        session.setAttribute("passErr", "");
        session.setAttribute("prdErr", "");
        session.setAttribute("descErr", "");
        session.setAttribute("priceErr", "");
        session.setAttribute("brandErr", "");
        session.setAttribute("typeErr", "");
        session.setAttribute("stockErr", "");
        session.setAttribute("existErr", "");
        session.setAttribute("emptyErr", "");
        session.setAttribute("added", "");
        session.setAttribute("nameErr", "");
        session.setAttribute("phoneErr", "");
        session.setAttribute("ccEmptyErr", "");
        session.setAttribute("ccNameErr", "");
        session.setAttribute("ccNumberErr", "");
        session.setAttribute("ccCvvErr", "");
        session.setAttribute("ccDateErr", "");
        session.setAttribute("edited", "");
    }
}
