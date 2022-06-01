module com.example.funcionesfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires exp4j;


    opens com.example.funcionesfx to javafx.fxml;
    exports com.example.funcionesfx;
}