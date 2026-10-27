package ni.edu.uam.orm.exception;

public class RegistroNoEncontradoException extends AppException {

    public RegistroNoEncontradoException(Long id) {
        super("No se encontró un estudiante con el ID: " + id);
    }

    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}