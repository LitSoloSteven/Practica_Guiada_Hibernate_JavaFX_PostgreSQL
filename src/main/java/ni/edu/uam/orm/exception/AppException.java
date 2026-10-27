package ni.edu.uam.orm.exception;



public abstract class AppException extends RuntimeException {

    public AppException(String mensaje) {
        super(mensaje);
    }

    public AppException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}