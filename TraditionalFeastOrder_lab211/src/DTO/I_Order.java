/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public interface I_Order {

    boolean placeAFeastOrder();

    boolean updateAnOrder(String code);

    void showListOrders();

    boolean readOrderFromFile();

    boolean saveOrderToFile();
}
