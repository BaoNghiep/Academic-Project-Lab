/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface I_List {

    boolean loadDataFromFile();

    boolean addATour();

    boolean updateATour(String id);

    List<Tour> findTourEearlierCurrent();

    List<Tour> listTourAfterCurrent();

    boolean addABooking();

    boolean updateABooking(String id);

    boolean removeABooking(String id);

    List<Booking> seachBooking();

    void statisticsHomestay();

    boolean saveDataToFile();
}
