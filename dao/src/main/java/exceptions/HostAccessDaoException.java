package exceptions;

/**
 * Created by asus on 5/27/2017.
 */
public class HostAccessDaoException extends DaoException {

    public HostAccessDaoException(String message) {
        super(message);
    }

    public HostAccessDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
