/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dto.I_Menu;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
    }

    @Override
    public void addMenu(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        for (String s : this) {
            System.out.println(s);
        }
    }

    @Override
    public int getChoice() {
        String inputMsg = "Choose [1.." + this.size() + "]: ";
        return Utils.getInt(inputMsg, "Wrong.Input again!", 1, this.size());
    }
}
