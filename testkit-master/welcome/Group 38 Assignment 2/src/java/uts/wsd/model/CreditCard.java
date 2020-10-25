/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model;

import java.util.Date;

/**
 *
 * @author Eva
 */
public class CreditCard {
    private int cardID;
    private String creditName;
    private Long creditNo;
    private int creditSecurityNo;
    private Date creditExpiryDate;

    public CreditCard(int cardID, String creditName, Long creditNo, int creditSecurityNo, Date creditExpiryDate) {
        this.cardID = cardID;
        this.creditName = creditName;
        this.creditNo = creditNo;
        this.creditSecurityNo = creditSecurityNo;
        this.creditExpiryDate = creditExpiryDate;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Long getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(Long creditNo) {
        this.creditNo = creditNo;
    }

    public int getCreditSecurityNo() {
        return creditSecurityNo;
    }

    public void setCreditSecurityNo(int creditSecurityNo) {
        this.creditSecurityNo = creditSecurityNo;
    }

    public Date getCreditExpiryDate() {
        return creditExpiryDate;
    }

    public void setCreditExpiryDate(Date creditExpiryDate) {
        this.creditExpiryDate = creditExpiryDate;
    }
    
}
