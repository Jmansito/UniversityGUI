package edu.ucdenver.csci3920.university;

import java.time.LocalDate;

// Making abstract for part 3 of assignment
// Prevent the class to instantiate objects, and in consequence remove its implementation of getStanding()
public abstract class Graduate extends Student{

    public Graduate(String name, LocalDate dob, String id){
        super(name, dob, id);
    }
    @Override
    public String getStanding(){
        return "Graduate";
    }

}
