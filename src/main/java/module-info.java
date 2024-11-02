module org.example.practice_calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.practice_calculator to javafx.fxml;
    exports org.example.practice_calculator;
}