package ni.edu.uam.orm.exception;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidacionException extends AppException {

    private final List<String> errores;

    public ValidacionException(String mensaje) {
        super(mensaje);
        this.errores = Collections.singletonList(mensaje);
    }

    public ValidacionException(List<String> errores) {
        super("Se encontraron errores de validación.");
        this.errores = Collections.unmodifiableList(
                new ArrayList<>(errores)
        );
    }

    public List<String> getErrores() {
        return errores;
    }
}