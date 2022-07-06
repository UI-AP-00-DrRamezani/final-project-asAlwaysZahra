module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.example.demo3 to javafx.fxml;
//    exports com.example.demo3;
    exports com.example.demo3.GUI;
    opens com.example.demo3.GUI to javafx.fxml;
}