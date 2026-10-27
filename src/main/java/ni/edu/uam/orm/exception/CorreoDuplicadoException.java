package ni.edu.uam.orm.exception;


public class CorreoDuplicadoException extends AppException {

    public CorreoDuplicadoException(String correo) {
        super("Ya existe un estudiante con el correo: " + correo);
    }

    public CorreoDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}