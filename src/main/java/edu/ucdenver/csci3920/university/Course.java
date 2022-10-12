package edu.ucdenver.csci3920.university;

import java.io.Serializable;

public class Course implements Serializable {
    private String subject;
    private int number;
    private String title;

    public Course(String subject, int number, String title){
        this.subject = subject;
        this.number = number;
        this.title = title;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return String.format("%s%-4d - %s",
                this.subject, this.number, this.title);
    }

}
