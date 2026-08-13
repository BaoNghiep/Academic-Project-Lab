/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import controllers.Acceptable;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Customer implements Serializable{

    private String id;
    private String name;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCode() {
        return id;
    }

    public void setCode(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + '}';
    }


    public boolean create() {
        boolean check = false;
        try {
            this.name = Utils.getStringREGEX("Enter Name[2->25 characters]: ", "Please check length of name!", Acceptable.NAME_FORMAT);
            this.phone = Utils.getStringREGEX("Enter Phone(Example 0x..x): ", "x is digit and must exactly 10 digits", Acceptable.PHONE_FORMAT);
            this.email = Utils.getStringREGEX("Enter Email: ", "Wrong format email.Input again!", Acceptable.EMAIL_FORMAT);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
    
    public boolean update() {
        boolean check = false;
        try {
            this.name = Utils.updateStringREGEX("Enter New Name: ", name, Acceptable.NAME_FORMAT);
            this.phone = Utils.updateStringREGEX("Enter New Phone: ", phone, Acceptable.PHONE_FORMAT);
            this.email = Utils.updateStringREGEX("Enter New Email: ", email, Acceptable.EMAIL_FORMAT);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
}
