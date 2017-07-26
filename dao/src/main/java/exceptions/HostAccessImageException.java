package exceptions;

/**
 * Created by asus on 7/18/2017.
 */
public class HostAccessImageException extends DaoException {

    public HostAccessImageException(String message) {
        super(message);
    }

    public HostAccessImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
