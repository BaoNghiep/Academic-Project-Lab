/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public interface Acceptable {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static String TOUR_ID_VALID = "^[Tt]\\d{5}$";
    public static String HOME_ID_VALID = "^[Hh][Ss]\\d{4}$";
    public static String BOOKING_ID_VALID = "^[Bb]\\d{5}$";
    public static String PHONE_VALID = "^[0]\\d{9}$";
}
