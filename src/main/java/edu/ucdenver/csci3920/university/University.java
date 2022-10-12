package edu.ucdenver.csci3920.university;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class University implements Serializable {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    public static final String filename = "./university.ser";

    public University(){
        this.students = new ArrayList<>(100);
        this.courses = new ArrayList<>(100);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Student getStudent(String name) throws IllegalArgumentException{
        for(Student s: this.students){
            if ((s.getName()).equalsIgnoreCase(name)){
                return s;
            }
        }
        throw new IllegalArgumentException("Student is not in the edu.ucdenver.csci3920.university.university");
    }



    public Course getCourse(String subject, int number) throws IllegalArgumentException{
        Course course;
        for(Course c: this.courses){
            if ((c.getNumber()==number) && c.getSubject().equalsIgnoreCase(subject)){
                return c;
            }
        }
        throw new IllegalArgumentException("Course is not in the edu.ucdenver.csci3920.university.university");
    }

    public void addCourse(String subject, int number, String title) throws IllegalArgumentException{
        Course course = null;
        try{
            course = this.getCourse(subject, number);
        }
        catch (IllegalArgumentException iae){
            this.courses.add(new Course(subject,number,title));
        }
        if (course != null){
            throw new IllegalArgumentException("Course is already in the edu.ucdenver.csci3920.university.university");
        }

    }

    public void addUndergrad(String name, LocalDate dob, String id){
        this.students.add(new Undergraduate(name,dob,id));
    }
    public void addMaster(String name, LocalDate dob, String id){
        this.students.add(new Master(name,dob,id));
    }
    public void addPhD(String name, LocalDate dob, String id, String dissertation){
        this.students.add(new PhD(name,dob,id,dissertation));
    }

    // Methods for the homework

    // sortStudents method
    public void sortStudents(){
        getStudents().sort(new StudentComparator());
    }

    // countStudentsPerStanding
    // Takes no arguments, returns a key-value data structure
    public Map<String, Integer> countStudentsPerStanding()
    {

        // Building a map for the three standings. Showing the count even if empty
        Map<String, Integer> myMap2 = new HashMap<>();
        myMap2.put("Master", 0);
        myMap2.put("Undergraduate", 0);
        myMap2.put("PhD", 0);
        for(Student s : this.getStudents()){
            if (s.getStanding().equals("Master")){
                int count = myMap2.get("Master");
                myMap2.put("Master", count + 1);
            }
            else if (s.getStanding().equals("Undergraduate")){
                int count = myMap2.get("Undergraduate");
                myMap2.put("Undergraduate", count + 1);
            }
            else if (s.getStanding().equals("PhD")){
                int count = myMap2.get("PhD");
                myMap2.put("PhD", count + 1);
            }
        }
        return myMap2;
    }

    // removeStudentByID method. Takes student ID and removes student with matching id

    public void removeStudentById(String id) throws IllegalArgumentException{
        // Different way to accomplish this, but does not have error handling
        // students.removeIf(student -> student.getId().equals(id));

        // Error exception method
        boolean exists = false;
        for(Student s : this.getStudents()) {
            if (s.getId().equals(id)) {
                students.remove(s);
                exists = true;
                break;
            }
        }
         if (!exists) {
             throw new IllegalArgumentException("No student found with id:" + id);
         }
    }

    // randomizeStudentList method, does not return anything.
    public void randomizeStudentList(){
        Collections.shuffle(students);
    }

    @Override
    public String toString(){
        return String.format("University with %d students and %d courses.",
                this.students.size(), this.courses.size());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void saveToFile(){


        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally {
            if(oos != null){
                try{
                    oos.close();
                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static University loadFromFile(){
        ObjectInputStream ois = null;
        University university = null;

        try{
            ois = new ObjectInputStream(new FileInputStream(University.filename));
            university = (University) ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
            university = new University();
        }
        finally {
            if(ois != null){
                try{
                    ois.close();
                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        return university;
    }


}
