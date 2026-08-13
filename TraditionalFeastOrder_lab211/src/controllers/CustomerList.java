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
import DTO.Customer;
import DTO.I_Customer;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class CustomerList extends ArrayList<Customer> implements I_Customer {

    @Override
    public Customer getCustomerByID(String code) {
        Customer c = null;
        for (Customer customer : this) {
            if (customer.getCode().equalsIgnoreCase(code)) {
                c = customer;
            }
        }
        return c;
    }

    @Override
    public boolean addACustomer() {
        Customer newCustomer = new Customer();
        String code;
        do {
            code = Utils.getStringREGEX("Enter ID(C,K,G and 4 digit): ", "Please check format!", Acceptable.ID_FORMAT);
            if (getCustomerByID(code) != null) {
                System.out.println("Customer ID already exists. Please enter again!");
            }
        } while (getCustomerByID(code) != null);

        newCustomer.setCode(code);
        newCustomer.create(); 

        this.add(newCustomer);
        return true;
    }

    @Override
    public boolean updateACustomer(String code) {
        boolean check = false;
        Customer customer = getCustomerByID(code);
        if (customer != null) {
            customer.update();
            check = true;
        }
        return check;
    }

    @Override
    public ArrayList<Customer> searchCustomerByName(String input) {
        ArrayList<Customer> listResult = new ArrayList();
        for (Customer customer : this) {
            if (customer.getName().toUpperCase().contains(input.toUpperCase())) {
                listResult.add(customer);
            }
        }
        if (!listResult.isEmpty()) {
            Collections.sort(this, (o1, o2) -> {
                return o1.getName().compareToIgnoreCase(o2.getName());
            });
        }
        return listResult;
    }

    @Override
    public void showListCustomers() {
        if (this.isEmpty()) {
            System.out.println("List Cumtomer Empty.Nothing to print");
        } else {
            Collections.sort(this, (o1, o2) -> {
                return o1.getName().compareToIgnoreCase(o2.getName());
            });
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Code      | Customer Name           | Phone      | Email     ");
            System.out.println("-------------------------------------------------------------------------");
            for (Customer ctm : this) {
                System.out.printf("%-10s|%-25s|%-12s|%-20s\n", ctm.getCode(), ctm.getName(), ctm.getPhone(), ctm.getEmail());
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    @Override
    public boolean readCustomerFromFile() {
        boolean check = true;
        ArrayList<Customer> listFile = new ArrayList();
        try {
            FileInputStream fis = new FileInputStream("customers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listFile = (ArrayList<Customer>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        if (!listFile.isEmpty()) {
            for (Customer c : listFile) {
                this.add(c);
            }
        }
        return check;
    }

    @Override
    public boolean saveCustomerToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("customers.dat");
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
