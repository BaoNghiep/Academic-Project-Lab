/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import controllers.CustomerList;
import controllers.SetMenuList;
import controllers.Menu;
import controllers.OrderList;
import DTO.Customer;
import DTO.I_Customer;
import DTO.I_SetMenu;
import DTO.I_Menu;
import DTO.I_Order;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class TraditionalFeastOrder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        I_Menu menu = new Menu();
        menu.addItem("1. Register customers.");
        menu.addItem("2. Update customer information.");
        menu.addItem("3. Search for customer information by name.");
        menu.addItem("4. Display feast menus.");
        menu.addItem("5. Place a feast order.");
        menu.addItem("6. Update order information.");
        menu.addItem("7. Save data to file.");
        menu.addItem("8. Display Customer or Order lists.");;
        menu.addItem("9. Exit the Program.");

        I_Customer customerList = new CustomerList();
        customerList.readCustomerFromFile();
        I_SetMenu feastMenuList = new SetMenuList();
        feastMenuList.loadFeastMenuFromFile();
        I_Order orderList = new OrderList(customerList, feastMenuList);
        orderList.readOrderFromFile();

        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    while (true) {
                        boolean checkAddCustomer = customerList.addACustomer();
                        if (checkAddCustomer) {                           
                            System.out.println("Add successful.");
                        } else {
                            System.out.println("Add fail!");
                        }
                        boolean isContinue = menu.confirmYesNo("Continue? (Y or N): ");
                        if (!isContinue) {

                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        String code = Utils.getString("Enter Code to Update: ", "Not blank.Input again!");
                        boolean checkUpdateCustomer = customerList.updateACustomer(code);
                        if (checkUpdateCustomer) {
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("Update fail!");
                        }
                        boolean isContinue = menu.confirmYesNo("Continue? (Y or N): ");
                        if (!isContinue) {

                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        String name = Utils.getString("Enter Name Search: ", "Not blank.Input again!");
                        List<Customer> listResult = customerList.searchCustomerByName(name);
                        if (listResult.isEmpty()) {
                            System.out.println("Not found customer by: " + name);
                        } else {
                            System.out.println("Matching customers: " + name);
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println("Code      | Customer Name      | Phone      |Email                 ");
                            System.out.println("-------------------------------------------------------------------");
                            for (Customer customer : listResult) {
                                System.out.printf("%-10s|%-20s|%-12s|%-20s\n", customer.getCode(), customer.getName(), customer.getPhone(), customer.getEmail());
                            }
                            System.out.println("-------------------------------------------------------------------");
                        }
                        boolean isContinue = menu.confirmYesNo("Continue? (Y or N): ");
                        if (!isContinue) {

                            break;
                        }
                    }

                    break;
                case 4:
                    feastMenuList.displayFeastMenu();
                    break;
                case 5:
                    while (true) {
                        boolean checkAddAnOrder = orderList.placeAFeastOrder();
                        if (checkAddAnOrder) {
                            System.out.println("Add successful.");
                        } else {
                            System.out.println("Add fail!");
                        }
                        boolean isContinue = menu.confirmYesNo("Continue? (Y or N): ");
                        if (!isContinue) {

                            break;
                        }
                    }
                    break;
                case 6:
                    while (true) {
                        String codeUpdate = Utils.getString("Enter OrderID Update: ", "Not blank.Input again!");
                        boolean checkUpdateAnOrder = orderList.updateAnOrder(codeUpdate);
                        if (checkUpdateAnOrder) {
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("Not found order.Update fail!");
                        }
                        boolean isContinue = menu.confirmYesNo("Continue? (Y or N): ");
                        if (!isContinue) {

                            break;
                        }
                    }

                    break;
                case 7:
                    boolean checkCustomerFile = customerList.saveCustomerToFile();
                    boolean checkOrderFile = orderList.saveOrderToFile();
                    if (checkCustomerFile) {
                        System.out.println("Save customer to file successful.");
                    } else {
                        System.out.println("Save customer to file fail.");
                    }
                    if (checkOrderFile) {
                        System.out.println("Save order to file successful.");
                    } else {
                        System.out.println("Save order to file fail.");
                    }
                    break;
                case 8:
                    int choiceDisplay = Utils.getInt("Enter Choice Display All List[1.Customer 2.Order]: ", "Just 1 or 2", 1, 2);
                    switch (choiceDisplay) {
                        case 1:
                            customerList.showListCustomers();
                            break;
                        case 2:
                            orderList.showListOrders();
                            break;
                    }
                    break;
                case 9:
                    System.out.println("Exist!");
                    break;
            }
        } while (choice != 9);
    }
}
