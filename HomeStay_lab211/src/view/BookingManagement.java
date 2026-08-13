/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import controllers.BookingList;
import controllers.Menu;
import dto.Acceptable;
import dto.Booking;
import dto.I_List;
import dto.I_Menu;
import dto.Tour;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class BookingManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        I_Menu menu = new Menu("HomeStay Booking Management.");
        menu.addMenu("1. Add a new Tour.");
        menu.addMenu("2. Update a Tour by ID.");
        menu.addMenu("3. List the Tours with departure dates earlier than the current date.");
        menu.addMenu("4. List the total Booking amount for tours with departure dates later than the current date.");
        menu.addMenu("5. Add a new Booking.");
        menu.addMenu("6. Remove a Boooking by bookingID.");
        menu.addMenu("7. Update a Booking by bookingID.");
        menu.addMenu("8. List all Booking by the fullName or a partial fullName.");
        menu.addMenu("9. Statics on the total number of tourists who have booked homestays.");
        menu.addMenu("10. Exit the Program.");

        I_List controllers = new BookingList();
        boolean checkLoadFile = controllers.loadDataFromFile();
        if (!checkLoadFile) {
            System.out.println("Please check file.Can't load datas.");
        }

        boolean changed = false;
        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    boolean checkAddTour = controllers.addATour();
                    if (checkAddTour) {
                        System.out.println("Add successful.");
                    } else {
                        System.out.println("Add fail.");
                    }
                    changed = true;
                    break;
                case 2:
                    String tourID = Utils.getString("Enter Tour ID(Txxxxx): ", "x is digit.Input again!");
                    boolean checkUpdateTour = controllers.updateATour(tourID);
                    if (checkUpdateTour) {
                        System.out.println("Update successful.");
                    } else {
                        System.out.println("Update fail.");
                    }
                    changed = true;
                    break;
                case 3:
                    List<Tour> list = controllers.findTourEearlierCurrent();
                    if (list.isEmpty()) {
                        System.out.println("Not found tour.");
                    } else {
                        System.out.println("+------+---------------+--------------------+-------+--------+---------------+---------------+---------------+----------+");
                        System.out.println("|ID    |NAME           |TIME                |PRICE  |HOME_ID |DEPARTURE_DATE |END_DATE       |NUMBER_TOURIST |IS_BOOKING|");
                        System.out.println("+------+---------------+--------------------+-------+--------+---------------+---------------+---------------+----------+");
                        for (Tour t : list) {
                            System.out.printf("|%-6s|%-15s|%-20s|%-7d|%-8s|%-15s|%-15s|%-15d|%-10s|\n", t.getTourID(), t.getTourName(), t.getTime(), t.getPrice(), t.getHomeID(),
                                    Acceptable.sdf.format(t.getDepartureDate()), Acceptable.sdf.format(t.getEndDate()), t.getNumberTourist(), t.isBooking());
                        }
                        System.out.println("+------+---------------+--------------------+-------+--------+---------------+---------------+---------------+----------+");
                    }
                    break;
                case 4:
                    List<Tour> listResult = controllers.findTourEearlierCurrent();
                    if (listResult.isEmpty()) {
                        System.out.println("Not found tour.");
                    } else {
                        System.out.println("+----------+----------+");
                        System.out.println("|TOUR_ID   |AMOUNT    |");
                        System.out.println("+----------+----------+");
                        for (Tour tour : listResult) {
                            System.out.printf("|%-10s|%-10d|\n", tour.getTourID(), tour.getPrice());
                        }
                        System.out.println("+----------+----------+");
                    }
                    break;
                case 5:
                    boolean checkAddBooking = controllers.addABooking();
                    if (checkAddBooking) {
                        System.out.println("Add successful.");
                    } else {
                        System.out.println("Add fail.");
                    }
                    changed = true;
                    break;
                case 6:
                    String bookingIDRemove = Utils.getString("Enter BookingID(Bxxxxx): ", "x is digit.Input again!", Acceptable.BOOKING_ID_VALID);
                    boolean removeBooking = controllers.removeABooking(bookingIDRemove);
                    if (removeBooking) {
                        System.out.println("Remove successful.");
                    } else {
                        System.out.println("Remove fail.");
                    }
                    changed = true;
                    break;
                case 7:
                    String bookingIDUpdate = Utils.getString("Enter BookingID(Bxxxxx): ", "x is digit.Input again!", Acceptable.BOOKING_ID_VALID);
                    boolean updateBooking = controllers.updateABooking(bookingIDUpdate);
                    if (updateBooking) {
                        System.out.println("Update successful.");
                    } else {
                        System.out.println("Update fail.");
                    }
                    changed = true;
                    break;
                case 8:
                    List<Booking> listBooking = controllers.seachBooking();
                    if (listBooking.isEmpty()) {
                        System.out.println("List empty.");
                    } else {
                        System.out.println("+----------+--------------------+----------+------------+-------------+");
                        System.out.println("|ID        |NAME                |TOUR_ID   |DATE        |PHONE        |");
                        System.out.println("+----------+--------------------+----------+------------+-------------+");
                        for (Booking booking : listBooking) {
                            System.out.printf("|%-10s|%-20s|%-10s|%-12s|%-13s|\n", booking.getBookingID(), booking.getFullName(), booking.getTourID(),
                                    Acceptable.sdf.format(booking.getBookingDate()), booking.getPhone());
                        }
                        System.out.println("+----------+--------------------+----------+------------+-------------+");
                    }
                    break;
                case 9:
                    controllers.statisticsHomestay();
                    break;
                case 10:
                    if (changed) {
                        boolean saveFile = controllers.saveDataToFile();
                        if (saveFile) {
                            System.out.println("Save file successful.");
                        } else {
                            System.out.println("Save file fail.");
                        }
                    }
                    System.out.println("Exist!");
                    break;
            }
        } while (choice != 10);
    }

}
