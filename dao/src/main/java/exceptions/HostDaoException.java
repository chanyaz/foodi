package exceptions;

/**
 * Created by asus on 5/26/2017.
 */
public class HostDaoException extends DaoException {

    public HostDaoException(String message) {
        super(message);
    }

    public HostDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
