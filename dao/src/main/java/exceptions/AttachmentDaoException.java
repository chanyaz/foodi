package exceptions;

/**
 * Created by asus on 7/31/2017.
 */
public class AttachmentDaoException extends DaoException {

    public AttachmentDaoException(String message) {
        super(message);
    }

    public AttachmentDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
