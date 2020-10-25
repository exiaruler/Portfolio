package uts.wsd.model;

/**
 *
 * @author Ricky Felix
 */

public class Orders implements java.io.Serializable {
  private int orderID; //primary key
  
  private String orderDate;
	//foreign keys
  private int custID;
  private int shippingID;

public Orders (int orderID,  String orderDate, int custID, int shippingID) {
    this.orderID = orderID;
    
    this.orderDate = orderDate;
    this.custID = custID;
    this.shippingID = shippingID;
}



public int getorderID() {
  return orderID;
	}

public void setorderID(int orderID) {
    this.orderID = orderID;
	}

public String getorderDate() {
  return orderDate;
	}

public void setorderDate(String orderDate) {
  this.orderDate = orderDate;
	}

//foreign key
public int getcustID() {
	return custID;
	}

public void setcustID(int custID) {
	this.custID = custID;
	}

public int getshippingID() {
	return shippingID;
	}

public void setshippingID(int shippingID) {
	this.shippingID = shippingID; 
	}
}
