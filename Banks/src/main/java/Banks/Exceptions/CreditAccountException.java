package Banks.Exceptions;

/**
 * The type Credit account exception.
 */
public class CreditAccountException extends BaseException {
    private CreditAccountException(String message) {
        super(message);
    }

    /**
     * Credit limit exceeded credit account exception.
     *
     * @return the credit account exception
     */
    public static CreditAccountException CreditLimitExceeded() {
        return new CreditAccountException("Credit limit exceeded");
    }

    /**
     * Maxed out credit limit credit account exception.
     *
     * @return the credit account exception
     */
    public static CreditAccountException MaxedOutCreditLimit() {
        return new CreditAccountException("Maxed out credit limit");
    }

    /**
     * Can not find transaction credit account exception.
     *
     * @return the credit account exception
     */
    public static CreditAccountException CanNotFindTransaction() {
        return new CreditAccountException("Can not find transaction");
    }
}
