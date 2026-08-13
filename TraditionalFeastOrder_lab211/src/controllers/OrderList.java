/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import DTO.SetMenu;
import DTO.I_Customer;
import DTO.I_SetMenu;
import DTO.Order;
import utils.Utils;
import DTO.I_Order;

/**
 *
 * @author Admin
 */
public class OrderList extends ArrayList<Order> implements I_Order {

    private I_Customer customerList;
    private I_SetMenu feastMenuList;

    public OrderList(I_Customer customerList, I_SetMenu feastMenuList) {
        this.customerList = customerList;
        this.feastMenuList = feastMenuList;
    }

    private String generateOrderCode() {
        String lastID;
        if (this.isEmpty()) {
            lastID = String.format("%02d", 1);
        } else {
            String lastIDProduct = this.get(this.size() - 1).getOrderCode();
            int idNumber = Integer.parseInt(lastIDProduct);
            idNumber++;
            lastID = String.format("%02d", idNumber);
        }
        return lastID;
    }

    @Override
    public boolean placeAFeastOrder() {
        String orderId = generateOrderCode();
        Order newOrder = new Order();

        System.out.println("OrderID: " + orderId);
        newOrder.setOrderCode(orderId);

        String customerID;
        do {
            customerID = Utils.getString("Enter Customer ID: ",
                    "Customer ID cannot be empty!");

            if (customerList.getCustomerByID(customerID) == null) {
                System.out.println("Customer does not exist!");
            }
        } while (customerList.getCustomerByID(customerID) == null);
        newOrder.setCustomerID(customerID);

        String menuID;
        do {
            menuID = Utils.getString("Enter FeastMenu ID: ",
                    "FeastMenu ID cannot be empty!");

            if (feastMenuList.getObjectFeastMenuByID(menuID) == null) {
                System.out.println("FeastMenu does not exist!!");
            }
        } while (feastMenuList.getObjectFeastMenuByID(menuID) == null);
        newOrder.setMenuID(menuID);

        int numberOfTables;
        do {
            numberOfTables = Utils.getInt("Enter Number Of Tables: ",
                    "Number of tables must be greater than 0!", 1, Integer.MAX_VALUE);
        } while (numberOfTables <= 0);
        newOrder.setNumberOfTables(numberOfTables);

        Date eventDate;
        Date now = new Date();
        do {
            eventDate = Utils.getDate("Enter Event Date (dd/MM/yyyy): ",
                    "Wrong date format! Please enter again!");
            if (eventDate.compareTo(now) < 0) {
                System.out.println("The eventDate must be in the future!");
            }
        } while (eventDate.compareTo(now) < 0);
        newOrder.setEventDate(eventDate);

        for (Order order : this) {
            if (order.getCustomerID().equalsIgnoreCase(newOrder.getCustomerID())
                    && order.getMenuID().equalsIgnoreCase(newOrder.getMenuID())
                    && order.getEventDate().compareTo(newOrder.getEventDate()) == 0) {
                System.out.println("Duplicate order data!");
                return false;
            }
        }

        this.add(newOrder);
        System.out.println("Order placed successfully!");
        return true;
    }

    private Order getOrderByID(String orderID) {
        Order order = null;
        for (Order o : this) {
            if (o.getOrderCode().equalsIgnoreCase(orderID)) {
                order = o;
            }
        }
        return order;
    }

    @Override
    public boolean updateAnOrder(String code) {
        Order order = getOrderByID(code);
        if (order == null) {
            return false;
        }

        String newMenuID;
        do {
            newMenuID = Utils.updateString("Enter New FeastMenu ID: ", order.getMenuID()).toUpperCase();

            if (newMenuID.equalsIgnoreCase(order.getMenuID())) {
                break;
            }

            SetMenu newFeastMenu = feastMenuList.getObjectFeastMenuByID(newMenuID);
            if (newFeastMenu == null) {
                System.out.println("FeastMenu ID does not exist. Please enter again!");
            } else {
                order.setMenuID(newMenuID);
                break;
            }
        } while (true);

        int newNumberOfTables = Utils.updateInt("Enter New Number Of Tables: ", 1, order.getNumberOfTables());
        order.setNumberOfTables(newNumberOfTables);

        Date newEventDate;
        Date currentDate = new Date();
        do {
            newEventDate = Utils.updateDate(
                    "Enter New Date(dd/MM/yyyy): ",
                    order.getEventDate()
            );

            if (newEventDate.compareTo(currentDate) < 0) {
                System.out.println("Event date must be today or later. Please enter again!");
            }
        } while (newEventDate.compareTo(currentDate) < 0);

        order.setEventDate(newEventDate);

        return true;
    }

    @Override
    public void showListOrders() {
        if (this.isEmpty()) {
            System.out.println("List Order Empty.Nothing to print.");
        } else {
            Collections.sort(this, (o1, o2) -> {
                return o1.getEventDate().compareTo(o2.getEventDate());
            });
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("ID             | Event Date |Customer ID| Set Menu | Price    | Tables |        Cost        ");
            System.out.println("--------------------------------------------------------------------------------------------");
            for (Order ord : this) {
                SetMenu sm = feastMenuList.getObjectFeastMenuByID(ord.getMenuID());
                System.out.printf("%-15s|%-12s|%-11s|%-10s|%10s|%8d|%12s\n", ord.getOrderCode(), Acceptable.sdf.format(ord.getEventDate()), ord.getCustomerID(),
                        ord.getMenuID(), String.format("%,.0f", sm.getPrice()), ord.getNumberOfTables(), String.format("%,.0f", sm.getPrice() * ord.getNumberOfTables()));
            }
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public boolean readOrderFromFile() {
        boolean check = true;
        ArrayList<Order> listFile = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("feast_order_service.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listFile = (ArrayList<Order>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        if (!listFile.isEmpty()) {
            for (Order o : listFile) {
                this.add(o);
            }
        }
        return check;
    }

    
    
    
    @Override
    public boolean saveOrderToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("feast_order_service.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

}
