package tn.itskills.android.webservices.models;

import java.io.Serializable;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */

public class EmployeeWrapper implements Serializable{

    private int id;
    private String name;
    private String designation;
    private String salary;
    private String image;

    public EmployeeWrapper(int id, String name, String designation, String salary, String image) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.image = image;
    }

    public EmployeeWrapper(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeWrapper(int id, String name, String designation, String salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public EmployeeWrapper(String name, String designation, String salary, String image) {
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.image = image;
    }

    public EmployeeWrapper(String name, String designation, String salary) {
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }

}
