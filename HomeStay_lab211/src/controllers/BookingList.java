/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.Acceptable;
import dto.Booking;
import dto.Homestay;
import dto.I_List;
import dto.Tour;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class BookingList extends ArrayList<Booking> implements I_List {

    private ArrayList<Homestay> listHomestay = new ArrayList();
    private ArrayList<Tour> listTour = new ArrayList();

    @Override
    public boolean loadDataFromFile() {
        boolean check = true;
        File filePathHomestay = new File("Homestays.txt");
        if (filePathHomestay.exists()) {
            try {
                FileReader fr = new FileReader(filePathHomestay);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    String txt[] = line.split("-");
                    String id = txt[0].trim();
                    String name = txt[1].trim();
                    int roomNumber = Integer.parseInt(txt[2].trim());
                    String address = txt[3].trim();
                    int maximumCapacity = Integer.parseInt(txt[4].trim());
                    Homestay h = getObjectHomestay(id);
                    if (h == null) {
                        listHomestay.add(new Homestay(id, name, roomNumber, address, maximumCapacity));
                    }
                }
            } catch (Exception e) {
                System.out.println("Load data Homestays.txt fail!");
                check = false;
            }
        }

        try {
            FileInputStream fis = new FileInputStream("Tours.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listTour = (ArrayList<Tour>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        ArrayList<Booking> listFileBooking = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("Bookings.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listFileBooking = (ArrayList<Booking>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        if (!listFileBooking.isEmpty()) {
            for (Booking b : listFileBooking) {
                this.add(b);
            }
        }
        return check;
    }

    @Override
    public boolean addATour() {
        boolean check = true;
        String tourID;
        Tour tour;
        do {
            tourID = Utils.getString("Enter Tour ID(Txxxxx): ", "Please check again!", Acceptable.TOUR_ID_VALID);
            tour = getObjectTour(tourID);
            if (tour != null) {
                System.out.println("Duplicated ID.Input again!");
            }
        } while (tour != null);
        String tourName = Utils.getString("Enter Name: ", "Input again!");
        String tourTime = Utils.getString("Enter Time: ", "Input again!");
        int tourPrice = Utils.getAnInteger("Enter Price: ", "More than 0.", 1);
        String homeID = Utils.getString("Enter Home ID(HSxxxx): ", "Please check again!", Acceptable.HOME_ID_VALID);
        Homestay homestay = getObjectHomestay(homeID);
        if (homestay == null) {
            System.out.println("Not found homeID. Can't add!");
            check = false;
        } else {
            Date departureDate = Utils.getDate("Enter DepartureDate(dd/MM/yyyy): ", "Invalid.Input again!");
            Date endDate;
            do {
                endDate = Utils.getDate("Enter EndDate(dd/MM/yyyy): ", "Invalid.Input again!");
                if (endDate.compareTo(departureDate) < 0) {
                    System.out.println("The end date must be greater than or equal to the departure date.");
                }
            } while (endDate.compareTo(departureDate) < 0);
            int numberTourist = Utils.getAnInteger("Enter Number Tourist:", "Positive number and smaller than the maximum capacity", 1, homestay.getMaximumCapacity());
            if (check && checkTimeExistHomestay(homeID, departureDate, endDate)) {
                listTour.add(new Tour(tourID, tourName, tourTime, tourPrice, homeID, departureDate, endDate, numberTourist, false));
            }
        }
        return check;
    }

    private boolean checkTimeExistHomestay(String homeID, Date departureDate, Date endDate) {
        boolean checkExist = true;
        for (Tour t : listTour) {
            if (t.getHomeID().equalsIgnoreCase(homeID)) {
                if ((departureDate.compareTo(t.getDepartureDate()) >= 0 && departureDate.compareTo(t.getEndDate()) <= 0)
                        || (endDate.compareTo(t.getDepartureDate()) >= 0 && endDate.compareTo(t.getEndDate()) <= 0)) {
                    checkExist = false;
                }
            }
        }
        return checkExist;
    }

    private Tour getObjectTour(String id) {
        Tour tour = null;
        for (Tour t : listTour) {
            if (t.getTourID().equalsIgnoreCase(id)) {
                tour = t;
                break;
            }
        }
        return tour;
    }

    private Homestay getObjectHomestay(String id) {
        Homestay homestay = null;
        for (Homestay h : listHomestay) {
            if (h.getHomeID().equalsIgnoreCase(id)) {
                homestay = h;
                break;
            }
        }
        return homestay;
    }

    private Booking getObjectBooking(String id) {
        Booking booking = null;
        for (Booking b : this) {
            if (b.getBookingID().equalsIgnoreCase(id)) {
                booking = b;
                break;
            }
        }
        return booking;
    }

    @Override
    public boolean updateATour(String id) {
        boolean check = false;
        Tour tour = getObjectTour(id);
        if (tour != null) {
            String newName = Utils.updateString("Enter New Tour Name: ", tour.getTourName());
            tour.setTourName(newName);
            String newTime = Utils.updateString("Enter New Time: ", tour.getTime());
            tour.setTime(newTime);
            int newPrice = Utils.updateAnInteger("Enter New Price: ", 1, tour.getPrice());
            tour.setPrice(newPrice);
            int newNumberTourist = Utils.updateAnInteger("Enter Number Tourist:", 1, tour.getNumberTourist());
            tour.setNumberTourist(newNumberTourist);
            String newHomeID = Utils.updateString("Enter New HomeID(HSxxxx): ", tour.getHomeID(), Acceptable.HOME_ID_VALID);
            Homestay homestay = getObjectHomestay(newHomeID);
            if (homestay != null) {
                tour.setHomeID(newHomeID);
            }
            Date newDepartureDate = Utils.updateDate("Enter New DepartureDate(dd/MM/yyyy): ", tour.getDepartureDate());
            Date newEndDate = Utils.updateDate("Enter New EndDate(dd/MM/yyyy): ", tour.getEndDate());
            if (newEndDate.compareTo(newDepartureDate) >= 0) {
                if (checkTimeExistHomestay(newHomeID, newDepartureDate, newEndDate)) {
                    tour.setDepartureDate(newDepartureDate);
                    tour.setEndDate(newEndDate);
                }
            }
            check = true;
        }
        return check;
    }

    @Override
    public List<Tour> findTourEearlierCurrent() {
        List<Tour> list = new ArrayList();
        Date now = new Date();
        for (Tour t : listTour) {
            if (t.getDepartureDate().compareTo(now) <= 0) {
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public List<Tour> listTourAfterCurrent() {
        List<Tour> list = new ArrayList();
        Date now = new Date();
        for (Tour t : listTour) {
            if (t.getDepartureDate().compareTo(now) >= 0 && t.isBooking() == true) {
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public boolean addABooking() {
        boolean check;
        String bookingID;
        Booking booking;
        do {
            bookingID = Utils.getString("Enter BookingID(Bxxxxx): ", "Please check again!", Acceptable.BOOKING_ID_VALID);
            booking = getObjectBooking(bookingID);
            if (booking != null) {
                System.out.println("Duplicated ID.Input again!");
            }
        } while (booking != null);
        String tourID = Utils.getString("Enter Tour ID(Txxxxx): ", "Please check again!", Acceptable.TOUR_ID_VALID);
        Tour tour = getObjectTour(tourID);
        if (tour == null || tour.isBooking() == true) {
            System.out.println("Invalid tour!");
            check = false;
        } else {
            String fullName = Utils.getString("Enter FullName: ", "Not blank.Input again!");
            String phone = Utils.getString("Enter Phone Number(0xx..): ", "10 digits.Input again!", Acceptable.PHONE_VALID);
            Date bookingDate;
            do {
                bookingDate = Utils.getDate("Enter BookingDate(dd/MM/yyyy): ", "Invalid.Input again!");
                if (bookingDate.compareTo(tour.getDepartureDate()) > 0) {
                    System.out.println("The booking date must less than the departure date of the corresponding tour.");
                }
            } while (bookingDate.compareTo(tour.getDepartureDate()) > 0);
            this.add(new Booking(bookingID, fullName, tourID, bookingDate, phone));
            tour.setBooking(true);
            check = true;
        }
        return check;
    }

    @Override
    public boolean updateABooking(String id) {
        boolean check = false;
        Booking booking = getObjectBooking(id);
        if (booking != null) {
            String newFullName = Utils.updateString("Enter New Full Name: ", booking.getFullName());
            booking.setFullName(newFullName);
            String newPhone = Utils.updateString("Enter New Phone Number(0xx...): ", booking.getPhone(), Acceptable.PHONE_VALID);
            booking.setPhone(newPhone);
            String newTourID = Utils.updateString("Enter New Tour ID(Txxxxx): ", booking.getTourID(), Acceptable.TOUR_ID_VALID);
            Date newBookingDate = Utils.updateDate("Enter BookingDate(dd/MM/yyyy): ", booking.getBookingDate());
            Tour tour = getObjectTour(newTourID);
            if (tour != null) {
                booking.setTourID(newTourID);
                tour.setBooking(true);
                if (newBookingDate.compareTo(tour.getDepartureDate()) <= 0) {
                    booking.setBookingDate(newBookingDate);
                }
            }
            check = true;
        }
        return check;
    }

    @Override
    public List<Booking> seachBooking() {
        ArrayList<Booking> listSearch = new ArrayList();
        String name = Utils.getString("Enter fullName or partial fullName: ", "Not blank.Input again!");
        for (Booking booking : this) {
            if (booking.getFullName().toLowerCase().contains(name.toLowerCase())) {
                listSearch.add(booking);
            }
        }
        return listSearch;
    }

    @Override
    public void statisticsHomestay() {
        if (listTour.isEmpty()) {
            System.out.println("Not found tour.");
        } else {
            HashMap<String, Integer> mapHomeIdOfTour = new HashMap<>();
            for (Tour tour : listTour) {
                if (tour.isBooking() == true) {
                    mapHomeIdOfTour.put(tour.getHomeID(), 0);
                }
            }
            System.out.println("+------------------------------+---------------+");
            System.out.println("|HOME NAME                     |NUMBER TOURIST |");
            System.out.println("+------------------------------+---------------+");
            int total;
            for (String idHome : mapHomeIdOfTour.keySet()) {
                total = 0;
                for (Tour tour : listTour) {
                    if (idHome.equalsIgnoreCase(tour.getHomeID()) && tour.isBooking() == true) {
                        total = total + tour.getNumberTourist();
                    }
                }
                System.out.printf("|%-30s|%-15d|\n", getObjectHomestay(idHome).getHomeName(), total);
            }
            System.out.println("+------------------------------+---------------+");
        }
    }

    @Override
    public boolean saveDataToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("Tours.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listTour);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileOutputStream fos = new FileOutputStream("Bookings.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    @Override
    public boolean removeABooking(String id) {
        boolean check = false;
        Booking booking = getObjectBooking(id);
        if (booking != null) {
            String confirm = Utils.getString("Do you want remove (y/n)", "Just y or n", "^[YyNn]$");
            if (confirm.equalsIgnoreCase("y")) {
                Tour tour = getObjectTour(booking.getTourID());
                tour.setBooking(false);
                this.remove(booking);
                check = true;
            }
        }
        return check;
    }

}
