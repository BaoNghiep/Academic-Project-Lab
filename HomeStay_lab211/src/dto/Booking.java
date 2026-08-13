/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Booking implements Serializable {

    private String bookingID;
    private String fullName;
    private String tourID;
    private Date bookingDate;
    private String phone;

    public Booking() {
    }

    public Booking(String bookingID, String fullName, String tourID, Date bookingDate, String phone) {
        this.bookingID = bookingID;
        this.fullName = fullName;
        this.tourID = tourID;
        this.bookingDate = bookingDate;
        this.phone = phone;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingID=" + bookingID + ", fullName=" + fullName + ", tourID=" + tourID + ", bookingDate=" + bookingDate + ", phone=" + phone + '}';
    }
}
