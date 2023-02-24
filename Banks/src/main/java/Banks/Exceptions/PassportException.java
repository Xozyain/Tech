package Banks.Exceptions;

/**
 * The type Passport exception.
 */
public class PassportException extends BaseException {
    private PassportException(String message) {
        super(message);
    }

    /**
     * Wrong passport format passport exception.
     *
     * @return the passport exception
     */
    public static PassportException WrongPassportFormat() {
        return new PassportException("Wrong passport format");
    }
}
