/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import controllers.DeveloperList;
import dto.Acceptable;
import dto.Developer;
import utils.Utils;
import dto.I_List;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addMenu("1. List all Developers.");
        menu.addMenu("2. Add a new Developer.");
        menu.addMenu("3. Search for a Developer by ID.");
        menu.addMenu("4. Update a Developer's salary by ID");
        menu.addMenu("5. List all Developers by Language.");
        menu.addMenu("6. Add a new Project.");
        menu.addMenu("7. List all Projects by Developer (Grouped).");
        menu.addMenu("8. Calculate Total Experience by Dev ID.");
        menu.addMenu("9. Remove a Developer by ID.");
        menu.addMenu("10. Sort Developers by Salary.");
        menu.addMenu("11. Save data to files.");
        menu.addMenu("12. Exit.");

        I_List list = new DeveloperList();
        list.readDataFromFile();

        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.showAllList();
                    break;
                case 2:
                    if (list.addNewDeveloper()) {
                        System.out.println("Add developer success.");
                    } else {
                        System.out.println("Add developer fail.");
                    }
                    break;
                case 3:
                    String devIDSearch = Utils.getString("Enter ID(DEVxxx): ", "Input again!", Acceptable.DEV_ID_VALID);
                    Developer developer = list.searchDeveloperByID(devIDSearch);
                    if (developer == null) {
                        System.out.println("Developer does not exist!");
                    } else {
                        System.out.println("ID        NAME                LANGUAGUES          SALARY");
                        System.out.println(developer.toString());
                    }
                    break;
                case 4:
                    String devIDUpdate = Utils.getString("Enter ID(DEVxxx): ", "Input again!", Acceptable.DEV_ID_VALID);
                    if (list.updateADeveloper(devIDUpdate)) {
                        System.out.println("Update developer successful.");
                    } else {
                        System.out.println("Update developer fail.");
                    }
                    break;
                case 5:
                    String languageFind = Utils.getString("Enter Language: ", "Input again!");
                    ArrayList<Developer> listFind = list.searchDeveloperByLanguages(languageFind);
                    list.showInforDeveloperList(listFind);
                    break;
                case 6:
                    if (list.addNewProject()) {
                        System.out.println("Add project success.");
                    } else {
                        System.out.println("Add project fail.");
                    }
                    break;
                case 7:
                    list.listAllProjectByDeveloperGrouped();
                    break;
                case 8:
                    String devIDCalculte = Utils.getString("Enter ID(DEVxxx): ", "Input again!", Acceptable.DEV_ID_VALID);
                    int totalExperience = list.calculteTotalExperienceByDevID(devIDCalculte);
                    if (totalExperience != 0) {
                        System.out.println("DEV ID    TOTAL EXPERIENCE");
                        System.out.printf("%-10s%-18d\n", devIDCalculte, totalExperience);
                    } else {
                        System.out.println("Not found project.");
                    }
                    break;
                case 9:
                    String devIDDelete = Utils.getString("Enter ID(DEVxxx): ", "Input again!", Acceptable.DEV_ID_VALID);
                    if (list.removeADeveloper(devIDDelete) != null) {
                        System.out.println("Remove developer success.");
                    } else {
                        System.out.println("Remove  developer fail.");
                    }
                    break;
                case 10:
                    ArrayList<Developer> listSort = list.sortDeveloperBySalary();
                    list.showInforDeveloperList(listSort);
                    break;
                case 11:
                    if (list.saveDataToFile()) {
                        System.out.println("Save file success");
                    } else {
                        System.out.println("Save file fail.");
                    }
                    break;
                case 12:
                    System.out.println("Exist!");
                    break;
            }
        } while (choice != 12);
    }
}
