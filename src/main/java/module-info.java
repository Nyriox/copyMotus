module project.motus {
    requires javafx.controls;
    requires javafx.fxml;

    opens project.motus to javafx.fxml;
    exports project.motus;
}
