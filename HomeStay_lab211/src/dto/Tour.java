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
public class Tour implements Serializable {

    private String tourID;
    private String tourName;
    private String time;
    private int price;
    private String homeID;
    private Date departureDate;
    private Date endDate;
    private int numberTourist;
    private boolean booking;

    public Tour() {
    }

    public Tour(String tourID, String tourName, String time, int price, String homeID, Date departureDate, Date endDate, int numberTourist, boolean booking) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.time = time;
        this.price = price;
        this.homeID = homeID;
        this.departureDate = departureDate;
        this.endDate = endDate;
        this.numberTourist = numberTourist;
        this.booking = booking;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHomeID() {
        return homeID;
    }

    public void setHomeID(String homeID) {
        this.homeID = homeID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    public int getNumberTourist() {
        return numberTourist;
    }

    public void setNumberTourist(int numberTourist) {
        this.numberTourist = numberTourist;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourID=" + tourID + ", tourName=" + tourName + ", time=" + time + ", price=" + price + ", homeID=" + homeID + ", departureDate=" + departureDate + ", endDate=" + endDate + ", numberTourist=" + numberTourist + ", booking=" + booking + '}';
    }
}
