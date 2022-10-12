package edu.ucdenver.csci3920.university;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Student implements Serializable {
    private LocalDate dob;
    private String name;
    private String email;
    // To-Do Student id
    private String id;

    public Student(String name, LocalDate dob, String id) {
        this.dob = dob;
        this.name = name;
        this.email = name +"@ucdenver.edu";
        this.id = id;
    }
//right click and generate to get getters and setters

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public abstract String getStanding();

    public String getId(){return id;}

    @Override
    public String toString(){
        return String.format( "%-20s - %s - %-50s - Standing: %s",
            this.name, this.dob, this.email, this.getStanding());
    }
}
