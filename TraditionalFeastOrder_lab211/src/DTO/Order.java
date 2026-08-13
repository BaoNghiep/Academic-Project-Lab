/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import controllers.Acceptable;
import java.io.Serializable;
import java.util.Date;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Order implements Serializable {

    private String orderCode;
    private String customerID;
    private String menuID;
    private int numberOfTables;
    private Date eventDate;

    public Order() {
        this.orderCode = orderCode;
        this.customerID = "";
        this.menuID = "";
    }

    public Order(String customerID, String menuID, int numberOfTables, Date eventDate) {
        this.orderCode = orderCode;
        this.customerID = customerID;
        this.menuID = menuID;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderCode=" + orderCode + ", customerID=" + customerID + ", menuID=" + menuID + ", numberOfTables=" + numberOfTables + ", eventDate=" + eventDate + '}';
    }

    public boolean create() {
        boolean check = false;
        try {
            customerID = Utils.getStringREGEX("Enter Customer ID(C,K,G and 4 digit): ", "Invalid!", Acceptable.ID_FORMAT);
            menuID = Utils.getStringREGEX("Enter FeastMenu ID: ", "Not blank.Input again!", Acceptable.FEAST_CODE);
            numberOfTables = Utils.getInt("Enter Number Of Tables: ", "More than 0", 1, Integer.MAX_VALUE);
            eventDate = Utils.getDate("Enter Event Date(dd/MM/yyyy): ", "Wrong format.Input again!");
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

}
