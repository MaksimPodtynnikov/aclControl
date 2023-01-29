module com.example.aclcontrol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aclcontrol to javafx.fxml;
    exports com.example.aclcontrol;
}