package Banks.Exceptions;

/**
 * The type Deposit account exception.
 */
public class DepositAccountException extends BaseException {
    private DepositAccountException(String message) {
        super(message);
    }

    /**
     * Maxed out credit limit deposit account exception.
     *
     * @return the deposit account exception
     */
    public static DepositAccountException MaxedOutCreditLimit() {
        return new DepositAccountException("Maxed out credit limit");
    }

    /**
     * Can not find transaction deposit account exception.
     *
     * @return the deposit account exception
     */
    public static DepositAccountException CanNotFindTransaction() {
        return new DepositAccountException("Can not find transaction");
    }
}
