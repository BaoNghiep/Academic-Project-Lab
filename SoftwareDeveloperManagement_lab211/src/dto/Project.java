/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import utils.Utils;

/**
 *
 * @author Admin
 */
public class Project implements Serializable {

    private String id;
    private String devID;
    private String name;
    private int duration;
    private Date startDate;

    @Override
    public boolean equals(Object obj) {
        Project p = (Project) obj;
        return this.id.equals(p.getId());
    }

    public Project() {
    }

    public Project(String id) {
        this.id = id;
    }

    public Project(String id, String devID, String name, int duration, Date startDate) {
        this.id = id;
        this.devID = devID;
        this.name = name;
        this.duration = duration;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevID() {
        return devID;
    }

    public void setDevID(String devID) {
        this.devID = devID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return duration;
    }

    public void setMonth(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", devID=" + devID + ", name=" + name + ", duration=" + duration + ", startDate=" + startDate + '}';
    }

    public boolean create() {
        boolean check = false;
        try {
            devID = Utils.getString("Enter ID(DEVxxx): ", "Input again!", Acceptable.DEV_ID_VALID);
            name = Utils.getString("Enter Name: ", "Input again!");
            duration = Utils.getInt("Enter Duration(month): ", "More than 0", 1);
            startDate = Utils.getDate("Enter Start Date(dd/MM/yyyy): ", "Input again!");
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

}
