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
    public static String DEV_ID_VALID = "^[Dd][Ee][Vv]\\d{3}$";
    public static String PROJECT_ID_VALID = "^[Pp][Rr][Oo][Jsj]\\d{2}$";
    public static String NAME_VALID = "^[a-zA-Z ]{2,100}$";
}
