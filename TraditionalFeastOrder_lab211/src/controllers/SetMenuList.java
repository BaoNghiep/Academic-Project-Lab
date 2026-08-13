/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import DTO.SetMenu;
import DTO.I_SetMenu;

/**
 *
 * @author Admin
 */
public class SetMenuList extends ArrayList<SetMenu> implements I_SetMenu {

    @Override
    public boolean loadFeastMenuFromFile() {
        boolean check = false;
        File filePathMenu = new File("FeastMenu.csv");
        if (filePathMenu.exists()) {
            this.clear();
            try {
                FileReader fr = new FileReader(filePathMenu);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String menuFeast[] = line.split(",");
                    String code = menuFeast[0].trim();
                    String name = menuFeast[1].trim();
                    double price = Double.parseDouble(menuFeast[2].trim());
                    String ingredients = menuFeast[3].trim();
                    this.add(new SetMenu(code, name, price, ingredients));
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Cannot read data from FeastMenu.csv. Please check it.");
            }
        }
        return check;
    }

    @Override
    public void displayFeastMenu() {
        loadFeastMenuFromFile();
        if (this.isEmpty()) {
            System.out.println("List FeastMenu empty.Nothing to print");
        } else {
            Collections.sort(this, (o1, o2) -> {
                return (int) (o1.getPrice() - o2.getPrice());
            });
            System.out.println("-----------------------------------------------");
            System.out.println("List of Set Menus for ordering party: ");
            System.out.println("-----------------------------------------------");
            for (SetMenu sm : this) {
                System.out.println("Code      : " + sm.getCode());
                System.out.println("Name      : " + sm.getName());
                System.out.println("Price     : " + String.format("%,.0f", sm.getPrice()) + " VND");
                System.out.println("Ingredients: ");
                String description[] = sm.getDescription().split("#");
                for (String s : description) {
                    System.out.println(s);
                }
                System.out.println("-----------------------------------------------");
            }
        }
    }

    @Override
    public SetMenu getObjectFeastMenuByID(String feastMenuID) {
        SetMenu sm = null;
        for (SetMenu setMenu : this) {
            if (setMenu.getCode().equalsIgnoreCase(feastMenuID)) {
                sm = setMenu;
            }
        }
        return sm;
    }

}
