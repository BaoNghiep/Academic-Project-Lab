/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import DTO.I_Menu;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        Utils input = new Utils();
        String result = input.getStringREGEX(welcome, "Just Y or N", "[YNyn]");
        return result.equalsIgnoreCase("Y");
    }

    @Override
    public int getChoice() {
        String input = "Enter Choice[1.." + this.size() + "]: ";
        return Utils.getInt(input, "Invalid.", 1, this.size());
    }

}
