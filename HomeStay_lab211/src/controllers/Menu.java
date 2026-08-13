/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import dto.I_Menu;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> implements I_Menu {

    private String title;

    public Menu(String title) {
        this.title = title;
    }

    @Override
    public void addMenu(String newOption) {
        this.add(newOption);
    }

    @Override
    public void showMenu() {
        System.out.println("Welcome To " + title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }

    @Override
    public int getChoice() {
        String inputMsg = "Choose [1.." + this.size() + "]: ";
        return Utils.getAnInteger(inputMsg, "Wrong.Input again!", 1, this.size());
    }
}
