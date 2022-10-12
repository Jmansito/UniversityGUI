module com.example.universitygui {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ucdenver.csci3920.app to javafx.fxml;
    exports edu.ucdenver.csci3920.app;
}