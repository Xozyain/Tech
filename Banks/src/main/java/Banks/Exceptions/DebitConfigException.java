package Banks.Exceptions;

/**
 * The type Debit config exception.
 */
public class DebitConfigException extends BaseException {
    private DebitConfigException(String message) {
        super(message);
    }

    /**
     * Negative percent debit config exception.
     *
     * @return the debit config exception
     */
    public static DebitConfigException NegativePercent() {
        return new DebitConfigException("Percent can not be negative");
    }
}
