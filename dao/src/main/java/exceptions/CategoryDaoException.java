package exceptions;

/**
 * Created by asus on 8/14/2017.
 */
public class CategoryDaoException extends DaoException {

    public CategoryDaoException(String message) {
        super(message);
    }

    public CategoryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
