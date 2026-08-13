/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public interface Acceptable {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static String FEAST_CODE = "^PW\\d{3}$";
    public static String ID_FORMAT = "^[CcGgKk]\\d{4}$";
    public static String NAME_FORMAT = "^.{2,25}$";
    public static String PHONE_FORMAT = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|"
            + "081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$";
    String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static String EMAIL_FORMAT = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

}
