package Banks.Exceptions;

/**
 * The type Money exception.
 */
public class MoneyException extends BaseException {
    private MoneyException(String message) {
        super(message);
    }

    /**
     * Negative money money exception.
     *
     * @return the money exception
     */
    public static MoneyException NegativeMoney() {
        return new MoneyException("Money can not be negative");
    }
}
