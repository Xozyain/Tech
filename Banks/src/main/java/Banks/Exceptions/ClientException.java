package Banks.Exceptions;

/**
 * The type Client exception.
 */
public class ClientException extends BaseException {
    private ClientException(String message) {
        super(message);
    }

    /**
     * Build without name client exception.
     *
     * @return the client exception
     */
    public static ClientException BuildWithoutName() {
        return new ClientException("Can not build client without name");
    }
}
