package ni.edu.uam.orm.config;

import ni.edu.uam.orm.model.Estudiante;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public final class HibernateUtil {
    private static final SessionFactory FACTORY = crear();

    private HibernateUtil() {}

    private static SessionFactory crear() {
        try (var in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("hibernate.properties")) {

            if (in == null) {
                throw new IllegalStateException("Falta configuración: No se encontró hibernate.properties en el classpath.");
            }

            Properties p = new Properties();
            p.load(in);
            return new Configuration().addProperties(p)
                    .addAnnotatedClass(Estudiante.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error al inicializar HibernateUtil: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() { return FACTORY; }
}