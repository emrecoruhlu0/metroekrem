module com.metroekrem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.metroekrem to javafx.fxml;
    exports com.metroekrem;
}