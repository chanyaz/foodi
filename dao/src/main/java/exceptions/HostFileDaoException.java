package exceptions;

/**
 * Created by asus on 7/31/2017.
 */
public class HostFileDaoException extends DaoException {

    public HostFileDaoException(String message) {
        super(message);
    }

    public HostFileDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
