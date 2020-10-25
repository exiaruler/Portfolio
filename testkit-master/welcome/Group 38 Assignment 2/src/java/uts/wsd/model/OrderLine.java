/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.model;

/**
 *
 * @author Ricky_Felix
 */

public class OrderLine implements java.io.Serializable {
    private String orderLineQuantity;
        //foreign keys
    private int orderID; 
    private int productID;
    

public OrderLine (String orderLineQuantity, int orderID, int productID) {
    this.orderLineQuantity = orderLineQuantity;
    this.orderID = orderID;
    this.productID = productID;
    }
    
public String getorderLineQuantity() {
    return orderLineQuantity;
    }

public void setorderQuantity(String orderLineQuantity) {
    this.orderLineQuantity = orderLineQuantity;
    }

public int getorderID () {
    return orderID;
    }

public void setorderID (int orderID) {
    this.orderID = orderID;
    }

public int getproductID () {
    return productID;
    }

public void setproductID (int productID) {
    this.productID = productID;
    }

}
