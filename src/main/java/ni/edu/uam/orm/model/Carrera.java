package ni.edu.uam.orm.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Carrera {

    INGENIERIA_SISTEMAS("Ingeniería de Sistemas"),
    ADMINISTRACION("Administración de Empresas"),
    CONTADURIA("Contaduría Pública"),
    DERECHO("Derecho"),
    ARQUITECTURA("Arquitectura");

    private final String nombre;

    Carrera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static List<String> nombres() {
        return Arrays.stream(values())
                .map(Carrera::getNombre)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return nombre;
    }
}