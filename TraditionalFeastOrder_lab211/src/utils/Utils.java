/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controllers.Acceptable;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Utils {

    public static Scanner sc = new Scanner(System.in);

    public static int getInt(String inputMsg, String errorMsg, int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if (id.length() == 0) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static Date getDate(String inputMsg, String errorMsg) {
        String data;
        while (true) {
            System.out.print(inputMsg);
            data = sc.nextLine().trim();
            try {
                Acceptable.sdf.setLenient(false);
                return Acceptable.sdf.parse(data);
            } catch (ParseException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getStringREGEX(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String updateStringREGEX(String inputMsg, String oldData, String regex) {
        String result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            boolean check = tmp.matches(regex);
            if (!check) {
                tmp = Utils.getStringREGEX(inputMsg, "Wrong format.Input again!", regex);
            }
            result = tmp;
        }
        return result;
    }

    public static String updateString(String inputMsg, String oldData) {
        String result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int updateInt(String msg, int min, int oldValue) {
        int value;
        do {
            System.out.print(msg);
            value = Integer.parseInt(sc.nextLine());
            if (value < min) {
                System.out.println("Value must be greater than 0");
            }
        } while (value < min);
        return value;
    }

    public static Date updateDate(String inputMsg, Date oldDate) {
        boolean check = true;
        Date resultDate = oldDate;
        do {
            try {
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    Acceptable.sdf.setLenient(false);
                    resultDate = Acceptable.sdf.parse(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check == true);
        return resultDate;
    }

}
