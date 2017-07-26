package exceptions;

/**
 * Created by asus on 5/31/2017.
 */
public class UserDaoException extends DaoException {

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
