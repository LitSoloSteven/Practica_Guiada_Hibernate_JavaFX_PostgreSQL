package ni.edu.uam.orm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {

    @Column(nullable = false, length = 100)
    private String carrera;

    @Column(length = 20)
    private String telefono;

    public Estudiante() {
    }

    public Estudiante(String nombres, String apellidos, String correo,
                      String carrera) {
        super(nombres, apellidos, correo);
        this.carrera = carrera;
    }

    public Estudiante(String nombres, String apellidos, String correo,
                      String carrera, String telefono) {
        super(nombres, apellidos, correo);
        this.carrera = carrera;
        this.telefono = telefono;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Estudiante otro)) {
            return false;
        }

        return getId() != null && Objects.equals(getId(), otro.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? System.identityHashCode(this)
                : Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Estudiante{"
                + "id=" + getId()
                + ", nombres='" + getNombres() + '\''
                + ", apellidos='" + getApellidos() + '\''
                + ", correo='" + getCorreo() + '\''
                + ", carrera='" + carrera + '\''
                + ", telefono='" + telefono + '\''
                + '}';
    }
}