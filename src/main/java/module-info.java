module com.example.graphicalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.graphicalproject to javafx.fxml;
    exports com.example.graphicalproject;
}