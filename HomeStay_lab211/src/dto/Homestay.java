/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Homestay implements Serializable {

    private String homeID;
    private String homeName;
    private int roomNumber;
    private String address;
    private int maximumCapacity;

    public Homestay() {
    }

    public Homestay(String homeID, String homeName, int roomNumber, String address, int maximumCapacity) {
        this.homeID = homeID;
        this.homeName = homeName;
        this.roomNumber = roomNumber;
        this.address = address;
        this.maximumCapacity = maximumCapacity;
    }

    public String getHomeID() {
        return homeID;
    }

    public void setHomeID(String homeID) {
        this.homeID = homeID;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    @Override
    public String toString() {
        return "HomeStay{" + "homeID=" + homeID + ", homeName=" + homeName + ", roomNumber=" + roomNumber + ", address=" + address + ", maximumCapacity=" + maximumCapacity + '}';
    }
}
