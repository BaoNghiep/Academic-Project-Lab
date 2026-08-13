/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utils.Utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import dto.Acceptable;
import dto.Developer;
import dto.I_List;
import dto.Project;

/**
 *
 * @author Admin
 */
public class DeveloperList extends ArrayList<Developer> implements I_List {

    private ArrayList<Project> listProject = new ArrayList();

    @Override
    public boolean readDataFromFile() {
        boolean check = true;
        ArrayList<Developer> listDeveloper = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("developers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listDeveloper = (ArrayList<Developer>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        if (!listDeveloper.isEmpty()) {
            for (Developer d : listDeveloper) {
                this.add(d);
            }
        }
        try {
            FileInputStream fis = new FileInputStream("projects.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listProject = (ArrayList<Project>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    @Override
    public void showInforDeveloperList(ArrayList<Developer> list) {
        if (list.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            System.out.println("ID        NAME                LANGUAGUES          SALARY");
            for (Developer d : list) {
                System.out.println(d.toString());
            }
        }
    }

    @Override
    public void showAllList() {
        showInforDeveloperList(this);
    }

    
    @Override
    public Developer searchDeveloperByID(String devID) {
        Developer d = null;
        int index = this.indexOf(new Developer(devID));
        if (index != -1) {
            d = this.get(index);
        }
        return d;
    }

    @Override
    public boolean updateADeveloper(String devID) {
        boolean check = false;
        int index = this.indexOf(new Developer(devID));
        if (index != -1) {
            Developer developer = this.get(index);
            developer.update();
            check = true;
        }
        return check;
    }

    @Override
    public ArrayList<Developer> searchDeveloperByLanguages(String languages) {
        ArrayList<Developer> listSearch = new ArrayList();
        for (Developer d : this) {
            for (String s : d.getArrayLanguage()) {
                if (s.equalsIgnoreCase(languages)) {
                    listSearch.add(d);
                    break;
                }
            }
        }
        return listSearch;
    }

    @Override
    public boolean addNewProject() {
        boolean check = true;
        boolean continous;
        try {
            String projectID;
            do {
                continous = false;
                projectID = Utils.getString("Enter ID Project(PROJxx): ", "Input again!", Acceptable.PROJECT_ID_VALID);
                if (listProject.indexOf(new Project(projectID)) != -1) {
                    continous = true;
                }
            } while (continous);

            Project newProject = new Project(projectID);
            newProject.create();

            if (this.indexOf(new Developer(newProject.getDevID())) == -1) {
                System.out.println("Not found developer");
                check = false;
            }
            Date current = new Date();
            if (newProject.getStartDate().compareTo(current) < 0) {
                System.out.println("Start date must be greater than or equals today!");
                check = false;
            }
            if (check) {
                listProject.add(newProject);
            }
        } catch (Exception e) {
        }
        return check;
    }

    @Override
    public void listAllProjectByDeveloperGrouped() {
        HashMap<String, String> mapDevIdOfProject = new HashMap<>();
        for (Project project : listProject) {
            mapDevIdOfProject.put(project.getDevID(), "");
        }
        for (String devID : mapDevIdOfProject.keySet()) {
            int indexDev = this.indexOf(new Developer(devID));
            Developer d = this.get(indexDev);
            System.out.println("ID        NAME                LANGUAGUES          SALARY");
            System.out.println(d.toString());
            System.out.println("PROJECT ID   DURATION     START DATE");
            for (Project p : listProject) {
                if (devID.equalsIgnoreCase(p.getDevID())) {
                    System.out.printf("|%-12s|%-12d|%-12s|\n", p.getId(), p.getMonth(), Acceptable.sdf.format(p.getStartDate()));
                }
            }
        }
    }

    @Override
    public int calculteTotalExperienceByDevID(String devID) {
        int sum = 0;
        for (Project p : listProject) {
            if (devID.equalsIgnoreCase(p.getDevID())) {
                sum = sum + p.getMonth();
            }
        }
        return sum;
    }

    @Override
    public Developer removeADeveloper(String devID) {
        Developer developer = null;
        int index = this.indexOf(new Developer(devID));
        if (index != -1) {
            boolean valid = true;
            for (Project p : listProject) {
                if (p.getDevID().equalsIgnoreCase(devID)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                developer = this.remove(index);
            }
        }
        return developer;
    }

    @Override
    public ArrayList<Developer> sortDeveloperBySalary() {
        ArrayList<Developer> listSort = new ArrayList<>();
        if (!this.isEmpty()) {
            for (Developer d : this) {
                listSort.add(d);
            }
            Collections.sort(listSort, new Comparator<Developer>() {
                @Override
                public int compare(Developer o1, Developer o2) {
                    return o1.getSalary() - o2.getSalary();
                }
            });
        }
        return listSort;
    }

    @Override
    public boolean saveDataToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("developers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }

        try {
            FileOutputStream fos = new FileOutputStream("projects.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listProject);
            oos.close();
            fos.close();
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    @Override
    public boolean addNewDeveloper() {
        boolean check = true;
        boolean continous;
        try {
            String devID;
            do {
                continous = false;
                devID = Utils.getString("Enter ID(Ex: DEV001): ", "Input again!", Acceptable.DEV_ID_VALID);
                if (this.indexOf(new Developer(devID)) != -1) {
                    continous = true;
                }
            } while (continous);
            
            Developer newDeveloper = new Developer(devID);
            newDeveloper.create();
            if (check) {
                this.add(newDeveloper);
            }
        } catch (Exception e) {
        }
        return check;
    }

}
