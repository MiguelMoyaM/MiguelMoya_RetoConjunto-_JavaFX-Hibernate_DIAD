module com.example.miguelmoya_retoconjuntodiad_2parte {
    requires org.hibernate.orm.core;

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.naming;
    opens com.example.miguelmoya_retoconjuntodiad_2parte to javafx.fxml;
    opens com.example.miguelmoya_retoconjuntodiad_2parte.model to org.hibernate.orm.core;
    exports com.example.miguelmoya_retoconjuntodiad_2parte;
    exports com.example.miguelmoya_retoconjuntodiad_2parte.controller;
    opens com.example.miguelmoya_retoconjuntodiad_2parte.controller to javafx.fxml;
}
