module ni.edu.uam.orm {
    requires javafx.controls;
    requires javafx.fxml;

    // Dependencias de Hibernate y Base de Datos
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;

    // Abrir los paquetes a JavaFX y Hibernate
    opens ni.edu.uam.orm to javafx.fxml;
    opens ni.edu.uam.orm.controller to javafx.fxml;
    opens ni.edu.uam.orm.model to org.hibernate.orm.core, javafx.base;

    exports ni.edu.uam.orm;
}