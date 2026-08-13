/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface I_Customer {

    Customer getCustomerByID(String code);

    boolean addACustomer();

    boolean updateACustomer(String code);

    List<Customer> searchCustomerByName(String input);

    void showListCustomers();

    boolean readCustomerFromFile();

    boolean saveCustomerToFile();
}
