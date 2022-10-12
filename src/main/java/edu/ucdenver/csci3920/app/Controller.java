package edu.ucdenver.csci3920.app;

import edu.ucdenver.csci3920.university.Course;
import edu.ucdenver.csci3920.university.Student;
import edu.ucdenver.csci3920.university.University;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {
    public TextField txtStudentName;
    public DatePicker dtpStudentDOB;
    public Button btnAddUndergraduate;
    public Button btnAddMaster;
    public Button btnAddPhD;
    public TextField txtPhDDissertation;
    public ComboBox<String> selCourseSubject;
    public TextField txtCourseNumber;
    public TextField txtCourseTitle;
    public Button btnAddCourse;
    public Button btnExit;
    public Tab tabListStudents;
    public ListView<Student> lstStudent;
    public Tab tabListCourses;
    public ListView<Course> lstCourse;

    private University university;

    public Controller(){
        university = new University();
        this.selCourseSubject = new ComboBox<>();
        this.lstStudent = new ListView<>();
        this.lstCourse = new ListView<>();
    }
    public void initialize(){

        this.selCourseSubject.setItems(FXCollections.observableArrayList("CSCI", "MATH"));
        this.lstStudent.setItems(FXCollections.observableArrayList(university.getStudents()));
        this.lstCourse.setItems(FXCollections.observableArrayList(university.getCourses()));
    }
    private void cleanAddStudent(){
        this.txtStudentName.setText("");
        this.dtpStudentDOB.setValue(null);
        this.txtPhDDissertation.setText("");
    }



    public void addUndergraduateStudent(ActionEvent actionEvent){
        university.addUndergrad(this.txtStudentName.getText(), this.dtpStudentDOB.getValue(), "9");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User added Successfully");
        alert.show();
        System.out.println(university);
        cleanAddStudent();
    }

    public void addMasterStudent(ActionEvent actionEvent) {
        university.addMaster(this.txtStudentName.getText(), this.dtpStudentDOB.getValue(), "9");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User added Successfully");
        alert.show();
        System.out.println(university);
        cleanAddStudent();
    }

    public void addPhDStudent(ActionEvent actionEvent) {
        university.addPhD(this.txtStudentName.getText(), this.dtpStudentDOB.getValue(), "9", this.txtPhDDissertation.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User added Successfully");
        alert.show();
        System.out.println(university);
        cleanAddStudent();
    }

    public void addCourse(ActionEvent actionEvent) {
        try{
            university.addCourse(this.selCourseSubject.getValue(), Integer.parseInt(this.txtCourseNumber.getText()),
                    this.txtCourseTitle.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course added Successfully");
            alert.show();
            System.out.println(university);
        }
        catch(IllegalArgumentException iae){
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
        }

    }

    public void exitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();
    }

    public void lstStudentsUpdate(Event event) {
        if(this.tabListStudents.isSelected()){
            this.lstStudent.setItems(FXCollections.observableArrayList(university.getStudents()));
        }
    }

    public void lstCoursesUpdate(Event event) {
        if(this.tabListCourses.isSelected()){
            this.lstCourse.setItems(FXCollections.observableArrayList(university.getCourses()));
        }
    }

    public void saveToFile(ActionEvent actionEvent) {
        university.saveToFile();
    }

    public void loadFromFile(ActionEvent actionEvent) {
        this.university  = University.loadFromFile();
        this.initialize();
    }
}