package Banks.Exceptions;

/**
 * The type Debit account exception.
 */
public class DebitAccountException extends BaseException {
    private DebitAccountException(String message) {
        super(message);
    }

    /**
     * Maxed out credit limit debit account exception.
     *
     * @return the debit account exception
     */
    public static DebitAccountException MaxedOutCreditLimit() {
        return new DebitAccountException("Maxed out credit limit");
    }

    /**
     * Can not find transaction debit account exception.
     *
     * @return the debit account exception
     */
    public static DebitAccountException CanNotFindTransaction() {
        return new DebitAccountException("Can not find transaction");
    }
}
