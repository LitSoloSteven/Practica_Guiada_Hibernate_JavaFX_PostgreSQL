package ni.edu.uam.orm.util;


import ni.edu.uam.orm.exception.ValidacionException;
import java.util.ArrayList;
import java.util.List;

public final class Validador {

    private Validador() {
    }

    public static String normalizar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    public static void requerido(String valor, String campo) {
        if (normalizar(valor).isEmpty()) {
            throw new ValidacionException(
                    campo + " es obligatorio."
            );
        }
    }

    public static void longitudMaxima(
            String valor, int max, String campo) {

        String texto = normalizar(valor);

        if (texto.length() > max) {
            throw new ValidacionException(
                    campo + " no puede superar los "
                            + max + " caracteres."
            );
        }
    }

    public static void formatoCorreo(String correo) {
        String texto = normalizar(correo);

        if (texto.isEmpty()) {
            return;
        }

        String regex =
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!texto.matches(regex)) {
            throw new ValidacionException(
                    "El formato del correo electrónico no es válido."
            );
        }
    }

    public static void formatoTelefono(String telefono) {
        String texto = normalizar(telefono);

        if (texto.isEmpty()) {
            return;
        }

        if (!texto.matches("^[0-9+\\- ]+$")) {
            throw new ValidacionException(
                    "El teléfono solo puede contener números, "
                            + "espacios, + y -."
            );
        }
    }

    public static void validarEstudiante(
            String nombres,
            String apellidos,
            String correo,
            String carrera,
            String telefono) {

        List<String> errores = new ArrayList<>();

        validarCampo(errores, () -> requerido(nombres, "Nombres"));
        validarCampo(errores, () -> requerido(apellidos, "Apellidos"));
        validarCampo(errores, () -> requerido(correo, "Correo"));
        validarCampo(errores, () -> requerido(carrera, "Carrera"));

        validarCampo(errores,
                () -> longitudMaxima(nombres, 100, "Nombres"));

        validarCampo(errores,
                () -> longitudMaxima(apellidos, 100, "Apellidos"));

        validarCampo(errores,
                () -> longitudMaxima(correo, 150, "Correo"));

        validarCampo(errores,
                () -> longitudMaxima(carrera, 100, "Carrera"));

        validarCampo(errores,
                () -> longitudMaxima(telefono, 20, "Teléfono"));

        validarCampo(errores, () -> formatoCorreo(correo));
        validarCampo(errores, () -> formatoTelefono(telefono));

        if (!errores.isEmpty()) {
            throw new ValidacionException(errores);
        }
    }

    private static void validarCampo(
            List<String> errores, Runnable validacion) {

        try {
            validacion.run();
        } catch (ValidacionException e) {
            errores.addAll(e.getErrores());
        }
    }
}