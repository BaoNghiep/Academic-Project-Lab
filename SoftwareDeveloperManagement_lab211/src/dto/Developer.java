/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Developer implements Serializable {

    private String devID;
    private String fullName;
    private String languages[];
    private int salary;

    @Override
    public boolean equals(Object obj) {
        Developer p = (Developer) obj;
        return this.devID.equals(p.getId());
    }

    public Developer() {
    }

    public Developer(String devID) {
        this.devID = devID;
    }

    public Developer(String devID, String fullName, String[] languages, int salary) {
        this.devID = devID;
        this.fullName = fullName;
        this.languages = languages;
        this.salary = salary;
    }

    public String getId() {
        return devID;
    }

    public void setId(String devID) {
        this.devID = devID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        String result = "";
        for (String s : languages) {
            result = result + s + ",";
        }
        return result.substring(0, result.length() - 1);
    }

    public String[] getArrayLanguage() {
        return languages;
    }

    public void setLanguage(String[] language) {
        this.languages = language;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        String x = String.format("%-10s%-20s%-20s%-10d", devID, fullName, getLanguage(), salary);
        return x;
    }

    public boolean create() {
        boolean check = false;
        try {
            fullName = Utils.getString("Enter Name: ", "Input again!", Acceptable.NAME_VALID);
            String languagesString = Utils.getString("Enter Languages(Ex: Java, C++): ", "Input again!");
            languages = languagesString.trim().split(",");
            salary = Utils.getInt("Enter Salary($): ", "More than 1000", 1000);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean update() {
        boolean check = false;
        try {
            salary = Utils.updateInt("Enter Salary($): ", 1000, salary);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
}
