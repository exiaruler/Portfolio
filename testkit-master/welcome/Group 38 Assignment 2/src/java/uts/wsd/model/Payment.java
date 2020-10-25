/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model;

/**
 *
 * @author Eva
 */
public class Payment {
    
    private Boolean paymentCreditCard;
    private Boolean paymentConfirm;
    private Boolean paymentPaypal;
    private int paymentID;

    public Payment(int paymentID, Boolean paymentCreditCard, Boolean paymentConfirm, Boolean paymentPaypal) {
        this.paymentCreditCard = paymentCreditCard;
        this.paymentConfirm = paymentConfirm;
        this.paymentPaypal = paymentPaypal;
        this.paymentID = paymentID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public Boolean getPaymentCreditCard() {
        return paymentCreditCard;
    }

    public void setPaymentCreditCard(Boolean paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
    }

    public Boolean getPaymentConfirm() {
        return paymentConfirm;
    }

    public void setPaymentConfirm(Boolean paymentConfirm) {
        this.paymentConfirm = paymentConfirm;
    }

    public Boolean getPaymentPaypal() {
        return paymentPaypal;
    }

    public void setPaymentPaypal(Boolean paymentPaypal) {
        this.paymentPaypal = paymentPaypal;
    }
    
}
