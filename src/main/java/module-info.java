module yoonchan.assignment02 {
    requires javafx.controls;
    requires javafx.fxml;

    opens yoonchan.assignment02 to javafx.fxml;
    exports yoonchan.assignment02;
}
