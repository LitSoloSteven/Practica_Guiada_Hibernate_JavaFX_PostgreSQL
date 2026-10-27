module ni.edu.uam.orm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;

    // Esta línea es esencial para que Java permita cargar el driver de PostgreSQL
    requires org.postgresql.jdbc;

    opens ni.edu.uam.orm to javafx.fxml;
    opens ni.edu.uam.orm.controller to javafx.fxml;
    opens ni.edu.uam.orm.model to org.hibernate.orm.core, javafx.base;

    exports ni.edu.uam.orm;
}