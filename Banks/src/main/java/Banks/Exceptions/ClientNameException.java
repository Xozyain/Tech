package Banks.Exceptions;

/**
 * The type Client name exception.
 */
public class ClientNameException extends BaseException {
    private ClientNameException(String message) {
        super(message);
    }

    /**
     * Null or white space client name exception.
     *
     * @return the client name exception
     */
    public static ClientNameException NullOrWhiteSpace() {
        return new ClientNameException("White space string");
    }
}
