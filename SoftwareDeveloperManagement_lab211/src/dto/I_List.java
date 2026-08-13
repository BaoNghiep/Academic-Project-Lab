/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface I_List {

    void showAllList();

    void showInforDeveloperList(ArrayList<Developer> list);

    boolean addNewDeveloper();

    boolean updateADeveloper(String devID);

    Developer searchDeveloperByID(String devID);

    ArrayList<Developer> searchDeveloperByLanguages(String languages);

    boolean addNewProject();

    void listAllProjectByDeveloperGrouped();

    int calculteTotalExperienceByDevID(String devID);

    Developer removeADeveloper(String devID);

    ArrayList<Developer> sortDeveloperBySalary();

    boolean readDataFromFile();

    boolean saveDataToFile();
}
